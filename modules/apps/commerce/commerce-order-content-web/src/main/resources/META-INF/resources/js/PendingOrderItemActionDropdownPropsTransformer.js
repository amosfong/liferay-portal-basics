/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {commerceEvents} from 'commerce-frontend-js';

export default function propsTransformer({...otherProps}) {
	return {
		...otherProps,
		onActionDropdownItemClick({action, itemData}) {
			if (action.data && action.data.action === 'edit') {
				Liferay.fire(commerceEvents.OPEN_MINICART_FOR_EDITING, {
					dataSetId: action.data.dataSetId,
					orderItemId: itemData.orderItemId,
				});
			}
		},
	};
}
