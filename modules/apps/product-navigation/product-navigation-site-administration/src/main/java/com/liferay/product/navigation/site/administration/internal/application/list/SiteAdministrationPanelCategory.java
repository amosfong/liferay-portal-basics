/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.site.administration.internal.application.list;

import com.liferay.application.list.BaseJSPPanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.ApplicationListWebKeys;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.product.navigation.site.administration.internal.constants.SiteAdministrationWebKeys;
import com.liferay.site.manager.RecentGroupManager;
import com.liferay.site.provider.GroupURLProvider;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {
		"panel.category.key=" + PanelCategoryKeys.ROOT,
		"panel.category.order:Integer=300"
	},
	service = PanelCategory.class
)
public class SiteAdministrationPanelCategory extends BaseJSPPanelCategory {

	@Override
	public String getHeaderJspPath() {
		return "/sites/site_administration_header.jsp";
	}

	@Override
	public String getJspPath() {
		return "/sites/site_administration_body.jsp";
	}

	@Override
	public String getKey() {
		return PanelCategoryKeys.SITE_ADMINISTRATION;
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "site-administration");
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		httpServletRequest.setAttribute(
			ApplicationListWebKeys.PANEL_CATEGORY, this);

		return super.include(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean includeHeader(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		httpServletRequest.setAttribute(
			ApplicationListWebKeys.PANEL_CATEGORY, this);
		httpServletRequest.setAttribute(
			SiteAdministrationWebKeys.GROUP_URL_PROVIDER, _groupURLProvider);
		httpServletRequest.setAttribute(
			SiteAdministrationWebKeys.RECENT_GROUP_MANAGER,
			_recentGroupManager);

		return super.includeHeader(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		if (group == null) {
			return false;
		}

		if (GroupPermissionUtil.contains(
				permissionChecker, group,
				ActionKeys.VIEW_SITE_ADMINISTRATION)) {

			return true;
		}

		return false;
	}

	@Override
	protected ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference
	private GroupURLProvider _groupURLProvider;

	@Reference
	private Language _language;

	@Reference
	private RecentGroupManager _recentGroupManager;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.product.navigation.site.administration)"
	)
	private ServletContext _servletContext;

}