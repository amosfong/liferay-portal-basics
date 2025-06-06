/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LayoutSet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LayoutSetCacheModel
	implements CacheModel<LayoutSet>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LayoutSetCacheModel)) {
			return false;
		}

		LayoutSetCacheModel layoutSetCacheModel = (LayoutSetCacheModel)object;

		if ((layoutSetId == layoutSetCacheModel.layoutSetId) &&
			(mvccVersion == layoutSetCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, layoutSetId);

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
		StringBundler sb = new StringBundler(31);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", layoutSetId=");
		sb.append(layoutSetId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", privateLayout=");
		sb.append(privateLayout);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", themeId=");
		sb.append(themeId);
		sb.append(", colorSchemeId=");
		sb.append(colorSchemeId);
		sb.append(", faviconFileEntryId=");
		sb.append(faviconFileEntryId);
		sb.append(", css=");
		sb.append(css);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);
		sb.append(", layoutSetPrototypeLinkEnabled=");
		sb.append(layoutSetPrototypeLinkEnabled);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LayoutSet toEntityModel() {
		LayoutSetImpl layoutSetImpl = new LayoutSetImpl();

		layoutSetImpl.setMvccVersion(mvccVersion);
		layoutSetImpl.setLayoutSetId(layoutSetId);
		layoutSetImpl.setGroupId(groupId);
		layoutSetImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			layoutSetImpl.setCreateDate(null);
		}
		else {
			layoutSetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			layoutSetImpl.setModifiedDate(null);
		}
		else {
			layoutSetImpl.setModifiedDate(new Date(modifiedDate));
		}

		layoutSetImpl.setPrivateLayout(privateLayout);
		layoutSetImpl.setLogoId(logoId);

		if (themeId == null) {
			layoutSetImpl.setThemeId("");
		}
		else {
			layoutSetImpl.setThemeId(themeId);
		}

		if (colorSchemeId == null) {
			layoutSetImpl.setColorSchemeId("");
		}
		else {
			layoutSetImpl.setColorSchemeId(colorSchemeId);
		}

		layoutSetImpl.setFaviconFileEntryId(faviconFileEntryId);

		if (css == null) {
			layoutSetImpl.setCss("");
		}
		else {
			layoutSetImpl.setCss(css);
		}

		if (settings == null) {
			layoutSetImpl.setSettings("");
		}
		else {
			layoutSetImpl.setSettings(settings);
		}

		if (layoutSetPrototypeUuid == null) {
			layoutSetImpl.setLayoutSetPrototypeUuid("");
		}
		else {
			layoutSetImpl.setLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
		}

		layoutSetImpl.setLayoutSetPrototypeLinkEnabled(
			layoutSetPrototypeLinkEnabled);

		layoutSetImpl.resetOriginalValues();

		layoutSetImpl.setCompanyFallbackVirtualHostname(
			_companyFallbackVirtualHostname);

		layoutSetImpl.setVirtualHostnames(_virtualHostnames);

		return layoutSetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		layoutSetId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		privateLayout = objectInput.readBoolean();

		logoId = objectInput.readLong();
		themeId = objectInput.readUTF();
		colorSchemeId = objectInput.readUTF();

		faviconFileEntryId = objectInput.readLong();
		css = (String)objectInput.readObject();
		settings = (String)objectInput.readObject();
		layoutSetPrototypeUuid = objectInput.readUTF();

		layoutSetPrototypeLinkEnabled = objectInput.readBoolean();

		_companyFallbackVirtualHostname = (String)objectInput.readObject();
		_virtualHostnames = (java.util.TreeMap)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(layoutSetId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(privateLayout);

		objectOutput.writeLong(logoId);

		if (themeId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(themeId);
		}

		if (colorSchemeId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(colorSchemeId);
		}

		objectOutput.writeLong(faviconFileEntryId);

		if (css == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(css);
		}

		if (settings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(settings);
		}

		if (layoutSetPrototypeUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(layoutSetPrototypeUuid);
		}

		objectOutput.writeBoolean(layoutSetPrototypeLinkEnabled);

		objectOutput.writeObject(_companyFallbackVirtualHostname);
		objectOutput.writeObject(_virtualHostnames);
	}

	public long mvccVersion;
	public long layoutSetId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public boolean privateLayout;
	public long logoId;
	public String themeId;
	public String colorSchemeId;
	public long faviconFileEntryId;
	public String css;
	public String settings;
	public String layoutSetPrototypeUuid;
	public boolean layoutSetPrototypeLinkEnabled;
	public String _companyFallbackVirtualHostname;
	public java.util.TreeMap _virtualHostnames;

}