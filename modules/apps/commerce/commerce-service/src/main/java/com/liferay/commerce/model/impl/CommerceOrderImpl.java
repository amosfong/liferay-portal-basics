/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryLocalServiceUtil;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.model.CommerceMoneyFactoryUtil;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalServiceUtil;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.CommerceAddressLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceShippingMethodLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.RepositoryProviderUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.RepositoryLocalServiceUtil;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 * @author Ethan Bustad
 */
public class CommerceOrderImpl extends CommerceOrderBaseImpl {

	@Override
	public AccountEntry getAccountEntry() throws PortalException {
		if (getCommerceAccountId() == AccountConstants.ACCOUNT_ENTRY_ID_GUEST) {
			return AccountEntryLocalServiceUtil.getGuestAccountEntry(
				getCompanyId());
		}

		return AccountEntryLocalServiceUtil.getAccountEntry(
			getCommerceAccountId());
	}

	@Override
	public List<FileEntry> getAttachmentFileEntries(int start, int end)
		throws PortalException {

		LocalRepository localRepository = getLocalRepository();

		if (localRepository == null) {
			return Collections.emptyList();
		}

		Folder folder = getFolder(localRepository);

		if (folder == null) {
			return Collections.emptyList();
		}

		return localRepository.getFileEntries(
			folder.getFolderId(), start, end, null);
	}

	@Override
	public int getAttachmentFileEntriesCount() throws PortalException {
		LocalRepository localRepository = getLocalRepository();

		if (localRepository == null) {
			return 0;
		}

		Folder folder = getFolder(localRepository);

		if (folder == null) {
			return 0;
		}

		return localRepository.getFileEntriesCount(folder.getFolderId());
	}

	@Override
	public CommerceAddress getBillingAddress() throws PortalException {
		long billingAddressId = getBillingAddressId();

		if (billingAddressId > 0) {
			return CommerceAddressLocalServiceUtil.fetchCommerceAddress(
				getBillingAddressId());
		}

		return null;
	}

	@Override
	public String getCommerceAccountName() throws PortalException {
		AccountEntry accountEntry = getAccountEntry();

		if (accountEntry.isPersonalAccount()) {
			return accountEntry.getUserName();
		}

		return accountEntry.getName();
	}

	@Override
	public CommerceCurrency getCommerceCurrency() throws PortalException {
		return CommerceCurrencyLocalServiceUtil.getCommerceCurrency(
			getCompanyId(), getCommerceCurrencyCode());
	}

