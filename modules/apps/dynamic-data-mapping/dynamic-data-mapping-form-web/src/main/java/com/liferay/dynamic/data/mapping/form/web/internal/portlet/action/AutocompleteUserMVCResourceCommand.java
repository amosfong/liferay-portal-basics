/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.UserScreenNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carolina Barbosa
 */
@Component(
	property = {
		"javax.portlet.name=" + DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
		"mvc.command.name=/admin/autocomplete_user"
	},
	service = MVCResourceCommand.class
)
public class AutocompleteUserMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			resourceRequest);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			throw new PrincipalException.MustBeAuthenticated(
				themeDisplay.getUserId());
		}

		JSONArray usersJSONArray = _getUsersJSONArray(httpServletRequest);

		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(resourceResponse);

		httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse, usersJSONArray);
	}

	private List<User> _getUsers(
		HttpServletRequest httpServletRequest, ThemeDisplay themeDisplay) {

		String query = ParamUtil.getString(httpServletRequest, "query");

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			return _userLocalService.search(
				themeDisplay.getCompanyId(), query,
				WorkflowConstants.STATUS_APPROVED, new LinkedHashMap<>(), 0, 20,
				UserScreenNameComparator.getInstance(false));
		}

		User user = themeDisplay.getUser();

		if (ArrayUtil.isEmpty(user.getGroupIds()) &&
			ArrayUtil.isEmpty(user.getUserGroupIds())) {

			return Collections.emptyList();
		}

		return _userLocalService.searchBySocial(
			themeDisplay.getCompanyId(), user.getGroupIds(),
			user.getUserGroupIds(), query, 0, 20,
			UserScreenNameComparator.getInstance(false));
	}

	private JSONArray _getUsersJSONArray(
		HttpServletRequest httpServletRequest) {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		for (User user : _getUsers(httpServletRequest, themeDisplay)) {
			if (user.isGuestUser() ||
				(themeDisplay.getUserId() == user.getUserId())) {

				continue;
			}

			jsonArray.put(
				JSONUtil.put(
					"emailAddress", user.getEmailAddress()
				).put(
					"fullName", user.getFullName()
				));
		}

		return jsonArray;
	}

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}