/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.internal.configurator;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.cache.PortalCacheReplicator;
import com.liferay.portal.cache.configuration.PortalCacheConfiguration;
import com.liferay.portal.cache.configuration.PortalCacheManagerConfiguration;
import com.liferay.portal.cache.ehcache.internal.EhcachePortalCacheConfiguration;
import com.liferay.portal.cache.ehcache.internal.constants.EhcacheConstants;
import com.liferay.portal.kernel.cache.PortalCacheListenerScope;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import java.net.URL;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.CacheConfiguration.CacheEventListenerFactoryConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.FactoryConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.event.NotificationScope;

/**
 * @author Tina Tian
 */
public class EhcachePortalCacheManagerConfigurator {

	public EhcachePortalCacheManagerConfigurator(
		Properties replicatorProperties,
		String defaultReplicatorPropertiesString) {

		_replicatorProperties = replicatorProperties;
		_defaultReplicatorPropertiesString = defaultReplicatorPropertiesString;
	}

	public ObjectValuePair<Configuration, PortalCacheManagerConfiguration>
		getConfigurationObjectValuePair(
			String portalCacheManagerName, URL configurationURL,
			ClassLoader classLoader, boolean usingDefault) {

		if (configurationURL == null) {
			throw new NullPointerException("Configuration path is null");
		}

		Configuration configuration = ConfigurationFactory.parseConfiguration(
			configurationURL);

		configuration.setName(portalCacheManagerName);

		PortalCacheManagerConfiguration portalCacheManagerConfiguration =
			_parseListenerConfigurations(
				configuration, classLoader, usingDefault);

		_clearListenerConfigrations(configuration);

		_populateCacheReplicator(portalCacheManagerConfiguration);

		return new ObjectValuePair<>(
			configuration, portalCacheManagerConfiguration);
	}

	protected Properties parseProperties(
		String propertiesString, String propertySeparator) {

		Properties properties = new Properties();

		if (propertiesString == null) {
			return properties;
		}

		String propertyLines = propertiesString.trim();

		propertyLines = StringUtil.replace(
			propertyLines, propertySeparator, StringPool.NEW_LINE);

		try {
			properties.load(new UnsyncStringReader(propertyLines));
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}

		return properties;
	}

	private void _clearListenerConfigrations(
		CacheConfiguration cacheConfiguration) {

		if (cacheConfiguration == null) {
			return;
		}

		List<?> factoryConfigurations =
			cacheConfiguration.getCacheEventListenerConfigurations();

		factoryConfigurations.clear();
	}

	private void _clearListenerConfigrations(Configuration configuration) {
		List<?> listenerFactoryConfigurations =
			configuration.getCacheManagerPeerListenerFactoryConfigurations();

		listenerFactoryConfigurations.clear();

		List<?> providerFactoryConfigurations =
			configuration.getCacheManagerPeerProviderFactoryConfiguration();

		providerFactoryConfigurations.clear();

		FactoryConfiguration<?> factoryConfiguration =
			configuration.getCacheManagerEventListenerFactoryConfiguration();

		if (factoryConfiguration != null) {
			factoryConfiguration.setClass(null);
		}

		_clearListenerConfigrations(
			configuration.getDefaultCacheConfiguration());

		Map<String, CacheConfiguration> cacheConfigurations =
			configuration.getCacheConfigurations();

		for (CacheConfiguration cacheConfiguration :
				cacheConfigurations.values()) {

			_clearListenerConfigrations(cacheConfiguration);
		}
	}

	@SuppressWarnings("deprecation")
	private boolean _isRequireSerialization(
		CacheConfiguration cacheConfiguration) {

		if (cacheConfiguration.isDiskPersistent() ||
			cacheConfiguration.isOverflowToDisk() ||
			cacheConfiguration.isOverflowToOffHeap()) {

			return true;
		}

		PersistenceConfiguration persistenceConfiguration =
			cacheConfiguration.getPersistenceConfiguration();

		if (persistenceConfiguration != null) {
			PersistenceConfiguration.Strategy strategy =
				persistenceConfiguration.getStrategy();

			if (!strategy.equals(PersistenceConfiguration.Strategy.NONE)) {
				return true;
			}
		}

		return false;
	}

	private Set<Properties> _parseCacheEventListenerConfigurations(
		CacheConfiguration cacheConfiguration, ClassLoader classLoader,
		boolean usingDefault) {

		if (usingDefault) {
			return Collections.emptySet();
		}

		Set<Properties> portalCacheListenerPropertiesSet = new HashSet<>();

		for (Object object :
				cacheConfiguration.getCacheEventListenerConfigurations()) {

			CacheEventListenerFactoryConfiguration
				cacheEventListenerFactoryConfiguration =
					(CacheEventListenerFactoryConfiguration)object;

			Properties properties = parseProperties(
				cacheEventListenerFactoryConfiguration.getProperties(),
				cacheEventListenerFactoryConfiguration.getPropertySeparator());

			String factoryClassName =
				cacheEventListenerFactoryConfiguration.
					getFullyQualifiedClassPath();

			properties.put(
				EhcacheConstants.
					CACHE_LISTENER_PROPERTIES_KEY_FACTORY_CLASS_LOADER,
				classLoader);
			properties.put(
				EhcacheConstants.
					CACHE_LISTENER_PROPERTIES_KEY_FACTORY_CLASS_NAME,
				factoryClassName);

			PortalCacheListenerScope portalCacheListenerScope =
				_portalCacheListenerScopes.get(
					cacheEventListenerFactoryConfiguration.getListenFor());

			properties.put(
				PortalCacheConfiguration.
					PORTAL_CACHE_LISTENER_PROPERTIES_KEY_SCOPE,
				portalCacheListenerScope);

			portalCacheListenerPropertiesSet.add(properties);
		}

		return portalCacheListenerPropertiesSet;
	}

