/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model.impl;

import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KBFolder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KBFolderCacheModel
	implements CacheModel<KBFolder>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KBFolderCacheModel)) {
			return false;
		}

		KBFolderCacheModel kbFolderCacheModel = (KBFolderCacheModel)object;

		if ((kbFolderId == kbFolderCacheModel.kbFolderId) &&
			(mvccVersion == kbFolderCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, kbFolderId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", ctCollectionId=");
		sb.append(ctCollectionId);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", kbFolderId=");
		sb.append(kbFolderId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", parentKBFolderId=");
		sb.append(parentKBFolderId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", urlTitle=");
		sb.append(urlTitle);
		sb.append(", description=");
		sb.append(description);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KBFolder toEntityModel() {
		KBFolderImpl kbFolderImpl = new KBFolderImpl();

		kbFolderImpl.setMvccVersion(mvccVersion);
		kbFolderImpl.setCtCollectionId(ctCollectionId);

		if (uuid == null) {
			kbFolderImpl.setUuid("");
		}
		else {
			kbFolderImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			kbFolderImpl.setExternalReferenceCode("");
		}
		else {
			kbFolderImpl.setExternalReferenceCode(externalReferenceCode);
		}

		kbFolderImpl.setKbFolderId(kbFolderId);
		kbFolderImpl.setGroupId(groupId);
		kbFolderImpl.setCompanyId(companyId);
		kbFolderImpl.setUserId(userId);

		if (userName == null) {
			kbFolderImpl.setUserName("");
		}
		else {
			kbFolderImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kbFolderImpl.setCreateDate(null);
		}
		else {
			kbFolderImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kbFolderImpl.setModifiedDate(null);
		}
		else {
			kbFolderImpl.setModifiedDate(new Date(modifiedDate));
		}

		kbFolderImpl.setParentKBFolderId(parentKBFolderId);

		if (name == null) {
			kbFolderImpl.setName("");
		}
		else {
			kbFolderImpl.setName(name);
		}

		if (urlTitle == null) {
			kbFolderImpl.setUrlTitle("");
		}
		else {
			kbFolderImpl.setUrlTitle(urlTitle);
		}

		if (description == null) {
			kbFolderImpl.setDescription("");
		}
		else {
			kbFolderImpl.setDescription(description);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			kbFolderImpl.setLastPublishDate(null);
		}
		else {
			kbFolderImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		kbFolderImpl.setStatus(status);
		kbFolderImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			kbFolderImpl.setStatusByUserName("");
		}
		else {
			kbFolderImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			kbFolderImpl.setStatusDate(null);
		}
		else {
			kbFolderImpl.setStatusDate(new Date(statusDate));
		}

		kbFolderImpl.resetOriginalValues();

		return kbFolderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		ctCollectionId = objectInput.readLong();
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		kbFolderId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		parentKBFolderId = objectInput.readLong();
		name = objectInput.readUTF();
		urlTitle = objectInput.readUTF();
		description = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(ctCollectionId);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(kbFolderId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(parentKBFolderId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (urlTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(urlTitle);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public long mvccVersion;
	public long ctCollectionId;
	public String uuid;
	public String externalReferenceCode;
	public long kbFolderId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentKBFolderId;
	public String name;
	public String urlTitle;
	public String description;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}