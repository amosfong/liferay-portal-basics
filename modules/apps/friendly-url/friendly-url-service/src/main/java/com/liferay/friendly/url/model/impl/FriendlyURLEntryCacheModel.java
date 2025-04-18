/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.model.impl;

import com.liferay.friendly.url.model.FriendlyURLEntry;
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
 * The cache model class for representing FriendlyURLEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FriendlyURLEntryCacheModel
	implements CacheModel<FriendlyURLEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FriendlyURLEntryCacheModel)) {
			return false;
		}

		FriendlyURLEntryCacheModel friendlyURLEntryCacheModel =
			(FriendlyURLEntryCacheModel)object;

		if ((friendlyURLEntryId ==
				friendlyURLEntryCacheModel.friendlyURLEntryId) &&
			(mvccVersion == friendlyURLEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, friendlyURLEntryId);

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
		StringBundler sb = new StringBundler(21);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", defaultLanguageId=");
		sb.append(defaultLanguageId);
		sb.append(", friendlyURLEntryId=");
		sb.append(friendlyURLEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FriendlyURLEntry toEntityModel() {
		FriendlyURLEntryImpl friendlyURLEntryImpl = new FriendlyURLEntryImpl();

		friendlyURLEntryImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			friendlyURLEntryImpl.setUuid("");
		}
		else {
			friendlyURLEntryImpl.setUuid(uuid);
		}

		if (defaultLanguageId == null) {
			friendlyURLEntryImpl.setDefaultLanguageId("");
		}
		else {
			friendlyURLEntryImpl.setDefaultLanguageId(defaultLanguageId);
		}

		friendlyURLEntryImpl.setFriendlyURLEntryId(friendlyURLEntryId);
		friendlyURLEntryImpl.setGroupId(groupId);
		friendlyURLEntryImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			friendlyURLEntryImpl.setCreateDate(null);
		}
		else {
			friendlyURLEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			friendlyURLEntryImpl.setModifiedDate(null);
		}
		else {
			friendlyURLEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		friendlyURLEntryImpl.setClassNameId(classNameId);
		friendlyURLEntryImpl.setClassPK(classPK);

		friendlyURLEntryImpl.resetOriginalValues();

		return friendlyURLEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();
		defaultLanguageId = objectInput.readUTF();

		friendlyURLEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
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

		if (defaultLanguageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(defaultLanguageId);
		}

		objectOutput.writeLong(friendlyURLEntryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);
	}

	public long mvccVersion;
	public String uuid;
	public String defaultLanguageId;
	public long friendlyURLEntryId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;

}