/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.layout.asset.entry.provider;

/**
 * @author Roberto Díaz
 */
public interface LayoutAssetEntryProviderRegistry {

	public LayoutAssetEntryProvider getLayoutAssetEntryProvider(
		String portletId);

}