/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.template.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the TemplateEntry service. Represents a row in the &quot;TemplateEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.template.model.impl.TemplateEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.template.model.impl.TemplateEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TemplateEntry
 * @generated
 */
@ProviderType
public interface TemplateEntryModel
	extends BaseModel<TemplateEntry>, CTModel<TemplateEntry>,
			ExternalReferenceCodeModel, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a template entry model instance should use the {@link TemplateEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this template entry.
	 *
	 * @return the primary key of this template entry
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this template entry.
	 *
	 * @param primaryKey the primary key of this template entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this template entry.
	 *
	 * @return the mvcc version of this template entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this template entry.
	 *
	 * @param mvccVersion the mvcc version of this template entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this template entry.
	 *
	 * @return the ct collection ID of this template entry
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this template entry.
	 *
	 * @param ctCollectionId the ct collection ID of this template entry
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this template entry.
	 *
	 * @return the uuid of this template entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this template entry.
	 *
	 * @param uuid the uuid of this template entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this template entry.
	 *
	 * @return the external reference code of this template entry
	 */
	@AutoEscape
	@Override
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this template entry.
	 *
	 * @param externalReferenceCode the external reference code of this template entry
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the template entry ID of this template entry.
	 *
	 * @return the template entry ID of this template entry
	 */
	public long getTemplateEntryId();

	/**
	 * Sets the template entry ID of this template entry.
	 *
	 * @param templateEntryId the template entry ID of this template entry
	 */
	public void setTemplateEntryId(long templateEntryId);

	/**
	 * Returns the group ID of this template entry.
	 *
	 * @return the group ID of this template entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this template entry.
	 *
	 * @param groupId the group ID of this template entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this template entry.
	 *
	 * @return the company ID of this template entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this template entry.
	 *
	 * @param companyId the company ID of this template entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this template entry.
	 *
	 * @return the user ID of this template entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this template entry.
	 *
	 * @param userId the user ID of this template entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this template entry.
	 *
	 * @return the user uuid of this template entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this template entry.
	 *
	 * @param userUuid the user uuid of this template entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this template entry.
	 *
	 * @return the user name of this template entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this template entry.
	 *
	 * @param userName the user name of this template entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this template entry.
	 *
	 * @return the create date of this template entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this template entry.
	 *
	 * @param createDate the create date of this template entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this template entry.
	 *
	 * @return the modified date of this template entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this template entry.
	 *
	 * @param modifiedDate the modified date of this template entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the ddm template ID of this template entry.
	 *
	 * @return the ddm template ID of this template entry
	 */
	public long getDDMTemplateId();

	/**
	 * Sets the ddm template ID of this template entry.
	 *
	 * @param ddmTemplateId the ddm template ID of this template entry
	 */
	public void setDDMTemplateId(long ddmTemplateId);

	/**
	 * Returns the info item class name of this template entry.
	 *
	 * @return the info item class name of this template entry
	 */
	@AutoEscape
	public String getInfoItemClassName();

	/**
	 * Sets the info item class name of this template entry.
	 *
	 * @param infoItemClassName the info item class name of this template entry
	 */
	public void setInfoItemClassName(String infoItemClassName);

	/**
	 * Returns the info item form variation key of this template entry.
	 *
	 * @return the info item form variation key of this template entry
	 */
	@AutoEscape
	public String getInfoItemFormVariationKey();

	/**
	 * Sets the info item form variation key of this template entry.
	 *
	 * @param infoItemFormVariationKey the info item form variation key of this template entry
	 */
	public void setInfoItemFormVariationKey(String infoItemFormVariationKey);

	/**
	 * Returns the last publish date of this template entry.
	 *
	 * @return the last publish date of this template entry
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this template entry.
	 *
	 * @param lastPublishDate the last publish date of this template entry
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public TemplateEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}