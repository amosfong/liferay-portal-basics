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
public class LanguagesSiteSettingsConfigurationScreenWrapper
	extends ConfigurationScreenWrapper {

	@Override
	protected ConfigurationScreen getConfigurationScreen() {
		return _siteSettingsConfigurationScreenFactory.create(
			new LanguagesSiteSettingsConfigurationScreenContributor());
	}

	@Reference
	private Language _language;

	@Reference(target = "(osgi.web.symbolicname=com.liferay.site.admin.web)")
	private ServletContext _servletContext;

	@Reference
	private SiteSettingsConfigurationScreenFactory
		_siteSettingsConfigurationScreenFactory;

	private class LanguagesSiteSettingsConfigurationScreenContributor
		implements SiteSettingsConfigurationScreenContributor {

		@Override
		public String getCategoryKey() {
			return "localization";
		}

		@Override
		public String getJspPath() {
			return "/site_settings/languages.jsp";
		}

		@Override
		public String getKey() {
			return "site-configuration-languages";
		}

		@Override
		public String getName(Locale locale) {
			return _language.get(locale, "languages");
		}

		@Override
		public String getSaveMVCActionCommandName() {
			return "/site_admin/edit_languages";
		}

		@Override
		public ServletContext getServletContext() {
			return _servletContext;
		}

		@Override
		public boolean isVisible(Group group) {
			if (group.isCompany()) {
				return false;
			}

			return true;
		}

	}

}