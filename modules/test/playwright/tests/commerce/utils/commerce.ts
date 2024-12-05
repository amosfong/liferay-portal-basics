import {DataApiHelpers} from '../../../helpers/ApiHelpers';
import getRandomString from '../../../utils/getRandomString';
import {ORDER_WORKFLOW_STATUS_CODE} from '../../workspaces/liferay-workspace-marketplace/utils/constants';

/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
export async function classicCommerceSetUp(
	apiHelpers: DataApiHelpers,
	siteName: string
) {
	return initializerSetUp(
		apiHelpers,
		'com.liferay.commerce.site.initializer',
		'Commerce Classic',
		'Liferay Commerce Channel',
		siteName
	);
}

export async function commerceReturnSetUp(
	apiHelpers: DataApiHelpers,
	amount?: number,
	authorized?: number,
	received?: number,
	quantity?: number,
	returnStatus?: string
) {
	const {
		account,
		address,
		catalog,
		channel,
		order,
		orderItem,
		payment,
		site,
		sku,
	} = await completedVirtualOrderItemSetUp(apiHelpers, 1);

	amount = amount || 10;
	authorized = authorized || 1;
	received = received || 1;
	quantity = quantity || 1;
	returnStatus = returnStatus || 'processing';

	let commerceReturn = null;

	if (returnStatus === 'draft') {
		commerceReturn =
			await apiHelpers.headlessCommerceReturn.postCommerceReturn({
				channelId: channel.id,
				commerceReturnToCommerceReturnItems: [
					{
						amount,
						authorized,
						quantity,
						r_accountToCommerceReturnItems_accountEntryId:
							account.id,
						r_commerceOrderItemToCommerceReturnItems_commerceOrderItemId:
							orderItem.id,
						r_commerceOrderToCommerceReturns_commerceOrderId:
							order.id,
						received,
						returnReason: {
							key: 'changeOfMind',
						},
						returnResolutionMethod: {
							key: 'refund',
						},
					},
				],
				r_accountToCommerceReturns_accountEntryId: account.id,
				r_commerceOrderToCommerceReturns_commerceOrderId: order.id,
				returnStatus: {
					key: returnStatus,
				},
			});
	}
	else {
		commerceReturn =
			await apiHelpers.headlessCommerceReturn.postCommerceReturn({
				channelId: channel.id,
				commerceReturnToCommerceReturnItems: [
					{
						amount,
						authorized,
						quantity,
						r_accountToCommerceReturnItems_accountEntryId:
							account.id,
						r_commerceOrderItemToCommerceReturnItems_commerceOrderItemId:
							orderItem.id,
						r_commerceOrderToCommerceReturns_commerceOrderId:
							order.id,
						received,
						returnItemStatus: {
							key: 'toBeProccessed',
						},
						returnReason: {
							key: 'changeOfMind',
						},
						returnResolutionMethod: {
							key: 'refund',
						},
					},
				],
				r_accountToCommerceReturns_accountEntryId: account.id,
				r_commerceOrderToCommerceReturns_commerceOrderId: order.id,
				returnStatus: {
					key: returnStatus,
				},
			});
	}

	return {
		account,
		address,
		catalog,
		channel,
		commerceReturn,
		order,
		orderItem,
		payment,
		site,
		sku,
	};
}

