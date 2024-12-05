/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.internal.importer.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.importer.LayoutsImportStrategy;
import com.liferay.layout.importer.LayoutsImporter;
import com.liferay.layout.importer.LayoutsImporterResultEntry;
import com.liferay.layout.page.template.constants.LayoutPageTemplateConstants;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.util.structure.CollectionStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactory;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.File;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Rubén Pulido
 */
@RunWith(Arquillian.class)
public class DisplayPagesImporterTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testImportDisplayPage() throws Exception {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_importLayoutPageTemplateEntry("display-page-template-one");

		String className =
			"com.liferay.portal.kernel.repository.model.FileEntry";

		Assert.assertEquals(className, layoutPageTemplateEntry.getClassName());

		Assert.assertEquals(
			"Display Page Template One", layoutPageTemplateEntry.getName());
		Assert.assertEquals(0, layoutPageTemplateEntry.getClassTypeId());

		_validateLayoutPageTemplateStructure(
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					_group.getGroupId(), layoutPageTemplateEntry.getPlid()));
	}

	@Test
	public void testImportDisplayPageCollections() throws Exception {
		_getLayoutsImporterResultEntries(
			"display-page-templates-with-collections");

		Assert.assertEquals(
			4,
			_layoutPageTemplateEntryService.
				getLayoutPageCollectionsAndLayoutPageTemplateEntriesCount(
					_group.getGroupId(),
					LayoutPageTemplateConstants.
						PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT,
					LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));
		Assert.assertNotNull(
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(),
				LayoutPageTemplateConstants.
					PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT,
				"Basic Web Content Display Page Template Entry",
				LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));
		Assert.assertNotNull(
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(),
				LayoutPageTemplateConstants.
					PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT,
				"Product Display Page Template Entry",
				LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));

		_assertLayoutPageTemplateCollections(
			_layoutPageTemplateCollectionLocalService.
				fetchLayoutPageTemplateCollection(
					_group.getGroupId(),
					"EVP.com Display Page Template Collection",
					LayoutPageTemplateConstants.
						PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT,
					LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));
		_assertLayoutPageTemplateCollections(
			_layoutPageTemplateCollectionLocalService.
				fetchLayoutPageTemplateCollection(
					_group.getGroupId(),
					"Liferay.com Display Page Template Collection",
					LayoutPageTemplateConstants.
						PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT,
					LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));
	}

	@Test
	public void testImportDisplayPageExistingNameNoOvewrite() throws Exception {
		String testCaseName = "display-page-template-one";

		_importLayoutPageTemplateEntry(testCaseName);

		List<LayoutsImporterResultEntry> layoutsImporterResultEntries =
			_getLayoutsImporterResultEntries(testCaseName);

		LayoutsImporterResultEntry layoutsImporterResultEntry =
			layoutsImporterResultEntries.get(0);

		Assert.assertEquals(
			LayoutsImporterResultEntry.Status.IGNORED,
			layoutsImporterResultEntry.getStatus());
		Assert.assertEquals(
			"display-page-templates/display-page-template-one" +
				"/display-page-template.json was ignored because a display " +
					"page template with the same key already exists.",
			layoutsImporterResultEntry.getErrorMessage(
				LocaleUtil.getSiteDefault()));
	}

	@Test
	public void testImportDisplayPages() throws Exception {
		List<LayoutsImporterResultEntry> layoutsImporterResultEntries =
			_getLayoutsImporterResultEntries("display-page-template-multiple");

		Assert.assertEquals(
			layoutsImporterResultEntries.toString(), 2,
			layoutsImporterResultEntries.size());

		LayoutPageTemplateEntry layoutPageTemplateEntry1 =
			_getLayoutPageTemplateEntry(layoutsImporterResultEntries, 0);
		LayoutPageTemplateEntry layoutPageTemplateEntry2 =
			_getLayoutPageTemplateEntry(layoutsImporterResultEntries, 1);

		List<String> actualLayoutPageTemplateEntryNames = ListUtil.sort(
			new ArrayList() {
				{
					add(layoutPageTemplateEntry1.getName());
					add(layoutPageTemplateEntry2.getName());
				}
			});

		Assert.assertArrayEquals(
			new String[] {
				"Display Page Template One", "Display Page Template Two"
			},
			actualLayoutPageTemplateEntryNames.toArray(new String[0]));
	}

	@Test
	public void testImportDisplayPageWithCollectionDisplay() throws Exception {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_importLayoutPageTemplateEntry(
				"display-page-template-with-collection-display");

		Assert.assertEquals(
			"com.liferay.portal.kernel.repository.model.FileEntry",
			layoutPageTemplateEntry.getClassName());
		Assert.assertEquals(
			"Display Page Template Collection",
			layoutPageTemplateEntry.getName());

		Assert.assertEquals(0, layoutPageTemplateEntry.getClassTypeId());

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					_group.getGroupId(), layoutPageTemplateEntry.getPlid());

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructure.getDefaultSegmentsExperienceData());

		LayoutStructureItem mainLayoutStructureItem =
			layoutStructure.getMainLayoutStructureItem();

		List<String> childrenItemIds =
			mainLayoutStructureItem.getChildrenItemIds();

		Assert.assertEquals(
			childrenItemIds.toString(), 1, childrenItemIds.size());

		String childItemId = childrenItemIds.get(0);

		LayoutStructureItem layoutStructureItem =
			layoutStructure.getLayoutStructureItem(childItemId);

		Assert.assertTrue(
			layoutStructureItem instanceof CollectionStyledLayoutStructureItem);

		CollectionStyledLayoutStructureItem
			collectionStyledLayoutStructureItem =
				(CollectionStyledLayoutStructureItem)layoutStructureItem;

		JSONObject collectionJSONObject =
			collectionStyledLayoutStructureItem.getCollectionJSONObject();

		Assert.assertEquals(
			"com.liferay.asset.kernel.model.AssetCategory",
			collectionJSONObject.getString("itemType"));
		Assert.assertEquals(
			"com.liferay.asset.categories.admin.web.internal.info.collection." +
				"provider.AssetCategoriesForAssetEntryRelatedInfoItem" +
					"CollectionProvider",
			collectionJSONObject.getString("key"));
	}

	private void _assertLayoutPageTemplateCollections(
		LayoutPageTemplateCollection layoutPageTemplateCollection) {

		Assert.assertEquals(
			3,
			_layoutPageTemplateEntryService.
				getLayoutPageCollectionsAndLayoutPageTemplateEntriesCount(
					_group.getGroupId(),
					layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId(),
					LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));
		Assert.assertNotNull(
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(),
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Product Display Page Template Entry",
				LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));

		LayoutPageTemplateCollection blogsDisplaylayoutPageTemplateCollection =
			_layoutPageTemplateCollectionLocalService.
				fetchLayoutPageTemplateCollection(
					_group.getGroupId(),
					"Blogs Display Page Template Collection",
					layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId(),
					LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE);

		Assert.assertNotNull(
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(),
				blogsDisplaylayoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Blogs Display Page Template Entry",
				LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));

		LayoutPageTemplateCollection
			webContentDisplaylayoutPageTemplateCollection =
				_layoutPageTemplateCollectionLocalService.
					fetchLayoutPageTemplateCollection(
						_group.getGroupId(),
						"Web Content Display Page Template Collection",
						layoutPageTemplateCollection.
							getLayoutPageTemplateCollectionId(),
						LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE);

		Assert.assertEquals(
			1,
			_layoutPageTemplateEntryService.
				getLayoutPageCollectionsAndLayoutPageTemplateEntriesCount(
					_group.getGroupId(),
					webContentDisplaylayoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId(),
					LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));

		LayoutPageTemplateCollection
			basicWebContentDisplaylayoutPageTemplateCollection =
				_layoutPageTemplateCollectionLocalService.
					fetchLayoutPageTemplateCollection(
						_group.getGroupId(),
						"Basic Web Content Display Page Template Collection",
						webContentDisplaylayoutPageTemplateCollection.
							getLayoutPageTemplateCollectionId(),
						LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE);

		Assert.assertEquals(
			1,
			_layoutPageTemplateEntryService.
				getLayoutPageCollectionsAndLayoutPageTemplateEntriesCount(
					_group.getGroupId(),
					basicWebContentDisplaylayoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId(),
					LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));
		Assert.assertNotNull(
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(),
				basicWebContentDisplaylayoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Basic Web Content Display Page Template Entry",
				LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE));
	}

	private File _getFile(String resourcePath) throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		Enumeration<URL> enumeration = bundle.findEntries(
			resourcePath, "*", true);

		ZipWriter zipWriter = _zipWriterFactory.getZipWriter();

		while (enumeration.hasMoreElements()) {
			URL url = enumeration.nextElement();

			String path = url.getPath();

			if (!path.endsWith(StringPool.SLASH)) {
				try (InputStream inputStream = url.openStream()) {
					zipWriter.addEntry(
						StringUtil.removeSubstring(url.getPath(), resourcePath),
						inputStream);
				}
			}
		}

		return zipWriter.getFile();
	}

	private LayoutPageTemplateEntry _getLayoutPageTemplateEntry(
		List<LayoutsImporterResultEntry> layoutsImporterResultEntries,
		int index) {

		LayoutsImporterResultEntry layoutsImporterResultEntry =
			layoutsImporterResultEntries.get(index);

		Assert.assertEquals(
			LayoutsImporterResultEntry.Status.IMPORTED,
			layoutsImporterResultEntry.getStatus());

		String layoutPageTemplateEntryKey = StringUtil.toLowerCase(
			layoutsImporterResultEntry.getName());

		layoutPageTemplateEntryKey = StringUtil.replace(
			layoutPageTemplateEntryKey, CharPool.SPACE, CharPool.DASH);

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				_group.getGroupId(), layoutPageTemplateEntryKey);

		Assert.assertNotNull(layoutPageTemplateEntry);

		return layoutPageTemplateEntry;
	}

	private List<LayoutsImporterResultEntry> _getLayoutsImporterResultEntries(
			String testCaseName)
		throws Exception {

		List<LayoutsImporterResultEntry> layoutsImporterResultEntries = null;

		File file = _getFile(_BASE_PATH + testCaseName);

		ServiceContextThreadLocal.pushServiceContext(
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		try {
			layoutsImporterResultEntries = _layoutsImporter.importFile(
				TestPropsValues.getUserId(), _group.getGroupId(), 0, file,
				LayoutsImportStrategy.DO_NOT_OVERWRITE, true);
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}

		Assert.assertNotNull(layoutsImporterResultEntries);

		return layoutsImporterResultEntries;
	}

	private LayoutPageTemplateEntry _importLayoutPageTemplateEntry(
			String testCaseName)
		throws Exception {

		List<LayoutsImporterResultEntry> layoutsImporterResultEntries =
			_getLayoutsImporterResultEntries(testCaseName);

		Assert.assertEquals(
			layoutsImporterResultEntries.toString(), 1,
			layoutsImporterResultEntries.size());

		return _getLayoutPageTemplateEntry(layoutsImporterResultEntries, 0);
	}

	private void _validateLayoutPageTemplateStructure(
		LayoutPageTemplateStructure layoutPageTemplateStructure) {

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructure.getDefaultSegmentsExperienceData());

		Assert.assertNotNull(layoutStructure.getMainLayoutStructureItem());
	}

	private static final String _BASE_PATH =
		"com/liferay/layout/page/template/internal/importer/test/dependencies/";

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutPageTemplateCollectionLocalService
		_layoutPageTemplateCollectionLocalService;

	@Inject
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Inject
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

	@Inject
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Inject
	private LayoutsImporter _layoutsImporter;

	@Inject
	private ZipWriterFactory _zipWriterFactory;

}