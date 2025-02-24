/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.facet.faceted.searcher.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.facet.modified.ModifiedFacetFactory;
import com.liferay.portal.search.test.util.FacetsAssert;
import com.liferay.portal.search.test.util.SearchMapUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;
import java.util.Map;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author André de Oliveira
 */
@RunWith(Arquillian.class)
public class ModifiedFacetedSearcherTest extends BaseFacetedSearcherTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testRanges() throws Exception {
		Group group = userSearchFixture.addGroup();

		String keyword = RandomTestUtil.randomString();

		addUser(group, keyword);

		SearchContext searchContext = getSearchContext(keyword);

		Facet facet = _modifiedFacetFactory.newInstance(searchContext);

		String configRange1 = "[11110101010101 TO 19990101010101]";
		String configRange2 = "[19990202020202 TO 22220202020202]";
		String customRange = "[11110101010101 TO 22220202020202]";

		setConfigurationRanges(facet, configRange1, configRange2);

		setCustomRange(facet, customRange);

		searchContext.addFacet(facet);

		Hits hits = search(searchContext);

		Map<String, Integer> frequencies = SearchMapUtil.join(
			toMap(configRange1, 0), toMap(configRange2, 1),
			toMap(customRange, 1));

		FacetsAssert.assertFrequencies(
			facet.getFieldName(), searchContext, hits, frequencies);
	}

	protected static JSONObject createDataJSONObject(String... ranges) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (String range : ranges) {
			jsonArray.put(
				JSONUtil.put(
					"label", range
				).put(
					"range", range
				));
		}

		return JSONUtil.put("ranges", jsonArray);
	}

	protected static void setConfigurationRanges(
		Facet facet, String... ranges) {

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		facetConfiguration.setDataJSONObject(createDataJSONObject(ranges));
	}

	protected static void setCustomRange(Facet facet, String customRange) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		JSONObject jsonObject = facetConfiguration.getData();

		JSONArray jsonArray = jsonObject.getJSONArray("ranges");

		jsonArray.put(
			JSONUtil.put(
				"label", "custom-range"
			).put(
				"range", customRange
			));

		jsonObject.put("ranges", jsonArray);
	}

	protected static Map<String, Integer> toMap(String key, Integer value) {
		return Collections.singletonMap(key, value);
	}

	@Inject
	private static ModifiedFacetFactory _modifiedFacetFactory;

}