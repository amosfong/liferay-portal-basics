/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const INITIAL_FILTER = {
	hasValue: false,
	partner: {
		name: 'Partner',
		value: [] as string[],
	},
	searchTerm: '',
	status: {
		name: 'Status',
		value: [] as string[],
	},
	submitDate: {
		dates: {
			endDate: '',
			startDate: '',
		},
	},

	type: {
		name: 'Type',
		value: [] as string[],
	},
};