/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ObjectActionApi} from '@liferay/object-admin-rest-client-js';
import {expect, mergeTests} from '@playwright/test';

import {applicationsMenuPageTest} from '../../../fixtures/applicationsMenuPageTest';
import {commercePagesTest} from '../../../fixtures/commercePagesTest';
import {dataApiHelpersTest} from '../../../fixtures/dataApiHelpersTest';
import {loginTest} from '../../../fixtures/loginTest';
import {notificationPagesTest} from '../../../fixtures/notificationPagesTest';
import {usersAndOrganizationsPagesTest} from '../../../fixtures/usersAndOrganizationsPagesTest';
import {liferayConfig} from '../../../liferay.config';
import getRandomString from '../../../utils/getRandomString';
import performLogin, {performLogout} from '../../../utils/performLogin';
import {miniumSetUp} from '../utils/commerce';

export const test = mergeTests(
	applicationsMenuPageTest,
	commercePagesTest,
	dataApiHelpersTest,
	loginTest(),
	notificationPagesTest,
	usersAndOrganizationsPagesTest
);

test('LPD-13627 Edit pending order item with UOM', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
	pendingOrdersPage,
}) => {
	const site = await apiHelpers.headlessSite.createSite({
		name: 'Edit pending order',
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		name: 'Edit pending order Channel',
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog({
		name: 'Edit pending order Catalog',
	});

	const product1 = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		name: {en_US: 'Product1'},
	});

	const sku1 = product1.skus[0];

	const uom1 =
		await apiHelpers.headlessCommerceAdminCatalog.postSkuUnitOfMeasure(
			sku1.id,
			{
				incrementalOrderQuantity: 3,
				name: {en_US: 'Box'},
				primary: true,
				priority: 1,
				rate: 1,
			}
		);

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['test@liferay.com']
	);

	await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					options: '[]',
					quantity: 3,
					replacedSkuId: 0,
					skuId: sku1.id,
					skuUnitOfMeasure: {key: uom1.key},
				},
			],
		},
		channel.id
	);

	await applicationsMenuPage.goToSite('Edit pending order');

	await commerceLayoutsPage.goToPages(false);
	await commerceLayoutsPage.createWidgetPage('Pending Orders Page');

	await page.goto(`/web/${site.name}`);

	await pendingOrdersPage.addPendingOrdersWidget();

	await pendingOrdersPage.viewButton.click();

	await pendingOrdersPage.orderItemActionsButton.click();

	await expect(pendingOrdersPage.orderItemActionsButtonEdit).toBeVisible();
});

test('LPD-13627 Edit pending order item without UOM', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
	pendingOrdersPage,
}) => {
	const site = await apiHelpers.headlessSite.createSite({
		name: 'Edit pending order',
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		name: 'Edit pending order Channel',
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog({
		name: 'Edit pending order Catalog',
	});

	const product1 = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		name: {en_US: 'Product1'},
	});

	const product1Skus = await apiHelpers.headlessCommerceAdminCatalog
		.getProduct(product1.productId)
		.then((product) => {
			return product.skus;
		});

	const sku1 = product1Skus[0];

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['test@liferay.com']
	);

	await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					options: '[]',
					quantity: 1,
					replacedSkuId: 0,
					skuId: sku1.id,
				},
			],
		},
		channel.id
	);

	await applicationsMenuPage.goToSite('Edit pending order');

	await commerceLayoutsPage.goToPages(false);
	await commerceLayoutsPage.createWidgetPage('Pending Orders Page');

	await page.goto(`/web/${site.name}`);

	await pendingOrdersPage.addPendingOrdersWidget();

	await pendingOrdersPage.viewButton.click();

	await pendingOrdersPage.orderItemActionsButton.click();

	await expect(pendingOrdersPage.orderItemActionsButtonEdit).toHaveCount(0);
});

