/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.TabsItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.TabsItemListBuilder;
import com.liferay.layout.admin.constants.LayoutScreenNavigationEntryConstants;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.style.book.model.StyleBookEntry;
import com.liferay.style.book.util.DefaultStyleBookEntryUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Víctor Galán
 */
public class LayoutLookAndFeelDisplayContext {

	public LayoutLookAndFeelDisplayContext(
		HttpServletRequest httpServletRequest,
		LayoutsAdminDisplayContext layoutsAdminDisplayContext,
		LiferayPortletResponse liferayPortletResponse) {

		_httpServletRequest = httpServletRequest;
		_layoutsAdminDisplayContext = layoutsAdminDisplayContext;
		_liferayPortletResponse = liferayPortletResponse;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getMasterLayoutConfigurationProps() {
		return HashMapBuilder.<String, Object>put(
			"editMasterLayoutURL",
			() -> {
				if (!hasMasterLayout()) {
					return StringPool.BLANK;
				}

				Layout selLayout = _layoutsAdminDisplayContext.getSelLayout();

				Layout masterLayout = LayoutLocalServiceUtil.getLayout(
					selLayout.getMasterLayoutPlid());

				return HttpComponentsUtil.addParameters(
					PortalUtil.getLayoutFullURL(
						masterLayout.fetchDraftLayout(), _themeDisplay),
					"p_l_back_url", _themeDisplay.getURLCurrent(),
					"p_l_back_url_title",
					LanguageUtil.get(
						_themeDisplay.getLocale(),
						LayoutScreenNavigationEntryConstants.ENTRY_KEY_DESIGN),
					"p_l_mode", Constants.EDIT);
			}
		).put(
			"isReadOnly", _layoutsAdminDisplayContext.isReadOnly()
		).put(
			"masterLayoutName", getMasterLayoutName()
		).put(
			"masterLayoutPlid",
			() -> {
				if (hasMasterLayout()) {
					Layout selLayout =
						_layoutsAdminDisplayContext.getSelLayout();

					return String.valueOf(selLayout.getMasterLayoutPlid());
				}

				return StringPool.BLANK;
			}
		).build();
	}

	public String getMasterLayoutName() {
		if (_masterLayoutName != null) {
			return _masterLayoutName;
		}

		String masterLayoutName = LanguageUtil.get(
			_httpServletRequest, "blank");

		Layout selLayout = _layoutsAdminDisplayContext.getSelLayout();

		if (selLayout.getMasterLayoutPlid() > 0) {
			LayoutPageTemplateEntry layoutPageTemplateEntry =
				LayoutPageTemplateEntryLocalServiceUtil.
					fetchLayoutPageTemplateEntryByPlid(
						selLayout.getMasterLayoutPlid());

			if (layoutPageTemplateEntry != null) {
				masterLayoutName = layoutPageTemplateEntry.getName();
			}
		}

		_masterLayoutName = masterLayoutName;

		return _masterLayoutName;
	}

	public Map<String, Object> getStyleBookConfigurationProps() {
		return HashMapBuilder.<String, Object>put(
			"isReadOnly", _layoutsAdminDisplayContext.isReadOnly()
		).put(
			"styleBookEntryId",
			() -> {
				Layout selLayout = _layoutsAdminDisplayContext.getSelLayout();

				return String.valueOf(selLayout.getStyleBookEntryId());
			}
		).put(
			"styleBookEntryName", getStyleBookEntryName()
		).build();
	}

	public String getStyleBookEntryName() {
		Layout selLayout = _layoutsAdminDisplayContext.getSelLayout();

		StyleBookEntry defaultStyleBookEntry =
			DefaultStyleBookEntryUtil.getDefaultStyleBookEntry(selLayout);

		if (selLayout.getStyleBookEntryId() > 0) {
			return defaultStyleBookEntry.getName();
		}

		if (defaultStyleBookEntry == null) {
			return LanguageUtil.get(_httpServletRequest, "styles-from-theme");
		}

		if (hasEditableMasterLayout() &&
			(selLayout.getMasterLayoutPlid() > 0)) {

			return LanguageUtil.get(_httpServletRequest, "styles-from-master");
		}

		return LanguageUtil.get(_httpServletRequest, "styles-by-default");
	}

	public List<TabsItem> getTabsItems() {
		return TabsItemListBuilder.add(
			tabsItem -> {
				tabsItem.setActive(true);
				tabsItem.setLabel(LanguageUtil.get(_httpServletRequest, "css"));
				tabsItem.setPanelId("css");
			}
		).add(
			tabsItem -> {
				tabsItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "javascript"));
				tabsItem.setPanelId("javascript");
			}
		).build();
	}

	public boolean hasEditableMasterLayout() {
		if (_hasEditableMasterLayout != null) {
			return _hasEditableMasterLayout;
		}

		boolean hasEditableMasterLayout = false;

		Layout selLayout = _layoutsAdminDisplayContext.getSelLayout();

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateEntryLocalServiceUtil.
				fetchLayoutPageTemplateEntryByPlid(selLayout.getPlid());

		if (layoutPageTemplateEntry == null) {
			layoutPageTemplateEntry =
				LayoutPageTemplateEntryLocalServiceUtil.
					fetchLayoutPageTemplateEntryByPlid(selLayout.getClassPK());
		}

		if ((layoutPageTemplateEntry == null) ||
			!Objects.equals(
				layoutPageTemplateEntry.getType(),
				LayoutPageTemplateEntryTypeConstants.MASTER_LAYOUT)) {

			hasEditableMasterLayout = true;
		}

		_hasEditableMasterLayout = hasEditableMasterLayout;

		return _hasEditableMasterLayout;
	}

	public boolean hasMasterLayout() {
		if (_hasMasterLayout != null) {
			return _hasMasterLayout;
		}

		boolean hasMasterLayout = false;

		Layout selLayout = _layoutsAdminDisplayContext.getSelLayout();

		if (selLayout.getMasterLayoutPlid() > 0) {
			LayoutPageTemplateEntry layoutPageTemplateEntry =
				LayoutPageTemplateEntryLocalServiceUtil.
					fetchLayoutPageTemplateEntryByPlid(
						selLayout.getMasterLayoutPlid());

			if (layoutPageTemplateEntry != null) {
				hasMasterLayout = true;
			}
		}

		_hasMasterLayout = hasMasterLayout;

		return _hasMasterLayout;
	}

	public boolean isIconSelectorVisible() {
		Layout selLayout = _layoutsAdminDisplayContext.getSelLayout();

		if (selLayout.isTypeAssetDisplay()) {
			return false;
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateEntryLocalServiceUtil.
				fetchLayoutPageTemplateEntryByPlid(selLayout.getPlid());

		if (layoutPageTemplateEntry == null) {
			layoutPageTemplateEntry =
				LayoutPageTemplateEntryLocalServiceUtil.
					fetchLayoutPageTemplateEntryByPlid(selLayout.getClassPK());
		}

		if ((layoutPageTemplateEntry != null) &&
			Objects.equals(
				layoutPageTemplateEntry.getType(),
				LayoutPageTemplateEntryTypeConstants.MASTER_LAYOUT)) {

			return false;
		}

		return true;
	}

	private String _getLayoutRootNodeName() {
		LayoutSet layoutSet = _layoutsAdminDisplayContext.getSelLayoutSet();

		Group group = GroupLocalServiceUtil.fetchGroup(layoutSet.getGroupId());

		if (group == null) {
			return StringPool.DASH;
		}

		return group.getLayoutRootNodeName(
			layoutSet.isPrivateLayout(), _themeDisplay.getLocale());
	}

	private Boolean _hasEditableMasterLayout;
	private Boolean _hasMasterLayout;
	private final HttpServletRequest _httpServletRequest;
	private final LayoutsAdminDisplayContext _layoutsAdminDisplayContext;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _masterLayoutName;
	private final ThemeDisplay _themeDisplay;

}