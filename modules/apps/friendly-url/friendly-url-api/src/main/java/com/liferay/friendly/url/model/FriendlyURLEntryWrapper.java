/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FriendlyURLEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntry
 * @generated
 */
public class FriendlyURLEntryWrapper
	extends BaseModelWrapper<FriendlyURLEntry>
	implements FriendlyURLEntry, ModelWrapper<FriendlyURLEntry> {

	public FriendlyURLEntryWrapper(FriendlyURLEntry friendlyURLEntry) {
		super(friendlyURLEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("defaultLanguageId", getDefaultLanguageId());
		attributes.put("friendlyURLEntryId", getFriendlyURLEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		String defaultLanguageId = (String)attributes.get("defaultLanguageId");

		if (defaultLanguageId != null) {
			setDefaultLanguageId(defaultLanguageId);
		}

		Long friendlyURLEntryId = (Long)attributes.get("friendlyURLEntryId");

		if (friendlyURLEntryId != null) {
			setFriendlyURLEntryId(friendlyURLEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	@Override
	public FriendlyURLEntry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	@Override
	public String getCategorizedUrlTitle(String languageId) {
		return model.getCategorizedUrlTitle(languageId);
	}

	/**
	 * Returns the fully qualified class name of this friendly url entry.
	 *
	 * @return the fully qualified class name of this friendly url entry
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this friendly url entry.
	 *
	 * @return the class name ID of this friendly url entry
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class pk of this friendly url entry.
	 *
	 * @return the class pk of this friendly url entry
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the company ID of this friendly url entry.
	 *
	 * @return the company ID of this friendly url entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this friendly url entry.
	 *
	 * @return the create date of this friendly url entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the default language ID of this friendly url entry.
	 *
	 * @return the default language ID of this friendly url entry
	 */
	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
	}

	/**
	 * Returns the friendly url entry ID of this friendly url entry.
	 *
	 * @return the friendly url entry ID of this friendly url entry
	 */
	@Override
	public long getFriendlyURLEntryId() {
		return model.getFriendlyURLEntryId();
	}

	/**
	 * Returns the group ID of this friendly url entry.
	 *
	 * @return the group ID of this friendly url entry
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	@Override
	public Map<String, String> getLanguageIdToUrlTitleMap() {
		return model.getLanguageIdToUrlTitleMap();
	}

	/**
	 * Returns the modified date of this friendly url entry.
	 *
	 * @return the modified date of this friendly url entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this friendly url entry.
	 *
	 * @return the mvcc version of this friendly url entry
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this friendly url entry.
	 *
	 * @return the primary key of this friendly url entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public String getUrlTitle() {
		return model.getUrlTitle();
	}

	@Override
	public String getUrlTitle(String languageId) {
		return model.getUrlTitle(languageId);
	}

	@Override
	public String getUrlTitle(String languageId, boolean useDefault) {
		return model.getUrlTitle(languageId, useDefault);
	}

	@Override
	public String getUrlTitleMapAsXML() {
		return model.getUrlTitleMapAsXML();
	}

	/**
	 * Returns the uuid of this friendly url entry.
	 *
	 * @return the uuid of this friendly url entry
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public boolean isMain()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.isMain();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void setClassName(String className) {
		model.setClassName(className);
	}

	/**
	 * Sets the class name ID of this friendly url entry.
	 *
	 * @param classNameId the class name ID of this friendly url entry
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this friendly url entry.
	 *
	 * @param classPK the class pk of this friendly url entry
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this friendly url entry.
	 *
	 * @param companyId the company ID of this friendly url entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this friendly url entry.
	 *
	 * @param createDate the create date of this friendly url entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the default language ID of this friendly url entry.
	 *
	 * @param defaultLanguageId the default language ID of this friendly url entry
	 */
	@Override
	public void setDefaultLanguageId(String defaultLanguageId) {
		model.setDefaultLanguageId(defaultLanguageId);
	}

	/**
	 * Sets the friendly url entry ID of this friendly url entry.
	 *
	 * @param friendlyURLEntryId the friendly url entry ID of this friendly url entry
	 */
	@Override
	public void setFriendlyURLEntryId(long friendlyURLEntryId) {
		model.setFriendlyURLEntryId(friendlyURLEntryId);
	}

	/**
	 * Sets the group ID of this friendly url entry.
	 *
	 * @param groupId the group ID of this friendly url entry
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this friendly url entry.
	 *
	 * @param modifiedDate the modified date of this friendly url entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this friendly url entry.
	 *
	 * @param mvccVersion the mvcc version of this friendly url entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this friendly url entry.
	 *
	 * @param primaryKey the primary key of this friendly url entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this friendly url entry.
	 *
	 * @param uuid the uuid of this friendly url entry
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected FriendlyURLEntryWrapper wrap(FriendlyURLEntry friendlyURLEntry) {
		return new FriendlyURLEntryWrapper(friendlyURLEntry);
	}

}