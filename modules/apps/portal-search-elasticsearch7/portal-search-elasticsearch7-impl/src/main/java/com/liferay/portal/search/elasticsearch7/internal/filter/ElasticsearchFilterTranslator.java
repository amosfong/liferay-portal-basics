/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.filter;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.DateRangeTermFilter;
import com.liferay.portal.kernel.search.filter.ExistsFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.FilterTranslator;
import com.liferay.portal.kernel.search.filter.GeoBoundingBoxFilter;
import com.liferay.portal.kernel.search.filter.GeoDistanceFilter;
import com.liferay.portal.kernel.search.filter.GeoDistanceRangeFilter;
import com.liferay.portal.kernel.search.filter.GeoPolygonFilter;
import com.liferay.portal.kernel.search.filter.MissingFilter;
import com.liferay.portal.kernel.search.filter.PrefixFilter;
import com.liferay.portal.kernel.search.filter.QueryFilter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.search.filter.DateRangeFilter;
import com.liferay.portal.search.filter.FilterVisitor;
import com.liferay.portal.search.filter.RangeFilter;
import com.liferay.portal.search.filter.TermsSetFilter;

import org.elasticsearch.index.query.QueryBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 * @author Marco Leo
 */
@Component(
	property = "search.engine.impl=Elasticsearch",
	service = FilterTranslator.class
)
public class ElasticsearchFilterTranslator
	implements FilterTranslator<QueryBuilder>, FilterVisitor<QueryBuilder> {

	@Override
	public QueryBuilder translate(Filter filter, SearchContext searchContext) {
		return filter.accept(this);
	}

	@Override
	public QueryBuilder visit(BooleanFilter booleanFilter) {
		return booleanFilterTranslator.translate(booleanFilter, this);
	}

	@Override
	public QueryBuilder visit(DateRangeFilter dateRangeFilter) {
		return dateRangeFilterTranslator.translate(dateRangeFilter);
	}

	@Override
	public QueryBuilder visit(DateRangeTermFilter dateRangeTermFilter) {
		return dateRangeTermFilterTranslator.translate(dateRangeTermFilter);
	}

	@Override
	public QueryBuilder visit(ExistsFilter existsFilter) {
		return existsFilterTranslator.translate(existsFilter);
	}

	@Override
	public QueryBuilder visit(GeoBoundingBoxFilter geoBoundingBoxFilter) {
		return geoBoundingBoxFilterTranslator.translate(geoBoundingBoxFilter);
	}

	@Override
	public QueryBuilder visit(GeoDistanceFilter geoDistanceFilter) {
		return geoDistanceFilterTranslator.translate(geoDistanceFilter);
	}

	@Override
	public QueryBuilder visit(GeoDistanceRangeFilter geoDistanceRangeFilter) {
		return geoDistanceRangeFilterTranslator.translate(
			geoDistanceRangeFilter);
	}

	@Override
	public QueryBuilder visit(GeoPolygonFilter geoPolygonFilter) {
		return geoPolygonFilterTranslator.translate(geoPolygonFilter);
	}

	@Override
	public QueryBuilder visit(MissingFilter missingFilter) {
		return missingFilterTranslator.translate(missingFilter);
	}

	@Override
	public QueryBuilder visit(PrefixFilter prefixFilter) {
		return prefixFilterTranslator.translate(prefixFilter);
	}

	@Override
	public QueryBuilder visit(QueryFilter queryFilter) {
		return queryFilterTranslator.translate(queryFilter);
	}

	@Override
	public QueryBuilder visit(RangeFilter rangeFilter) {
		return rangeFilterTranslator.translate(rangeFilter);
	}

	@Override
	public QueryBuilder visit(RangeTermFilter rangeTermFilter) {
		return rangeTermFilterTranslator.translate(rangeTermFilter);
	}

	@Override
	public QueryBuilder visit(TermFilter termFilter) {
		return termFilterTranslator.translate(termFilter);
	}

	@Override
	public QueryBuilder visit(TermsFilter termsFilter) {
		return termsFilterTranslator.translate(termsFilter);
	}

	@Override
	public QueryBuilder visit(TermsSetFilter termsSetFilter) {
		return termsSetFilterTranslator.translate(termsSetFilter);
	}

	@Reference
	protected BooleanFilterTranslator booleanFilterTranslator;

	@Reference
	protected DateRangeFilterTranslator dateRangeFilterTranslator;

	@Reference
	protected DateRangeTermFilterTranslator dateRangeTermFilterTranslator;

	@Reference
	protected ExistsFilterTranslator existsFilterTranslator;

	@Reference
	protected GeoBoundingBoxFilterTranslator geoBoundingBoxFilterTranslator;

	@Reference
	protected GeoDistanceFilterTranslator geoDistanceFilterTranslator;

	@Reference
	protected GeoDistanceRangeFilterTranslator geoDistanceRangeFilterTranslator;

	@Reference
	protected GeoPolygonFilterTranslator geoPolygonFilterTranslator;

	@Reference
	protected MissingFilterTranslator missingFilterTranslator;

	@Reference
	protected PrefixFilterTranslator prefixFilterTranslator;

	@Reference
	protected QueryFilterTranslator queryFilterTranslator;

	@Reference
	protected RangeFilterTranslator rangeFilterTranslator;

	@Reference
	protected RangeTermFilterTranslator rangeTermFilterTranslator;

	@Reference
	protected TermFilterTranslator termFilterTranslator;

	@Reference
	protected TermsFilterTranslator termsFilterTranslator;

	@Reference
	protected TermsSetFilterTranslator termsSetFilterTranslator;

}