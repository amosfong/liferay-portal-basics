/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {waitForAlert} from '../../utils/waitForAlert';

export class WidgetPagePage {
	readonly page: Page;

	readonly addButton: Locator;
	readonly addPanelBody: Locator;
	readonly contentTab: Locator;
	readonly toggleControlsButton: Locator;
	readonly widgetsTab: Locator;

	constructor(page: Page) {
		this.page = page;

		this.addButton = page
			.locator('.control-menu-nav-item')
			.getByRole('button', {
				exact: true,
				name: 'Add',
			});
		this.contentTab = page.getByText('Content', {
			exact: true,
		});
		this.toggleControlsButton = page
			.locator('.control-menu-nav-item')
			.getByRole('button', {
				exact: true,
				name: 'Toggle Controls',
			});
		this.widgetsTab = page.getByText('Widgets', {
			exact: true,
		});
	}

	async addContent(contentName: string) {
		await this.openAddPanel();

		await this.contentTab.click();

		await this.page
			.locator('.sidebar-body__add-panel__tab-item')
			.filter({hasText: contentName})
			.getByRole('button', {name: 'Add Content'})
			.click();

		await waitForAlert(
			this.page,
			'Success:The application was added to the page.'
		);
	}

	async addPortlet(portletName: string, category: string = undefined) {
		await this.openAddPanel();

		await this.widgetsTab.click();

		await this.page
			.getByRole('textbox', {name: 'Search Form'})
			.fill(portletName);

		if (category) {
			const categoryPanel = this.page.locator(
				'.add-content-menu .panel',
				{
					has: this.page
						.locator('.panel-header')
						.getByText(category, {exact: true}),
				}
			);

			categoryPanel
				.locator('.panel-body')
				.filter({hasText: portletName})
				.getByRole('button', {name: 'Add Content'})
				.click();
		}
		else {
			await this.page
				.locator('.sidebar-body__add-panel__tab-item')
				.filter({hasText: portletName})
				.getByRole('button', {name: 'Add Content'})
				.first()
				.click();
		}

		await waitForAlert(
			this.page,
			'Success:The application was added to the page.'
		);
	}

	async clickOnAction(portletName: string, action: string) {
		await this.page
			.locator('.portlet-topper', {hasText: portletName})
			.getByLabel('Options')
			.click();

		await this.page
			.getByRole('menuitem', {exact: true, name: action})
			.click();
	}

	async deletePortlet(portletName: string) {
		this.page.on('dialog', async (dialog) => {
			await dialog.accept();
		});

		await this.page
			.locator('.portlet-topper', {hasText: portletName})
			.getByLabel('Options')
			.click();

		await this.page
			.getByRole('menuitem', {
				name: 'Delete',
			})
			.click();
	}

	async dragPortlet(portletName: string, target: Locator) {
		const topper = this.page.locator(
			'.portlet-journal-content .portlet-topper',
			{hasText: portletName}
		);

		const targetRect = await target.evaluate((element) =>
			element.getBoundingClientRect()
		);

		await topper.hover();

		await this.page.mouse.down();

		await this.page.mouse.move(
			targetRect.x + targetRect.width / 2,
			targetRect.y + targetRect.height / 2,
			{steps: 10}
		);

		await this.page
			.locator('.sortable-layout-drag-indicator')
			.waitFor({state: 'visible'});

		await this.page.mouse.up();
	}

	async goto(layout: Layout, siteUrl?: Site['friendlyUrlPath']) {
		await this.page.goto(`/web${siteUrl || '/guest'}${layout.friendlyURL}`);
	}

	async openAddPanel() {
		const isOpen = await this.addButton.evaluate((element) =>
			element.classList.contains('open')
		);

		if (!isOpen) {
			await this.addButton.click();
		}
	}

	async save(title: string) {
		const configurationIFrame = this.page.frameLocator(
			`iframe[title*="${title}"]`
		);

		await configurationIFrame.getByRole('button', {name: 'Save'}).click();

		await waitForAlert(
			configurationIFrame,
			'Success:You have successfully updated the setup.'
		);
	}

	async saveAndClose(title: string) {
		await this.save(title);

		await this.page
			.locator('.modal-header')
			.getByLabel('close', {exact: true})
			.click();
	}

	async toggleControls(state: 'visible' | 'hidden') {
		const isOpen = await this.toggleControlsButton
			.locator('svg')
			.evaluate((element) =>
				element.classList.contains('lexicon-icon-view')
			);

		if (
			(state === 'visible' && !isOpen) ||
			(state === 'hidden' && isOpen)
		) {
			await this.toggleControlsButton.click();
		}
	}
}
