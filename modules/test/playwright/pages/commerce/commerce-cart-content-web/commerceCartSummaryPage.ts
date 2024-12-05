/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {CommerceLayoutsPage} from '../commerce-order-content-web/commerceLayoutsPage';

export class CommerceCartSummaryPage {
	readonly checkoutButton: Locator;
	readonly layoutsPage: CommerceLayoutsPage;
	readonly orderItemActionsButton: Locator;
	readonly orderItemActionsButtonEdit: Locator;
	readonly page: Page;
	readonly pageLabel: Locator;
	readonly pageTitle: Locator;
	readonly panelList: Locator;
	readonly viewButton: Locator;

	constructor(page: Page) {
		this.checkoutButton = page.getByRole('button', {name: 'Checkout'});
		this.layoutsPage = new CommerceLayoutsPage(page);
		this.orderItemActionsButton = page.getByRole('button', {
			name: 'Actions',
		});
		this.orderItemActionsButtonEdit = page.getByRole('menuitem', {
			name: 'Edit',
		});
		this.page = page;
		this.pageLabel = page
			.getByTestId('layoutHref')
			.getByLabel('Commerce Cart Page');
		this.pageTitle = page
			.getByTestId('headerTitle')
			.filter({hasText: 'Commerce Cart Page'});
		this.panelList = page
			.getByTestId('specificationFacetPanel')
			.getByRole('button');
		this.viewButton = page.getByLabel('View');
	}

	async addCartSummaryWidget() {
		await this.layoutsPage.addWidgetToPage('Cart Summary');
	}

	async goto() {
		await this.layoutsPage.goto();
	}
}
