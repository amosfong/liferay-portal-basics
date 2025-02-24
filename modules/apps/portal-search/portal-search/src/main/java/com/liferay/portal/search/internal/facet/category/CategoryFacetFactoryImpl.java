/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.facet.category;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.facet.category.CategoryFacetFactory;
import com.liferay.portal.search.internal.facet.FacetImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bryan Engler
 */
@Component(service = CategoryFacetFactory.class)
public class CategoryFacetFactoryImpl implements CategoryFacetFactory {

	@Override
	public String getFacetClassName() {
		return "assetVocabularyCategoryIds";
	}

	@Override
	public Facet newInstance(SearchContext searchContext) {
		return new FacetImpl("assetVocabularyCategoryIds", searchContext);
	}

}