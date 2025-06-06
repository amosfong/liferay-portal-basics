/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.configuration.ConfigurationImpl;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class PropsUtil {

	public static void addProperties(Company company, Properties properties) {
		Configuration configuration = _getConfiguration(company);

		configuration.addProperties(properties);
	}

	public static void addProperties(
		Company company, UnicodeProperties unicodeProperties) {

		Configuration configuration = _getConfiguration(company);

		Properties properties = new Properties();

		properties.putAll(unicodeProperties);

		configuration.addProperties(properties);
	}

	public static void addProperties(Properties properties) {
		Configuration configuration = _getConfiguration();

		configuration.addProperties(properties);
	}

	public static void addProperties(UnicodeProperties unicodeProperties) {
		Configuration configuration = _getConfiguration();

		Properties properties = new Properties();

		properties.putAll(unicodeProperties);

		configuration.addProperties(properties);
	}

	public static boolean contains(Company company, String key) {
		Configuration configuration = _getConfiguration(company);

		return configuration.contains(key);
	}

	public static boolean contains(String key) {
		Configuration configuration = _getConfiguration();

		return configuration.contains(key);
	}

	public static String get(Company company, String key) {
		Configuration configuration = _getConfiguration(company);

		return configuration.get(key);
	}

	public static String get(Company company, String key, Filter filter) {
		Configuration configuration = _getConfiguration(company);

		return configuration.get(key, filter);
	}

	public static String get(String key) {
		Configuration configuration = _getConfiguration();

		return configuration.get(key);
	}

	public static String get(String key, Filter filter) {
		Configuration configuration = _getConfiguration();

		return configuration.get(key, filter);
	}

	public static String[] getArray(Company company, String key) {
		Configuration configuration = _getConfiguration(company);

		return configuration.getArray(key);
	}

	public static String[] getArray(
		Company company, String key, Filter filter) {

		Configuration configuration = _getConfiguration(company);

		return configuration.getArray(key, filter);
	}

	public static String[] getArray(String key) {
		Configuration configuration = _getConfiguration();

		return configuration.getArray(key);
	}

	public static String[] getArray(String key, Filter filter) {
		Configuration configuration = _getConfiguration();

		return configuration.getArray(key, filter);
	}

	public static List<String> getLoadedSources() {
		Configuration configuration = _getConfiguration();

		return configuration.getLoadedSources();
	}

	public static Properties getProperties() {
		return getProperties(false);
	}

	public static Properties getProperties(boolean includeSystem) {
		Configuration configuration = _getConfiguration();

		Properties properties = configuration.getProperties();

		if (!includeSystem) {
			return properties;
		}

		Properties systemCompanyProperties = _configuration.getProperties();

		Properties mergedProperties =
			(Properties)systemCompanyProperties.clone();

		mergedProperties.putAll(properties);

		return mergedProperties;
	}

	public static Properties getProperties(Company company) {
		return getProperties(company, false);
	}

	public static Properties getProperties(
		Company company, boolean includeSystem) {

		Configuration configuration = _getConfiguration(company);

		Properties properties = configuration.getProperties();

		if (!includeSystem) {
			return properties;
		}

		Properties systemCompanyProperties = _configuration.getProperties();

		Properties mergedProperties =
			(Properties)systemCompanyProperties.clone();

		mergedProperties.putAll(properties);

		return mergedProperties;
	}

	public static Properties getProperties(
		Company company, String prefix, boolean removePrefix) {

		Configuration configuration = _getConfiguration(company);

		return configuration.getProperties(prefix, removePrefix);
	}

	public static Properties getProperties(
		String prefix, boolean removePrefix) {

		Configuration configuration = _getConfiguration();

		return configuration.getProperties(prefix, removePrefix);
	}

	public static void removeProperties(
		Company company, Properties properties) {

		Configuration configuration = _getConfiguration(company);

		configuration.removeProperties(properties);
	}

	public static void removeProperties(Properties properties) {
		Configuration configuration = _getConfiguration();

		configuration.removeProperties(properties);
	}

	public static void set(Company company, String key, String value) {
		Configuration configuration = _getConfiguration(company);

		configuration.set(key, value);
	}

	public static void set(String key, String value) {
		Configuration configuration = _getConfiguration();

		configuration.set(key, value);
	}

	private static Configuration _getConfiguration() {
		if (_configurations == null) {
			return _configuration;
		}

		long companyId = CompanyThreadLocal.getCompanyId();

		if (companyId > CompanyConstants.SYSTEM) {
			Configuration configuration = _configurations.get(companyId);

			if (configuration == null) {
				String webId = null;

				try {
					Company company = CompanyLocalServiceUtil.getCompany(
						companyId);

					webId = company.getWebId();
				}
				catch (Exception exception) {
					_log.error(exception);
				}

				configuration = new ConfigurationImpl(
					PropsUtil.class.getClassLoader(), PropsFiles.PORTAL,
					companyId, webId);

				_configurations.put(companyId, configuration);
			}

			return configuration;
		}

		return _configuration;
	}

	private static Configuration _getConfiguration(Company company) {
		if (_configurations == null) {
			return _configuration;
		}

		long companyId = company.getCompanyId();

		Configuration configuration = _configurations.get(companyId);

		if (configuration == null) {
			configuration = new ConfigurationImpl(
				PropsUtil.class.getClassLoader(), PropsFiles.PORTAL, companyId,
				company.getWebId());

			_configurations.put(companyId, configuration);
		}

		return configuration;
	}

	private static String _getDefaultLiferayHome() {
		String defaultLiferayHome = null;

		if (ServerDetector.isTomcat()) {
			defaultLiferayHome = SystemProperties.get("catalina.base") + "/..";
		}
		else {
			defaultLiferayHome = SystemProperties.get("user.dir") + "/liferay";
		}

		defaultLiferayHome = StringUtil.replace(
			defaultLiferayHome, CharPool.BACK_SLASH, CharPool.SLASH);

		defaultLiferayHome = StringUtil.replace(
			defaultLiferayHome, StringPool.DOUBLE_SLASH, StringPool.SLASH);

		if (defaultLiferayHome.endsWith("/..")) {
			int pos = defaultLiferayHome.lastIndexOf(
				CharPool.SLASH, defaultLiferayHome.length() - 4);

			if (pos != -1) {
				defaultLiferayHome = defaultLiferayHome.substring(0, pos);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Default Liferay home " + defaultLiferayHome);
		}

		return defaultLiferayHome;
	}

	private static final Log _log = LogFactoryUtil.getLog(PropsUtil.class);

	private static final Configuration _configuration;
	private static final Map<Long, Configuration> _configurations;

	static {

		// Default liferay home directory

		SystemProperties.set(
			PropsKeys.DEFAULT_LIFERAY_HOME, _getDefaultLiferayHome());

		// Liferay home directory

		_configuration = ConfigurationFactoryImpl.CONFIGURATION_PORTAL;

		String liferayHome = _configuration.get(PropsKeys.LIFERAY_HOME);

		if (_log.isDebugEnabled()) {
			_log.debug("Configured Liferay home " + liferayHome);
		}

		SystemProperties.set(PropsKeys.LIFERAY_HOME, liferayHome);

		// Ehcache disk directory

		SystemProperties.set(
			"ehcache.disk.store.dir", liferayHome + "/data/ehcache");

		if (GetterUtil.getBoolean(
				SystemProperties.get("company-id-properties"))) {

			_configurations = new HashMap<>();
		}
		else {
			_configurations = null;
		}
	}

}