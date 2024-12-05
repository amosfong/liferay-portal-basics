/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.admin.web.internal.configuration.admin.display;

import com.liferay.configuration.admin.display.ConfigurationScreen;
import com.liferay.configuration.admin.display.ConfigurationScreenWrapper;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Group;
import com.liferay.site.settings.configuration.admin.display.SiteSettingsConfigurationScreenContributor;
import com.liferay.site.settings.configuration.admin.display.SiteSettingsConfigurationScreenFactory;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ConfigurationScreen.class)
public class PublicPrivateSiteURLSiteSettingsConfigurationScreenWrapper
	extends ConfigurationScreenWrapper {

	@Override
	protected ConfigurationScreen getConfigurationScreen() {
		return _siteSettingsConfigurationScreenFactory.create(
			new PublicPrivateSiteURLSiteSettingsConfigurationScreenContributor());
	}

	@Reference
	private Language _language;

	@Reference(target = "(osgi.web.symbolicname=com.liferay.site.admin.web)")
	private ServletContext _servletContext;

	@Reference
	private SiteSettingsConfigurationScreenFactory
		_siteSettingsConfigurationScreenFactory;

	private class PublicPrivateSiteURLSiteSettingsConfigurationScreenContributor
		implements SiteSettingsConfigurationScreenContributor {

		@Override
		public String getCategoryKey() {
			return "site-configuration";
		}

		@Override
		public String getJspPath() {
			return "/site_settings/public_private_site_url.jsp";
		}

		@Override
		public String getKey() {
			return "site-configuration-public-private-site-url";
		}

		@Override
		public String getName(Locale locale) {
			return _language.get(locale, "site-url");
		}

		@Override
		public String getSaveMVCActionCommandName() {
			return "/site_admin/edit_site_url";
		}

		@Override
		public ServletContext getServletContext() {
			return _servletContext;
		}

		@Override
		public boolean isVisible(Group group) {
			if (group.isCompany() || !group.isPrivateLayoutsEnabled()) {
				return false;
			}

			return true;
		}

	}

}