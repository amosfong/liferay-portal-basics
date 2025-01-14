/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.index;

import com.liferay.portal.search.tuning.rankings.index.name.RankingIndexName;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Wade Cao
 */
public class DuplicateQueryStringsDetectorTest
	extends BaseRankingsIndexTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_duplicateQueryStringsDetector = new DuplicateQueryStringsDetector(
			queries, searchEngineAdapter);
	}

	@Test
	public void testBuilder() {
		Assert.assertNotNull(new Criteria.Builder());
	}

	@Test
	public void testDetect() {
		Criteria criteria = Mockito.mock(Criteria.class);

		List<String> queryStrings = new ArrayList<>(
			Arrays.asList("queryStrings"));

		Mockito.doReturn(
			queryStrings
		).when(
			criteria
		).getQueryStrings();

		Mockito.doReturn(
			Mockito.mock(RankingIndexName.class)
		).when(
			criteria
		).getRankingIndexName();

		setUpQueries();
		setUpSearchEngineAdapter(setUpSearchHits(queryStrings));

		Assert.assertEquals(
			queryStrings, _duplicateQueryStringsDetector.detect(criteria));
	}

	@Test
	public void testDetectCriteriaQueryStringsEmpty() {
		Assert.assertEquals(
			Collections.emptyList(),
			_duplicateQueryStringsDetector.detect(
				Mockito.mock(Criteria.class)));
	}

	private DuplicateQueryStringsDetector _duplicateQueryStringsDetector;

}