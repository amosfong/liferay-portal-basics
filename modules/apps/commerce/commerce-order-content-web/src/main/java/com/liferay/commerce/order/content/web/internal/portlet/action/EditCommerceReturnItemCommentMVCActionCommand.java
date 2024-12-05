/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.content.web.internal.portlet.action;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.portal.kernel.comment.Comment;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.comment.DiscussionPermission;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContextFunction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Danny Situ
 */
@Component(
	property = {
		"javax.portlet.name=" + CommercePortletKeys.COMMERCE_RETURN_CONTENT,
		"mvc.command.name=/commerce_return_content/edit_commerce_return_item_comment"
	},
	service = MVCActionCommand.class
)
public class EditCommerceReturnItemCommentMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			if (cmd.equals(Constants.UPDATE)) {
				_updateCommerceReturnItemComment(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				_deleteCommerceReturnItemComment(actionRequest);
			}
		}
		catch (Exception exception) {
			hideDefaultErrorMessage(actionRequest);
			hideDefaultSuccessMessage(actionRequest);

			SessionErrors.add(actionRequest, exception.getClass());

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			sendRedirect(actionRequest, actionResponse, redirect);
		}
	}

	private void _deleteCommerceReturnItemComment(ActionRequest actionRequest)
		throws Exception {

		long commentId = ParamUtil.getLong(actionRequest, "commentId");

		Comment comment = _commentManager.fetchComment(commentId);

		if (comment == null) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (comment.getUserId() != themeDisplay.getUserId()) {
			_discussionPermission.checkDeletePermission(
				themeDisplay.getPermissionChecker(), commentId);
		}

		_commentManager.deleteComment(commentId);
	}

	private void _updateCommerceReturnItemComment(ActionRequest actionRequest)
		throws Exception {

		long commentId = ParamUtil.getLong(actionRequest, "commentId");

		Comment comment = _commentManager.fetchComment(commentId);

		if (comment == null) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (comment.getUserId() != themeDisplay.getUserId()) {
			_discussionPermission.checkUpdatePermission(
				themeDisplay.getPermissionChecker(), commentId);
		}

		String body = ParamUtil.getString(actionRequest, "body");

		_commentManager.updateComment(
			comment.getUserId(), comment.getClassName(), comment.getClassPK(),
			commentId, body, body, new ServiceContextFunction(actionRequest));
	}

	@Reference
	private CommentManager _commentManager;

	@Reference
	private DiscussionPermission _discussionPermission;

}