export async function completedVirtualOrderItemSetUp(
	apiHelpers: DataApiHelpers,
	orderItemQuantity: number
) {
	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		name: getRandomString(),
		siteGroupId: site.id,
	});

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog();

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		productType: 'virtual',
	});

	const productSkus = await apiHelpers.headlessCommerceAdminCatalog
		.getProduct(product.productId)
		.then((product) => {
			return product.skus;
		});

	const sku = productSkus[0];

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['test@liferay.com']
	);

	const address = await apiHelpers.headlessCommerceAdminAccount.postAddress(
		account.id,
		{phoneNumber: '1234567890', regionISOCode: 'AL'}
	);

	const order = await apiHelpers.headlessCommerceAdminOrder.postOrder({
		accountId: account.id,
		billingAddressId: address.id,
		channelId: channel.id,
		orderItems: [
			{
				quantity: orderItemQuantity,
				skuId: sku.id,
			},
		],
		shippingAddressId: address.id,
	});

	const payment =
		await apiHelpers.headlessCommerceAdminPaymentApiHelper.postPayment({
			amount: 10,
			relatedItemId: order.id,
		});

	await apiHelpers.headlessCommerceAdminPaymentApiHelper.patchPayment(
		{
			paymentStatus: 0,
			relatedItemId: payment.relatedItemId,
		},
		payment.id
	);

	await apiHelpers.headlessCommerceAdminOrder.patchOrder(order.id, {
		orderStatus: ORDER_WORKFLOW_STATUS_CODE.PROCESSING,
	});

	await apiHelpers.headlessCommerceAdminOrder.patchOrder(order.id, {
		orderStatus: ORDER_WORKFLOW_STATUS_CODE.COMPLETED,
	});

	const orderItem = order.orderItems[0];

	return {
		account,
		address,
		catalog,
		channel,
		order,
		orderItem,
		payment,
		site,
		sku,
	};
}

export async function initializerSetUp(
	apiHelpers: DataApiHelpers,
	templateKey: string,
	catalogName?: string,
	channelName?: string,
	siteName?: string
) {
	siteName = siteName || getRandomString();

	catalogName = catalogName || siteName;
	channelName = channelName || siteName;

	const site = await apiHelpers.headlessSite.createSite({
		name: siteName,
		templateKey,
		templateType: 'site-initializer',
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const channels =
		await apiHelpers.headlessCommerceAdminChannel.getChannelsPage(
			channelName
		);

	apiHelpers.data.push({id: channels.items[0].id, type: 'channel'});

	const catalogs =
		await apiHelpers.headlessCommerceAdminCatalog.getCatalogsPage(
			catalogName
		);

	apiHelpers.data.push({id: catalogs.items[0].id, type: 'catalog'});

	const products =
		await apiHelpers.headlessCommerceAdminCatalog.getProductsPage(100, '');

	for (let i = 0; i < products.totalCount; i++) {
		if (products.items[i].catalogId === catalogs.items[0].id) {
			apiHelpers.data.push({
				id: products.items[i].productId,
				type: 'product',
			});
		}
	}

	const options = await apiHelpers.headlessCommerceAdminCatalog.getOptions();

	for (let i = 0; i < options.totalCount; i++) {
		apiHelpers.data.push({
			id: options.items[i].id,
			type: 'option',
		});
	}

	const optionCategories =
		await apiHelpers.headlessCommerceAdminCatalog.getOptionCategories();

	for (let i = 0; i < optionCategories.totalCount; i++) {
		apiHelpers.data.push({
			id: optionCategories.items[i].id,
			type: 'optionCategory',
		});
	}

	const specifications =
		await apiHelpers.headlessCommerceAdminCatalog.getSpecifications();

	for (let i = 0; i < specifications.totalCount; i++) {
		apiHelpers.data.push({
			id: specifications.items[i].id,
			type: 'specification',
		});
	}

	const warehouses =
		await apiHelpers.headlessCommerceAdminInventoryApiHelper.getWarehousesPage();

	for (let i = 0; i < warehouses.totalCount; i++) {
		apiHelpers.data.push({
			id: warehouses.items[i].id,
			type: 'warehouse',
		});
	}

	return {catalog: catalogs.items[0], channel: channels.items[0], site};
}

export async function miniumSetUp(
	apiHelpers: DataApiHelpers,
	siteName?: string
) {
	return initializerSetUp(
		apiHelpers,
		'minium-initializer',
		null,
		null,
		siteName
	);
}