	@Override
	public List<CommerceOrderItem> getCommerceOrderItems() {
		return CommerceOrderItemLocalServiceUtil.getCommerceOrderItems(
			getCommerceOrderId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<CommerceOrderItem> getCommerceOrderItems(long cpInstanceId) {
		return CommerceOrderItemLocalServiceUtil.getCommerceOrderItems(
			getCommerceOrderId(), cpInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	@Override
	public int getCommerceOrderItemsCount(long cpInstanceId) {
		return CommerceOrderItemLocalServiceUtil.getCommerceOrderItemsCount(
			getCommerceOrderId(), cpInstanceId);
	}

	@Override
	public CommerceShippingMethod getCommerceShippingMethod()
		throws PortalException {

		long commerceShippingMethodId = getCommerceShippingMethodId();

		if (commerceShippingMethodId > 0) {
			return CommerceShippingMethodLocalServiceUtil.
				getCommerceShippingMethod(commerceShippingMethodId);
		}

		return null;
	}

	@Override
	public List<Long> getCustomerCommerceOrderIds() {
		return CommerceOrderItemLocalServiceUtil.getCustomerCommerceOrderIds(
			getCommerceOrderId());
	}

	@Override
	public int getCustomerCommerceOrderIdsCount() {
		return CommerceOrderItemLocalServiceUtil.
			getCustomerCommerceOrderIdsCount(getCommerceOrderId());
	}

	@Override
	public Folder getFolder(LocalRepository localRepository) {
		if (localRepository == null) {
			return null;
		}

		return localRepository.fetchFolderByExternalReferenceCode(
			"order-" + getCommerceOrderId());
	}

	@Override
	public LocalRepository getLocalRepository() throws PortalException {
		Repository repository = RepositoryLocalServiceUtil.fetchRepository(
			getGroupId(), CommerceConstants.SERVICE_NAME_COMMERCE_ORDER);

		if (repository == null) {
			return null;
		}

		return RepositoryProviderUtil.getLocalRepository(
			repository.getRepositoryId());
	}

	@Override
	public long getScopeGroupId() throws PortalException {
		AccountEntry accountEntry = getAccountEntry();

		if (accountEntry.isBusinessAccount()) {
			return accountEntry.getAccountEntryGroupId();
		}

		return getGroupId();
	}

	@Override
	public CommerceAddress getShippingAddress() throws PortalException {
		long shippingAddressId = getShippingAddressId();

		if (shippingAddressId > 0) {
			return CommerceAddressLocalServiceUtil.fetchCommerceAddress(
				getShippingAddressId());
		}

		return null;
	}

	@Override
	public CommerceMoney getShippingMoney() throws PortalException {
		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrency(), getShippingAmount());
	}

	@Override
	public CommerceMoney getShippingWithTaxAmountMoney()
		throws PortalException {

		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrency(), getShippingWithTaxAmount());
	}

	@Override
	public CommerceMoney getSubtotalMoney() throws PortalException {
		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrency(), getSubtotal());
	}

	@Override
	public CommerceMoney getSubtotalWithTaxAmountMoney()
		throws PortalException {

		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrency(), getSubtotalWithTaxAmount());
	}

	@Override
	public List<Long> getSupplierCommerceOrderIds() {
		return CommerceOrderItemLocalServiceUtil.getSupplierCommerceOrderIds(
			getCommerceOrderId());
	}

	@Override
	public int getSupplierCommerceOrderIdsCount() {
		return CommerceOrderItemLocalServiceUtil.
			getSupplierCommerceOrderIdsCount(getCommerceOrderId());
	}