test('LPD-4174 Sales agent can receive email notifications for new orders placed to their accounts', async ({
	apiHelpers,
	applicationsMenuPage,
	checkoutPage,
	commerceMiniCartPage,
	page,
	queuePage,
}) => {
	test.setTimeout(180000);

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: 'Sales agent can receive email notifications account',
		type: 'business',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['test@liferay.com']
	);

	const user =
		await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
			'test@liferay.com'
		);

	const roles = await apiHelpers.headlessAdminUser.getRoles('Sales Agent');

	await apiHelpers.headlessAdminUser.postRoleUserAccountAssociation(
		roles.items[0].id,
		user.id
	);

	apiHelpers.data.push({
		id: `${roles.items[0].id}_${user.id}`,
		type: 'roleUserAccountAssociation',
	});

	const {site} = await miniumSetUp(apiHelpers);

	const notificationTemplate =
		await apiHelpers.notification.postNotificationTemplate({
			editorType: 'richText',
			name: 'Sales agent can receive email notifications Template',
			recipientType: 'email',
			recipients: [
				{
					from: 'do-not-reply@liferay.com',
					fromName: {
						en_US: 'do-not-replay@liferay.com',
					},
					to: {
						en_US: '[%SALES_AGENT%]',
					},
					toType: 'email',
				},
			],
			subject: {
				en_US: 'Sales agent can receive email notifications',
			},
			type: 'email',
		});

	apiHelpers.data.push({
		id: notificationTemplate.id,
		type: 'notificationTemplate',
	});

	const objectActionApiClient =
		await apiHelpers.buildRestClient(ObjectActionApi);

	const {body: objectAction} =
		await objectActionApiClient.postObjectDefinitionByExternalReferenceCodeObjectAction(
			'L_COMMERCE_ORDER',
			{
				active: true,
				label: {
					en_US: 'commerceOrderStatusOnChange',
				},
				name: 'commerceOrderStatusOnChange',
				objectActionExecutorKey: 'notification',
				objectActionTriggerKey: 'liferay/commerce_order_status',
				parameters: {
					notificationTemplateId: notificationTemplate.id,
				},
			}
		);

	apiHelpers.data.push({
		id: objectAction.id,
		type: 'objectAction',
	});

	await applicationsMenuPage.goToSite(site.name);

	await commerceMiniCartPage.miniCartButton.click();
	await commerceMiniCartPage.searchProductsInput.fill('MIN55861');
	await commerceMiniCartPage.quickAddToCartSku('MIN55861').click();
	await commerceMiniCartPage.quickAddToCartButton.click();
	await commerceMiniCartPage.submitButton.click();

	await checkoutPage.nameInput.fill('name');
	await checkoutPage.addressInput.fill('address');
	await checkoutPage.zipInput.fill('1234');
	await checkoutPage.phoneNumberInput.fill('1234');
	await checkoutPage.cityInput.fill('city');
	await checkoutPage.countryInput.selectOption({label: 'Italy'});
	await checkoutPage.continueButton.click();
	await checkoutPage.continueButton.click();
	await checkoutPage.continueButton.click();

	await expect(checkoutPage.orderSuccessMessage).toBeVisible();

	await applicationsMenuPage.goToQueue();

	try {
		await expect(queuePage.pageTitle).toBeVisible();
		await expect(
			page.getByText('Sales agent can receive email notifications')
		).toHaveCount(1);
	}
	finally {
		const notificationQueueEntry =
			await apiHelpers.notification.getNotificationQueueEntriesPage(
				'Sales agent can receive email notifications'
			);

		apiHelpers.data.push({
			id: notificationQueueEntry.items[0].id,
			type: 'notificationQueueEntry',
		});
	}
});

test('COMMERCE-7697 Verify user can download CSV template', async ({
	apiHelpers,
	page,
}) => {
	test.setTimeout(180000);

	const {channel, site} = await miniumSetUp(apiHelpers);

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: 'Download CSV',
		type: 'business',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['test@liferay.com']
	);

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
		},
		channel.id
	);

	await page.goto(
		`/web/${site.name}/pending-orders/-/pending-order/${cart.id}`
	);

	await page
		.locator(
			"//div[contains(@class, 'dropdown')]/a[contains(@class, 'action') and contains(@class, 'btn-primary')]"
		)
		.click();
	await page.getByRole('menuitem', {name: 'Import from CSV'}).click();

	const downloadPromise = page.waitForEvent('download');

	await page
		.frameLocator('iframe[title="Import from CSV"]')
		.getByRole('button', {name: 'Download Template'})
		.click();

	const download = await downloadPromise;
	expect(download.suggestedFilename()).toEqual('csv_template.csv');
});

