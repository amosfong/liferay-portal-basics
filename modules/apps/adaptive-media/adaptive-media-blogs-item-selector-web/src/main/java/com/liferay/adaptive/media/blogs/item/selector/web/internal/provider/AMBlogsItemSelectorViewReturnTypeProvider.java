/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.blogs.item.selector.web.internal.provider;

import com.liferay.adaptive.media.image.item.selector.AMImageFileEntryItemSelectorReturnType;
import com.liferay.adaptive.media.image.item.selector.AMImageURLItemSelectorReturnType;
import com.liferay.blogs.item.selector.constants.BlogsItemSelectorViewConstants;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorViewReturnTypeProvider;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(
	property = "item.selector.view.key=" + BlogsItemSelectorViewConstants.ITEM_SELECTOR_VIEW_KEY,
	service = ItemSelectorViewReturnTypeProvider.class
)
public class AMBlogsItemSelectorViewReturnTypeProvider
	implements ItemSelectorViewReturnTypeProvider {

	@Override
	public List<ItemSelectorReturnType>
		populateSupportedItemSelectorReturnTypes(
			List<ItemSelectorReturnType> supportedItemSelectorReturnTypes) {

		supportedItemSelectorReturnTypes.add(
			new AMImageFileEntryItemSelectorReturnType());
		supportedItemSelectorReturnTypes.add(
			new AMImageURLItemSelectorReturnType());

		return supportedItemSelectorReturnTypes;
	}

}