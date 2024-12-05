/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.index;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.document.DeleteByQueryDocumentRequest;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.index.SyncReindexManager;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.DateRangeTermQuery;
import com.liferay.portal.search.query.ExistsQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermsQuery;

import java.text.Format;

import java.util.Date;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 * @author Petteri Karttunen
 */
@Component(service = SyncReindexManager.class)
public class SyncReindexManagerImpl implements SyncReindexManager {

	@Override
	public void deleteStaleDocuments(
		long companyId, Date date, Set<String> entryClassNames) {

		deleteStaleDocuments(
			_indexNameBuilder.getIndexName(companyId), date, entryClassNames);
	}

	@Override
	public void deleteStaleDocuments(
		String indexName, Date date, Set<String> entryClassNames) {

		if (_log.isInfoEnabled()) {
			_log.info("Deleting stale documents in index " + indexName);
		}

		BooleanQuery booleanQuery = _queries.booleanQuery();

		if (SetUtil.isNotEmpty(entryClassNames)) {
			booleanQuery.addFilterQueryClauses(
				_getEntryClassNamesFilterQuery(entryClassNames));
		}

		booleanQuery.addFilterQueryClauses(
			_getTimeStampFilterBooleanQuery(date));

		_searchEngineAdapter.execute(
			new DeleteByQueryDocumentRequest(booleanQuery, indexName));
	}

	private TermsQuery _getEntryClassNamesFilterQuery(
		Set<String> entryClassNames) {

		TermsQuery termsQuery = _queries.terms(Field.ENTRY_CLASS_NAME);

		termsQuery.addValues(entryClassNames.toArray());

		return termsQuery;
	}

	private BooleanQuery _getTimeStampFilterBooleanQuery(Date date) {
		BooleanQuery booleanQuery = _queries.booleanQuery();

		Format format = _fastDateFormatFactory.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		DateRangeTermQuery dateRangeTermQuery = _queries.dateRangeTerm(
			"timestamp", false, false, null, format.format(date));

		booleanQuery.addShouldQueryClauses(dateRangeTermQuery);

		BooleanQuery existsBooleanQuery = _queries.booleanQuery();

		ExistsQuery existsQuery = _queries.exists("timestamp");

		existsBooleanQuery.addMustNotQueryClauses(existsQuery);

		booleanQuery.addShouldQueryClauses(existsBooleanQuery);

		return booleanQuery;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SyncReindexManagerImpl.class);

	@Reference
	private FastDateFormatFactory _fastDateFormatFactory;

	@Reference
	private IndexNameBuilder _indexNameBuilder;

	@Reference
	private Queries _queries;

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}