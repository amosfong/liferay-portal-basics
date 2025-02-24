/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.commerce.pricing.exception.NoSuchPriceModifierException;
import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.model.CommercePriceModifierRel;
import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.service.CommercePriceModifierRelService;
import com.liferay.commerce.pricing.service.CommercePriceModifierService;
import com.liferay.commerce.pricing.service.CommercePricingClassService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceModifier;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceModifierProductGroup;
import com.liferay.headless.commerce.admin.pricing.internal.util.v2_0.PriceModifierProductGroupUtil;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.PriceModifierProductGroupResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/price-modifier-product-group.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = PriceModifierProductGroupResource.class
)
public class PriceModifierProductGroupResourceImpl
	extends BasePriceModifierProductGroupResourceImpl {

	@Override
	public void deletePriceModifierProductGroup(Long id) throws Exception {
		_commercePriceModifierRelService.deleteCommercePriceModifierRel(id);
	}

	@Override
	public Page<PriceModifierProductGroup>
			getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommercePriceModifier commercePriceModifier =
			_commercePriceModifierService.
				fetchCommercePriceModifierByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commercePriceModifier == null) {
			throw new NoSuchPriceModifierException(
				"Unable to find price modifier with external reference code " +
					externalReferenceCode);
		}

		List<CommercePriceModifierRel> commercePriceModifierRels =
			_commercePriceModifierRelService.getCommercePriceModifierRels(
				commercePriceModifier.getCommercePriceModifierId(),
				CommercePricingClass.class.getName(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalCount =
			_commercePriceModifierRelService.getCommercePriceModifierRelsCount(
				commercePriceModifier.getCommercePriceModifierId(),
				CommercePricingClass.class.getName());

		return Page.of(
			_toPriceModifierProductGroups(commercePriceModifierRels),
			pagination, totalCount);
	}

	@NestedField(
		parentClass = PriceModifier.class, value = "priceModifierProductGroups"
	)
	@Override
	public Page<PriceModifierProductGroup>
			getPriceModifierIdPriceModifierProductGroupsPage(
				Long id, String search, Filter filter, Pagination pagination,
				Sort[] sorts)
		throws Exception {

		List<CommercePriceModifierRel> commercePriceModifierRels =
			_commercePriceModifierRelService.
				getCommercePricingClassesCommercePriceModifierRels(
					id, search, pagination.getStartPosition(),
					pagination.getEndPosition());

		int totalCount =
			_commercePriceModifierRelService.
				getCommercePricingClassesCommercePriceModifierRelsCount(
					id, search);

		return Page.of(
			_toPriceModifierProductGroups(commercePriceModifierRels),
			pagination, totalCount);
	}

	@Override
	public PriceModifierProductGroup
			postPriceModifierByExternalReferenceCodePriceModifierProductGroup(
				String externalReferenceCode,
				PriceModifierProductGroup priceModifierProductGroup)
		throws Exception {

		CommercePriceModifier commercePriceModifier =
			_commercePriceModifierService.
				fetchCommercePriceModifierByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commercePriceModifier == null) {
			throw new NoSuchPriceModifierException(
				"Unable to find price modifier with external reference code " +
					externalReferenceCode);
		}

		CommercePriceModifierRel commercePriceModifierRel =
			PriceModifierProductGroupUtil.addCommercePriceModifierRel(
				_commercePricingClassService, _commercePriceModifierRelService,
				priceModifierProductGroup, commercePriceModifier,
				_serviceContextHelper);

		return _toPriceModifierProductGroup(
			commercePriceModifierRel.getCommercePriceModifierRelId());
	}

	@Override
	public PriceModifierProductGroup
			postPriceModifierIdPriceModifierProductGroup(
				Long id, PriceModifierProductGroup priceModifierProductGroup)
		throws Exception {

		CommercePriceModifierRel commercePriceModifierRel =
			PriceModifierProductGroupUtil.addCommercePriceModifierRel(
				_commercePricingClassService, _commercePriceModifierRelService,
				priceModifierProductGroup,
				_commercePriceModifierService.getCommercePriceModifier(id),
				_serviceContextHelper);

		return _toPriceModifierProductGroup(
			commercePriceModifierRel.getCommercePriceModifierRelId());
	}

	private Map<String, Map<String, String>> _getActions(
			CommercePriceModifierRel commercePriceModifierRel)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commercePriceModifierRel.getCommercePriceModifierRelId(),
				"deletePriceModifierProductGroup",
				_commercePriceModifierRelModelResourcePermission)
		).build();
	}

	private PriceModifierProductGroup _toPriceModifierProductGroup(
			Long commercePriceModifierRelId)
		throws Exception {

		CommercePriceModifierRel commercePriceModifierRel =
			_commercePriceModifierRelService.getCommercePriceModifierRel(
				commercePriceModifierRelId);

		return _priceModifierProductGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commercePriceModifierRel), _dtoConverterRegistry,
				commercePriceModifierRelId,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	private List<PriceModifierProductGroup> _toPriceModifierProductGroups(
			List<CommercePriceModifierRel> commercePriceModifierRels)
		throws Exception {

		List<PriceModifierProductGroup> priceModifierProductGroups =
			new ArrayList<>();

		for (CommercePriceModifierRel commercePriceModifierRel :
				commercePriceModifierRels) {

			priceModifierProductGroups.add(
				_toPriceModifierProductGroup(
					commercePriceModifierRel.getCommercePriceModifierRelId()));
		}

		return priceModifierProductGroups;
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.pricing.model.CommercePriceModifierRel)"
	)
	private ModelResourcePermission<CommercePriceModifierRel>
		_commercePriceModifierRelModelResourcePermission;

	@Reference
	private CommercePriceModifierRelService _commercePriceModifierRelService;

	@Reference
	private CommercePriceModifierService _commercePriceModifierService;

	@Reference
	private CommercePricingClassService _commercePricingClassService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.PriceModifierProductGroupDTOConverter)"
	)
	private DTOConverter<CommercePriceModifierRel, PriceModifierProductGroup>
		_priceModifierProductGroupDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}