	@Override
	public CommerceMoney getTotalMoney() throws PortalException {
		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrency(), getTotal());
	}

	@Override
	public CommerceMoney getTotalWithTaxAmountMoney() throws PortalException {
		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrency(), getTotalWithTaxAmount());
	}

	@Override
	public boolean isB2B() throws PortalException {
		AccountEntry accountEntry = getAccountEntry();

		return accountEntry.isBusinessAccount();
	}

	@Override
	public boolean isEmpty() {
		int count =
			CommerceOrderItemLocalServiceUtil.getCommerceOrderItemsCount(
				getCommerceOrderId());

		if (count > 0) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isGuestOrder() throws PortalException {
		AccountEntry accountEntry = getAccountEntry();

		if (accountEntry.isGuestAccount()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isOpen() {
		if (getOrderStatus() == CommerceOrderConstants.ORDER_STATUS_OPEN) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isQuote() {
		if ((getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_QUOTE_PROCESSED) ||
			(getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_QUOTE_REQUESTED)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isSubscription() {
		if (getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_SUBSCRIPTION) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isSubscriptionOrder() {
		List<CommerceOrderItem> commerceOrderItems = getCommerceOrderItems();

		if (commerceOrderItems.isEmpty()) {
			return false;
		}

		CommerceOrderItem commerceOrderItem = commerceOrderItems.get(0);

		if (commerceOrderItem.isSubscription()) {
			return true;
		}

		return false;
	}

	@Override
	public void setShippingDiscounts(
		CommerceDiscountValue commerceDiscountValue) {

		BigDecimal shippingDiscountAmount = BigDecimal.ZERO;
		BigDecimal shippingDiscountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal shippingDiscountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal shippingDiscountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal shippingDiscountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmountCommerceMoney =
				commerceDiscountValue.getDiscountAmount();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			shippingDiscountAmount = discountAmountCommerceMoney.getPrice();

			if ((percentages != null) && (percentages.length > 0)) {
				shippingDiscountPercentageLevel1 = percentages[0];
			}

			if ((percentages != null) && (percentages.length > 1)) {
				shippingDiscountPercentageLevel1 = percentages[1];
			}

			if ((percentages != null) && (percentages.length > 2)) {
				shippingDiscountPercentageLevel1 = percentages[2];
			}

			if ((percentages != null) && (percentages.length > 3)) {
				shippingDiscountPercentageLevel1 = percentages[3];
			}
		}

		setShippingDiscountAmount(shippingDiscountAmount);
		setShippingDiscountPercentageLevel1(shippingDiscountPercentageLevel1);
		setShippingDiscountPercentageLevel2(shippingDiscountPercentageLevel2);
		setShippingDiscountPercentageLevel3(shippingDiscountPercentageLevel3);
		setShippingDiscountPercentageLevel4(shippingDiscountPercentageLevel4);
	}

	@Override
	public void setSubtotalDiscounts(
		CommerceDiscountValue commerceDiscountValue) {

		BigDecimal subtotalDiscountAmount = BigDecimal.ZERO;
		BigDecimal subtotalDiscountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal subtotalDiscountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal subtotalDiscountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal subtotalDiscountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmountCommerceMoney =
				commerceDiscountValue.getDiscountAmount();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			subtotalDiscountAmount = discountAmountCommerceMoney.getPrice();

			if ((percentages != null) && (percentages.length > 0)) {
				subtotalDiscountPercentageLevel1 = percentages[0];
			}

			if ((percentages != null) && (percentages.length > 1)) {
				subtotalDiscountPercentageLevel1 = percentages[1];
			}

			if ((percentages != null) && (percentages.length > 2)) {
				subtotalDiscountPercentageLevel1 = percentages[2];
			}

			if ((percentages != null) && (percentages.length > 3)) {
				subtotalDiscountPercentageLevel1 = percentages[3];
			}
		}

		setSubtotalDiscountAmount(subtotalDiscountAmount);
		setSubtotalDiscountPercentageLevel1(subtotalDiscountPercentageLevel1);
		setSubtotalDiscountPercentageLevel2(subtotalDiscountPercentageLevel2);
		setSubtotalDiscountPercentageLevel3(subtotalDiscountPercentageLevel3);
		setSubtotalDiscountPercentageLevel4(subtotalDiscountPercentageLevel4);
	}

	@Override
	public void setTotalDiscounts(CommerceDiscountValue commerceDiscountValue) {
		BigDecimal totalDiscountAmount = BigDecimal.ZERO;
		BigDecimal totalDiscountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal totalDiscountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal totalDiscountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal totalDiscountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmountCommerceMoney =
				commerceDiscountValue.getDiscountAmount();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			totalDiscountAmount = discountAmountCommerceMoney.getPrice();

			if ((percentages != null) && (percentages.length > 0)) {
				totalDiscountPercentageLevel1 = percentages[0];
			}

			if ((percentages != null) && (percentages.length > 1)) {
				totalDiscountPercentageLevel1 = percentages[1];
			}

			if ((percentages != null) && (percentages.length > 2)) {
				totalDiscountPercentageLevel1 = percentages[2];
			}

			if ((percentages != null) && (percentages.length > 3)) {
				totalDiscountPercentageLevel1 = percentages[3];
			}
		}

		setTotalDiscountAmount(totalDiscountAmount);
		setTotalDiscountPercentageLevel1(totalDiscountPercentageLevel1);
		setTotalDiscountPercentageLevel2(totalDiscountPercentageLevel2);
		setTotalDiscountPercentageLevel3(totalDiscountPercentageLevel3);
		setTotalDiscountPercentageLevel4(totalDiscountPercentageLevel4);
	}

}