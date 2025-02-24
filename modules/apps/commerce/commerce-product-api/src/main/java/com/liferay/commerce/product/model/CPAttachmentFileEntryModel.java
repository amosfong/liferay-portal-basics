/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CPAttachmentFileEntry service. Represents a row in the &quot;CPAttachmentFileEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.product.model.impl.CPAttachmentFileEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.product.model.impl.CPAttachmentFileEntryImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CPAttachmentFileEntry
 * @generated
 */
@ProviderType
public interface CPAttachmentFileEntryModel
	extends AttachedModel, BaseModel<CPAttachmentFileEntry>,
			CTModel<CPAttachmentFileEntry>, ExternalReferenceCodeModel,
			LocalizedModel, MVCCModel, ShardedModel, StagedGroupedModel,
			WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp attachment file entry model instance should use the {@link CPAttachmentFileEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this cp attachment file entry.
	 *
	 * @return the primary key of this cp attachment file entry
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp attachment file entry.
	 *
	 * @param primaryKey the primary key of this cp attachment file entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this cp attachment file entry.
	 *
	 * @return the mvcc version of this cp attachment file entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this cp attachment file entry.
	 *
	 * @param mvccVersion the mvcc version of this cp attachment file entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this cp attachment file entry.
	 *
	 * @return the ct collection ID of this cp attachment file entry
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this cp attachment file entry.
	 *
	 * @param ctCollectionId the ct collection ID of this cp attachment file entry
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this cp attachment file entry.
	 *
	 * @return the uuid of this cp attachment file entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp attachment file entry.
	 *
	 * @param uuid the uuid of this cp attachment file entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this cp attachment file entry.
	 *
	 * @return the external reference code of this cp attachment file entry
	 */
	@AutoEscape
	@Override
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this cp attachment file entry.
	 *
	 * @param externalReferenceCode the external reference code of this cp attachment file entry
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the cp attachment file entry ID of this cp attachment file entry.
	 *
	 * @return the cp attachment file entry ID of this cp attachment file entry
	 */
	public long getCPAttachmentFileEntryId();

	/**
	 * Sets the cp attachment file entry ID of this cp attachment file entry.
	 *
	 * @param CPAttachmentFileEntryId the cp attachment file entry ID of this cp attachment file entry
	 */
	public void setCPAttachmentFileEntryId(long CPAttachmentFileEntryId);

	/**
	 * Returns the group ID of this cp attachment file entry.
	 *
	 * @return the group ID of this cp attachment file entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this cp attachment file entry.
	 *
	 * @param groupId the group ID of this cp attachment file entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this cp attachment file entry.
	 *
	 * @return the company ID of this cp attachment file entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp attachment file entry.
	 *
	 * @param companyId the company ID of this cp attachment file entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp attachment file entry.
	 *
	 * @return the user ID of this cp attachment file entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp attachment file entry.
	 *
	 * @param userId the user ID of this cp attachment file entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp attachment file entry.
	 *
	 * @return the user uuid of this cp attachment file entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp attachment file entry.
	 *
	 * @param userUuid the user uuid of this cp attachment file entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp attachment file entry.
	 *
	 * @return the user name of this cp attachment file entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp attachment file entry.
	 *
	 * @param userName the user name of this cp attachment file entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp attachment file entry.
	 *
	 * @return the create date of this cp attachment file entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp attachment file entry.
	 *
	 * @param createDate the create date of this cp attachment file entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp attachment file entry.
	 *
	 * @return the modified date of this cp attachment file entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp attachment file entry.
	 *
	 * @param modifiedDate the modified date of this cp attachment file entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this cp attachment file entry.
	 *
	 * @return the fully qualified class name of this cp attachment file entry
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this cp attachment file entry.
	 *
	 * @return the class name ID of this cp attachment file entry
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this cp attachment file entry.
	 *
	 * @param classNameId the class name ID of this cp attachment file entry
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this cp attachment file entry.
	 *
	 * @return the class pk of this cp attachment file entry
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this cp attachment file entry.
	 *
	 * @param classPK the class pk of this cp attachment file entry
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the file entry ID of this cp attachment file entry.
	 *
	 * @return the file entry ID of this cp attachment file entry
	 */
	public long getFileEntryId();

	/**
	 * Sets the file entry ID of this cp attachment file entry.
	 *
	 * @param fileEntryId the file entry ID of this cp attachment file entry
	 */
	public void setFileEntryId(long fileEntryId);

	/**
	 * Returns the cdn enabled of this cp attachment file entry.
	 *
	 * @return the cdn enabled of this cp attachment file entry
	 */
	public boolean getCDNEnabled();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is cdn enabled.
	 *
	 * @return <code>true</code> if this cp attachment file entry is cdn enabled; <code>false</code> otherwise
	 */
	public boolean isCDNEnabled();

	/**
	 * Sets whether this cp attachment file entry is cdn enabled.
	 *
	 * @param cdnEnabled the cdn enabled of this cp attachment file entry
	 */
	public void setCDNEnabled(boolean cdnEnabled);

	/**
	 * Returns the cdn url of this cp attachment file entry.
	 *
	 * @return the cdn url of this cp attachment file entry
	 */
	@AutoEscape
	public String getCDNURL();

	/**
	 * Sets the cdn url of this cp attachment file entry.
	 *
	 * @param cdnURL the cdn url of this cp attachment file entry
	 */
	public void setCDNURL(String cdnURL);

	/**
	 * Returns the display date of this cp attachment file entry.
	 *
	 * @return the display date of this cp attachment file entry
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this cp attachment file entry.
	 *
	 * @param displayDate the display date of this cp attachment file entry
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this cp attachment file entry.
	 *
	 * @return the expiration date of this cp attachment file entry
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this cp attachment file entry.
	 *
	 * @param expirationDate the expiration date of this cp attachment file entry
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the gallery enabled of this cp attachment file entry.
	 *
	 * @return the gallery enabled of this cp attachment file entry
	 */
	public boolean getGalleryEnabled();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is gallery enabled.
	 *
	 * @return <code>true</code> if this cp attachment file entry is gallery enabled; <code>false</code> otherwise
	 */
	public boolean isGalleryEnabled();

	/**
	 * Sets whether this cp attachment file entry is gallery enabled.
	 *
	 * @param galleryEnabled the gallery enabled of this cp attachment file entry
	 */
	public void setGalleryEnabled(boolean galleryEnabled);

	/**
	 * Returns the title of this cp attachment file entry.
	 *
	 * @return the title of this cp attachment file entry
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this cp attachment file entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this cp attachment file entry
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this cp attachment file entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this cp attachment file entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this cp attachment file entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this cp attachment file entry
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this cp attachment file entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this cp attachment file entry
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this cp attachment file entry.
	 *
	 * @return the locales and localized titles of this cp attachment file entry
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this cp attachment file entry.
	 *
	 * @param title the title of this cp attachment file entry
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this cp attachment file entry in the language.
	 *
	 * @param title the localized title of this cp attachment file entry
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this cp attachment file entry in the language, and sets the default locale.
	 *
	 * @param title the localized title of this cp attachment file entry
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this cp attachment file entry from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this cp attachment file entry
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this cp attachment file entry from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this cp attachment file entry
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the json of this cp attachment file entry.
	 *
	 * @return the json of this cp attachment file entry
	 */
	@AutoEscape
	public String getJson();

	/**
	 * Sets the json of this cp attachment file entry.
	 *
	 * @param json the json of this cp attachment file entry
	 */
	public void setJson(String json);

	/**
	 * Returns the priority of this cp attachment file entry.
	 *
	 * @return the priority of this cp attachment file entry
	 */
	public double getPriority();

	/**
	 * Sets the priority of this cp attachment file entry.
	 *
	 * @param priority the priority of this cp attachment file entry
	 */
	public void setPriority(double priority);

	/**
	 * Returns the type of this cp attachment file entry.
	 *
	 * @return the type of this cp attachment file entry
	 */
	public int getType();

	/**
	 * Sets the type of this cp attachment file entry.
	 *
	 * @param type the type of this cp attachment file entry
	 */
	public void setType(int type);

	/**
	 * Returns the last publish date of this cp attachment file entry.
	 *
	 * @return the last publish date of this cp attachment file entry
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this cp attachment file entry.
	 *
	 * @param lastPublishDate the last publish date of this cp attachment file entry
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this cp attachment file entry.
	 *
	 * @return the status of this cp attachment file entry
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this cp attachment file entry.
	 *
	 * @param status the status of this cp attachment file entry
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this cp attachment file entry.
	 *
	 * @return the status by user ID of this cp attachment file entry
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this cp attachment file entry.
	 *
	 * @param statusByUserId the status by user ID of this cp attachment file entry
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this cp attachment file entry.
	 *
	 * @return the status by user uuid of this cp attachment file entry
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this cp attachment file entry.
	 *
	 * @param statusByUserUuid the status by user uuid of this cp attachment file entry
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this cp attachment file entry.
	 *
	 * @return the status by user name of this cp attachment file entry
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this cp attachment file entry.
	 *
	 * @param statusByUserName the status by user name of this cp attachment file entry
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this cp attachment file entry.
	 *
	 * @return the status date of this cp attachment file entry
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this cp attachment file entry.
	 *
	 * @param statusDate the status date of this cp attachment file entry
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this cp attachment file entry is approved.
	 *
	 * @return <code>true</code> if this cp attachment file entry is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is denied.
	 *
	 * @return <code>true</code> if this cp attachment file entry is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is a draft.
	 *
	 * @return <code>true</code> if this cp attachment file entry is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is expired.
	 *
	 * @return <code>true</code> if this cp attachment file entry is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is inactive.
	 *
	 * @return <code>true</code> if this cp attachment file entry is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is incomplete.
	 *
	 * @return <code>true</code> if this cp attachment file entry is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is pending.
	 *
	 * @return <code>true</code> if this cp attachment file entry is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this cp attachment file entry is scheduled.
	 *
	 * @return <code>true</code> if this cp attachment file entry is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public CPAttachmentFileEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}