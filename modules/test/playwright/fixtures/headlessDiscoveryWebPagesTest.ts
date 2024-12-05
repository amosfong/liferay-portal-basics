/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {test} from '@playwright/test';

import {ApiExplorerPage} from '../pages/headless-discovery-web/apiExplorerPage';

const headlessDiscoveryPagesTest = test.extend<{
	apiExplorerPage: ApiExplorerPage;
}>({
	apiExplorerPage: async ({page}, use) => {
		await use(new ApiExplorerPage(page));
	},
});

export {headlessDiscoveryPagesTest};
