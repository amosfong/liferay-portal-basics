/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.aggregation;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.aggregation.bucket.GeoDistanceAggregation;
import com.liferay.portal.search.aggregation.bucket.GeoDistanceAggregationResult;
import com.liferay.portal.search.aggregation.bucket.Range;
import com.liferay.portal.search.opensearch2.internal.OpenSearchTestRule;
import com.liferay.portal.search.opensearch2.internal.indexing.LiferayOpenSearchIndexingFixtureFactory;
import com.liferay.portal.search.test.util.aggregation.bucket.BaseGeoDistanceAggregationTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class GeoDistanceAggregationTest
	extends BaseGeoDistanceAggregationTestCase {

	@ClassRule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@ClassRule
	public static final OpenSearchTestRule openSearchTestRule =
		OpenSearchTestRule.INSTANCE;

	@Override
	@Test
	public void testGeoDistance() throws Exception {
		addDocument(
			DocumentCreationHelpers.singleGeoLocation(
				Field.GEO_LOCATION, 52.374081, 4.912350));
		addDocument(
			DocumentCreationHelpers.singleGeoLocation(
				Field.GEO_LOCATION, 52.369219, 4.901618));
		addDocument(
			DocumentCreationHelpers.singleGeoLocation(
				Field.GEO_LOCATION, 52.369219, 4.901618));
		addDocument(
			DocumentCreationHelpers.singleGeoLocation(
				Field.GEO_LOCATION, 51.222900, 4.405200));
		addDocument(
			DocumentCreationHelpers.singleGeoLocation(
				Field.GEO_LOCATION, 48.861111, 2.336389));
		addDocument(
			DocumentCreationHelpers.singleGeoLocation(
				Field.GEO_LOCATION, 48.860000, 2.327000));

		GeoDistanceAggregation geoDistanceAggregation =
			aggregations.geoDistance(
				"geo_distance", Field.GEO_LOCATION,
				geoBuilders.geoLocationPoint(52.3760, 4.894));

		// LPD-17346

		geoDistanceAggregation.addRange(new Range(0.0, 100000.0));
		geoDistanceAggregation.addRange(new Range(null, 100000.0, 300000.0));
		geoDistanceAggregation.addRange(new Range(300000.0, 1000000.0));

		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.defineRequest(
					searchRequestBuilder -> searchRequestBuilder.addAggregation(
						geoDistanceAggregation));

				indexingTestHelper.search();

				GeoDistanceAggregationResult geoDistanceAggregationResult =
					indexingTestHelper.getAggregationResult(
						geoDistanceAggregation);

				List<Bucket> buckets = new ArrayList<>(
					geoDistanceAggregationResult.getBuckets());

				Assert.assertEquals(buckets.toString(), 3, buckets.size());

				assertBucket(buckets.get(0), "*-100000.0", 3);
				assertBucket(buckets.get(1), "100000.0-300000.0", 1);
				assertBucket(buckets.get(2), "300000.0-1000000.0", 2);
			});
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		return LiferayOpenSearchIndexingFixtureFactory.getInstance();
	}

}