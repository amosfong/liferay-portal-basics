/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.search.engine.adapter.index;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.engine.adapter.index.FlushIndexRequest;
import com.liferay.portal.search.opensearch2.internal.BaseOpenSearchTestCase;
import com.liferay.portal.search.opensearch2.internal.OpenSearchTestRule;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import org.opensearch.client.opensearch.indices.FlushRequest;

/**
 * @author Michael C. Han
 */
public class FlushIndexRequestExecutorTest extends BaseOpenSearchTestCase {

	@ClassRule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@ClassRule
	public static OpenSearchTestRule openSearchTestRule =
		OpenSearchTestRule.INSTANCE;

	@Test
	public void testIndexRequestTranslation() {
		FlushIndexRequest flushIndexRequest = new FlushIndexRequest(
			TEST_INDEX_NAME);

		flushIndexRequest.setForce(true);
		flushIndexRequest.setWaitIfOngoing(true);

		FlushIndexRequestExecutorImpl flushIndexRequestExecutorImpl =
			new FlushIndexRequestExecutorImpl();

		ReflectionTestUtil.setFieldValue(
			flushIndexRequestExecutorImpl,
			"_indexRequestShardFailureTranslator",
			new IndexRequestShardFailureTranslatorImpl());
		ReflectionTestUtil.setFieldValue(
			flushIndexRequestExecutorImpl, "_openSearchConnectionManager",
			openSearchConnectionManager);

		FlushRequest flushRequest =
			flushIndexRequestExecutorImpl.createFlushRequest(flushIndexRequest);

		Assert.assertArrayEquals(
			new String[] {TEST_INDEX_NAME},
			ArrayUtil.toStringArray(flushRequest.index()));
		Assert.assertTrue(flushRequest.force());
		Assert.assertTrue(flushRequest.waitIfOngoing());
	}

}