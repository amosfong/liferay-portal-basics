/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceChannelAccountEntryRel service. Represents a row in the &quot;CChannelAccountEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.product.model.impl.CommerceChannelAccountEntryRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.product.model.impl.CommerceChannelAccountEntryRelImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelAccountEntryRel
 * @generated
 */
@ProviderType
public interface CommerceChannelAccountEntryRelModel
	extends AttachedModel, AuditedModel,
			BaseModel<CommerceChannelAccountEntryRel>,
			CTModel<CommerceChannelAccountEntryRel>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce channel account entry rel model instance should use the {@link CommerceChannelAccountEntryRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce channel account entry rel.
	 *
	 * @return the primary key of this commerce channel account entry rel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce channel account entry rel.
	 *
	 * @param primaryKey the primary key of this commerce channel account entry rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce channel account entry rel.
	 *
	 * @return the mvcc version of this commerce channel account entry rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce channel account entry rel.
	 *
	 * @param mvccVersion the mvcc version of this commerce channel account entry rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this commerce channel account entry rel.
	 *
	 * @return the ct collection ID of this commerce channel account entry rel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this commerce channel account entry rel.
	 *
	 * @param ctCollectionId the ct collection ID of this commerce channel account entry rel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the commerce channel account entry rel ID of this commerce channel account entry rel.
	 *
	 * @return the commerce channel account entry rel ID of this commerce channel account entry rel
	 */
	public long getCommerceChannelAccountEntryRelId();

	/**
	 * Sets the commerce channel account entry rel ID of this commerce channel account entry rel.
	 *
	 * @param commerceChannelAccountEntryRelId the commerce channel account entry rel ID of this commerce channel account entry rel
	 */
	public void setCommerceChannelAccountEntryRelId(
		long commerceChannelAccountEntryRelId);

	/**
	 * Returns the company ID of this commerce channel account entry rel.
	 *
	 * @return the company ID of this commerce channel account entry rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce channel account entry rel.
	 *
	 * @param companyId the company ID of this commerce channel account entry rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce channel account entry rel.
	 *
	 * @return the user ID of this commerce channel account entry rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce channel account entry rel.
	 *
	 * @param userId the user ID of this commerce channel account entry rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce channel account entry rel.
	 *
	 * @return the user uuid of this commerce channel account entry rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce channel account entry rel.
	 *
	 * @param userUuid the user uuid of this commerce channel account entry rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce channel account entry rel.
	 *
	 * @return the user name of this commerce channel account entry rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce channel account entry rel.
	 *
	 * @param userName the user name of this commerce channel account entry rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce channel account entry rel.
	 *
	 * @return the create date of this commerce channel account entry rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce channel account entry rel.
	 *
	 * @param createDate the create date of this commerce channel account entry rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce channel account entry rel.
	 *
	 * @return the modified date of this commerce channel account entry rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce channel account entry rel.
	 *
	 * @param modifiedDate the modified date of this commerce channel account entry rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the account entry ID of this commerce channel account entry rel.
	 *
	 * @return the account entry ID of this commerce channel account entry rel
	 */
	public long getAccountEntryId();

	/**
	 * Sets the account entry ID of this commerce channel account entry rel.
	 *
	 * @param accountEntryId the account entry ID of this commerce channel account entry rel
	 */
	public void setAccountEntryId(long accountEntryId);

	/**
	 * Returns the fully qualified class name of this commerce channel account entry rel.
	 *
	 * @return the fully qualified class name of this commerce channel account entry rel
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this commerce channel account entry rel.
	 *
	 * @return the class name ID of this commerce channel account entry rel
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this commerce channel account entry rel.
	 *
	 * @param classNameId the class name ID of this commerce channel account entry rel
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this commerce channel account entry rel.
	 *
	 * @return the class pk of this commerce channel account entry rel
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this commerce channel account entry rel.
	 *
	 * @param classPK the class pk of this commerce channel account entry rel
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the commerce channel ID of this commerce channel account entry rel.
	 *
	 * @return the commerce channel ID of this commerce channel account entry rel
	 */
	public long getCommerceChannelId();

	/**
	 * Sets the commerce channel ID of this commerce channel account entry rel.
	 *
	 * @param commerceChannelId the commerce channel ID of this commerce channel account entry rel
	 */
	public void setCommerceChannelId(long commerceChannelId);

	/**
	 * Returns the override eligibility of this commerce channel account entry rel.
	 *
	 * @return the override eligibility of this commerce channel account entry rel
	 */
	public boolean getOverrideEligibility();

	/**
	 * Returns <code>true</code> if this commerce channel account entry rel is override eligibility.
	 *
	 * @return <code>true</code> if this commerce channel account entry rel is override eligibility; <code>false</code> otherwise
	 */
	public boolean isOverrideEligibility();

	/**
	 * Sets whether this commerce channel account entry rel is override eligibility.
	 *
	 * @param overrideEligibility the override eligibility of this commerce channel account entry rel
	 */
	public void setOverrideEligibility(boolean overrideEligibility);

	/**
	 * Returns the priority of this commerce channel account entry rel.
	 *
	 * @return the priority of this commerce channel account entry rel
	 */
	public double getPriority();

	/**
	 * Sets the priority of this commerce channel account entry rel.
	 *
	 * @param priority the priority of this commerce channel account entry rel
	 */
	public void setPriority(double priority);

	/**
	 * Returns the type of this commerce channel account entry rel.
	 *
	 * @return the type of this commerce channel account entry rel
	 */
	public int getType();

	/**
	 * Sets the type of this commerce channel account entry rel.
	 *
	 * @param type the type of this commerce channel account entry rel
	 */
	public void setType(int type);

	@Override
	public CommerceChannelAccountEntryRel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}