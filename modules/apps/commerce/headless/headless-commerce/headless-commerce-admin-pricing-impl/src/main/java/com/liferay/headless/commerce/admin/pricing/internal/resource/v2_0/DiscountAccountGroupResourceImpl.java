/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.account.service.AccountGroupService;
import com.liferay.commerce.discount.exception.NoSuchDiscountException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelService;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.Discount;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountAccountGroup;
import com.liferay.headless.commerce.admin.pricing.internal.util.v2_0.DiscountAccountGroupUtil;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.DiscountAccountGroupResource;
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
 * @author Riccardo Alberti
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/discount-account-group.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = DiscountAccountGroupResource.class
)
public class DiscountAccountGroupResourceImpl
	extends BaseDiscountAccountGroupResourceImpl {

	@Override
	public void deleteDiscountAccountGroup(Long id) throws Exception {
		_commerceDiscountCommerceAccountGroupRelService.
			deleteCommerceDiscountCommerceAccountGroupRel(id);
	}

	@Override
	public Page<DiscountAccountGroup>
			getDiscountByExternalReferenceCodeDiscountAccountGroupsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.
				fetchCommerceDiscountByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commerceDiscount == null) {
			throw new NoSuchDiscountException(
				"Unable to find discount with external reference code " +
					externalReferenceCode);
		}

		List<CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels =
				_commerceDiscountCommerceAccountGroupRelService.
					getCommerceDiscountCommerceAccountGroupRels(
						commerceDiscount.getCommerceDiscountId(),
						pagination.getStartPosition(),
						pagination.getEndPosition(), null);

		int totalCount =
			_commerceDiscountCommerceAccountGroupRelService.
				getCommerceDiscountCommerceAccountGroupRelsCount(
					commerceDiscount.getCommerceDiscountId());

		return Page.of(
			_toDiscountAccountGroups(commerceDiscountCommerceAccountGroupRels),
			pagination, totalCount);
	}

	@NestedField(parentClass = Discount.class, value = "discountAccountGroups")
	@Override
	public Page<DiscountAccountGroup> getDiscountIdDiscountAccountGroupsPage(
			Long id, String search, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception {

		List<CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels =
				_commerceDiscountCommerceAccountGroupRelService.
					getCommerceDiscountCommerceAccountGroupRels(
						id, search, pagination.getStartPosition(),
						pagination.getEndPosition());

		int totalCount =
			_commerceDiscountCommerceAccountGroupRelService.
				getCommerceDiscountCommerceAccountGroupRelsCount(id, search);

		return Page.of(
			_toDiscountAccountGroups(commerceDiscountCommerceAccountGroupRels),
			pagination, totalCount);
	}

	@Override
	public DiscountAccountGroup
			postDiscountByExternalReferenceCodeDiscountAccountGroup(
				String externalReferenceCode,
				DiscountAccountGroup discountAccountGroup)
		throws Exception {

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.
				fetchCommerceDiscountByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commerceDiscount == null) {
			throw new NoSuchDiscountException(
				"Unable to find discount with external reference code " +
					externalReferenceCode);
		}

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				DiscountAccountGroupUtil.addCommerceDiscountAccountGroupRel(
					_accountGroupService,
					_commerceDiscountCommerceAccountGroupRelService,
					discountAccountGroup, commerceDiscount,
					_serviceContextHelper);

		return _toDiscountAccountGroup(
			commerceDiscountCommerceAccountGroupRel.
				getCommerceDiscountCommerceAccountGroupRelId());
	}

	@Override
	public DiscountAccountGroup postDiscountIdDiscountAccountGroup(
			Long id, DiscountAccountGroup discountAccountGroup)
		throws Exception {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				DiscountAccountGroupUtil.addCommerceDiscountAccountGroupRel(
					_accountGroupService,
					_commerceDiscountCommerceAccountGroupRelService,
					discountAccountGroup,
					_commerceDiscountService.getCommerceDiscount(id),
					_serviceContextHelper);

		return _toDiscountAccountGroup(
			commerceDiscountCommerceAccountGroupRel.
				getCommerceDiscountCommerceAccountGroupRelId());
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commerceDiscountCommerceAccountGroupRel.
					getCommerceDiscountCommerceAccountGroupRelId(),
				"deleteDiscountAccountGroup",
				_commerceDiscountCommerceAccountGroupRelModelResourcePermission)
		).build();
	}

	private DiscountAccountGroup _toDiscountAccountGroup(
			Long commerceDiscountCommerceAccountGroupRelId)
		throws Exception {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				_commerceDiscountCommerceAccountGroupRelService.
					getCommerceDiscountCommerceAccountGroupRel(
						commerceDiscountCommerceAccountGroupRelId);

		return _discountAccountGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceDiscountCommerceAccountGroupRel),
				_dtoConverterRegistry,
				commerceDiscountCommerceAccountGroupRelId,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	private List<DiscountAccountGroup> _toDiscountAccountGroups(
			List<CommerceDiscountCommerceAccountGroupRel>
				commerceDiscountCommerceAccountGroupRels)
		throws Exception {

		List<DiscountAccountGroup> discountAccountGroups = new ArrayList<>();

		for (CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel :
					commerceDiscountCommerceAccountGroupRels) {

			discountAccountGroups.add(
				_toDiscountAccountGroup(
					commerceDiscountCommerceAccountGroupRel.
						getCommerceDiscountCommerceAccountGroupRelId()));
		}

		return discountAccountGroups;
	}

	@Reference
	private AccountGroupService _accountGroupService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel)"
	)
	private ModelResourcePermission<CommerceDiscountCommerceAccountGroupRel>
		_commerceDiscountCommerceAccountGroupRelModelResourcePermission;

	@Reference
	private CommerceDiscountCommerceAccountGroupRelService
		_commerceDiscountCommerceAccountGroupRelService;

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.DiscountAccountGroupDTOConverter)"
	)
	private DTOConverter
		<CommerceDiscountCommerceAccountGroupRel, DiscountAccountGroup>
			_discountAccountGroupDTOConverter;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}