/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {test} from '@playwright/test';

import {CustomerApiHelpers} from '../helpers/CustomerApiHelpers';

const customerApiHelpersTest = test.extend<{
	customerApiHelpers: CustomerApiHelpers;
}>({
	customerApiHelpers: async ({page}, use) => {
		await use(new CustomerApiHelpers(page));
	},
});

export {customerApiHelpersTest};