/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.math.BigDecimal;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceShipmentItem service. Represents a row in the &quot;CommerceShipmentItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.model.impl.CommerceShipmentItemModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.model.impl.CommerceShipmentItemImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentItem
 * @generated
 */
@ProviderType
public interface CommerceShipmentItemModel
	extends BaseModel<CommerceShipmentItem>, ExternalReferenceCodeModel,
			GroupedModel, MVCCModel, ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce shipment item model instance should use the {@link CommerceShipmentItem} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce shipment item.
	 *
	 * @return the primary key of this commerce shipment item
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce shipment item.
	 *
	 * @param primaryKey the primary key of this commerce shipment item
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce shipment item.
	 *
	 * @return the mvcc version of this commerce shipment item
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce shipment item.
	 *
	 * @param mvccVersion the mvcc version of this commerce shipment item
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this commerce shipment item.
	 *
	 * @return the uuid of this commerce shipment item
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce shipment item.
	 *
	 * @param uuid the uuid of this commerce shipment item
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this commerce shipment item.
	 *
	 * @return the external reference code of this commerce shipment item
	 */
	@AutoEscape
	@Override
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce shipment item.
	 *
	 * @param externalReferenceCode the external reference code of this commerce shipment item
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce shipment item ID of this commerce shipment item.
	 *
	 * @return the commerce shipment item ID of this commerce shipment item
	 */
	public long getCommerceShipmentItemId();

	/**
	 * Sets the commerce shipment item ID of this commerce shipment item.
	 *
	 * @param commerceShipmentItemId the commerce shipment item ID of this commerce shipment item
	 */
	public void setCommerceShipmentItemId(long commerceShipmentItemId);

	/**
	 * Returns the group ID of this commerce shipment item.
	 *
	 * @return the group ID of this commerce shipment item
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce shipment item.
	 *
	 * @param groupId the group ID of this commerce shipment item
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce shipment item.
	 *
	 * @return the company ID of this commerce shipment item
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce shipment item.
	 *
	 * @param companyId the company ID of this commerce shipment item
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce shipment item.
	 *
	 * @return the user ID of this commerce shipment item
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce shipment item.
	 *
	 * @param userId the user ID of this commerce shipment item
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce shipment item.
	 *
	 * @return the user uuid of this commerce shipment item
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce shipment item.
	 *
	 * @param userUuid the user uuid of this commerce shipment item
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce shipment item.
	 *
	 * @return the user name of this commerce shipment item
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce shipment item.
	 *
	 * @param userName the user name of this commerce shipment item
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce shipment item.
	 *
	 * @return the create date of this commerce shipment item
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce shipment item.
	 *
	 * @param createDate the create date of this commerce shipment item
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce shipment item.
	 *
	 * @return the modified date of this commerce shipment item
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce shipment item.
	 *
	 * @param modifiedDate the modified date of this commerce shipment item
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce shipment ID of this commerce shipment item.
	 *
	 * @return the commerce shipment ID of this commerce shipment item
	 */
	public long getCommerceShipmentId();

	/**
	 * Sets the commerce shipment ID of this commerce shipment item.
	 *
	 * @param commerceShipmentId the commerce shipment ID of this commerce shipment item
	 */
	public void setCommerceShipmentId(long commerceShipmentId);

	/**
	 * Returns the commerce order item ID of this commerce shipment item.
	 *
	 * @return the commerce order item ID of this commerce shipment item
	 */
	public long getCommerceOrderItemId();

	/**
	 * Sets the commerce order item ID of this commerce shipment item.
	 *
	 * @param commerceOrderItemId the commerce order item ID of this commerce shipment item
	 */
	public void setCommerceOrderItemId(long commerceOrderItemId);

	/**
	 * Returns the commerce inventory warehouse ID of this commerce shipment item.
	 *
	 * @return the commerce inventory warehouse ID of this commerce shipment item
	 */
	public long getCommerceInventoryWarehouseId();

	/**
	 * Sets the commerce inventory warehouse ID of this commerce shipment item.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID of this commerce shipment item
	 */
	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId);

	/**
	 * Returns the quantity of this commerce shipment item.
	 *
	 * @return the quantity of this commerce shipment item
	 */
	public BigDecimal getQuantity();

	/**
	 * Sets the quantity of this commerce shipment item.
	 *
	 * @param quantity the quantity of this commerce shipment item
	 */
	public void setQuantity(BigDecimal quantity);

	/**
	 * Returns the unit of measure key of this commerce shipment item.
	 *
	 * @return the unit of measure key of this commerce shipment item
	 */
	@AutoEscape
	public String getUnitOfMeasureKey();

	/**
	 * Sets the unit of measure key of this commerce shipment item.
	 *
	 * @param unitOfMeasureKey the unit of measure key of this commerce shipment item
	 */
	public void setUnitOfMeasureKey(String unitOfMeasureKey);

	@Override
	public CommerceShipmentItem cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}