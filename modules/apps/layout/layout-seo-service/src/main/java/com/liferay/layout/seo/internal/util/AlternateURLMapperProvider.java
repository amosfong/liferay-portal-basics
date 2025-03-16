/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lourdes Fernández Besada
 */
public class AlternateURLMapperProvider {

	public AlternateURLMapperProvider(
		ClassNameLocalService classNameLocalService, Portal portal) {

		_classNameLocalService = classNameLocalService;
		_portal = portal;
	}

	public AlternateURLMapperProvider.AlternateURLMapper getAlternateURLMapper(
		HttpServletRequest httpServletRequest) {

		return new AlternateURLMapperProvider.DefaultPageAlternateURLMapper(
			_portal);
	}

	public static class DefaultPageAlternateURLMapper
		implements AlternateURLMapperProvider.AlternateURLMapper {

		@Override
		public String getAlternateURL(
				String canonicalURL, ThemeDisplay themeDisplay, Locale locale,
				Layout layout)
			throws PortalException {

			return _portal.getAlternateURL(
				canonicalURL, themeDisplay, locale, layout);
		}

		protected DefaultPageAlternateURLMapper(Portal portal) {
			_portal = portal;
		}

		private final Portal _portal;

	}

	public interface AlternateURLMapper {

		public String getAlternateURL(
				String canonicalURL, ThemeDisplay themeDisplay, Locale locale,
				Layout layout)
			throws PortalException;

		public default Map<Locale, String> getAlternateURLs(
				String canonicalURL, ThemeDisplay themeDisplay, Layout layout,
				Set<Locale> locales)
			throws PortalException {

			Map<Locale, String> alternateURLs = new HashMap<>();

			for (Locale locale : locales) {
				alternateURLs.put(
					locale,
					getAlternateURL(
						canonicalURL, themeDisplay, locale, layout));
			}

			return alternateURLs;
		}

	}

	private final ClassNameLocalService _classNameLocalService;
	private final Portal _portal;

}