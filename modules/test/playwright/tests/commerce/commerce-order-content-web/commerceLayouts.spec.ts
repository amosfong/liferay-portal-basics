/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';
import path from 'path';

import {apiHelpersTest} from '../../../fixtures/apiHelpersTest';
import {applicationsMenuPageTest} from '../../../fixtures/applicationsMenuPageTest';
import {commercePagesTest} from '../../../fixtures/commercePagesTest';
import {dataApiHelpersTest} from '../../../fixtures/dataApiHelpersTest';
import {displayPageTemplatesPagesTest} from '../../../fixtures/displayPageTemplatesPagesTest';
import {featureFlagsTest} from '../../../fixtures/featureFlagsTest';
import {loginTest} from '../../../fixtures/loginTest';
import {liferayConfig} from '../../../liferay.config';
import {getRandomInt} from '../../../utils/getRandomInt';
import getRandomString from '../../../utils/getRandomString';
import performLogin, {performLogout} from '../../../utils/performLogin';
import {waitForAlert} from '../../../utils/waitForAlert';
import getPageDefinition from '../../layout-content-page-editor-web/utils/getPageDefinition';
import getWidgetDefinition from '../../layout-content-page-editor-web/utils/getWidgetDefinition';
import {commerceReturnSetUp} from '../utils/commerce';
import {customFormatDate, getDateCustomFormat} from '../utils/date';

export const test = mergeTests(
	apiHelpersTest,
	applicationsMenuPageTest,
	commercePagesTest,
	dataApiHelpersTest,
	displayPageTemplatesPagesTest,
	featureFlagsTest({
		'LPD-11147': true,
		'LPD-20379': true,
	}),
	loginTest()
);

test('LPD-25926 Display page template edit mode works with Speedwell theme', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const displayPageTemplateName = getRandomString();

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		displayPageTemplateName,
		'Blogs Entry',
		site.name
	);
	await commerceLayoutsPage.addFragment('Heading');

	await expect(page.getByText('Heading Example')).toBeVisible();

	await commerceLayoutsPage.publishButton.click();
	await commerceLayoutsPage.configureDisplayPageTemplateTheme(
		'Select Speedwell By Liferay, Inc.'
	);

	await expect(
		page.getByText('Success:The page was updated successfully.')
	).toBeVisible();

	await commerceLayoutsPage.backLink.click();

	await commerceLayoutsPage
		.displayPageTemplateLink(displayPageTemplateName)
		.click();

	await expect(page.getByText('Heading Example')).toBeVisible();
});

test('LPD-33439 Default order display page template is accessible via friendly URL', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Heading');

	await expect(page.getByText('Heading Example')).toBeVisible();

	await commerceLayoutsPage.publishButton.click();
	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('Heading Example')).toBeVisible();
});

test('LPD-32227 Order info box fragment configuration', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');
	await commerceLayoutsPage.infoBoxReadOnlyToggle.uncheck();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption(
		'purchaseOrderNumber'
	);

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.infoBoxLabelInput.fill('PON');
	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('PON')).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('PON').click();
	await commerceLayoutsPage.inputTextbox('PON').fill('testPON');
	await commerceLayoutsPage.saveButton.click();

	await expect(page.getByText('testPON')).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('PON').click();
	await commerceLayoutsPage.inputTextbox('PON').fill('testPONEdited');
	await commerceLayoutsPage.saveButton.click();

	await expect(page.getByText('testPONEdited')).toBeVisible();

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.editMenuItem.click();
	await commerceLayoutsPage.firstFragment.click();
	await commerceLayoutsPage.infoBoxFieldSelect.selectOption('accountInfo');

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxReadOnlyToggle.check();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.infoBoxLabelInput.fill('Account Info');
	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('Account Info')).toBeVisible();
	await expect(page.getByText(account.name)).toBeVisible();
	await expect(page.getByText(String(account.id))).toBeVisible();
	await expect(commerceLayoutsPage.infoBoxButton('PON')).toBeHidden();
});

test('LPD-32236 Order Step Tracker fragment configuration', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Step Tracker', 'Order');
	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await apiHelpers.headlessCommerceDeliveryCart.checkoutCart(cart.id);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(commerceLayoutsPage.stepTrackerItem('Pending')).toHaveClass(
		/active/
	);
	await expect(
		commerceLayoutsPage.stepTrackerItem('Processing')
	).not.toHaveClass(/active/);
	await expect(
		commerceLayoutsPage.stepTrackerItem('Shipped')
	).not.toHaveClass(/active/);
	await expect(
		commerceLayoutsPage.stepTrackerItem('Completed')
	).not.toHaveClass(/active/);
});

