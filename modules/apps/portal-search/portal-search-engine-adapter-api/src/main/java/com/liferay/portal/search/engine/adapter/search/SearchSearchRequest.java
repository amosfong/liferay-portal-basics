/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.engine.adapter.search;

import com.liferay.portal.kernel.search.GroupBy;
import com.liferay.portal.kernel.search.Stats;
import com.liferay.portal.search.collapse.Collapse;
import com.liferay.portal.search.groupby.GroupByRequest;
import com.liferay.portal.search.highlight.Highlight;
import com.liferay.portal.search.sort.Sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Dylan Rebelak
 */
public class SearchSearchRequest
	extends BaseSearchRequest implements SearchRequest<SearchSearchResponse> {

	public SearchSearchRequest() {
		setPreferLocalCluster(true);
	}

	@Override
	public SearchSearchResponse accept(
		SearchRequestExecutor searchRequestExecutor) {

		return searchRequestExecutor.executeSearchRequest(this);
	}

	public void addSorts(Sort... sorts) {
		Collections.addAll(_sorts, sorts);
	}

	public String getAlternateUidFieldName() {
		return _alternateUidFieldName;
	}

	public Collapse getCollapse() {
		return _collapse;
	}

	public Boolean getFetchSource() {
		return _fetchSource;
	}

	public String[] getFetchSourceExcludes() {
		return _fetchSourceExcludes;
	}

	public String[] getFetchSourceIncludes() {
		return _fetchSourceIncludes;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by GroupByRequest
	 */
	@Deprecated
	public GroupBy getGroupBy() {
		return _groupBy;
	}

	public List<GroupByRequest> getGroupByRequests() {
		return Collections.unmodifiableList(_groupByRequests);
	}

	@Override
	public Highlight getHighlight() {
		return _highlight;
	}

	public String[] getHighlightFieldNames() {
		return _highlightFieldNames;
	}

	public int getHighlightFragmentSize() {
		return _highlightFragmentSize;
	}

	public int getHighlightSnippetSize() {
		return _highlightSnippetSize;
	}

	public Locale getLocale() {
		return _locale;
	}

	public String getPreference() {
		return _preference;
	}

	public Boolean getScoreEnabled() {
		return _scoreEnabled;
	}

	public String getScrollId() {
		return _scrollId;
	}

	public long getScrollKeepAliveMinutes() {
		return _scrollKeepAliveMinutes;
	}

	public Object[] getSearchAfter() {
		return _searchAfter;
	}

	public String[] getSelectedFieldNames() {
		return _selectedFieldNames;
	}

	public Integer getSize() {
		return _size;
	}

	public List<Sort> getSorts() {
		return Collections.unmodifiableList(_sorts);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by Sort
	 */
	@Deprecated
	public com.liferay.portal.kernel.search.Sort[] getSorts71() {
		return _legacySorts;
	}

	public Integer getStart() {
		return _start;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by
	 *             com.liferay.portal.search.stats.StatsRequest
	 */
	@Deprecated
	public Map<String, Stats> getStats() {
		return _stats;
	}

	public String[] getStoredFields() {
		return _storedFields;
	}

	public Boolean getVersion() {
		return _version;
	}

	public boolean isAllFieldsSelected() {
		return _allFieldsSelected;
	}

	public boolean isHighlightEnabled() {
		return _highlightEnabled;
	}

	public boolean isHighlightRequireFieldMatch() {
		return _highlightRequireFieldMatch;
	}

	public boolean isLuceneSyntax() {
		return _luceneSyntax;
	}

	public boolean isScoreEnabled() {
		if (_scoreEnabled != null) {
			return _scoreEnabled;
		}

		return false;
	}

	public void putAllStats(Map<String, Stats> stats) {
		if (_stats == null) {
			_stats = new LinkedHashMap<>();
		}

		_stats.putAll(stats);
	}

	public void setAllFieldsSelected(boolean allFieldsSelected) {
		_allFieldsSelected = allFieldsSelected;
	}

	public void setAlternateUidFieldName(String alternateUidFieldName) {
		_alternateUidFieldName = alternateUidFieldName;
	}

	public void setCollapse(Collapse collapse) {
		_collapse = collapse;
	}

	public void setFetchSource(Boolean fetchSource) {
		_fetchSource = fetchSource;
	}

	public void setFetchSourceExcludes(String[] fetchSourceExcludes) {
		_fetchSourceExcludes = fetchSourceExcludes;
	}

	public void setFetchSourceIncludes(String[] fetchSourceIncludes) {
		_fetchSourceIncludes = fetchSourceIncludes;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by GroupByRequest
	 */
	@Deprecated
	public void setGroupBy(GroupBy groupBy) {
		_groupBy = groupBy;
	}

	public void setGroupByRequests(Collection<GroupByRequest> groupByRequests) {
		_groupByRequests = new ArrayList<>(groupByRequests);
	}

	@Override
	public void setHighlight(Highlight highlight) {
		_highlight = highlight;
	}

	public void setHighlightEnabled(boolean highlightEnabled) {
		_highlightEnabled = highlightEnabled;
	}

	public void setHighlightFieldNames(String... highlightFieldNames) {
		_highlightFieldNames = highlightFieldNames;
	}

	public void setHighlightFragmentSize(int highlightFragmentSize) {
		_highlightFragmentSize = highlightFragmentSize;
	}

	public void setHighlightRequireFieldMatch(
		boolean highlightRequireFieldMatch) {

		_highlightRequireFieldMatch = highlightRequireFieldMatch;
	}

	public void setHighlightSnippetSize(int highlightSnippetSize) {
		_highlightSnippetSize = highlightSnippetSize;
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setLuceneSyntax(boolean luceneSyntax) {
		_luceneSyntax = luceneSyntax;
	}

	public void setPreference(String preference) {
		_preference = preference;
	}

	public void setScoreEnabled(boolean scoreEnabled) {
		_scoreEnabled = scoreEnabled;
	}

	public void setScrollId(String scrollId) {
		_scrollId = scrollId;
	}

	public void setScrollKeepAliveMinutes(long scrollKeepAliveMinutes) {
		_scrollKeepAliveMinutes = scrollKeepAliveMinutes;
	}

	public void setSearchAfter(Object[] searchAfter) {
		_searchAfter = searchAfter;
	}

	public void setSelectedFieldNames(String... selectedFieldNames) {
		_selectedFieldNames = selectedFieldNames;
	}

	public void setSize(Integer size) {
		_size = size;
	}

	public void setSorts(Collection<Sort> sorts) {
		_sorts = new ArrayList<>(sorts);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by Sort
	 */
	@Deprecated
	public void setSorts(com.liferay.portal.kernel.search.Sort[] sorts) {
		_legacySorts = sorts;
	}

	public void setStart(Integer start) {
		_start = start;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by
	 *             com.liferay.portal.search.stats.StatsRequest
	 */
	@Deprecated
	public void setStats(Map<String, Stats> stats) {
		_stats = stats;
	}

	public void setStoredFields(String... storedFields) {
		_storedFields = storedFields;
	}

	public void setVersion(Boolean version) {
		_version = version;
	}

	private boolean _allFieldsSelected;
	private String _alternateUidFieldName;
	private Collapse _collapse;
	private Boolean _fetchSource;
	private String[] _fetchSourceExcludes;
	private String[] _fetchSourceIncludes;
	private GroupBy _groupBy;
	private List<GroupByRequest> _groupByRequests = Collections.emptyList();
	private Highlight _highlight;
	private boolean _highlightEnabled;
	private String[] _highlightFieldNames = {};
	private int _highlightFragmentSize = 80;
	private boolean _highlightRequireFieldMatch = true;
	private int _highlightSnippetSize = 3;
	private com.liferay.portal.kernel.search.Sort[] _legacySorts = {};
	private Locale _locale;
	private boolean _luceneSyntax;
	private String _preference;
	private Boolean _scoreEnabled;
	private String _scrollId;
	private long _scrollKeepAliveMinutes;
	private Object[] _searchAfter;
	private String[] _selectedFieldNames;
	private Integer _size;
	private List<Sort> _sorts = new ArrayList<>();
	private Integer _start;
	private Map<String, Stats> _stats;
	private String[] _storedFields;
	private Boolean _version;

}