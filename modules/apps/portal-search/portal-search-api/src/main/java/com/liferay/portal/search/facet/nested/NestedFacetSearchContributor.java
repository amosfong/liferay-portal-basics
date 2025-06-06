/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.facet.nested;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.search.aggregation.Aggregation;
import com.liferay.portal.search.searcher.SearchRequestBuilder;

import java.util.function.Consumer;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jorge Díaz
 * @author Petteri Karttunen
 */
@ProviderType
public interface NestedFacetSearchContributor {

	public void contribute(
		SearchRequestBuilder searchRequestBuilder,
		Consumer<NestedFacetBuilder> nestedFacetBuilderConsumer);

	@ProviderType
	public interface NestedFacetBuilder {

		public NestedFacetBuilder additionalFacetConfigurationData(
			JSONObject additionalFacetConfigurationDataJSONObject);

		public NestedFacetBuilder aggregationName(String aggregationName);

		public NestedFacetBuilder childAggregation(
			Aggregation childAggregation);

		public NestedFacetBuilder childAggregationValuesFilter(
			Filter childAggregationValuesFilter);

		public NestedFacetBuilder fieldToAggregate(String fieldToAggregate);

		public NestedFacetBuilder filterField(String filterField);

		public NestedFacetBuilder filterValue(String filterValue);

		public NestedFacetBuilder frequencyThreshold(int frequencyThreshold);

		public NestedFacetBuilder maxTerms(int maxTerms);

		public NestedFacetBuilder path(String path);

		public NestedFacetBuilder selectedValues(String... selectedValues);

	}

}