/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ObjectFilter service. Represents a row in the &quot;ObjectFilter&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.object.model.impl.ObjectFilterModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.object.model.impl.ObjectFilterImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectFilter
 * @generated
 */
@ProviderType
public interface ObjectFilterModel
	extends BaseModel<ObjectFilter>, MVCCModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a object filter model instance should use the {@link ObjectFilter} interface instead.
	 */

	/**
	 * Returns the primary key of this object filter.
	 *
	 * @return the primary key of this object filter
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this object filter.
	 *
	 * @param primaryKey the primary key of this object filter
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this object filter.
	 *
	 * @return the mvcc version of this object filter
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this object filter.
	 *
	 * @param mvccVersion the mvcc version of this object filter
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this object filter.
	 *
	 * @return the uuid of this object filter
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this object filter.
	 *
	 * @param uuid the uuid of this object filter
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the object filter ID of this object filter.
	 *
	 * @return the object filter ID of this object filter
	 */
	public long getObjectFilterId();

	/**
	 * Sets the object filter ID of this object filter.
	 *
	 * @param objectFilterId the object filter ID of this object filter
	 */
	public void setObjectFilterId(long objectFilterId);

	/**
	 * Returns the company ID of this object filter.
	 *
	 * @return the company ID of this object filter
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this object filter.
	 *
	 * @param companyId the company ID of this object filter
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this object filter.
	 *
	 * @return the user ID of this object filter
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this object filter.
	 *
	 * @param userId the user ID of this object filter
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this object filter.
	 *
	 * @return the user uuid of this object filter
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this object filter.
	 *
	 * @param userUuid the user uuid of this object filter
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this object filter.
	 *
	 * @return the user name of this object filter
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this object filter.
	 *
	 * @param userName the user name of this object filter
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this object filter.
	 *
	 * @return the create date of this object filter
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this object filter.
	 *
	 * @param createDate the create date of this object filter
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this object filter.
	 *
	 * @return the modified date of this object filter
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this object filter.
	 *
	 * @param modifiedDate the modified date of this object filter
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the object field ID of this object filter.
	 *
	 * @return the object field ID of this object filter
	 */
	public long getObjectFieldId();

	/**
	 * Sets the object field ID of this object filter.
	 *
	 * @param objectFieldId the object field ID of this object filter
	 */
	public void setObjectFieldId(long objectFieldId);

	/**
	 * Returns the filter by of this object filter.
	 *
	 * @return the filter by of this object filter
	 */
	@AutoEscape
	public String getFilterBy();

	/**
	 * Sets the filter by of this object filter.
	 *
	 * @param filterBy the filter by of this object filter
	 */
	public void setFilterBy(String filterBy);

	/**
	 * Returns the filter type of this object filter.
	 *
	 * @return the filter type of this object filter
	 */
	@AutoEscape
	public String getFilterType();

	/**
	 * Sets the filter type of this object filter.
	 *
	 * @param filterType the filter type of this object filter
	 */
	public void setFilterType(String filterType);

	/**
	 * Returns the json of this object filter.
	 *
	 * @return the json of this object filter
	 */
	@AutoEscape
	public String getJSON();

	/**
	 * Sets the json of this object filter.
	 *
	 * @param json the json of this object filter
	 */
	public void setJSON(String json);

	@Override
	public ObjectFilter cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}