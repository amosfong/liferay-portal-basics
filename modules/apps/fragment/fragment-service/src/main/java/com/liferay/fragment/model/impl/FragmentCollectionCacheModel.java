/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.model.impl;

import com.liferay.fragment.model.FragmentCollection;
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
 * The cache model class for representing FragmentCollection in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FragmentCollectionCacheModel
	implements CacheModel<FragmentCollection>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FragmentCollectionCacheModel)) {
			return false;
		}

		FragmentCollectionCacheModel fragmentCollectionCacheModel =
			(FragmentCollectionCacheModel)object;

		if ((fragmentCollectionId ==
				fragmentCollectionCacheModel.fragmentCollectionId) &&
			(mvccVersion == fragmentCollectionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, fragmentCollectionId);

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
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);
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
		sb.append(", fragmentCollectionKey=");
		sb.append(fragmentCollectionKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FragmentCollection toEntityModel() {
		FragmentCollectionImpl fragmentCollectionImpl =
			new FragmentCollectionImpl();

		fragmentCollectionImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			fragmentCollectionImpl.setUuid("");
		}
		else {
			fragmentCollectionImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			fragmentCollectionImpl.setExternalReferenceCode("");
		}
		else {
			fragmentCollectionImpl.setExternalReferenceCode(
				externalReferenceCode);
		}

		fragmentCollectionImpl.setFragmentCollectionId(fragmentCollectionId);
		fragmentCollectionImpl.setGroupId(groupId);
		fragmentCollectionImpl.setCompanyId(companyId);
		fragmentCollectionImpl.setUserId(userId);

		if (userName == null) {
			fragmentCollectionImpl.setUserName("");
		}
		else {
			fragmentCollectionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			fragmentCollectionImpl.setCreateDate(null);
		}
		else {
			fragmentCollectionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fragmentCollectionImpl.setModifiedDate(null);
		}
		else {
			fragmentCollectionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (fragmentCollectionKey == null) {
			fragmentCollectionImpl.setFragmentCollectionKey("");
		}
		else {
			fragmentCollectionImpl.setFragmentCollectionKey(
				fragmentCollectionKey);
		}

		if (name == null) {
			fragmentCollectionImpl.setName("");
		}
		else {
			fragmentCollectionImpl.setName(name);
		}

		if (description == null) {
			fragmentCollectionImpl.setDescription("");
		}
		else {
			fragmentCollectionImpl.setDescription(description);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			fragmentCollectionImpl.setLastPublishDate(null);
		}
		else {
			fragmentCollectionImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		fragmentCollectionImpl.resetOriginalValues();

		return fragmentCollectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		fragmentCollectionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fragmentCollectionKey = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();
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

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(fragmentCollectionId);

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

		if (fragmentCollectionKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fragmentCollectionKey);
		}

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

		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public String uuid;
	public String externalReferenceCode;
	public long fragmentCollectionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String fragmentCollectionKey;
	public String name;
	public String description;
	public long lastPublishDate;

}