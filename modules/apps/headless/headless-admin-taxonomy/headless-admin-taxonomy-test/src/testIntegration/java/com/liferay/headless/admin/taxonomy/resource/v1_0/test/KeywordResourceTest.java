/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.taxonomy.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.test.util.AssetTestUtil;
import com.liferay.headless.admin.taxonomy.client.dto.v1_0.Keyword;
import com.liferay.headless.admin.taxonomy.client.pagination.Page;
import com.liferay.headless.admin.taxonomy.client.pagination.Pagination;
import com.liferay.headless.admin.taxonomy.client.problem.Problem;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.KeywordResource;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Javier Gamarra
 */
@RunWith(Arquillian.class)
public class KeywordResourceTest extends BaseKeywordResourceTestCase {

	@Override
	@Test
	public void testDeleteAssetLibraryKeywordByExternalReferenceCode()
		throws Exception {

		super.testDeleteAssetLibraryKeywordByExternalReferenceCode();

		testDeleteAssetLibraryKeywordByExternalReferenceCode_addKeyword();

		String externalReferenceCode = StringUtil.toLowerCase(
			RandomTestUtil.randomString());

		try {
			keywordResource.deleteAssetLibraryKeywordByExternalReferenceCode(
				testDeleteAssetLibraryKeywordByExternalReferenceCode_getAssetLibraryId(),
				externalReferenceCode);

			Assert.fail();
		}
		catch (Problem.ProblemException problemException) {
			Problem problem = problemException.getProblem();

			Assert.assertEquals("NOT_FOUND", problem.getStatus());
			Assert.assertNull(problem.getTitle());
		}
	}

	@Override
	@Test
	public void testDeleteSiteKeywordByExternalReferenceCode()
		throws Exception {

		super.testDeleteSiteKeywordByExternalReferenceCode();

		Keyword keyword =
			testDeleteSiteKeywordByExternalReferenceCode_addKeyword();

		String externalReferenceCode = StringUtil.toLowerCase(
			RandomTestUtil.randomString());

		try {
			keywordResource.deleteSiteKeywordByExternalReferenceCode(
				testDeleteSiteKeywordByExternalReferenceCode_getSiteId(keyword),
				externalReferenceCode);

			Assert.fail();
		}
		catch (Problem.ProblemException problemException) {
			Problem problem = problemException.getProblem();

			Assert.assertEquals("NOT_FOUND", problem.getStatus());
			Assert.assertNull(problem.getTitle());
		}
	}

	@Override
	@Test
	public void testGetAssetLibraryKeywordByExternalReferenceCode()
		throws Exception {

		super.testGetAssetLibraryKeywordByExternalReferenceCode();

		String externalReferenceCode = StringUtil.toLowerCase(
			RandomTestUtil.randomString());

		try {
			keywordResource.getAssetLibraryKeywordByExternalReferenceCode(
				testGetAssetLibraryKeywordByExternalReferenceCode_getAssetLibraryId(),
				externalReferenceCode);

			Assert.fail();
		}
		catch (Problem.ProblemException problemException) {
			Problem problem = problemException.getProblem();

			Assert.assertEquals("NOT_FOUND", problem.getStatus());
			Assert.assertNull(problem.getTitle());
		}
	}

	@Override
	@Test
	public void testGetAssetLibraryKeywordsPage() throws Exception {
		super.testGetAssetLibraryKeywordsPage();

		Keyword keyword = testPostAssetLibraryKeyword_addKeyword(
			randomKeyword());

		keywordResource = KeywordResource.builder(
		).authentication(
			"test@liferay.com", PropsValues.DEFAULT_ADMIN_PASSWORD
		).locale(
			LocaleUtil.getDefault()
		).parameters(
			"fields", "name"
		).build();

		Page<Keyword> page = keywordResource.getAssetLibraryKeywordsPage(
			testDepotEntry.getDepotEntryId(), null, null, null,
			Pagination.of(1, 10), null);

		Assert.assertEquals(1, page.getTotalCount());

		assertEquals(
			new Keyword() {
				{
					name = keyword.getName();
				}
			},
			page.fetchFirstItem());

		assertValid(page);

		keywordResource = KeywordResource.builder(
		).authentication(
			"test@liferay.com", PropsValues.DEFAULT_ADMIN_PASSWORD
		).locale(
			LocaleUtil.getDefault()
		).parameters(
			"restrictFields",
			"actions,assetLibraryKey,creator,dateCreated,dateModified,name," +
				"keywordUsageCount,subscribed"
		).build();

		page = keywordResource.getAssetLibraryKeywordsPage(
			testDepotEntry.getDepotEntryId(), null, null, null,
			Pagination.of(1, 10), null);

		Assert.assertEquals(1, page.getTotalCount());

		assertEquals(
			new Keyword() {
				{
					id = keyword.getId();
				}
			},
			page.fetchFirstItem());

		assertValid(page);

		keywordResource.deleteKeyword(keyword.getId());
	}

