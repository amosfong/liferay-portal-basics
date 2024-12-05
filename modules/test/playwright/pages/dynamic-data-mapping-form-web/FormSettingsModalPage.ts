/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

export class FormSettingsModalPage {
	readonly doneButton: Locator;
	readonly objectSelect: Locator;
	readonly page: Page;
	readonly storageTypeSelect: Locator;

	constructor(page: Page) {
		this.doneButton = page.getByRole('button', {name: 'Done'});
		this.objectSelect = page.getByLabel('Select Object');
		this.page = page;
		this.storageTypeSelect = page.getByLabel('Select a Storage Type');
	}

	async clickDoneButton() {
		await this.doneButton.click();
	}

	async selectObject(objectLabel: string) {
		await this.objectSelect.click();

		const option = this.getSelectOptionLocator(objectLabel);
		await option.click();
	}

	async selectStorageType(storageTypeLabel: string) {
		await this.storageTypeSelect.click();

		const option = this.getSelectOptionLocator(storageTypeLabel);
		await option.click();
	}

	getSelectOptionLocator = (optionLabel: string) => {
		return this.page.getByRole('option', {name: optionLabel});
	};
}
