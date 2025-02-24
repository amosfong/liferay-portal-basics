/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.query;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.SimpleStringQuery;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public abstract class BaseSimpleStringQueryTestCase
	extends BaseIndexingTestCase {

	@Test
	public void testSimpleStringQuery() {
		for (int i = 0; i < 10; i++) {
			addDocument(
				DocumentCreationHelpers.singleKeyword(
					Field.USER_NAME, "SomeUser" + i));
			addDocument(
				DocumentCreationHelpers.singleKeyword(
					Field.USER_NAME, "OtherUser" + i));
			addDocument(
				DocumentCreationHelpers.singleKeyword(
					Field.USER_NAME, "Other" + i));
		}

		SimpleStringQuery simpleStringQuery = queries.simpleString(
			"(SomeUser* | OtherUser*)");

		simpleStringQuery.addField(Field.USER_NAME, 1.0F);

		assertSearch(
			indexingTestHelper -> {
				SearchEngineAdapter searchEngineAdapter =
					getSearchEngineAdapter();

				SearchSearchResponse searchSearchResponse =
					searchEngineAdapter.execute(
						new SearchSearchRequest() {
							{
								addSorts(sorts.field(Field.USER_NAME));
								setIndexNames("_all");
								setQuery(simpleStringQuery);
								setSize(30);
							}
						});

				SearchHits searchHits = searchSearchResponse.getSearchHits();

				Assert.assertEquals(
					"Total hits", 20, searchHits.getTotalHits());

				List<SearchHit> searchHitsList = searchHits.getSearchHits();

				Assert.assertEquals(
					"Retrieved hits", 20, searchHitsList.size());

				searchHitsList.forEach(
					searchHit -> {
						Document document = searchHit.getDocument();

						String userName = document.getString(Field.USER_NAME);

						Assert.assertTrue(
							userName.startsWith("OtherUser") ||
							userName.startsWith("SomeUser"));
					});
			});
	}

}