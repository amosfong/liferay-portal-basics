/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Page} from '@playwright/test';

export class QuestionsTopicsPage {
	readonly page: Page;

	constructor(page: Page) {
		this.page = page;
	}

	async goto() {}

	async addNewTopic(topicName: string) {
		await this.goto();
		await this.page.getByText('New Topic', {exact: true}).click();
		await this.page
			.getByPlaceholder('Please enter a valid topic name.')
			.fill(topicName);
		await this.page.getByLabel('Create').click();
	}

	async goToTopic(topicName: string) {
		await this.goto();
		await this.page.getByRole('link', {name: topicName}).click();
	}
}