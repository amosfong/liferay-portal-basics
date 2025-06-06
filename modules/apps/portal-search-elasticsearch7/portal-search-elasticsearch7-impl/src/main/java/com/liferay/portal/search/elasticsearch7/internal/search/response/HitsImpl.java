/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.response;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.StatsResults;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 */
public class HitsImpl implements Hits {

	@Override
	public void addGroupedHits(String groupValue, Hits hits) {
		_groupedHits.put(groupValue, hits);
	}

	@Override
	public void addStatsResults(StatsResults statsResults) {
		_statsResults.put(statsResults.getField(), statsResults);
	}

	@Override
	public void copy(Hits hits) {
		setDocs(hits.getDocs());
		setLength(hits.getLength());
		setQuery(hits.getQuery());
		setQuerySuggestions(hits.getQuerySuggestions());
		setQueryTerms(hits.getQueryTerms());
		setScores(hits.getScores());
		setSearchTime(hits.getSearchTime());
		setSnippets(hits.getSnippets());
		setSpellCheckResults(hits.getSpellCheckResults());
		setStart(hits.getStart());
	}

	@Override
	public Document doc(int n) {
		return _docs[n];
	}

	@JSON
	@Override
	public String getCollatedSpellCheckResult() {
		return _collatedSpellCheckResult;
	}

	@JSON
	@Override
	public Document[] getDocs() {
		return _docs;
	}

	@Override
	public Map<String, Hits> getGroupedHits() {
		return Collections.unmodifiableMap(_groupedHits);
	}

	@Override
	public int getLength() {
		return _length;
	}

	@JSON(include = false)
	@Override
	public Query getQuery() {
		return _query;
	}

	@JSON
	@Override
	public String[] getQuerySuggestions() {
		if (ArrayUtil.isEmpty(_querySuggestions)) {
			return StringPool.EMPTY_ARRAY;
		}

		return _querySuggestions;
	}

	@JSON
	@Override
	public String[] getQueryTerms() {
		return _queryTerms;
	}

	@JSON
	@Override
	public float[] getScores() {
		return _scores;
	}

	@Override
	public float getSearchTime() {
		return _searchTime;
	}

	@JSON
	@Override
	public String[] getSnippets() {
		return _snippets;
	}

	@Override
	public Map<String, List<String>> getSpellCheckResults() {
		return _spellCheckResults;
	}

	@Override
	public long getStart() {
		return _start;
	}

	@Override
	public Map<String, StatsResults> getStatsResults() {
		return Collections.unmodifiableMap(_statsResults);
	}

	@Override
	public boolean hasGroupedHits() {
		return !_groupedHits.isEmpty();
	}

	@Override
	public float score(int n) {
		return _scores[n];
	}

	@Override
	public void setCollatedSpellCheckResult(String collatedSpellCheckResult) {
		_collatedSpellCheckResult = collatedSpellCheckResult;
	}

	@Override
	public void setDocs(Document[] docs) {
		_docs = docs;
	}

	@Override
	public void setLength(int length) {
		_length = length;
	}

	@Override
	public void setQuery(Query query) {
		_query = query;
	}

	@Override
	public void setQuerySuggestions(String[] querySuggestions) {
		_querySuggestions = querySuggestions;
	}

	@Override
	public void setQueryTerms(String[] queryTerms) {
		_queryTerms = queryTerms;
	}

	@Override
	public void setScores(float[] scores) {
		_scores = scores;
	}

	@Override
	public void setSearchTime(float time) {
		_searchTime = time;
	}

	@Override
	public void setSnippets(String[] snippets) {
		_snippets = snippets;
	}

	@Override
	public void setSpellCheckResults(
		Map<String, List<String>> spellCheckResults) {

		_spellCheckResults = spellCheckResults;
	}

	@Override
	public void setStart(long start) {
		_start = start;
	}

	@Override
	public String snippet(int n) {
		return _snippets[n];
	}

	@Override
	public List<Document> toList() {
		return Arrays.asList(_docs);
	}

	@Override
	public String toString() {
		if (ArrayUtil.isEmpty(_docs)) {
			return StringBundler.concat(
				"{docs={}, length=", _length, ", query=", _query,
				StringPool.CLOSE_BRACKET);
		}

		StringBundler sb = new StringBundler((2 * _docs.length) + 4);

		sb.append(StringPool.OPEN_BRACKET);

		for (Document document : _docs) {
			sb.append(document);
			sb.append(StringPool.COMMA_AND_SPACE);
		}

		sb.setStringAt("}, length=", sb.index() - 1);

		sb.append(_length);
		sb.append(", query=");
		sb.append(_query);
		sb.append(StringPool.CLOSE_BRACKET);

		return sb.toString();
	}

	private static final Document[] _EMPTY_DOCUMENTS = new Document[0];

	private String _collatedSpellCheckResult;
	private Document[] _docs = _EMPTY_DOCUMENTS;
	private final Map<String, Hits> _groupedHits = new LinkedHashMap<>();
	private int _length;
	private Query _query;
	private String[] _querySuggestions;
	private String[] _queryTerms;
	private float[] _scores = new float[0];
	private float _searchTime;
	private String[] _snippets = {};
	private Map<String, List<String>> _spellCheckResults;
	private long _start;
	private final Map<String, StatsResults> _statsResults = new HashMap<>();

}