/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.client.extension.type.internal.configuration.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.client.extension.constants.ClientExtensionEntryConstants;
import com.liferay.client.extension.model.ClientExtensionEntryRel;
import com.liferay.client.extension.service.ClientExtensionEntryRelLocalService;
import com.liferay.client.extension.type.CET;
import com.liferay.client.extension.type.manager.CETManager;
import com.liferay.feature.flag.test.util.FeatureFlagTestHelper;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.osgi.util.service.OSGiServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeoutException;

import javax.portlet.Portlet;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Gregory Amerson
 */
@RunWith(Arquillian.class)
public class CETConfigurationFactoryTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		Company company = CompanyLocalServiceUtil.addCompany(
			null, _VIRTUAL_HOSTNAME, _VIRTUAL_HOSTNAME, _VIRTUAL_HOSTNAME, 0,
			true, true, null, null, null, null, null, null);

		_autoCloseables.add(
			() -> CompanyLocalServiceUtil.deleteCompany(company));

		_virtualInstanceCompanyId = company.getCompanyId();
	}

	@AfterClass
	public static void tearDownClass() {
		ListIterator<AutoCloseable> listIterator = _autoCloseables.listIterator(
			_autoCloseables.size());

		while (listIterator.hasPrevious()) {
			AutoCloseable previousAutoCloseable = listIterator.previous();

			try {
				previousAutoCloseable.close();
			}
			catch (Exception exception) {
				_log.error(exception);
			}
		}
	}

	@Test
	public void testActivate() throws Exception {
		FeatureFlagTestHelper featureFlagTestHelper =
			new FeatureFlagTestHelper();

		featureFlagTestHelper.setFeatureFlagValue(
			_virtualInstanceCompanyId, "LPS-202104", true);

		String liferayMode = SystemProperties.get("liferay.mode");

		try {
			_testActivate();
		}
		finally {
			featureFlagTestHelper.setFeatureFlagValue(
				_virtualInstanceCompanyId, "LPS-202104", false);

			SystemProperties.set("liferay.mode", liferayMode);
		}
	}

	@Test
	public void testControlPanelThemeCSSCET() throws Exception {
		Company company = CompanyTestUtil.addCompany();

		String pid1 = ConfigurationTestUtil.createFactoryConfiguration(
			_PID, _getThemeCSSConfigurationProperties(company.getCompanyId()));

		String pid2 = null;

		try (LogCapture logCapture = LoggerTestUtil.configureLog4JLogger(
				"com.liferay.client.extension.type.internal.configuration." +
					"CETConfigurationFactory",
				LoggerTestUtil.ERROR)) {

			Layout layout = _getControlPanelLayout(company.getCompanyId());

			List<ClientExtensionEntryRel> clientExtensionEntryRels =
				_clientExtensionEntryRelLocalService.
					getClientExtensionEntryRels(
						_portal.getClassNameId(Layout.class), layout.getPlid(),
						ClientExtensionEntryConstants.TYPE_THEME_CSS);

			Assert.assertEquals(
				clientExtensionEntryRels.toString(), 1,
				clientExtensionEntryRels.size());

			pid2 = ConfigurationTestUtil.createFactoryConfiguration(
				_PID,
				_getThemeCSSConfigurationProperties(company.getCompanyId()));

			clientExtensionEntryRels =
				_clientExtensionEntryRelLocalService.
					getClientExtensionEntryRels(
						_portal.getClassNameId(Layout.class), layout.getPlid(),
						ClientExtensionEntryConstants.TYPE_THEME_CSS);

			Assert.assertEquals(
				clientExtensionEntryRels.toString(), 2,
				clientExtensionEntryRels.size());

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals(
				"Only one theme CSS client extension can be applied at a " +
					"time. To avoid conflicts, none of them will be applied.",
				logEntry.getMessage());
		}
		finally {
			ConfigurationTestUtil.deleteConfiguration(pid1);
			ConfigurationTestUtil.deleteConfiguration(pid2);
		}
	}

	private Layout _getControlPanelLayout(long companyId)
		throws PortalException {

		Group group = _groupLocalService.fetchGroup(
			companyId, GroupConstants.CONTROL_PANEL);

		List<Layout> layouts = _layoutLocalService.getLayouts(
			group.getGroupId(), true);

		if (ListUtil.isEmpty(layouts)) {
			throw new NoSuchLayoutException(
				"Unable to get control panel layout");
		}

		return layouts.get(0);
	}

	private Dictionary<String, Object> _getThemeCSSConfigurationProperties(
			long companyId)
		throws Exception {

		String name = RandomTestUtil.randomString();

		return HashMapDictionaryBuilder.<String, Object>put(
			"baseURL", "${portalURL}/o/" + name
		).put(
			"companyId", companyId
		).put(
			"name", name
		).put(
			"projectId", name
		).put(
			"projectName", name
		).put(
			"service.pid", _PID + "~" + name
		).put(
			"type", ClientExtensionEntryConstants.TYPE_THEME_CSS
		).put(
			"typeSettings", Collections.singletonList("scope=controlPanel")
		).put(
			"userId", TestPropsValues.getUserId()
		).put(
			"webContextPath", "/" + name
		).build();
	}

	private void _testActivate() throws Exception {
		SystemProperties.clear("liferay.mode");

		Bundle bundle = FrameworkUtil.getBundle(
			CETConfigurationFactoryTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		Configuration configuration = OSGiServiceUtil.callService(
			bundleContext, ConfigurationAdmin.class,
			(ConfigurationAdmin configurationAdmin) ->
				configurationAdmin.getFactoryConfiguration(
					"com.liferay.client.extension.type.configuration." +
						"CETConfiguration",
					"test/" + _VIRTUAL_HOSTNAME, StringPool.QUESTION));

		ConfigurationTestUtil.saveConfiguration(
			configuration.getPid(),
			HashMapDictionaryBuilder.<String, Object>put(
				"baseURL", "${portalURL}/o/test_" + _VIRTUAL_HOSTNAME
			).put(
				"buildTimestamp", System.currentTimeMillis()
			).put(
				"description", ""
			).put(
				"dxp.lxc.liferay.com.virtualInstanceId", _VIRTUAL_HOSTNAME
			).put(
				"name", "Test " + _VIRTUAL_HOSTNAME
			).put(
				"projectId", "test"
			).put(
				"projectName", "test"
			).put(
				"properties", new String[] {""}
			).put(
				"sourceCodeURL", ""
			).put(
				"type", "customElement"
			).put(
				"typeSettings",
				new String[] {
					"friendlyURLMapping=test", "htmlElementName=test",
					"instanceable=false",
					"portletCategoryName=category.client-extensions",
					"urls=index.js", "useESM=false"
				}
			).put(
				"webContextPath", "/test_" + _VIRTUAL_HOSTNAME
			).build());

		_autoCloseables.add(
			() -> ConfigurationTestUtil.deleteConfiguration(configuration));

		CET cet = _cetManager.getCET(_virtualInstanceCompanyId, "LXC:test");

		Assert.assertEquals("Test " + _VIRTUAL_HOSTNAME, cet.getName());

		String filterString = StringBundler.concat(
			"(&(javax.portlet.name=com_liferay_client_extension_web",
			"_internal_portlet_ClientExtensionEntryPortlet_",
			_virtualInstanceCompanyId, "_LXC_test)",
			"(objectClass=javax.portlet.Portlet))");
		int timeout = 10_000;

		ServiceTracker<Portlet, Portlet> serviceTracker =
			ServiceTrackerFactory.open(bundleContext, filterString, null);

		try {
			Portlet portlet = serviceTracker.waitForService(timeout);

			if (portlet == null) {
				throw new TimeoutException(
					StringBundler.concat(
						"Timeout on waiting for ", filterString, " after ",
						timeout, "ms"));
			}

			ServiceReference<?> serviceReference =
				serviceTracker.getServiceReference();

			String[] value = (String[])serviceReference.getProperty(
				"com.liferay.portlet.header-portal-javascript");

			Assert.assertFalse(value[0].contains("?t="));
		}
		finally {
			serviceTracker.close();
		}
	}

	private static final String _PID =
		"com.liferay.client.extension.type.configuration.CETConfiguration";

	private static final String _VIRTUAL_HOSTNAME =
		RandomTestUtil.randomString() + ".localtest.me";

	private static final Log _log = LogFactoryUtil.getLog(
		CETConfigurationFactoryTest.class);

	private static final List<AutoCloseable> _autoCloseables =
		new ArrayList<>();
	private static long _virtualInstanceCompanyId;

	@Inject
	private CETManager _cetManager;

	@Inject
	private ClientExtensionEntryRelLocalService
		_clientExtensionEntryRelLocalService;

	@Inject
	private GroupLocalService _groupLocalService;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private Portal _portal;

}