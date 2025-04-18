/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMFieldAttribute;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DDMFieldAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DDMFieldAttributeCacheModel
	implements CacheModel<DDMFieldAttribute>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFieldAttributeCacheModel)) {
			return false;
		}

		DDMFieldAttributeCacheModel ddmFieldAttributeCacheModel =
			(DDMFieldAttributeCacheModel)object;

		if ((fieldAttributeId ==
				ddmFieldAttributeCacheModel.fieldAttributeId) &&
			(mvccVersion == ddmFieldAttributeCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, fieldAttributeId);

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
		StringBundler sb = new StringBundler(19);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", fieldAttributeId=");
		sb.append(fieldAttributeId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", fieldId=");
		sb.append(fieldId);
		sb.append(", storageId=");
		sb.append(storageId);
		sb.append(", attributeName=");
		sb.append(attributeName);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", largeAttributeValue=");
		sb.append(largeAttributeValue);
		sb.append(", smallAttributeValue=");
		sb.append(smallAttributeValue);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDMFieldAttribute toEntityModel() {
		DDMFieldAttributeImpl ddmFieldAttributeImpl =
			new DDMFieldAttributeImpl();

		ddmFieldAttributeImpl.setMvccVersion(mvccVersion);
		ddmFieldAttributeImpl.setFieldAttributeId(fieldAttributeId);
		ddmFieldAttributeImpl.setCompanyId(companyId);
		ddmFieldAttributeImpl.setFieldId(fieldId);
		ddmFieldAttributeImpl.setStorageId(storageId);

		if (attributeName == null) {
			ddmFieldAttributeImpl.setAttributeName("");
		}
		else {
			ddmFieldAttributeImpl.setAttributeName(attributeName);
		}

		if (languageId == null) {
			ddmFieldAttributeImpl.setLanguageId("");
		}
		else {
			ddmFieldAttributeImpl.setLanguageId(languageId);
		}

		if (largeAttributeValue == null) {
			ddmFieldAttributeImpl.setLargeAttributeValue("");
		}
		else {
			ddmFieldAttributeImpl.setLargeAttributeValue(largeAttributeValue);
		}

		if (smallAttributeValue == null) {
			ddmFieldAttributeImpl.setSmallAttributeValue("");
		}
		else {
			ddmFieldAttributeImpl.setSmallAttributeValue(smallAttributeValue);
		}

		ddmFieldAttributeImpl.resetOriginalValues();

		return ddmFieldAttributeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		fieldAttributeId = objectInput.readLong();

		companyId = objectInput.readLong();

		fieldId = objectInput.readLong();

		storageId = objectInput.readLong();
		attributeName = objectInput.readUTF();
		languageId = objectInput.readUTF();
		largeAttributeValue = (String)objectInput.readObject();
		smallAttributeValue = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(fieldAttributeId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(fieldId);

		objectOutput.writeLong(storageId);

		if (attributeName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(attributeName);
		}

		if (languageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		if (largeAttributeValue == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(largeAttributeValue);
		}

		if (smallAttributeValue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(smallAttributeValue);
		}
	}

	public long mvccVersion;
	public long fieldAttributeId;
	public long companyId;
	public long fieldId;
	public long storageId;
	public String attributeName;
	public String languageId;
	public String largeAttributeValue;
	public String smallAttributeValue;

}