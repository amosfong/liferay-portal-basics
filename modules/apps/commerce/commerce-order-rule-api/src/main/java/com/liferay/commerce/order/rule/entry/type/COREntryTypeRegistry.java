/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.rule.entry.type;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
public interface COREntryTypeRegistry {

	public COREntryType getCOREntryType(String key);

	public List<COREntryType> getCOREntryTypes();

}