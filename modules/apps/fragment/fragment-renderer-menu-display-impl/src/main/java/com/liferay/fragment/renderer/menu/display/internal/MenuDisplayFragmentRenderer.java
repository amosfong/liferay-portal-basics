/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.renderer.menu.display.internal;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.fragment.util.configuration.FragmentEntryMenuDisplayConfiguration;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.NavItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.taglib.servlet.taglib.NavigationMenuTag;

import java.io.PrintWriter;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(service = FragmentRenderer.class)
public class MenuDisplayFragmentRenderer implements FragmentRenderer {

	@Override
	public String getCollectionKey() {
		return "menu-display";
	}

	@Override
	public String getConfiguration(
		FragmentRendererContext fragmentRendererContext) {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getClass());

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(
				StringUtil.read(
					getClass(),
					"/com/liferay/fragment/renderer/menu/display/internal" +
						"/dependencies/configuration.json"));

			return _fragmentEntryConfigurationParser.translateConfiguration(
				jsonObject, resourceBundle);
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException);
			}

			return StringPool.BLANK;
		}
	}

	@Override
	public String getIcon() {
		return "sites";
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "menu-display");
	}

	@Override
	public void render(
		FragmentRendererContext fragmentRendererContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			PrintWriter printWriter = httpServletResponse.getWriter();

			FragmentEntryLink fragmentEntryLink =
				fragmentRendererContext.getFragmentEntryLink();

			String fragmentElementId =
				fragmentRendererContext.getFragmentElementId();

			printWriter.write("<div id=\"" + fragmentElementId + "\">");

			_writeCss(
				getConfiguration(fragmentRendererContext),
				fragmentEntryLink.getEditableValues(), fragmentElementId,
				printWriter);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			NavigationMenuTag navigationMenuTag = _getNavigationMenuTag(
				themeDisplay.getCompanyId(),
				getConfiguration(fragmentRendererContext),
				fragmentEntryLink.getEditableValues());

			navigationMenuTag.doTag(httpServletRequest, httpServletResponse);

			printWriter.write("</div>");
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private NavigationMenuTag _getNavigationMenuTag(
			long companyId, String configuration, String editableValues)
		throws PortalException {

		NavigationMenuTag navigationMenuTag = new NavigationMenuTag();

		String displayStyle = GetterUtil.getString(
			_fragmentEntryConfigurationParser.getFieldValue(
				configuration, editableValues,
				LocaleUtil.getMostRelevantLocale(), "displayStyle"));

		DDMTemplate ddmTemplate = _getTagDDMTemplate(companyId, displayStyle);

		if (ddmTemplate != null) {
			navigationMenuTag.setDdmTemplateGroupId(ddmTemplate.getGroupId());
			navigationMenuTag.setDdmTemplateKey(ddmTemplate.getTemplateKey());
		}

		int sublevels = GetterUtil.getInteger(
			_fragmentEntryConfigurationParser.getFieldValue(
				configuration, editableValues,
				LocaleUtil.getMostRelevantLocale(), "sublevels"));

		navigationMenuTag.setDisplayDepth(sublevels + 1);

		String source = GetterUtil.getString(
			_fragmentEntryConfigurationParser.getFieldValue(
				configuration, editableValues,
				LocaleUtil.getMostRelevantLocale(), "source"));

		FragmentEntryMenuDisplayConfiguration
			fragmentEntryMenuDisplayConfiguration =
				new FragmentEntryMenuDisplayConfiguration(source);

		navigationMenuTag.setNavigationMenuMode(
			fragmentEntryMenuDisplayConfiguration.getNavigationMenuMode());
		navigationMenuTag.setRootItemId(
			fragmentEntryMenuDisplayConfiguration.getRootItemId());
		navigationMenuTag.setRootItemLevel(
			fragmentEntryMenuDisplayConfiguration.getRootItemLevel());
		navigationMenuTag.setRootItemType(
			fragmentEntryMenuDisplayConfiguration.getRootItemType());
		navigationMenuTag.setSiteNavigationMenuId(
			fragmentEntryMenuDisplayConfiguration.getSiteNavigationMenuId());

		return navigationMenuTag;
	}

	private DDMTemplate _getTagDDMTemplate(long companyId, String displayStyle)
		throws PortalException {

		Group companyGroup = _groupLocalService.getCompanyGroup(companyId);

		String ddmTemplateKey = "NAVBAR-BLANK-FTL";

		if (Objects.equals(displayStyle, "stacked")) {
			ddmTemplateKey = "LIST-MENU-FTL";
		}

		return _ddmTemplateLocalService.fetchTemplate(
			companyGroup.getGroupId(), _portal.getClassNameId(NavItem.class),
			ddmTemplateKey);
	}

	private void _writeCss(
		String configuration, String editableValues, String fragmentElementId,
		PrintWriter printWriter) {

		String styles = StringUtil.replace(
			StringUtil.read(
				getClass(),
				"/com/liferay/fragment/renderer/menu/display/internal" +
					"/dependencies/styles.tmpl"),
			"${", "}",
			HashMapBuilder.put(
				"fragmentElementId", fragmentElementId
			).put(
				"hoveredItemColor",
				GetterUtil.getString(
					_fragmentEntryConfigurationParser.getFieldValue(
						configuration, editableValues,
						LocaleUtil.getMostRelevantLocale(), "hoveredItemColor"),
					"inherit")
			).put(
				"selectedItemColor",
				GetterUtil.getString(
					_fragmentEntryConfigurationParser.getFieldValue(
						configuration, editableValues,
						LocaleUtil.getMostRelevantLocale(),
						"selectedItemColor"),
					"inherit")
			).build());

		printWriter.write(styles);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MenuDisplayFragmentRenderer.class);

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}