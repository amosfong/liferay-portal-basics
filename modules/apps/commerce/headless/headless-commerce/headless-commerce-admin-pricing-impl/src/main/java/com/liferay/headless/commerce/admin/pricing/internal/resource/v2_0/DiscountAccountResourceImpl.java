/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.account.service.AccountEntryService;
import com.liferay.commerce.discount.exception.NoSuchDiscountException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountAccountRel;
import com.liferay.commerce.discount.service.CommerceDiscountAccountRelService;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.Discount;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountAccount;
import com.liferay.headless.commerce.admin.pricing.internal.util.v2_0.DiscountAccountUtil;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.DiscountAccountResource;
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
	properties = "OSGI-INF/liferay/rest/v2_0/discount-account.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = DiscountAccountResource.class
)
public class DiscountAccountResourceImpl
	extends BaseDiscountAccountResourceImpl {

	@Override
	public void deleteDiscountAccount(Long id) throws Exception {
		_commerceDiscountAccountRelService.deleteCommerceDiscountAccountRel(id);
	}

	@Override
	public Page<DiscountAccount>
			getDiscountByExternalReferenceCodeDiscountAccountsPage(
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

		List<CommerceDiscountAccountRel> commerceDiscountAccountRels =
			_commerceDiscountAccountRelService.getCommerceDiscountAccountRels(
				commerceDiscount.getCommerceDiscountId(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalCount =
			_commerceDiscountAccountRelService.
				getCommerceDiscountAccountRelsCount(
					commerceDiscount.getCommerceDiscountId());

		return Page.of(
			_toDiscountAccounts(commerceDiscountAccountRels), pagination,
			totalCount);
	}

	@NestedField(parentClass = Discount.class, value = "discountAccounts")
	@Override
	public Page<DiscountAccount> getDiscountIdDiscountAccountsPage(
			Long id, String search, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception {

		List<CommerceDiscountAccountRel> commerceDiscountAccountRels =
			_commerceDiscountAccountRelService.getCommerceDiscountAccountRels(
				id, search, pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalCount =
			_commerceDiscountAccountRelService.
				getCommerceDiscountAccountRelsCount(id, search);

		return Page.of(
			_toDiscountAccounts(commerceDiscountAccountRels), pagination,
			totalCount);
	}

	@Override
	public DiscountAccount postDiscountByExternalReferenceCodeDiscountAccount(
			String externalReferenceCode, DiscountAccount discountAccount)
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

		CommerceDiscountAccountRel commerceDiscountAccountRel =
			DiscountAccountUtil.addCommerceDiscountAccountRel(
				_accountEntryService, _commerceDiscountAccountRelService,
				discountAccount, commerceDiscount, _serviceContextHelper);

		return _toDiscountAccount(
			commerceDiscountAccountRel.getCommerceDiscountAccountRelId());
	}

	@Override
	public DiscountAccount postDiscountIdDiscountAccount(
			Long id, DiscountAccount discountAccount)
		throws Exception {

		CommerceDiscountAccountRel commerceDiscountAccountRel =
			DiscountAccountUtil.addCommerceDiscountAccountRel(
				_accountEntryService, _commerceDiscountAccountRelService,
				discountAccount,
				_commerceDiscountService.getCommerceDiscount(id),
				_serviceContextHelper);

		return _toDiscountAccount(
			commerceDiscountAccountRel.getCommerceDiscountAccountRelId());
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceDiscountAccountRel commerceDiscountAccountRel)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commerceDiscountAccountRel.getCommerceDiscountAccountRelId(),
				"deleteDiscountAccount",
				_commerceDiscountAccountRelModelResourcePermission)
		).build();
	}

	private DiscountAccount _toDiscountAccount(
			Long commerceDiscountAccountRelId)
		throws Exception {

		CommerceDiscountAccountRel commerceDiscountAccountRel =
			_commerceDiscountAccountRelService.getCommerceDiscountAccountRel(
				commerceDiscountAccountRelId);

		return _discountAccountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceDiscountAccountRel), _dtoConverterRegistry,
				commerceDiscountAccountRelId,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	private List<DiscountAccount> _toDiscountAccounts(
			List<CommerceDiscountAccountRel> commerceDiscountAccountRels)
		throws Exception {

		List<DiscountAccount> discountAccounts = new ArrayList<>();

		for (CommerceDiscountAccountRel commerceDiscountAccountRel :
				commerceDiscountAccountRels) {

			discountAccounts.add(
				_toDiscountAccount(
					commerceDiscountAccountRel.
						getCommerceDiscountAccountRelId()));
		}

		return discountAccounts;
	}

	@Reference
	private AccountEntryService _accountEntryService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.discount.model.CommerceDiscountAccountRel)"
	)
	private ModelResourcePermission<CommerceDiscountAccountRel>
		_commerceDiscountAccountRelModelResourcePermission;

	@Reference
	private CommerceDiscountAccountRelService
		_commerceDiscountAccountRelService;

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.DiscountAccountDTOConverter)"
	)
	private DTOConverter<CommerceDiscountAccountRel, DiscountAccount>
		_discountAccountDTOConverter;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}