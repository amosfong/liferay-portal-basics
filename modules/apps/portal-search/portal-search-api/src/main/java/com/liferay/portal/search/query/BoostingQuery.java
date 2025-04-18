/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.query;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Michael C. Han
 */
@ProviderType
public interface BoostingQuery extends Query {

	public Float getNegativeBoost();

	public Query getNegativeQuery();

	public Query getPositiveQuery();

	public void setNegativeBoost(Float negativeBoost);

}