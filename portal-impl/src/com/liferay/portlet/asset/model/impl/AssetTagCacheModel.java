/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.model.impl;

import com.liferay.asset.kernel.model.AssetTag;
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
 * The cache model class for representing AssetTag in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetTagCacheModel
	implements CacheModel<AssetTag>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AssetTagCacheModel)) {
			return false;
		}

		AssetTagCacheModel assetTagCacheModel = (AssetTagCacheModel)object;

		if ((tagId == assetTagCacheModel.tagId) &&
			(mvccVersion == assetTagCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, tagId);

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
		StringBundler sb = new StringBundler(29);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", ctCollectionId=");
		sb.append(ctCollectionId);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", tagId=");
		sb.append(tagId);
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
		sb.append(", assetCount=");
		sb.append(assetCount);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AssetTag toEntityModel() {
		AssetTagImpl assetTagImpl = new AssetTagImpl();

		assetTagImpl.setMvccVersion(mvccVersion);
		assetTagImpl.setCtCollectionId(ctCollectionId);

		if (uuid == null) {
			assetTagImpl.setUuid("");
		}
		else {
			assetTagImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			assetTagImpl.setExternalReferenceCode("");
		}
		else {
			assetTagImpl.setExternalReferenceCode(externalReferenceCode);
		}

		assetTagImpl.setTagId(tagId);
		assetTagImpl.setGroupId(groupId);
		assetTagImpl.setCompanyId(companyId);
		assetTagImpl.setUserId(userId);

		if (userName == null) {
			assetTagImpl.setUserName("");
		}
		else {
			assetTagImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetTagImpl.setCreateDate(null);
		}
		else {
			assetTagImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetTagImpl.setModifiedDate(null);
		}
		else {
			assetTagImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			assetTagImpl.setName("");
		}
		else {
			assetTagImpl.setName(name);
		}

		assetTagImpl.setAssetCount(assetCount);

		if (lastPublishDate == Long.MIN_VALUE) {
			assetTagImpl.setLastPublishDate(null);
		}
		else {
			assetTagImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		assetTagImpl.resetOriginalValues();

		return assetTagImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		ctCollectionId = objectInput.readLong();
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		tagId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();

		assetCount = objectInput.readInt();
		lastPublishDate = objectInput.readLong();
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

		objectOutput.writeLong(tagId);

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

		objectOutput.writeInt(assetCount);
		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public long ctCollectionId;
	public String uuid;
	public String externalReferenceCode;
	public long tagId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public int assetCount;
	public long lastPublishDate;

}