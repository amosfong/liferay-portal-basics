/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.filter;

import com.liferay.portal.kernel.search.SearchContext;

/**
 * @author Michael C. Han
 */
public interface FilterTranslator<T> {

	public T translate(Filter filter, SearchContext searchContext);

}