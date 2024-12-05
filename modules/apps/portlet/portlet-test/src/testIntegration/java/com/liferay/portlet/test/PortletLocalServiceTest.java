/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.expando.kernel.model.BaseCustomAttributesDisplay;
import com.liferay.expando.kernel.model.CustomAttributesDisplay;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.portlet.Portlet;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Mikel Lorza
 */
@RunWith(Arquillian.class)
public class PortletLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testGetCustomAttributesDisplaysWithCustomAttributesDisplayDisabled() {
		List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<>();

		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		TestCustomAttributesDisplay disabledFFCustomAttributesDisplay =
			new TestCustomAttributesDisplay(RandomTestUtil.randomString());

		String portletName = RandomTestUtil.randomString();

		serviceRegistrations.add(
			bundleContext.registerService(
				CustomAttributesDisplay.class,
				disabledFFCustomAttributesDisplay,
				MapUtil.singletonDictionary(
					"javax.portlet.name", portletName)));

		String enabledFFKey = RandomTestUtil.randomString();

		PropsTestUtil.setProps(
			"feature.flag." + enabledFFKey, Boolean.TRUE.toString());

		TestCustomAttributesDisplay enabledFFCustomAttributesDisplay =
			new TestCustomAttributesDisplay(enabledFFKey);

		serviceRegistrations.add(
			bundleContext.registerService(
				CustomAttributesDisplay.class, enabledFFCustomAttributesDisplay,
				MapUtil.singletonDictionary(
					"javax.portlet.name", portletName)));

		TestCustomAttributesDisplay nullFFCustomAttributesDisplay =
			new TestCustomAttributesDisplay(null);

		serviceRegistrations.add(
			bundleContext.registerService(
				CustomAttributesDisplay.class, nullFFCustomAttributesDisplay,
				MapUtil.singletonDictionary(
					"javax.portlet.name", portletName)));

		serviceRegistrations.add(
			bundleContext.registerService(
				Portlet.class, new TestPortlet(),
				MapUtil.singletonDictionary(
					"javax.portlet.name", portletName)));

		List<CustomAttributesDisplay> customAttributesDisplays =
			TransformUtil.transform(
				_portletLocalService.getCustomAttributesDisplays(),
				customAttributesDisplay -> {
					if (Objects.equals(
							TestCustomAttributesDisplay.class.getName(),
							customAttributesDisplay.getClassName())) {

						return customAttributesDisplay;
					}

					return null;
				});

		Assert.assertFalse(
			customAttributesDisplays.contains(
				disabledFFCustomAttributesDisplay));
		Assert.assertTrue(
			customAttributesDisplays.contains(
				enabledFFCustomAttributesDisplay));
		Assert.assertTrue(
			customAttributesDisplays.contains(nullFFCustomAttributesDisplay));
		Assert.assertEquals(
			customAttributesDisplays.toString(), 2,
			customAttributesDisplays.size());

		PropsUtil.setProps(_props);

		for (ServiceRegistration<?> serviceRegistration :
				serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Inject
	private PortletLocalService _portletLocalService;

	@Inject
	private Props _props;

	private class TestCustomAttributesDisplay
		extends BaseCustomAttributesDisplay {

		@Override
		public String getClassName() {
			return TestCustomAttributesDisplay.class.getName();
		}

		@Override
		public String getFeatureFlagKey() {
			return _featureFlagKey;
		}

		private TestCustomAttributesDisplay(String featureFlagKey) {
			_featureFlagKey = featureFlagKey;
		}

		private final String _featureFlagKey;

	}

	private class TestPortlet extends MVCPortlet {
	}

}