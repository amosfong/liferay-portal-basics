/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr8.internal.facet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 * @author Tibor Lipusz
 */
@Component(
	property = {
		"class.name=com.liferay.portal.kernel.search.facet.RangeFacet",
		"class.name=com.liferay.portal.search.internal.facet.ModifiedFacetImpl",
		"class.name=com.liferay.portal.search.internal.facet.RangeFacetImpl"
	},
	service = FacetProcessor.class
)
public class RangeFacetProcessor implements FacetProcessor<SolrQuery> {

	@Override
	public Map<String, JSONObject> processFacet(Facet facet) {
		Map<String, JSONObject> map = new HashMap<>();

		addConfigurationRanges(map, facet);
		addCustomRange(map, facet);

		return sort(map);
	}

	protected static Map<String, JSONObject> sort(
		Map<String, JSONObject> map1) {

		List<String> keys = new ArrayList<>(map1.keySet());

		Collections.sort(keys);

		Map<String, JSONObject> map2 = new LinkedHashMap<>();

		keys.forEach(key -> map2.put(key, map1.get(key)));

		return map2;
	}

	protected void addConfigurationRanges(
		Map<String, JSONObject> map, Facet facet) {

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		JSONObject jsonObject = facetConfiguration.getData();

		JSONArray jsonArray = jsonObject.getJSONArray("ranges");

		if (jsonArray == null) {
			return;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject rangeJSONObject = jsonArray.getJSONObject(i);

			String range = rangeJSONObject.getString("range");

			putFacetParameters(map, facet, range);
		}
	}

	protected void addCustomRange(Map<String, JSONObject> map, Facet facet) {
		SearchContext searchContext = facet.getSearchContext();

		String range = GetterUtil.getString(
			searchContext.getAttribute(facet.getFieldId()));

		if (Validator.isNull(range)) {
			return;
		}

		putFacetParameters(map, facet, range);
	}

	protected JSONObject getFacetParametersJSONObject(
		Facet facet, String range) {

		JSONObject jsonObject = jsonFactory.createJSONObject();

		jsonObject.put(
			"q", facet.getFieldName() + StringPool.COLON + range
		).put(
			"type", "query"
		);

		return jsonObject;
	}

	protected void putFacetParameters(
		Map<String, JSONObject> map, Facet facet, String range) {

		String name =
			FacetUtil.getAggregationName(facet) + StringPool.UNDERLINE + range;

		JSONObject jsonObject = getFacetParametersJSONObject(facet, range);

		map.put(name, jsonObject);
	}

	@Reference
	protected JSONFactory jsonFactory;

}