/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {UNLOAD_REDUCER} from './types';

export default function unloadReducer(key: string) {
	return {
		key,
		type: UNLOAD_REDUCER,
	} as const;
}
