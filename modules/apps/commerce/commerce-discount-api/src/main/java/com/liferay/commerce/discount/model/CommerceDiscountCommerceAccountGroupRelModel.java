/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceDiscountCommerceAccountGroupRel service. Represents a row in the &quot;CDiscountCAccountGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscountCommerceAccountGroupRel
 * @generated
 */
@ProviderType
public interface CommerceDiscountCommerceAccountGroupRelModel
	extends AuditedModel, BaseModel<CommerceDiscountCommerceAccountGroupRel>,
			MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce discount commerce account group rel model instance should use the {@link CommerceDiscountCommerceAccountGroupRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce discount commerce account group rel.
	 *
	 * @return the primary key of this commerce discount commerce account group rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce discount commerce account group rel.
	 *
	 * @param primaryKey the primary key of this commerce discount commerce account group rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce discount commerce account group rel.
	 *
	 * @return the mvcc version of this commerce discount commerce account group rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce discount commerce account group rel.
	 *
	 * @param mvccVersion the mvcc version of this commerce discount commerce account group rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the commerce discount commerce account group rel ID of this commerce discount commerce account group rel.
	 *
	 * @return the commerce discount commerce account group rel ID of this commerce discount commerce account group rel
	 */
	public long getCommerceDiscountCommerceAccountGroupRelId();

	/**
	 * Sets the commerce discount commerce account group rel ID of this commerce discount commerce account group rel.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the commerce discount commerce account group rel ID of this commerce discount commerce account group rel
	 */
	public void setCommerceDiscountCommerceAccountGroupRelId(
		long commerceDiscountCommerceAccountGroupRelId);

	/**
	 * Returns the company ID of this commerce discount commerce account group rel.
	 *
	 * @return the company ID of this commerce discount commerce account group rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce discount commerce account group rel.
	 *
	 * @param companyId the company ID of this commerce discount commerce account group rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce discount commerce account group rel.
	 *
	 * @return the user ID of this commerce discount commerce account group rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce discount commerce account group rel.
	 *
	 * @param userId the user ID of this commerce discount commerce account group rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce discount commerce account group rel.
	 *
	 * @return the user uuid of this commerce discount commerce account group rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce discount commerce account group rel.
	 *
	 * @param userUuid the user uuid of this commerce discount commerce account group rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce discount commerce account group rel.
	 *
	 * @return the user name of this commerce discount commerce account group rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce discount commerce account group rel.
	 *
	 * @param userName the user name of this commerce discount commerce account group rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce discount commerce account group rel.
	 *
	 * @return the create date of this commerce discount commerce account group rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce discount commerce account group rel.
	 *
	 * @param createDate the create date of this commerce discount commerce account group rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce discount commerce account group rel.
	 *
	 * @return the modified date of this commerce discount commerce account group rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce discount commerce account group rel.
	 *
	 * @param modifiedDate the modified date of this commerce discount commerce account group rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce discount ID of this commerce discount commerce account group rel.
	 *
	 * @return the commerce discount ID of this commerce discount commerce account group rel
	 */
	public long getCommerceDiscountId();

	/**
	 * Sets the commerce discount ID of this commerce discount commerce account group rel.
	 *
	 * @param commerceDiscountId the commerce discount ID of this commerce discount commerce account group rel
	 */
	public void setCommerceDiscountId(long commerceDiscountId);

	/**
	 * Returns the commerce account group ID of this commerce discount commerce account group rel.
	 *
	 * @return the commerce account group ID of this commerce discount commerce account group rel
	 */
	public long getCommerceAccountGroupId();

	/**
	 * Sets the commerce account group ID of this commerce discount commerce account group rel.
	 *
	 * @param commerceAccountGroupId the commerce account group ID of this commerce discount commerce account group rel
	 */
	public void setCommerceAccountGroupId(long commerceAccountGroupId);

	@Override
	public CommerceDiscountCommerceAccountGroupRel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}