/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.display.context;

import com.liferay.portal.search.tuning.rankings.constants.ResultRankingsConstants;
import com.liferay.portal.search.tuning.rankings.index.Ranking;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Wade Cao
 */
public class RankingEntryDisplayContextBuilderTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_rankingEntryDisplayContextBuilder =
			new RankingEntryDisplayContextBuilder(_ranking);
	}

	@Test
	public void testBuild() throws Exception {
		Mockito.doReturn(
			Arrays.asList("aliases")
		).when(
			_ranking
		).getAliases();

		Mockito.doReturn(
			"groupExternalReferenceCode"
		).when(
			_ranking
		).getGroupExternalReferenceCode();

		Mockito.doReturn(
			Arrays.asList("blockIds")
		).when(
			_ranking
		).getHiddenDocumentIds();

		Mockito.doReturn(
			"indexName"
		).when(
			_ranking
		).getIndexName();

		Mockito.doReturn(
			"name"
		).when(
			_ranking
		).getName();

		Mockito.doReturn(
			"nameForDisplay"
		).when(
			_ranking
		).getNameForDisplay();

		Mockito.doReturn(
			Arrays.asList(new Ranking.Pin[] {Mockito.mock(Ranking.Pin.class)})
		).when(
			_ranking
		).getPins();

		Mockito.doReturn(
			"rankingDocumentId"
		).when(
			_ranking
		).getRankingDocumentId();

		Mockito.doReturn(
			"active"
		).when(
			_ranking
		).getStatus();

		Mockito.doReturn(
			"sxpBlueprintExternalReferenceCode"
		).when(
			_ranking
		).getSXPBlueprintExternalReferenceCode();

		RankingEntryDisplayContext rankingEntryDisplayContext =
			_rankingEntryDisplayContextBuilder.build();

		Assert.assertEquals("aliases", rankingEntryDisplayContext.getAliases());
		Assert.assertEquals(
			"1", rankingEntryDisplayContext.getHiddenResultsCount());
		Assert.assertEquals("indexName", rankingEntryDisplayContext.getIndex());
		Assert.assertEquals(
			"nameForDisplay", rankingEntryDisplayContext.getKeywords());
		Assert.assertEquals(
			"1", rankingEntryDisplayContext.getPinnedResultsCount());
		Assert.assertEquals(
			ResultRankingsConstants.STATUS_ACTIVE,
			rankingEntryDisplayContext.getStatus());
		Assert.assertEquals(
			"rankingDocumentId", rankingEntryDisplayContext.getUid());
	}

	private final Ranking _ranking = Mockito.mock(Ranking.class);
	private RankingEntryDisplayContextBuilder
		_rankingEntryDisplayContextBuilder;

}