/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ListType service. Represents a row in the &quot;ListType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.ListTypeModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.ListTypeImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ListType
 * @generated
 */
@ProviderType
public interface ListTypeModel
	extends BaseModel<ListType>, MVCCModel, ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a list type model instance should use the {@link ListType} interface instead.
	 */

	/**
	 * Returns the primary key of this list type.
	 *
	 * @return the primary key of this list type
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this list type.
	 *
	 * @param primaryKey the primary key of this list type
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this list type.
	 *
	 * @return the mvcc version of this list type
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this list type.
	 *
	 * @param mvccVersion the mvcc version of this list type
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this list type.
	 *
	 * @return the uuid of this list type
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this list type.
	 *
	 * @param uuid the uuid of this list type
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the list type ID of this list type.
	 *
	 * @return the list type ID of this list type
	 */
	public long getListTypeId();

	/**
	 * Sets the list type ID of this list type.
	 *
	 * @param listTypeId the list type ID of this list type
	 */
	public void setListTypeId(long listTypeId);

	/**
	 * Returns the company ID of this list type.
	 *
	 * @return the company ID of this list type
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this list type.
	 *
	 * @param companyId the company ID of this list type
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this list type.
	 *
	 * @return the user ID of this list type
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this list type.
	 *
	 * @param userId the user ID of this list type
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this list type.
	 *
	 * @return the user uuid of this list type
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this list type.
	 *
	 * @param userUuid the user uuid of this list type
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this list type.
	 *
	 * @return the user name of this list type
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this list type.
	 *
	 * @param userName the user name of this list type
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this list type.
	 *
	 * @return the create date of this list type
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this list type.
	 *
	 * @param createDate the create date of this list type
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this list type.
	 *
	 * @return the modified date of this list type
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this list type.
	 *
	 * @param modifiedDate the modified date of this list type
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this list type.
	 *
	 * @return the name of this list type
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this list type.
	 *
	 * @param name the name of this list type
	 */
	public void setName(String name);

	/**
	 * Returns the type of this list type.
	 *
	 * @return the type of this list type
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this list type.
	 *
	 * @param type the type of this list type
	 */
	public void setType(String type);

	@Override
	public ListType cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}