/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import duplicateItem from '../actions/duplicateItem';
import {InitAction} from '../actions/init';
import pasteItems from '../actions/pasteItems';
import {DUPLICATE_ITEM, INIT, PASTE_ITEM} from '../actions/types';

export const INITIAL_STATE = new Set<string>();

export default function restrictedItemIdsReducer(
	restrictedItemIds = INITIAL_STATE,
	action:
		| InitAction
		| ReturnType<typeof duplicateItem>
		| ReturnType<typeof pasteItems>
) {
	switch (action.type) {
		case INIT:
			return new Set(restrictedItemIds);

		case DUPLICATE_ITEM:
		case PASTE_ITEM:
			return new Set(action.restrictedItemIds);

		default:
			return restrictedItemIds;
	}
}
