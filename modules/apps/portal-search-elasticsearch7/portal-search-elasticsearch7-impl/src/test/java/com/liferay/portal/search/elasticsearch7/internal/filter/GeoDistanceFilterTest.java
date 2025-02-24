/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.filter;

import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.GeoDistanceFilter;
import com.liferay.portal.kernel.search.filter.GeoDistanceRangeFilter;
import com.liferay.portal.kernel.search.geolocation.GeoDistance;
import com.liferay.portal.kernel.search.geolocation.GeoLocationPoint;
import com.liferay.portal.search.elasticsearch7.internal.indexing.LiferayElasticsearchIndexingFixtureFactory;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Bryan Engler
 */
public class GeoDistanceFilterTest extends BaseIndexingTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGeoDistanceFilter() throws Exception {
		index(33.9987, -117.8129);
		index(34.0003, -117.8127);

		GeoLocationPoint geoLocationPoint = new GeoLocationPoint(
			33.9977, -117.8145);

		_assertCountWithinDistance(2, 500.0, geoLocationPoint);

		_assertCountWithinDistance(1, 250.0, geoLocationPoint);

		_assertCountWithinDistance(0, 100.0, geoLocationPoint);
	}

	@Test
	public void testGeoDistanceRangeFilter() throws Exception {
		index(33.9987, -117.8129);
		index(34.0003, -117.8127);

		GeoLocationPoint geoLocationPoint = new GeoLocationPoint(
			33.9977, -117.8145);

		_assertCountWithinDistanceRange(2, 100.0, 600.0, geoLocationPoint);

		_assertCountWithinDistanceRange(1, 160.0, 300.0, geoLocationPoint);

		_assertCountWithinDistanceRange(0, 50.0, 150.0, geoLocationPoint);
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		return LiferayElasticsearchIndexingFixtureFactory.getInstance();
	}

	protected void index(double latitude, double longitude) throws Exception {
		addDocument(
			DocumentCreationHelpers.singleGeoLocation(
				FIELD, latitude, longitude));
	}

	@Override
	protected void setPreBooleanFilter(Filter filter, Query query) {
		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.add(filter, BooleanClauseOccur.MUST);

		query.setPreBooleanFilter(booleanFilter);
	}

	protected static final String FIELD = Field.GEO_LOCATION;

	private void _assertCount(int expected, Filter filter) throws Exception {
		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.setFilter(filter);

				indexingTestHelper.search();

				indexingTestHelper.verify(
					hits -> Assert.assertEquals(
						indexingTestHelper.getRequestString(), expected,
						hits.getLength()));
			});
	}

	private void _assertCountWithinDistance(
			int expected, double distance, GeoLocationPoint geoLocationPoint)
		throws Exception {

		_assertCount(
			expected,
			new GeoDistanceFilter(
				FIELD, geoLocationPoint, new GeoDistance(distance)));
	}

	private void _assertCountWithinDistanceRange(
			int expected, double fromDistance, double toDistance,
			GeoLocationPoint geoLocationPoint)
		throws Exception {

		_assertCount(
			expected,
			new GeoDistanceRangeFilter(
				FIELD, true, true, new GeoDistance(fromDistance),
				geoLocationPoint, new GeoDistance(toDistance)));
	}

}