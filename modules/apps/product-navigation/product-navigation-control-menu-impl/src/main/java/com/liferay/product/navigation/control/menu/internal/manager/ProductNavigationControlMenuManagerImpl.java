/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.control.menu.internal.manager;

import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.control.menu.manager.ProductNavigationControlMenuManager;
import com.liferay.site.configuration.MenuAccessConfiguration;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mikel Lorza
 */
@Component(service = ProductNavigationControlMenuManager.class)
public class ProductNavigationControlMenuManagerImpl
	implements ProductNavigationControlMenuManager {

	@Override
	public boolean isShowControlMenu(HttpServletRequest httpServletRequest) {
		if (Objects.equals(
				Constants.PREVIEW,
				ParamUtil.getString(
					httpServletRequest, "p_l_mode", Constants.VIEW)) ||
			_isLockedLayoutView(httpServletRequest) ||
			_isGuestUser(httpServletRequest)) {

			return false;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Group group = themeDisplay.getScopeGroup();
		Layout layout = themeDisplay.getLayout();

		if (!group.isSite() || layout.isDraftLayout() ||
			layout.isTypeControlPanel()) {

			return true;
		}

		try {
			MenuAccessConfiguration menuAccessConfiguration =
				_configurationProvider.getGroupConfiguration(
					MenuAccessConfiguration.class, group.getGroupId());

			if ((menuAccessConfiguration != null) &&
				menuAccessConfiguration.showControlMenuByRole()) {

				String[] accessToControlMenuRoleIds =
					menuAccessConfiguration.accessToControlMenuRoleIds();

				User user = themeDisplay.getUser();

				for (Role role : user.getAllRoles()) {
					if (Objects.equals(
							role.getName(), RoleConstants.ADMINISTRATOR) ||
						Objects.equals(
							role.getRoleId(),
							RoleConstants.SITE_ADMINISTRATOR) ||
						ArrayUtil.contains(
							accessToControlMenuRoleIds,
							String.valueOf(role.getRoleId()))) {

						return true;
					}
				}

				return false;
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		return true;
	}

	private boolean _isGuestUser(HttpServletRequest httpServletRequest) {
		try {
			User user = _portal.getUser(httpServletRequest);

			if (user == null) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)httpServletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				user = themeDisplay.getUser();
			}

			if ((user == null) || user.isGuestUser()) {
				return true;
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to determine if user is guest user", exception);
			}
		}

		return false;
	}

	private boolean _isLockedLayoutView(HttpServletRequest httpServletRequest) {
		String mvcRenderCommandName =
			_portal.getPortletNamespace(LayoutAdminPortletKeys.GROUP_PAGES) +
				"mvcRenderCommandName";

		if (Objects.equals(
				ParamUtil.getString(httpServletRequest, mvcRenderCommandName),
				"/layout_admin/locked_layout")) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductNavigationControlMenuManagerImpl.class);

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Portal _portal;

}