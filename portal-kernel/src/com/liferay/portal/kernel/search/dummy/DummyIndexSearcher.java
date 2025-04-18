/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.dummy;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.kernel.search.suggest.Suggester;
import com.liferay.portal.kernel.search.suggest.SuggesterResults;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Marcellus Tavares
 * @author Carlos Sierra Andrés
 */
public class DummyIndexSearcher implements IndexSearcher {

	@Override
	public String getQueryString(SearchContext searchContext, Query query) {
		return StringPool.BLANK;
	}

	@Override
	public Hits search(SearchContext searchContext, Query query) {
		return _getHits();
	}

	@Override
	public long searchCount(SearchContext searchContext, Query query) {
		return 0;
	}

	@Override
	public String spellCheckKeywords(SearchContext searchContext) {
		return StringPool.BLANK;
	}

	@Override
	public Map<String, List<String>> spellCheckKeywords(
		SearchContext searchContext, int max) {

		return Collections.emptyMap();
	}

	@Override
	public SuggesterResults suggest(
		SearchContext searchContext, Suggester suggester) {

		return new SuggesterResults();
	}

	@Override
	public String[] suggestKeywordQueries(
		SearchContext searchContext, int max) {

		return new String[0];
	}

	private Hits _getHits() {
		Hits hits = new HitsImpl();

		hits.setCollatedSpellCheckResult(StringPool.BLANK);
		hits.setDocs(new Document[0]);
		hits.setLength(0);
		hits.setQuery(new StringQuery(StringPool.BLANK));
		hits.setQuerySuggestions(new String[0]);
		hits.setQueryTerms(new String[0]);
		hits.setLength(0);
		hits.setScores(new float[0]);
		hits.setSearchTime(0);
		hits.setSnippets(new String[0]);
		hits.setSpellCheckResults(_spellCheckResults);
		hits.setStart(0);

		return hits;
	}

	private static final Map<String, List<String>> _spellCheckResults =
		Collections.emptyMap();

}