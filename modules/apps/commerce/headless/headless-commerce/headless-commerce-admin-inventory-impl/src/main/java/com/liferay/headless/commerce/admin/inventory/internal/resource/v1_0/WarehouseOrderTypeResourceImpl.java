/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.inventory.internal.resource.v1_0;

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseRel;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseRelService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.service.CommerceOrderTypeService;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseOrderType;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseOrderTypeResource;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Crescenzo Rega
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/warehouse-order-type.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = WarehouseOrderTypeResource.class
)
public class WarehouseOrderTypeResourceImpl
	extends BaseWarehouseOrderTypeResourceImpl {

	@Override
	public void deleteWarehouseOrderType(Long id) throws Exception {
		_commerceInventoryWarehouseRelService.
			deleteCommerceInventoryWarehouseRel(id);
	}

	@Override
	public Page<WarehouseOrderType>
			getWarehouseByExternalReferenceCodeWarehouseOrderTypesPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.
				fetchCommerceInventoryWarehouseByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find warehouse with external reference code " +
					externalReferenceCode);
		}

		return Page.of(
			transform(
				_commerceInventoryWarehouseRelService.
					getCommerceOrderTypeCommerceInventoryWarehouseRels(
						commerceInventoryWarehouse.
							getCommerceInventoryWarehouseId(),
						null, pagination.getStartPosition(),
						pagination.getEndPosition()),
				commerceInventoryWarehouseRel -> _toWarehouseOrderType(
					commerceInventoryWarehouseRel)),
			pagination,
			_commerceInventoryWarehouseRelService.
				getCommerceOrderTypeCommerceInventoryWarehouseRelsCount(
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId(),
					null));
	}

	@NestedField(parentClass = Warehouse.class, value = "warehouseOrderTypes")
	@Override
	public Page<WarehouseOrderType> getWarehouseIdWarehouseOrderTypesPage(
			Long id, String search, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.
				fetchByCommerceInventoryWarehouse(id);

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find warehouse with ID " + id);
		}

		return Page.of(
			transform(
				_commerceInventoryWarehouseRelService.
					getCommerceOrderTypeCommerceInventoryWarehouseRels(
						id, search, pagination.getStartPosition(),
						pagination.getEndPosition()),
				commerceInventoryWarehouseRel -> _toWarehouseOrderType(
					commerceInventoryWarehouseRel)),
			pagination,
			_commerceInventoryWarehouseRelService.
				getCommerceOrderTypeCommerceInventoryWarehouseRelsCount(
					id, search));
	}

	@Override
	public WarehouseOrderType
			postWarehouseByExternalReferenceCodeWarehouseOrderType(
				String externalReferenceCode,
				WarehouseOrderType warehouseOrderType)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.
				fetchCommerceInventoryWarehouseByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find warehouse with external reference code " +
					externalReferenceCode);
		}

		CommerceOrderType commerceOrderType = _getCommerceOrderType(
			warehouseOrderType);

		return _toWarehouseOrderType(
			_commerceInventoryWarehouseRelService.
				addCommerceInventoryWarehouseRel(
					CommerceOrderType.class.getName(),
					commerceOrderType.getCommerceOrderTypeId(),
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId()));
	}

	@Override
	public WarehouseOrderType postWarehouseIdWarehouseOrderType(
			Long id, WarehouseOrderType warehouseOrderType)
		throws Exception {

		CommerceOrderType commerceOrderType = _getCommerceOrderType(
			warehouseOrderType);

		return _toWarehouseOrderType(
			_commerceInventoryWarehouseRelService.
				addCommerceInventoryWarehouseRel(
					CommerceOrderType.class.getName(),
					commerceOrderType.getCommerceOrderTypeId(), id));
	}

	private Map<String, Map<String, String>> _getActions(
		CommerceInventoryWarehouseRel commerceInventoryWarehouseRel) {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commerceInventoryWarehouseRel.getCommerceInventoryWarehouseId(),
				"deleteWarehouseOrderType",
				_commerceInventoryWarehouseModelResourcePermission)
		).build();
	}

	private CommerceOrderType _getCommerceOrderType(
			WarehouseOrderType warehouseOrderType)
		throws Exception {

		CommerceOrderType commerceOrderType = null;

		if (warehouseOrderType.getOrderTypeId() > 0) {
			commerceOrderType = _commerceOrderTypeService.getCommerceOrderType(
				warehouseOrderType.getOrderTypeId());
		}
		else {
			commerceOrderType =
				_commerceOrderTypeService.
					fetchCommerceOrderTypeByExternalReferenceCode(
						warehouseOrderType.getOrderTypeExternalReferenceCode(),
						contextCompany.getCompanyId());
		}

		return commerceOrderType;
	}

	private WarehouseOrderType _toWarehouseOrderType(
			CommerceInventoryWarehouseRel commerceInventoryWarehouseRel)
		throws Exception {

		return _warehouseOrderTypeDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceInventoryWarehouseRel),
				_dtoConverterRegistry,
				commerceInventoryWarehouseRel.
					getCommerceInventoryWarehouseRelId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)"
	)
	private ModelResourcePermission<CommerceInventoryWarehouse>
		_commerceInventoryWarehouseModelResourcePermission;

	@Reference
	private CommerceInventoryWarehouseRelService
		_commerceInventoryWarehouseRelService;

	@Reference
	private CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;

	@Reference
	private CommerceOrderTypeService _commerceOrderTypeService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.inventory.internal.dto.v1_0.converter.WarehouseOrderTypeDTOConverter)"
	)
	private DTOConverter<CommerceInventoryWarehouseRel, WarehouseOrderType>
		_warehouseOrderTypeDTOConverter;

}