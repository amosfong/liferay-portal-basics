/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.configuration;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.configuration.Filter;

/**
 * @author Adam Brandizzi
 */
public class CalendarServiceConfigurationUtil {

	public static String get(String key) {
		return _configuration.get(key);
	}

	public static String get(String key, Filter filter) {
		return _configuration.get(key, filter);
	}

	private static final Configuration _configuration =
		ConfigurationFactoryUtil.getConfiguration(
			CalendarServiceConfigurationUtil.class.getClassLoader(), "portlet");

}