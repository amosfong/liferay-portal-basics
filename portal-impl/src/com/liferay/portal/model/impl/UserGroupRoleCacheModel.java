/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.UserGroupRole;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserGroupRole in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserGroupRoleCacheModel
	implements CacheModel<UserGroupRole>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserGroupRoleCacheModel)) {
			return false;
		}

		UserGroupRoleCacheModel userGroupRoleCacheModel =
			(UserGroupRoleCacheModel)object;

		if ((userGroupRoleId == userGroupRoleCacheModel.userGroupRoleId) &&
			(mvccVersion == userGroupRoleCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, userGroupRoleId);

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
		sb.append(", userGroupRoleId=");
		sb.append(userGroupRoleId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserGroupRole toEntityModel() {
		UserGroupRoleImpl userGroupRoleImpl = new UserGroupRoleImpl();

		userGroupRoleImpl.setMvccVersion(mvccVersion);
		userGroupRoleImpl.setUserGroupRoleId(userGroupRoleId);
		userGroupRoleImpl.setCompanyId(companyId);
		userGroupRoleImpl.setUserId(userId);
		userGroupRoleImpl.setGroupId(groupId);
		userGroupRoleImpl.setRoleId(roleId);

		userGroupRoleImpl.resetOriginalValues();

		return userGroupRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		userGroupRoleId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();

		groupId = objectInput.readLong();

		roleId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(userGroupRoleId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(roleId);
	}

	public long mvccVersion;
	public long userGroupRoleId;
	public long companyId;
	public long userId;
	public long groupId;
	public long roleId;

}