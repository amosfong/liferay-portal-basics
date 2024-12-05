/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const DROP_POSITIONS = {
	bottom: 'bottom',
	middle: 'middle',
	top: 'top',
} as const;

export type DropPosition = keyof typeof DROP_POSITIONS;
