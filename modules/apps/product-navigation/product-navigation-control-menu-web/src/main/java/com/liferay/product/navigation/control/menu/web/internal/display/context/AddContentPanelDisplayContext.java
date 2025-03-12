/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.control.menu.web.internal.display.context;

import com.liferay.layout.portlet.category.PortletCategoryManager;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionClicks;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class AddContentPanelDisplayContext {

	public AddContentPanelDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_httpServletRequest = httpServletRequest;
		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;

		_portletCategoryManager =
			(PortletCategoryManager)httpServletRequest.getAttribute(
				PortletCategoryManager.class.getName());
		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getAddContentPanelData() throws Exception {
		return HashMapBuilder.<String, Object>put(
			"addContentsURLs",
			() -> {
				if (hasAddContentPermission()) {
					return _getAddContentsURLs();
				}

				return Collections.emptyList();
			}
		).put(
			"contents",
			() -> {
				if (hasAddContentPermission()) {
					return getContents();
				}

				return Collections.emptyList();
			}
		).put(
			"getContentsURL",
			() -> {
				ResourceURL resourceURL =
					_liferayPortletResponse.createResourceURL();

				resourceURL.setParameter(
					"status", String.valueOf(WorkflowConstants.STATUS_ANY));
				resourceURL.setResourceID(
					"/product_navigation_control_menu/get_contents");

				return resourceURL.toString();
			}
		).put(
			"hasAddContentPermission", hasAddContentPermission()
		).put(
			"languageDirection", _getLanguageDirection()
		).put(
			"languageId", _themeDisplay.getLanguageId()
		).put(
			"namespace", _liferayPortletResponse.getNamespace()
		).put(
			"plid", _themeDisplay.getPlid()
		).put(
			"widgets",
			() -> {
				if (hasAddApplicationsPermission()) {
					return _getWidgetsJSONArray();
				}

				return Collections.emptyList();
			}
		).build();
	}

	public List<Map<String, Object>> getContents() throws Exception {
		List<Map<String, Object>> contents = new ArrayList<>();

		return contents;
	}

	public boolean hasAddApplicationsPermission() throws Exception {
		if (_hasAddApplicationsPermission != null) {
			return _hasAddApplicationsPermission;
		}

		_hasAddApplicationsPermission = false;

		boolean stateMaximized = ParamUtil.getBoolean(
			_httpServletRequest, "stateMaximized");

		LayoutTypePortlet layoutTypePortlet =
			_themeDisplay.getLayoutTypePortlet();

		LayoutTypeController layoutTypeController =
			layoutTypePortlet.getLayoutTypeController();

		Layout layout = _themeDisplay.getLayout();

		if (!stateMaximized && layout.isTypePortlet() &&
			!layout.isLayoutPrototypeLinkActive() &&
			!layoutTypeController.isFullPageDisplayable() &&
			(hasLayoutUpdatePermission() ||
			 (layoutTypePortlet.isCustomizable() &&
			  layoutTypePortlet.isCustomizedView() &&
			  hasLayoutCustomizePermission()))) {

			_hasAddApplicationsPermission = true;
		}

		return _hasAddApplicationsPermission;
	}

	public boolean hasAddContentPermission() throws Exception {
		if (_hasAddContentPermission != null) {
			return _hasAddContentPermission;
		}

		_hasAddContentPermission = false;

		Group group = _themeDisplay.getScopeGroup();

		if (hasAddApplicationsPermission() && !group.isLayoutPrototype()) {
			_hasAddContentPermission = true;
		}

		return _hasAddContentPermission;
	}

	public boolean hasLayoutCustomizePermission() throws Exception {
		if (_hasLayoutCustomizePermission != null) {
			return _hasLayoutCustomizePermission;
		}

		_hasLayoutCustomizePermission = false;

		if (LayoutPermissionUtil.contains(
				_themeDisplay.getPermissionChecker(), _themeDisplay.getLayout(),
				ActionKeys.CUSTOMIZE)) {

			_hasLayoutCustomizePermission = true;
		}

		return _hasLayoutCustomizePermission;
	}

	public boolean hasLayoutUpdatePermission() throws Exception {
		if (_hasLayoutUpdatePermission != null) {
			return _hasLayoutUpdatePermission;
		}

		_hasLayoutUpdatePermission = false;

		if (LayoutPermissionUtil.contains(
				_themeDisplay.getPermissionChecker(), _themeDisplay.getLayout(),
				ActionKeys.UPDATE)) {

			_hasLayoutUpdatePermission = true;
		}

		return _hasLayoutUpdatePermission;
	}

	public boolean showAddPanel() throws Exception {
		Group group = _themeDisplay.getScopeGroup();

		LayoutTypePortlet layoutTypePortlet =
			_themeDisplay.getLayoutTypePortlet();

		if (!group.isControlPanel() &&
			(hasLayoutUpdatePermission() ||
			 (layoutTypePortlet.isCustomizable() &&
			  layoutTypePortlet.isCustomizedView() &&
			  hasLayoutCustomizePermission()))) {

			return true;
		}

		return false;
	}

	private List<Map<String, Object>> _getAddContentsURLs() throws Exception {
		return new ArrayList<>();
	}

	private int _getDelta() {
		if (_delta != null) {
			return _delta;
		}

		int deltaDefault = GetterUtil.getInteger(
			SessionClicks.get(
				_httpServletRequest,
				"com.liferay.product.navigation.control.menu." +
					"web_addPanelNumItems",
				"10"));

		_delta = ParamUtil.getInteger(
			_httpServletRequest, "delta", deltaDefault);

		return _delta;
	}

	private String _getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		return _keywords;
	}

	private Map<String, String> _getLanguageDirection() {
		Map<String, String> map = new HashMap<>();

		for (Locale locale :
				LanguageUtil.getAvailableLocales(
					_themeDisplay.getScopeGroupId())) {

			map.put(
				LocaleUtil.toLanguageId(locale),
				LanguageUtil.get(locale, "lang.dir"));
		}

		return map;
	}

	private String _getRedirectURL() throws Exception {
		return PortalUtil.getLayoutFullURL(
			_themeDisplay.getLayout(), _themeDisplay);
	}

	private JSONArray _getWidgetsJSONArray() throws Exception {
		return _portletCategoryManager.getPortletsJSONArray(
			_httpServletRequest, _themeDisplay);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddContentPanelDisplayContext.class);

	private Integer _delta;
	private Boolean _hasAddApplicationsPermission;
	private Boolean _hasAddContentPermission;
	private Boolean _hasLayoutCustomizePermission;
	private Boolean _hasLayoutUpdatePermission;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final PortletCategoryManager _portletCategoryManager;
	private final ThemeDisplay _themeDisplay;

}