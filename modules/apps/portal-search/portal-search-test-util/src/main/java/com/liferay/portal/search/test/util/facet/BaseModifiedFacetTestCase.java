/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.facet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.facet.modified.ModifiedFacetFactory;
import com.liferay.portal.search.internal.facet.modified.ModifiedFacetFactoryImpl;
import com.liferay.portal.search.internal.filter.FilterBuildersImpl;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author Bryan Engler
 */
public abstract class BaseModifiedFacetTestCase extends BaseFacetTestCase {

	@Test
	public void testCustomRange() throws Exception {
		addDocument("20170102000000");
		addDocument("20170104000000");
		addDocument("20170106000000");

		String customRange = "[20170101000000 TO 20170105000000]";

		assertSearchFacet(
			helper -> {
				Facet facet = helper.addFacet(this::createFacet);

				setCustomRange(facet, customRange);

				helper.search();

				helper.assertFrequencies(
					facet,
					Arrays.asList("[20170101000000 TO 20170105000000]=2"));
			});
	}

	@Test
	public void testRanges() throws Exception {
		addDocument("20170102000000");

		String[] configRanges = {
			"[11110101010101 TO 19990101010101]",
			"[19990202020202 TO 22220202020202]"
		};

		String customRange = "[11110101010101 TO 22220202020202]";

		List<String> expectedRanges = Arrays.asList(
			"[11110101010101 TO 19990101010101]=0",
			"[11110101010101 TO 22220202020202]=1",
			"[19990202020202 TO 22220202020202]=1");

		assertSearchFacet(
			helper -> {
				Facet facet = helper.addFacet(this::createFacet);

				setConfigurationRanges(facet, configRanges);
				setCustomRange(facet, customRange);

				helper.search();

				helper.assertFrequencies(facet, expectedRanges);
			});
	}

	protected Facet createFacet(SearchContext searchContext) {
		ModifiedFacetFactory modifiedFacetFactory =
			new ModifiedFacetFactoryImpl() {
				{
					filterBuilders = new FilterBuildersImpl();
				}
			};

		Facet facet = modifiedFacetFactory.newInstance(searchContext);

		initFacet(facet);

		return facet;
	}

	protected Facet createFacetWithRangeSelected(
		String range, SearchContext searchContext) {

		Facet facet = createFacet(searchContext);

		setConfigurationRanges(facet, new String[] {range});

		facet.select(range);

		return facet;
	}

	protected JSONArray createRangeArray(String... ranges) {
		JSONArray jsonArray = jsonFactory.createJSONArray();

		for (String range : ranges) {
			jsonArray.put(createRangeArrayElement(range, range));
		}

		return jsonArray;
	}

	protected JSONObject createRangeArrayElement(String label, String range) {
		JSONObject jsonObject = jsonFactory.createJSONObject();

		jsonObject.put(
			"label", label
		).put(
			"range", range
		);

		return jsonObject;
	}

	protected void doTestSearchEngineDateMath(String range, int frequency)
		throws Exception {

		assertSearchFacet(
			facetTestHelper -> {
				Facet facet = facetTestHelper.addFacet(
					searchContext -> createFacetWithRangeSelected(
						range, searchContext));

				BooleanClause<Filter> booleanClause =
					facet.getFacetFilterBooleanClause();

				facetTestHelper.setPostFilter(booleanClause.getClause());

				facetTestHelper.search();

				facetTestHelper.assertResultCount(frequency);

				facetTestHelper.assertFrequencies(
					facet, Arrays.asList(range + "=" + frequency));
			});
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

	protected void setCustomRange(Facet facet, String customRange) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		JSONObject jsonObject = facetConfiguration.getData();

		JSONArray jsonArray = jsonObject.getJSONArray("ranges");

		if (jsonArray == null) {
			jsonArray = jsonFactory.createJSONArray();
		}

		jsonArray.put(createRangeArrayElement("custom-range", customRange));

		jsonObject.put("ranges", jsonArray);
	}

}