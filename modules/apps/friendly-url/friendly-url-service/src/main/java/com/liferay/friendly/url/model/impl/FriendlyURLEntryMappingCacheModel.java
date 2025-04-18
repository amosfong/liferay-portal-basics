/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.model.impl;

import com.liferay.friendly.url.model.FriendlyURLEntryMapping;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FriendlyURLEntryMapping in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FriendlyURLEntryMappingCacheModel
	implements CacheModel<FriendlyURLEntryMapping>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FriendlyURLEntryMappingCacheModel)) {
			return false;
		}

		FriendlyURLEntryMappingCacheModel friendlyURLEntryMappingCacheModel =
			(FriendlyURLEntryMappingCacheModel)object;

		if ((friendlyURLEntryMappingId ==
				friendlyURLEntryMappingCacheModel.friendlyURLEntryMappingId) &&
			(mvccVersion == friendlyURLEntryMappingCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, friendlyURLEntryMappingId);

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
		StringBundler sb = new StringBundler(13);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", friendlyURLEntryMappingId=");
		sb.append(friendlyURLEntryMappingId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", friendlyURLEntryId=");
		sb.append(friendlyURLEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FriendlyURLEntryMapping toEntityModel() {
		FriendlyURLEntryMappingImpl friendlyURLEntryMappingImpl =
			new FriendlyURLEntryMappingImpl();

		friendlyURLEntryMappingImpl.setMvccVersion(mvccVersion);
		friendlyURLEntryMappingImpl.setFriendlyURLEntryMappingId(
			friendlyURLEntryMappingId);
		friendlyURLEntryMappingImpl.setCompanyId(companyId);
		friendlyURLEntryMappingImpl.setClassNameId(classNameId);
		friendlyURLEntryMappingImpl.setClassPK(classPK);
		friendlyURLEntryMappingImpl.setFriendlyURLEntryId(friendlyURLEntryId);

		friendlyURLEntryMappingImpl.resetOriginalValues();

		return friendlyURLEntryMappingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		friendlyURLEntryMappingId = objectInput.readLong();

		companyId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		friendlyURLEntryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(friendlyURLEntryMappingId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(friendlyURLEntryId);
	}

	public long mvccVersion;
	public long friendlyURLEntryMappingId;
	public long companyId;
	public long classNameId;
	public long classPK;
	public long friendlyURLEntryId;

}