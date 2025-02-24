/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.model;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CTProcess service. Represents a row in the &quot;CTProcess&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.change.tracking.model.impl.CTProcessModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.change.tracking.model.impl.CTProcessImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTProcess
 * @generated
 */
@ProviderType
public interface CTProcessModel
	extends BaseModel<CTProcess>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ct process model instance should use the {@link CTProcess} interface instead.
	 */

	/**
	 * Returns the primary key of this ct process.
	 *
	 * @return the primary key of this ct process
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ct process.
	 *
	 * @param primaryKey the primary key of this ct process
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ct process.
	 *
	 * @return the mvcc version of this ct process
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ct process.
	 *
	 * @param mvccVersion the mvcc version of this ct process
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct process ID of this ct process.
	 *
	 * @return the ct process ID of this ct process
	 */
	public long getCtProcessId();

	/**
	 * Sets the ct process ID of this ct process.
	 *
	 * @param ctProcessId the ct process ID of this ct process
	 */
	public void setCtProcessId(long ctProcessId);

	/**
	 * Returns the company ID of this ct process.
	 *
	 * @return the company ID of this ct process
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ct process.
	 *
	 * @param companyId the company ID of this ct process
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ct process.
	 *
	 * @return the user ID of this ct process
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this ct process.
	 *
	 * @param userId the user ID of this ct process
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ct process.
	 *
	 * @return the user uuid of this ct process
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ct process.
	 *
	 * @param userUuid the user uuid of this ct process
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this ct process.
	 *
	 * @return the create date of this ct process
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this ct process.
	 *
	 * @param createDate the create date of this ct process
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the ct collection ID of this ct process.
	 *
	 * @return the ct collection ID of this ct process
	 */
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this ct process.
	 *
	 * @param ctCollectionId the ct collection ID of this ct process
	 */
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the background task ID of this ct process.
	 *
	 * @return the background task ID of this ct process
	 */
	public long getBackgroundTaskId();

	/**
	 * Sets the background task ID of this ct process.
	 *
	 * @param backgroundTaskId the background task ID of this ct process
	 */
	public void setBackgroundTaskId(long backgroundTaskId);

	/**
	 * Returns the type of this ct process.
	 *
	 * @return the type of this ct process
	 */
	public int getType();

	/**
	 * Sets the type of this ct process.
	 *
	 * @param type the type of this ct process
	 */
	public void setType(int type);

	@Override
	public CTProcess cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}