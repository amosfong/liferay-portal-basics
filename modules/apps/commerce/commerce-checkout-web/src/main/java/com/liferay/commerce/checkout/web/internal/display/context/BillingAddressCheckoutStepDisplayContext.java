/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.checkout.web.internal.display.context;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountRoleLocalService;
import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.constants.CommerceChannelAccountEntryRelConstants;
import com.liferay.commerce.product.model.CommerceChannelAccountEntryRel;
import com.liferay.commerce.product.service.CommerceChannelAccountEntryRelLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
public class BillingAddressCheckoutStepDisplayContext
	extends BaseAddressCheckoutStepDisplayContext {

	public BillingAddressCheckoutStepDisplayContext(
		AccountEntryLocalService accountEntryLocalService,
		AccountRoleLocalService accountRoleLocalService,
		ModelResourcePermission<AccountEntry>
			accountEntryModelResourcePermission,
		CommerceAddressService commerceAddressService,
		CommerceChannelAccountEntryRelLocalService
			commerceChannelAccountEntryRelLocalService,
		CommerceChannelLocalService commerceChannelLocalService,
		HttpServletRequest httpServletRequest,
		PortletResourcePermission portletResourcePermission) {

		super(
			accountEntryLocalService, accountEntryModelResourcePermission,
			accountRoleLocalService, commerceAddressService,
			commerceChannelAccountEntryRelLocalService,
			commerceChannelLocalService, httpServletRequest,
			portletResourcePermission);
	}

	@Override
	public List<CommerceAddress> getCommerceAddresses() throws PortalException {
		CommerceContext commerceContext = getCommerceContext();
		CommerceOrder commerceOrder = getCommerceOrder();

		return commerceAddressService.getBillingCommerceAddresses(
			commerceContext.getCommerceChannelId(),
			AccountEntry.class.getName(), commerceOrder.getCommerceAccountId(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public String getCommerceCountrySelectionColumnName() {
		return "billingAllowed";
	}

	@Override
	public String getCommerceCountrySelectionMethodName() {
		return "get-billing-countries";
	}

	@Override
	public long getDefaultCommerceAddressId(long commerceChannelId)
		throws PortalException {

		long billingAddressId = 0;

		CommerceOrder commerceOrder = getCommerceOrder();

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		if (accountEntry == null) {
			return billingAddressId;
		}

		CommerceChannelAccountEntryRel commerceChannelAccountEntryRel =
			commerceChannelAccountEntryRelLocalService.
				fetchCommerceChannelAccountEntryRel(
					accountEntry.getAccountEntryId(), commerceChannelId,
					CommerceChannelAccountEntryRelConstants.
						TYPE_BILLING_ADDRESS);

		if (commerceChannelAccountEntryRel == null) {
			return billingAddressId;
		}

		CommerceAddress commerceAddress =
			commerceAddressService.fetchCommerceAddress(
				commerceChannelAccountEntryRel.getClassPK());

		if (commerceAddress != null) {
			billingAddressId = commerceAddress.getCommerceAddressId();
		}

		return billingAddressId;
	}

	@Override
	public String getParamName() {
		return CommerceCheckoutWebKeys.BILLING_ADDRESS_PARAM_NAME;
	}

	@Override
	public String getTitle() {
		return "billing-address";
	}

}