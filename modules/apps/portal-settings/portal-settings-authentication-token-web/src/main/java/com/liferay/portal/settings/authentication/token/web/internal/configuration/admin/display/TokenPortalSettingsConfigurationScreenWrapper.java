/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.settings.authentication.token.web.internal.configuration.admin.display;

import com.liferay.configuration.admin.display.ConfigurationScreen;
import com.liferay.configuration.admin.display.ConfigurationScreenWrapper;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.settings.configuration.admin.display.PortalSettingsConfigurationScreenContributor;
import com.liferay.portal.settings.configuration.admin.display.PortalSettingsConfigurationScreenFactory;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stian Sigvartsen
 */
@Component(service = ConfigurationScreen.class)
public class TokenPortalSettingsConfigurationScreenWrapper
	extends ConfigurationScreenWrapper {

	@Override
	protected ConfigurationScreen getConfigurationScreen() {
		return _portalSettingsConfigurationScreenFactory.create(
			new TokenPortalSettingsConfigurationScreenContributor());
	}

	@Reference
	private Language _language;

	@Reference
	private PortalSettingsConfigurationScreenFactory
		_portalSettingsConfigurationScreenFactory;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portal.settings.authentication.token.web)"
	)
	private ServletContext _servletContext;

	private class TokenPortalSettingsConfigurationScreenContributor
		implements PortalSettingsConfigurationScreenContributor {

		@Override
		public String getCategoryKey() {
			return "sso";
		}

		@Override
		public String getDeleteMVCActionCommandName() {
			return "/portal_settings/token_delete";
		}

		@Override
		public String getJspPath() {
			return "/dynamic_include/com.liferay.portal.settings.web/token.jsp";
		}

		@Override
		public String getKey() {
			return "token";
		}

		@Override
		public String getName(Locale locale) {
			return _language.get(locale, "token-configuration-name");
		}

		@Override
		public String getSaveMVCActionCommandName() {
			return "/portal_settings/token";
		}

		@Override
		public ServletContext getServletContext() {
			return _servletContext;
		}

	}

}