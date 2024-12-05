/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.index;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.document.Field;
import com.liferay.portal.search.internal.document.DocumentBuilderFactoryImpl;
import com.liferay.portal.search.tuning.rankings.helper.RankingHelper;
import com.liferay.portal.search.tuning.rankings.index.Ranking;
import com.liferay.portal.search.tuning.rankings.index.RankingBuilderFactory;
import com.liferay.portal.search.tuning.rankings.index.RankingPinBuilderFactory;
import com.liferay.portal.search.tuning.rankings.web.internal.helper.RankingHelperImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class RankingToDocumentTranslatorUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		ReflectionTestUtil.setFieldValue(
			_rankingBuilderFactory, "_rankingHelper", _rankingHelper);
	}

	@Test
	public void testBlocks() {
		Ranking.Builder rankingBuilder = _rankingBuilderFactory.builder();

		rankingBuilder.hiddenDocumentIds(
			Arrays.asList("142857", "285714", "428571"));

		Document document = translate(rankingBuilder.build());

		Map<String, Field> fieldsMap = document.getFields();

		Assert.assertEquals(
			"{blocks=[142857, 285714, 428571]}", fieldsMap.toString());

		Ranking ranking2 = DocumentToRankingTranslatorUtil.translate(
			_rankingBuilderFactory, document, null);

		Assert.assertEquals(
			"[142857, 285714, 428571]",
			String.valueOf(ranking2.getHiddenDocumentIds()));
	}

	@Test
	public void testDefaults() {
		Ranking.Builder rankingBuilder = _rankingBuilderFactory.builder();

		Document document = translate(rankingBuilder.build());

		Map<String, Field> fieldsMap = document.getFields();

		Assert.assertEquals("{}", fieldsMap.toString());

		Ranking ranking2 = DocumentToRankingTranslatorUtil.translate(
			_rankingBuilderFactory, document, null);

		Assert.assertEquals("[]", String.valueOf(ranking2.getAliases()));
		Assert.assertEquals(null, ranking2.getGroupExternalReferenceCode());
		Assert.assertEquals(
			"[]", String.valueOf(ranking2.getHiddenDocumentIds()));
		Assert.assertEquals("[]", String.valueOf(ranking2.getPins()));
		Assert.assertEquals("[]", String.valueOf(ranking2.getQueryStrings()));
		Assert.assertEquals(
			null, ranking2.getSXPBlueprintExternalReferenceCode());
	}

	@Test
	public void testPins() {
		Ranking.Builder rankingBuilder = _rankingBuilderFactory.builder();

		rankingBuilder.pins(
			Collections.singletonList(
				_rankingPinBuilderFactory.builder(
				).documentId(
					"uid"
				).position(
					142857
				).build()));

		Document document = translate(rankingBuilder.build());

		Map<String, Field> fieldsMap = document.getFields();

		Assert.assertEquals(
			"{pins={position=142857, uid=uid}}", fieldsMap.toString());

		Ranking ranking2 = DocumentToRankingTranslatorUtil.translate(
			_rankingBuilderFactory, document, null);

		Assert.assertEquals("[142857=uid]", _toString(ranking2.getPins()));
	}

	@Test
	public void testQueryStrings() {
		Ranking.Builder rankingBuilder = _rankingBuilderFactory.builder();

		rankingBuilder.aliases(Arrays.asList("142857", "285714", "428571"));

		Document document = translate(rankingBuilder.build());

		Map<String, Field> fieldsMap = document.getFields();

		Assert.assertEquals(
			"{aliases=[142857, 285714, 428571], queryStrings=[142857, " +
				"285714, 428571]}",
			fieldsMap.toString());

		Ranking ranking2 = DocumentToRankingTranslatorUtil.translate(
			_rankingBuilderFactory, document, null);

		Assert.assertEquals(
			"[142857, 285714, 428571]",
			String.valueOf(ranking2.getQueryStrings()));
	}

	protected Document translate(Ranking ranking) {
		return RankingToDocumentTranslatorUtil.translate(
			new DocumentBuilderFactoryImpl(), ranking);
	}

	private String _toString(List<Ranking.Pin> pins) {
		return String.valueOf(
			TransformUtil.transform(
				pins, pin -> pin.getPosition() + "=" + pin.getDocumentId()));
	}

	private final RankingBuilderFactory _rankingBuilderFactory =
		new RankingBuilderFactoryImpl();
	private final RankingHelper _rankingHelper = new RankingHelperImpl();
	private final RankingPinBuilderFactory _rankingPinBuilderFactory =
		new RankingPinBuilderFactoryImpl();

}