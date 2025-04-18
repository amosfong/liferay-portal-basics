/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import deleteItem from '../../thunks/deleteItem';

function undoAction({action, store}) {
	return deleteItem({...action, store});
}

function getDerivedStateForUndo({action}) {
	return {itemIds: action.itemIds};
}

export {undoAction, getDerivedStateForUndo};
