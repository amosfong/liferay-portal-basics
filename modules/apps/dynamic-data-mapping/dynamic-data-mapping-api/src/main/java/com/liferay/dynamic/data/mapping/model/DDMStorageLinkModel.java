/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMStorageLink service. Represents a row in the &quot;DDMStorageLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMStorageLinkModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMStorageLinkImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStorageLink
 * @generated
 */
@ProviderType
public interface DDMStorageLinkModel
	extends AttachedModel, BaseModel<DDMStorageLink>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm storage link model instance should use the {@link DDMStorageLink} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm storage link.
	 *
	 * @return the primary key of this ddm storage link
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm storage link.
	 *
	 * @param primaryKey the primary key of this ddm storage link
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm storage link.
	 *
	 * @return the mvcc version of this ddm storage link
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm storage link.
	 *
	 * @param mvccVersion the mvcc version of this ddm storage link
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this ddm storage link.
	 *
	 * @return the uuid of this ddm storage link
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this ddm storage link.
	 *
	 * @param uuid the uuid of this ddm storage link
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the storage link ID of this ddm storage link.
	 *
	 * @return the storage link ID of this ddm storage link
	 */
	public long getStorageLinkId();

	/**
	 * Sets the storage link ID of this ddm storage link.
	 *
	 * @param storageLinkId the storage link ID of this ddm storage link
	 */
	public void setStorageLinkId(long storageLinkId);

	/**
	 * Returns the company ID of this ddm storage link.
	 *
	 * @return the company ID of this ddm storage link
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm storage link.
	 *
	 * @param companyId the company ID of this ddm storage link
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the fully qualified class name of this ddm storage link.
	 *
	 * @return the fully qualified class name of this ddm storage link
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this ddm storage link.
	 *
	 * @return the class name ID of this ddm storage link
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this ddm storage link.
	 *
	 * @param classNameId the class name ID of this ddm storage link
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this ddm storage link.
	 *
	 * @return the class pk of this ddm storage link
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this ddm storage link.
	 *
	 * @param classPK the class pk of this ddm storage link
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the structure ID of this ddm storage link.
	 *
	 * @return the structure ID of this ddm storage link
	 */
	public long getStructureId();

	/**
	 * Sets the structure ID of this ddm storage link.
	 *
	 * @param structureId the structure ID of this ddm storage link
	 */
	public void setStructureId(long structureId);

	/**
	 * Returns the structure version ID of this ddm storage link.
	 *
	 * @return the structure version ID of this ddm storage link
	 */
	public long getStructureVersionId();

	/**
	 * Sets the structure version ID of this ddm storage link.
	 *
	 * @param structureVersionId the structure version ID of this ddm storage link
	 */
	public void setStructureVersionId(long structureVersionId);

	@Override
	public DDMStorageLink cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}