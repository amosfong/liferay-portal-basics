/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.rest.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.journal.constants.JournalFolderConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.field.util.ObjectFieldUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManager;
import com.liferay.object.rest.test.util.ObjectEntryTestUtil;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineHelper;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.HTTPTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.version.Version;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.search.engine.ConnectionInformation;
import com.liferay.portal.search.engine.NodeInformation;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.rest.client.pagination.Page;
import com.liferay.portal.search.rest.dto.v1_0.FacetConfiguration;
import com.liferay.portal.search.rest.dto.v1_0.SearchRequestBody;
import com.liferay.portal.search.rest.dto.v1_0.SearchResult;
import com.liferay.portal.search.rest.pagination.SearchPage;
import com.liferay.portal.test.rule.FeatureFlags;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.search.experiences.model.SXPBlueprint;
import com.liferay.search.experiences.service.SXPBlueprintLocalService;

import java.net.URLEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.time.DateFormatUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Petteri Karttunen
 * @author Almir Ferreira
 */
@FeatureFlags({"LPD-11232", "LPS-179669"})
@RunWith(Arquillian.class)
public class SearchResultResourceTest extends BaseSearchResultResourceTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_locale = LocaleUtil.getSiteDefault();

		_ddmStructure = _addJournalArticleDDMStructure(_locale);

		_searchEngine = _searchEngineHelper.getSearchEngine();

		_user = TestPropsValues.getUser();

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			testGroup, _user.getUserId());

		_assetCategory = _addAssetCategory(_serviceContext, _user);
		_assetTag = _addAssetTag(_serviceContext, _user);

		_journalArticle = _addJournalArticle(
			_assetCategory, _assetTag, _serviceContext, _user);
	}

	@Override
	@Test
	public void testGetSearchPage() throws Exception {
		String scope = String.valueOf(testGroup.getGroupId());

		Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult> page =
			searchResultResource.getSearchPage(
				null, true, null, scope, null, null,
				com.liferay.portal.search.rest.client.pagination.Pagination.of(
					1, 10),
				null);

		long totalCount = page.getTotalCount();

		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult1 = testGetSearchPage_addSearchResult(
				randomSearchResult());

		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult2 = testGetSearchPage_addSearchResult(
				randomSearchResult());

		page = searchResultResource.getSearchPage(
			null, true, null, scope, null, null,
			com.liferay.portal.search.rest.client.pagination.Pagination.of(
				1, 10),
			null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			searchResult1,
			(List<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>)
				page.getItems());
		assertContains(
			searchResult2,
			(List<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>)
				page.getItems());
		assertValid(page, testGetSearchPage_getExpectedActions());
	}

	@Override
	@Test
	public void testGetSearchPageWithFilterDateTimeEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult1 = randomSearchResult();

		searchResult1 = testGetSearchPage_addSearchResult(searchResult1);

		for (EntityField entityField : entityFields) {
			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				page = searchResultResource.getSearchPage(
					null, null, null, String.valueOf(testGroup.getGroupId()),
					searchResult1.getTitle(),
					getFilterString(entityField, "between", searchResult1),
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(1, 2),
					null);

			assertEquals(
				Collections.singletonList(searchResult1),
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)page.getItems());
		}
	}

	@Override
	@Test
	public void testGetSearchPageWithPagination() throws Exception {
		Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
			searchResultPage = searchResultResource.getSearchPage(
				null, true, null, null, null, null, null, null);

		int totalCount = GetterUtil.getInteger(
			searchResultPage.getTotalCount());

		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult1 = testGetSearchPage_addSearchResult(
				randomSearchResult());

		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult2 = testGetSearchPage_addSearchResult(
				randomSearchResult());

		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult3 = testGetSearchPage_addSearchResult(
				randomSearchResult());

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				page1 = searchResultResource.getSearchPage(
					null, true, null, null, null, null,
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit),
					null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				searchResult1,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)page1.getItems());

			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				page2 = searchResultResource.getSearchPage(
					null, true, null, null, null, null,
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit),
					null);

			assertContains(
				searchResult2,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)page2.getItems());

			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				page3 = searchResultResource.getSearchPage(
					null, true, null, null, null, null,
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit),
					null);

			assertContains(
				searchResult3,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)page3.getItems());
		}
		else {
			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				page1 = searchResultResource.getSearchPage(
					null, true, null, null, null, null,
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(1, totalCount + 2),
					null);

			List<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				searchResults1 =
					(List
						<com.liferay.portal.search.rest.client.dto.v1_0.
							SearchResult>)page1.getItems();

			Assert.assertEquals(
				searchResults1.toString(), totalCount + 2,
				searchResults1.size());

			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				page2 = searchResultResource.getSearchPage(
					null, true, null, null, null, null,
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(2, totalCount + 2),
					null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				searchResults2 =
					(List
						<com.liferay.portal.search.rest.client.dto.v1_0.
							SearchResult>)page2.getItems();

			Assert.assertEquals(
				searchResults2.toString(), 1, searchResults2.size());

			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				page3 = searchResultResource.getSearchPage(
					null, true, null, null, null, null,
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(1, (int)totalCount + 3),
					null);

			assertContains(
				searchResult1,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)page3.getItems());
			assertContains(
				searchResult2,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)page3.getItems());
			assertContains(
				searchResult3,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)page3.getItems());
		}
	}

	@Override
	@Test
	public void testGetSearchPageWithSortInteger() throws Exception {
	}

	@Override
	@Test
	public void testPostSearchPage() throws Exception {
		_testPostSearchPageAggregationNameAsFacetName();
		_testPostSearchPageWithCategoryTreeFacetConfiguration();
		_testPostSearchPageWithCustomFacetConfiguration();
		_testPostSearchPageWithDateRangeFacetConfiguration();
		_testPostSearchPageWithEmbeddedNestedFields();
		_testPostSearchPageWithEmptyScope();
		_testPostSearchPageWithFaultyScope();
		_testPostSearchPageWithFilter();
		_testPostSearchPageWithFolderFacetConfiguration();
		_testPostSearchPageWithGroupERCAndGroupIdScope();
		_testPostSearchPageWithGroupERCScope();
		_testPostSearchPageWithGroupIdScope();
		_testPostSearchPageWithHighlightConfiguration();
		_testPostSearchPageWithLocalizedTextObjectField();
		_testPostSearchPageWithKeywords();
		_testPostSearchPageWithMultipleGroupIdsScope();
		_testPostSearchPageWithNestedFacetConfiguration();
		_testPostSearchPageWithSiteFacetConfiguration();
		_testPostSearchPageWithTagFacetConfiguration();
		_testPostSearchPageWithTypeFacetConfiguration();
		_testPostSearchPageWithUserFacetConfiguration();
		_testPostSearchPageWithoutHighlightConfiguration();
		_testPostSearchPageZeroResults();
	}

	@Test
	public void testSearchEndpointRedirect() throws Exception {
		_baseURI = "portal-search-rest";

		testPostSearchPage();
	}

	@Override
	protected com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			testGetSearchPage_addSearchResult(
				com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
					searchResult)
		throws Exception {

		JournalTestUtil.addArticle(
			testGroup.getGroupId(), searchResult.getTitle(),
			searchResult.getDescription());

		return searchResult;
	}

	@Override
	protected void testGetSearchPageWithFilter(
			String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult1 = testGetSearchPage_addSearchResult(
				randomSearchResult());

		for (EntityField entityField : entityFields) {
			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				page = searchResultResource.getSearchPage(
					null, true, null, null, null,
					getFilterString(entityField, operator, searchResult1),
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(1, 2),
					null);

			assertEquals(
				Collections.singletonList(searchResult1),
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)page.getItems());
		}
	}

	@Override
	protected void testGetSearchPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField,
				 com.liferay.portal.search.rest.client.dto.v1_0.SearchResult,
				 com.liferay.portal.search.rest.client.dto.v1_0.SearchResult,
				 Exception> unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult1 = randomSearchResult();
		com.liferay.portal.search.rest.client.dto.v1_0.SearchResult
			searchResult2 = randomSearchResult();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, searchResult1, searchResult2);
		}

		searchResult1 = testGetSearchPage_addSearchResult(searchResult1);

		searchResult2 = testGetSearchPage_addSearchResult(searchResult2);

		Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult> page =
			searchResultResource.getSearchPage(
				null, true, null, null, null, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				ascPage = searchResultResource.getSearchPage(
					null, true, null, null, null, null,
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(1, (int)page.getTotalCount() + 1),
					entityField.getName() + ":asc");

			assertContains(
				searchResult1,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)ascPage.getItems());
			assertContains(
				searchResult2,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)ascPage.getItems());

			Page<com.liferay.portal.search.rest.client.dto.v1_0.SearchResult>
				descPage = searchResultResource.getSearchPage(
					null, true, null, null, null, null,
					com.liferay.portal.search.rest.client.pagination.Pagination.
						of(1, (int)page.getTotalCount() + 1),
					entityField.getName() + ":desc");

			assertContains(
				searchResult2,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)descPage.getItems());
			assertContains(
				searchResult1,
				(List
					<com.liferay.portal.search.rest.client.dto.v1_0.
						SearchResult>)descPage.getItems());
		}
	}

	private AssetCategory _addAssetCategory(
			ServiceContext serviceContext, User user)
		throws Exception {

		AssetVocabulary assetVocabulary =
			_assetVocabularyLocalService.addDefaultVocabulary(
				testGroup.getGroupId());

		return _assetCategoryLocalService.addCategory(
			user.getUserId(), testGroup.getGroupId(), StringUtil.randomString(),
			assetVocabulary.getVocabularyId(), serviceContext);
	}

	private AssetTag _addAssetTag(ServiceContext serviceContext, User user)
		throws Exception {

		return _assetTagLocalService.addTag(
			null, user.getUserId(), testGroup.getGroupId(),
			StringUtil.randomString(), serviceContext);
	}

	private JournalArticle _addJournalArticle(
			AssetCategory assetCategory, AssetTag assetTag,
			ServiceContext serviceContext, User user)
		throws Exception {

		JournalFolder journalFolder = _journalFolderLocalService.addFolder(
			null, user.getUserId(), testGroup.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			StringUtil.randomString(), StringPool.BLANK, serviceContext);

		return JournalTestUtil.addArticle(
			testGroup.getGroupId(), journalFolder.getFolderId(),
			ServiceContextTestUtil.getServiceContext(
				testGroup.getGroupId(), user.getUserId(),
				new long[] {assetCategory.getCategoryId()},
				new String[] {assetTag.getName()}));
	}

	private DDMStructure _addJournalArticleDDMStructure(Locale locale)
		throws Exception {

		Class<JournalArticle> clazz = JournalArticle.class;

		return DDMStructureTestUtil.addStructure(
			testGroup.getGroupId(), clazz.getName(),
			DDMStructureTestUtil.getSampleDDMForm(
				"name", "string", "keyword", true, "text",
				new Locale[] {locale}, locale),
			locale);
	}

	private void _addJournalArticleWithDDMStructure() throws Exception {
		_journalArticleLocalService.addArticle(
			null, _user.getUserId(), testGroup.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			HashMapBuilder.put(
				_locale, StringUtil.randomString()
			).build(),
			HashMapBuilder.put(
				_locale, StringUtil.randomString()
			).build(),
			DDMStructureTestUtil.getSampleStructuredContent("test"),
			_ddmStructure.getStructureId(), null, _serviceContext);
	}

	private SXPBlueprint _addSXPBlueprint(boolean highlightingEnabled)
		throws Exception {

		JSONObject configurationJSONObject = JSONUtil.put(
			"advancedConfiguration",
			JSONUtil.put(
				"source",
				JSONUtil.put(
					"fetchSource", true
				).put(
					"includes",
					JSONFactoryUtil.createJSONArray(
					).put(
						"fullName"
					)
				))
		).put(
			"generalConfiguration",
			JSONUtil.put(
				"searchableAssetTypes",
				JSONUtil.put("com.liferay.portal.kernel.model.User"))
		).put(
			"queryConfiguration", JSONUtil.put("applyIndexerClauses", true)
		);

		if (highlightingEnabled) {
			configurationJSONObject.put(
				"highlightConfiguration",
				_createSXPBlueprintHighlightConfigurationJSON());
		}

		return _sxpBlueprintLocalService.addSXPBlueprint(
			null, _user.getUserId(), configurationJSONObject.toString(),
			Collections.singletonMap(_locale, StringPool.BLANK), null,
			StringPool.BLANK,
			Collections.singletonMap(_locale, RandomTestUtil.randomString()),
			_serviceContext);
	}

	private SearchPage<SearchResult> _assertFacetConfiguration(
			boolean anyMatch, String entryClassNames,
			Map<String, Object> facetAttributes, String facetName,
			Object facetValues, String... expectedValues)
		throws Exception {

		SearchPage<SearchResult> searchPage =
			_postSearchPageWithFacetConfiguration(
				entryClassNames,
				new FacetConfiguration() {
					{
						attributes = facetAttributes;
						frequencyThreshold = 1;
						name = facetName;
						values = new Object[] {facetValues};
					}
				});

		Map<String, Object> searchFacets =
			(Map<String, Object>)searchPage.getSearchFacets();

		Assert.assertNotNull(searchFacets);

		Assert.assertTrue(searchFacets.containsKey(facetName));

		List<String> termValuesList = new ArrayList<>();

		JSONArray termJSONArray = (JSONArray)searchFacets.get(facetName);

		for (int i = 0; i < termJSONArray.length(); i++) {
			JSONObject termJSONObject = _jsonFactory.createJSONObject(
				termJSONArray.getString(i));

			Assert.assertTrue(termJSONObject.has("displayName"));
			Assert.assertTrue(termJSONObject.has("frequency"));
			Assert.assertTrue(termJSONObject.has("term"));

			termValuesList.add(termJSONObject.getString("term"));
		}

		String[] termValues = termValuesList.toArray(new String[0]);

		Arrays.sort(expectedValues);
		Arrays.sort(termValues);

		if (anyMatch) {
			Assert.assertFalse(
				Collections.disjoint(
					Arrays.asList(expectedValues), Arrays.asList(termValues)));
		}
		else {
			Assert.assertArrayEquals(expectedValues, termValues);
		}

		return searchPage;
	}

	private void _assertSearchResultTitles(
		SearchPage<SearchResult> searchPage, String... expectedValues) {

		List<SearchResult> searchResults = ListUtil.fromCollection(
			searchPage.getItems());

		List<String> titles = new ArrayList<>();

		for (SearchResult searchResult : searchResults) {
			titles.add(searchResult.getTitle());
		}

		Arrays.sort(expectedValues);

		Collections.sort(titles);

		Assert.assertEquals(
			Arrays.toString(expectedValues), String.valueOf(titles));
	}

	private JSONObject _createSXPBlueprintHighlightConfigurationJSON() {
		return JSONUtil.put(
			"fields",
			JSONUtil.put(
				"fullName",
				JSONUtil.put(
					"fragment_size", 100
				).put(
					"number_of_fragments", 10
				))
		).put(
			"post_tags",
			JSONFactoryUtil.createJSONArray(
			).put(
				"</liferay-hl>"
			)
		).put(
			"pre_tags",
			JSONFactoryUtil.createJSONArray(
			).put(
				"<liferay-hl>"
			)
		).put(
			"require_field_match", true
		);
	}

	private String _getEndpoint(
			String entryClassNames, String filterString, String keywords,
			String nestedFields, String scope)
		throws Exception {

		String endpoint = _baseURI + "/v1.0/search?";

		if (!Validator.isBlank(entryClassNames)) {
			endpoint +=
				"&entryClassNames=" +
					URLEncoder.encode(entryClassNames, StringPool.UTF8);
		}

		if (!Validator.isBlank(filterString)) {
			endpoint +=
				"&filter=" + URLEncoder.encode(filterString, StringPool.UTF8);
		}

		if (!Validator.isBlank(nestedFields)) {
			endpoint +=
				"&nestedFields=" +
					URLEncoder.encode(nestedFields, StringPool.UTF8);
		}

		if (!Validator.isBlank(scope)) {
			endpoint += "&scope=" + URLEncoder.encode(scope, StringPool.UTF8);
		}

		if (!Validator.isBlank(keywords)) {
			endpoint +=
				"&search=" + URLEncoder.encode(keywords, StringPool.UTF8);
		}

		return endpoint;
	}

	private Version _getSearchEngineVersion() {
		List<ConnectionInformation> connectionInformationList =
			_searchEngineInformation.getConnectionInformationList();

		ConnectionInformation connectionInformation =
			connectionInformationList.get(0);

		List<NodeInformation> nodeInformationList =
			connectionInformation.getNodeInformationList();

		NodeInformation nodeInformation = nodeInformationList.get(0);

		return Version.parseVersion(nodeInformation.getVersion());
	}

	private Map<String, JSONArray> _getSearchFacets(JSONObject jsonObject) {
		JSONObject searchFacetsJSONObject = jsonObject.getJSONObject(
			"searchFacets");

		if (searchFacetsJSONObject == null) {
			return null;
		}

		Map<String, JSONArray> map = new HashMap<>();

		Iterator<String> iterator = searchFacetsJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			map.put(key, searchFacetsJSONObject.getJSONArray(key));
		}

		return map;
	}

	private String _getUserHighlightedFullName() {
		Version version = _getSearchEngineVersion();

		if (_isSearchEngineElasticsearch() &&
			(version.compareTo(Version.parseVersion("8.10.2")) >= 0)) {

			return StringBundler.concat(
				HighlightUtil.HIGHLIGHT_TAG_OPEN, _user.getFirstName(),
				StringPool.SPACE, _user.getLastName(),
				HighlightUtil.HIGHLIGHT_TAG_CLOSE);
		}

		return StringBundler.concat(
			HighlightUtil.HIGHLIGHT_TAG_OPEN, _user.getFirstName(),
			HighlightUtil.HIGHLIGHT_TAG_CLOSE, StringPool.SPACE,
			HighlightUtil.HIGHLIGHT_TAG_OPEN, _user.getLastName(),
			HighlightUtil.HIGHLIGHT_TAG_CLOSE);
	}

	private boolean _isSearchEngineElasticsearch() {
		return Objects.equals(
			_searchEngineInformation.getVendorString(), "Elasticsearch");
	}

	private SearchPage<SearchResult> _postSearchPage(String keywords)
		throws Exception {

		return _postSearchPage(
			null, null, keywords, null, String.valueOf(testGroup.getGroupId()),
			new SearchRequestBody());
	}

	private SearchPage<SearchResult> _postSearchPage(
			String keywords, String scope)
		throws Exception {

		return _postSearchPage(
			null, null, keywords, null, scope, new SearchRequestBody());
	}

	private SearchPage<SearchResult> _postSearchPage(
			String entryClassNames, String filterString, String keywords,
			String nestedFields, String scope,
			SearchRequestBody searchRequestBody)
		throws Exception {

		return _toSearchPage(
			HTTPTestUtil.invokeToJSONObject(
				searchRequestBody.toString(),
				_getEndpoint(
					entryClassNames, filterString, keywords, nestedFields,
					scope),
				Http.Method.POST));
	}

	private SearchPage<SearchResult> _postSearchPageWithFacetConfiguration(
			String entryClassNames, FacetConfiguration facetConfiguration)
		throws Exception {

		SearchRequestBody searchRequestBody = new SearchRequestBody() {
			{
				attributes = HashMapBuilder.<String, Object>put(
					"search.empty.search", true
				).build();

				facetConfigurations = new FacetConfiguration[] {
					facetConfiguration
				};
			}
		};

		return _postSearchPage(
			entryClassNames, null, null, null,
			String.valueOf(testGroup.getGroupId()), searchRequestBody);
	}

	private SearchPage<SearchResult>
			_postSearchPageWithSXPBlueprintConfiguration(
				String entryClassNames, String keywords,
				SXPBlueprint sxpBlueprint)
		throws Exception {

		SearchRequestBody searchRequestBody = new SearchRequestBody() {
			{
				attributes = HashMapBuilder.<String, Object>put(
					"search.experiences.blueprint.external.reference.code",
					sxpBlueprint.getExternalReferenceCode()
				).build();
			}
		};

		return _postSearchPage(
			entryClassNames, null, keywords, null, null, searchRequestBody);
	}

	private void _testPostSearchPageAggregationNameAsFacetName()
		throws Exception {

		String facetAggregationName = StringUtil.randomString();

		SearchPage<SearchResult> searchPage1 =
			_postSearchPageWithFacetConfiguration(
				null,
				new FacetConfiguration() {
					{
						aggregationName = facetAggregationName;
						name = "tag";
					}
				});

		Map<String, Object> map1 =
			(Map<String, Object>)searchPage1.getSearchFacets();

		Assert.assertTrue(map1.containsKey(facetAggregationName));

		SearchPage<SearchResult> searchPage2 =
			_postSearchPageWithFacetConfiguration(
				null,
				new FacetConfiguration() {
					{
						name = "tag";
					}
				});

		Map<String, Object> map2 =
			(Map<String, Object>)searchPage2.getSearchFacets();

		Assert.assertTrue(map2.containsKey("tag"));
	}

	private void _testPostSearchPageWithCategoryTreeFacetConfiguration()
		throws Exception {

		_assertFacetConfiguration(
			false, null,
			HashMapBuilder.<String, Object>put(
				"mode", "tree"
			).put(
				"vocabularyIds",
				new String[] {String.valueOf(_assetCategory.getVocabularyId())}
			).build(),
			"category", _assetCategory.getCategoryId(),
			String.valueOf(_assetCategory.getCategoryId()));
	}

	private void _testPostSearchPageWithCustomFacetConfiguration()
		throws Exception {

		_assertFacetConfiguration(
			false, null,
			HashMapBuilder.<String, Object>put(
				"field", Field.COMPANY_ID
			).build(),
			"custom", testCompany.getCompanyId(),
			String.valueOf(testCompany.getCompanyId()));
	}

	private void _testPostSearchPageWithDateRangeFacetConfiguration()
		throws Exception {

		LocalDateTime startOfDayLocalDateTime = LocalDateTime.of(
			LocalDate.now(), LocalTime.MIN);

		JSONArray rangesJSONArray = _jsonFactory.createJSONArray();

		String range = StringBundler.concat(
			StringPool.OPEN_BRACKET,
			DateFormatUtils.format(
				Date.from(
					startOfDayLocalDateTime.toInstant(ZoneOffset.ofHours(0))),
				"yyyyMMddHHmmss"),
			" TO ", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"),
			StringPool.CLOSE_BRACKET);

		rangesJSONArray.put(
			JSONUtil.put(
				"label", range
			).put(
				"range", range
			));

		SearchPage<SearchResult> searchPage = _assertFacetConfiguration(
			false, null,
			HashMapBuilder.<String, Object>put(
				"field", "modified"
			).put(
				"format", "yyyyMMddHHmmss"
			).put(
				"ranges", rangesJSONArray
			).build(),
			"date-range", range, range);

		Map<String, Object> searchFacets =
			(Map<String, Object>)searchPage.getSearchFacets();

		JSONArray termJSONArray = (JSONArray)searchFacets.get("date-range");

		Assert.assertEquals(
			range,
			_jsonFactory.createJSONObject(
				termJSONArray.getString(0)
			).getString(
				"displayName"
			));
	}

	private void _testPostSearchPageWithEmbeddedNestedFields()
		throws Exception {

		if (Objects.equals(_searchEngine.getVendor(), "Solr")) {
			return;
		}

		ObjectField objectField = ObjectFieldUtil.createObjectField(
			"Text", "String", true, true, null,
			StringUtil.toLowerCase(RandomTestUtil.randomString()), "test",
			false);

		objectField.setExternalReferenceCode(RandomTestUtil.randomString());

		ObjectDefinition objectDefinition =
			ObjectDefinitionTestUtil.publishObjectDefinition(
				Collections.singletonList(objectField));

		ObjectEntryTestUtil.addObjectEntry(
			objectDefinition, "test", RandomTestUtil.randomString());

		SearchPage<SearchResult> searchPage = _postSearchPage(
			objectDefinition.getClassName(), null,
			objectDefinition.getUserName(), "embedded", null,
			new SearchRequestBody());

		Collection<SearchResult> searchResults = searchPage.getItems();

		Assert.assertFalse(searchResults.isEmpty());

		for (SearchResult searchResult : searchResults) {
			Assert.assertNotNull(searchResult.getEmbedded());
		}
	}

	private void _testPostSearchPageWithEmptyScope() throws Exception {
		Group group = GroupTestUtil.addGroup();

		JournalArticle journalArticle = JournalTestUtil.addArticle(
			group.getGroupId(), StringUtil.randomString(),
			StringUtil.randomString());

		SearchPage<SearchResult> searchPage = _postSearchPage(
			StringBundler.concat(
				_journalArticle.getTitle(_locale), StringPool.SPACE,
				journalArticle.getTitle(_locale)),
			null);

		_assertSearchResultTitles(
			searchPage, _journalArticle.getTitle(_locale),
			journalArticle.getTitle(_locale));

		GroupTestUtil.deleteGroup(group);
	}

	private void _testPostSearchPageWithFaultyScope() throws Exception {
		SearchPage<SearchResult> searchPage = _postSearchPage(
			_journalArticle.getArticleId(), "notexistingscope");

		_assertSearchResultTitles(searchPage, new String[0]);
	}

	private void _testPostSearchPageWithFilter() throws Exception {
		SearchPage<SearchResult> searchPage = _postSearchPage(
			null, "groupIds/any(g:g eq " + testGroup.getGroupId() + ")",
			_journalArticle.getArticleId(), null, null,
			new SearchRequestBody());

		_assertSearchResultTitles(
			searchPage, _journalArticle.getTitle(_locale));
	}

	private void _testPostSearchPageWithFolderFacetConfiguration()
		throws Exception {

		_assertFacetConfiguration(
			false, null, null, "folder", _journalArticle.getFolderId(),
			String.valueOf(_journalArticle.getFolderId()));
	}

	private void _testPostSearchPageWithGroupERCAndGroupIdScope()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		JournalArticle journalArticle = JournalTestUtil.addArticle(
			group.getGroupId(), StringUtil.randomString(),
			StringUtil.randomString());

		SearchPage<SearchResult> searchPage = _postSearchPage(
			StringBundler.concat(
				_journalArticle.getTitle(_locale), StringPool.SPACE,
				journalArticle.getTitle(_locale)),
			StringBundler.concat(
				testGroup.getGroupId(), StringPool.COMMA,
				group.getExternalReferenceCode()));

		_assertSearchResultTitles(
			searchPage, _journalArticle.getTitle(_locale),
			journalArticle.getTitle(_locale));

		GroupTestUtil.deleteGroup(group);
	}

	private void _testPostSearchPageWithGroupERCScope() throws Exception {
		SearchPage<SearchResult> searchPage = _postSearchPage(
			_journalArticle.getArticleId(),
			String.valueOf(testGroup.getExternalReferenceCode()));

		_assertSearchResultTitles(
			searchPage, _journalArticle.getTitle(_locale));
	}

	private void _testPostSearchPageWithGroupIdScope() throws Exception {
		SearchPage<SearchResult> searchPage = _postSearchPage(
			_journalArticle.getArticleId(),
			String.valueOf(testGroup.getGroupId()));

		_assertSearchResultTitles(
			searchPage, _journalArticle.getTitle(_locale));
	}

	private void _testPostSearchPageWithHighlightConfiguration()
		throws Exception {

		if (Objects.equals(_searchEngine.getVendor(), "Solr")) {
			return;
		}

		SearchPage<SearchResult> searchPage =
			_postSearchPageWithSXPBlueprintConfiguration(
				_user.getModelClassName(), _user.getFullName(),
				_addSXPBlueprint(true));

		List<SearchResult> searchResults = ListUtil.fromCollection(
			searchPage.getItems());

		Assert.assertFalse(searchResults.isEmpty());

		int count = ListUtil.count(
			searchResults,
			searchResult -> Objects.equals(
				searchResult.getTitle(), _getUserHighlightedFullName()));

		Assert.assertTrue(count >= 1);
	}

	private void _testPostSearchPageWithKeywords() throws Exception {
		SearchPage<SearchResult> searchPage = _postSearchPage(
			_journalArticle.getArticleId());

		Assert.assertEquals(1L, searchPage.getPage());
		Assert.assertEquals(1L, searchPage.getTotalCount());
	}

	private void _testPostSearchPageWithLocalizedTextObjectField()
		throws Exception {

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.addCustomObjectDefinition(
				_user.getUserId(), 0, null, false, true, true, false,
				LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString()),
				ObjectDefinitionTestUtil.getRandomName(), null, null,
				LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString()),
				true, ObjectDefinitionConstants.SCOPE_COMPANY,
				ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT,
				Arrays.asList(
					new TextObjectFieldBuilder(
					).labelMap(
						LocalizedMapUtil.getLocalizedMap(
							RandomTestUtil.randomString())
					).indexed(
						true
					).name(
						"localizedTextObjectFieldName"
					).localized(
						true
					).build()));

		objectDefinition =
			_objectDefinitionLocalService.publishCustomObjectDefinition(
				_user.getUserId(), objectDefinition.getObjectDefinitionId());

		DTOConverterContext dtoConverterContext =
			new DefaultDTOConverterContext(
				false, Collections.emptyMap(), _dtoConverterRegistry, null,
				LocaleUtil.getDefault(), null, TestPropsValues.getUser());

		_objectEntryManager.addObjectEntry(
			dtoConverterContext, objectDefinition,
			new ObjectEntry() {
				{
					properties = HashMapBuilder.<String, Object>put(
						"localizedTextObjectFieldName_i18n",
						HashMapBuilder.put(
							"en_US", "en_US localizedTextObjectFieldValue"
						).put(
							"pt_BR", "pt_BR localizedTextObjectFieldValue"
						).build()
					).build();
				}
			},
			ObjectDefinitionConstants.SCOPE_COMPANY);

		SearchPage<SearchResult> searchPage = _postSearchPage(
			objectDefinition.getClassName(), null, "pt_BR", "embedded", "0",
			new SearchRequestBody());

		List<SearchResult> searchResults = ListUtil.fromCollection(
			searchPage.getItems());

		Assert.assertTrue(
			searchResults.toString(
			).contains(
				"en_US localizedTextObjectFieldValue"
			));
	}

	private void _testPostSearchPageWithMultipleGroupIdsScope()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		JournalArticle journalArticle = JournalTestUtil.addArticle(
			group.getGroupId(), StringUtil.randomString(),
			StringUtil.randomString());

		SearchPage<SearchResult> searchPage = _postSearchPage(
			StringBundler.concat(
				_journalArticle.getTitle(_locale), StringPool.SPACE,
				journalArticle.getTitle(_locale)),
			StringBundler.concat(
				testGroup.getGroupId(), StringPool.COMMA, group.getGroupId()));

		_assertSearchResultTitles(
			searchPage, _journalArticle.getTitle(_locale),
			journalArticle.getTitle(_locale));

		GroupTestUtil.deleteGroup(group);
	}

	private void _testPostSearchPageWithNestedFacetConfiguration()
		throws Exception {

		_addJournalArticleWithDDMStructure();

		if (Objects.equals(_searchEngine.getVendor(), "Solr")) {
			return;
		}

		_assertFacetConfiguration(
			false, null,
			HashMapBuilder.<String, Object>put(
				"field",
				"ddmFieldArray.ddmFieldValueKeyword_" +
					LocaleUtil.toLanguageId(_locale)
			).put(
				"filterField", "ddmFieldArray.ddmFieldName"
			).put(
				"filterValue",
				StringBundler.concat(
					"ddm__keyword__", _ddmStructure.getStructureId(), "__name_",
					LocaleUtil.toLanguageId(_locale))
			).put(
				"path", "ddmFieldArray"
			).build(),
			"nested", "test", "test");
	}

	private void _testPostSearchPageWithoutHighlightConfiguration()
		throws Exception {

		if (Objects.equals(_searchEngine.getVendor(), "Solr")) {
			return;
		}

		SearchPage<SearchResult> searchPage =
			_postSearchPageWithSXPBlueprintConfiguration(
				_user.getModelClassName(), _user.getFullName(),
				_addSXPBlueprint(false));

		List<SearchResult> searchResults = ListUtil.fromCollection(
			searchPage.getItems());

		Assert.assertFalse(searchResults.isEmpty());

		int count = ListUtil.count(
			searchResults,
			searchResult -> Objects.equals(
				searchResult.getTitle(), _user.getFullName()));

		Assert.assertTrue(count >= 1);

		Assert.assertEquals(
			0,
			ListUtil.count(
				searchResults,
				searchResult -> Objects.equals(
					searchResult.getTitle(), _getUserHighlightedFullName())));
	}

	private void _testPostSearchPageWithSiteFacetConfiguration()
		throws Exception {

		_assertFacetConfiguration(
			true, null, null, "site", testGroup.getGroupId(),
			String.valueOf(testGroup.getGroupId()));
	}

	private void _testPostSearchPageWithTagFacetConfiguration()
		throws Exception {

		_assertFacetConfiguration(
			true, null, null, "tag", _assetTag.getName(), _assetTag.getName());
	}

	private void _testPostSearchPageWithTypeFacetConfiguration()
		throws Exception {

		_assertFacetConfiguration(
			false,
			StringBundler.concat(
				JournalArticle.class.getName(), StringPool.COMMA,
				JournalFolder.class.getName(), StringPool.COMMA,
				User.class.getName()),
			null, "type", StringPool.BLANK, JournalArticle.class.getName(),
			JournalFolder.class.getName(), User.class.getName());
	}

	private void _testPostSearchPageWithUserFacetConfiguration()
		throws Exception {

		_assertFacetConfiguration(
			true, null, null, "user", _user.getUserId(),
			String.valueOf(_user.getUserId()));
	}

	private void _testPostSearchPageZeroResults() throws Exception {
		SearchPage<SearchResult> searchPage = _postSearchPage(
			"shouldnotmatchanything");

		Assert.assertEquals(0L, searchPage.getTotalCount());
	}

	private SearchPage<SearchResult> _toSearchPage(JSONObject jsonObject)
		throws Exception {

		return SearchPage.of(
			null, null, _getSearchFacets(jsonObject),
			JSONUtil.toList(
				jsonObject.getJSONArray("items"),
				itemJSONObject -> SearchResult.toDTO(
					itemJSONObject.toString())),
			Pagination.of(
				jsonObject.getInt("page"), jsonObject.getInt("pageSize")),
			jsonObject.getLong("totalCount"));
	}

	@Inject
	private static DTOConverterRegistry _dtoConverterRegistry;

	@Inject
	private static ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Inject(
		filter = "object.entry.manager.storage.type=" + ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT
	)
	private static ObjectEntryManager _objectEntryManager;

	private AssetCategory _assetCategory;

	@Inject
	private AssetCategoryLocalService _assetCategoryLocalService;

	private AssetTag _assetTag;

	@Inject
	private AssetTagLocalService _assetTagLocalService;

	@Inject
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	private String _baseURI = "search";
	private DDMStructure _ddmStructure;
	private JournalArticle _journalArticle;

	@Inject
	private JournalArticleLocalService _journalArticleLocalService;

	@Inject
	private JournalFolderLocalService _journalFolderLocalService;

	@Inject
	private JSONFactory _jsonFactory;

	private Locale _locale;
	private SearchEngine _searchEngine;

	@Inject
	private SearchEngineHelper _searchEngineHelper;

	@Inject
	private SearchEngineInformation _searchEngineInformation;

	private ServiceContext _serviceContext;

	@Inject
	private SXPBlueprintLocalService _sxpBlueprintLocalService;

	private User _user;

}