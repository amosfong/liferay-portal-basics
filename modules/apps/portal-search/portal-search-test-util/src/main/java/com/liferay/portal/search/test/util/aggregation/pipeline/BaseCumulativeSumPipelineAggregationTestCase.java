/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.aggregation.pipeline;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.aggregation.AggregationResult;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.aggregation.bucket.HistogramAggregation;
import com.liferay.portal.search.aggregation.bucket.HistogramAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.CumulativeSumPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.CumulativeSumPipelineAggregationResult;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public abstract class BaseCumulativeSumPipelineAggregationTestCase
	extends BaseIndexingTestCase {

	@Test
	public void testCumulativeSum() throws Exception {
		for (int i = 1; i <= 20; i++) {
			addDocument(
				DocumentCreationHelpers.singleNumber(Field.PRIORITY, i));
		}

		HistogramAggregation histogramAggregation =
			aggregationFixture.getDefaultHistogramAggregation();

		CumulativeSumPipelineAggregation cumulativeSumPipelineAggregation =
			aggregations.cumulativeSum("cumulative_sum", "sum");

		histogramAggregation.addPipelineAggregation(
			cumulativeSumPipelineAggregation);

		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.defineRequest(
					searchRequestBuilder -> searchRequestBuilder.addAggregation(
						histogramAggregation));

				indexingTestHelper.search();

				HistogramAggregationResult histogramAggregationResult =
					indexingTestHelper.getAggregationResult(
						histogramAggregation);

				List<Bucket> buckets = new ArrayList<>(
					histogramAggregationResult.getBuckets());

				Assert.assertEquals("Number of buckets", 5, buckets.size());

				assertBucket(buckets.get(0), "0.0", 4, 10.0);
				assertBucket(buckets.get(1), "5.0", 5, 45.0);
				assertBucket(buckets.get(2), "10.0", 5, 105.0);
				assertBucket(buckets.get(3), "15.0", 5, 190.0);
				assertBucket(buckets.get(4), "20.0", 1, 210.0);
			});
	}

	protected void assertBucket(
		Bucket bucket, String expectedKey, long expectedCount,
		Double cumulativeSum) {

		Assert.assertEquals(expectedKey, bucket.getKey());
		Assert.assertEquals(expectedCount, bucket.getDocCount());

		if (cumulativeSum != null) {
			Map<String, AggregationResult> childrenAggregationResults =
				bucket.getChildrenAggregationResults();

			CumulativeSumPipelineAggregationResult
				cumulativeSumPipelineAggregationResult =
					(CumulativeSumPipelineAggregationResult)
						childrenAggregationResults.get("cumulative_sum");

			Assert.assertNotNull(cumulativeSumPipelineAggregationResult);

			Assert.assertEquals(
				"Cumulative sum value", cumulativeSum,
				cumulativeSumPipelineAggregationResult.getValue(), 0);
		}
	}

}