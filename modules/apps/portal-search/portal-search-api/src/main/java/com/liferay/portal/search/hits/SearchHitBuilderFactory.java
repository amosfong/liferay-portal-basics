/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.hits;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Wade Cao
 */
@ProviderType
public interface SearchHitBuilderFactory {

	public SearchHitBuilder getSearchHitBuilder();

}