test('LPD-32232 Edit Requested Delivery Date in Open Order Details', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');
	await commerceLayoutsPage.infoBoxReadOnlyToggle.uncheck();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption(
		'requestedDeliveryDate'
	);

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.infoBoxLabelInput.fill('Requested Delivery Date');
	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('Requested Delivery Date')).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Requested Delivery Date').click();
	await commerceLayoutsPage
		.inputTextbox('Requested Delivery Date')
		.fill('2024-09-11');
	await commerceLayoutsPage.saveButton.click();

	await expect(page.getByText('9/11/24', {exact: true})).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Requested Delivery Date').click();
	await commerceLayoutsPage
		.inputTextbox('Requested Delivery Date')
		.fill('2024-09-13');
	await commerceLayoutsPage.saveButton.click();

	await expect(page.getByText('9/13/24', {exact: true})).toBeVisible();

	await apiHelpers.headlessCommerceDeliveryCart.checkoutCart(cart.id);

	await page.reload();

	await expect(
		commerceLayoutsPage.infoBoxButton('Requested Delivery Date')
	).toBeHidden();
});

test('LPD-33808 Edit Shipping Method in Open Order Details', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceAdminChannelsPage,
	commerceLayoutsPage,
	page,
}) => {
	test.setTimeout(180000);

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');
	await commerceLayoutsPage.infoBoxReadOnlyToggle.uncheck();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption('shippingMethod');
	await commerceLayoutsPage.infoBoxLabelInput.fill('Shipping Method');

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const address = await apiHelpers.headlessCommerceAdminAccount.postAddress(
		account.id,
		{phoneNumber: '1234567890', regionISOCode: 'AL'}
	);

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
			shippingAddressId: address.id,
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(
		commerceLayoutsPage.infoBoxButton('Shipping Method')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Shipping Method').click();

	await expect(commerceLayoutsPage.infoBoxShippingMethodAlert).toBeVisible();

	await commerceLayoutsPage.infoBoxCancelButton.click();

	const shippingOptions = [getRandomString(), getRandomString()];

	await commerceAdminChannelsPage.setupCommerceChannelShippingMethod(
		channel.name,
		'Flat Rate',
		shippingOptions
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(
		commerceLayoutsPage.infoBoxButton('Shipping Method')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Shipping Method').click();

	await expect(
		commerceLayoutsPage.infoBoxValue(shippingOptions[0])
	).toHaveCount(0);

	await commerceLayoutsPage.infoBoxShippingMethodSelect.selectOption(
		'Flat Rate'
	);

	await expect(
		commerceLayoutsPage.infoBoxValue(shippingOptions[0])
	).toBeVisible();

	await commerceLayoutsPage.infoBoxValue(shippingOptions[0]).click();
	await commerceLayoutsPage.saveButton.click();

	await expect(
		commerceLayoutsPage.infoBoxValue('Flat Rate - ' + shippingOptions[0])
	).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Shipping Method').click();
	await commerceLayoutsPage.infoBoxValue(shippingOptions[1]).click();
	await commerceLayoutsPage.saveButton.click();

	await expect(
		commerceLayoutsPage.infoBoxValue('Flat Rate - ' + shippingOptions[1])
	).toBeVisible();

	await page.reload();

	await expect(
		commerceLayoutsPage.infoBoxValue('Flat Rate - ' + shippingOptions[1])
	).toBeVisible();

	await apiHelpers.headlessCommerceDeliveryCart.checkoutCart(cart.id);

	await page.reload();

	await expect(
		commerceLayoutsPage.infoBoxButton('Shipping Method')
	).toBeHidden();
});

test('LPD-33809 Edit Payment Method in Open Order Details', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceAdminChannelDetailsPage,
	commerceAdminChannelsPage,
	commerceLayoutsPage,
	page,
}) => {
	test.setTimeout(180000);

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');
	await commerceLayoutsPage.infoBoxReadOnlyToggle.uncheck();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption('paymentMethod');
	await commerceLayoutsPage.infoBoxLabelInput.fill('Payment Method');

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const address = await apiHelpers.headlessCommerceAdminAccount.postAddress(
		account.id,
		{phoneNumber: '1234567890', regionISOCode: 'AL'}
	);

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
			shippingAddressId: address.id,
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(
		commerceLayoutsPage.infoBoxButton('Payment Method')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Payment Method').click();

	await expect(commerceLayoutsPage.infoBoxShippingMethodAlert).toBeVisible();

	await commerceLayoutsPage.infoBoxCancelButton.click();

	const paymentMethod1 = 'Money Order';
	const paymentMethod2 = 'PayPal';

	await commerceAdminChannelsPage.goto();
	await (
		await commerceAdminChannelsPage.channelsTableRowLink(channel.name)
	).click();
	await commerceAdminChannelDetailsPage.activateChannelConfiguration(
		paymentMethod1,
		'Payment Methods'
	);
	await (
		await commerceAdminChannelDetailsPage.generalCommerceAdminChannelTableLink(
			paymentMethod1
		)
	).click();
	await commerceAdminChannelDetailsPage.activateChannelConfiguration(
		paymentMethod2,
		'Payment Methods'
	);
	await (
		await commerceAdminChannelDetailsPage.generalCommerceAdminChannelTableLink(
			paymentMethod2
		)
	).click();

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(
		commerceLayoutsPage.infoBoxButton('Payment Method')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Payment Method').click();
	await commerceLayoutsPage.infoBoxValue(paymentMethod1).click();
	await commerceLayoutsPage.saveButton.click();

	await expect(
		commerceLayoutsPage.infoBoxValue(paymentMethod1)
	).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Payment Method').click();
	await commerceLayoutsPage.infoBoxValue(paymentMethod2).click();
	await commerceLayoutsPage.saveButton.click();

	await expect(
		commerceLayoutsPage.infoBoxValue(paymentMethod2)
	).toBeVisible();

	await page.reload();

	await expect(
		commerceLayoutsPage.infoBoxValue(paymentMethod2)
	).toBeVisible();

	await apiHelpers.headlessCommerceDeliveryCart.checkoutCart(cart.id);

	await page.reload();

	await expect(
		commerceLayoutsPage.infoBoxButton('Payment Method')
	).toBeHidden();
});

test('LPD-35558 Order Details - Order Summary', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceAdminDiscountsPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');

	await commerceLayoutsPage.infoBoxReadOnlyToggle.check();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption('orderSummary');

	await commerceLayoutsPage.infoBoxLabelInput.fill('Order Summary');

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		skus: [
			{
				cost: 0,
				price: 20,
				published: true,
				purchasable: true,
				sku: 'Sku' + getRandomInt(),
			},
		],
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	const discount = await apiHelpers.headlessCommerceAdminPricing.postDiscount(
		{
			couponCode: getRandomString(),
			percentageLevel1: 10,
			target: 'subtotal',
			useCouponCode: true,
			usePercentage: true,
		}
	);

	await apiHelpers.headlessCommerceAdminPricing.postDiscount({
		percentageLevel1: 10,
		target: 'total',
		usePercentage: true,
	});

	await commerceLayoutsPage.addWidget('Coupon Code Entry', 'Commerce');

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('Order Summary')).toBeVisible();

	await commerceAdminDiscountsPage.enterPromoCodeToWidget(
		discount.couponCode
	);

	await commerceLayoutsPage.checkValueOrderSummary('Subtotal', '$ 20.00');
	await commerceLayoutsPage.checkValueOrderSummary(
		'Subtotal Discount',
		'$ 2.00'
	);
	await commerceLayoutsPage.checkValueOrderSummary(
		'Total Discount',
		discount.couponCode
	);

	await expect(page.getByText('Total', {exact: true})).toBeVisible();
	await expect(page.getByText('$ 16.20')).toBeVisible();
});

