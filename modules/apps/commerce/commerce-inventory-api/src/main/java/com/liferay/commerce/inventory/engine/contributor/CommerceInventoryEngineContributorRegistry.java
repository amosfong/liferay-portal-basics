/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.engine.contributor;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public interface CommerceInventoryEngineContributorRegistry {

	public CommerceInventoryEngineContributor
		getCommerceInventoryEngineContributor(String key);

	public List<CommerceInventoryEngineContributor>
		getCommerceInventoryEngineContributors();

}