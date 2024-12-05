/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.taglib.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.list.constants.AssetListEntryTypeConstants;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRel;
import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.asset.list.service.AssetListEntrySegmentsEntryRelLocalService;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.dynamic.data.mapping.constants.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestHelper;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.contributor.FragmentCollectionContributorRegistry;
import com.liferay.fragment.entry.processor.constants.FragmentEntryProcessorConstants;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentCollectionLocalService;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.info.collection.provider.RepeatableFieldInfoItemCollectionProvider;
import com.liferay.info.constants.InfoDisplayWebKeys;
import com.liferay.info.exception.InfoFormValidationException;
import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldSet;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.item.capability.InfoItemCapability;
import com.liferay.info.list.provider.item.selector.criterion.InfoListProviderItemSelectorReturnType;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.info.test.util.MockInfoServiceRegistrationHolder;
import com.liferay.info.test.util.model.MockObject;
import com.liferay.item.selector.criteria.InfoListItemSelectorReturnType;
import com.liferay.journal.constants.JournalArticleConstants;
import com.liferay.journal.constants.JournalFolderConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProvider;
import com.liferay.layout.display.page.constants.LayoutDisplayPageWebKeys;
import com.liferay.layout.page.template.info.item.capability.DisplayPageInfoItemCapability;
import com.liferay.layout.page.template.info.item.capability.EditPageInfoItemCapability;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalServiceUtil;
import com.liferay.layout.provider.LayoutStructureProvider;
import com.liferay.layout.taglib.servlet.taglib.RenderLayoutStructureTag;
import com.liferay.layout.test.util.ContentLayoutTestUtil;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.layout.util.constants.LayoutDataItemTypeConstants;
import com.liferay.layout.util.structure.ContainerStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.exception.InfoFormException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.LayoutTypePortletConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.TestInfo;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionRequest;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.util.PortalImpl;
import com.liferay.portal.util.PropsValues;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.criteria.CriteriaSerializer;
import com.liferay.segments.criteria.contributor.SegmentsCriteriaContributor;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceLocalService;
import com.liferay.segments.test.util.SegmentsTestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;

/**
 * @author Eudaldo Alonso
 */
