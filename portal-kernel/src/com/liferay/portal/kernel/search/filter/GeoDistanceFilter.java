/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.filter;

import com.liferay.portal.kernel.search.geolocation.GeoDistance;
import com.liferay.portal.kernel.search.geolocation.GeoLocationPoint;

/**
 * @author Michael C. Han
 */
public class GeoDistanceFilter extends BaseFilter {

	public GeoDistanceFilter(
		String field, GeoLocationPoint pinGeoLocationPoint,
		GeoDistance geoDistance) {

		_field = field;
		_pinGeoLocationPoint = pinGeoLocationPoint;
		_geoDistance = geoDistance;
	}

	@Override
	public <T> T accept(FilterVisitor<T> filterVisitor) {
		return filterVisitor.visit(this);
	}

	public String getField() {
		return _field;
	}

	public GeoDistance getGeoDistance() {
		return _geoDistance;
	}

	public GeoLocationPoint getPinGeoLocationPoint() {
		return _pinGeoLocationPoint;
	}

	@Override
	public int getSortOrder() {
		return 100;
	}

	private final String _field;
	private final GeoDistance _geoDistance;
	private final GeoLocationPoint _pinGeoLocationPoint;

}