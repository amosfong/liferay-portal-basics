/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr8.internal.search.engine.adapter.search;

import com.liferay.portal.search.engine.adapter.search.BaseSearchRequest;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author Bryan Engler
 */
public interface BaseSolrQueryAssembler {

	public void assemble(
		SolrQuery solrQuery, BaseSearchRequest baseSearchRequest);

}