test('LPD-32237 Order actions fragment', async ({
	apiHelpers,
	applicationsMenuPage,
	checkoutPage,
	commerceAdminChannelsPage,
	commerceLayoutsPage,
	page,
}) => {
	test.setTimeout(180000);

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'business',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['demo.unprivileged@liferay.com']
	);
	const user =
		await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
			'demo.unprivileged@liferay.com'
		);

	const siteRole =
		await apiHelpers.headlessAdminUser.getRoleByName('Site Member');

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
	await apiHelpers.headlessAdminUser.assignUserToSite(
		siteRole.id,
		site.id,
		user.id
	);

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Heading');

	await page.getByText('Heading Example', {exact: true}).dblclick();
	await page.getByLabel('Field').selectOption('CommerceOrder_orderId');

	await commerceLayoutsPage.addFragment('Order Actions', 'Order');

	await expect(
		page.getByText('The order actions component will be shown here.')
	).toBeVisible();

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	await commerceAdminChannelsPage.fixCommerceChannelIssue(
		['Checkout'],
		channel.name
	);
	await commerceAdminChannelsPage.changeCommerceChannelSiteType(
		channel.name,
		'B2B',
		true
	);

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	await performLogout(page);

	await performLogin(page, 'demo.unprivileged');

	const cart1 = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl +
			`/web/${site.name}/order/${cart1.id}`
	);

	await expect(
		page.getByRole('heading', {name: String(cart1.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({checkoutCount: 1});
	await commerceLayoutsPage.orderActionsButton('Checkout').click();

	await checkoutPage.performCheckout({
		shippingAddress: {
			city: 'testCity',
			countryLabel: 'United States',
			name: user.name,
			regionLabel: 'Florida',
			street: 'testStreet',
			zip: '12345',
		},
	});

	await page.goto(
		liferayConfig.environment.baseUrl +
			`/web/${site.name}/order/${cart1.id}`
	);

	await expect(
		page.getByRole('heading', {name: String(cart1.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({reorderCount: 1});

	await performLogout(page);

	await performLogin(page, 'test');

	await commerceAdminChannelsPage.changeCommerceChannelBuyerOrderApprovalWorkflow(
		'Single Approver (Version 1)',
		channel.name
	);
	await commerceAdminChannelsPage.changeCommerceChannelSellerOrderAcceptanceWorkflow(
		'Single Approver (Version 1)',
		channel.name,
		true
	);

	await performLogout(page);

	await performLogin(page, 'demo.unprivileged');

	const cart2 = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl +
			`/web/${site.name}/order/${cart2.id}`
	);

	await expect(
		page.getByRole('heading', {name: String(cart2.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({submitCount: 1});
	await commerceLayoutsPage.orderActionsButton('Submit').click();

	await expect(
		page.getByRole('heading', {name: String(cart2.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({});

	await performLogout(page);

	await performLogin(page, 'test');

	await page.goto(
		liferayConfig.environment.baseUrl +
			`/web/${site.name}/order/${cart2.id}`
	);

	await expect(
		page.getByRole('heading', {name: String(cart2.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({
		approveCount: 1,
		rejectCount: 1,
	});
	await commerceLayoutsPage.orderActionsButton('Approve').click();

	await expect(
		page.getByRole('heading', {name: String(cart2.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({checkoutCount: 1});

	await performLogout(page);

	await performLogin(page, 'demo.unprivileged');

	await page.goto(
		liferayConfig.environment.baseUrl +
			`/web/${site.name}/order/${cart2.id}`
	);

	await expect(
		page.getByRole('heading', {name: String(cart2.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({checkoutCount: 1});
	await commerceLayoutsPage.orderActionsButton('Checkout').click();

	await checkoutPage.performCheckout(
		{
			shippingAddress: {
				city: 'testCity',
				countryLabel: 'United States',
				name: user.name,
				regionLabel: 'California',
				street: 'testStreet',
				zip: '12345',
			},
		},
		async (activeStep: string) => {
			if (activeStep.includes('Order Confirmation')) {
				await page.waitForTimeout(1000);
			}
		}
	);

	await performLogout(page);

	await performLogin(page, 'test');

	await page.goto(
		liferayConfig.environment.baseUrl +
			`/web/${site.name}/order/${cart2.id}`
	);

	await expect(
		page.getByRole('heading', {name: String(cart2.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({
		approveCount: 1,
		rejectCount: 1,
		reorderCount: 1,
	});
	await commerceLayoutsPage.orderActionsButton('Approve').click();

	await expect(
		page.getByRole('heading', {name: String(cart2.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({reorderCount: 1});

	await performLogout(page);

	await performLogin(page, 'demo.unprivileged');

	await page.goto(
		liferayConfig.environment.baseUrl +
			`/web/${site.name}/order/${cart2.id}`
	);

	await expect(
		page.getByRole('heading', {name: String(cart2.id)}).first()
	).toBeVisible();

	await commerceLayoutsPage.expectOrderActionButtons({reorderCount: 1});
	await commerceLayoutsPage.orderActionsButton('Reorder').click();

	await expect(
		page.getByRole('heading', {name: String(cart2.id)})
	).toHaveCount(0);

	await commerceLayoutsPage.expectOrderActionButtons({submitCount: 1});
});

test('LPD-32230 Billing and shipping address order info box fragment configuration', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');
	await commerceLayoutsPage.infoBoxReadOnlyToggle.uncheck();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption('billingAddress');

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.infoBoxLabelInput.fill('Billing Address');
	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('Billing Address')).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Billing Address').click();

	await commerceLayoutsPage.cancelButton.click();

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.editMenuItem.click();
	await commerceLayoutsPage.firstFragment.click();
	await commerceLayoutsPage.infoBoxFieldSelect.selectOption(
		'shippingAddress'
	);
	await commerceLayoutsPage.infoBoxReadOnlyToggle.check();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.infoBoxLabelInput.fill('Shipping Address');
	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('Shipping Address')).toBeVisible();
	await expect(
		commerceLayoutsPage.infoBoxButton('Shipping Address')
	).toBeHidden();
});

test('LPD-33503 Order Details - Questions & Answers', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['demo.unprivileged@liferay.com']
	);
	const user =
		await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
			'demo.unprivileged@liferay.com'
		);

	const siteRole =
		await apiHelpers.headlessAdminUser.getRoleByName('Site Member');

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
	await apiHelpers.headlessAdminUser.assignUserToSite(
		siteRole.id,
		site.id,
		user.id
	);

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');
	await commerceLayoutsPage.infoBoxReadOnlyToggle.uncheck();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption('notes');

	await commerceLayoutsPage.infoBoxLabelInput.fill('Order notes');
	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('Order notes')).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Order notes').click();

	const randomComment = getRandomString();

	await commerceLayoutsPage.inputTextArea.fill(randomComment);

	await commerceLayoutsPage.submitButton.click();

	let comment = await apiHelpers.headlessCommerceDeliveryCart.getComments(
		cart.id
	);

	const locale = await page.evaluate(() => {
		return Liferay.ThemeDisplay.getBCP47LanguageId();
	});

	await expect(
		page.getByText(
			getDateCustomFormat(
				comment.items[0].modifiedDate,
				locale,
				customFormatDate.DATE_AND_TIME
			).replace(',', '')
		)
	).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Order notes').click();

	await expect(page.getByText(comment.items[0].author)).toBeVisible();
	await expect(page.getByText(randomComment)).toBeVisible();

	const randomComment2 = getRandomString();

	await commerceLayoutsPage.inputTextArea.fill(randomComment2);

	await page.getByLabel('Private').check();

	await commerceLayoutsPage.submitButton.click();

	await commerceLayoutsPage.infoBoxButton('Order notes').click();

	await expect(page.getByText(randomComment2)).toBeVisible();
	await expect(commerceLayoutsPage.iconLock).toBeVisible();

	await commerceLayoutsPage.moreActionsButton.first().click();

	page.once('dialog', async (dialog) => {
		expect(dialog.message()).toContain(
			'Are you sure you want to delete this? It will be deleted immediately.'
		);
		await dialog.accept();
	});

	await commerceLayoutsPage.deleteMenuItemModal.click();

	const randomComment3 = getRandomString();

	await commerceLayoutsPage.infoBoxButton('Order notes').click();

	await commerceLayoutsPage.inputTextArea.fill(randomComment3);

	await commerceLayoutsPage.submitButton.click();

	await performLogout(page);

	await performLogin(page, 'demo.unprivileged');

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	comment = await apiHelpers.headlessCommerceDeliveryCart.getComments(
		cart.id
	);

	await expect(
		page.getByText(
			getDateCustomFormat(
				comment.items[0].modifiedDate,
				locale,
				customFormatDate.DATE_AND_TIME
			).replace(',', '')
		)
	).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Order notes').click();

	await expect(page.getByText(comment.items[0].author)).toBeVisible();
	await expect(page.getByText(randomComment3)).toBeVisible();
	await expect(commerceLayoutsPage.iconLock).toBeHidden();

	await performLogout(page);
});

test('LPD-35558 Order Data Sets and header fragments', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Orders Data Set', 'Order');
	await commerceLayoutsPage.addFragment('Order Items Data Set', 'Order');
	await commerceLayoutsPage.addFragment('Order Status Label', 'Order');
	await commerceLayoutsPage.addFragment(
		'Order Inline Editable Order Field',
		'Order'
	);

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		skus: [
			{
				cost: 0,
				price: 20,
				published: true,
				purchasable: true,
				sku: 'Sku' + getRandomInt(),
			},
		],
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText(cart.id.toString())).toBeVisible();
	await expect(page.getByText(sku.toString())).toBeVisible();
});

test('LPD-37698 Payment and Delivery Terms order info box fragment configuration', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceAdminChannelDetailsPage,
	commerceAdminChannelsPage,
	commerceLayoutsPage,
	page,
}) => {
	test.setTimeout(180000);

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');
	await commerceLayoutsPage.infoBoxReadOnlyToggle.uncheck();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption('paymentTermId');
	await commerceLayoutsPage.infoBoxLabelInput.fill('Payment Term');

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const paymentTerm1 = await apiHelpers.headlessCommerceAdminOrder.postTerm({
		label: {
			en_US: 'MoneyA',
		},
		name: 'moneya',
		priority: 0,
		type: 'payment-terms',
	});
	const paymentTerm2 = await apiHelpers.headlessCommerceAdminOrder.postTerm({
		label: {
			en_US: 'MoneyB',
		},
		name: 'moneyb',
		priority: 1,
		type: 'payment-terms',
	});

	await commerceAdminChannelsPage.goto();
	await (
		await commerceAdminChannelsPage.channelsTableRowLink(channel.name)
	).click();

	await commerceAdminChannelDetailsPage.activateChannelConfiguration(
		'Money Order',
		'Payment Methods'
	);
	await (
		await commerceAdminChannelDetailsPage.generalCommerceAdminChannelTableLink(
			'Money Order'
		)
	).click();
	await commerceAdminChannelDetailsPage.setEntryEligibility(
		'Specific Payment Terms',
		paymentTerm1.name,
		'Payment Methods'
	);
	await (
		await commerceAdminChannelDetailsPage.generalCommerceAdminChannelTableLink(
			'Money Order'
		)
	).click();
	await commerceAdminChannelDetailsPage.setEntryEligibility(
		'Specific Payment Terms',
		paymentTerm2.name,
		'Payment Methods'
	);

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const address = await apiHelpers.headlessCommerceAdminAccount.postAddress(
		account.id,
		{phoneNumber: '1234567890', regionISOCode: 'AL'}
	);

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
			paymentMethod: 'money-order',
			shippingAddressId: address.id,
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText('Payment Term')).toBeVisible();

	await commerceLayoutsPage.infoBoxButton('Payment Term').click();

	await commerceLayoutsPage.paymentTermsSelect.selectOption(
		String(paymentTerm1.id)
	);

	await expect(page.getByText(paymentTerm1.description.en_US)).toBeVisible();

	await commerceLayoutsPage.saveButton.click();

	await expect(page.getByTestId('infoBoxValue')).toHaveText(
		String(paymentTerm1.label.en_US)
	);

	await commerceLayoutsPage.infoBoxButton('Payment Term').click();

	await commerceLayoutsPage.paymentTermsSelect.selectOption(
		String(paymentTerm2.id)
	);

	await expect(page.getByText(paymentTerm2.description.en_US)).toBeVisible();

	await commerceLayoutsPage.saveButton.click();

	await expect(page.getByTestId('infoBoxValue')).toHaveText(
		String(paymentTerm2.label.en_US)
	);

	await page.reload();

	await expect(page.getByTestId('infoBoxValue')).toHaveText(
		String(paymentTerm2.label.en_US)
	);

	await apiHelpers.headlessCommerceDeliveryCart.checkoutCart(cart.id);

	await page.reload();

	await commerceLayoutsPage.infoBoxButton('Payment Term').click();

	await expect(commerceLayoutsPage.saveButton).not.toBeVisible();
});

test('LPD-33490 Purchase Document in Open Order Details', async ({
	apiHelpers,
	commerceLayoutsPage,
	displayPageTemplatesPage,
	page,
}) => {
	test.setTimeout(180000);

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await displayPageTemplatesPage.goto(site.friendlyUrlPath);
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Info Box', 'Order');
	await commerceLayoutsPage.infoBoxReadOnlyToggle.uncheck();

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeVisible();

	await commerceLayoutsPage.infoBoxFieldSelect.selectOption(
		'purchaseOrderDocument'
	);
	await commerceLayoutsPage.infoBoxLabelInput.fill('Purchase Order Document');

	await expect(
		page.getByText('The info box component is not correctly configured.')
	).toBeHidden();

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(
		commerceLayoutsPage.infoBoxButton('purchaseOrderDocument')
	).toBeVisible();

	let fileChooserPromise = page.waitForEvent('filechooser');

	await commerceLayoutsPage.infoBoxButton('purchaseOrderDocument').click();

	let fileChooser = await fileChooserPromise;
	await fileChooser.setFiles(
		path.join(__dirname, '/dependencies/image1.jpg')
	);

	await expect(
		commerceLayoutsPage.infoBoxButton('purchaseOrderDocument')
	).toHaveCount(0);
	await expect(commerceLayoutsPage.infoBoxValue('image1.jpg')).toBeVisible();

	fileChooserPromise = page.waitForEvent('filechooser');

	await commerceLayoutsPage.infoBoxEditPurchaseOrderDocumentButton.click();

	fileChooser = await fileChooserPromise;
	await fileChooser.setFiles(
		path.join(__dirname, '/dependencies/image2.jpg')
	);

	await expect(
		commerceLayoutsPage.infoBoxButton('purchaseOrderDocument')
	).toHaveCount(0);
	await expect(commerceLayoutsPage.infoBoxValue('image2.jpg')).toBeVisible();

	await page.reload();

	await expect(
		commerceLayoutsPage.infoBoxButton('purchaseOrderDocument')
	).toHaveCount(0);
	await expect(commerceLayoutsPage.infoBoxValue('image2.jpg')).toBeVisible();

	await commerceLayoutsPage.infoBoxDeletePurchaseOrderDocumentButton.click();

	await expect(
		commerceLayoutsPage.infoBoxButton('purchaseOrderDocument')
	).toBeVisible();
	await expect(commerceLayoutsPage.infoBoxValue('image2.jpg')).toHaveCount(0);

	await page.reload();

	await expect(
		commerceLayoutsPage.infoBoxButton('purchaseOrderDocument')
	).toBeVisible();
	await expect(commerceLayoutsPage.infoBoxValue('image2.jpg')).toHaveCount(0);
});

test('LPD-34399 Quick checkout from order actions fragment', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceAdminChannelDetailsPage,
	commerceAdminChannelsPage,
	commerceLayoutsPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Heading');

	await page.getByText('Heading Example', {exact: true}).dblclick();
	await page.getByLabel('Field').selectOption('CommerceOrder_orderId');

	await commerceLayoutsPage.addFragment('Order Actions', 'Order');

	await expect(
		page.getByText('The order actions component will be shown here.')
	).toBeVisible();

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku = product.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(
		commerceLayoutsPage.orderActionsButton('Quick Checkout')
	).toBeDisabled();

	const address = await apiHelpers.headlessCommerceAdminAccount.postAddress(
		account.id,
		{phoneNumber: '1234567890', regionISOCode: 'AL'}
	);

	await commerceAdminChannelsPage.goto();
	await (
		await commerceAdminChannelsPage.channelsTableRowLink(channel.name)
	).click();
	await commerceAdminChannelDetailsPage.activateChannelConfiguration(
		'Money Order',
		'Payment Methods'
	);
	await (
		await commerceAdminChannelDetailsPage.generalCommerceAdminChannelTableLink(
			'Money Order'
		)
	).click();

	const shippingOption = getRandomString();

	await commerceAdminChannelsPage.setupCommerceChannelShippingMethod(
		channel.name,
		'Flat Rate',
		[shippingOption]
	);

	await apiHelpers.headlessCommerceDeliveryCart.patchCart(
		{
			accountId: account.id,
			billingAddressId: address.id,
			paymentMethod: 'money-order',
			shippingAddressId: address.id,
			shippingMethod: 'fixed',
			shippingOption,
		},
		cart.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(
		commerceLayoutsPage.orderActionsButton('Quick Checkout')
	).toBeEnabled();
});

test('LPD-32243 Order Returns Data Set fragment', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceLayoutsPage,
	page,
}) => {
	const {commerceReturn, order, site} = await commerceReturnSetUp(apiHelpers);

	await applicationsMenuPage.goToSite(site.name);

	await commerceLayoutsPage.goToDisplayPageTemplates();
	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);
	await commerceLayoutsPage.addFragment('Order Returns Data Set', 'Order');

	await expect(
		page.getByText(
			'The order returns data set component will be shown here.'
		)
	).toBeVisible();

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();
	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	await page.goto(
		liferayConfig.environment.baseUrl +
			`/web/${site.name}/order/${order.id}`
	);

	await expect(page.getByRole('button', {name: 'Return ID'})).toBeVisible();
	await expect(page.getByText(commerceReturn.id)).toBeVisible();
});

test('LPD-38261 All commerce widgets work in a content page', async ({
	apiHelpers,
	page,
}) => {
	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const layout = await apiHelpers.headlessDelivery.createSitePage({
		pageDefinition: getPageDefinition([
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_cart_content_web_internal_portlet_CommerceCartContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_cart_content_web_internal_portlet_CommerceCartContentTotalPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_web_internal_portlet_CPCategoryContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_checkout_web_internal_portlet_CommerceCheckoutPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_address_content_web_internal_portlet_CommerceAddressContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_asset_categories_navigation_web_internal_portlet_CPAssetCategoriesNavigationPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_discount_content_web_internal_portlet_CommerceDiscountContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_cart_content_web_internal_portlet_CommerceCartContentMiniPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_search_web_internal_portlet_CPOptionFacetsPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_dashboard_web_internal_portlet_CommerceDashboardForecastsChartPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_organization_web_internal_portlet_CommerceOrganizationPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_order_content_web_internal_portlet_CommerceOrderContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_search_web_internal_portlet_CPPriceRangeFacetsPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_web_internal_portlet_CPCompareContentMiniPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_web_internal_portlet_CPCompareContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_web_internal_portlet_CPContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_type_virtual_order_content_web_internal_portlet_CommerceVirtualOrderItemContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_web_internal_portlet_CPPublisherPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_subscription_web_internal_portlet_CommerceSubscriptionContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_order_content_web_internal_portlet_CommerceReturnContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_search_web_internal_portlet_CPSearchResultsPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_shipment_content_web_internal_portlet_CommerceShipmentContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_search_web_internal_portlet_CPSortPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_product_content_search_web_internal_portlet_CPSpecificationOptionFacetsPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_wish_list_web_internal_portlet_CommerceWishListContentPortlet',
			}),
			getWidgetDefinition({
				id: getRandomString(),
				widgetName:
					'com_liferay_commerce_wish_list_web_internal_portlet_MyCommerceWishListsPortlet',
			}),
		]),
		siteId: site.id,
		title: getRandomString(),
	});

	await page.goto(
		`${liferayConfig.environment.baseUrl}/web${site.friendlyUrlPath}${layout.friendlyUrlPath}`
	);

	await expect(page.getByText('is temporarily unavailable.')).toHaveCount(0);
});

test('LPD-36953 Order Multishipping fragment', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceAdminChannelDetailsPage,
	commerceAdminChannelsPage,
	commerceLayoutsPage,
	displayPageTemplatesPage,
	page,
}) => {
	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await applicationsMenuPage.goToSite(site.name);

	await displayPageTemplatesPage.goto(site.friendlyUrlPath);

	await commerceLayoutsPage.createDisplayPageTemplate(
		getRandomString(),
		'Order',
		site.name
	);

	await commerceLayoutsPage.addFragment('Multishipping', 'Order');

	await commerceLayoutsPage.publishButton.click();

	await waitForAlert(
		page,
		'The display page template was published successfully.'
	);

	await commerceLayoutsPage.moreActionsButton.click();

	await commerceLayoutsPage.markAsDefaultMenuItem.click();

	await waitForAlert(page);

	await expect(
		commerceLayoutsPage.defaultDisplayPageTemplateIcon
	).toBeVisible();

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product1 = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});
	const product2 = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
	});

	const sku1 = product1.skus[0];
	const sku2 = product2.skus[0];

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [
				{
					quantity: 1,
					skuId: sku1.id,
				},
				{
					quantity: 1,
					skuId: sku2.id,
				},
			],
		},
		channel.id
	);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText(sku1.sku)).toHaveCount(0);
	await expect(page.getByText(sku2.sku)).toHaveCount(0);

	await commerceAdminChannelsPage.goto();

	await (
		await commerceAdminChannelsPage.channelsTableRowLink(channel.name)
	).click();

	await commerceAdminChannelDetailsPage.allowMultishippingToggle.setChecked(
		true
	);

	await expect(
		commerceAdminChannelDetailsPage.allowMultishippingToggle
	).toBeChecked();

	await commerceAdminChannelDetailsPage.saveButton.click();

	await expect(
		commerceAdminChannelDetailsPage.allowMultishippingToggle
	).toBeChecked();

	await waitForAlert(page);

	await page.goto(
		liferayConfig.environment.baseUrl + `/web/${site.name}/order/${cart.id}`
	);

	await expect(page.getByText(sku1.sku)).toBeVisible();

	await expect(page.getByText(sku2.sku)).toBeVisible();
});
