/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DUPLICATE_ITEM} from './types';

import type {LayoutData} from '../../types/layout_data/LayoutData';
import type {FragmentEntryLink} from './addFragmentEntryLinks';

export default function duplicateItem({
	addedFragmentEntryLinks = [],
	itemIds,
	layoutData,
	restrictedItemIds,
}: {
	addedFragmentEntryLinks?: FragmentEntryLink[];
	itemIds: string[];
	layoutData: LayoutData;
	restrictedItemIds: string[];
}) {
	return {
		addedFragmentEntryLinks,
		itemIds,
		layoutData,
		restrictedItemIds,
		type: DUPLICATE_ITEM,
	} as const;
}
