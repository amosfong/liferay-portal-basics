/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.logging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.opensearch2.internal.OpenSearchIndexWriter;
import com.liferay.portal.search.opensearch2.internal.OpenSearchTestRule;
import com.liferay.portal.search.opensearch2.internal.connection.TestOpenSearchConnectionManager;
import com.liferay.portal.search.opensearch2.internal.indexing.LiferayOpenSearchIndexingFixtureFactory;
import com.liferay.portal.search.opensearch2.internal.search.engine.adapter.document.BulkDocumentRequestExecutorImpl;
import com.liferay.portal.search.test.rule.logging.ExpectedLogMethodTestRule;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.logging.ExpectedLog;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Bryan Engler
 */
public class OpenSearchIndexWriterLogExceptionsOnlyTest
	extends BaseIndexingTestCase {

	@ClassRule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			ExpectedLogMethodTestRule.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@ClassRule
	public static OpenSearchTestRule openSearchTestRule =
		OpenSearchTestRule.INSTANCE;

	@ExpectedLog(
		expectedClass = OpenSearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.WARNING,
		expectedLog = "failed to parse field [expirationDate] of type [date]"
	)
	@Test
	public void testAddDocument() throws Exception {
		addDocument(
			DocumentCreationHelpers.singleKeyword(
				Field.EXPIRATION_DATE, "text"));
	}

	@ExpectedLog(
		expectedClass = OpenSearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.WARNING,
		expectedLog = "Bulk add failed"
	)
	@Test
	public void testAddDocuments() {
		List<Document> documents = new ArrayList<>();

		Document document = new DocumentImpl();

		document.addKeyword(Field.EXPIRATION_DATE, "text");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.addDocuments(createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = BulkDocumentRequestExecutorImpl.class,
		expectedLevel = ExpectedLog.Level.WARNING,
		expectedLog = "failed to parse field [expirationDate] of type [date]"
	)
	@Test
	public void testAddDocumentsBulkExecutor() {
		List<Document> documents = new ArrayList<>();

		Document document = new DocumentImpl();

		document.addKeyword(Field.EXPIRATION_DATE, "text");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.addDocuments(createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = OpenSearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.WARNING, expectedLog = "no such index"
	)
	@Test
	public void testCommit() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.commit(searchContext);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testDeleteDocument() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.deleteDocument(searchContext, "1");
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = OpenSearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.INFO, expectedLog = "no such index"
	)
	@Test
	public void testDeleteDocumentInfoLevel() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.deleteDocument(searchContext, "1");
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = OpenSearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.WARNING,
		expectedLog = "Bulk delete failed"
	)
	@Test
	public void testDeleteDocuments() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		List<String> uids = new ArrayList<>();

		uids.add("1");

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.deleteDocuments(searchContext, uids);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = BulkDocumentRequestExecutorImpl.class,
		expectedLevel = ExpectedLog.Level.WARNING, expectedLog = "no such index"
	)
	@Test
	public void testDeleteDocumentsBulkExecutor() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		List<String> uids = new ArrayList<>();

		uids.add("1");

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.deleteDocuments(searchContext, uids);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = OpenSearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.WARNING, expectedLog = "no such index"
	)
	@Test
	public void testDeleteEntityDocuments() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(1);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.deleteEntityDocuments(searchContext, "test");
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testPartiallyUpdateDocument() {
		Document document = new DocumentImpl();

		document.addKeyword(Field.UID, "1");

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.partiallyUpdateDocument(
				createSearchContext(), document);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Test
	public void testPartiallyUpdateDocuments() {
		Document document = new DocumentImpl();

		List<Document> documents = new ArrayList<>();

		document.addKeyword(Field.UID, "1");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.partiallyUpdateDocuments(
				createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = BulkDocumentRequestExecutorImpl.class,
		expectedLevel = ExpectedLog.Level.WARNING,
		expectedLog = "document missing"
	)
	@Test
	public void testPartiallyUpdateDocumentsBulkExecutor() {
		Document document = new DocumentImpl();

		List<Document> documents = new ArrayList<>();

		document.addKeyword(Field.UID, "1");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.partiallyUpdateDocuments(
				createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = OpenSearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.WARNING, expectedLog = "Update failed"
	)
	@Test
	public void testUpdateDocument() {
		Document document = new DocumentImpl();

		document.addKeyword(Field.UID, "1");
		document.addKeyword(Field.EXPIRATION_DATE, "text");

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.updateDocument(createSearchContext(), document);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = BulkDocumentRequestExecutorImpl.class,
		expectedLevel = ExpectedLog.Level.WARNING,
		expectedLog = "failed to parse field [expirationDate] of type [date]"
	)
	@Test
	public void testUpdateDocumentBulkExecutor() {
		Document document = new DocumentImpl();

		document.addKeyword(Field.UID, "1");
		document.addKeyword(Field.EXPIRATION_DATE, "text");

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.updateDocument(createSearchContext(), document);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = OpenSearchIndexWriter.class,
		expectedLevel = ExpectedLog.Level.WARNING,
		expectedLog = "Bulk update failed"
	)
	@Test
	public void testUpdateDocuments() {
		List<Document> documents = new ArrayList<>();

		Document document = new DocumentImpl();

		document.addKeyword(Field.UID, "1");
		document.addKeyword(Field.EXPIRATION_DATE, "text");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.updateDocuments(createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@ExpectedLog(
		expectedClass = BulkDocumentRequestExecutorImpl.class,
		expectedLevel = ExpectedLog.Level.WARNING,
		expectedLog = "failed to parse field [expirationDate] of type [date]"
	)
	@Test
	public void testUpdateDocumentsBulkExecutor() {
		List<Document> documents = new ArrayList<>();

		Document document = new DocumentImpl();

		document.addKeyword(Field.UID, "1");
		document.addKeyword(Field.EXPIRATION_DATE, "text");

		documents.add(document);

		IndexWriter indexWriter = getIndexWriter();

		try {
			indexWriter.updateDocuments(createSearchContext(), documents);
		}
		catch (SearchException searchException) {
			if (_log.isDebugEnabled()) {
				_log.debug(searchException);
			}
		}
	}

	@Override
	protected IndexingFixture createIndexingFixture() {
		return LiferayOpenSearchIndexingFixtureFactory.builder(
		).testOpenSearchConnectionManager(
			new TestOpenSearchConnectionManager(
				HashMapBuilder.<String, Object>put(
					"logExceptionsOnly", true
				).build())
		).build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OpenSearchIndexWriterLogExceptionsOnlyTest.class);

}