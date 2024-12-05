/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

export class NotificationsPage {
	readonly page: Page;

	readonly backButton: Locator;
	readonly requestsTab: Locator;

	constructor(page: Page) {
		this.page = page;

		this.backButton = this.page.getByRole('link', {
			name: 'Return to Full Page',
		});
		this.requestsTab = this.page.getByRole('link', {
			name: 'Requests List (0)',
		});
	}

	async goto() {
		await this.page.getByLabel('Test Test User Profile').click();
		await this.page.getByRole('menuitem', {name: 'Notifications'}).click();
	}
}