	@Override
	@Test
	public void testGetKeyword() throws Exception {
		super.testGetKeyword();

		Keyword postKeyword = testGetKeyword_addKeyword();

		Keyword getKeyword = keywordResource.getKeyword(postKeyword.getId());

		assertValid(
			getKeyword.getActions(),
			HashMapBuilder.<String, Map<String, String>>put(
				"delete",
				HashMapBuilder.put(
					"href",
					"http://localhost:8080/o/headless-admin-taxonomy/v1.0" +
						"/keywords/" + getKeyword.getId()
				).put(
					"method", "DELETE"
				).build()
			).put(
				"get",
				HashMapBuilder.put(
					"href",
					"http://localhost:8080/o/headless-admin-taxonomy/v1.0" +
						"/keywords/" + getKeyword.getId()
				).put(
					"method", "GET"
				).build()
			).put(
				"replace",
				HashMapBuilder.put(
					"href",
					"http://localhost:8080/o/headless-admin-taxonomy/v1.0" +
						"/keywords/" + getKeyword.getId()
				).put(
					"method", "PUT"
				).build()
			).put(
				"subscribe",
				HashMapBuilder.put(
					"href",
					StringBundler.concat(
						"http://localhost:8080/o/headless-admin-taxonomy/v1.0",
						"/keywords/", getKeyword.getId(), "/subscribe")
				).put(
					"method", "PUT"
				).build()
			).put(
				"unsubscribe",
				HashMapBuilder.put(
					"href",
					StringBundler.concat(
						"http://localhost:8080/o/headless-admin-taxonomy/v1.0",
						"/keywords/", getKeyword.getId(), "/unsubscribe")
				).put(
					"method", "PUT"
				).build()
			).build());

		Keyword keyword = testGetKeyword_addKeyword();

		keywordResource = KeywordResource.builder(
		).authentication(
			"test@liferay.com", PropsValues.DEFAULT_ADMIN_PASSWORD
		).locale(
			LocaleUtil.getDefault()
		).parameters(
			"fields", "name"
		).build();

		assertEquals(
			new Keyword() {
				{
					externalReferenceCode = keyword.getExternalReferenceCode();
					name = keyword.getName();
				}
			},
			keywordResource.getKeyword(keyword.getId()));

		keywordResource.deleteKeyword(keyword.getId());
	}

