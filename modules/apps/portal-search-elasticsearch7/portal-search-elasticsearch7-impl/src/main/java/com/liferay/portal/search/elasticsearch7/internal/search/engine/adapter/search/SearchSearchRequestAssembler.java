/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.search;

import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author Michael C. Han
 */
public interface SearchSearchRequestAssembler {

	public void assemble(
		SearchSourceBuilder searchSourceBuilder,
		SearchSearchRequest searchSearchRequest, SearchRequest searchRequest);

}