/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.menu.item.display.page.internal.display.context;

import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.url.builder.ResourceURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.menu.item.display.page.internal.type.DisplayPageTypeContext;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;

import java.util.Map;

import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lourdes Fernández Besada
 */
public class DisplayPageTypeSiteNavigationMenuTypeDisplayContext {

	public DisplayPageTypeSiteNavigationMenuTypeDisplayContext(
		DisplayPageTypeContext displayPageTypeContext,
		HttpServletRequest httpServletRequest,
		SiteNavigationMenuItem siteNavigationMenuItem) {

		_displayPageTypeContext = displayPageTypeContext;
		_httpServletRequest = httpServletRequest;
		_siteNavigationMenuItem = siteNavigationMenuItem;

		PortletResponse portletResponse =
			(PortletResponse)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public long getClassNameId() {
		if (_classNameId != null) {
			return _classNameId;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				_siteNavigationMenuItem.getTypeSettings()
			).build();

		_classNameId = GetterUtil.getLong(
			typeSettingsUnicodeProperties.get("classNameId"));

		return _classNameId;
	}

	public long getClassPK() {
		if (_classPK != null) {
			return _classPK;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				_siteNavigationMenuItem.getTypeSettings()
			).build();

		_classPK = GetterUtil.getLong(
			typeSettingsUnicodeProperties.get("classPK"));

		return _classPK;
	}

	public long getClassTypeId() {
		if (_classTypeId != null) {
			return _classTypeId;
		}

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			_getLayoutDisplayPageObjectProvider();

		if (layoutDisplayPageObjectProvider != null) {
			_classTypeId = layoutDisplayPageObjectProvider.getClassTypeId();
		}
		else {
			UnicodeProperties typeSettingsUnicodeProperties =
				UnicodePropertiesBuilder.fastLoad(
					_siteNavigationMenuItem.getTypeSettings()
				).build();

			_classTypeId = GetterUtil.getLong(
				typeSettingsUnicodeProperties.get("classTypeId"));
		}

		return _classTypeId;
	}

	public Map<String, Object> getDisplayPageItemContextualSidebarContext()
		throws Exception {

		return HashMapBuilder.<String, Object>put(
			"defaultLanguageId",
			LocaleUtil.toLanguageId(LocaleUtil.getMostRelevantLocale())
		).put(
			"item",
			HashMapBuilder.<String, Object>put(
				"classNameId", getClassNameId()
			).put(
				"classPK", getClassPK()
			).put(
				"classTypeId", getClassTypeId()
			).put(
				"data", _getDataJSONArray()
			).put(
				"title", getTitle()
			).put(
				"type", getType()
			).build()
		).put(
			"itemSubtype", getItemSubtype()
		).put(
			"itemType", getItemType()
		).put(
			"locales",
			JSONUtil.toJSONArray(
				LanguageUtil.getAvailableLocales(
					_themeDisplay.getSiteGroupId()),
				locale -> {
					String w3cLanguageId = LocaleUtil.toW3cLanguageId(locale);

					return JSONUtil.put(
						"id", LocaleUtil.toLanguageId(locale)
					).put(
						"label", w3cLanguageId
					).put(
						"symbol", StringUtil.toLowerCase(w3cLanguageId)
					);
				})
		).put(
			"localizedNames",
			() -> {
				UnicodeProperties typeSettingsUnicodeProperties =
					UnicodePropertiesBuilder.fastLoad(
						_siteNavigationMenuItem.getTypeSettings()
					).build();

				return JSONFactoryUtil.createJSONObject(
					typeSettingsUnicodeProperties.getProperty(
						"localizedNames", "{}"));
			}
		).put(
			"namespace", _liferayPortletResponse.getNamespace()
		).put(
			"useCustomName",
			() -> {
				UnicodeProperties typeSettingsUnicodeProperties =
					UnicodePropertiesBuilder.fastLoad(
						_siteNavigationMenuItem.getTypeSettings()
					).build();

				return GetterUtil.getBoolean(
					typeSettingsUnicodeProperties.get("useCustomName"));
			}
		).build();
	}

	public String getItemDetailsURL() {
		LiferayPortletURL itemDetailsURL =
			(LiferayPortletURL)ResourceURLBuilder.createResourceURL(
				_liferayPortletResponse
			).setResourceID(
				"/navigation_menu/get_item_details"
			).buildResourceURL();

		itemDetailsURL.setCopyCurrentRenderParameters(false);

		return itemDetailsURL.toString();
	}

	public String getItemSubtype() {
		return StringPool.BLANK;
	}

	public String getItemType() {
		return _displayPageTypeContext.getLabel(_themeDisplay.getLocale());
	}

	public String getOriginalTitle() {
		if (Validator.isNotNull(_originalTitle)) {
			return _originalTitle;
		}

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			_getLayoutDisplayPageObjectProvider();

		if (layoutDisplayPageObjectProvider == null) {
			UnicodeProperties typeSettingsUnicodeProperties =
				UnicodePropertiesBuilder.fastLoad(
					_siteNavigationMenuItem.getTypeSettings()
				).build();

			_originalTitle = typeSettingsUnicodeProperties.getProperty("title");
		}
		else {
			_originalTitle = layoutDisplayPageObjectProvider.getTitle(
				_themeDisplay.getLocale());
		}

		return _originalTitle;
	}

	public String getTitle() {
		if (Validator.isNotNull(_title)) {
			return _title;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				_siteNavigationMenuItem.getTypeSettings()
			).build();

		_title = typeSettingsUnicodeProperties.get("title");

		return _title;
	}

	public String getType() {
		if (Validator.isNotNull(_type)) {
			return _type;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				_siteNavigationMenuItem.getTypeSettings()
			).build();

		_type = typeSettingsUnicodeProperties.get("type");

		return _type;
	}

	private JSONArray _getDataJSONArray() throws Exception {
		return JSONFactoryUtil.createJSONArray();
	}

	private LayoutDisplayPageObjectProvider<?>
		_getLayoutDisplayPageObjectProvider() {

		if (_layoutDisplayPageObjectProvider != null) {
			return _layoutDisplayPageObjectProvider;
		}

		_layoutDisplayPageObjectProvider =
			_displayPageTypeContext.getLayoutDisplayPageObjectProvider(
				getClassPK());

		return _layoutDisplayPageObjectProvider;
	}

	private Long _classNameId;
	private Long _classPK;
	private Long _classTypeId;
	private final DisplayPageTypeContext _displayPageTypeContext;
	private final HttpServletRequest _httpServletRequest;
	private LayoutDisplayPageObjectProvider<?> _layoutDisplayPageObjectProvider;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _originalTitle;
	private final SiteNavigationMenuItem _siteNavigationMenuItem;
	private final ThemeDisplay _themeDisplay;
	private String _title;
	private String _type;

}