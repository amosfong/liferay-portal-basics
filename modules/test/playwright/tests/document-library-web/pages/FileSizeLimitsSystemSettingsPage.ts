/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {SystemSettingsPage} from '../../../pages/configuration-admin-web/SystemSettingsPage';
import {waitForAlert} from '../../../utils/waitForAlert';

export class FileSizeLimitsSystemSettingsPage {
	readonly page: Page;
	readonly saveButton: Locator;
	readonly systemSettingsPage: SystemSettingsPage;

	constructor(page: Page) {
		this.page = page;
		this.saveButton = page.getByRole('button', {name: 'Save'});
		this.systemSettingsPage = new SystemSettingsPage(page);
	}

	async goto() {
		await this.systemSettingsPage.goToSystemSetting(
			'Documents and Media',
			'File Size Limits'
		);
	}

	async modifyInputAndSave(label: string, value: string) {
		const inputField = this.page.getByLabel(label);
		await inputField.click();
		await inputField.fill(value);

		await this.saveButton.click();
		await waitForAlert(this.page);
	}
}