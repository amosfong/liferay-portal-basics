/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.facet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.ModifiedFacet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public abstract class BaseClassicModifiedFacetTestCase
	extends BaseFacetTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testBuiltInNamedRanges() throws Exception {
		addDocument("17760704000000");
		addDocument("27760704000000");

		String[] configRanges = {
			"[past-hour TO *]", "[past-24-hours TO *]", "[past-week TO *]",
			"[past-month TO *]", "[past-year TO *]"
		};

		assertSearchFacet(
			helper -> {
				Facet facet = helper.addFacet(this::createFacet);

				setConfigurationRanges(facet, configRanges);

				helper.search();

				FacetCollector facetCollector = facet.getFacetCollector();

				List<TermCollector> termCollectors =
					facetCollector.getTermCollectors();

				Assert.assertNotNull(termCollectors);

				Assert.assertEquals(
					termCollectors.toString(), configRanges.length,
					termCollectors.size());

				for (TermCollector termCollector : termCollectors) {
					String term = termCollector.getTerm();

					Assert.assertTrue(term.contains("00 TO 20"));
				}
			});
	}

	protected Facet createFacet(SearchContext searchContext) {
		Facet facet = new ModifiedFacet(searchContext);

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		facetConfiguration.setDataJSONObject(jsonFactory.createJSONObject());

		return facet;
	}

	protected JSONArray createRangeArray(String... ranges) {
		JSONArray jsonArray = jsonFactory.createJSONArray();

		for (String range : ranges) {
			jsonArray.put(createRangeArrayElement(range));
		}

		return jsonArray;
	}

	protected JSONObject createRangeArrayElement(String range) {
		JSONObject jsonObject = jsonFactory.createJSONObject();

		jsonObject.put(
			"label", range
		).put(
			"range", range
		);

		return jsonObject;
	}

	@Override
	protected String getField() {
		return Field.MODIFIED_DATE;
	}

	protected void setConfigurationRanges(Facet facet, String... ranges) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		JSONObject jsonObject = facetConfiguration.getData();

		jsonObject.put("ranges", createRangeArray(ranges));
	}

}