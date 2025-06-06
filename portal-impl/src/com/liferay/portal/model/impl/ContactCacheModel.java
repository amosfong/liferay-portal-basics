/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Contact in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactCacheModel
	implements CacheModel<Contact>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactCacheModel)) {
			return false;
		}

		ContactCacheModel contactCacheModel = (ContactCacheModel)object;

		if ((contactId == contactCacheModel.contactId) &&
			(mvccVersion == contactCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, contactId);

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
		StringBundler sb = new StringBundler(57);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", contactId=");
		sb.append(contactId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", parentContactId=");
		sb.append(parentContactId);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", middleName=");
		sb.append(middleName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", prefixListTypeId=");
		sb.append(prefixListTypeId);
		sb.append(", suffixListTypeId=");
		sb.append(suffixListTypeId);
		sb.append(", male=");
		sb.append(male);
		sb.append(", birthday=");
		sb.append(birthday);
		sb.append(", smsSn=");
		sb.append(smsSn);
		sb.append(", facebookSn=");
		sb.append(facebookSn);
		sb.append(", jabberSn=");
		sb.append(jabberSn);
		sb.append(", skypeSn=");
		sb.append(skypeSn);
		sb.append(", twitterSn=");
		sb.append(twitterSn);
		sb.append(", employeeStatusId=");
		sb.append(employeeStatusId);
		sb.append(", employeeNumber=");
		sb.append(employeeNumber);
		sb.append(", jobTitle=");
		sb.append(jobTitle);
		sb.append(", jobClass=");
		sb.append(jobClass);
		sb.append(", hoursOfOperation=");
		sb.append(hoursOfOperation);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Contact toEntityModel() {
		ContactImpl contactImpl = new ContactImpl();

		contactImpl.setMvccVersion(mvccVersion);
		contactImpl.setContactId(contactId);
		contactImpl.setCompanyId(companyId);
		contactImpl.setUserId(userId);

		if (userName == null) {
			contactImpl.setUserName("");
		}
		else {
			contactImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			contactImpl.setCreateDate(null);
		}
		else {
			contactImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			contactImpl.setModifiedDate(null);
		}
		else {
			contactImpl.setModifiedDate(new Date(modifiedDate));
		}

		contactImpl.setClassNameId(classNameId);
		contactImpl.setClassPK(classPK);
		contactImpl.setParentContactId(parentContactId);

		if (emailAddress == null) {
			contactImpl.setEmailAddress("");
		}
		else {
			contactImpl.setEmailAddress(emailAddress);
		}

		if (firstName == null) {
			contactImpl.setFirstName("");
		}
		else {
			contactImpl.setFirstName(firstName);
		}

		if (middleName == null) {
			contactImpl.setMiddleName("");
		}
		else {
			contactImpl.setMiddleName(middleName);
		}

		if (lastName == null) {
			contactImpl.setLastName("");
		}
		else {
			contactImpl.setLastName(lastName);
		}

		contactImpl.setPrefixListTypeId(prefixListTypeId);
		contactImpl.setSuffixListTypeId(suffixListTypeId);
		contactImpl.setMale(male);

		if (birthday == Long.MIN_VALUE) {
			contactImpl.setBirthday(null);
		}
		else {
			contactImpl.setBirthday(new Date(birthday));
		}

		if (smsSn == null) {
			contactImpl.setSmsSn("");
		}
		else {
			contactImpl.setSmsSn(smsSn);
		}

		if (facebookSn == null) {
			contactImpl.setFacebookSn("");
		}
		else {
			contactImpl.setFacebookSn(facebookSn);
		}

		if (jabberSn == null) {
			contactImpl.setJabberSn("");
		}
		else {
			contactImpl.setJabberSn(jabberSn);
		}

		if (skypeSn == null) {
			contactImpl.setSkypeSn("");
		}
		else {
			contactImpl.setSkypeSn(skypeSn);
		}

		if (twitterSn == null) {
			contactImpl.setTwitterSn("");
		}
		else {
			contactImpl.setTwitterSn(twitterSn);
		}

		if (employeeStatusId == null) {
			contactImpl.setEmployeeStatusId("");
		}
		else {
			contactImpl.setEmployeeStatusId(employeeStatusId);
		}

		if (employeeNumber == null) {
			contactImpl.setEmployeeNumber("");
		}
		else {
			contactImpl.setEmployeeNumber(employeeNumber);
		}

		if (jobTitle == null) {
			contactImpl.setJobTitle("");
		}
		else {
			contactImpl.setJobTitle(jobTitle);
		}

		if (jobClass == null) {
			contactImpl.setJobClass("");
		}
		else {
			contactImpl.setJobClass(jobClass);
		}

		if (hoursOfOperation == null) {
			contactImpl.setHoursOfOperation("");
		}
		else {
			contactImpl.setHoursOfOperation(hoursOfOperation);
		}

		contactImpl.resetOriginalValues();

		return contactImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		contactId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		parentContactId = objectInput.readLong();
		emailAddress = objectInput.readUTF();
		firstName = objectInput.readUTF();
		middleName = objectInput.readUTF();
		lastName = objectInput.readUTF();

		prefixListTypeId = objectInput.readLong();

		suffixListTypeId = objectInput.readLong();

		male = objectInput.readBoolean();
		birthday = objectInput.readLong();
		smsSn = objectInput.readUTF();
		facebookSn = objectInput.readUTF();
		jabberSn = objectInput.readUTF();
		skypeSn = objectInput.readUTF();
		twitterSn = objectInput.readUTF();
		employeeStatusId = objectInput.readUTF();
		employeeNumber = objectInput.readUTF();
		jobTitle = objectInput.readUTF();
		jobClass = objectInput.readUTF();
		hoursOfOperation = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(contactId);

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

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(parentContactId);

		if (emailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (middleName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(middleName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		objectOutput.writeLong(prefixListTypeId);

		objectOutput.writeLong(suffixListTypeId);

		objectOutput.writeBoolean(male);
		objectOutput.writeLong(birthday);

		if (smsSn == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(smsSn);
		}

		if (facebookSn == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(facebookSn);
		}

		if (jabberSn == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jabberSn);
		}

		if (skypeSn == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(skypeSn);
		}

		if (twitterSn == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(twitterSn);
		}

		if (employeeStatusId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeStatusId);
		}

		if (employeeNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeNumber);
		}

		if (jobTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jobTitle);
		}

		if (jobClass == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jobClass);
		}

		if (hoursOfOperation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hoursOfOperation);
		}
	}

	public long mvccVersion;
	public long contactId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long parentContactId;
	public String emailAddress;
	public String firstName;
	public String middleName;
	public String lastName;
	public long prefixListTypeId;
	public long suffixListTypeId;
	public boolean male;
	public long birthday;
	public String smsSn;
	public String facebookSn;
	public String jabberSn;
	public String skypeSn;
	public String twitterSn;
	public String employeeStatusId;
	public String employeeNumber;
	public String jobTitle;
	public String jobClass;
	public String hoursOfOperation;

}