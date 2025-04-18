/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.taglib.internal.servlet.item.selector;

import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.module.service.Snapshot;

/**
 * @author Roberto Díaz
 */
public class ItemSelectorUtil {

	public static ItemSelector getItemSelector() {
		return _itemSelectorSnapshot.get();
	}

	private static final Snapshot<ItemSelector> _itemSelectorSnapshot =
		new Snapshot<>(ItemSelectorUtil.class, ItemSelector.class);

}