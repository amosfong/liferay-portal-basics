/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.menu.web.internal.portlet.contributor;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.toolbar.contributor.PortletToolbarContributor;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.constants.SiteNavigationMenuPortletKeys;
import com.liferay.site.navigation.menu.web.internal.display.context.SiteNavigationMenuDisplayContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	property = {
		"javax.portlet.name=" + SiteNavigationMenuPortletKeys.SITE_NAVIGATION_MENU,
		"mvc.path=-", "mvc.path=/view.jsp"
	},
	service = PortletToolbarContributor.class
)
public class SiteNavigationMenuAddPortletToolbarContributor
	implements PortletToolbarContributor {

	@Override
	public List<Menu> getPortletTitleMenus(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group scopeGroup = themeDisplay.getScopeGroup();

		if ((scopeGroup == null) || scopeGroup.isLayoutPrototype()) {
			return Collections.emptyList();
		}

		List<MenuItem> menuItems = _getPortletTitleMenuItems(portletRequest);

		if (menuItems.isEmpty()) {
			return Collections.emptyList();
		}

		List<Menu> menus = new ArrayList<>();

		Menu menu = new Menu();

		menu.setDirection("right");
		menu.setExtended(false);
		menu.setIcon("plus");
		menu.setMarkupView("lexicon");
		menu.setMenuItems(menuItems);
		menu.setMessage("add");
		menu.setScroll(false);
		menu.setShowArrow(false);
		menu.setShowWhenSingleIcon(true);

		menus.add(menu);

		return menus;
	}

	private MenuItem _createMenuItem(
			ThemeDisplay themeDisplay, PortletRequest portletRequest)
		throws Exception {

		SiteNavigationMenuDisplayContext siteNavigationMenuDisplayContext =
			new SiteNavigationMenuDisplayContext(
				_portal.getHttpServletRequest(portletRequest));

		long siteNavigationMenuId =
			siteNavigationMenuDisplayContext.getSelectSiteNavigationMenuId();

		if (siteNavigationMenuId > 0) {
			return null;
		}

		URLMenuItem urlMenuItem = new URLMenuItem();

		urlMenuItem.setLabel(
			_language.get(
				_portal.getHttpServletRequest(portletRequest), "add-page"));

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		urlMenuItem.setURL(
			PortletURLBuilder.create(
				PortletProviderUtil.getPortletURL(
					portletRequest, Layout.class.getName(),
					PortletProvider.Action.EDIT)
			).setMVCRenderCommandName(
				"/layout_admin/select_layout_page_template_entry"
			).setRedirect(
				_portal.getLayoutFullURL(themeDisplay)
			).setPortletResource(
				portletDisplay.getPortletName()
			).setParameter(
				"groupId", themeDisplay.getScopeGroupId()
			).setParameter(
				"privateLayout",
				() -> {
					Layout layout = themeDisplay.getLayout();

					return layout.isPrivateLayout();
				}
			).buildString());

		return urlMenuItem;
	}

	private List<MenuItem> _getPortletTitleMenuItems(
		PortletRequest portletRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			if (!LayoutPermissionUtil.contains(
					themeDisplay.getPermissionChecker(),
					themeDisplay.getLayout(), ActionKeys.UPDATE)) {

				return Collections.emptyList();
			}

			MenuItem menuItem = _createMenuItem(themeDisplay, portletRequest);

			if (menuItem == null) {
				return Collections.emptyList();
			}

			return Collections.singletonList(menuItem);
		}
		catch (Exception exception) {
			_log.error(
				"Unable to set edit site navigation menu to menu item",
				exception);

			return Collections.emptyList();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SiteNavigationMenuAddPortletToolbarContributor.class);

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}