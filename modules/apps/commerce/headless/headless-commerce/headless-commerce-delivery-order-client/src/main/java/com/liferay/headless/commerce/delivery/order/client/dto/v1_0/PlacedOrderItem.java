/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.order.client.dto.v1_0;

import com.liferay.headless.commerce.delivery.order.client.function.UnsafeSupplier;
import com.liferay.headless.commerce.delivery.order.client.serdes.v1_0.PlacedOrderItemSerDes;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public class PlacedOrderItem implements Cloneable, Serializable {

	public static PlacedOrderItem toDTO(String json) {
		return PlacedOrderItemSerDes.toDTO(json);
	}

	public String getAdaptiveMediaImageHTMLTag() {
		return adaptiveMediaImageHTMLTag;
	}

	public void setAdaptiveMediaImageHTMLTag(String adaptiveMediaImageHTMLTag) {
		this.adaptiveMediaImageHTMLTag = adaptiveMediaImageHTMLTag;
	}

	public void setAdaptiveMediaImageHTMLTag(
		UnsafeSupplier<String, Exception>
			adaptiveMediaImageHTMLTagUnsafeSupplier) {

		try {
			adaptiveMediaImageHTMLTag =
				adaptiveMediaImageHTMLTagUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String adaptiveMediaImageHTMLTag;

	public Map<String, ?> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, ?> customFields) {
		this.customFields = customFields;
	}

	public void setCustomFields(
		UnsafeSupplier<Map<String, ?>, Exception> customFieldsUnsafeSupplier) {

		try {
			customFields = customFieldsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, ?> customFields;

	public String getDeliveryGroup() {
		return deliveryGroup;
	}

	public void setDeliveryGroup(String deliveryGroup) {
		this.deliveryGroup = deliveryGroup;
	}

	public void setDeliveryGroup(
		UnsafeSupplier<String, Exception> deliveryGroupUnsafeSupplier) {

		try {
			deliveryGroup = deliveryGroupUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String deliveryGroup;

	public String getDeliveryGroupName() {
		return deliveryGroupName;
	}

	public void setDeliveryGroupName(String deliveryGroupName) {
		this.deliveryGroupName = deliveryGroupName;
	}

	public void setDeliveryGroupName(
		UnsafeSupplier<String, Exception> deliveryGroupNameUnsafeSupplier) {

		try {
			deliveryGroupName = deliveryGroupNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String deliveryGroupName;

	public String[] getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String[] errorMessages) {
		this.errorMessages = errorMessages;
	}

	public void setErrorMessages(
		UnsafeSupplier<String[], Exception> errorMessagesUnsafeSupplier) {

		try {
			errorMessages = errorMessagesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String[] errorMessages;

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	public void setExternalReferenceCode(
		UnsafeSupplier<String, Exception> externalReferenceCodeUnsafeSupplier) {

		try {
			externalReferenceCode = externalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String externalReferenceCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public void setOptions(
		UnsafeSupplier<String, Exception> optionsUnsafeSupplier) {

		try {
			options = optionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String options;

	public Long getParentOrderItemId() {
		return parentOrderItemId;
	}

	public void setParentOrderItemId(Long parentOrderItemId) {
		this.parentOrderItemId = parentOrderItemId;
	}

	public void setParentOrderItemId(
		UnsafeSupplier<Long, Exception> parentOrderItemIdUnsafeSupplier) {

		try {
			parentOrderItemId = parentOrderItemIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long parentOrderItemId;

	public PlacedOrderItemShipment[] getPlacedOrderItemShipments() {
		return placedOrderItemShipments;
	}

	public void setPlacedOrderItemShipments(
		PlacedOrderItemShipment[] placedOrderItemShipments) {

		this.placedOrderItemShipments = placedOrderItemShipments;
	}

	public void setPlacedOrderItemShipments(
		UnsafeSupplier<PlacedOrderItemShipment[], Exception>
			placedOrderItemShipmentsUnsafeSupplier) {

		try {
			placedOrderItemShipments =
				placedOrderItemShipmentsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected PlacedOrderItemShipment[] placedOrderItemShipments;

	public PlacedOrderItem[] getPlacedOrderItems() {
		return placedOrderItems;
	}

	public void setPlacedOrderItems(PlacedOrderItem[] placedOrderItems) {
		this.placedOrderItems = placedOrderItems;
	}

	public void setPlacedOrderItems(
		UnsafeSupplier<PlacedOrderItem[], Exception>
			placedOrderItemsUnsafeSupplier) {

		try {
			placedOrderItems = placedOrderItemsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected PlacedOrderItem[] placedOrderItems;

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public void setPrice(UnsafeSupplier<Price, Exception> priceUnsafeSupplier) {
		try {
			price = priceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Price price;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setProductId(
		UnsafeSupplier<Long, Exception> productIdUnsafeSupplier) {

		try {
			productId = productIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long productId;

	public Map<String, String> getProductURLs() {
		return productURLs;
	}

	public void setProductURLs(Map<String, String> productURLs) {
		this.productURLs = productURLs;
	}

	public void setProductURLs(
		UnsafeSupplier<Map<String, String>, Exception>
			productURLsUnsafeSupplier) {

		try {
			productURLs = productURLsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> productURLs;

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setQuantity(
		UnsafeSupplier<BigDecimal, Exception> quantityUnsafeSupplier) {

		try {
			quantity = quantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected BigDecimal quantity;

	public String getReplacedSku() {
		return replacedSku;
	}

	public void setReplacedSku(String replacedSku) {
		this.replacedSku = replacedSku;
	}

	public void setReplacedSku(
		UnsafeSupplier<String, Exception> replacedSkuUnsafeSupplier) {

		try {
			replacedSku = replacedSkuUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String replacedSku;

	public Date getRequestedDeliveryDate() {
		return requestedDeliveryDate;
	}

	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}

	public void setRequestedDeliveryDate(
		UnsafeSupplier<Date, Exception> requestedDeliveryDateUnsafeSupplier) {

		try {
			requestedDeliveryDate = requestedDeliveryDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date requestedDeliveryDate;

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public void setSettings(
		UnsafeSupplier<Settings, Exception> settingsUnsafeSupplier) {

		try {
			settings = settingsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Settings settings;

	public String getShippingAddressExternalReferenceCode() {
		return shippingAddressExternalReferenceCode;
	}

	public void setShippingAddressExternalReferenceCode(
		String shippingAddressExternalReferenceCode) {

		this.shippingAddressExternalReferenceCode =
			shippingAddressExternalReferenceCode;
	}

	public void setShippingAddressExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			shippingAddressExternalReferenceCodeUnsafeSupplier) {

		try {
			shippingAddressExternalReferenceCode =
				shippingAddressExternalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String shippingAddressExternalReferenceCode;

	public Long getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public void setShippingAddressId(
		UnsafeSupplier<Long, Exception> shippingAddressIdUnsafeSupplier) {

		try {
			shippingAddressId = shippingAddressIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long shippingAddressId;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setSku(UnsafeSupplier<String, Exception> skuUnsafeSupplier) {
		try {
			sku = skuUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String sku;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public void setSkuId(UnsafeSupplier<Long, Exception> skuIdUnsafeSupplier) {
		try {
			skuId = skuIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long skuId;

	public Boolean getSubscription() {
		return subscription;
	}

	public void setSubscription(Boolean subscription) {
		this.subscription = subscription;
	}

	public void setSubscription(
		UnsafeSupplier<Boolean, Exception> subscriptionUnsafeSupplier) {

		try {
			subscription = subscriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean subscription;

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setThumbnail(
		UnsafeSupplier<String, Exception> thumbnailUnsafeSupplier) {

		try {
			thumbnail = thumbnailUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String thumbnail;

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public void setUnitOfMeasure(
		UnsafeSupplier<String, Exception> unitOfMeasureUnsafeSupplier) {

		try {
			unitOfMeasure = unitOfMeasureUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String unitOfMeasure;

	public String getUnitOfMeasureKey() {
		return unitOfMeasureKey;
	}

	public void setUnitOfMeasureKey(String unitOfMeasureKey) {
		this.unitOfMeasureKey = unitOfMeasureKey;
	}

	public void setUnitOfMeasureKey(
		UnsafeSupplier<String, Exception> unitOfMeasureKeyUnsafeSupplier) {

		try {
			unitOfMeasureKey = unitOfMeasureKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String unitOfMeasureKey;

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public void setValid(
		UnsafeSupplier<Boolean, Exception> validUnsafeSupplier) {

		try {
			valid = validUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean valid;

	public String[] getVirtualItemURLs() {
		return virtualItemURLs;
	}

	public void setVirtualItemURLs(String[] virtualItemURLs) {
		this.virtualItemURLs = virtualItemURLs;
	}

	public void setVirtualItemURLs(
		UnsafeSupplier<String[], Exception> virtualItemURLsUnsafeSupplier) {

		try {
			virtualItemURLs = virtualItemURLsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String[] virtualItemURLs;

	public VirtualItem[] getVirtualItems() {
		return virtualItems;
	}

	public void setVirtualItems(VirtualItem[] virtualItems) {
		this.virtualItems = virtualItems;
	}

	public void setVirtualItems(
		UnsafeSupplier<VirtualItem[], Exception> virtualItemsUnsafeSupplier) {

		try {
			virtualItems = virtualItemsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected VirtualItem[] virtualItems;

	@Override
	public PlacedOrderItem clone() throws CloneNotSupportedException {
		return (PlacedOrderItem)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PlacedOrderItem)) {
			return false;
		}

		PlacedOrderItem placedOrderItem = (PlacedOrderItem)object;

		return Objects.equals(toString(), placedOrderItem.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return PlacedOrderItemSerDes.toJSON(this);
	}

}