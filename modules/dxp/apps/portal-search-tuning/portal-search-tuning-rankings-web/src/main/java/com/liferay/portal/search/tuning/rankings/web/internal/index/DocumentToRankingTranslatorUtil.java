/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.index;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.tuning.rankings.constants.ResultRankingsConstants;
import com.liferay.portal.search.tuning.rankings.index.Ranking;
import com.liferay.portal.search.tuning.rankings.index.RankingBuilderFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author André de Oliveira
 */
public class DocumentToRankingTranslatorUtil {

	public static Ranking translate(
		RankingBuilderFactory rankingBuilderFactory, Document document,
		String rankingDocumentId) {

		return builder(
			rankingBuilderFactory
		).aliases(
			_getAliases(document)
		).groupExternalReferenceCode(
			document.getString(RankingFields.GROUP_EXTERNAL_REFERENCE_CODE)
		).hiddenDocumentIds(
			document.getStrings(RankingFields.BLOCKS)
		).indexName(
			document.getString("index")
		).name(
			_getName(document)
		).pins(
			_getPins(document)
		).queryString(
			_getQueryString(document)
		).rankingDocumentId(
			rankingDocumentId
		).status(
			_getStatus(document)
		).sxpBlueprintExternalReferenceCode(
			document.getString(
				RankingFields.SXP_BLUEPRINT_EXTERNAL_REFERENCE_CODE)
		).build();
	}

	protected static Ranking.Builder builder(
		RankingBuilderFactory rankingBuilderFactory) {

		return rankingBuilderFactory.builder();
	}

	private static List<String> _getAliases(Document document) {
		List<String> aliases = document.getStrings(RankingFields.ALIASES);

		if (ListUtil.isEmpty(aliases)) {
			List<String> queryStrings = document.getStrings(
				RankingFields.QUERY_STRINGS);

			queryStrings.remove(document.getString(RankingFields.QUERY_STRING));

			return queryStrings;
		}

		return aliases;
	}

	private static String _getName(Document document) {
		String string = document.getString(RankingFields.NAME);

		if (Validator.isBlank(string)) {
			return _getQueryString(document);
		}

		return string;
	}

	private static List<Ranking.Pin> _getPins(Document document) {
		List<?> values = document.getValues(RankingFields.PINS);

		if (ListUtil.isEmpty(values)) {
			return Collections.emptyList();
		}

		return TransformUtil.transform(
			(List<Map<String, String>>)values,
			DocumentToRankingTranslatorUtil::_toPin);
	}

	private static String _getQueryString(Document document) {
		String string = document.getString(RankingFields.QUERY_STRING);

		if (Validator.isBlank(string)) {
			List<String> strings = _getAliases(document);

			if (ListUtil.isNotEmpty(strings)) {
				return strings.get(0);
			}
		}

		return string;
	}

	private static String _getStatus(Document document) {
		String status = document.getString(RankingFields.STATUS);

		if (!Validator.isBlank(status)) {
			return status;
		}

		if (document.getBoolean("inactive")) {
			return ResultRankingsConstants.STATUS_INACTIVE;
		}

		return ResultRankingsConstants.STATUS_ACTIVE;
	}

	private static Ranking.Pin _toPin(Map<String, String> map) {
		Ranking.Pin.Builder builder = new RankingImpl.PinImpl.BuilderImpl();

		return builder.documentId(
			map.get("uid")
		).position(
			GetterUtil.getInteger(map.get("position"))
		).build();
	}

}