@RunWith(Arquillian.class)
public class RenderLayoutStructureTagTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			TestPropsValues.getGroupId(), TestPropsValues.getUserId());

		ServiceContextThreadLocal.pushServiceContext(_serviceContext);
	}

	@After
	public void tearDown() {
		ServiceContextThreadLocal.popServiceContext();
	}

	@Test
	public void testEnsureFileURLWhenChangingGroupFriendlyURL()
		throws Exception {

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		FileEntry fileEntry = _addFileEntry();

		String url = _dlURLHelper.getPreviewURL(
			fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
			false, false);

		Assert.assertTrue(
			StringUtil.contains(
				url, _group.getFriendlyURL(), StringPool.BLANK));

		long segmentExperienceId =
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid());

		ContentLayoutTestUtil.addItemToLayout(
			JSONUtil.put(
				"styles",
				JSONUtil.put(
					"backgroundImage",
					JSONUtil.put(
						"classNameId", _portal.getClassNameId(FileEntry.class)
					).put(
						"classPK", fileEntry.getFileEntryId()
					).put(
						"fileEntryId", fileEntry.getFileEntryId()
					).put(
						"url", url
					))
			).toString(),
			LayoutDataItemTypeConstants.TYPE_CONTAINER,
			layout.fetchDraftLayout(), _layoutStructureProvider,
			segmentExperienceId);

		ContentLayoutTestUtil.publishLayout(layout.fetchDraftLayout(), layout);

		String content = _getRenderLayoutHTML(layout);

		Assert.assertTrue(
			content, StringUtil.contains(content, url, StringPool.BLANK));

		_groupLocalService.updateFriendlyURL(
			_group.getGroupId(), "/new-friendly-url");

		url = _dlURLHelper.getPreviewURL(
			fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
			false, false);

		Assert.assertTrue(
			StringUtil.contains(url, "/new-friendly-url", StringPool.BLANK));

		content = _getRenderLayoutHTML(layout);

		Assert.assertTrue(
			content, StringUtil.contains(content, url, StringPool.BLANK));
	}

	@Test
	public void testRemovedLayoutTemplateId() throws Exception {
		Layout layout = _layoutLocalService.addLayout(
			null, TestPropsValues.getUserId(), _group.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false,
			StringPool.BLANK, _serviceContext);

		UnicodeProperties typeSettingsUnicodeProperties =
			layout.getTypeSettingsProperties();

		typeSettingsUnicodeProperties.setProperty(
			LayoutTypePortletConstants.LAYOUT_TEMPLATE_ID,
			"removed-template-id");

		layout = LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			typeSettingsUnicodeProperties.toString());

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		Assert.assertEquals(
			"removed-template-id", layoutTypePortlet.getLayoutTemplateId());

		RenderLayoutStructureTag renderLayoutStructureTag =
			new RenderLayoutStructureTag();

		renderLayoutStructureTag.setLayoutStructure(
			_getDefaultMasterLayoutStructure());

		renderLayoutStructureTag.doTag(
			_getMockHttpServletRequest(layout), new MockHttpServletResponse());

		Assert.assertEquals(
			PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID,
			layoutTypePortlet.getLayoutTemplateId());

		layout = _layoutLocalService.fetchLayout(layout.getPlid());

		layoutTypePortlet = (LayoutTypePortlet)layout.getLayoutType();

		Assert.assertEquals(
			"removed-template-id", layoutTypePortlet.getLayoutTemplateId());
	}

	@Test
	public void testRenderCollectionStyledLayoutStructureItem()
		throws Exception {

		AssetListEntry assetListEntry =
			_assetListEntryLocalService.addAssetListEntry(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				AssetListEntryTypeConstants.TYPE_MANUAL, _serviceContext);

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		long segmentsExperienceId =
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid());

		_addCollectionStyledLayoutStructureItem(
			assetListEntry, layout, _COUNT_INFO_LIST_ITEMS, "none",
			segmentsExperienceId,
			_addFragmentEntryLinks(
				_COUNT_FRAGMENT_ENTRY_LINKS,
				JSONUtil.put("collectionFieldId", "JournalArticle_title"),
				layout.fetchDraftLayout(), segmentsExperienceId));

		List<AssetEntry> assetEntries = _addAssetEntries(assetListEntry);

		MockHttpServletRequest mockHttpServletRequest =
			_getMockHttpServletRequest(layout);
		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		RenderLayoutStructureTag renderLayoutStructureTag =
			_getRenderLayoutStructureTag(
				layout, mockHttpServletRequest, mockHttpServletResponse,
				segmentsExperienceId);

		_entityCache.clearCache();
		_multiVMPool.clear();

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			renderLayoutStructureTag.doTag(
				mockHttpServletRequest, mockHttpServletResponse);
		}

		String content = mockHttpServletResponse.getContentAsString();

		for (AssetEntry assetEntry : assetEntries) {
			int count = StringUtil.count(
				content,
				assetEntry.getTitle(assetEntry.getDefaultLanguageId()));

			Assert.assertTrue(
				String.valueOf(count), count >= _COUNT_FRAGMENT_ENTRY_LINKS);
		}
	}

	@Test
	public void testRenderCollectionStyledLayoutStructureItemForRepeatableFields()
		throws Exception {

		DDMStructureTestHelper ddmStructureTestHelper =
			new DDMStructureTestHelper(
				_portal.getClassNameId(JournalArticle.class), _group);

		DDMStructure ddmStructure = ddmStructureTestHelper.addStructure(
			_portal.getClassNameId(JournalArticle.class),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			_deserialize(_read("structure_with_repeatable_fieldset.json")),
			StorageType.DEFAULT.getValue(), DDMStructureConstants.TYPE_DEFAULT);

		JournalArticle journalArticle =
			JournalTestUtil.addArticleWithXMLContent(
				_group.getGroupId(),
				JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				PortalUtil.getClassNameId(DDMStructure.class),
				ddmStructure.getStructureId(),
				_read("repeatable_fieldset_content.xml"),
				ddmStructure.getStructureKey(), null,
				LocaleUtil.getSiteDefault());

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.addLayoutPageTemplateEntry(
				null, _group.getGroupId(), 0,
				_portal.getClassNameId(JournalArticle.class.getName()),
				ddmStructure.getStructureId(), RandomTestUtil.randomString(), 0,
				WorkflowConstants.STATUS_DRAFT, _serviceContext);

		Layout layout = _layoutLocalService.getLayout(
			layoutPageTemplateEntry.getPlid());

		MockHttpServletRequest mockHttpServletRequest =
			_getMockHttpServletRequest(
				layout,
				_journalArticleLayoutDisplayPageProvider.
					getLayoutDisplayPageObjectProvider(journalArticle));

		mockHttpServletRequest.setAttribute(
			InfoDisplayWebKeys.INFO_ITEM, journalArticle);

		long segmentsExperienceId =
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid());

		_addCollectionStyledLayoutStructureItem(
			JSONUtil.put(
				"fieldName", "Fieldset"
			).put(
				"itemSubType", String.valueOf(ddmStructure.getStructureId())
			).put(
				"itemType", JournalArticle.class.getName()
			).put(
				"key", RepeatableFieldInfoItemCollectionProvider.class.getName()
			).put(
				"type", InfoListProviderItemSelectorReturnType.class.getName()
			),
			JSONUtil.put(
				"displayAllPages", true
			).put(
				"numberOfItems", 3
			).put(
				"numberOfItemsPerPage", 3
			).put(
				"paginationType", "none"
			).put(
				"showAllItems", true
			),
			layout, null, segmentsExperienceId,
			_addFragmentEntryLinks(
				1, JSONUtil.put("collectionFieldId", "DDMStructure_Text1"),
				layout.fetchDraftLayout(), segmentsExperienceId));

		MockHttpServletResponse mockHttpServletResponse = _renderLayout(
			layout, mockHttpServletRequest);

		String content = mockHttpServletResponse.getContentAsString();

		Assert.assertTrue(content.contains("text1one"));
		Assert.assertTrue(content.contains("text1two"));
		Assert.assertTrue(content.contains("text1three"));
	}

	@Test
	public void testRenderCollectionStyledLayoutStructureItemSelectingSegmentsExperienceWithDifferentSegmentsEntry()
		throws Exception {

		AssetListEntry assetListEntry =
			_assetListEntryLocalService.addAssetListEntry(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				AssetListEntryTypeConstants.TYPE_MANUAL, _serviceContext);

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName());

		JournalArticle expectedJournalArticle1 = _addJournalArticle(
			ddmStructure);

		AssetEntry assetEntry1 = _assetEntryLocalService.fetchEntry(
			JournalArticle.class.getName(),
			expectedJournalArticle1.getResourcePrimKey());

		_assetListEntryLocalService.addAssetEntrySelections(
			assetListEntry.getAssetListEntryId(),
			new long[] {assetEntry1.getEntryId()},
			SegmentsEntryConstants.ID_DEFAULT, _serviceContext);

		JournalArticle expectedJournalArticle2 = _addJournalArticle(
			ddmStructure);

		AssetEntry assetEntry2 = _assetEntryLocalService.fetchEntry(
			JournalArticle.class.getName(),
			expectedJournalArticle2.getResourcePrimKey());

		SegmentsEntry segmentsEntry1 = _addSegmentsEntryByFirstName("Test");

		_assetListEntryLocalService.addAssetEntrySelections(
			assetListEntry.getAssetListEntryId(),
			new long[] {assetEntry2.getEntryId()},
			segmentsEntry1.getSegmentsEntryId(), _serviceContext);

		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel1 =
			_assetListEntrySegmentsEntryRelLocalService.
				getAssetListEntrySegmentsEntryRel(
					assetListEntry.getAssetListEntryId(),
					SegmentsEntryConstants.ID_DEFAULT);

		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel2 =
			_assetListEntrySegmentsEntryRelLocalService.
				getAssetListEntrySegmentsEntryRel(
					assetListEntry.getAssetListEntryId(),
					segmentsEntry1.getSegmentsEntryId());

		_assetListEntrySegmentsEntryRelLocalService.updateVariationsPriority(
			new long[] {
				assetListEntrySegmentsEntryRel2.
					getAssetListEntrySegmentsEntryRelId(),
				assetListEntrySegmentsEntryRel1.
					getAssetListEntrySegmentsEntryRelId()
			});

		SegmentsEntry segmentsEntry2 = _addSegmentsEntryByFirstName("User");

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		long segmentsExperienceId = _addSegmentsExperience(
			layout, segmentsEntry2.getSegmentsEntryId());

		_addCollectionStyledLayoutStructureItem(
			assetListEntry.getAssetListEntryId(), layout, segmentsExperienceId);

		MockHttpServletRequest mockHttpServletRequest =
			_getMockHttpServletRequest(layout);

		mockHttpServletRequest.addParameter(
			"segmentsExperienceId", String.valueOf(segmentsExperienceId));

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		RenderLayoutStructureTag renderLayoutStructureTag =
			_getRenderLayoutStructureTag(
				layout, mockHttpServletRequest, mockHttpServletResponse,
				segmentsExperienceId);

		renderLayoutStructureTag.doTag(
			mockHttpServletRequest, mockHttpServletResponse);

		List<JournalArticle> actualJournalArticles =
			(List<JournalArticle>)mockHttpServletRequest.getAttribute(
				"liferay-info:info-list-grid:infoListObjects");

		Assert.assertNotNull(actualJournalArticles);
		Assert.assertEquals(
			actualJournalArticles.toString(), 1, actualJournalArticles.size());

		JournalArticle actualJournalArticle1 = actualJournalArticles.get(0);

		Assert.assertEquals(
			expectedJournalArticle1.getArticleId(),
			actualJournalArticle1.getArticleId());
	}

	@Test
	public void testRenderCollectionStyledLayoutStructureItemSelectingSegmentsExperienceWithSameSegmentsEntry()
		throws Exception {

		AssetListEntry assetListEntry =
			_assetListEntryLocalService.addAssetListEntry(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				AssetListEntryTypeConstants.TYPE_MANUAL, _serviceContext);

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName());

		JournalArticle expectedJournalArticle1 = _addJournalArticle(
			ddmStructure);

		AssetEntry assetEntry1 = _assetEntryLocalService.fetchEntry(
			JournalArticle.class.getName(),
			expectedJournalArticle1.getResourcePrimKey());

		_assetListEntryLocalService.addAssetEntrySelections(
			assetListEntry.getAssetListEntryId(),
			new long[] {assetEntry1.getEntryId()},
			SegmentsEntryConstants.ID_DEFAULT, _serviceContext);

		JournalArticle expectedJournalArticle2 = _addJournalArticle(
			ddmStructure);

		AssetEntry assetEntry2 = _assetEntryLocalService.fetchEntry(
			JournalArticle.class.getName(),
			expectedJournalArticle2.getResourcePrimKey());

		SegmentsEntry segmentsEntry = _addSegmentsEntryByFirstName("Test");

		_assetListEntryLocalService.addAssetEntrySelections(
			assetListEntry.getAssetListEntryId(),
			new long[] {assetEntry2.getEntryId()},
			segmentsEntry.getSegmentsEntryId(), _serviceContext);

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		long segmentsExperienceId = _addSegmentsExperience(
			layout, segmentsEntry.getSegmentsEntryId());

		_addCollectionStyledLayoutStructureItem(
			assetListEntry.getAssetListEntryId(), layout, segmentsExperienceId);

		MockHttpServletRequest mockHttpServletRequest =
			_getMockHttpServletRequest(layout);

		mockHttpServletRequest.addParameter(
			"segmentsExperienceId", String.valueOf(segmentsExperienceId));

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		RenderLayoutStructureTag renderLayoutStructureTag =
			_getRenderLayoutStructureTag(
				layout, mockHttpServletRequest, mockHttpServletResponse,
				segmentsExperienceId);

		renderLayoutStructureTag.doTag(
			mockHttpServletRequest, mockHttpServletResponse);

		List<JournalArticle> actualJournalArticles =
			(List<JournalArticle>)mockHttpServletRequest.getAttribute(
				"liferay-info:info-list-grid:infoListObjects");

		Assert.assertNotNull(actualJournalArticles);
		Assert.assertEquals(
			actualJournalArticles.toString(), 1, actualJournalArticles.size());

		JournalArticle actualJournalArticle = actualJournalArticles.get(0);

		Assert.assertEquals(
			expectedJournalArticle2.getArticleId(),
			actualJournalArticle.getArticleId());
	}

	@Test
	public void testRenderCollectionStyledLayoutStructureItemWithoutSelectingSegmentsExperience()
		throws Exception {

		AssetListEntry assetListEntry =
			_assetListEntryLocalService.addAssetListEntry(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				AssetListEntryTypeConstants.TYPE_MANUAL, _serviceContext);

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName());

		JournalArticle expectedJournalArticle1 = _addJournalArticle(
			ddmStructure);

		AssetEntry assetEntry1 = _assetEntryLocalService.fetchEntry(
			JournalArticle.class.getName(),
			expectedJournalArticle1.getResourcePrimKey());

		_assetListEntryLocalService.addAssetEntrySelections(
			assetListEntry.getAssetListEntryId(),
			new long[] {assetEntry1.getEntryId()},
			SegmentsEntryConstants.ID_DEFAULT, _serviceContext);

		JournalArticle expectedJournalArticle2 = _addJournalArticle(
			ddmStructure);

		AssetEntry assetEntry2 = _assetEntryLocalService.fetchEntry(
			JournalArticle.class.getName(),
			expectedJournalArticle2.getResourcePrimKey());

		SegmentsEntry segmentsEntry = _addSegmentsEntryByFirstName("Test");

		_assetListEntryLocalService.addAssetEntrySelections(
			assetListEntry.getAssetListEntryId(),
			new long[] {assetEntry2.getEntryId()},
			segmentsEntry.getSegmentsEntryId(), _serviceContext);

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		_addCollectionStyledLayoutStructureItem(
			JSONUtil.put(
				"classNameId", _portal.getClassNameId(AssetListEntry.class)
			).put(
				"classPK", assetListEntry.getAssetListEntryId()
			).put(
				"itemType", JournalArticle.class.getName()
			).put(
				"type", InfoListItemSelectorReturnType.class.getName()
			),
			null, layout,
			"com.liferay.journal.web.internal.info.list.renderer." +
				"BulletedJournalArticleBasicInfoListRenderer",
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid()));

		MockHttpServletRequest mockHttpServletRequest =
			_getMockHttpServletRequest(layout);

		_renderLayout(layout, mockHttpServletRequest);

		List<JournalArticle> actualJournalArticles =
			(List<JournalArticle>)mockHttpServletRequest.getAttribute(
				"liferay-info:info-list-grid:infoListObjects");

		Assert.assertNotNull(actualJournalArticles);
		Assert.assertEquals(
			actualJournalArticles.toString(), 1, actualJournalArticles.size());

		JournalArticle actualJournalArticle = actualJournalArticles.get(0);

		Assert.assertEquals(
			expectedJournalArticle1.getArticleId(),
			actualJournalArticle.getArticleId());
	}

	@Test
	@TestInfo({"LPS-123825", "LPS-149178"})
	public void testRenderCollectionStyledLayoutStructureItemWithPagination()
		throws Exception {

		AssetListEntry assetListEntry =
			_assetListEntryLocalService.addAssetListEntry(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				AssetListEntryTypeConstants.TYPE_DYNAMIC,
				UnicodePropertiesBuilder.create(
					true
				).put(
					"anyAssetType",
					String.valueOf(_portal.getClassNameId(JournalArticle.class))
				).put(
					"orderByColumn1", "priority"
				).put(
					"orderByType1", "ASC"
				).buildString(),
				_serviceContext);

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		long segmentsExperienceId =
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid());

		String itemId = _addCollectionStyledLayoutStructureItem(
			JSONUtil.put(
				"classNameId", _portal.getClassNameId(AssetListEntry.class)
			).put(
				"classPK", assetListEntry.getAssetListEntryId()
			).put(
				"itemType", JournalArticle.class.getName()
			).put(
				"type", InfoListItemSelectorReturnType.class.getName()
			),
			null, layout, null, segmentsExperienceId,
			_addFragmentEntryLinks(
				1, JSONUtil.put("collectionFieldId", "JournalArticle_title"),
				layout.fetchDraftLayout(), segmentsExperienceId));

		Locale locale = _portal.getSiteDefaultLocale(_group);

		List<String> titles = _addJournalArticlesAndGetTitles(7, locale);

		_testPagination(
			itemId,
			JSONUtil.put(
				"displayAllItems", false
			).put(
				"displayAllPages", true
			).put(
				"numberOfItemsPerPage", 2
			),
			layout, 2, 4, segmentsExperienceId, titles);

		_testPagination(
			itemId,
			JSONUtil.put(
				"displayAllPages", false
			).put(
				"numberOfItemsPerPage", 6
			).put(
				"numberOfPages", 1
			),
			layout, 6, 1, segmentsExperienceId, titles);

		_testPagination(
			itemId,
			JSONUtil.put(
				"displayAllPages", true
			).put(
				"numberOfItemsPerPage", 7
			).put(
				"numberOfPages", 1
			),
			layout, 7, 1, segmentsExperienceId, titles);

		_testPagination(
			itemId,
			JSONUtil.put(
				"displayAllItems", false
			).put(
				"displayAllPages", false
			).put(
				"numberOfItemsPerPage", 2
			).put(
				"numberOfPages", 2
			),
			layout, 2, 2, segmentsExperienceId, titles);
	}

	@Test
	public void testRenderContainerWithBackgroundImageAndCustomPathContext()
		throws Exception {

		String pathContext = "/de";

		PortalImpl portalImpl = new PortalImpl() {

			@Override
			public String getPathContext() {
				return pathContext;
			}

		};

		PortalUtil portalUtil = new PortalUtil();

		portalUtil.setPortal(portalImpl);

		try {
			Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

			LayoutPageTemplateStructure layoutPageTemplateStructure =
				LayoutPageTemplateStructureLocalServiceUtil.
					fetchLayoutPageTemplateStructure(
						_group.getGroupId(), layout.getPlid());

			LayoutStructure layoutStructure = LayoutStructure.of(
				layoutPageTemplateStructure.getDefaultSegmentsExperienceData());

			ContainerStyledLayoutStructureItem
				containerStyledLayoutStructureItem =
					(ContainerStyledLayoutStructureItem)
						layoutStructure.addContainerStyledLayoutStructureItem(
							layoutStructure.getMainItemId(), 0);

			FileEntry fileEntry = _addFileEntry();

			String url = _dlURLHelper.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
				false, false);

			containerStyledLayoutStructureItem.updateItemConfig(
				JSONUtil.put(
					"styles",
					JSONUtil.put(
						"backgroundImage",
						JSONUtil.put(
							"classNameId",
							_portal.getClassNameId(FileEntry.class)
						).put(
							"classPK", fileEntry.getFileEntryId()
						).put(
							"url", url
						))));

			_layoutPageTemplateStructureLocalService.
				updateLayoutPageTemplateStructureData(
					_group.getGroupId(), layout.getPlid(),
					_segmentsExperienceLocalService.
						fetchDefaultSegmentsExperienceId(layout.getPlid()),
					layoutStructure.toString());

			String content = _getRenderLayoutHTML(layout);

			Assert.assertTrue(content.contains(url));
			Assert.assertFalse(content.contains(pathContext + url));
		}
		finally {
			portalUtil.setPortal(new PortalImpl());
		}
	}

	@Test
	@TestInfo("LPS-119817")
	public void testRenderContainerWithLinkToURL() throws Exception {
		String languageId = LocaleUtil.toLanguageId(
			_portal.getSiteDefaultLocale(_group));

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		long segmentsExperienceId =
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid());

		JSONObject jsonObject = ContentLayoutTestUtil.addItemToLayout(
			JSONUtil.put(
				"link",
				JSONUtil.put(
					"href", JSONUtil.put(languageId, "https://www.liferay.com/")
				).put(
					"target", "_blank"
				)
			).toString(),
			LayoutDataItemTypeConstants.TYPE_CONTAINER,
			layout.fetchDraftLayout(), _layoutStructureProvider,
			segmentsExperienceId);

		String expectedContent = RandomTestUtil.randomString();

		_addFragmentEntryLinkToLayout(
			JSONUtil.put(languageId, expectedContent),
			layout.fetchDraftLayout(), jsonObject.getString("addedItemId"),
			segmentsExperienceId);

		ContentLayoutTestUtil.publishLayout(layout.fetchDraftLayout(), layout);

		String content = _getRenderLayoutHTML(layout);

		Assert.assertTrue(
			content,
			StringUtil.startsWith(
				content, "<a href=\"https://www.liferay.com/\""));
		Assert.assertTrue(
			content,
			StringUtil.contains(
				content, "target=\"_blank\"><div", StringPool.BLANK));
		Assert.assertTrue(
			content,
			StringUtil.contains(
				content,
				StringBundler.concat(
					"<h1 data-lfr-editable-id=\"element-text\" ",
					"data-lfr-editable-type=\"text\">", expectedContent,
					"</h1>"),
				StringPool.BLANK));
		Assert.assertTrue(content, StringUtil.endsWith(content, "</div></a>"));
	}

	@Test
	public void testRenderEditionForm() throws Exception {
		MockObject mockObject = new MockObject(RandomTestUtil.randomLong());

		InfoField<TextInfoFieldType> infoField1 = _getInfoField(false);

		String infoField1Value = RandomTestUtil.randomString();

		mockObject.addInfoField(infoField1, infoField1Value);

		InfoField<TextInfoFieldType> infoField2 = _getInfoField(false);

		String infoField2Value = RandomTestUtil.randomString();

		mockObject.addInfoField(infoField2, infoField2Value);

		try (MockInfoServiceRegistrationHolder
				mockInfoServiceRegistrationHolder =
					new MockInfoServiceRegistrationHolder(
						InfoFieldSet.builder(
						).infoFieldSetEntries(
							ListUtil.fromArray(infoField1, infoField2)
						).build(),
						mockObject, _portal, _displayPageInfoItemCapability,
						_editPageInfoItemCapability)) {

			Layout layout = _addDisplayPageWithFormAndGetLayout(
				infoField1, infoField2);

			MockHttpServletResponse mockHttpServletResponse = _renderLayout(
				layout,
				_getMockHttpServletRequest(
					layout,
					mockInfoServiceRegistrationHolder.
						getMockObjectLayoutDisplayPageObjectProvider()));

			String content = mockHttpServletResponse.getContentAsString();

			Assert.assertFalse(
				content.contains("<fieldset disabled=\"disabled\">"));

			_assertInfoFieldInput(infoField1, content, infoField1Value);
			_assertInfoFieldInput(infoField2, content, infoField2Value);

			_assertInputJSONObject(content, infoField1, infoField2);
		}
	}

	@Test
	@TestInfo("LPS-169924")
	public void testRenderEditionFormWithAddPermissionAndWithoutViewPermission()
		throws Exception {

		MockObject mockObject = new MockObject(
			true, RandomTestUtil.randomLong(), false, false);

		InfoField<TextInfoFieldType> infoField1 = _getInfoField(false);

		String infoField1Value = RandomTestUtil.randomString();

		mockObject.addInfoField(infoField1, infoField1Value);

		InfoField<TextInfoFieldType> infoField2 = _getInfoField(false);

		String infoField2Value = RandomTestUtil.randomString();

		mockObject.addInfoField(infoField2, infoField2Value);

		try (MockInfoServiceRegistrationHolder
				mockInfoServiceRegistrationHolder =
					new MockInfoServiceRegistrationHolder(
						InfoFieldSet.builder(
						).infoFieldSetEntries(
							ListUtil.fromArray(infoField1, infoField2)
						).build(),
						mockObject, _portal, _displayPageInfoItemCapability,
						_editPageInfoItemCapability)) {

			Layout layout = _addDisplayPageWithFormAndGetLayout(
				infoField1, infoField2);

			MockHttpServletResponse mockHttpServletResponse = _renderLayout(
				layout,
				_getMockHttpServletRequest(
					layout,
					mockInfoServiceRegistrationHolder.
						getMockObjectLayoutDisplayPageObjectProvider()));

			String content = mockHttpServletResponse.getContentAsString();

			Assert.assertTrue(
				content.contains("<fieldset disabled=\"disabled\">"));

			_assertInfoFieldInput(infoField1, content, infoField1Value);
			_assertInfoFieldInput(infoField2, content, infoField2Value);

			_assertInputJSONObject(content, infoField1, infoField2);
		}
	}

	@Test
	@TestInfo("LPS-169924")
	public void testRenderEditionFormWithoutAddPermission() throws Exception {
		MockObject mockObject = new MockObject(
			false, RandomTestUtil.randomLong(), false, false);

		InfoField<TextInfoFieldType> infoField1 = _getInfoField(false);

		String infoField1Value = RandomTestUtil.randomString();

		mockObject.addInfoField(infoField1, infoField1Value);

		InfoField<TextInfoFieldType> infoField2 = _getInfoField(false);

		String infoField2Value = RandomTestUtil.randomString();

		mockObject.addInfoField(infoField2, infoField2Value);

		try (MockInfoServiceRegistrationHolder
				mockInfoServiceRegistrationHolder =
					new MockInfoServiceRegistrationHolder(
						InfoFieldSet.builder(
						).infoFieldSetEntries(
							ListUtil.fromArray(infoField1, infoField2)
						).build(),
						mockObject, _portal, _displayPageInfoItemCapability,
						_editPageInfoItemCapability)) {

			Layout layout = _addDisplayPageWithFormAndGetLayout(
				infoField1, infoField2);

			MockHttpServletResponse mockHttpServletResponse = _renderLayout(
				layout,
				_getMockHttpServletRequest(
					layout,
					mockInfoServiceRegistrationHolder.
						getMockObjectLayoutDisplayPageObjectProvider()));

			String content = mockHttpServletResponse.getContentAsString();

			Assert.assertTrue(content.isEmpty());
		}
	}

	@Test
	public void testRenderEditionFormWithoutUpdatePermission()
		throws Exception {

		MockObject mockObject = new MockObject(
			RandomTestUtil.randomLong(), false, true);

		InfoField<TextInfoFieldType> infoField1 = _getInfoField(false);

		String infoField1Value = RandomTestUtil.randomString();

		mockObject.addInfoField(infoField1, infoField1Value);

		InfoField<TextInfoFieldType> infoField2 = _getInfoField(false);

		String infoField2Value = RandomTestUtil.randomString();

		mockObject.addInfoField(infoField2, infoField2Value);

		try (MockInfoServiceRegistrationHolder
				mockInfoServiceRegistrationHolder =
					new MockInfoServiceRegistrationHolder(
						InfoFieldSet.builder(
						).infoFieldSetEntries(
							ListUtil.fromArray(infoField1, infoField2)
						).build(),
						mockObject, _portal, _displayPageInfoItemCapability,
						_editPageInfoItemCapability)) {

			Layout layout = _addDisplayPageWithFormAndGetLayout(
				infoField1, infoField2);

			MockHttpServletResponse mockHttpServletResponse = _renderLayout(
				layout,
				_getMockHttpServletRequest(
					layout,
					mockInfoServiceRegistrationHolder.
						getMockObjectLayoutDisplayPageObjectProvider()));

			String content = mockHttpServletResponse.getContentAsString();

			Assert.assertTrue(
				content.contains("<fieldset disabled=\"disabled\">"));

			_assertInfoFieldInput(infoField1, content, infoField1Value);
			_assertInfoFieldInput(infoField2, content, infoField2Value);

			_assertInputJSONObject(content, infoField1, infoField2);
		}
	}

	@Test
	public void testRenderFormWithInfoFormException() throws Exception {
		InfoField<TextInfoFieldType> infoField = _getInfoField(false);

		try (MockInfoServiceRegistrationHolder
				mockInfoServiceRegistrationHolder =
					new MockInfoServiceRegistrationHolder(
						InfoFieldSet.builder(
						).infoFieldSetEntries(
							ListUtil.fromArray(infoField)
						).build(),
						_portal, _editPageInfoItemCapability)) {

			Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

			MockHttpServletRequest mockHttpServletRequest =
				_getMockHttpServletRequest(layout);

			String formItemId = ContentLayoutTestUtil.addFormToPublishedLayout(
				false,
				String.valueOf(
					_portal.getClassNameId(MockObject.class.getName())),
				"0", layout, _layoutStructureProvider, infoField);

			InfoFormException infoFormException = new InfoFormException();

			SessionErrors.add(
				mockHttpServletRequest, formItemId, infoFormException);

			MockHttpServletResponse mockHttpServletResponse = _renderLayout(
				layout, mockHttpServletRequest);

			Assert.assertFalse(
				SessionErrors.contains(mockHttpServletRequest, formItemId));

			String content = mockHttpServletResponse.getContentAsString();

			_assertErrorMessage(
				content,
				infoFormException.getLocalizedMessage(
					_portal.getSiteDefaultLocale(_group)));

			_assertInfoFieldInput(infoField, content);
		}
	}

	@Test
	public void testRenderFormWithInfoFormValidationException()
		throws Exception {

		InfoField<TextInfoFieldType> infoField = _getInfoField(false);

		try (MockInfoServiceRegistrationHolder
				mockInfoServiceRegistrationHolder =
					new MockInfoServiceRegistrationHolder(
						InfoFieldSet.builder(
						).infoFieldSetEntries(
							ListUtil.fromArray(infoField)
						).build(),
						_portal, _editPageInfoItemCapability)) {

			Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

			MockHttpServletRequest mockHttpServletRequest =
				_getMockHttpServletRequest(layout);

			String formItemId = ContentLayoutTestUtil.addFormToPublishedLayout(
				false,
				String.valueOf(
					_portal.getClassNameId(MockObject.class.getName())),
				"0", layout, _layoutStructureProvider, infoField);

			InfoFormValidationException infoFormValidationException =
				new InfoFormValidationException(infoField.getUniqueId());

			SessionErrors.add(
				mockHttpServletRequest, formItemId,
				infoFormValidationException);
			SessionErrors.add(
				mockHttpServletRequest, InfoFormException.class,
				infoFormValidationException);

			MockHttpServletResponse mockHttpServletResponse = _renderLayout(
				layout, mockHttpServletRequest);

			Assert.assertFalse(
				SessionErrors.contains(mockHttpServletRequest, formItemId));
			Assert.assertFalse(
				SessionErrors.contains(
					mockHttpServletRequest, InfoFormException.class));

			String content = mockHttpServletResponse.getContentAsString();

			Locale locale = _portal.getSiteDefaultLocale(_group);

			_assertErrorMessage(
				content,
				infoFormValidationException.getLocalizedMessage(
					infoField.getLabel(locale), locale));

			_assertInfoFieldInput(infoField, content);
		}
	}

	@Test
	public void testRenderFormWithoutErrors() throws Exception {
		InfoField<TextInfoFieldType> infoField = _getInfoField(false);
		InfoField<TextInfoFieldType> readOnlyInfoField = _getInfoField(true);

		try (MockInfoServiceRegistrationHolder
				mockInfoServiceRegistrationHolder =
					new MockInfoServiceRegistrationHolder(
						InfoFieldSet.builder(
						).infoFieldSetEntries(
							ListUtil.fromArray(infoField, readOnlyInfoField)
						).build(),
						_portal, _editPageInfoItemCapability)) {

			Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

			ContentLayoutTestUtil.addFormToPublishedLayout(
				false,
				String.valueOf(
					_portal.getClassNameId(MockObject.class.getName())),
				"0", layout, _layoutStructureProvider, infoField,
				readOnlyInfoField);

			String content = _getRenderLayoutHTML(layout);

			String errorHTML = "<div class=\"alert alert-danger\">";

			Assert.assertFalse(content.contains(errorHTML));

			_assertInfoFieldInput(infoField, content);
			_assertInfoFieldInput(readOnlyInfoField, content);

			_assertInputJSONObject(content, infoField, readOnlyInfoField);
		}
	}

	@Test
	public void testRenderFormWithSuccessMessage() throws Exception {
		InfoField<TextInfoFieldType> infoField = _getInfoField(false);

		try (MockInfoServiceRegistrationHolder
				mockInfoServiceRegistrationHolder =
					new MockInfoServiceRegistrationHolder(
						InfoFieldSet.builder(
						).infoFieldSetEntries(
							ListUtil.fromArray(infoField)
						).build(),
						_portal, _editPageInfoItemCapability)) {

			Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

			String formItemId = ContentLayoutTestUtil.addFormToPublishedLayout(
				false,
				String.valueOf(
					_portal.getClassNameId(MockObject.class.getName())),
				"0", layout, _layoutStructureProvider, infoField);

			MockHttpServletRequest mockHttpServletRequest =
				_getMockHttpServletRequest(layout);

			SessionMessages.add(mockHttpServletRequest, formItemId);

			MockHttpServletResponse mockHttpServletResponse = _renderLayout(
				layout, mockHttpServletRequest);

			String content = mockHttpServletResponse.getContentAsString();

			String formStartHTML = "<form action=\"";

			Assert.assertFalse(content.contains(formStartHTML));

			Locale locale = _portal.getSiteDefaultLocale(_group);

			String expectedSuccessMessage = LanguageUtil.get(
				locale,
				"thank-you.-your-information-was-successfully-received");

			String expectedSuccessHTML = StringBundler.concat(
				"<div class=\"bg-white font-weight-semi-bold p-5 text-3 ",
				"text-center text-secondary\">", expectedSuccessMessage,
				"</div>");

			Assert.assertTrue(content.contains(expectedSuccessHTML));

			String expectedInfoFieldInput =
				"<p>InputName:" + infoField.getName() + "</p>";

			Assert.assertFalse(content.contains(expectedInfoFieldInput));
		}
	}

	@Test
	@TestInfo("LPS-120094")
	public void testRenderFragmentEntryLinkWithImageLinkToURL()
		throws Exception {

		String languageId = LocaleUtil.toLanguageId(
			_portal.getSiteDefaultLocale(_group));

		FileEntry fileEntry = _addFileEntry();

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		_addFragmentEntryLinkToLayout(
			JSONUtil.put(
				"image-square",
				JSONUtil.put(
					languageId, RandomTestUtil.randomString()
				).put(
					languageId,
					JSONUtil.put(
						"classNameId", _portal.getClassNameId(FileEntry.class)
					).put(
						"classPK", fileEntry.getFileEntryId()
					).put(
						"fileEntryId", fileEntry.getFileEntryId()
					).put(
						"url",
						_dlURLHelper.getPreviewURL(
							fileEntry, fileEntry.getFileVersion(), null,
							StringPool.BLANK, false, false)
					)
				).put(
					"config",
					JSONUtil.put(
						"href",
						JSONUtil.put(languageId, "https://www.liferay.com/")
					).put(
						"mapperType", "link"
					)
				)),
			"BASIC_COMPONENT-image", layout.fetchDraftLayout(),
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid()));

		ContentLayoutTestUtil.publishLayout(layout.fetchDraftLayout(), layout);

		String content = _getRenderLayoutHTML(layout);

		Assert.assertTrue(
			content,
			StringUtil.contains(
				content,
				StringBundler.concat(
					"<a href=\"https://www.liferay.com/\"><img alt=\"\" ",
					"class=\"w-100\" data-lfr-editable-id=\"image-square\" ",
					"data-lfr-editable-type=\"image\" src=\"",
					HtmlUtil.escape(
						_dlURLHelper.getPreviewURL(
							fileEntry, fileEntry.getFileVersion(), null,
							StringPool.BLANK)),
					"\" data-fileentryid=\"", fileEntry.getFileEntryId(),
					"\"></a>"),
				StringPool.BLANK));
	}

	@Test
	@TestInfo("LPS-120348")
	public void testRenderFragmentEntryLinkWithLinkToURL() throws Exception {
		String languageId = LocaleUtil.toLanguageId(
			_portal.getSiteDefaultLocale(_group));

		String expectedContent = RandomTestUtil.randomString();

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		_addFragmentEntryLinkToLayout(
			JSONUtil.put(
				"element-text",
				JSONUtil.put(
					languageId, expectedContent
				).put(
					"config",
					JSONUtil.put(
						"href",
						JSONUtil.put(languageId, "https://www.liferay.com/")
					).put(
						"mapperType", "link"
					).put(
						"target", "_blank"
					)
				).put(
					"defaultValue", "Heading Example"
				)),
			"BASIC_COMPONENT-heading", layout.fetchDraftLayout(),
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid()));

		ContentLayoutTestUtil.publishLayout(layout.fetchDraftLayout(), layout);

		String content = _getRenderLayoutHTML(layout);

		Assert.assertTrue(
			content,
			StringUtil.contains(
				content,
				StringBundler.concat(
					"data-lfr-editable-id=\"element-text\" ",
					"data-lfr-editable-type=\"text\"><a target=\"_blank\" ",
					"href=\"https://www.liferay.com/\">", expectedContent,
					"</a></h1></div>"),
				StringPool.BLANK));
	}

	@Test
	@TestInfo("LPD-41653")
	public void testViewAssertAnalyticsTargetableCollectionIdForCollectionStyledLayoutStructureItem()
		throws Exception {

		AssetListEntry assetListEntry =
			_assetListEntryLocalService.addAssetListEntry(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				AssetListEntryTypeConstants.TYPE_MANUAL, _serviceContext);

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		long segmentsExperienceId =
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid());

		String itemId = _addCollectionStyledLayoutStructureItem(
			assetListEntry, layout, _COUNT_INFO_LIST_ITEMS, "none",
			segmentsExperienceId);

		String content = _getRenderLayoutHTML(layout);

		Assert.assertTrue(
			content.contains(
				"id=\"analytics-targetable-collection-" + itemId + "\""));
	}

	@Test
	@TestInfo("LPS-151738")
	public void testViewCommonStylesClassesGeneratedInOuterDivForFragmentEntryWithoutStylingAttribute()
		throws Exception {

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		Layout draftLayout = layout.fetchDraftLayout();

		ContentLayoutTestUtil.addFragmentEntryLinkToLayout(
			"{}", draftLayout,
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				draftLayout.getPlid()));

		ContentLayoutTestUtil.publishLayout(draftLayout, layout);

		String content = _getRenderLayoutHTML(layout);

		Assert.assertTrue(
			content.startsWith("<div class=\"lfr-layout-structure-item-"));
	}

	private List<AssetEntry> _addAssetEntries(AssetListEntry assetListEntry)
		throws Exception {

		List<AssetEntry> assetEntries = new ArrayList<>();

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName());

		for (int i = 0; i < _COUNT_INFO_LIST_ITEMS; i++) {
			JournalArticle journalArticle = _addJournalArticle(ddmStructure);

			assetEntries.add(
				_assetEntryLocalService.fetchEntry(
					JournalArticle.class.getName(),
					journalArticle.getResourcePrimKey()));
		}

		_assetListEntryLocalService.addAssetEntrySelections(
			assetListEntry.getAssetListEntryId(),
			TransformUtil.transformToLongArray(
				assetEntries, assetEntry -> assetEntry.getEntryId()),
			SegmentsEntryConstants.ID_DEFAULT, _serviceContext);

		return assetEntries;
	}

	private String _addCollectionStyledLayoutStructureItem(
			AssetListEntry assetListEntry, Layout layout,
			int numberOfItemsPerPage, String paginationType,
			long segmentsExperienceId, FragmentEntryLink... fragmentEntryLinks)
		throws Exception {

		return _addCollectionStyledLayoutStructureItem(
			JSONUtil.put(
				"classNameId", _portal.getClassNameId(AssetListEntry.class)
			).put(
				"classPK", assetListEntry.getAssetListEntryId()
			).put(
				"itemType", JournalArticle.class.getName()
			).put(
				"type", InfoListItemSelectorReturnType.class.getName()
			),
			JSONUtil.put(
				"displayAllPages", true
			).put(
				"numberOfItemsPerPage", numberOfItemsPerPage
			).put(
				"paginationType", paginationType
			).put(
				"showAllItems", true
			),
			layout, null, segmentsExperienceId, fragmentEntryLinks);
	}

	private String _addCollectionStyledLayoutStructureItem(
			JSONObject collectionJSONObject, JSONObject displayConfigJSONObject,
			Layout layout, String listStyle, long segmentsExperienceId,
			FragmentEntryLink... fragmentEntryLinks)
		throws Exception {

		Layout draftLayout = layout.fetchDraftLayout();

		String itemId = ContentLayoutTestUtil.addCollectionDisplayToLayout(
			collectionJSONObject, draftLayout, _layoutStructureProvider,
			listStyle, null, 0, segmentsExperienceId, fragmentEntryLinks);

		if (displayConfigJSONObject != null) {
			_updateItemConfig(
				itemId, displayConfigJSONObject, draftLayout,
				segmentsExperienceId);
		}

		ContentLayoutTestUtil.publishLayout(draftLayout, layout);

		return itemId;
	}

	private void _addCollectionStyledLayoutStructureItem(
			long assetListEntryId, Layout layout, long segmentsExperienceId)
		throws Exception {

		_addCollectionStyledLayoutStructureItem(
			JSONUtil.put(
				"classNameId", _portal.getClassNameId(AssetListEntry.class)
			).put(
				"classPK", assetListEntryId
			).put(
				"itemType", JournalArticle.class.getName()
			).put(
				"type", InfoListItemSelectorReturnType.class.getName()
			),
			null, layout,
			"com.liferay.journal.web.internal.info.list.renderer." +
				"BulletedJournalArticleBasicInfoListRenderer",
			segmentsExperienceId);
	}

	private Layout _addDisplayPageWithFormAndGetLayout(InfoField... infoFields)
		throws Exception {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.addLayoutPageTemplateEntry(
				null, _group.getGroupId(), 0,
				_portal.getClassNameId(MockObject.class.getName()), 0,
				RandomTestUtil.randomString(), 0,
				WorkflowConstants.STATUS_DRAFT, _serviceContext);

		Layout layout = _layoutLocalService.getLayout(
			layoutPageTemplateEntry.getPlid());

		ContentLayoutTestUtil.addFormToPublishedLayout(
			false,
			String.valueOf(_portal.getClassNameId(MockObject.class.getName())),
			"0", layout, _layoutStructureProvider, infoFields);

		return _layoutLocalService.getLayout(layout.getPlid());
	}

	private FileEntry _addFileEntry() throws Exception {
		return _dlAppLocalService.addFileEntry(
			null, TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString() + ".jpg", ContentTypes.IMAGE_JPEG,
			FileUtil.getBytes(
				RenderLayoutStructureTagTest.class, "dependencies/liferay.jpg"),
			null, null, null,
			ServiceContextTestUtil.getServiceContext(
				_group, TestPropsValues.getUserId()));
	}

	private FragmentEntry _addFragmentEntry() throws Exception {
		FragmentCollection fragmentCollection =
			_fragmentCollectionLocalService.addFragmentCollection(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				StringUtil.randomString(), StringPool.BLANK, _serviceContext);

		return _fragmentEntryLocalService.addFragmentEntry(
			null, TestPropsValues.getUserId(), _group.getGroupId(),
			fragmentCollection.getFragmentCollectionId(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			StringPool.BLANK,
			"<h1 data-lfr-editable-id=\"element-text\" " +
				"data-lfr-editable-type=\"text\">Heading Example</h1>",
			StringPool.BLANK, false, StringPool.BLANK, null, 0, false,
			FragmentConstants.TYPE_COMPONENT, null,
			WorkflowConstants.STATUS_APPROVED, _serviceContext);
	}

	private FragmentEntryLink[] _addFragmentEntryLinks(
			int count, JSONObject jsonObject, Layout layout,
			long segmentsExperienceId)
		throws Exception {

		FragmentEntryLink[] fragmentEntryLinks = new FragmentEntryLink[count];

		FragmentEntry fragmentEntry = _addFragmentEntry();

		for (int i = 0; i < count; i++) {
			fragmentEntryLinks[i] =
				_fragmentEntryLinkLocalService.addFragmentEntryLink(
					null, TestPropsValues.getUserId(), _group.getGroupId(), 0,
					0, segmentsExperienceId, layout.getPlid(),
					fragmentEntry.getCss(), fragmentEntry.getHtml(),
					fragmentEntry.getJs(), fragmentEntry.getConfiguration(),
					JSONUtil.put(
						FragmentEntryProcessorConstants.
							KEY_EDITABLE_FRAGMENT_ENTRY_PROCESSOR,
						JSONUtil.put("element-text", jsonObject)
					).toString(),
					StringPool.BLANK, 0, fragmentEntry.getFragmentEntryKey(),
					fragmentEntry.getType(), _serviceContext);
		}

		return fragmentEntryLinks;
	}

	private FragmentEntryLink _addFragmentEntryLinkToLayout(
			JSONObject editableFragmentEntryProcessorJSONObject,
			FragmentEntry fragmentEntry, Layout layout, String parentItemId,
			int position, long segmentsExperienceId)
		throws Exception {

		return ContentLayoutTestUtil.addFragmentEntryLinkToLayout(
			JSONUtil.put(
				FragmentEntryProcessorConstants.
					KEY_EDITABLE_FRAGMENT_ENTRY_PROCESSOR,
				editableFragmentEntryProcessorJSONObject
			).toString(),
			fragmentEntry.getCss(), fragmentEntry.getConfiguration(),
			fragmentEntry.getFragmentEntryId(), fragmentEntry.getHtml(),
			fragmentEntry.getJs(), layout, fragmentEntry.getFragmentEntryKey(),
			fragmentEntry.getType(), parentItemId, position,
			segmentsExperienceId);
	}

	private FragmentEntryLink _addFragmentEntryLinkToLayout(
			JSONObject elemenTextJSONObject, Layout layout, String parentItemId,
			long segmentsExperienceId)
		throws Exception {

		FragmentEntry fragmentEntry = _addFragmentEntry();

		return _addFragmentEntryLinkToLayout(
			JSONUtil.put("element-text", elemenTextJSONObject), fragmentEntry,
			layout, parentItemId, 0, segmentsExperienceId);
	}

	private FragmentEntryLink _addFragmentEntryLinkToLayout(
			JSONObject editableFragmentEntryProcessorJSONObject,
			String fragmentEntryKey, Layout layout, long segmentsExperienceId)
		throws Exception {

		FragmentEntry fragmentEntry =
			_fragmentCollectionContributorRegistry.getFragmentEntry(
				fragmentEntryKey);

		return ContentLayoutTestUtil.addFragmentEntryLinkToLayout(
			JSONUtil.put(
				FragmentEntryProcessorConstants.
					KEY_EDITABLE_FRAGMENT_ENTRY_PROCESSOR,
				editableFragmentEntryProcessorJSONObject
			).toString(),
			fragmentEntry.getCss(), fragmentEntry.getConfiguration(),
			fragmentEntry.getFragmentEntryId(), fragmentEntry.getHtml(),
			fragmentEntry.getJs(), layout, fragmentEntry.getFragmentEntryKey(),
			fragmentEntry.getType(), null, 0, segmentsExperienceId);
	}

	private JournalArticle _addJournalArticle(DDMStructure ddmStructure)
		throws Exception {

		return _journalArticleLocalService.addArticle(
			null, TestPropsValues.getUserId(), _group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			JournalArticleConstants.CLASS_NAME_ID_DEFAULT, 0, StringPool.BLANK,
			true, JournalArticleConstants.VERSION_DEFAULT,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			DDMStructureTestUtil.getSampleStructuredContent(),
			ddmStructure.getStructureId(), StringPool.BLANK, null, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0, true, true, false, 0, 0,
			null, null, null, null,
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));
	}

	private List<String> _addJournalArticlesAndGetTitles(
			int count, Locale locale)
		throws Exception {

		List<String> titles = new ArrayList<>();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setCommand(Constants.ADD);
		serviceContext.setLayoutFullURL("http://localhost");

		for (int i = 0; i < count; i++) {
			serviceContext.setAssetPriority(i);

			JournalArticle journalArticle = JournalTestUtil.addArticle(
				_group.getGroupId(),
				JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				JournalArticleConstants.CLASS_NAME_ID_DEFAULT,
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), locale, true, true,
				serviceContext);

			titles.add(journalArticle.getTitle(locale));
		}

		return titles;
	}

	private SegmentsEntry _addSegmentsEntryByFirstName(String firstName)
		throws Exception {

		Criteria criteria = new Criteria();

		_segmentsCriteriaContributor.contribute(
			criteria, String.format("(firstName eq '%s')", firstName),
			Criteria.Conjunction.AND);

		return SegmentsTestUtil.addSegmentsEntry(
			_group.getGroupId(), CriteriaSerializer.serialize(criteria));
	}

	private long _addSegmentsExperience(Layout layout, long segmentsEntryId)
		throws Exception {

		Layout draftLayout = layout.fetchDraftLayout();

		MVCActionCommand addSegmentsExperienceMVCActionCommand =
			ContentLayoutTestUtil.getMVCActionCommand(
				"/layout_content_page_editor/add_segments_experience");

		MockLiferayPortletActionRequest mockLiferayPortletActionRequest =
			ContentLayoutTestUtil.getMockLiferayPortletActionRequest(
				_companyLocalService.getCompany(_group.getCompanyId()), _group,
				draftLayout);

		mockLiferayPortletActionRequest.setParameter(
			"groupId", String.valueOf(draftLayout.getGroupId()));
		mockLiferayPortletActionRequest.setParameter(
			"name", RandomTestUtil.randomString());
		mockLiferayPortletActionRequest.setParameter(
			"plid", String.valueOf(draftLayout.getPlid()));
		mockLiferayPortletActionRequest.setParameter(
			"segmentsEntryId", String.valueOf(segmentsEntryId));

		JSONObject jsonObject = ReflectionTestUtil.invoke(
			addSegmentsExperienceMVCActionCommand, "doTransactionalCommand",
			new Class<?>[] {ActionRequest.class, ActionResponse.class},
			mockLiferayPortletActionRequest,
			new MockLiferayPortletActionResponse());

		JSONObject segmentsExperienceJSONObject = jsonObject.getJSONObject(
			"segmentsExperience");

		SegmentsExperience segmentsExperience =
			_segmentsExperienceLocalService.getSegmentsExperience(
				segmentsExperienceJSONObject.getLong("segmentsExperienceId"));

		return segmentsExperience.getSegmentsExperienceId();
	}

	private void _assertErrorMessage(
		String content, String expectedErrorMessage) {

		String expectedErrorHTML =
			"<div class=\"alert alert-danger\">" + expectedErrorMessage +
				"</div>";

		Assert.assertTrue(content.contains(expectedErrorHTML));
	}

	private void _assertInfoFieldInput(
		InfoField<TextInfoFieldType> infoField, String content) {

		String expectedInfoFieldInput =
			"<p>InputName:" + infoField.getName() + "</p>";

		Assert.assertTrue(content.contains(expectedInfoFieldInput));
	}

	private void _assertInfoFieldInput(
		InfoField<TextInfoFieldType> infoField, String content, String value) {

		_assertInfoFieldInput(infoField, content);

		Assert.assertTrue(content.contains(value));
	}

	private void _assertInfoFieldInputJSONObject(
			InfoField<TextInfoFieldType> infoField, String jsonString,
			Locale locale)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonString);

		Assert.assertEquals(
			infoField.getLabel(locale), jsonObject.getString("label"));
		Assert.assertEquals(
			infoField.isReadOnly(), jsonObject.getBoolean("readOnly"));
		Assert.assertEquals("text", jsonObject.getString("type"));
	}

	private void _assertInputJSONObject(String content, InfoField... infoFields)
		throws Exception {

		Matcher matcher = _inputJSONObjectPattern.matcher(content);
		Locale locale = _portal.getSiteDefaultLocale(_group);

		for (InfoField infoField : infoFields) {
			Assert.assertTrue(matcher.find());

			_assertInfoFieldInputJSONObject(
				infoField, matcher.group(1), locale);
		}
	}

	private void _assertNumericPagination(
		String html, int pageNumber, int numberOfPages) {

		Assert.assertEquals(
			html, numberOfPages + 2, StringUtil.count(html, "page-item"));

		String[] pageItems = html.split("page-item");

		Assert.assertEquals(
			pageItems.toString(), numberOfPages + 3, pageItems.length);

		Assert.assertTrue(
			pageItems[pageNumber],
			StringUtil.endsWith(pageItems[pageNumber], "\"active "));

		if (pageNumber != 1) {
			Assert.assertFalse(
				pageItems[0], StringUtil.endsWith(pageItems[0], "\"disabled "));
		}
		else {
			Assert.assertTrue(
				pageItems[0], StringUtil.endsWith(pageItems[0], "\"disabled "));
		}

		if (pageNumber != numberOfPages) {
			Assert.assertFalse(
				pageItems[pageNumber + 1],
				StringUtil.endsWith(pageItems[pageNumber + 1], "\"disabled "));
		}
		else {
			Assert.assertTrue(
				pageItems[pageNumber + 1],
				StringUtil.endsWith(pageItems[pageNumber + 1], "\"disabled "));
		}
	}

	private void _assertPagination(
			String itemId, Layout layout, int pageNumber,
			int numberOfItemsPerPage, int numberOfPages, String paginationType,
			List<String> strings)
		throws Exception {

		String html = _getRenderLayoutHTML(
			layout,
			HashMapBuilder.put(
				"page_number_" + itemId,
				() -> {
					if (pageNumber > 1) {
						return String.valueOf(pageNumber);
					}

					return null;
				}
			).build());

		int endIndex = pageNumber * numberOfItemsPerPage;
		int startIndex =
			(pageNumber * numberOfItemsPerPage) - numberOfItemsPerPage;

		for (int i = 0; i < strings.size(); i++) {
			String string = strings.get(i);

			if ((i >= startIndex) && (i < endIndex)) {
				Assert.assertTrue(
					html + " does not contain " + string,
					html.contains(string));
			}
			else {
				Assert.assertFalse(
					html + " contains " + string, html.contains(string));
			}
		}

		if (paginationType.equals("numeric")) {
			_assertNumericPagination(html, pageNumber, numberOfPages);
		}
		else {
			_assertSimplePagination(html, itemId, pageNumber, numberOfPages);
		}
	}

	private void _assertSimplePagination(
		String html, String itemId, int pageNumber, int numberOfPages) {

		Assert.assertTrue(
			html, html.contains("paginationNextButton_" + itemId));
		Assert.assertTrue(
			html, html.contains("paginationPreviousButton_" + itemId));

		if (pageNumber != 1) {
			Assert.assertFalse(
				html,
				html.contains(
					"paginationPreviousButton_" + itemId +
						"\" disabled=\"disabled\""));
		}
		else {
			Assert.assertTrue(
				html,
				html.contains(
					"paginationPreviousButton_" + itemId +
						"\" disabled=\"disabled\""));
		}

		if (pageNumber != numberOfPages) {
			Assert.assertFalse(
				html,
				html.contains(
					"paginationNextButton_" + itemId +
						"\" disabled=\"disabled\""));
		}
		else {
			Assert.assertTrue(
				html,
				html.contains(
					"paginationNextButton_" + itemId +
						"\" disabled=\"disabled\""));
		}
	}

	private DDMForm _deserialize(String content) {
		DDMFormDeserializerDeserializeRequest.Builder builder =
			DDMFormDeserializerDeserializeRequest.Builder.newBuilder(content);

		DDMFormDeserializerDeserializeResponse
			ddmFormDeserializerDeserializeResponse =
				_jsonDDMFormDeserializer.deserialize(builder.build());

		return ddmFormDeserializerDeserializeResponse.getDDMForm();
	}

	private LayoutStructure _getDefaultMasterLayoutStructure() {
		LayoutStructure layoutStructure = new LayoutStructure();

		LayoutStructureItem rootLayoutStructureItem =
			layoutStructure.addRootLayoutStructureItem();

		layoutStructure.addDropZoneLayoutStructureItem(
			rootLayoutStructureItem.getItemId(), 0);

		return layoutStructure;
	}

	private InfoField<TextInfoFieldType> _getInfoField(boolean readOnly) {
		return InfoField.builder(
		).infoFieldType(
			TextInfoFieldType.INSTANCE
		).namespace(
			RandomTestUtil.randomString()
		).name(
			RandomTestUtil.randomString()
		).labelInfoLocalizedValue(
			InfoLocalizedValue.singleValue(RandomTestUtil.randomString())
		).localizable(
			true
		).readOnly(
			readOnly
		).build();
	}

	private MockHttpServletRequest _getMockHttpServletRequest(Layout layout)
		throws Exception {

		return _getMockHttpServletRequest(layout, null, Collections.emptyMap());
	}

	private MockHttpServletRequest _getMockHttpServletRequest(
			Layout layout,
			LayoutDisplayPageObjectProvider layoutDisplayPageObjectProvider)
		throws Exception {

		return _getMockHttpServletRequest(
			layout, layoutDisplayPageObjectProvider, Collections.emptyMap());
	}

	private MockHttpServletRequest _getMockHttpServletRequest(
			Layout layout,
			LayoutDisplayPageObjectProvider layoutDisplayPageObjectProvider,
			Map<String, String> map)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			ContentLayoutTestUtil.getMockHttpServletRequest(
				_companyLocalService.getCompany(layout.getCompanyId()), _group,
				layout);

		mockHttpServletRequest.setAttribute(
			LayoutDisplayPageWebKeys.LAYOUT_DISPLAY_PAGE_OBJECT_PROVIDER,
			layoutDisplayPageObjectProvider);
		mockHttpServletRequest.setAttribute(
			"ORIGINAL_HTTP_SERVLET_REQUEST", mockHttpServletRequest);
		mockHttpServletRequest.setMethod(HttpMethods.GET);

		for (Map.Entry<String, String> entry : map.entrySet()) {
			mockHttpServletRequest.setParameter(
				entry.getKey(), entry.getValue());
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)mockHttpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		themeDisplay.setRequest(mockHttpServletRequest);

		return mockHttpServletRequest;
	}

	private String _getRenderLayoutHTML(Layout layout) throws Exception {
		return _getRenderLayoutHTML(layout, Collections.emptyMap());
	}

	private String _getRenderLayoutHTML(Layout layout, Map<String, String> map)
		throws Exception {

		MockHttpServletResponse mockHttpServletResponse = _renderLayout(
			layout, _getMockHttpServletRequest(layout, null, map));

		return mockHttpServletResponse.getContentAsString();
	}

	private RenderLayoutStructureTag _getRenderLayoutStructureTag(
		Layout layout, MockHttpServletRequest mockHttpServletRequest,
		MockHttpServletResponse mockHttpServletResponse,
		long selectedSegmentsExperienceId) {

		RenderLayoutStructureTag renderLayoutStructureTag =
			new RenderLayoutStructureTag();

		renderLayoutStructureTag.setLayoutStructure(
			_layoutStructureProvider.getLayoutStructure(
				layout.getPlid(), selectedSegmentsExperienceId));
		renderLayoutStructureTag.setPageContext(
			new MockPageContext(
				null, mockHttpServletRequest, mockHttpServletResponse));

		return renderLayoutStructureTag;
	}

	private RenderLayoutStructureTag
		_getRenderLayoutStructureTagDefaultSegmentsExperience(
			Layout layout, MockHttpServletRequest mockHttpServletRequest,
			MockHttpServletResponse mockHttpServletResponse) {

		return _getRenderLayoutStructureTag(
			layout, mockHttpServletRequest, mockHttpServletResponse,
			_segmentsExperienceLocalService.fetchDefaultSegmentsExperienceId(
				layout.getPlid()));
	}

	private String _read(String fileName) throws Exception {
		Class<?> clazz = getClass();

		return StringUtil.read(
			clazz.getResourceAsStream("dependencies/" + fileName));
	}

	private MockHttpServletResponse _renderLayout(
			Layout layout, MockHttpServletRequest mockHttpServletRequest)
		throws Exception {

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		RenderLayoutStructureTag renderLayoutStructureTag =
			_getRenderLayoutStructureTagDefaultSegmentsExperience(
				layout, mockHttpServletRequest, mockHttpServletResponse);

		renderLayoutStructureTag.doTag(
			mockHttpServletRequest, mockHttpServletResponse);

		return mockHttpServletResponse;
	}

	private void _testPagination(
			String itemId, JSONObject jsonObject, Layout layout,
			int numberOfItemsPerPage, int numberOfPages,
			long segmentsExperienceId, List<String> strings)
		throws Exception {

		jsonObject.put("paginationType", "numeric");

		_updateItemConfig(
			itemId, jsonObject, layout.fetchDraftLayout(),
			segmentsExperienceId);

		ContentLayoutTestUtil.publishLayout(layout.fetchDraftLayout(), layout);

		for (int i = 1; i <= numberOfPages; i++) {
			_assertPagination(
				itemId, layout, i, numberOfItemsPerPage, numberOfPages,
				"numeric", strings);
		}

		jsonObject.put("paginationType", "simple");

		_updateItemConfig(
			itemId, jsonObject, layout.fetchDraftLayout(),
			segmentsExperienceId);

		ContentLayoutTestUtil.publishLayout(layout.fetchDraftLayout(), layout);

		for (int i = 1; i <= numberOfPages; i++) {
			_assertPagination(
				itemId, layout, i, numberOfItemsPerPage, numberOfPages,
				"simple", strings);
		}
	}

	private void _updateItemConfig(
			String itemId, JSONObject jsonObject, Layout layout,
			long segmentsExperienceId)
		throws Exception {

		LayoutStructure layoutStructure =
			_layoutStructureProvider.getLayoutStructure(
				layout.getPlid(), segmentsExperienceId);

		LayoutStructureItem layoutStructureItem =
			layoutStructure.getLayoutStructureItem(itemId);

		JSONObject itemConfigJSONObject =
			layoutStructureItem.getItemConfigJSONObject();

		for (String key : jsonObject.keySet()) {
			itemConfigJSONObject.put(key, jsonObject.get(key));
		}

		layoutStructureItem.updateItemConfig(itemConfigJSONObject);

		_layoutPageTemplateStructureLocalService.
			updateLayoutPageTemplateStructureData(
				_group.getGroupId(), layout.getPlid(), segmentsExperienceId,
				layoutStructure.toString());
	}

	private static final int _COUNT_FRAGMENT_ENTRY_LINKS = 5;

	private static final int _COUNT_INFO_LIST_ITEMS = 5;

	private static final Pattern _inputJSONObjectPattern = Pattern.compile(
		"<p>InputJSONObject:(.*?)<\\/p>");

	@Inject(filter = "ddm.form.deserializer.type=json")
	private static DDMFormDeserializer _jsonDDMFormDeserializer;

	@Inject
	private AssetEntryLocalService _assetEntryLocalService;

	@Inject
	private AssetListEntryLocalService _assetListEntryLocalService;

	@Inject
	private AssetListEntrySegmentsEntryRelLocalService
		_assetListEntrySegmentsEntryRelLocalService;

	@Inject
	private CompanyLocalService _companyLocalService;

	@Inject(
		filter = "info.item.capability.key=" + DisplayPageInfoItemCapability.KEY
	)
	private InfoItemCapability _displayPageInfoItemCapability;

	@Inject
	private DLAppLocalService _dlAppLocalService;

	@Inject
	private DLURLHelper _dlURLHelper;

	@Inject(
		filter = "info.item.capability.key=" + EditPageInfoItemCapability.KEY
	)
	private InfoItemCapability _editPageInfoItemCapability;

	@Inject
	private EntityCache _entityCache;

	@Inject
	private FragmentCollectionContributorRegistry
		_fragmentCollectionContributorRegistry;

	@Inject
	private FragmentCollectionLocalService _fragmentCollectionLocalService;

	@Inject
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Inject
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private GroupLocalService _groupLocalService;

	@Inject(
		filter = "component.name=com.liferay.journal.web.internal.layout.display.page.JournalArticleLayoutDisplayPageProvider"
	)
	private LayoutDisplayPageProvider<JournalArticle>
		_journalArticleLayoutDisplayPageProvider;

	@Inject
	private JournalArticleLocalService _journalArticleLocalService;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

	@Inject
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Inject
	private LayoutStructureProvider _layoutStructureProvider;

	@Inject
	private MultiVMPool _multiVMPool;

	@Inject
	private Portal _portal;

	@Inject(
		filter = "segments.criteria.contributor.key=user",
		type = SegmentsCriteriaContributor.class
	)
	private SegmentsCriteriaContributor _segmentsCriteriaContributor;

	@Inject
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	private ServiceContext _serviceContext;

}