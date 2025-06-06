/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.site.administration.internal.menu;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilder;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilderFactory;
import com.liferay.portal.util.PropsValues;
import com.liferay.product.navigation.personal.menu.PersonalMenuEntry;
import com.liferay.site.manager.RecentGroupManager;
import com.liferay.taglib.aui.AUIUtil;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Samuel Trong Tran
 */
@Component(
	property = {
		"product.navigation.personal.menu.entry.order:Integer=100",
		"product.navigation.personal.menu.group:Integer=100"
	},
	service = PersonalMenuEntry.class
)
public class MySitesPersonalMenuEntry implements PersonalMenuEntry {

	@Override
	public String getIcon(PortletRequest portletRequest) {
		return "sites";
	}

	@Override
	public JSONObject getJSOnClickConfigJSONObject(
		HttpServletRequest httpServletRequest) {

		String namespace = AUIUtil.getNamespace(httpServletRequest);

		String eventName = namespace + "selectSite";

		return JSONUtil.put(
			"selectEventName", eventName
		).put(
			"title", _language.get(httpServletRequest, "select-site")
		);
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "my-sites");
	}

	@Override
	public String getOnClickESModule(HttpServletRequest httpServletRequest) {
		AbsolutePortalURLBuilder absolutePortalURLBuilder =
			_absolutePortalURLBuilderFactory.getAbsolutePortalURLBuilder(
				httpServletRequest);

		String moduleURL = absolutePortalURLBuilder.forESModule(
			"product-navigation-site-administration", "index.js"
		).build();

		return "{mySitesOpener} from " + moduleURL;
	}

	@Override
	public String getPortletURL(HttpServletRequest httpServletRequest) {
		return null;
	}

	@Override
	public boolean isShow(
			PortletRequest portletRequest, PermissionChecker permissionChecker)
		throws PortalException {

		User user = permissionChecker.getUser();

		List<Group> mySiteGroups = user.getMySiteGroups(
			new String[] {
				Company.class.getName(), Group.class.getName(),
				Organization.class.getName()
			},
			PropsValues.MY_SITES_MAX_ELEMENTS);

		if (!mySiteGroups.isEmpty()) {
			return true;
		}

		List<Group> recentGroups = _recentGroupManager.getRecentGroups(
			_portal.getHttpServletRequest(portletRequest));

		if (!recentGroups.isEmpty()) {
			return true;
		}

		return false;
	}

	@Reference
	private AbsolutePortalURLBuilderFactory _absolutePortalURLBuilderFactory;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference
	private RecentGroupManager _recentGroupManager;

}