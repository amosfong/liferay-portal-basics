/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.collection.provider.item.selector.criterion;

import com.liferay.item.selector.BaseItemSelectorCriterion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Víctor Galán
 */
public class RelatedInfoItemCollectionProviderItemSelectorCriterion
	extends BaseItemSelectorCriterion {

	public List<String> getSourceItemTypes() {
		return _sourceItemTypes;
	}

	public void setSourceItemTypes(List<String> sourceItemTypes) {
		_sourceItemTypes = sourceItemTypes;
	}

	private List<String> _sourceItemTypes = new ArrayList<>();

}