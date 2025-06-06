/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMContent;
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
 * The cache model class for representing DDMContent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DDMContentCacheModel
	implements CacheModel<DDMContent>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMContentCacheModel)) {
			return false;
		}

		DDMContentCacheModel ddmContentCacheModel =
			(DDMContentCacheModel)object;

		if ((contentId == ddmContentCacheModel.contentId) &&
			(mvccVersion == ddmContentCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, contentId);

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
		StringBundler sb = new StringBundler(25);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", contentId=");
		sb.append(contentId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", data=");
		sb.append(data);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDMContent toEntityModel() {
		DDMContentImpl ddmContentImpl = new DDMContentImpl();

		ddmContentImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			ddmContentImpl.setUuid("");
		}
		else {
			ddmContentImpl.setUuid(uuid);
		}

		ddmContentImpl.setContentId(contentId);
		ddmContentImpl.setGroupId(groupId);
		ddmContentImpl.setCompanyId(companyId);
		ddmContentImpl.setUserId(userId);

		if (userName == null) {
			ddmContentImpl.setUserName("");
		}
		else {
			ddmContentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ddmContentImpl.setCreateDate(null);
		}
		else {
			ddmContentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ddmContentImpl.setModifiedDate(null);
		}
		else {
			ddmContentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			ddmContentImpl.setName("");
		}
		else {
			ddmContentImpl.setName(name);
		}

		if (description == null) {
			ddmContentImpl.setDescription("");
		}
		else {
			ddmContentImpl.setDescription(description);
		}

		if (data == null) {
			ddmContentImpl.setData("");
		}
		else {
			ddmContentImpl.setData(data);
		}

		ddmContentImpl.resetOriginalValues();

		return ddmContentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		contentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		data = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(contentId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (data == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(data);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long contentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String data;

}