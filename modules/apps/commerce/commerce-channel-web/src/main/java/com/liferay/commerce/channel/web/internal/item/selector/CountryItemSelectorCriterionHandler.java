/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.channel.web.internal.item.selector;

import com.liferay.commerce.channel.web.internal.item.selector.criterion.CountryItemSelectorCriterion;
import com.liferay.item.selector.BaseItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorCriterionHandler;

import org.osgi.service.component.annotations.Component;

/**
 * @author Stefano Motta
 */
@Component(service = ItemSelectorCriterionHandler.class)
public class CountryItemSelectorCriterionHandler
	extends BaseItemSelectorCriterionHandler<CountryItemSelectorCriterion> {

	@Override
	public Class<CountryItemSelectorCriterion> getItemSelectorCriterionClass() {
		return CountryItemSelectorCriterion.class;
	}

}