	private Set<Properties> _parseCacheManagerEventListenerConfigurations(
		FactoryConfiguration<?> factoryConfiguration, ClassLoader classLoader) {

		if (factoryConfiguration == null) {
			return Collections.emptySet();
		}

		Properties properties = parseProperties(
			factoryConfiguration.getProperties(),
			factoryConfiguration.getPropertySeparator());

		properties.put(
			EhcacheConstants.
				CACHE_MANAGER_LISTENER_PROPERTIES_KEY_FACTORY_CLASS_LOADER,
			classLoader);
		properties.put(
			EhcacheConstants.
				CACHE_MANAGER_LISTENER_PROPERTIES_KEY_FACTORY_CLASS_NAME,
			factoryConfiguration.getFullyQualifiedClassPath());

		return Collections.singleton(properties);
	}

	private PortalCacheManagerConfiguration _parseListenerConfigurations(
		Configuration configuration, ClassLoader classLoader,
		boolean usingDefault) {

		Set<Properties> cacheManagerListenerPropertiesSet =
			_parseCacheManagerEventListenerConfigurations(
				configuration.
					getCacheManagerEventListenerFactoryConfiguration(),
				classLoader);

		CacheConfiguration defaultCacheConfiguration =
			configuration.getDefaultCacheConfiguration();

		if (defaultCacheConfiguration == null) {
			defaultCacheConfiguration = new CacheConfiguration();
		}

		defaultCacheConfiguration.setName(
			PortalCacheConfiguration.PORTAL_CACHE_NAME_DEFAULT);

		PortalCacheConfiguration defaultPortalCacheConfiguration =
			new EhcachePortalCacheConfiguration(
				defaultCacheConfiguration.getName(),
				_parseCacheEventListenerConfigurations(
					defaultCacheConfiguration, classLoader, usingDefault),
				_isRequireSerialization(defaultCacheConfiguration));

		Set<PortalCacheConfiguration> portalCacheConfigurations =
			new HashSet<>();

		Map<String, CacheConfiguration> cacheConfigurations =
			configuration.getCacheConfigurations();

		for (Map.Entry<String, CacheConfiguration> entry :
				cacheConfigurations.entrySet()) {

			CacheConfiguration cacheConfiguration = entry.getValue();

			portalCacheConfigurations.add(
				new EhcachePortalCacheConfiguration(
					cacheConfiguration.getName(),
					_parseCacheEventListenerConfigurations(
						cacheConfiguration, classLoader, usingDefault),
					_isRequireSerialization(cacheConfiguration)));
		}

		return new PortalCacheManagerConfiguration(
			cacheManagerListenerPropertiesSet, defaultPortalCacheConfiguration,
			portalCacheConfigurations);
	}

	private void _populateCacheReplicator(
		PortalCacheConfiguration portalCacheConfiguration,
		String replicatorPropertiesString) {

		Properties replicatorProperties = parseProperties(
			replicatorPropertiesString, StringPool.COMMA);

		replicatorProperties.put(PortalCacheReplicator.REPLICATOR, true);

		Set<Properties> portalCacheListenerPropertiesSet =
			portalCacheConfiguration.getPortalCacheListenerPropertiesSet();

		portalCacheListenerPropertiesSet.add(replicatorProperties);
	}

	private void _populateCacheReplicator(
		PortalCacheManagerConfiguration portalCacheManagerConfiguration) {

		if (_replicatorProperties == null) {
			return;
		}

		Set<String> portalCacheNames = new HashSet<>(
			_replicatorProperties.stringPropertyNames());

		portalCacheNames.addAll(
			portalCacheManagerConfiguration.getPortalCacheNames());

		for (String portalCacheName : portalCacheNames) {
			_populateCacheReplicator(
				portalCacheManagerConfiguration.getPortalCacheConfiguration(
					portalCacheName),
				GetterUtil.getString(
					_replicatorProperties.getProperty(portalCacheName),
					_defaultReplicatorPropertiesString));
		}

		_populateCacheReplicator(
			portalCacheManagerConfiguration.
				getDefaultPortalCacheConfiguration(),
			_defaultReplicatorPropertiesString);
	}

	private static final Map<NotificationScope, PortalCacheListenerScope>
		_portalCacheListenerScopes =
			new EnumMap<NotificationScope, PortalCacheListenerScope>(
				NotificationScope.class) {

				{
					put(NotificationScope.ALL, PortalCacheListenerScope.ALL);
					put(
						NotificationScope.LOCAL,
						PortalCacheListenerScope.LOCAL);
					put(
						NotificationScope.REMOTE,
						PortalCacheListenerScope.REMOTE);
				}
			};

	private final String _defaultReplicatorPropertiesString;
	private final Properties _replicatorProperties;

}