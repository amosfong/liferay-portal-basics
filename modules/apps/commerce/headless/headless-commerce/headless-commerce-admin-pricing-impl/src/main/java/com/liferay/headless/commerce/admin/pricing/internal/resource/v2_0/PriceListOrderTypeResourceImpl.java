/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListOrderTypeRel;
import com.liferay.commerce.price.list.service.CommercePriceListOrderTypeRelService;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.service.CommerceOrderTypeService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceListOrderType;
import com.liferay.headless.commerce.admin.pricing.internal.util.v2_0.PriceListOrderTypeUtil;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.PriceListOrderTypeResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.petra.string.StringPool;
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
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/price-list-order-type.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = PriceListOrderTypeResource.class
)
public class PriceListOrderTypeResourceImpl
	extends BasePriceListOrderTypeResourceImpl {

	@Override
	public void deletePriceListOrderType(Long id) throws Exception {
		_commercePriceListOrderTypeRelService.
			deleteCommercePriceListOrderTypeRel(id);
	}

	@Override
	public Page<PriceListOrderType>
			getPriceListByExternalReferenceCodePriceListOrderTypesPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommercePriceList commercePriceList =
			_commercePriceListService.
				fetchCommercePriceListByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"Unable to find price list with external reference code " +
					externalReferenceCode);
		}

		return Page.of(
			transform(
				_commercePriceListOrderTypeRelService.
					getCommercePriceListOrderTypeRels(
						commercePriceList.getCommercePriceListId(),
						StringPool.BLANK, pagination.getStartPosition(),
						pagination.getEndPosition(), null),
				commercePriceListOrderTypeRel -> _toPriceListOrderType(
					commercePriceListOrderTypeRel.
						getCommercePriceListOrderTypeRelId())),
			pagination,
			_commercePriceListOrderTypeRelService.
				getCommercePriceListOrderTypeRelsCount(
					commercePriceList.getCommercePriceListId(),
					StringPool.BLANK));
	}

	@NestedField(parentClass = PriceList.class, value = "priceListOrderTypes")
	@Override
	public Page<PriceListOrderType> getPriceListIdPriceListOrderTypesPage(
			Long id, String search, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_commercePriceListOrderTypeRelService.
					getCommercePriceListOrderTypeRels(
						id, search, pagination.getStartPosition(),
						pagination.getEndPosition(), null),
				commercePriceListOrderTypeRel -> _toPriceListOrderType(
					commercePriceListOrderTypeRel.
						getCommercePriceListOrderTypeRelId())),
			pagination,
			_commercePriceListOrderTypeRelService.
				getCommercePriceListOrderTypeRelsCount(id, search));
	}

	@Override
	public PriceListOrderType
			postPriceListByExternalReferenceCodePriceListOrderType(
				String externalReferenceCode,
				PriceListOrderType priceListOrderType)
		throws Exception {

		CommercePriceList commercePriceList =
			_commercePriceListService.
				fetchCommercePriceListByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"Unable to find price list with external reference code " +
					externalReferenceCode);
		}

		CommercePriceListOrderTypeRel commercePriceListOrderTypeRel =
			PriceListOrderTypeUtil.addCommercePriceListOrderTypeRel(
				_commerceOrderTypeService,
				_commercePriceListOrderTypeRelService, priceListOrderType,
				commercePriceList, _serviceContextHelper);

		return _toPriceListOrderType(
			commercePriceListOrderTypeRel.getCommercePriceListOrderTypeRelId());
	}

	@Override
	public PriceListOrderType postPriceListIdPriceListOrderType(
			Long id, PriceListOrderType priceListOrderType)
		throws Exception {

		CommercePriceListOrderTypeRel commercePriceListOrderTypeRel =
			PriceListOrderTypeUtil.addCommercePriceListOrderTypeRel(
				_commerceOrderTypeService,
				_commercePriceListOrderTypeRelService, priceListOrderType,
				_commercePriceListService.getCommercePriceList(id),
				_serviceContextHelper);

		return _toPriceListOrderType(
			commercePriceListOrderTypeRel.getCommercePriceListOrderTypeRelId());
	}

	private Map<String, Map<String, String>> _getActions(
			CommercePriceListOrderTypeRel commercePriceListOrderTypeRel)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commercePriceListOrderTypeRel.
					getCommercePriceListOrderTypeRelId(),
				"deletePriceListOrderType",
				_commercePriceListOrderTypeRelModelResourcePermission)
		).build();
	}

	private PriceListOrderType _toPriceListOrderType(
			Long commercePriceListOrderTypeRelId)
		throws Exception {

		CommercePriceListOrderTypeRel commercePriceListOrderTypeRel =
			_commercePriceListOrderTypeRelService.
				getCommercePriceListOrderTypeRel(
					commercePriceListOrderTypeRelId);

		return _priceListOrderTypeDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commercePriceListOrderTypeRel),
				_dtoConverterRegistry, commercePriceListOrderTypeRelId,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	@Reference
	private CommerceOrderTypeService _commerceOrderTypeService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.price.list.model.CommercePriceListOrderTypeRel)"
	)
	private ModelResourcePermission<CommercePriceListOrderTypeRel>
		_commercePriceListOrderTypeRelModelResourcePermission;

	@Reference
	private CommercePriceListOrderTypeRelService
		_commercePriceListOrderTypeRelService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.PriceListOrderTypeDTOConverter)"
	)
	private DTOConverter<CommercePriceListOrderTypeRel, PriceListOrderType>
		_priceListOrderTypeDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}