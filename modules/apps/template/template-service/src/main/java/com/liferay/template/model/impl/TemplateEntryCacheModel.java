/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.template.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.template.model.TemplateEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TemplateEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TemplateEntryCacheModel
	implements CacheModel<TemplateEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TemplateEntryCacheModel)) {
			return false;
		}

		TemplateEntryCacheModel templateEntryCacheModel =
			(TemplateEntryCacheModel)object;

		if ((templateEntryId == templateEntryCacheModel.templateEntryId) &&
			(mvccVersion == templateEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, templateEntryId);

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
		sb.append(", templateEntryId=");
		sb.append(templateEntryId);
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
		sb.append(", ddmTemplateId=");
		sb.append(ddmTemplateId);
		sb.append(", infoItemClassName=");
		sb.append(infoItemClassName);
		sb.append(", infoItemFormVariationKey=");
		sb.append(infoItemFormVariationKey);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TemplateEntry toEntityModel() {
		TemplateEntryImpl templateEntryImpl = new TemplateEntryImpl();

		templateEntryImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			templateEntryImpl.setUuid("");
		}
		else {
			templateEntryImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			templateEntryImpl.setExternalReferenceCode("");
		}
		else {
			templateEntryImpl.setExternalReferenceCode(externalReferenceCode);
		}

		templateEntryImpl.setTemplateEntryId(templateEntryId);
		templateEntryImpl.setGroupId(groupId);
		templateEntryImpl.setCompanyId(companyId);
		templateEntryImpl.setUserId(userId);

		if (userName == null) {
			templateEntryImpl.setUserName("");
		}
		else {
			templateEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			templateEntryImpl.setCreateDate(null);
		}
		else {
			templateEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			templateEntryImpl.setModifiedDate(null);
		}
		else {
			templateEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		templateEntryImpl.setDDMTemplateId(ddmTemplateId);

		if (infoItemClassName == null) {
			templateEntryImpl.setInfoItemClassName("");
		}
		else {
			templateEntryImpl.setInfoItemClassName(infoItemClassName);
		}

		if (infoItemFormVariationKey == null) {
			templateEntryImpl.setInfoItemFormVariationKey("");
		}
		else {
			templateEntryImpl.setInfoItemFormVariationKey(
				infoItemFormVariationKey);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			templateEntryImpl.setLastPublishDate(null);
		}
		else {
			templateEntryImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		templateEntryImpl.resetOriginalValues();

		return templateEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		templateEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		ddmTemplateId = objectInput.readLong();
		infoItemClassName = objectInput.readUTF();
		infoItemFormVariationKey = objectInput.readUTF();
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

		objectOutput.writeLong(templateEntryId);

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

		objectOutput.writeLong(ddmTemplateId);

		if (infoItemClassName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(infoItemClassName);
		}

		if (infoItemFormVariationKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(infoItemFormVariationKey);
		}

		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public String uuid;
	public String externalReferenceCode;
	public long templateEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long ddmTemplateId;
	public String infoItemClassName;
	public String infoItemFormVariationKey;
	public long lastPublishDate;

}