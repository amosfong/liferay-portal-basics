/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.channel.internal.resource.v1_0;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.service.CommerceOrderTypeService;
import com.liferay.commerce.shipping.engine.fixed.exception.NoSuchShippingFixedOptionException;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionQualifier;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionQualifierService;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionService;
import com.liferay.headless.commerce.admin.channel.dto.v1_0.ShippingFixedOptionOrderType;
import com.liferay.headless.commerce.admin.channel.resource.v1_0.ShippingFixedOptionOrderTypeResource;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/shipping-fixed-option-order-type.properties",
	scope = ServiceScope.PROTOTYPE,
	service = ShippingFixedOptionOrderTypeResource.class
)
public class ShippingFixedOptionOrderTypeResourceImpl
	extends BaseShippingFixedOptionOrderTypeResourceImpl {

	@Override
	public void deleteShippingFixedOptionOrderType(Long id) throws Exception {
		_commerceShippingFixedOptionQualifierService.
			deleteCommerceShippingFixedOptionQualifier(id);
	}

	@Override
	public Page<ShippingFixedOptionOrderType>
			getShippingFixedOptionIdShippingFixedOptionOrderTypesPage(
				Long id, String search, Filter filter, Pagination pagination,
				Sort[] sorts)
		throws Exception {

		CommerceShippingFixedOption commerceShippingFixedOption =
			_commerceShippingFixedOptionService.
				fetchCommerceShippingFixedOption(id);

		if (commerceShippingFixedOption == null) {
			throw new NoSuchShippingFixedOptionException(
				"Unable to find shipping fixed option with ID " + id);
		}

		return Page.of(
			transform(
				_commerceShippingFixedOptionQualifierService.
					getCommerceOrderTypeCommerceShippingFixedOptionQualifiers(
						id, search, pagination.getStartPosition(),
						pagination.getEndPosition()),
				corEntryRel -> _toShippingFixedOptionOrderType(corEntryRel)),
			pagination,
			_commerceShippingFixedOptionQualifierService.
				getCommerceOrderTypeCommerceShippingFixedOptionQualifiersCount(
					id, search));
	}

	@Override
	public ShippingFixedOptionOrderType
			postShippingFixedOptionIdShippingFixedOptionOrderType(
				Long id,
				ShippingFixedOptionOrderType shippingFixedOptionOrderType)
		throws Exception {

		CommerceOrderType commerceOrderType = _getCommerceOrderType(
			shippingFixedOptionOrderType);

		return _toShippingFixedOptionOrderType(
			_commerceShippingFixedOptionQualifierService.
				addCommerceShippingFixedOptionQualifier(
					CommerceOrderType.class.getName(),
					commerceOrderType.getCommerceOrderTypeId(), id));
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceShippingFixedOptionQualifier
				commerceShippingFixedOptionQualifier)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commerceShippingFixedOptionQualifier.
					getCommerceShippingFixedOptionQualifierId(),
				"deleteShippingFixedOptionOrderType",
				_commerceShippingFixedOptionQualifierModelResourcePermission)
		).build();
	}

	private CommerceOrderType _getCommerceOrderType(
			ShippingFixedOptionOrderType shippingFixedOptionOrderType)
		throws Exception {

		CommerceOrderType commerceOrderType = null;

		if (shippingFixedOptionOrderType.getOrderTypeId() > 0) {
			commerceOrderType = _commerceOrderTypeService.getCommerceOrderType(
				shippingFixedOptionOrderType.getOrderTypeId());
		}
		else {
			commerceOrderType =
				_commerceOrderTypeService.
					fetchCommerceOrderTypeByExternalReferenceCode(
						shippingFixedOptionOrderType.
							getOrderTypeExternalReferenceCode(),
						contextCompany.getCompanyId());
		}

		return commerceOrderType;
	}

	private ShippingFixedOptionOrderType _toShippingFixedOptionOrderType(
			CommerceShippingFixedOptionQualifier
				commerceShippingFixedOptionQualifier)
		throws Exception {

		return _shippingFixedOptionOrderTypeDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceShippingFixedOptionQualifier),
				_dtoConverterRegistry,
				commerceShippingFixedOptionQualifier.
					getCommerceShippingFixedOptionQualifierId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	@Reference
	private CommerceOrderTypeService _commerceOrderTypeService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionQualifier)"
	)
	private ModelResourcePermission<CommerceShippingFixedOptionQualifier>
		_commerceShippingFixedOptionQualifierModelResourcePermission;

	@Reference
	private CommerceShippingFixedOptionQualifierService
		_commerceShippingFixedOptionQualifierService;

	@Reference
	private CommerceShippingFixedOptionService
		_commerceShippingFixedOptionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.channel.internal.dto.v1_0.converter.ShippingFixedOptionOrderTypeDTOConverter)"
	)
	private DTOConverter
		<CommerceShippingFixedOptionQualifier, ShippingFixedOptionOrderType>
			_shippingFixedOptionOrderTypeDTOConverter;

}