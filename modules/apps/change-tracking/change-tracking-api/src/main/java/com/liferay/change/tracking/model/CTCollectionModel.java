/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CTCollection service. Represents a row in the &quot;CTCollection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.change.tracking.model.impl.CTCollectionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.change.tracking.model.impl.CTCollectionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTCollection
 * @generated
 */
@ProviderType
public interface CTCollectionModel
	extends BaseModel<CTCollection>, ExternalReferenceCodeModel, MVCCModel,
			ShardedModel, StagedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ct collection model instance should use the {@link CTCollection} interface instead.
	 */

	/**
	 * Returns the primary key of this ct collection.
	 *
	 * @return the primary key of this ct collection
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ct collection.
	 *
	 * @param primaryKey the primary key of this ct collection
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ct collection.
	 *
	 * @return the mvcc version of this ct collection
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ct collection.
	 *
	 * @param mvccVersion the mvcc version of this ct collection
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this ct collection.
	 *
	 * @return the uuid of this ct collection
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this ct collection.
	 *
	 * @param uuid the uuid of this ct collection
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this ct collection.
	 *
	 * @return the external reference code of this ct collection
	 */
	@AutoEscape
	@Override
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this ct collection.
	 *
	 * @param externalReferenceCode the external reference code of this ct collection
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the ct collection ID of this ct collection.
	 *
	 * @return the ct collection ID of this ct collection
	 */
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this ct collection.
	 *
	 * @param ctCollectionId the ct collection ID of this ct collection
	 */
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the company ID of this ct collection.
	 *
	 * @return the company ID of this ct collection
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ct collection.
	 *
	 * @param companyId the company ID of this ct collection
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ct collection.
	 *
	 * @return the user ID of this ct collection
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this ct collection.
	 *
	 * @param userId the user ID of this ct collection
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ct collection.
	 *
	 * @return the user uuid of this ct collection
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ct collection.
	 *
	 * @param userUuid the user uuid of this ct collection
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this ct collection.
	 *
	 * @return the create date of this ct collection
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this ct collection.
	 *
	 * @param createDate the create date of this ct collection
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this ct collection.
	 *
	 * @return the modified date of this ct collection
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ct collection.
	 *
	 * @param modifiedDate the modified date of this ct collection
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the ct remote ID of this ct collection.
	 *
	 * @return the ct remote ID of this ct collection
	 */
	public long getCtRemoteId();

	/**
	 * Sets the ct remote ID of this ct collection.
	 *
	 * @param ctRemoteId the ct remote ID of this ct collection
	 */
	public void setCtRemoteId(long ctRemoteId);

	/**
	 * Returns the schema version ID of this ct collection.
	 *
	 * @return the schema version ID of this ct collection
	 */
	public long getSchemaVersionId();

	/**
	 * Sets the schema version ID of this ct collection.
	 *
	 * @param schemaVersionId the schema version ID of this ct collection
	 */
	public void setSchemaVersionId(long schemaVersionId);

	/**
	 * Returns the name of this ct collection.
	 *
	 * @return the name of this ct collection
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this ct collection.
	 *
	 * @param name the name of this ct collection
	 */
	public void setName(String name);

	/**
	 * Returns the description of this ct collection.
	 *
	 * @return the description of this ct collection
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this ct collection.
	 *
	 * @param description the description of this ct collection
	 */
	public void setDescription(String description);

	/**
	 * Returns the on demand user ID of this ct collection.
	 *
	 * @return the on demand user ID of this ct collection
	 */
	public long getOnDemandUserId();

	/**
	 * Sets the on demand user ID of this ct collection.
	 *
	 * @param onDemandUserId the on demand user ID of this ct collection
	 */
	public void setOnDemandUserId(long onDemandUserId);

	/**
	 * Returns the on demand user uuid of this ct collection.
	 *
	 * @return the on demand user uuid of this ct collection
	 */
	public String getOnDemandUserUuid();

	/**
	 * Sets the on demand user uuid of this ct collection.
	 *
	 * @param onDemandUserUuid the on demand user uuid of this ct collection
	 */
	public void setOnDemandUserUuid(String onDemandUserUuid);

	/**
	 * Returns the shareable of this ct collection.
	 *
	 * @return the shareable of this ct collection
	 */
	public boolean getShareable();

	/**
	 * Returns <code>true</code> if this ct collection is shareable.
	 *
	 * @return <code>true</code> if this ct collection is shareable; <code>false</code> otherwise
	 */
	public boolean isShareable();

	/**
	 * Sets whether this ct collection is shareable.
	 *
	 * @param shareable the shareable of this ct collection
	 */
	public void setShareable(boolean shareable);

	/**
	 * Returns the status of this ct collection.
	 *
	 * @return the status of this ct collection
	 */
	public int getStatus();

	/**
	 * Sets the status of this ct collection.
	 *
	 * @param status the status of this ct collection
	 */
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this ct collection.
	 *
	 * @return the status by user ID of this ct collection
	 */
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this ct collection.
	 *
	 * @param statusByUserId the status by user ID of this ct collection
	 */
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this ct collection.
	 *
	 * @return the status by user uuid of this ct collection
	 */
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this ct collection.
	 *
	 * @param statusByUserUuid the status by user uuid of this ct collection
	 */
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status date of this ct collection.
	 *
	 * @return the status date of this ct collection
	 */
	public Date getStatusDate();

	/**
	 * Sets the status date of this ct collection.
	 *
	 * @param statusDate the status date of this ct collection
	 */
	public void setStatusDate(Date statusDate);

	@Override
	public CTCollection cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}