test('LPD-28683 When clicking on order item without visibility the user is not redirected to the catalog page', async ({
	apiHelpers,
	commerceMiniCartPage,
	commerceThemeMiniumPage,
	page,
	pendingOrdersPage,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: 'admin',
		type: 'business',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const {site} = await miniumSetUp(apiHelpers);

	const accountGroup = await apiHelpers.headlessAdminUser.postAccountGroup({
		name: 'AG1',
	});

	apiHelpers.data.push({id: accountGroup.id, type: 'accountGroup'});

	await apiHelpers.headlessAdminUser.assignAccountToAccountGroup(
		account.externalReferenceCode,
		accountGroup.externalReferenceCode
	);

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['demo.unprivileged@liferay.com']
	);

	const user =
		await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
			'demo.unprivileged@liferay.com'
		);

	const rolesResponse = await apiHelpers.headlessAdminUser.getAccountRoles(
		account.id
	);

	const accountRoleBuyer = rolesResponse?.items?.filter((role) => {
		return role.name === 'Buyer';
	});

	await apiHelpers.headlessAdminUser.assignAccountRoles(
		account.externalReferenceCode,
		accountRoleBuyer[0].id,
		user.emailAddress
	);

	const product = await apiHelpers.headlessCommerceAdminCatalog.getProducts(
		new URLSearchParams({
			filter: `name eq 'U-Joint'`,
			nestedFields: `productSkus`,
		})
	);

	await apiHelpers.headlessCommerceAdminCatalog.patchProduct(
		product.items[0].productId,
		{
			productAccountGroupFilter: true,
			productAccountGroups: [
				{
					accountGroupId: accountGroup.id,
				},
			],
		}
	);

	const productAccountGroups =
		await apiHelpers.headlessCommerceAdminCatalog.getProductAccountGroups(
			product.items[0].productId
		);

	const siteRole =
		await apiHelpers.headlessAdminUser.getRoleByName('Site Member');

	await apiHelpers.headlessAdminUser.assignUserToSite(
		siteRole.id,
		site.id,
		user.id
	);

	await performLogout(page);

	await performLogin(page, user.alternateName);

	await page.goto(`/web/${site.name}`);

	await commerceMiniCartPage.quickAddToCart(product.items[0].skuFormatted);

	await expect(
		await commerceMiniCartPage.priceField(
			'$ 24.00',
			commerceMiniCartPage.miniCartItemsContainer
		)
	).toBeVisible();

	await apiHelpers.headlessCommerceAdminCatalog.deleteProductAccountGroup(
		productAccountGroups.items[0].id
	);

	await commerceMiniCartPage.viewDetailsButton.click();

	await expect(
		page.getByText('One or more products are no longer available.')
	).toBeVisible();

	await pendingOrdersPage.errorMessageCloseButton.click();
	await pendingOrdersPage.skuLink(product.items[0].skuFormatted).click();

	await expect(
		await commerceThemeMiniumPage.goToMiniumLink(site.name)
	).toBeVisible();
});

