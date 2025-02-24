/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.search.engine.adapter.document;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.engine.adapter.document.DeleteByQueryDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.DeleteDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.IndexDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.UpdateDocumentRequest;
import com.liferay.portal.search.opensearch2.internal.OpenSearchTestRule;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Dylan Rebelak
 */
public class OpenSearchDocumentRequestExecutorTest {

	@ClassRule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@ClassRule
	public static OpenSearchTestRule openSearchTestRule =
		OpenSearchTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_openSearchDocumentRequestExecutor =
			new OpenSearchDocumentRequestExecutor();

		ReflectionTestUtil.setFieldValue(
			_openSearchDocumentRequestExecutor,
			"_deleteByQueryDocumentRequestExecutor",
			_deleteByQueryDocumentRequestExecutor);
		ReflectionTestUtil.setFieldValue(
			_openSearchDocumentRequestExecutor,
			"_deleteDocumentRequestExecutor", _deleteDocumentRequestExecutor);
		ReflectionTestUtil.setFieldValue(
			_openSearchDocumentRequestExecutor, "_indexDocumentRequestExecutor",
			_indexDocumentRequestExecutor);
		ReflectionTestUtil.setFieldValue(
			_openSearchDocumentRequestExecutor,
			"_updateByQueryDocumentRequestExecutor",
			_updateByQueryDocumentRequestExecutor);
		ReflectionTestUtil.setFieldValue(
			_openSearchDocumentRequestExecutor,
			"_updateDocumentRequestExecutor", _updateDocumentRequestExecutor);
	}

	@Test
	public void testExecuteDeleteByQueryDocumentRequest() {
		DeleteByQueryDocumentRequest deleteByQueryDocumentRequest =
			new DeleteByQueryDocumentRequest((Query)null);

		_openSearchDocumentRequestExecutor.executeDocumentRequest(
			deleteByQueryDocumentRequest);

		Mockito.verify(
			_deleteByQueryDocumentRequestExecutor
		).execute(
			deleteByQueryDocumentRequest
		);
	}

	@Test
	public void testExecuteDeleteDocumentRequest() {
		DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest(
			null, null);

		_openSearchDocumentRequestExecutor.executeDocumentRequest(
			deleteDocumentRequest);

		Mockito.verify(
			_deleteDocumentRequestExecutor
		).execute(
			deleteDocumentRequest
		);
	}

	@Test
	public void testExecuteIndexDocumentRequest() {
		IndexDocumentRequest indexDocumentRequest = new IndexDocumentRequest(
			null, (Document)null);

		_openSearchDocumentRequestExecutor.executeDocumentRequest(
			indexDocumentRequest);

		Mockito.verify(
			_indexDocumentRequestExecutor
		).execute(
			indexDocumentRequest
		);
	}

	@Test
	public void testExecuteUpdateByQueryDocumentRequest() {
		UpdateByQueryDocumentRequest updateByQueryDocumentRequest =
			new UpdateByQueryDocumentRequest((Query)null, null);

		_openSearchDocumentRequestExecutor.executeDocumentRequest(
			updateByQueryDocumentRequest);

		Mockito.verify(
			_updateByQueryDocumentRequestExecutor
		).execute(
			updateByQueryDocumentRequest
		);
	}

	@Test
	public void testExecuteUpdateDocumentRequest() {
		UpdateDocumentRequest updateDocumentRequest = new UpdateDocumentRequest(
			null, null, (Document)null);

		_openSearchDocumentRequestExecutor.executeDocumentRequest(
			updateDocumentRequest);

		Mockito.verify(
			_updateDocumentRequestExecutor
		).execute(
			updateDocumentRequest
		);
	}

	private final DeleteByQueryDocumentRequestExecutor
		_deleteByQueryDocumentRequestExecutor = Mockito.mock(
			DeleteByQueryDocumentRequestExecutor.class);
	private final DeleteDocumentRequestExecutor _deleteDocumentRequestExecutor =
		Mockito.mock(DeleteDocumentRequestExecutor.class);
	private final IndexDocumentRequestExecutor _indexDocumentRequestExecutor =
		Mockito.mock(IndexDocumentRequestExecutor.class);
	private OpenSearchDocumentRequestExecutor
		_openSearchDocumentRequestExecutor;
	private final UpdateByQueryDocumentRequestExecutor
		_updateByQueryDocumentRequestExecutor = Mockito.mock(
			UpdateByQueryDocumentRequestExecutor.class);
	private final UpdateDocumentRequestExecutor _updateDocumentRequestExecutor =
		Mockito.mock(UpdateDocumentRequestExecutor.class);

}