/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchPaginationUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexSearcher;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.suggest.QuerySuggester;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.search.aggregation.Aggregation;
import com.liferay.portal.search.aggregation.pipeline.PipelineAggregation;
import com.liferay.portal.search.constants.SearchContextAttributes;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.BaseSearchRequest;
import com.liferay.portal.search.engine.adapter.search.BaseSearchResponse;
import com.liferay.portal.search.engine.adapter.search.ClosePointInTimeRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchResponse;
import com.liferay.portal.search.engine.adapter.search.OpenPointInTimeRequest;
import com.liferay.portal.search.engine.adapter.search.OpenPointInTimeResponse;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.legacy.searcher.SearchResponseBuilderFactory;
import com.liferay.portal.search.opensearch2.constants.OpenSearchSearchContextAttributes;
import com.liferay.portal.search.opensearch2.internal.configuration.OpenSearchConfigurationWrapper;
import com.liferay.portal.search.opensearch2.internal.deep.pagination.configuration.DeepPaginationConfigurationWrapper;
import com.liferay.portal.search.opensearch2.internal.util.JsonpUtil;
import com.liferay.portal.search.opensearch2.internal.util.SetterUtil;
import com.liferay.portal.search.pit.PointInTime;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchResponseBuilder;
import com.liferay.portal.search.sort.ScoreSort;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.sort.Sorts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

import org.opensearch.client.opensearch._types.OpenSearchException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
@Component(
	property = "search.engine.impl=OpenSearch", service = IndexSearcher.class
)
public class OpenSearchIndexSearcher extends BaseIndexSearcher {

	@Override
	public String getQueryString(SearchContext searchContext, Query query) {
		return _searchEngineAdapter.getQueryString(query);
	}

