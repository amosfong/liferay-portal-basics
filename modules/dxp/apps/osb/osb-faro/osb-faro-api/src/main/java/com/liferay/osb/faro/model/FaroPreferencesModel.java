/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FaroPreferences service. Represents a row in the &quot;OSBFaro_FaroPreferences&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.faro.model.impl.FaroPreferencesModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.faro.model.impl.FaroPreferencesImpl</code>.
 * </p>
 *
 * @author Matthew Kong
 * @see FaroPreferences
 * @generated
 */
@ProviderType
public interface FaroPreferencesModel
	extends BaseModel<FaroPreferences>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a faro preferences model instance should use the {@link FaroPreferences} interface instead.
	 */

	/**
	 * Returns the primary key of this faro preferences.
	 *
	 * @return the primary key of this faro preferences
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this faro preferences.
	 *
	 * @param primaryKey the primary key of this faro preferences
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this faro preferences.
	 *
	 * @return the mvcc version of this faro preferences
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this faro preferences.
	 *
	 * @param mvccVersion the mvcc version of this faro preferences
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the faro preferences ID of this faro preferences.
	 *
	 * @return the faro preferences ID of this faro preferences
	 */
	public long getFaroPreferencesId();

	/**
	 * Sets the faro preferences ID of this faro preferences.
	 *
	 * @param faroPreferencesId the faro preferences ID of this faro preferences
	 */
	public void setFaroPreferencesId(long faroPreferencesId);

	/**
	 * Returns the group ID of this faro preferences.
	 *
	 * @return the group ID of this faro preferences
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this faro preferences.
	 *
	 * @param groupId the group ID of this faro preferences
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this faro preferences.
	 *
	 * @return the company ID of this faro preferences
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this faro preferences.
	 *
	 * @param companyId the company ID of this faro preferences
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this faro preferences.
	 *
	 * @return the user ID of this faro preferences
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this faro preferences.
	 *
	 * @param userId the user ID of this faro preferences
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this faro preferences.
	 *
	 * @return the user uuid of this faro preferences
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this faro preferences.
	 *
	 * @param userUuid the user uuid of this faro preferences
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this faro preferences.
	 *
	 * @return the user name of this faro preferences
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this faro preferences.
	 *
	 * @param userName the user name of this faro preferences
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create time of this faro preferences.
	 *
	 * @return the create time of this faro preferences
	 */
	public long getCreateTime();

	/**
	 * Sets the create time of this faro preferences.
	 *
	 * @param createTime the create time of this faro preferences
	 */
	public void setCreateTime(long createTime);

	/**
	 * Returns the modified time of this faro preferences.
	 *
	 * @return the modified time of this faro preferences
	 */
	public long getModifiedTime();

	/**
	 * Sets the modified time of this faro preferences.
	 *
	 * @param modifiedTime the modified time of this faro preferences
	 */
	public void setModifiedTime(long modifiedTime);

	/**
	 * Returns the owner ID of this faro preferences.
	 *
	 * @return the owner ID of this faro preferences
	 */
	public long getOwnerId();

	/**
	 * Sets the owner ID of this faro preferences.
	 *
	 * @param ownerId the owner ID of this faro preferences
	 */
	public void setOwnerId(long ownerId);

	/**
	 * Returns the preferences of this faro preferences.
	 *
	 * @return the preferences of this faro preferences
	 */
	@AutoEscape
	public String getPreferences();

	/**
	 * Sets the preferences of this faro preferences.
	 *
	 * @param preferences the preferences of this faro preferences
	 */
	public void setPreferences(String preferences);

	@Override
	public FaroPreferences cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}