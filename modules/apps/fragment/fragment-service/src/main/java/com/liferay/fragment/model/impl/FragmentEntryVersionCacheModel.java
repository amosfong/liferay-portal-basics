/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.model.impl;

import com.liferay.fragment.model.FragmentEntryVersion;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FragmentEntryVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FragmentEntryVersionCacheModel
	implements CacheModel<FragmentEntryVersion>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FragmentEntryVersionCacheModel)) {
			return false;
		}

		FragmentEntryVersionCacheModel fragmentEntryVersionCacheModel =
			(FragmentEntryVersionCacheModel)object;

		if (fragmentEntryVersionId ==
				fragmentEntryVersionCacheModel.fragmentEntryVersionId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fragmentEntryVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(59);

		sb.append("{fragmentEntryVersionId=");
		sb.append(fragmentEntryVersionId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", fragmentEntryId=");
		sb.append(fragmentEntryId);
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
		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);
		sb.append(", fragmentEntryKey=");
		sb.append(fragmentEntryKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", css=");
		sb.append(css);
		sb.append(", html=");
		sb.append(html);
		sb.append(", js=");
		sb.append(js);
		sb.append(", cacheable=");
		sb.append(cacheable);
		sb.append(", configuration=");
		sb.append(configuration);
		sb.append(", icon=");
		sb.append(icon);
		sb.append(", previewFileEntryId=");
		sb.append(previewFileEntryId);
		sb.append(", readOnly=");
		sb.append(readOnly);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typeOptions=");
		sb.append(typeOptions);
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
	public FragmentEntryVersion toEntityModel() {
		FragmentEntryVersionImpl fragmentEntryVersionImpl =
			new FragmentEntryVersionImpl();

		fragmentEntryVersionImpl.setFragmentEntryVersionId(
			fragmentEntryVersionId);
		fragmentEntryVersionImpl.setVersion(version);

		if (uuid == null) {
			fragmentEntryVersionImpl.setUuid("");
		}
		else {
			fragmentEntryVersionImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			fragmentEntryVersionImpl.setExternalReferenceCode("");
		}
		else {
			fragmentEntryVersionImpl.setExternalReferenceCode(
				externalReferenceCode);
		}

		fragmentEntryVersionImpl.setFragmentEntryId(fragmentEntryId);
		fragmentEntryVersionImpl.setGroupId(groupId);
		fragmentEntryVersionImpl.setCompanyId(companyId);
		fragmentEntryVersionImpl.setUserId(userId);

		if (userName == null) {
			fragmentEntryVersionImpl.setUserName("");
		}
		else {
			fragmentEntryVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			fragmentEntryVersionImpl.setCreateDate(null);
		}
		else {
			fragmentEntryVersionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fragmentEntryVersionImpl.setModifiedDate(null);
		}
		else {
			fragmentEntryVersionImpl.setModifiedDate(new Date(modifiedDate));
		}

		fragmentEntryVersionImpl.setFragmentCollectionId(fragmentCollectionId);

		if (fragmentEntryKey == null) {
			fragmentEntryVersionImpl.setFragmentEntryKey("");
		}
		else {
			fragmentEntryVersionImpl.setFragmentEntryKey(fragmentEntryKey);
		}

		if (name == null) {
			fragmentEntryVersionImpl.setName("");
		}
		else {
			fragmentEntryVersionImpl.setName(name);
		}

		if (css == null) {
			fragmentEntryVersionImpl.setCss("");
		}
		else {
			fragmentEntryVersionImpl.setCss(css);
		}

		if (html == null) {
			fragmentEntryVersionImpl.setHtml("");
		}
		else {
			fragmentEntryVersionImpl.setHtml(html);
		}

		if (js == null) {
			fragmentEntryVersionImpl.setJs("");
		}
		else {
			fragmentEntryVersionImpl.setJs(js);
		}

		fragmentEntryVersionImpl.setCacheable(cacheable);

		if (configuration == null) {
			fragmentEntryVersionImpl.setConfiguration("");
		}
		else {
			fragmentEntryVersionImpl.setConfiguration(configuration);
		}

		if (icon == null) {
			fragmentEntryVersionImpl.setIcon("");
		}
		else {
			fragmentEntryVersionImpl.setIcon(icon);
		}

		fragmentEntryVersionImpl.setPreviewFileEntryId(previewFileEntryId);
		fragmentEntryVersionImpl.setReadOnly(readOnly);
		fragmentEntryVersionImpl.setType(type);

		if (typeOptions == null) {
			fragmentEntryVersionImpl.setTypeOptions("");
		}
		else {
			fragmentEntryVersionImpl.setTypeOptions(typeOptions);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			fragmentEntryVersionImpl.setLastPublishDate(null);
		}
		else {
			fragmentEntryVersionImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		fragmentEntryVersionImpl.setStatus(status);
		fragmentEntryVersionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			fragmentEntryVersionImpl.setStatusByUserName("");
		}
		else {
			fragmentEntryVersionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			fragmentEntryVersionImpl.setStatusDate(null);
		}
		else {
			fragmentEntryVersionImpl.setStatusDate(new Date(statusDate));
		}

		fragmentEntryVersionImpl.resetOriginalValues();

		return fragmentEntryVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		fragmentEntryVersionId = objectInput.readLong();

		version = objectInput.readInt();
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		fragmentEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		fragmentCollectionId = objectInput.readLong();
		fragmentEntryKey = objectInput.readUTF();
		name = objectInput.readUTF();
		css = (String)objectInput.readObject();
		html = (String)objectInput.readObject();
		js = (String)objectInput.readObject();

		cacheable = objectInput.readBoolean();
		configuration = (String)objectInput.readObject();
		icon = objectInput.readUTF();

		previewFileEntryId = objectInput.readLong();

		readOnly = objectInput.readBoolean();

		type = objectInput.readInt();
		typeOptions = (String)objectInput.readObject();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(fragmentEntryVersionId);

		objectOutput.writeInt(version);

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

		objectOutput.writeLong(fragmentEntryId);

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

		objectOutput.writeLong(fragmentCollectionId);

		if (fragmentEntryKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fragmentEntryKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (css == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(css);
		}

		if (html == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(html);
		}

		if (js == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(js);
		}

		objectOutput.writeBoolean(cacheable);

		if (configuration == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(configuration);
		}

		if (icon == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(icon);
		}

		objectOutput.writeLong(previewFileEntryId);

		objectOutput.writeBoolean(readOnly);

		objectOutput.writeInt(type);

		if (typeOptions == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(typeOptions);
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

	public long fragmentEntryVersionId;
	public int version;
	public String uuid;
	public String externalReferenceCode;
	public long fragmentEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long fragmentCollectionId;
	public String fragmentEntryKey;
	public String name;
	public String css;
	public String html;
	public String js;
	public boolean cacheable;
	public String configuration;
	public String icon;
	public long previewFileEntryId;
	public boolean readOnly;
	public int type;
	public String typeOptions;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}