test('LPD-26906 As a buyer, I can edit product options from the pending orders page', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceAdminProductPage,
	commerceMiniCartPage,
	page,
	pendingOrdersPage,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		type: 'business',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const {site} = await miniumSetUp(apiHelpers);

	const user =
		await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
			'demo.unprivileged@liferay.com'
		);

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['demo.unprivileged@liferay.com']
	);

	const companyId = await page.evaluate(() => {
		return Liferay.ThemeDisplay.getCompanyId();
	});

	const role = await apiHelpers.headlessAdminUser.postRole({
		name: 'Buyer ' + getRandomString(),
		rolePermissions: [
			{
				actionIds: ['MANAGE_ADDRESSES', 'VIEW_ADDRESSES'],
				primaryKey: '0',
				resourceName: 'com.liferay.account.model.AccountEntry',
				scope: 3,
			},
			{
				actionIds: ['VIEW'],
				primaryKey: companyId,
				resourceName: 'com.liferay.commerce.model.CommerceOrderType',
				scope: 1,
			},
			{
				actionIds: [
					'ADD_COMMERCE_ORDER',
					'CHECKOUT_OPEN_COMMERCE_ORDERS',
					'MANAGE_COMMERCE_ORDER_DELIVERY_TERMS',
					'MANAGE_COMMERCE_ORDER_PAYMENT_METHODS',
					'MANAGE_COMMERCE_ORDER_PAYMENT_TERMS',
					'MANAGE_COMMERCE_ORDER_SHIPPING_OPTIONS',
					'VIEW_BILLING_ADDRESS',
					'VIEW_COMMERCE_ORDERS',
					'VIEW_OPEN_COMMERCE_ORDERS',
				],
				primaryKey: '0',
				resourceName: 'com.liferay.commerce.order',
				scope: 3,
			},
		],
	});

	await apiHelpers.headlessAdminUser.postRoleUserAccountAssociation(
		role.id,
		user.id
	);

	apiHelpers.data.push({
		id: `${role.id}_${user.id}`,
		type: 'roleUserAccountAssociation',
	});

	await apiHelpers.jsonWebServicesUser.addGroupUsers(site.id, [user.id]);

	const option = await apiHelpers.headlessCommerceAdminCatalog.postOption(
		'select',
		getRandomString(),
		'Color',
		1
	);

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product1 = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		name: {en_US: getRandomString()},
	});

	const product2 = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		name: {en_US: getRandomString()},
	});

	const productBundleName = getRandomString();

	await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		name: {en_US: productBundleName},
		productOptions: [
			{
				fieldType: 'select',
				key: option.key,
				name: option.name,
				optionId: option.id,
				priceType: 'static',
				priority: 1,
				productOptionValues: [
					{
						deltaPrice: 10.0,
						key: 'black',
						name: {
							en_US: 'Black',
						},
						priority: 1,
						quantity: 1,
						skuId: product1.skus[0].id,
					},
					{
						deltaPrice: 20.0,
						key: 'white',
						name: {
							en_US: 'White',
						},
						priority: 2,
						quantity: 1,
						skuId: product2.skus[0].id,
					},
				],
				skuContributor: true,
			},
		],
	});

	await applicationsMenuPage.goToProducts();

	await commerceAdminProductPage.managementToolbarSearchInput.fill(
		productBundleName
	);
	await commerceAdminProductPage.managementToolbarSearchInput.press('Enter');
	await commerceAdminProductPage
		.managementToolbarItemLink(productBundleName)
		.click();
	await commerceAdminProductPage.generateSkus();

	await expect(page.getByText('Showing 1 to 3 of 3 entries.')).toBeVisible();

	await performLogout(page);

	await performLogin(page, user.alternateName);

	await page.goto(
		`${liferayConfig.environment.baseUrl}/web${site.friendlyUrlPath}/catalog`
	);

	await commerceMiniCartPage.miniCartButton.click();
	await commerceMiniCartPage.searchProductsInput.fill('BLACK');
	await commerceMiniCartPage.quickAddToCartSku('BLACK').click();
	await commerceMiniCartPage.quickAddToCartButton.click();
	await commerceMiniCartPage.searchProductsInput.fill('MIN55858');
	await commerceMiniCartPage.quickAddToCartSku('MIN55858').click();
	await commerceMiniCartPage.quickAddToCartButton.click();
	await commerceMiniCartPage.searchProductsInput.fill('MIN93016A');
	await commerceMiniCartPage.quickAddToCartSku('MIN93016A').click();
	await commerceMiniCartPage.quickAddToCartButton.click();

	await pendingOrdersPage.layoutsPage.pendingOrdersLink.click();
	await page.getByLabel('View').click();

	await expect(page.getByText('$ 4.00').nth(1)).toBeVisible();
	await expect(page.getByText('$ 72.00').nth(1)).toBeVisible();
	await expect(page.getByText('$ 10.00').nth(1)).toBeVisible();
	await expect(page.getByText('$ 10.00').nth(3)).toBeVisible();

	await (
		await pendingOrdersPage.orderItemsTableRowLink('Brake Fluid')
	).click();

	await pendingOrdersPage.editMenuItem.click();

	await commerceMiniCartPage.selectOption('48', 'Package Quantity');
	await commerceMiniCartPage.miniCartSaveButton.click();

	await page.reload();

	await (
		await pendingOrdersPage.orderItemsTableRowLink(productBundleName)
	).click();

	await pendingOrdersPage.editMenuItem.click();

	await commerceMiniCartPage.selectOption('White', 'Color');
	await commerceMiniCartPage.miniCartSaveButton.click();

	await page.reload();

	await (
		await pendingOrdersPage.orderItemsTableRowLink('Wheel Seal - Front')
	).click();

	await expect(pendingOrdersPage.editMenuItem).toHaveCount(0);

	await expect(page.getByText('$ 4.00').nth(1)).toBeVisible();
	await expect(page.getByText('$ 72.00').nth(1)).toBeVisible();
	await expect(page.getByText('$ 20.00').nth(1)).toBeVisible();
	await expect(page.getByText('$ 20.00').nth(3)).toBeVisible();

	await commerceMiniCartPage.miniCartButton.click();

	await expect(
		commerceMiniCartPage.miniCartItemPrice(/^List Price\$ 4\.00$/)
	).toBeVisible();
	await expect(
		commerceMiniCartPage.miniCartItemPrice(
			/^List Price\$ 80\.00Promotion Price\$ 72\.00$/
		)
	).toBeVisible();
	await expect(
		commerceMiniCartPage.miniCartItemPrice(/^List Price\$ 20\.00$/)
	).toBeVisible();
});

