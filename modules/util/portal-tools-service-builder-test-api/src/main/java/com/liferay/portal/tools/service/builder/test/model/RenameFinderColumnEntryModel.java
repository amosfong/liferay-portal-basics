/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the RenameFinderColumnEntry service. Represents a row in the &quot;RenameFinderColumnEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.tools.service.builder.test.model.impl.RenameFinderColumnEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.tools.service.builder.test.model.impl.RenameFinderColumnEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RenameFinderColumnEntry
 * @generated
 */
@ProviderType
public interface RenameFinderColumnEntryModel
	extends BaseModel<RenameFinderColumnEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a rename finder column entry model instance should use the {@link RenameFinderColumnEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this rename finder column entry.
	 *
	 * @return the primary key of this rename finder column entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this rename finder column entry.
	 *
	 * @param primaryKey the primary key of this rename finder column entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the rename finder column entry ID of this rename finder column entry.
	 *
	 * @return the rename finder column entry ID of this rename finder column entry
	 */
	public long getRenameFinderColumnEntryId();

	/**
	 * Sets the rename finder column entry ID of this rename finder column entry.
	 *
	 * @param renameFinderColumnEntryId the rename finder column entry ID of this rename finder column entry
	 */
	public void setRenameFinderColumnEntryId(long renameFinderColumnEntryId);

	/**
	 * Returns the group ID of this rename finder column entry.
	 *
	 * @return the group ID of this rename finder column entry
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this rename finder column entry.
	 *
	 * @param groupId the group ID of this rename finder column entry
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the column to rename of this rename finder column entry.
	 *
	 * @return the column to rename of this rename finder column entry
	 */
	@AutoEscape
	public String getColumnToRename();

	/**
	 * Sets the column to rename of this rename finder column entry.
	 *
	 * @param columnToRename the column to rename of this rename finder column entry
	 */
	public void setColumnToRename(String columnToRename);

	@Override
	public RenameFinderColumnEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}