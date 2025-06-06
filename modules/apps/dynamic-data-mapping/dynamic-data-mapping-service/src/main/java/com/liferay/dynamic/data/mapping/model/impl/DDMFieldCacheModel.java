/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMField;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DDMField in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DDMFieldCacheModel
	implements CacheModel<DDMField>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFieldCacheModel)) {
			return false;
		}

		DDMFieldCacheModel ddmFieldCacheModel = (DDMFieldCacheModel)object;

		if ((fieldId == ddmFieldCacheModel.fieldId) &&
			(mvccVersion == ddmFieldCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, fieldId);

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
		StringBundler sb = new StringBundler(23);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", fieldId=");
		sb.append(fieldId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", parentFieldId=");
		sb.append(parentFieldId);
		sb.append(", storageId=");
		sb.append(storageId);
		sb.append(", structureVersionId=");
		sb.append(structureVersionId);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append(", fieldType=");
		sb.append(fieldType);
		sb.append(", instanceId=");
		sb.append(instanceId);
		sb.append(", localizable=");
		sb.append(localizable);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDMField toEntityModel() {
		DDMFieldImpl ddmFieldImpl = new DDMFieldImpl();

		ddmFieldImpl.setMvccVersion(mvccVersion);
		ddmFieldImpl.setFieldId(fieldId);
		ddmFieldImpl.setCompanyId(companyId);
		ddmFieldImpl.setParentFieldId(parentFieldId);
		ddmFieldImpl.setStorageId(storageId);
		ddmFieldImpl.setStructureVersionId(structureVersionId);

		if (fieldName == null) {
			ddmFieldImpl.setFieldName("");
		}
		else {
			ddmFieldImpl.setFieldName(fieldName);
		}

		if (fieldType == null) {
			ddmFieldImpl.setFieldType("");
		}
		else {
			ddmFieldImpl.setFieldType(fieldType);
		}

		if (instanceId == null) {
			ddmFieldImpl.setInstanceId("");
		}
		else {
			ddmFieldImpl.setInstanceId(instanceId);
		}

		ddmFieldImpl.setLocalizable(localizable);
		ddmFieldImpl.setPriority(priority);

		ddmFieldImpl.resetOriginalValues();

		return ddmFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		fieldId = objectInput.readLong();

		companyId = objectInput.readLong();

		parentFieldId = objectInput.readLong();

		storageId = objectInput.readLong();

		structureVersionId = objectInput.readLong();
		fieldName = objectInput.readUTF();
		fieldType = objectInput.readUTF();
		instanceId = objectInput.readUTF();

		localizable = objectInput.readBoolean();

		priority = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(fieldId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(parentFieldId);

		objectOutput.writeLong(storageId);

		objectOutput.writeLong(structureVersionId);

		if (fieldName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fieldName);
		}

		if (fieldType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fieldType);
		}

		if (instanceId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(instanceId);
		}

		objectOutput.writeBoolean(localizable);

		objectOutput.writeInt(priority);
	}

	public long mvccVersion;
	public long fieldId;
	public long companyId;
	public long parentFieldId;
	public long storageId;
	public long structureVersionId;
	public String fieldName;
	public String fieldType;
	public String instanceId;
	public boolean localizable;
	public int priority;

}