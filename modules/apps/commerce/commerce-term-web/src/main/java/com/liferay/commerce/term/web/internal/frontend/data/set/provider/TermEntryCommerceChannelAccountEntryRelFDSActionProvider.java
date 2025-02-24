/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.term.web.internal.frontend.data.set.provider;

import com.liferay.account.constants.AccountPortletKeys;
import com.liferay.account.model.AccountEntry;
import com.liferay.commerce.constants.CommerceAccountActionKeys;
import com.liferay.commerce.term.web.internal.entry.constants.CommerceTermEntryFDSNames;
import com.liferay.commerce.term.web.internal.model.TermEntry;
import com.liferay.frontend.data.set.provider.FDSActionProvider;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.List;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"fds.data.provider.key=" + CommerceTermEntryFDSNames.ACCOUNT_ENTRY_DELIVERY_TERM_ENTRIES,
		"fds.data.provider.key=" + CommerceTermEntryFDSNames.ACCOUNT_ENTRY_PAYMENT_TERM_ENTRIES
	},
	service = FDSActionProvider.class
)
public class TermEntryCommerceChannelAccountEntryRelFDSActionProvider
	implements FDSActionProvider {

	@Override
	public List<DropdownItem> getDropdownItems(
			long groupId, HttpServletRequest httpServletRequest, Object model)
		throws PortalException {

		TermEntry termEntry = (TermEntry)model;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		return DropdownItemListBuilder.add(
			() -> _accountEntryModelResourcePermission.contains(
				permissionChecker, termEntry.getAccountEntryId(),
				CommerceAccountActionKeys.MANAGE_CHANNEL_DEFAULTS),
			dropdownItem -> {
				dropdownItem.setHref(
					_getCommerceChannelAccountEntryRelEditURL(
						termEntry.getAccountEntryId(),
						termEntry.getCommerceChannelAccountEntryRelId(),
						httpServletRequest, termEntry.getType()));
				dropdownItem.setLabel(
					_language.get(httpServletRequest, Constants.EDIT));
				dropdownItem.setTarget("modal-lg");
			}
		).add(
			() -> _accountEntryModelResourcePermission.contains(
				permissionChecker, termEntry.getAccountEntryId(),
				CommerceAccountActionKeys.MANAGE_CHANNEL_DEFAULTS),
			dropdownItem -> {
				dropdownItem.setHref(
					_getCommerceChannelAccountEntryRelDeleteURL(
						termEntry.getCommerceChannelAccountEntryRelId(),
						httpServletRequest));
				dropdownItem.setLabel(
					_language.get(httpServletRequest, Constants.DELETE));
			}
		).build();
	}

	private String _getCommerceChannelAccountEntryRelDeleteURL(
		long commerceChannelAccountEntryRelId,
		HttpServletRequest httpServletRequest) {

		return PortletURLBuilder.create(
			_portal.getControlPanelPortletURL(
				httpServletRequest, AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
				PortletRequest.ACTION_PHASE)
		).setActionName(
			"/commerce_term_entry" +
				"/edit_account_entry_default_commerce_term_entry"
		).setCMD(
			Constants.DELETE
		).setRedirect(
			ParamUtil.getString(
				httpServletRequest, "currentUrl",
				_portal.getCurrentURL(httpServletRequest))
		).setParameter(
			"commerceChannelAccountEntryRelId", commerceChannelAccountEntryRelId
		).buildString();
	}

	private String _getCommerceChannelAccountEntryRelEditURL(
		long accountEntryId, long commerceChannelAccountEntryRelId,
		HttpServletRequest httpServletRequest, int type) {

		return PortletURLBuilder.create(
			_portal.getControlPanelPortletURL(
				httpServletRequest, AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
				PortletRequest.RENDER_PHASE)
		).setMVCRenderCommandName(
			"/commerce_term_entry" +
				"/edit_account_entry_default_commerce_term_entry"
		).setParameter(
			"accountEntryId", accountEntryId
		).setParameter(
			"commerceChannelAccountEntryRelId", commerceChannelAccountEntryRelId
		).setParameter(
			"type", type
		).setWindowState(
			LiferayWindowState.POP_UP
		).buildString();
	}

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(model.class.name=com.liferay.account.model.AccountEntry)"
	)
	private volatile ModelResourcePermission<AccountEntry>
		_accountEntryModelResourcePermission;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}