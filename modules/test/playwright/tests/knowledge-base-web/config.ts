/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const config = {
	expect: {
		timeout: 30 * 1000,
	},
	name: 'knowledge-base-web',
	testDir: 'tests/knowledge-base-web',
	use: {
		testIdAttribute: 'data-qa-id',
	},
};