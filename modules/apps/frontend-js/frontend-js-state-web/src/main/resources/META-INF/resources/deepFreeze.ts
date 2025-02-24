/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import type {Immutable} from './types';

export default function deepFreeze<T>(value: T): Immutable<T> {
	if (process.env.NODE_ENV === 'development') {
		if (Array.isArray(value)) {
			value.forEach(deepFreeze);
		}
		else if (value && typeof value === 'object') {
			Object.values(value).forEach(deepFreeze);
		}

		Object.freeze(value);
	}

	return value as Immutable<T>;
}