test('LPD-3259 As a buyer with approval workflow, when I click review order in minicart, I get redirect to pending orders page', async ({
	apiHelpers,
	commerceAdminChannelDetailsPage,
	commerceAdminChannelsPage,
	commerceMiniCartPage,
	page,
	pendingOrdersPage,
}) => {
	const {site} = await miniumSetUp(apiHelpers);

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'business',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const user =
		await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
			'demo.unprivileged@liferay.com'
		);
	const rolesResponse = await apiHelpers.headlessAdminUser.getAccountRoles(
		account.id
	);

	const accountRoleBuyer = rolesResponse?.items?.filter((role) => {
		return role.name === 'Buyer';
	});

	await apiHelpers.headlessAdminUser.assignAccountRoles(
		account.externalReferenceCode,
		accountRoleBuyer[0].id,
		user.emailAddress
	);
	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['demo.unprivileged@liferay.com']
	);
	const siteRole =
		await apiHelpers.headlessAdminUser.getRoleByName('Site Member');
	await apiHelpers.headlessAdminUser.assignUserToSite(
		siteRole.id,
		site.id,
		user.id
	);

	const channels =
		await apiHelpers.headlessCommerceAdminChannel.getChannelsPage(
			`${site.name} Portal`
		);

	await commerceAdminChannelsPage.changeCommerceChannelBuyerOrderApprovalWorkflow(
		'Single Approver (Version 1)',
		channels.items[0].name
	);

	await (
		await commerceAdminChannelDetailsPage.commerceChannelHealthChecksTableRowAction(
			'Fix Issue',
			'Commerce Cart'
		)
	).click();

	const product = await apiHelpers.headlessCommerceAdminCatalog.getProducts(
		new URLSearchParams({
			filter: `name eq 'Abs Sensor'`,
		})
	);

	const productId = product.items[0].productId;

	const productSkus = await apiHelpers.headlessCommerceAdminCatalog
		.getProduct(productId)
		.then((product) => {
			return product.skus;
		});

	const sku = productSkus[0];

	await performLogout(page);

	await performLogin(page, 'demo.unprivileged');

	await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					options: '[]',
					quantity: 1,
					replacedSkuId: 0,
					skuId: sku.id,
				},
			],
		},
		channels.items[0].id
	);

	await page.goto(`/web/${site.name}`);

	await commerceMiniCartPage.miniCartButton.click();
	await commerceMiniCartPage.reviewOrderButton.click();

	await expect(pendingOrdersPage.orderItemsTable).toBeVisible();
});

