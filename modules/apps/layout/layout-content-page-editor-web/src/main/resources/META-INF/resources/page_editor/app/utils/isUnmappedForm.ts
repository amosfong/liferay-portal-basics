/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {LayoutDataItem} from '../../types/layout_data/LayoutData';
import {LAYOUT_DATA_ITEM_TYPES} from '../config/constants/layoutDataItemTypes';
import {formIsMapped} from './formIsMapped';

export function isUnmappedForm(item: LayoutDataItem) {
	return item.type === LAYOUT_DATA_ITEM_TYPES.form && !formIsMapped(item);
}