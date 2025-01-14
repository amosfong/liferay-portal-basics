/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.revert.schema.version.model;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the RSVEntry service. Represents a row in the &quot;RSVEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.revert.schema.version.model.impl.RSVEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.revert.schema.version.model.impl.RSVEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RSVEntry
 * @generated
 */
@ProviderType
public interface RSVEntryModel
	extends BaseModel<RSVEntry>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a rsv entry model instance should use the {@link RSVEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this rsv entry.
	 *
	 * @return the primary key of this rsv entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this rsv entry.
	 *
	 * @param primaryKey the primary key of this rsv entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this rsv entry.
	 *
	 * @return the mvcc version of this rsv entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this rsv entry.
	 *
	 * @param mvccVersion the mvcc version of this rsv entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the rsv entry ID of this rsv entry.
	 *
	 * @return the rsv entry ID of this rsv entry
	 */
	public long getRsvEntryId();

	/**
	 * Sets the rsv entry ID of this rsv entry.
	 *
	 * @param rsvEntryId the rsv entry ID of this rsv entry
	 */
	public void setRsvEntryId(long rsvEntryId);

	/**
	 * Returns the company ID of this rsv entry.
	 *
	 * @return the company ID of this rsv entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this rsv entry.
	 *
	 * @param companyId the company ID of this rsv entry
	 */
	@Override
	public void setCompanyId(long companyId);

	@Override
	public RSVEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}