	@Override
	public Hits search(SearchContext searchContext, Query query) {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			int end = searchContext.getEnd();
			int start = searchContext.getStart();

			SearchRequest searchRequest = _getSearchRequest(searchContext);

			Integer from = searchRequest.getFrom();
			Integer size = searchRequest.getSize();

			if ((from == null) && (size != null)) {
				end = size;
				start = 0;
			}
			else if ((from != null) && (size != null)) {
				end = from + size;
				start = from;
			}

			if (start == QueryUtil.ALL_POS) {
				start = 0;
			}
			else if (start < 0) {
				throw new IllegalArgumentException("Invalid start " + start);
			}

			if (end == QueryUtil.ALL_POS) {
				end = GetterUtil.getInteger(
					_props.get(PropsKeys.INDEX_SEARCH_LIMIT));
			}
			else if (end < 0) {
				throw new IllegalArgumentException("Invalid end " + end);
			}

			int maxResultWindow =
				_openSearchConfigurationWrapper.indexMaxResultWindow();

			if ((end - start) > maxResultWindow) {
				end = start + maxResultWindow;
			}

			SearchResponseBuilder searchResponseBuilder =
				_getSearchResponseBuilder(searchContext);

			Hits hits = null;

			if (FeatureFlagManagerUtil.isEnabled("LPS-172416") &&
				_deepPaginationConfigurationWrapper.isEnableDeepPagination(
					searchContext.getCompanyId())) {

				hits = _searchWithDeepPagination(
					end, query, searchContext, searchRequest,
					searchResponseBuilder, start);
			}
			else {
				hits = _search(
					end, query, searchContext, searchRequest,
					searchResponseBuilder, start);
			}

			hits.setStart(stopWatch.getStartTime());

			return hits;
		}
		catch (RuntimeException runtimeException) {
			if (!handle(runtimeException)) {
				if (_openSearchConfigurationWrapper.logExceptionsOnly()) {
					_log.error(runtimeException);
				}
				else {
					throw runtimeException;
				}
			}

			searchContext.setAttribute(
				"search.exception.message",
				_getExceptionMessage(runtimeException));

			return new HitsImpl();
		}
		finally {
			if (_log.isInfoEnabled()) {
				stopWatch.stop();

				_log.info(
					StringBundler.concat(
						"Searching took ", stopWatch.getTime(), " ms"));
			}
		}
	}

	@Override
	public long searchCount(SearchContext searchContext, Query query) {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			CountSearchRequest countSearchRequest = _createCountSearchRequest(
				query, searchContext);

			CountSearchResponse countSearchResponse =
				_searchEngineAdapter.execute(countSearchRequest);

			if (_log.isInfoEnabled()) {
				_log.info(
					StringBundler.concat(
						"The search engine processed ",
						countSearchResponse.getSearchRequestString(), " in ",
						countSearchResponse.getExecutionTime(), " ms"));
			}

			_populateResponse(
				countSearchResponse, _getSearchResponseBuilder(searchContext));

			return countSearchResponse.getCount();
		}
		catch (RuntimeException runtimeException) {
			if (!handle(runtimeException)) {
				if (_openSearchConfigurationWrapper.logExceptionsOnly()) {
					_log.error(runtimeException);
				}
				else {
					throw runtimeException;
				}
			}

			return 0;
		}
		finally {
			if (_log.isInfoEnabled()) {
				stopWatch.stop();

				_log.info(
					StringBundler.concat(
						"Searching took ", stopWatch.getTime(), " ms"));
			}
		}
	}

	protected SearchSearchRequest createSearchSearchRequest(
		Query query, SearchContext searchContext, SearchRequest searchRequest) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		_prepare(searchSearchRequest, query, searchContext, searchRequest);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		searchSearchRequest.setAlternateUidFieldName(
			queryConfig.getAlternateUidFieldName());

		searchSearchRequest.setBasicFacetSelection(
			searchRequest.isBasicFacetSelection());

		searchSearchRequest.putAllFacets(searchContext.getFacets());
		searchSearchRequest.setFetchSource(searchRequest.getFetchSource());
		searchSearchRequest.setFetchSourceExcludes(
			searchRequest.getFetchSourceExcludes());
		searchSearchRequest.setFetchSourceIncludes(
			searchRequest.getFetchSourceIncludes());
		searchSearchRequest.setGroupBy(searchContext.getGroupBy());
		searchSearchRequest.setGroupByRequests(
			searchRequest.getGroupByRequests());
		searchSearchRequest.setHighlightEnabled(
			queryConfig.isHighlightEnabled());
		searchSearchRequest.setHighlightFieldNames(
			queryConfig.getHighlightFieldNames());
		searchSearchRequest.setHighlightFragmentSize(
			queryConfig.getHighlightFragmentSize());
		searchSearchRequest.setHighlightSnippetSize(
			queryConfig.getHighlightSnippetSize());
		searchSearchRequest.setLocale(queryConfig.getLocale());
		searchSearchRequest.setHighlightRequireFieldMatch(
			queryConfig.isHighlightRequireFieldMatch());
		searchSearchRequest.setLuceneSyntax(
			GetterUtil.getBoolean(
				searchContext.getAttribute(
					SearchContextAttributes.ATTRIBUTE_KEY_LUCENE_SYNTAX)));

		SetterUtil.setNotBlankString(
			searchSearchRequest::setPreference,
			(String)searchContext.getAttribute(
				OpenSearchSearchContextAttributes.
					ATTRIBUTE_KEY_SEARCH_REQUEST_PREFERENCE));

		searchSearchRequest.setScoreEnabled(queryConfig.isScoreEnabled());
		searchSearchRequest.setSelectedFieldNames(
			queryConfig.getSelectedFieldNames());
		searchSearchRequest.setStats(searchContext.getStats());

		return searchSearchRequest;
	}

	@Override
	protected QuerySuggester getQuerySuggester() {
		return _querySuggester;
	}

	protected boolean handle(Exception exception) {
		String message = null;

		if (exception instanceof OpenSearchException) {
			OpenSearchException openSearchException =
				(OpenSearchException)exception;

			message = JsonpUtil.toString(openSearchException.error());
		}
		else {
			Throwable throwable = exception.getCause();

			if (throwable == null) {
				return false;
			}

			message = throwable.getMessage();
		}

		if (message == null) {
			return false;
		}

		if (message.contains(
				"Text fields are not optimised for operations that require " +
					"per-document field data")) {

			_log.error(
				"Unable to aggregate facet on a nonkeyword field", exception);

			return true;
		}

		return false;
	}

	protected void setIndexNames(
		BaseSearchRequest baseSearchRequest, SearchContext searchContext,
		SearchRequest searchRequest) {

		baseSearchRequest.setIndexNames(
			_getIndexes(searchContext, searchRequest));
	}

	protected void setQuery(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest) {

		baseSearchRequest.setQuery(searchRequest.getQuery());
	}

	private CountSearchRequest _createCountSearchRequest(
		Query query, SearchContext searchContext) {

		CountSearchRequest countSearchRequest = new CountSearchRequest();

		_prepare(
			countSearchRequest, query, searchContext,
			_getSearchRequest(searchContext));

		return countSearchRequest;
	}

	private PointInTime _createPointInTime(
		SearchContext searchContext, SearchRequest searchRequest) {

		OpenPointInTimeRequest openPointInTimeRequest =
			new OpenPointInTimeRequest(1);

		openPointInTimeRequest.setIndices(
			_getIndexes(searchContext, searchRequest));

		OpenPointInTimeResponse openPointInTimeResponse =
			_searchEngineAdapter.execute(openPointInTimeRequest);

		PointInTime pointInTime = new PointInTime(
			openPointInTimeResponse.pitId());

		pointInTime.setKeepAlive(
			_validatePointInTimeKeepAliveSeconds(
				_deepPaginationConfigurationWrapper.
					getPointInTimeKeepAliveSeconds()));

		return pointInTime;
	}

	private SearchSearchRequest _createSearchSearchRequestWithDeepPagination(
		Query query, SearchContext searchContext, SearchRequest searchRequest,
		int start) {

		SearchSearchRequest searchSearchRequest = createSearchSearchRequest(
			query, searchContext, searchRequest);

		searchSearchRequest.setPointInTime(
			_createPointInTime(searchContext, searchRequest));

		if (ArrayUtil.isEmpty(searchContext.getSorts()) &&
			ListUtil.isEmpty(searchRequest.getSorts())) {

			ScoreSort scoreSort = _sorts.score();

			scoreSort.setSortOrder(SortOrder.DESC);

			searchSearchRequest.addSorts(scoreSort, _sorts.field("_id"));

			return searchSearchRequest;
		}

		if (ListUtil.isNotEmpty(searchRequest.getSorts())) {
			if (start > 0) {
				List<com.liferay.portal.search.sort.Sort> sorts =
					new ArrayList<>();

				sorts.add(_sorts.field("_index"));
				sorts.addAll(searchRequest.getSorts());

				searchSearchRequest.setSorts(sorts);
			}
			else {
				searchSearchRequest.setSorts(searchRequest.getSorts());
			}
		}
		else if (ArrayUtil.isNotEmpty(searchContext.getSorts())) {
			if (start > 0) {
				searchSearchRequest.setSorts(
					ArrayUtil.append(
						new Sort[] {new Sort("index", false)},
						searchContext.getSorts()));
			}
			else {
				searchSearchRequest.setSorts(searchContext.getSorts());
			}
		}

		return searchSearchRequest;
	}

	private String _getExceptionMessage(RuntimeException runtimeException) {
		String message = runtimeException.toString();

		if (runtimeException instanceof OpenSearchException) {
			OpenSearchException openSearchException =
				(OpenSearchException)runtimeException;

			message = message.concat(
				"\n" + JsonpUtil.toString(openSearchException.error()));
		}
		else {
			for (Throwable throwable : runtimeException.getSuppressed()) {
				message = message.concat(
					"\nSuppressed: " + throwable.getMessage());
			}
		}

		return message;
	}

	private String[] _getIndexes(
		SearchContext searchContext, SearchRequest searchRequest) {

		List<String> indexes = searchRequest.getIndexes();

		if (!indexes.isEmpty()) {
			return indexes.toArray(new String[0]);
		}

		String indexName = _indexNameBuilder.getIndexName(
			searchContext.getCompanyId());

		return new String[] {indexName};
	}

	private SearchHit _getLastSearchHit(
		Object[] searchAfter, SearchSearchRequest searchSearchRequest,
		int size) {

		if (searchAfter != null) {
			searchSearchRequest.setSearchAfter(searchAfter);
		}

		searchSearchRequest.setSize(size);
		searchSearchRequest.setStart(0);

		SearchSearchResponse searchSearchResponse =
			_searchEngineAdapter.execute(searchSearchRequest);

		return _getLastSearchHit(searchSearchResponse);
	}

	private SearchHit _getLastSearchHit(
		SearchSearchResponse searchSearchResponse) {

		SearchHits searchHits = searchSearchResponse.getSearchHits();

		List<SearchHit> searchHitList = searchHits.getSearchHits();

		if (searchHitList.isEmpty()) {
			return null;
		}

		return searchHitList.get(searchHitList.size() - 1);
	}

	private SearchRequest _getSearchRequest(SearchContext searchContext) {
		SearchRequestBuilder searchRequestBuilder = _getSearchRequestBuilder(
			searchContext);

		return searchRequestBuilder.build();
	}

	private SearchRequestBuilder _getSearchRequestBuilder(
		SearchContext searchContext) {

		return _searchRequestBuilderFactory.builder(searchContext);
	}

	private SearchResponseBuilder _getSearchResponseBuilder(
		SearchContext searchContext) {

		return _searchResponseBuilderFactory.builder(searchContext);
	}

	private void _populateResponse(
		BaseSearchResponse baseSearchResponse,
		SearchResponseBuilder searchResponseBuilder) {

		searchResponseBuilder.aggregationResultsMap(
			baseSearchResponse.getAggregationResultsMap()
		).count(
			baseSearchResponse.getCount()
		).requestString(
			baseSearchResponse.getSearchRequestString()
		).responseString(
			baseSearchResponse.getSearchResponseString()
		).searchTimeValue(
			baseSearchResponse.getSearchTimeValue()
		).statsResponseMap(
			baseSearchResponse.getStatsResponseMap()
		);
	}

	private void _populateResponse(
		SearchResponseBuilder searchResponseBuilder,
		SearchSearchResponse searchSearchResponse) {

		_populateResponse(
			(BaseSearchResponse)searchSearchResponse, searchResponseBuilder);

		searchResponseBuilder.groupByResponses(
			searchSearchResponse.getGroupByResponses());
	}

	private void _prepare(
		BaseSearchRequest baseSearchRequest, Query query,
		SearchContext searchContext, SearchRequest searchRequest) {

		baseSearchRequest.addComplexQueryParts(
			searchRequest.getComplexQueryParts());
		baseSearchRequest.setExplain(searchRequest.isExplain());
		baseSearchRequest.setHighlight(searchRequest.getHighlight());
		baseSearchRequest.setIncludeResponseString(
			searchRequest.isIncludeResponseString());
		baseSearchRequest.setPostFilterQuery(
			searchRequest.getPostFilterQuery());
		baseSearchRequest.addPostFilterComplexQueryParts(
			searchRequest.getPostFilterComplexQueryParts());
		baseSearchRequest.setRescores(searchRequest.getRescores());
		baseSearchRequest.setStatsRequests(searchRequest.getStatsRequests());
		baseSearchRequest.setTrackTotalHits(
			_openSearchConfigurationWrapper.trackTotalHits());

		_setAggregations(baseSearchRequest, searchRequest);
		_setConnectionId(baseSearchRequest, searchRequest);
		setIndexNames(baseSearchRequest, searchContext, searchRequest);
		_setLegacyQuery(baseSearchRequest, query);
		_setLegacyPostFilter(baseSearchRequest, query);
		_setPipelineAggregations(baseSearchRequest, searchRequest);
		setQuery(baseSearchRequest, searchRequest);
	}

	private Hits _search(
		int end, Query query, SearchContext searchContext,
		SearchRequest searchRequest,
		SearchResponseBuilder searchResponseBuilder, int start) {

		Hits hits;

		while (true) {
			SearchSearchRequest searchSearchRequest = createSearchSearchRequest(
				query, searchContext, searchRequest);

			searchSearchRequest.setSize(
				Math.min(
					end - start,
					_openSearchConfigurationWrapper.indexMaxResultWindow() -
						start));
			searchSearchRequest.setSorts(searchContext.getSorts());
			searchSearchRequest.setSorts(searchRequest.getSorts());
			searchSearchRequest.setStart(start);

			SearchSearchResponse searchSearchResponse =
				_searchEngineAdapter.execute(searchSearchRequest);

			if (_log.isInfoEnabled()) {
				_log.info(
					StringBundler.concat(
						"The search engine processed ",
						searchSearchResponse.getSearchRequestString(), " in ",
						searchSearchResponse.getExecutionTime(), " ms"));
			}

			_populateResponse(searchResponseBuilder, searchSearchResponse);

			searchResponseBuilder.searchHits(
				searchSearchResponse.getSearchHits());

			hits = searchSearchResponse.getHits();

			Document[] documents = hits.getDocs();

			if ((documents.length != 0) || (start == 0)) {
				break;
			}

			int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(
				start, end, hits.getLength());

			start = startAndEnd[0];
			end = startAndEnd[1];
		}

		return hits;
	}

	private Hits _searchWithDeepPagination(
		int end, Query query, SearchContext searchContext,
		SearchRequest searchRequest,
		SearchResponseBuilder searchResponseBuilder, int start) {

		SearchSearchRequest searchSearchRequest =
			_createSearchSearchRequestWithDeepPagination(
				query, searchContext, searchRequest, start);

		SearchSearchResponse searchSearchResponse = null;

		int maxResultWindow =
			_openSearchConfigurationWrapper.indexMaxResultWindow();

		try {
			if (end > maxResultWindow) {
				SearchHit lastSearchHit = _skipToLastSearchHit(
					maxResultWindow, searchSearchRequest, start);

				if (lastSearchHit == null) {
					return new HitsImpl();
				}

				searchSearchRequest.setSearchAfter(
					lastSearchHit.getSortValues());
				searchSearchRequest.setStart(0);
			}
			else {
				searchSearchRequest.setStart(start);
			}

			searchSearchRequest.setSize(Math.min(end - start, maxResultWindow));

			searchSearchResponse = _searchEngineAdapter.execute(
				searchSearchRequest);
		}
		catch (RuntimeException runtimeException) {
			throw runtimeException;
		}
		finally {
			PointInTime pointInTime = searchSearchRequest.getPointInTime();

			_searchEngineAdapter.execute(
				new ClosePointInTimeRequest(pointInTime.getPointInTimeId()));
		}

		_populateResponse(searchResponseBuilder, searchSearchResponse);

		searchResponseBuilder.searchHits(searchSearchResponse.getSearchHits());

		return searchSearchResponse.getHits();
	}

	private void _setAggregations(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest) {

		Map<String, Aggregation> aggregations =
			searchRequest.getAggregationsMap();

		for (Aggregation aggregation : aggregations.values()) {
			baseSearchRequest.addAggregation(aggregation);
		}
	}

	private void _setConnectionId(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest) {

		baseSearchRequest.setConnectionId(searchRequest.getConnectionId());
	}

	private void _setLegacyPostFilter(
		BaseSearchRequest baseSearchRequest, Query query) {

		if (query != null) {
			baseSearchRequest.setPostFilter(query.getPostFilter());
		}
	}

	private void _setLegacyQuery(
		BaseSearchRequest baseSearchRequest, Query query) {

		baseSearchRequest.setQuery(query);
	}

	private void _setPipelineAggregations(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest) {

		Map<String, PipelineAggregation> pipelineAggregations =
			searchRequest.getPipelineAggregationsMap();

		for (PipelineAggregation aggregation : pipelineAggregations.values()) {
			baseSearchRequest.addPipelineAggregation(aggregation);
		}
	}

	private SearchHit _skipToLastSearchHit(
		int maxResultWindow, SearchSearchRequest searchSearchRequest,
		int start) {

		int skipToStart = 0;

		if (start >= maxResultWindow) {
			searchSearchRequest.setStart(maxResultWindow - 1);

			skipToStart = start % maxResultWindow;
		}

		searchSearchRequest.setSize(1);

		SearchSearchResponse searchSearchResponse =
			_searchEngineAdapter.execute(searchSearchRequest);

		SearchHit lastSearchHit = _getLastSearchHit(searchSearchResponse);

		if (lastSearchHit == null) {
			return null;
		}

		int maxResultWindowPages = start / maxResultWindow;

		for (int i = 1; i < maxResultWindowPages; i++) {
			lastSearchHit = _getLastSearchHit(
				lastSearchHit.getSortValues(), searchSearchRequest,
				maxResultWindow);

			if (lastSearchHit == null) {
				return null;
			}
		}

		if (skipToStart > 0) {
			lastSearchHit = _getLastSearchHit(
				lastSearchHit.getSortValues(), searchSearchRequest,
				skipToStart);
		}

		return lastSearchHit;
	}

	private int _validatePointInTimeKeepAliveSeconds(
		int pointInTimeKeepAliveSeconds) {

		if ((pointInTimeKeepAliveSeconds > 0) &&
			(pointInTimeKeepAliveSeconds <= 60)) {

			return pointInTimeKeepAliveSeconds;
		}

		return 60;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OpenSearchIndexSearcher.class);

	@Reference
	private DeepPaginationConfigurationWrapper
		_deepPaginationConfigurationWrapper;

	@Reference
	private IndexNameBuilder _indexNameBuilder;

	@Reference
	private OpenSearchConfigurationWrapper _openSearchConfigurationWrapper;

	@Reference
	private Props _props;

	@Reference(target = "(search.engine.impl=OpenSearch)")
	private QuerySuggester _querySuggester;

	@Reference(target = "(search.engine.impl=OpenSearch)")
	private SearchEngineAdapter _searchEngineAdapter;

	@Reference
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

	@Reference
	private SearchResponseBuilderFactory _searchResponseBuilderFactory;

	@Reference
	private Sorts _sorts;

}