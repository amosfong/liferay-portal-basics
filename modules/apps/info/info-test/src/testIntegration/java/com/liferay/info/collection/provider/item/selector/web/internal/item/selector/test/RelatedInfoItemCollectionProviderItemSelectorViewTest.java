/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.collection.provider.item.selector.web.internal.item.selector.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.RelatedInfoItemCollectionProvider;
import com.liferay.info.collection.provider.item.selector.criterion.RelatedInfoItemCollectionProviderItemSelectorCriterion;
import com.liferay.info.list.provider.item.selector.criterion.InfoListProviderItemSelectorReturnType;
import com.liferay.info.pagination.InfoPage;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
 * @author Jürgen Kappler
 */
@RunWith(Arquillian.class)
public class RelatedInfoItemCollectionProviderItemSelectorViewTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Test
	public void testGetAvailableRelatedInfoItemCollectionProviders() {
		RelatedInfoItemCollectionProvider testInfoItemCollectionProvider =
			new TestItemRelatedInfoItemCollectionProvider(true);

		ServiceRegistration<RelatedInfoItemCollectionProvider>
			serviceRegistration = _getServiceRegistration(
				testInfoItemCollectionProvider);

		try {
			List<RelatedInfoItemCollectionProvider<?, ?>>
				relatedInfoItemCollectionProviders = ReflectionTestUtil.invoke(
					_itemSelectorView, "_getRelatedInfoItemCollectionProviders",
					new Class<?>[] {
						RelatedInfoItemCollectionProviderItemSelectorCriterion.
							class
					},
					_getRelatedInfoItemCollectionProviderItemSelectorCriterion(
						Collections.singletonList(TestItem.class.getName())));

			Assert.assertEquals(
				relatedInfoItemCollectionProviders.toString(), 1,
				relatedInfoItemCollectionProviders.size());
			Assert.assertTrue(
				relatedInfoItemCollectionProviders.contains(
					testInfoItemCollectionProvider));
		}
		finally {
			serviceRegistration.unregister();
		}
	}

	@Test
	public void testGetAvailableRelatedInfoItemCollectionProvidersForAssetCategory() {
		List<RelatedInfoItemCollectionProvider<?, ?>>
			relatedInfoItemCollectionProviders = ReflectionTestUtil.invoke(
				_itemSelectorView, "_getRelatedInfoItemCollectionProviders",
				new Class<?>[] {
					RelatedInfoItemCollectionProviderItemSelectorCriterion.class
				},
				_getRelatedInfoItemCollectionProviderItemSelectorCriterion(
					Collections.singletonList(AssetCategory.class.getName())));

		Assert.assertTrue(
			ListUtil.exists(
				relatedInfoItemCollectionProviders,
				relatedInfoItemCollectionProvider -> Objects.equals(
					relatedInfoItemCollectionProvider.getLabel(
						LocaleUtil.getSiteDefault()),
					LanguageUtil.get(
						LocaleUtil.getSiteDefault(),
						"items-with-this-category"))));
		Assert.assertTrue(
			ListUtil.exists(
				relatedInfoItemCollectionProviders,
				relatedInfoItemCollectionProvider -> Objects.equals(
					relatedInfoItemCollectionProvider.getLabel(
						LocaleUtil.getSiteDefault()),
					LanguageUtil.get(
						LocaleUtil.getSiteDefault(),
						"blogs-with-this-category"))));
	}

	@Test
	public void testGetAvailableRelatedInfoItemCollectionProvidersForAssetEntry() {
		List<RelatedInfoItemCollectionProvider<?, ?>>
			relatedInfoItemCollectionProviders = ReflectionTestUtil.invoke(
				_itemSelectorView, "_getRelatedInfoItemCollectionProviders",
				new Class<?>[] {
					RelatedInfoItemCollectionProviderItemSelectorCriterion.class
				},
				_getRelatedInfoItemCollectionProviderItemSelectorCriterion(
					Collections.singletonList(AssetEntry.class.getName())));

		Assert.assertTrue(
			ListUtil.exists(
				relatedInfoItemCollectionProviders,
				relatedInfoItemCollectionProvider -> Objects.equals(
					relatedInfoItemCollectionProvider.getLabel(
						LocaleUtil.getSiteDefault()),
					LanguageUtil.get(
						LocaleUtil.getSiteDefault(),
						"categories-for-this-item"))));
		Assert.assertTrue(
			ListUtil.exists(
				relatedInfoItemCollectionProviders,
				relatedInfoItemCollectionProvider -> Objects.equals(
					relatedInfoItemCollectionProvider.getLabel(
						LocaleUtil.getSiteDefault()),
					LanguageUtil.get(
						LocaleUtil.getSiteDefault(),
						"items-with-same-categories"))));
		Assert.assertTrue(
			ListUtil.exists(
				relatedInfoItemCollectionProviders,
				relatedInfoItemCollectionProvider -> Objects.equals(
					relatedInfoItemCollectionProvider.getLabel(
						LocaleUtil.getSiteDefault()),
					LanguageUtil.get(
						LocaleUtil.getSiteDefault(), "related-assets"))));
	}

	@Test
	public void testGetNotAvailableRelatedInfoItemCollectionProviders() {
		RelatedInfoItemCollectionProvider testInfoItemCollectionProvider =
			new TestItemRelatedInfoItemCollectionProvider(false);

		ServiceRegistration<RelatedInfoItemCollectionProvider>
			serviceRegistration = _getServiceRegistration(
				testInfoItemCollectionProvider);

		try {
			List<RelatedInfoItemCollectionProvider<?, ?>>
				relatedInfoItemCollectionProviders = ReflectionTestUtil.invoke(
					_itemSelectorView, "_getRelatedInfoItemCollectionProviders",
					new Class<?>[] {
						RelatedInfoItemCollectionProviderItemSelectorCriterion.
							class
					},
					_getRelatedInfoItemCollectionProviderItemSelectorCriterion(
						Collections.singletonList(TestItem.class.getName())));

			Assert.assertEquals(
				relatedInfoItemCollectionProviders.toString(), 0,
				relatedInfoItemCollectionProviders.size());
		}
		finally {
			serviceRegistration.unregister();
		}
	}

	private RelatedInfoItemCollectionProviderItemSelectorCriterion
		_getRelatedInfoItemCollectionProviderItemSelectorCriterion(
			List<String> sourceItemTypes) {

		RelatedInfoItemCollectionProviderItemSelectorCriterion
			relatedInfoItemCollectionProviderItemSelectorCriterion =
				new RelatedInfoItemCollectionProviderItemSelectorCriterion();

		relatedInfoItemCollectionProviderItemSelectorCriterion.
			setDesiredItemSelectorReturnTypes(
				new InfoListProviderItemSelectorReturnType());
		relatedInfoItemCollectionProviderItemSelectorCriterion.
			setSourceItemTypes(sourceItemTypes);

		return relatedInfoItemCollectionProviderItemSelectorCriterion;
	}

	private ServiceRegistration<RelatedInfoItemCollectionProvider>
		_getServiceRegistration(
			RelatedInfoItemCollectionProvider
				relatedInfoItemCollectionProvider) {

		Bundle bundle = FrameworkUtil.getBundle(
			RelatedInfoItemCollectionProviderItemSelectorViewTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		return bundleContext.registerService(
			RelatedInfoItemCollectionProvider.class,
			relatedInfoItemCollectionProvider, null);
	}

	@Inject(
		filter = "component.name=com.liferay.info.collection.provider.item.selector.web.internal.item.selector.RelatedInfoItemCollectionProviderItemSelectorView"
	)
	private ItemSelectorView
		<RelatedInfoItemCollectionProviderItemSelectorCriterion>
			_itemSelectorView;

	private static class TestItem {
	}

	private static class TestItemRelatedInfoItemCollectionProvider
		implements RelatedInfoItemCollectionProvider<TestItem, TestItem> {

		public TestItemRelatedInfoItemCollectionProvider(boolean available) {
			_available = available;
		}

		@Override
		public InfoPage<TestItem> getCollectionInfoPage(
			CollectionQuery collectionQuery) {

			return null;
		}

		@Override
		public String getLabel(Locale locale) {
			return null;
		}

		@Override
		public boolean isAvailable() {
			return _available;
		}

		private final boolean _available;

	}

}