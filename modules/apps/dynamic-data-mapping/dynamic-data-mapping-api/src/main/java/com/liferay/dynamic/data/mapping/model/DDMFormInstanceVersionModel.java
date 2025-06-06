/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMFormInstanceVersion service. Represents a row in the &quot;DDMFormInstanceVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceVersionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceVersionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceVersion
 * @generated
 */
@ProviderType
public interface DDMFormInstanceVersionModel
	extends BaseModel<DDMFormInstanceVersion>, LocalizedModel, MVCCModel,
			ShardedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm form instance version model instance should use the {@link DDMFormInstanceVersion} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm form instance version.
	 *
	 * @return the primary key of this ddm form instance version
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm form instance version.
	 *
	 * @param primaryKey the primary key of this ddm form instance version
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm form instance version.
	 *
	 * @return the mvcc version of this ddm form instance version
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm form instance version.
	 *
	 * @param mvccVersion the mvcc version of this ddm form instance version
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the form instance version ID of this ddm form instance version.
	 *
	 * @return the form instance version ID of this ddm form instance version
	 */
	public long getFormInstanceVersionId();

	/**
	 * Sets the form instance version ID of this ddm form instance version.
	 *
	 * @param formInstanceVersionId the form instance version ID of this ddm form instance version
	 */
	public void setFormInstanceVersionId(long formInstanceVersionId);

	/**
	 * Returns the group ID of this ddm form instance version.
	 *
	 * @return the group ID of this ddm form instance version
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this ddm form instance version.
	 *
	 * @param groupId the group ID of this ddm form instance version
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this ddm form instance version.
	 *
	 * @return the company ID of this ddm form instance version
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm form instance version.
	 *
	 * @param companyId the company ID of this ddm form instance version
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ddm form instance version.
	 *
	 * @return the user ID of this ddm form instance version
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this ddm form instance version.
	 *
	 * @param userId the user ID of this ddm form instance version
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ddm form instance version.
	 *
	 * @return the user uuid of this ddm form instance version
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ddm form instance version.
	 *
	 * @param userUuid the user uuid of this ddm form instance version
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this ddm form instance version.
	 *
	 * @return the user name of this ddm form instance version
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this ddm form instance version.
	 *
	 * @param userName the user name of this ddm form instance version
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this ddm form instance version.
	 *
	 * @return the create date of this ddm form instance version
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this ddm form instance version.
	 *
	 * @param createDate the create date of this ddm form instance version
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the form instance ID of this ddm form instance version.
	 *
	 * @return the form instance ID of this ddm form instance version
	 */
	public long getFormInstanceId();

	/**
	 * Sets the form instance ID of this ddm form instance version.
	 *
	 * @param formInstanceId the form instance ID of this ddm form instance version
	 */
	public void setFormInstanceId(long formInstanceId);

	/**
	 * Returns the structure version ID of this ddm form instance version.
	 *
	 * @return the structure version ID of this ddm form instance version
	 */
	public long getStructureVersionId();

	/**
	 * Sets the structure version ID of this ddm form instance version.
	 *
	 * @param structureVersionId the structure version ID of this ddm form instance version
	 */
	public void setStructureVersionId(long structureVersionId);

	/**
	 * Returns the name of this ddm form instance version.
	 *
	 * @return the name of this ddm form instance version
	 */
	public String getName();

	/**
	 * Returns the localized name of this ddm form instance version in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this ddm form instance version
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this ddm form instance version in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm form instance version. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this ddm form instance version in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this ddm form instance version
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this ddm form instance version in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm form instance version
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this ddm form instance version.
	 *
	 * @return the locales and localized names of this ddm form instance version
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this ddm form instance version.
	 *
	 * @param name the name of this ddm form instance version
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this ddm form instance version in the language.
	 *
	 * @param name the localized name of this ddm form instance version
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this ddm form instance version in the language, and sets the default locale.
	 *
	 * @param name the localized name of this ddm form instance version
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this ddm form instance version from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this ddm form instance version
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this ddm form instance version from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this ddm form instance version
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this ddm form instance version.
	 *
	 * @return the description of this ddm form instance version
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this ddm form instance version in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this ddm form instance version
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this ddm form instance version in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm form instance version. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this ddm form instance version in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this ddm form instance version
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this ddm form instance version in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm form instance version
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this ddm form instance version.
	 *
	 * @return the locales and localized descriptions of this ddm form instance version
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this ddm form instance version.
	 *
	 * @param description the description of this ddm form instance version
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this ddm form instance version in the language.
	 *
	 * @param description the localized description of this ddm form instance version
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this ddm form instance version in the language, and sets the default locale.
	 *
	 * @param description the localized description of this ddm form instance version
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this ddm form instance version from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm form instance version
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this ddm form instance version from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm form instance version
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the settings of this ddm form instance version.
	 *
	 * @return the settings of this ddm form instance version
	 */
	@AutoEscape
	public String getSettings();

	/**
	 * Sets the settings of this ddm form instance version.
	 *
	 * @param settings the settings of this ddm form instance version
	 */
	public void setSettings(String settings);

	/**
	 * Returns the version of this ddm form instance version.
	 *
	 * @return the version of this ddm form instance version
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this ddm form instance version.
	 *
	 * @param version the version of this ddm form instance version
	 */
	public void setVersion(String version);

	/**
	 * Returns the status of this ddm form instance version.
	 *
	 * @return the status of this ddm form instance version
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this ddm form instance version.
	 *
	 * @param status the status of this ddm form instance version
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this ddm form instance version.
	 *
	 * @return the status by user ID of this ddm form instance version
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this ddm form instance version.
	 *
	 * @param statusByUserId the status by user ID of this ddm form instance version
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this ddm form instance version.
	 *
	 * @return the status by user uuid of this ddm form instance version
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this ddm form instance version.
	 *
	 * @param statusByUserUuid the status by user uuid of this ddm form instance version
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this ddm form instance version.
	 *
	 * @return the status by user name of this ddm form instance version
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this ddm form instance version.
	 *
	 * @param statusByUserName the status by user name of this ddm form instance version
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this ddm form instance version.
	 *
	 * @return the status date of this ddm form instance version
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this ddm form instance version.
	 *
	 * @param statusDate the status date of this ddm form instance version
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this ddm form instance version is approved.
	 *
	 * @return <code>true</code> if this ddm form instance version is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this ddm form instance version is denied.
	 *
	 * @return <code>true</code> if this ddm form instance version is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this ddm form instance version is a draft.
	 *
	 * @return <code>true</code> if this ddm form instance version is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this ddm form instance version is expired.
	 *
	 * @return <code>true</code> if this ddm form instance version is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this ddm form instance version is inactive.
	 *
	 * @return <code>true</code> if this ddm form instance version is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this ddm form instance version is incomplete.
	 *
	 * @return <code>true</code> if this ddm form instance version is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this ddm form instance version is pending.
	 *
	 * @return <code>true</code> if this ddm form instance version is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this ddm form instance version is scheduled.
	 *
	 * @return <code>true</code> if this ddm form instance version is scheduled; <code>false</code> otherwise
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
	public DDMFormInstanceVersion cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}