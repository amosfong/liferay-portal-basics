/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.item.selector.criterion;

import com.liferay.item.selector.BaseItemSelectorCriterion;

/**
 * @author Bárbara Cabrera
 */
public class LayoutPageTemplateCollectionTreeNodeItemSelectorCriterion
	extends BaseItemSelectorCriterion {

	public long[] getLayoutPageTemplateCollectionIds() {
		return _layoutPageTemplateCollectionIds;
	}

	public void setLayoutPageTemplateCollectionIds(
		long[] layoutPageTemplateCollectionIds) {

		_layoutPageTemplateCollectionIds = layoutPageTemplateCollectionIds;
	}

	private long[] _layoutPageTemplateCollectionIds;

}