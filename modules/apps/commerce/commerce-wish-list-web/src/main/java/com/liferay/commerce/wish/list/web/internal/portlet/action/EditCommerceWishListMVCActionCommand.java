/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.wish.list.web.internal.portlet.action;

import com.liferay.commerce.wish.list.constants.CommerceWishListPortletKeys;
import com.liferay.commerce.wish.list.exception.CommerceWishListNameException;
import com.liferay.commerce.wish.list.exception.NoSuchWishListException;
import com.liferay.commerce.wish.list.model.CommerceWishList;
import com.liferay.commerce.wish.list.service.CommerceWishListService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"javax.portlet.name=" + CommerceWishListPortletKeys.COMMERCE_WISH_LIST_CONTENT,
		"javax.portlet.name=" + CommerceWishListPortletKeys.MY_COMMERCE_WISH_LISTS,
		"mvc.command.name=/commerce_wish_list_content/edit_commerce_wish_list"
	},
	service = MVCActionCommand.class
)
public class EditCommerceWishListMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				_updateCommerceWishList(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				_deleteCommerceWishLists(actionRequest);
			}
			else if (cmd.equals(Constants.SAVE)) {
				_saveCommerceWishList(actionRequest, actionResponse);

				hideDefaultSuccessMessage(actionRequest);
			}
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchWishListException ||
				exception instanceof PrincipalException) {

				SessionErrors.add(actionRequest, exception.getClass());
			}
			else if (exception instanceof CommerceWishListNameException) {
				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName",
					"/commerce_wish_list_content/edit_commerce_wish_list");
			}
			else {
				throw exception;
			}
		}
	}

	private void _deleteCommerceWishLists(ActionRequest actionRequest)
		throws PortalException {

		long[] deleteCommerceWishListIds = null;

		long commerceWishListId = ParamUtil.getLong(
			actionRequest, "commerceWishListId");

		if (commerceWishListId > 0) {
			deleteCommerceWishListIds = new long[] {commerceWishListId};
		}
		else {
			deleteCommerceWishListIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteCommerceWishListIds"),
				0L);
		}

		for (long deleteCommerceWishListId : deleteCommerceWishListIds) {
			_commerceWishListService.deleteCommerceWishList(
				deleteCommerceWishListId);
		}
	}

	private void _saveCommerceWishList(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", _portal.getLocale(actionRequest), getClass());

		String name = _language.get(resourceBundle, "new-wish-list");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceWishList.class.getName(), actionRequest);

		CommerceWishList commerceWishList =
			_commerceWishListService.addCommerceWishList(
				name, false, serviceContext);

		actionResponse.setRenderParameter(
			"commerceWishListId",
			String.valueOf(commerceWishList.getCommerceWishListId()));
	}

	private void _updateCommerceWishList(ActionRequest actionRequest)
		throws PortalException {

		long commerceWishListId = ParamUtil.getLong(
			actionRequest, "commerceWishListId");

		String name = ParamUtil.getString(actionRequest, "name");
		boolean defaultWishList = ParamUtil.getBoolean(
			actionRequest, "defaultWishList");

		if (commerceWishListId > 0) {
			_commerceWishListService.updateCommerceWishList(
				commerceWishListId, name, defaultWishList);
		}
		else {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceWishList.class.getName(), actionRequest);

			_commerceWishListService.addCommerceWishList(
				name, defaultWishList, serviceContext);
		}
	}

	@Reference
	private CommerceWishListService _commerceWishListService;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}