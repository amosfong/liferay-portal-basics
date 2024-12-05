/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {PORTLET_URLS} from '../../utils/portletUrls';

export class NotificationTemplatesPage {
	readonly page: Page;
	readonly frontEndDatasetItemAction: Locator;
	readonly frontEndDatasetItemActionDelete: Locator;
	readonly newNotificationTemplateButton: Locator;
	readonly emailNotificationDropdownItem: Locator;

	constructor(page: Page) {
		this.page = page;
		this.emailNotificationDropdownItem = page
			.getByRole('menuitem')
			.filter({hasText: 'Email'});
		this.frontEndDatasetItemAction = page.getByRole('button', {
			name: 'Actions',
		});
		this.frontEndDatasetItemActionDelete = page.getByRole('menuitem', {
			name: 'Delete',
		});
		this.newNotificationTemplateButton = page.getByTitle('New').first();
	}

	getFrontEndDatasetItemLocator(notificationTemplateName: string) {
		return this.page.getByRole('link', {name: notificationTemplateName});
	}

	async goto(siteUrl?: Site['friendlyUrlPath']) {
		await this.page.goto(
			`/group${siteUrl || '/guest'}${PORTLET_URLS.notificationTemplates}`
		);
	}

	async openNotificationTemplate(notificationName: string) {
		await this.page.getByRole('link', {name: notificationName}).click();
	}
}