	@Override
	@Test
	public void testGetKeywordsRankedPage() throws Exception {
		Page<Keyword> page = keywordResource.getKeywordsRankedPage(
			RandomTestUtil.randomString(), testGroup.getGroupId(),
			Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		Keyword keyword1 = testGetKeywordsRankedPage_addKeyword(
			randomKeyword());
		Keyword keyword2 = testGetKeywordsRankedPage_addKeyword(
			randomKeyword());

		page = keywordResource.getKeywordsRankedPage(
			null, testGroup.getGroupId(), Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(keyword1, keyword2), (List<Keyword>)page.getItems());
		assertValid(page, testGetKeywordsRankedPage_getExpectedActions());

		keywordResource.deleteKeyword(keyword1.getId());
		keywordResource.deleteKeyword(keyword2.getId());
	}

	@Override
	@Test
	public void testGetKeywordsRankedPageWithPagination() throws Exception {
		Keyword keyword1 = testGetKeywordsRankedPage_addKeyword(
			randomKeyword());
		Keyword keyword2 = testGetKeywordsRankedPage_addKeyword(
			randomKeyword());
		Keyword keyword3 = testGetKeywordsRankedPage_addKeyword(
			randomKeyword());

		Page<Keyword> page1 = keywordResource.getKeywordsRankedPage(
			null, testGroup.getGroupId(), Pagination.of(1, 2));

		List<Keyword> keywords1 = (List<Keyword>)page1.getItems();

		Assert.assertEquals(keywords1.toString(), 2, keywords1.size());

		Page<Keyword> page2 = keywordResource.getKeywordsRankedPage(
			null, testGroup.getGroupId(), Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Keyword> keywords2 = (List<Keyword>)page2.getItems();

		Assert.assertEquals(keywords2.toString(), 1, keywords2.size());

		Page<Keyword> page3 = keywordResource.getKeywordsRankedPage(
			null, testGroup.getGroupId(), Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(keyword1, keyword2, keyword3),
			(List<Keyword>)page3.getItems());
	}

	@Override
	@Test
	public void testGetSiteKeywordByExternalReferenceCode() throws Exception {
		super.testGetSiteKeywordByExternalReferenceCode();

		String externalReferenceCode = StringUtil.toLowerCase(
			RandomTestUtil.randomString());

		try {
			keywordResource.getSiteKeywordByExternalReferenceCode(
				testGetSiteKeywordByExternalReferenceCode_getSiteId(
					randomKeyword()),
				externalReferenceCode);

			Assert.fail();
		}
		catch (Problem.ProblemException problemException) {
			Problem problem = problemException.getProblem();

			Assert.assertEquals("NOT_FOUND", problem.getStatus());
			Assert.assertNull(problem.getTitle());
		}
	}

	@Override
	@Test
	public void testPutAssetLibraryKeywordByExternalReferenceCode()
		throws Exception {

		super.testPutAssetLibraryKeywordByExternalReferenceCode();

		String externalReferenceCode = StringUtil.toLowerCase(
			RandomTestUtil.randomString());

		Keyword keyword =
			testPutAssetLibraryKeywordByExternalReferenceCode_createKeyword();

		Keyword putKeyword =
			keywordResource.putAssetLibraryKeywordByExternalReferenceCode(
				testPutAssetLibraryKeywordByExternalReferenceCode_getAssetLibraryId(),
				externalReferenceCode, keyword);

		Assert.assertEquals(
			externalReferenceCode, putKeyword.getExternalReferenceCode());
		assertValid(putKeyword);
	}

	@Override
	@Test
	public void testPutSiteKeywordByExternalReferenceCode() throws Exception {
		super.testPutSiteKeywordByExternalReferenceCode();

		String externalReferenceCode = StringUtil.toLowerCase(
			RandomTestUtil.randomString());

		Keyword keyword =
			testPutSiteKeywordByExternalReferenceCode_createKeyword();

		Keyword putKeyword =
			keywordResource.putSiteKeywordByExternalReferenceCode(
				testPutSiteKeywordByExternalReferenceCode_getSiteId(keyword),
				externalReferenceCode, keyword);

		Assert.assertEquals(
			externalReferenceCode, putKeyword.getExternalReferenceCode());
		assertValid(putKeyword);
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"name"};
	}

	@Override
	protected Keyword randomKeyword() throws Exception {
		Keyword keyword = super.randomKeyword();

		keyword.setName(StringUtil.toLowerCase(keyword.getName()));

		return keyword;
	}

	@Override
	protected Keyword
			testDeleteAssetLibraryKeywordByExternalReferenceCode_addKeyword()
		throws Exception {

		return keywordResource.postAssetLibraryKeyword(
			testDepotEntry.getDepotEntryId(), randomKeyword());
	}

	@Override
	protected Long
			testDeleteAssetLibraryKeywordByExternalReferenceCode_getAssetLibraryId()
		throws Exception {

		return testDepotEntry.getDepotEntryId();
	}

	@Override
	protected Keyword
			testGetAssetLibraryKeywordByExternalReferenceCode_addKeyword()
		throws Exception {

		return keywordResource.postAssetLibraryKeyword(
			testDepotEntry.getDepotEntryId(), randomKeyword());
	}

	@Override
	protected Long
			testGetAssetLibraryKeywordByExternalReferenceCode_getAssetLibraryId()
		throws Exception {

		return testDepotEntry.getDepotEntryId();
	}

	@Override
	protected Keyword testGetKeywordsRankedPage_addKeyword(Keyword keyword)
		throws Exception {

		keyword = testPostSiteKeyword_addKeyword(keyword);

		AssetEntry assetEntry = AssetTestUtil.addAssetEntry(
			testGroup.getGroupId());

		AssetTagLocalServiceUtil.addAssetEntryAssetTag(
			assetEntry.getEntryId(), keyword.getId());

		return keyword;
	}

	@Override
	protected Keyword
			testGraphQLGetAssetLibraryKeywordByExternalReferenceCode_addKeyword()
		throws Exception {

		return testGetAssetLibraryKeywordByExternalReferenceCode_addKeyword();
	}

	@Override
	protected Long
			testGraphQLGetAssetLibraryKeywordByExternalReferenceCode_getAssetLibraryId()
		throws Exception {

		return testDepotEntry.getDepotEntryId();
	}

	@Override
	protected Long
			testPutAssetLibraryKeywordByExternalReferenceCode_getAssetLibraryId()
		throws Exception {

		return testDepotEntry.getDepotEntryId();
	}

}