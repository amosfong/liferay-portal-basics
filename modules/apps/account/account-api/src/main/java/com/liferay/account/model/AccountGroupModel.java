/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AccountGroup service. Represents a row in the &quot;AccountGroup&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.account.model.impl.AccountGroupModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.account.model.impl.AccountGroupImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountGroup
 * @generated
 */
@ProviderType
public interface AccountGroupModel
	extends BaseModel<AccountGroup>, ExternalReferenceCodeModel, MVCCModel,
			ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a account group model instance should use the {@link AccountGroup} interface instead.
	 */

	/**
	 * Returns the primary key of this account group.
	 *
	 * @return the primary key of this account group
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this account group.
	 *
	 * @param primaryKey the primary key of this account group
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this account group.
	 *
	 * @return the mvcc version of this account group
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this account group.
	 *
	 * @param mvccVersion the mvcc version of this account group
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this account group.
	 *
	 * @return the uuid of this account group
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this account group.
	 *
	 * @param uuid the uuid of this account group
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this account group.
	 *
	 * @return the external reference code of this account group
	 */
	@AutoEscape
	@Override
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this account group.
	 *
	 * @param externalReferenceCode the external reference code of this account group
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the account group ID of this account group.
	 *
	 * @return the account group ID of this account group
	 */
	public long getAccountGroupId();

	/**
	 * Sets the account group ID of this account group.
	 *
	 * @param accountGroupId the account group ID of this account group
	 */
	public void setAccountGroupId(long accountGroupId);

	/**
	 * Returns the company ID of this account group.
	 *
	 * @return the company ID of this account group
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this account group.
	 *
	 * @param companyId the company ID of this account group
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this account group.
	 *
	 * @return the user ID of this account group
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this account group.
	 *
	 * @param userId the user ID of this account group
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this account group.
	 *
	 * @return the user uuid of this account group
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this account group.
	 *
	 * @param userUuid the user uuid of this account group
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this account group.
	 *
	 * @return the user name of this account group
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this account group.
	 *
	 * @param userName the user name of this account group
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this account group.
	 *
	 * @return the create date of this account group
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this account group.
	 *
	 * @param createDate the create date of this account group
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this account group.
	 *
	 * @return the modified date of this account group
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this account group.
	 *
	 * @param modifiedDate the modified date of this account group
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the default account group of this account group.
	 *
	 * @return the default account group of this account group
	 */
	public boolean getDefaultAccountGroup();

	/**
	 * Returns <code>true</code> if this account group is default account group.
	 *
	 * @return <code>true</code> if this account group is default account group; <code>false</code> otherwise
	 */
	public boolean isDefaultAccountGroup();

	/**
	 * Sets whether this account group is default account group.
	 *
	 * @param defaultAccountGroup the default account group of this account group
	 */
	public void setDefaultAccountGroup(boolean defaultAccountGroup);

	/**
	 * Returns the description of this account group.
	 *
	 * @return the description of this account group
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this account group.
	 *
	 * @param description the description of this account group
	 */
	public void setDescription(String description);

	/**
	 * Returns the name of this account group.
	 *
	 * @return the name of this account group
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this account group.
	 *
	 * @param name the name of this account group
	 */
	public void setName(String name);

	/**
	 * Returns the type of this account group.
	 *
	 * @return the type of this account group
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this account group.
	 *
	 * @param type the type of this account group
	 */
	public void setType(String type);

	@Override
	public AccountGroup cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}