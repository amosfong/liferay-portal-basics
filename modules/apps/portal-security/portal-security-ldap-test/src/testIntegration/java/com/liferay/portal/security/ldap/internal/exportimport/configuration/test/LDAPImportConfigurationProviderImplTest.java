/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.ldap.internal.exportimport.configuration.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.security.ldap.configuration.ConfigurationProvider;
import com.liferay.portal.security.ldap.exportimport.configuration.LDAPImportConfiguration;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alvaro Saugar
 */
@RunWith(Arquillian.class)
public class LDAPImportConfigurationProviderImplTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_companyId = TestPropsValues.getCompanyId();
		_defaultLDAPImportConfiguration = ConfigurableUtil.createConfigurable(
			LDAPImportConfiguration.class, Collections.emptyMap());
	}

	@Test
	public void testGetDefaultSettings() throws Exception {
		LDAPImportConfiguration ldapImportConfiguration =
			_ldapImportConfigurationProvider.getConfiguration(_companyId);

		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importEnabled(),
			ldapImportConfiguration.importEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importOnStartup(),
			ldapImportConfiguration.importOnStartup());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importMethod(),
			ldapImportConfiguration.importMethod());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importInterval(),
			ldapImportConfiguration.importInterval());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importCreateRolePerGroup(),
			ldapImportConfiguration.importCreateRolePerGroup());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importGroupCacheEnabled(),
			ldapImportConfiguration.importGroupCacheEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importLockExpirationTime(),
			ldapImportConfiguration.importLockExpirationTime());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordAutogenerated(),
			ldapImportConfiguration.importUserPasswordAutogenerated());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordDefault(),
			ldapImportConfiguration.importUserPasswordDefault());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserSyncStrategy(),
			ldapImportConfiguration.importUserSyncStrategy());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordEnabled(),
			ldapImportConfiguration.importUserPasswordEnabled());
	}

	@Test
	public void testGetInstanceSettings() throws Exception {
		String pid = ConfigurationTestUtil.createFactoryConfiguration(
			LDAPImportConfiguration.class.getName(),
			HashMapDictionaryBuilder.<String, Object>put(
				"companyId", _companyId
			).put(
				"importEnabled", true
			).put(
				"importOnStartup", false
			).build());

		LDAPImportConfiguration ldapImportConfiguration =
			_ldapImportConfigurationProvider.getConfiguration(_companyId);

		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importCreateRolePerGroup(),
			ldapImportConfiguration.importCreateRolePerGroup());
		Assert.assertTrue(ldapImportConfiguration.importEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importGroupCacheEnabled(),
			ldapImportConfiguration.importGroupCacheEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importInterval(),
			ldapImportConfiguration.importInterval());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importLockExpirationTime(),
			ldapImportConfiguration.importLockExpirationTime());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importMethod(),
			ldapImportConfiguration.importMethod());
		Assert.assertFalse(ldapImportConfiguration.importOnStartup());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordAutogenerated(),
			ldapImportConfiguration.importUserPasswordAutogenerated());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordDefault(),
			ldapImportConfiguration.importUserPasswordDefault());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordEnabled(),
			ldapImportConfiguration.importUserPasswordEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserSyncStrategy(),
			ldapImportConfiguration.importUserSyncStrategy());

		ConfigurationTestUtil.deleteFactoryConfiguration(
			pid, LDAPImportConfiguration.class.getName());
	}

	@Test
	public void testGetInstanceSettingsWithSystemSettings() throws Exception {
		ConfigurationTestUtil.saveConfiguration(
			LDAPImportConfiguration.class.getName(),
			HashMapDictionaryBuilder.<String, Object>put(
				"companyId", 0
			).put(
				"importEnabled", false
			).put(
				"importOnStartup", true
			).build());

		String pid = ConfigurationTestUtil.createFactoryConfiguration(
			LDAPImportConfiguration.class.getName(),
			HashMapDictionaryBuilder.<String, Object>put(
				"companyId", _companyId
			).put(
				"importEnabled", true
			).put(
				"importOnStartup", false
			).build());

		LDAPImportConfiguration ldapImportConfiguration =
			_ldapImportConfigurationProvider.getConfiguration(_companyId);

		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importCreateRolePerGroup(),
			ldapImportConfiguration.importCreateRolePerGroup());
		Assert.assertTrue(ldapImportConfiguration.importEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importGroupCacheEnabled(),
			ldapImportConfiguration.importGroupCacheEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importInterval(),
			ldapImportConfiguration.importInterval());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importLockExpirationTime(),
			ldapImportConfiguration.importLockExpirationTime());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importMethod(),
			ldapImportConfiguration.importMethod());
		Assert.assertFalse(ldapImportConfiguration.importOnStartup());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordAutogenerated(),
			ldapImportConfiguration.importUserPasswordAutogenerated());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordDefault(),
			ldapImportConfiguration.importUserPasswordDefault());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordEnabled(),
			ldapImportConfiguration.importUserPasswordEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserSyncStrategy(),
			ldapImportConfiguration.importUserSyncStrategy());

		ConfigurationTestUtil.deleteFactoryConfiguration(
			pid, LDAPImportConfiguration.class.getName());
	}

	@Test
	public void testGetSystemSettings() throws Exception {
		ConfigurationTestUtil.saveConfiguration(
			LDAPImportConfiguration.class.getName(),
			HashMapDictionaryBuilder.<String, Object>put(
				"companyId", 0
			).put(
				"importEnabled", false
			).put(
				"importOnStartup", true
			).build());

		LDAPImportConfiguration ldapImportConfiguration =
			_ldapImportConfigurationProvider.getConfiguration(_companyId);

		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importCreateRolePerGroup(),
			ldapImportConfiguration.importCreateRolePerGroup());
		Assert.assertFalse(ldapImportConfiguration.importEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importGroupCacheEnabled(),
			ldapImportConfiguration.importGroupCacheEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importInterval(),
			ldapImportConfiguration.importInterval());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importLockExpirationTime(),
			ldapImportConfiguration.importLockExpirationTime());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importMethod(),
			ldapImportConfiguration.importMethod());
		Assert.assertTrue(ldapImportConfiguration.importOnStartup());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordAutogenerated(),
			ldapImportConfiguration.importUserPasswordAutogenerated());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordDefault(),
			ldapImportConfiguration.importUserPasswordDefault());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserPasswordEnabled(),
			ldapImportConfiguration.importUserPasswordEnabled());
		Assert.assertEquals(
			_defaultLDAPImportConfiguration.importUserSyncStrategy(),
			ldapImportConfiguration.importUserSyncStrategy());

		ConfigurationTestUtil.deleteConfiguration(
			LDAPImportConfiguration.class.getName());
	}

	private static long _companyId;
	private static LDAPImportConfiguration _defaultLDAPImportConfiguration;

	@Inject(
		filter = "factoryPid=com.liferay.portal.security.ldap.exportimport.configuration.LDAPImportConfiguration"
	)
	private ConfigurationProvider<LDAPImportConfiguration>
		_ldapImportConfigurationProvider;

}