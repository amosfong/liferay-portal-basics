/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.legacy.query;

import com.liferay.portal.kernel.search.TermQuery;

import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
public interface TermQueryTranslator {

	public QueryBuilder translate(TermQuery termQuery);

}