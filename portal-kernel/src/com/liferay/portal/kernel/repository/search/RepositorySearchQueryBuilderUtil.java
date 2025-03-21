/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.repository.search;

import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;

/**
 * @author Mika Koivisto
 */
public class RepositorySearchQueryBuilderUtil {

	public static BooleanQuery getFullQuery(SearchContext searchContext)
		throws SearchException {

		RepositorySearchQueryBuilder repositorySearchQueryBuilder =
			_repositorySearchQueryBuilderSnapshot.get();

		return repositorySearchQueryBuilder.getFullQuery(searchContext);
	}

	public static RepositorySearchQueryBuilder
		getRepositorySearchQueryBuilder() {

		return _repositorySearchQueryBuilderSnapshot.get();
	}

	private static final Snapshot<RepositorySearchQueryBuilder>
		_repositorySearchQueryBuilderSnapshot = new Snapshot<>(
			RepositorySearchQueryBuilderUtil.class,
			RepositorySearchQueryBuilder.class);

}