test('LPD-33783 Pending orders table displays correct fields', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
	pendingOrdersPage,
}) => {
	const site = await apiHelpers.headlessSite.createSite({
		name: 'Pending order',
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		name: 'Pending order Channel',
		siteGroupId: site.id,
	});

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	await apiHelpers.headlessCommerceAdminOrder.postOrder({
		accountId: account.id,
		channelId: channel.id,
		name: 'order1',
		orderStatus: '2',
	});

	await applicationsMenuPage.goToSite('Pending order');

	await commerceLayoutsPage.goToPages(false);
	await commerceLayoutsPage.createWidgetPage('Pending Orders Page');

	await page.goto(`/web/${site.name}`);

	await pendingOrdersPage.addPendingOrdersWidget();

	await expect(pendingOrdersPage.orderItemsTable).toBeVisible();

	const tableHeaderLabels = [
		'Order ID',
		'Name',
		'Order Type',
		'ERC',
		'Purchase Order Number',
		'Create Date',
		'Account',
		'Created By',
		'Status',
		'Amount',
	];

	await expect(await pendingOrdersPage.tableHeaders.innerText()).toEqual(
		tableHeaderLabels.join('\n')
	);
});

test('LPD-3440 As a order manager with buyer approval workflow, I can approve orders on pending orders page', async ({
	apiHelpers,
	commerceAdminChannelDetailsPage,
	commerceAdminChannelsPage,
	commerceLayoutsPage,
	commerceMiniCartPage,
	page,
	pendingOrdersPage,
}) => {
	const {channel, site} = await miniumSetUp(apiHelpers);

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'business',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const user =
		await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
			'demo.unprivileged@liferay.com'
		);
	const rolesResponse = await apiHelpers.headlessAdminUser.getAccountRoles(
		account.id
	);

	const accountRoleOrderManager = rolesResponse?.items?.filter((role) => {
		return role.name === 'Order Manager';
	});

	await apiHelpers.headlessAdminUser.assignAccountRoles(
		account.externalReferenceCode,
		accountRoleOrderManager[0].id,
		user.emailAddress
	);
	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['demo.unprivileged@liferay.com']
	);
	const siteRole =
		await apiHelpers.headlessAdminUser.getRoleByName('Site Member');
	await apiHelpers.headlessAdminUser.assignUserToSite(
		siteRole.id,
		site.id,
		user.id
	);

	await commerceAdminChannelsPage.changeCommerceChannelBuyerOrderApprovalWorkflow(
		'Single Approver (Version 1)',
		channel.name
	);

	await (
		await commerceAdminChannelDetailsPage.commerceChannelHealthChecksTableRowAction(
			'Fix Issue',
			'Commerce Cart'
		)
	).click();

	const product = await apiHelpers.headlessCommerceAdminCatalog.getProducts(
		new URLSearchParams({
			filter: `name eq 'Abs Sensor'`,
		})
	);

	const productId = product.items[0].productId;

	const productSkus = await apiHelpers.headlessCommerceAdminCatalog
		.getProduct(productId)
		.then((product) => {
			return product.skus;
		});

	const sku = productSkus[0];

	const phoneNumber = '12345';

	const address = await apiHelpers.headlessCommerceAdminAccount.postAddress(
		account.id,
		{phoneNumber, regionISOCode: 'AL'}
	);

	await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			billingAddressId: address.id,
			cartItems: [
				{
					options: '[]',
					quantity: 1,
					replacedSkuId: 0,
					skuId: sku.id,
				},
			],
			shippingAddressId: address.id,
			shippingMethod: 'fixed',
		},
		channel.id
	);

	await page.goto(`/web/${site.name}`);

	await commerceMiniCartPage.miniCartButton.click();
	await commerceMiniCartPage.reviewOrderButton.click();
	await commerceMiniCartPage.submitButton.click();

	await expect(commerceMiniCartPage.submitButton).toBeHidden();

	await performLogout(page);

	await performLogin(page, 'demo.unprivileged');

	await page.goto(`/web/${site.name}`);

	await commerceLayoutsPage.pendingOrdersLink.click();

	await pendingOrdersPage.viewButton.click();
	await pendingOrdersPage.approveButton.click();
	await pendingOrdersPage.doneButton.click();

	await expect(pendingOrdersPage.checkoutButton).toBeVisible();
});
