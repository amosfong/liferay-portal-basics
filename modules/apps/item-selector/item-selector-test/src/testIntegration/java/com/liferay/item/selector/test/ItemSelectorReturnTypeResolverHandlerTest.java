/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.item.selector.ItemSelectorReturnTypeResolver;
import com.liferay.item.selector.ItemSelectorReturnTypeResolverHandler;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewReturnTypeProvider;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Roberto Díaz
 */
@RunWith(Arquillian.class)
public class ItemSelectorReturnTypeResolverHandlerTest {

	@Before
	public void setUp() throws Exception {
		_bundle = FrameworkUtil.getBundle(
			ItemSelectorReturnTypeResolverHandlerTest.class);

		_bundleContext = _bundle.getBundleContext();

		_serviceReference = _bundleContext.getServiceReference(
			ItemSelectorReturnTypeResolverHandler.class);

		_itemSelectorReturnTypeResolverHandler = _bundleContext.getService(
			_serviceReference);
	}

	@After
	public void tearDown() throws BundleException {
		_bundleContext.ungetService(_serviceReference);
	}

	@Test
	public void testItemSelectorCriterionHandlerReturnsViewsWithProvidedReturnTypes() {
		TestItemSelectorView testItemSelectorView = new TestItemSelectorView();

		ServiceRegistration<ItemSelectorView<?>>
			itemSelectorViewServiceRegistration = registerItemSelectorView(
				testItemSelectorView, "test-view");

		ServiceRegistration<ItemSelectorReturnTypeResolver<?, ?>>
			itemSelectorReturnTypeResolverServiceRegistration =
				registerItemSelectorReturnTypeResolver(
					new TestItemSelectorReturnTypeResolver(), 50);

		ServiceRegistration<ItemSelectorViewReturnTypeProvider>
			itemSelectorViewReturnTypeProviderServiceRegistration =
				registerItemSelectorViewProvider(
					new TestItemSelectorViewReturnTypeProvider(), "test-view");

		List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<>();

		serviceRegistrations.add(itemSelectorViewServiceRegistration);
		serviceRegistrations.add(
			itemSelectorReturnTypeResolverServiceRegistration);
		serviceRegistrations.add(
			itemSelectorViewReturnTypeProviderServiceRegistration);

		try {
			TestItemSelectorCriterion testItemSelectorCriterion =
				new TestItemSelectorCriterion();

			testItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
				new TestItemSelectorReturnType());

			ItemSelectorReturnTypeResolver<?, ?>
				itemSelectorReturnTypeResolver =
					_itemSelectorReturnTypeResolverHandler.
						getItemSelectorReturnTypeResolver(
							testItemSelectorCriterion, testItemSelectorView,
							String.class);

			Assert.assertTrue(
				itemSelectorReturnTypeResolver instanceof
					TestItemSelectorReturnTypeResolver);
		}
		finally {
			_unregister(serviceRegistrations);
		}
	}

	@Test
	public void testItemSelectorReturnTypeResolverIsReplacedByServiceRanking() {
		ServiceRegistration<ItemSelectorReturnTypeResolver<?, ?>>
			itemSelectorReturnTypeResolverServiceRegistration1 =
				registerItemSelectorReturnTypeResolver(
					new TestItemSelectorReturnTypeResolver1(), 100);
		ServiceRegistration<ItemSelectorReturnTypeResolver<?, ?>>
			itemSelectorReturnTypeResolverServiceRegistration2 =
				registerItemSelectorReturnTypeResolver(
					new TestItemSelectorReturnTypeResolver2(), 200);
		ServiceRegistration<ItemSelectorReturnTypeResolver<?, ?>>
			itemSelectorReturnTypeResolverServiceRegistration3 =
				registerItemSelectorReturnTypeResolver(
					new TestItemSelectorReturnTypeResolver3(), 50);

		List<ServiceRegistration<?>> serviceRegistrations =
			new CopyOnWriteArrayList<>();

		serviceRegistrations.add(
			itemSelectorReturnTypeResolverServiceRegistration1);
		serviceRegistrations.add(
			itemSelectorReturnTypeResolverServiceRegistration2);
		serviceRegistrations.add(
			itemSelectorReturnTypeResolverServiceRegistration3);

		try {
			ItemSelectorReturnTypeResolver<?, ?>
				itemSelectorReturnTypeResolver =
					_itemSelectorReturnTypeResolverHandler.
						getItemSelectorReturnTypeResolver(
							TestItemSelectorReturnType.class, String.class);

			Assert.assertTrue(
				itemSelectorReturnTypeResolver instanceof
					TestItemSelectorReturnTypeResolver2);

			serviceRegistrations.remove(
				itemSelectorReturnTypeResolverServiceRegistration2);

			itemSelectorReturnTypeResolverServiceRegistration2.unregister();

			itemSelectorReturnTypeResolver =
				_itemSelectorReturnTypeResolverHandler.
					getItemSelectorReturnTypeResolver(
						TestItemSelectorReturnType.class, String.class);

			Assert.assertTrue(
				itemSelectorReturnTypeResolver instanceof
					TestItemSelectorReturnTypeResolver1);

			serviceRegistrations.remove(
				itemSelectorReturnTypeResolverServiceRegistration1);

			itemSelectorReturnTypeResolverServiceRegistration1.unregister();

			itemSelectorReturnTypeResolver =
				_itemSelectorReturnTypeResolverHandler.
					getItemSelectorReturnTypeResolver(
						TestItemSelectorReturnType.class, String.class);

			Assert.assertTrue(
				itemSelectorReturnTypeResolver instanceof
					TestItemSelectorReturnTypeResolver3);
		}
		finally {
			_unregister(serviceRegistrations);
		}
	}

	@Test
	public void testItemSelectorReturnTypeResolverIsReturnedByServiceRanking() {
		ServiceRegistration<ItemSelectorReturnTypeResolver<?, ?>>
			itemSelectorReturnTypeResolverServiceRegistration1 =
				registerItemSelectorReturnTypeResolver(
					new TestItemSelectorReturnTypeResolver1(), 100);
		ServiceRegistration<ItemSelectorReturnTypeResolver<?, ?>>
			itemSelectorReturnTypeResolverServiceRegistration2 =
				registerItemSelectorReturnTypeResolver(
					new TestItemSelectorReturnTypeResolver2(), 200);
		ServiceRegistration<ItemSelectorReturnTypeResolver<?, ?>>
			itemSelectorReturnTypeResolverServiceRegistration3 =
				registerItemSelectorReturnTypeResolver(
					new TestItemSelectorReturnTypeResolver3(), 50);

		List<ServiceRegistration<?>> serviceRegistrations =
			new CopyOnWriteArrayList<>();

		serviceRegistrations.add(
			itemSelectorReturnTypeResolverServiceRegistration1);
		serviceRegistrations.add(
			itemSelectorReturnTypeResolverServiceRegistration2);
		serviceRegistrations.add(
			itemSelectorReturnTypeResolverServiceRegistration3);

		try {
			ItemSelectorReturnTypeResolver<?, ?>
				itemSelectorReturnTypeResolver =
					_itemSelectorReturnTypeResolverHandler.
						getItemSelectorReturnTypeResolver(
							TestItemSelectorReturnType.class, String.class);

			Assert.assertTrue(
				itemSelectorReturnTypeResolver instanceof
					TestItemSelectorReturnTypeResolver2);
		}
		finally {
			_unregister(serviceRegistrations);
		}
	}

	@Test
	public void testItemSelectorReturnTypeResolverIsReturnedWithDisabledReference()
		throws Exception {

		ConfigurationTestUtil.saveConfiguration(
			_BUNDLE_BLACKLIST_CONFIGURATION_PID,
			HashMapDictionaryBuilder.<String, Object>put(
				"blacklist-bundle-symbolic-names",
				"com.liferay.document.library.video"
			).build());

		Assert.assertNotNull(
			_itemSelectorReturnTypeResolverHandler.
				getItemSelectorReturnTypeResolver(
					"com.liferay.item.selector.criteria." +
						"FileEntryItemSelectorReturnType",
					FileEntry.class.getName()));

		ConfigurationTestUtil.deleteConfiguration(
			_BUNDLE_BLACKLIST_CONFIGURATION_PID);
	}

	protected ServiceRegistration<ItemSelectorReturnTypeResolver<?, ?>>
		registerItemSelectorReturnTypeResolver(
			ItemSelectorReturnTypeResolver<?, ?> itemSelectorReturnTypeResolver,
			int serviceRanking) {

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("service.ranking", serviceRanking);

		return _bundleContext.registerService(
			(Class<ItemSelectorReturnTypeResolver<?, ?>>)
				(Class<?>)ItemSelectorReturnTypeResolver.class,
			itemSelectorReturnTypeResolver, properties);
	}

	protected ServiceRegistration<ItemSelectorView<?>> registerItemSelectorView(
		ItemSelectorView<?> itemSelectorView, String itemSelectorViewKey) {

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("item.selector.view.key", itemSelectorViewKey);

		return _bundleContext.registerService(
			(Class<ItemSelectorView<?>>)(Class<?>)ItemSelectorView.class,
			itemSelectorView, properties);
	}

	protected ServiceRegistration<ItemSelectorViewReturnTypeProvider>
		registerItemSelectorViewProvider(
			ItemSelectorViewReturnTypeProvider
				itemSelectorViewReturnTypeProvider,
			String itemSelectorViewKey) {

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("item.selector.view.key", itemSelectorViewKey);

		return _bundleContext.registerService(
			ItemSelectorViewReturnTypeProvider.class,
			itemSelectorViewReturnTypeProvider, properties);
	}

	private void _unregister(
		List<ServiceRegistration<?>> serviceRegistrations) {

		serviceRegistrations.forEach(ServiceRegistration::unregister);
	}

	private static final String _BUNDLE_BLACKLIST_CONFIGURATION_PID =
		"com.liferay.portal.bundle.blacklist.internal.configuration." +
			"BundleBlacklistConfiguration";

	private Bundle _bundle;
	private BundleContext _bundleContext;
	private ItemSelectorReturnTypeResolverHandler
		_itemSelectorReturnTypeResolverHandler;
	private ServiceReference<ItemSelectorReturnTypeResolverHandler>
		_serviceReference;

	private abstract class BaseTestItemSelectorReturnTypeResolver
		implements ItemSelectorReturnTypeResolver
			<TestItemSelectorReturnType, String> {

		public Class<TestItemSelectorReturnType>
			getItemSelectorReturnTypeClass() {

			return TestItemSelectorReturnType.class;
		}

		public Class<String> getModelClass() {
			return String.class;
		}

	}

	private class TestItemSelectorReturnTypeResolver1
		extends BaseTestItemSelectorReturnTypeResolver {

		public String getValue(String s, ThemeDisplay themeDisplay)
			throws Exception {

			return "Value 1";
		}

	}

	private class TestItemSelectorReturnTypeResolver2
		extends BaseTestItemSelectorReturnTypeResolver {

		public String getValue(String s, ThemeDisplay themeDisplay)
			throws Exception {

			return "Value 2";
		}

	}

	private class TestItemSelectorReturnTypeResolver3
		extends BaseTestItemSelectorReturnTypeResolver {

		public String getValue(String s, ThemeDisplay themeDisplay)
			throws Exception {

			return "Value 3";
		}

	}

}