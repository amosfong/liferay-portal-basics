/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.facet.custom;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.facet.custom.CustomFacetFactory;
import com.liferay.portal.search.internal.facet.FacetImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Wade Cao
 */
@Component(service = CustomFacetFactory.class)
public class CustomFacetFactoryImpl implements CustomFacetFactory {

	@Override
	public String getFacetClassName() {
		return CustomFacetFactory.class.getName();
	}

	@Override
	public Facet newInstance(SearchContext searchContext) {
		return new FacetImpl(null, searchContext);
	}

}