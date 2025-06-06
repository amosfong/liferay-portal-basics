/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DDMFormInstanceRecordVersion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceRecordVersion
 * @generated
 */
public class DDMFormInstanceRecordVersionWrapper
	extends BaseModelWrapper<DDMFormInstanceRecordVersion>
	implements DDMFormInstanceRecordVersion,
			   ModelWrapper<DDMFormInstanceRecordVersion> {

	public DDMFormInstanceRecordVersionWrapper(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		super(ddmFormInstanceRecordVersion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put(
			"formInstanceRecordVersionId", getFormInstanceRecordVersionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("formInstanceId", getFormInstanceId());
		attributes.put("formInstanceVersion", getFormInstanceVersion());
		attributes.put("formInstanceRecordId", getFormInstanceRecordId());
		attributes.put("version", getVersion());
		attributes.put("storageId", getStorageId());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long formInstanceRecordVersionId = (Long)attributes.get(
			"formInstanceRecordVersionId");

		if (formInstanceRecordVersionId != null) {
			setFormInstanceRecordVersionId(formInstanceRecordVersionId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long formInstanceId = (Long)attributes.get("formInstanceId");

		if (formInstanceId != null) {
			setFormInstanceId(formInstanceId);
		}

		String formInstanceVersion = (String)attributes.get(
			"formInstanceVersion");

		if (formInstanceVersion != null) {
			setFormInstanceVersion(formInstanceVersion);
		}

		Long formInstanceRecordId = (Long)attributes.get(
			"formInstanceRecordId");

		if (formInstanceRecordId != null) {
			setFormInstanceRecordId(formInstanceRecordId);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Long storageId = (Long)attributes.get("storageId");

		if (storageId != null) {
			setStorageId(storageId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	@Override
	public DDMFormInstanceRecordVersion cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this ddm form instance record version.
	 *
	 * @return the company ID of this ddm form instance record version
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this ddm form instance record version.
	 *
	 * @return the create date of this ddm form instance record version
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public DDMForm getDDMForm()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getDDMForm();
	}

	@Override
	public com.liferay.dynamic.data.mapping.storage.DDMFormValues
			getDDMFormValues()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getDDMFormValues();
	}

	@Override
	public DDMFormInstance getFormInstance()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getFormInstance();
	}

	/**
	 * Returns the form instance ID of this ddm form instance record version.
	 *
	 * @return the form instance ID of this ddm form instance record version
	 */
	@Override
	public long getFormInstanceId() {
		return model.getFormInstanceId();
	}

	@Override
	public DDMFormInstanceRecord getFormInstanceRecord()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getFormInstanceRecord();
	}

	/**
	 * Returns the form instance record ID of this ddm form instance record version.
	 *
	 * @return the form instance record ID of this ddm form instance record version
	 */
	@Override
	public long getFormInstanceRecordId() {
		return model.getFormInstanceRecordId();
	}

	/**
	 * Returns the form instance record version ID of this ddm form instance record version.
	 *
	 * @return the form instance record version ID of this ddm form instance record version
	 */
	@Override
	public long getFormInstanceRecordVersionId() {
		return model.getFormInstanceRecordVersionId();
	}

	/**
	 * Returns the form instance version of this ddm form instance record version.
	 *
	 * @return the form instance version of this ddm form instance record version
	 */
	@Override
	public String getFormInstanceVersion() {
		return model.getFormInstanceVersion();
	}

	/**
	 * Returns the group ID of this ddm form instance record version.
	 *
	 * @return the group ID of this ddm form instance record version
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the mvcc version of this ddm form instance record version.
	 *
	 * @return the mvcc version of this ddm form instance record version
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this ddm form instance record version.
	 *
	 * @return the primary key of this ddm form instance record version
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this ddm form instance record version.
	 *
	 * @return the status of this ddm form instance record version
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this ddm form instance record version.
	 *
	 * @return the status by user ID of this ddm form instance record version
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this ddm form instance record version.
	 *
	 * @return the status by user name of this ddm form instance record version
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this ddm form instance record version.
	 *
	 * @return the status by user uuid of this ddm form instance record version
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this ddm form instance record version.
	 *
	 * @return the status date of this ddm form instance record version
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Returns the storage ID of this ddm form instance record version.
	 *
	 * @return the storage ID of this ddm form instance record version
	 */
	@Override
	public long getStorageId() {
		return model.getStorageId();
	}

	/**
	 * Returns the user ID of this ddm form instance record version.
	 *
	 * @return the user ID of this ddm form instance record version
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this ddm form instance record version.
	 *
	 * @return the user name of this ddm form instance record version
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this ddm form instance record version.
	 *
	 * @return the user uuid of this ddm form instance record version
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the version of this ddm form instance record version.
	 *
	 * @return the version of this ddm form instance record version
	 */
	@Override
	public String getVersion() {
		return model.getVersion();
	}

	/**
	 * Returns <code>true</code> if this ddm form instance record version is approved.
	 *
	 * @return <code>true</code> if this ddm form instance record version is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this ddm form instance record version is denied.
	 *
	 * @return <code>true</code> if this ddm form instance record version is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this ddm form instance record version is a draft.
	 *
	 * @return <code>true</code> if this ddm form instance record version is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this ddm form instance record version is expired.
	 *
	 * @return <code>true</code> if this ddm form instance record version is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this ddm form instance record version is inactive.
	 *
	 * @return <code>true</code> if this ddm form instance record version is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this ddm form instance record version is incomplete.
	 *
	 * @return <code>true</code> if this ddm form instance record version is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this ddm form instance record version is pending.
	 *
	 * @return <code>true</code> if this ddm form instance record version is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this ddm form instance record version is scheduled.
	 *
	 * @return <code>true</code> if this ddm form instance record version is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this ddm form instance record version.
	 *
	 * @param companyId the company ID of this ddm form instance record version
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this ddm form instance record version.
	 *
	 * @param createDate the create date of this ddm form instance record version
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the form instance ID of this ddm form instance record version.
	 *
	 * @param formInstanceId the form instance ID of this ddm form instance record version
	 */
	@Override
	public void setFormInstanceId(long formInstanceId) {
		model.setFormInstanceId(formInstanceId);
	}

	/**
	 * Sets the form instance record ID of this ddm form instance record version.
	 *
	 * @param formInstanceRecordId the form instance record ID of this ddm form instance record version
	 */
	@Override
	public void setFormInstanceRecordId(long formInstanceRecordId) {
		model.setFormInstanceRecordId(formInstanceRecordId);
	}

	/**
	 * Sets the form instance record version ID of this ddm form instance record version.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID of this ddm form instance record version
	 */
	@Override
	public void setFormInstanceRecordVersionId(
		long formInstanceRecordVersionId) {

		model.setFormInstanceRecordVersionId(formInstanceRecordVersionId);
	}

	/**
	 * Sets the form instance version of this ddm form instance record version.
	 *
	 * @param formInstanceVersion the form instance version of this ddm form instance record version
	 */
	@Override
	public void setFormInstanceVersion(String formInstanceVersion) {
		model.setFormInstanceVersion(formInstanceVersion);
	}

	/**
	 * Sets the group ID of this ddm form instance record version.
	 *
	 * @param groupId the group ID of this ddm form instance record version
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the mvcc version of this ddm form instance record version.
	 *
	 * @param mvccVersion the mvcc version of this ddm form instance record version
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this ddm form instance record version.
	 *
	 * @param primaryKey the primary key of this ddm form instance record version
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this ddm form instance record version.
	 *
	 * @param status the status of this ddm form instance record version
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this ddm form instance record version.
	 *
	 * @param statusByUserId the status by user ID of this ddm form instance record version
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this ddm form instance record version.
	 *
	 * @param statusByUserName the status by user name of this ddm form instance record version
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this ddm form instance record version.
	 *
	 * @param statusByUserUuid the status by user uuid of this ddm form instance record version
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this ddm form instance record version.
	 *
	 * @param statusDate the status date of this ddm form instance record version
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the storage ID of this ddm form instance record version.
	 *
	 * @param storageId the storage ID of this ddm form instance record version
	 */
	@Override
	public void setStorageId(long storageId) {
		model.setStorageId(storageId);
	}

	/**
	 * Sets the user ID of this ddm form instance record version.
	 *
	 * @param userId the user ID of this ddm form instance record version
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this ddm form instance record version.
	 *
	 * @param userName the user name of this ddm form instance record version
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this ddm form instance record version.
	 *
	 * @param userUuid the user uuid of this ddm form instance record version
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the version of this ddm form instance record version.
	 *
	 * @param version the version of this ddm form instance record version
	 */
	@Override
	public void setVersion(String version) {
		model.setVersion(version);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DDMFormInstanceRecordVersionWrapper wrap(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		return new DDMFormInstanceRecordVersionWrapper(
			ddmFormInstanceRecordVersion);
	}

}