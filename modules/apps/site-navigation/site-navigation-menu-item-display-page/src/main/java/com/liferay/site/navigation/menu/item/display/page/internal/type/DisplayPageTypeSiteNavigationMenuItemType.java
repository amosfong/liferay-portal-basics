/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.menu.item.display.page.internal.type;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.site.navigation.menu.item.display.page.internal.display.context.DisplayPageTypeSiteNavigationMenuTypeDisplayContext;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;
import com.liferay.site.navigation.type.SiteNavigationMenuItemTypeContext;

import java.io.IOException;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lourdes Fernández Besada
 */
public class DisplayPageTypeSiteNavigationMenuItemType
	implements SiteNavigationMenuItemType {

	public DisplayPageTypeSiteNavigationMenuItemType(
		DisplayPageTypeContext displayPageTypeContext, JSPRenderer jspRenderer,
		Portal portal, ServletContext servletContext) {

		_displayPageTypeContext = displayPageTypeContext;
		_jspRenderer = jspRenderer;
		_portal = portal;
		_servletContext = servletContext;
	}

	@Override
	public boolean exportData(
		PortletDataContext portletDataContext,
		Element siteNavigationMenuItemElement,
		SiteNavigationMenuItem siteNavigationMenuItem) {

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				siteNavigationMenuItem.getTypeSettings()
			).build();

		long classPK = GetterUtil.getLong(
			typeSettingsUnicodeProperties.get("classPK"));

		try {
			LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
				_displayPageTypeContext.getLayoutDisplayPageObjectProvider(
					classPK);

			if (layoutDisplayPageObjectProvider == null) {
				return false;
			}

			siteNavigationMenuItemElement.addAttribute(
				"display-page-class-name",
				_displayPageTypeContext.getClassName());
			siteNavigationMenuItemElement.addAttribute(
				"display-page-class-pk", String.valueOf(classPK));

			portletDataContext.addReferenceElement(
				siteNavigationMenuItem, siteNavigationMenuItemElement,
				(ClassedModel)
					layoutDisplayPageObjectProvider.getDisplayObject(),
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, false);

			return true;
		}
		catch (RuntimeException runtimeException) {
			_log.error(runtimeException);
		}

		return false;
	}

	@Override
	public String getAddTitle(Locale locale) {
		String label = _displayPageTypeContext.getLabel(locale);

		return LanguageUtil.format(locale, "select-x", label);
	}

	@Override
	public PortletURL getAddURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		return PortletURLBuilder.createActionURL(
			renderResponse
		).setActionName(
			() -> {
				if (isMultiSelection()) {
					return "/navigation_menu" +
						"/add_multiple_display_page_type_site_navigation_" +
							"menu_item";
				}

				return "/navigation_menu" +
					"/add_display_page_type_site_navigation_menu_item";
			}
		).setParameter(
			"siteNavigationMenuItemType", getType()
		).buildPortletURL();
	}

	@Override
	public String getIcon() {
		return "page";
	}

	@Override
	public String getLabel(Locale locale) {
		return _displayPageTypeContext.getLabel(locale);
	}

	@Override
	public String getName(String typeSettings) {
		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				typeSettings
			).build();

		return typeSettingsUnicodeProperties.get("title");
	}

	@Override
	public String getRegularURL(
			HttpServletRequest httpServletRequest,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (themeDisplay == null) {
			return StringPool.BLANK;
		}

		return StringPool.BLANK;
	}

	@Override
	public String getStatusIcon(SiteNavigationMenuItem siteNavigationMenuItem) {
		return SiteNavigationMenuItemType.super.getStatusIcon(
			siteNavigationMenuItem);
	}

	@Override
	public String getSubtitle(
		SiteNavigationMenuItem siteNavigationMenuItem, Locale locale) {

		return _displayPageTypeContext.getLabel(locale);
	}

	@Override
	public String getTarget(SiteNavigationMenuItem siteNavigationMenuItem) {
		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				siteNavigationMenuItem.getTypeSettings()
			).build();

		boolean useNewTab = GetterUtil.getBoolean(
			typeSettingsUnicodeProperties.getProperty(
				"useNewTab", Boolean.FALSE.toString()));

		if (!useNewTab) {
			return StringPool.BLANK;
		}

		return "target=\"_blank\"";
	}

	@Override
	public String getTitle(
		SiteNavigationMenuItem siteNavigationMenuItem, Locale locale) {

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				siteNavigationMenuItem.getTypeSettings()
			).build();

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			_displayPageTypeContext.getLayoutDisplayPageObjectProvider(
				GetterUtil.getLong(
					typeSettingsUnicodeProperties.get("classPK")));

		String defaultTitle = typeSettingsUnicodeProperties.getProperty(
			"title");

		if (layoutDisplayPageObjectProvider != null) {
			defaultTitle = layoutDisplayPageObjectProvider.getTitle(locale);
		}

		if (!GetterUtil.getBoolean(
				typeSettingsUnicodeProperties.get("useCustomName"))) {

			return defaultTitle;
		}

		String defaultLanguageId = typeSettingsUnicodeProperties.getProperty(
			Field.DEFAULT_LANGUAGE_ID,
			LocaleUtil.toLanguageId(LocaleUtil.getMostRelevantLocale()));

		String localizedNames = typeSettingsUnicodeProperties.getProperty(
			"localizedNames", "{}");

		try {
			JSONObject localizedNamesJSONObject =
				JSONFactoryUtil.createJSONObject(localizedNames);

			return localizedNamesJSONObject.getString(
				LocaleUtil.toLanguageId(locale),
				localizedNamesJSONObject.getString(
					defaultLanguageId, defaultTitle));
		}
		catch (JSONException jsonException) {
			_log.error(
				"Unable to get localizedNamesJSONObject from localizedNames: " +
					localizedNames,
				jsonException);
		}

		return defaultTitle;
	}

	@Override
	public String getType() {
		return _displayPageTypeContext.getClassName();
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws PortalException {

		return true;
	}

	@Override
	public boolean importData(
		PortletDataContext portletDataContext,
		SiteNavigationMenuItem siteNavigationMenuItem,
		SiteNavigationMenuItem importedSiteNavigationMenuItem) {

		Element element = portletDataContext.getImportDataElement(
			siteNavigationMenuItem);

		long classPK = GetterUtil.getLong(
			element.attributeValue("display-page-class-pk"));

		if (classPK <= 0) {
			return false;
		}

		importedSiteNavigationMenuItem.setTypeSettings(
			UnicodePropertiesBuilder.fastLoad(
				siteNavigationMenuItem.getTypeSettings()
			).put(
				"classNameId",
				String.valueOf(
					_portal.getClassNameId(
						_displayPageTypeContext.getClassName()))
			).put(
				"classPK",
				String.valueOf(
					MapUtil.getLong(
						(Map<Long, Long>)
							portletDataContext.getNewPrimaryKeysMap(
								_displayPageTypeContext.getClassName()),
						classPK, classPK))
			).buildString());

		return true;
	}

	@Override
	public boolean isAvailable(
		SiteNavigationMenuItemTypeContext siteNavigationMenuItemTypeContext) {

		return _displayPageTypeContext.isAvailable();
	}

	@Override
	public boolean isBrowsable(SiteNavigationMenuItem siteNavigationMenuItem) {
		return false;
	}

	@Override
	public boolean isMultiSelection() {
		return false;
	}

	@Override
	public void renderEditPage(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws IOException {

		httpServletRequest.setAttribute(
			DisplayPageTypeSiteNavigationMenuTypeDisplayContext.class.getName(),
			new DisplayPageTypeSiteNavigationMenuTypeDisplayContext(
				_displayPageTypeContext, httpServletRequest,
				siteNavigationMenuItem));

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/edit_display_page_type.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DisplayPageTypeSiteNavigationMenuItemType.class);

	private final DisplayPageTypeContext _displayPageTypeContext;
	private final JSPRenderer _jspRenderer;
	private final Portal _portal;
	private final ServletContext _servletContext;

}