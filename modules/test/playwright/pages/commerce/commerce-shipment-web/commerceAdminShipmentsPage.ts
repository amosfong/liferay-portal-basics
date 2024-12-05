/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {ApplicationsMenuPage} from '../../product-navigation-applications-menu/ApplicationsMenuPage';
import {CommerceIframeDNDTablePage} from '../commerceIframeDNDTablePage';

export class CommerceAdminShipmentsPage extends CommerceIframeDNDTablePage {
	readonly addQuantityInShipment: Locator;
	readonly addProductsToShipment: Locator;
	readonly applicationsMenuPage: ApplicationsMenuPage;
	readonly backLink: Locator;
	readonly editProductCloseButton: Locator;
	readonly editProductMenuItem: Locator;
	readonly editProductSaveButton: Locator;
	readonly keyShipmentStatus: (orderStatus: string) => Locator;
	readonly page: Page;
	readonly productEllipsis: Locator;
	readonly shipmentIdLink: (shipmentId: string) => Locator;
	readonly shipmentsItemSubmitButton: Locator;
	readonly shipmentItemsTable: Locator;
	readonly shipmentItemsTableRow: (
		colPosition: number,
		value: number | string,
		strictEqual?: boolean
	) => Promise<{column: Locator; row: Locator}>;
	readonly shipmentItemsTableRows: () => Promise<Locator[]>;
	readonly shipmentItemsTableRowAction: (sku: string) => Promise<Locator>;
	readonly shipmentStatusLink: (shipmentStatus: string) => Locator;

	constructor(page: Page) {
		super(
			page,
			'iframe >> nth=1',
			'#_com_liferay_commerce_shipment_web_internal_portlet_CommerceShipmentPortlet_fm .dnd-table'
		);
		this.addQuantityInShipment = page
			.frameLocator('iframe')
			.getByRole('spinbutton');
		this.addProductsToShipment = page.getByText(
			'Add Products to This Shipment'
		);
		this.applicationsMenuPage = new ApplicationsMenuPage(page);
		this.backLink = page.getByRole('link', {exact: true, name: 'Back'});
		this.editProductCloseButton = page
			.frameLocator('iframe')
			.getByRole('button')
			.first();
		this.editProductMenuItem = page.getByRole('menuitem', {
			exact: true,
			name: 'Edit',
		});
		this.editProductSaveButton = page
			.frameLocator('iframe')
			.getByRole('button', {exact: true, name: 'Save'});
		this.keyShipmentStatus = (orderStatus: string) =>
			page.getByText(orderStatus);
		this.page = page;
		this.productEllipsis = page.getByRole('button', {
			exact: true,
			name: 'Actions',
		});
		this.shipmentIdLink = (shipmentId: string) =>
			page
				.locator('.dnd-table')
				.getByRole('link', {exact: true, name: shipmentId});
		this.shipmentsItemSubmitButton = page
			.frameLocator('iframe >> nth=1')
			.getByRole('button', {exact: true, name: 'Submit'});

		this.shipmentItemsTable = this.table;
		this.shipmentItemsTableRow = this.tableRow;
		this.shipmentItemsTableRows = this.tableRows;
		this.shipmentItemsTableRowAction = async (sku: string) => {
			const shipmentTableRow = await this.shipmentItemsTableRow(
				1,
				sku,
				true
			);

			if (shipmentTableRow && shipmentTableRow.column) {
				return shipmentTableRow.row.getByLabel('', {exact: true});
			}

			throw new Error(`Cannot locate shipment row with value ${sku}`);
		};
		this.shipmentStatusLink = (shipmentStatus: string) =>
			page.getByRole('link', {exact: true, name: shipmentStatus});
	}

	async goTo() {
		await this.applicationsMenuPage.goToCommerceShipments();
	}
}
