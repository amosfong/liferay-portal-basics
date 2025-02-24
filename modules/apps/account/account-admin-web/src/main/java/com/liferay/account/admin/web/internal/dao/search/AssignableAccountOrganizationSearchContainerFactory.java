/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.admin.web.internal.dao.search;

import com.liferay.account.admin.web.internal.security.permission.resource.AccountEntryPermission;
import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.constants.AccountPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.LinkedHashMap;

/**
 * @author Pei-Jung Lan
 */
public class AssignableAccountOrganizationSearchContainerFactory {

	public static SearchContainer<Organization> create(
			long accountEntryId, LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		SearchContainer<Organization> searchContainer = new SearchContainer(
			liferayPortletRequest,
			PortletURLUtil.getCurrent(
				liferayPortletRequest, liferayPortletResponse),
			null, "no-organizations-were-found");

		searchContainer.setId("organizations");
		searchContainer.setOrderByCol(
			SearchOrderByUtil.getOrderByCol(
				liferayPortletRequest, AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
				"assignable-account-organization-order-by-col", "name"));
		searchContainer.setOrderByType(
			SearchOrderByUtil.getOrderByType(
				liferayPortletRequest, AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN,
				"assignable-account-organization-order-by-type", "asc"));

		String keywords = ParamUtil.getString(
			liferayPortletRequest, "keywords", null);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (!AccountEntryPermission.contains(
				permissionChecker, accountEntryId,
				AccountActionKeys.MANAGE_ORGANIZATIONS) &&
			!permissionChecker.hasPermission(
				null, Organization.class.getName(),
				Organization.class.getName(), ActionKeys.VIEW)) {

			params.put(
				"organizationsTree",
				OrganizationLocalServiceUtil.getUserOrganizations(
					PortalUtil.getUserId(liferayPortletRequest), true));
		}

		searchContainer.setResultsAndTotal(
			OrganizationLocalServiceUtil.searchOrganizations(
				themeDisplay.getCompanyId(),
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, keywords,
				params, searchContainer.getStart(), searchContainer.getEnd(),
				SortFactoryUtil.getSort(
					Organization.class, searchContainer.getOrderByCol(),
					searchContainer.getOrderByType())));

		searchContainer.setRowChecker(
			new SelectAccountOrganizationRowChecker(
				liferayPortletResponse, accountEntryId));

		return searchContainer;
	}

}