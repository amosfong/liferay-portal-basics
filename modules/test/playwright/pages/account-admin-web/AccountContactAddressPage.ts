/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

export class AccountContactAddressPage {
	readonly addAddressesButton: Locator;
	readonly page: Page;

	constructor(page: Page) {
		this.addAddressesButton = page.getByLabel('Add Addresses');
		this.page = page;
	}
}