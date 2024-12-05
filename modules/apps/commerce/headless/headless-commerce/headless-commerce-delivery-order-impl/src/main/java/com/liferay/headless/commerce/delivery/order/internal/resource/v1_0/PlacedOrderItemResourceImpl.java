/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.order.internal.resource.v1_0;

import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.exception.NoSuchOrderItemException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.headless.commerce.delivery.order.dto.v1_0.PlacedOrder;
import com.liferay.headless.commerce.delivery.order.dto.v1_0.PlacedOrderItem;
import com.liferay.headless.commerce.delivery.order.internal.dto.v1_0.converter.PlacedOrderItemDTOConverterContext;
import com.liferay.headless.commerce.delivery.order.internal.odate.entity.v1_0.PlacedOrderItemEntityModel;
import com.liferay.headless.commerce.delivery.order.resource.v1_0.PlacedOrderItemResource;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Andrea Sbarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/placed-order-item.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = PlacedOrderItemResource.class
)
public class PlacedOrderItemResourceImpl
	extends BasePlacedOrderItemResourceImpl {

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Page<PlacedOrderItem>
			getPlacedOrderByExternalReferenceCodePlacedOrderItemsPage(
				String externalReferenceCode, String search, Long skuId,
				Pagination pagination, Sort[] sorts)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchCommerceOrderByExternalReferenceCode(
				externalReferenceCode, contextCompany.getCompanyId());

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find order with external reference code " +
					externalReferenceCode);
		}

		return getPlacedOrderPlacedOrderItemsPage(
			commerceOrder.getCommerceOrderId(), search, skuId, pagination,
			sorts);
	}

	@Override
	public PlacedOrderItem getPlacedOrderItem(Long placedOrderItemId)
		throws Exception {

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.getCommerceOrderItem(placedOrderItemId);

		CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

		if (commerceOrder.isOpen()) {
			throw new NoSuchOrderException();
		}

		return _toPlacedOrderItem(
			commerceOrder.getCommerceAccountId(), commerceOrderItem);
	}

	@Override
	public PlacedOrderItem getPlacedOrderItemByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.
				fetchCommerceOrderItemByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commerceOrderItem == null) {
			throw new NoSuchOrderItemException(
				"Unable to find order item with external reference code " +
					externalReferenceCode);
		}

		return getPlacedOrderItem(commerceOrderItem.getCommerceOrderItemId());
	}

	@NestedField(parentClass = PlacedOrder.class, value = "placedOrderItems")
	@Override
	public Page<PlacedOrderItem> getPlacedOrderPlacedOrderItemsPage(
			@NestedFieldId("id") Long placedOrderId, String search, Long skuId,
			Pagination pagination, Sort[] sorts)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			placedOrderId);

		if (commerceOrder.isOpen()) {
			throw new NoSuchOrderException();
		}

		Sort sort = new Sort();

		if (sorts != null) {
			sort = sorts[0];
		}

		int startPosition = QueryUtil.ALL_POS;
		int endPosition = QueryUtil.ALL_POS;

		if (pagination != null) {
			startPosition = pagination.getStartPosition();
			endPosition = pagination.getEndPosition();
		}

		BaseModelSearchResult<CommerceOrderItem> searchResult =
			_commerceOrderItemService.searchCommerceOrderItems(
				placedOrderId, search, startPosition, endPosition, sort);

		List<PlacedOrderItem> placedOrderItems = _filterPlacedOrderItems(
			transform(
				searchResult.getBaseModels(),
				commerceOrderItem -> _toPlacedOrderItem(
					commerceOrder.getCommerceAccountId(), commerceOrderItem)));

		return Page.of(
			placedOrderItems, pagination,
			Math.max(
				_commerceOrderItemService.getCommerceOrderItemsCount(
					placedOrderId),
				placedOrderItems.size()));
	}

	private List<PlacedOrderItem> _filterPlacedOrderItems(
		List<PlacedOrderItem> placedOrderItems) {

		Map<Long, PlacedOrderItem> placedOrderItemMap = new HashMap<>();

		for (PlacedOrderItem placedOrderItem : placedOrderItems) {
			placedOrderItemMap.put(placedOrderItem.getId(), placedOrderItem);
		}

		for (PlacedOrderItem placedOrderItem : placedOrderItems) {
			Long parentOrderItemId = placedOrderItem.getParentOrderItemId();

			if (parentOrderItemId == null) {
				continue;
			}

			PlacedOrderItem parentOrderItem = placedOrderItemMap.get(
				parentOrderItemId);

			if (parentOrderItem == null) {
				continue;
			}

			PlacedOrderItem[] parentOrderItemPlacedOrderItems =
				parentOrderItem.getPlacedOrderItems();

			parentOrderItem.setPlacedOrderItems(
				() -> {
					if (parentOrderItemPlacedOrderItems == null) {
						return new PlacedOrderItem[] {placedOrderItem};
					}

					return ArrayUtil.append(
						parentOrderItemPlacedOrderItems, placedOrderItem);
				});

			placedOrderItemMap.remove(placedOrderItem.getId());
		}

		return new ArrayList(placedOrderItemMap.values());
	}

	private PlacedOrderItem _toPlacedOrderItem(
			long commerceAccountId, CommerceOrderItem commerceOrderItem)
		throws Exception {

		return _placedOrderItemDTOConverter.toDTO(
			new PlacedOrderItemDTOConverterContext(
				commerceAccountId, commerceOrderItem.getCommerceOrderItemId(),
				contextAcceptLanguage.getPreferredLocale()));
	}

	private static final EntityModel _entityModel =
		new PlacedOrderItemEntityModel();

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.delivery.order.internal.dto.v1_0.converter.PlacedOrderItemDTOConverter)"
	)
	private DTOConverter<CommerceOrderItem, PlacedOrderItem>
		_placedOrderItemDTOConverter;

}