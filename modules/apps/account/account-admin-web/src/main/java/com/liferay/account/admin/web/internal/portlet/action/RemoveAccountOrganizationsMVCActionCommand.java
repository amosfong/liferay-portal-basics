/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.admin.web.internal.portlet.action;

import com.liferay.account.constants.AccountPortletKeys;
import com.liferay.account.exception.NoSuchEntryOrganizationRelException;
import com.liferay.account.service.AccountEntryOrganizationRelService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pei-Jung Lan
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
		"javax.portlet.name=" + AccountPortletKeys.ACCOUNT_ENTRIES_MANAGEMENT,
		"mvc.command.name=/account_admin/remove_account_organizations"
	},
	service = MVCActionCommand.class
)
public class RemoveAccountOrganizationsMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long accountEntryId = ParamUtil.getLong(
				actionRequest, "accountEntryId");
			long[] accountOrganizationIds = ParamUtil.getLongValues(
				actionRequest, "accountOrganizationIds");

			_accountEntryOrganizationRelService.
				deleteAccountEntryOrganizationRels(
					accountEntryId, accountOrganizationIds);

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			if (Validator.isNotNull(redirect)) {
				sendRedirect(actionRequest, actionResponse, redirect);
			}
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchEntryOrganizationRelException) {
				SessionErrors.add(actionRequest, exception.getClass());
			}
			else {
				throw exception;
			}

			actionResponse.setRenderParameter(
				"mvcPath", "/account_entries_admin/view.jsp");
		}
	}

	@Reference
	private AccountEntryOrganizationRelService
		_accountEntryOrganizationRelService;

}