/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.model;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CTPreferences service. Represents a row in the &quot;CTPreferences&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.change.tracking.model.impl.CTPreferencesModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.change.tracking.model.impl.CTPreferencesImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTPreferences
 * @generated
 */
@ProviderType
public interface CTPreferencesModel
	extends BaseModel<CTPreferences>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ct preferences model instance should use the {@link CTPreferences} interface instead.
	 */

	/**
	 * Returns the primary key of this ct preferences.
	 *
	 * @return the primary key of this ct preferences
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ct preferences.
	 *
	 * @param primaryKey the primary key of this ct preferences
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ct preferences.
	 *
	 * @return the mvcc version of this ct preferences
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ct preferences.
	 *
	 * @param mvccVersion the mvcc version of this ct preferences
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct preferences ID of this ct preferences.
	 *
	 * @return the ct preferences ID of this ct preferences
	 */
	public long getCtPreferencesId();

	/**
	 * Sets the ct preferences ID of this ct preferences.
	 *
	 * @param ctPreferencesId the ct preferences ID of this ct preferences
	 */
	public void setCtPreferencesId(long ctPreferencesId);

	/**
	 * Returns the company ID of this ct preferences.
	 *
	 * @return the company ID of this ct preferences
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ct preferences.
	 *
	 * @param companyId the company ID of this ct preferences
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ct preferences.
	 *
	 * @return the user ID of this ct preferences
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this ct preferences.
	 *
	 * @param userId the user ID of this ct preferences
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ct preferences.
	 *
	 * @return the user uuid of this ct preferences
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ct preferences.
	 *
	 * @param userUuid the user uuid of this ct preferences
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the ct collection ID of this ct preferences.
	 *
	 * @return the ct collection ID of this ct preferences
	 */
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this ct preferences.
	 *
	 * @param ctCollectionId the ct collection ID of this ct preferences
	 */
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the previous ct collection ID of this ct preferences.
	 *
	 * @return the previous ct collection ID of this ct preferences
	 */
	public long getPreviousCtCollectionId();

	/**
	 * Sets the previous ct collection ID of this ct preferences.
	 *
	 * @param previousCtCollectionId the previous ct collection ID of this ct preferences
	 */
	public void setPreviousCtCollectionId(long previousCtCollectionId);

	/**
	 * Returns the confirmation enabled of this ct preferences.
	 *
	 * @return the confirmation enabled of this ct preferences
	 */
	public boolean getConfirmationEnabled();

	/**
	 * Returns <code>true</code> if this ct preferences is confirmation enabled.
	 *
	 * @return <code>true</code> if this ct preferences is confirmation enabled; <code>false</code> otherwise
	 */
	public boolean isConfirmationEnabled();

	/**
	 * Sets whether this ct preferences is confirmation enabled.
	 *
	 * @param confirmationEnabled the confirmation enabled of this ct preferences
	 */
	public void setConfirmationEnabled(boolean confirmationEnabled);

	@Override
	public CTPreferences cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}