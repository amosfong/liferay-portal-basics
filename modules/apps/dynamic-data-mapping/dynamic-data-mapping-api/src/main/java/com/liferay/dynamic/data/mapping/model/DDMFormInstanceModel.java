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
import com.liferay.portal.kernel.model.StagedGroupedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMFormInstance service. Represents a row in the &quot;DDMFormInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstance
 * @generated
 */
@ProviderType
public interface DDMFormInstanceModel
	extends BaseModel<DDMFormInstance>, LocalizedModel, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm form instance model instance should use the {@link DDMFormInstance} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm form instance.
	 *
	 * @return the primary key of this ddm form instance
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm form instance.
	 *
	 * @param primaryKey the primary key of this ddm form instance
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm form instance.
	 *
	 * @return the mvcc version of this ddm form instance
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm form instance.
	 *
	 * @param mvccVersion the mvcc version of this ddm form instance
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this ddm form instance.
	 *
	 * @return the uuid of this ddm form instance
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this ddm form instance.
	 *
	 * @param uuid the uuid of this ddm form instance
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the form instance ID of this ddm form instance.
	 *
	 * @return the form instance ID of this ddm form instance
	 */
	public long getFormInstanceId();

	/**
	 * Sets the form instance ID of this ddm form instance.
	 *
	 * @param formInstanceId the form instance ID of this ddm form instance
	 */
	public void setFormInstanceId(long formInstanceId);

	/**
	 * Returns the group ID of this ddm form instance.
	 *
	 * @return the group ID of this ddm form instance
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this ddm form instance.
	 *
	 * @param groupId the group ID of this ddm form instance
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this ddm form instance.
	 *
	 * @return the company ID of this ddm form instance
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm form instance.
	 *
	 * @param companyId the company ID of this ddm form instance
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ddm form instance.
	 *
	 * @return the user ID of this ddm form instance
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this ddm form instance.
	 *
	 * @param userId the user ID of this ddm form instance
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ddm form instance.
	 *
	 * @return the user uuid of this ddm form instance
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ddm form instance.
	 *
	 * @param userUuid the user uuid of this ddm form instance
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this ddm form instance.
	 *
	 * @return the user name of this ddm form instance
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this ddm form instance.
	 *
	 * @param userName the user name of this ddm form instance
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the version user ID of this ddm form instance.
	 *
	 * @return the version user ID of this ddm form instance
	 */
	public long getVersionUserId();

	/**
	 * Sets the version user ID of this ddm form instance.
	 *
	 * @param versionUserId the version user ID of this ddm form instance
	 */
	public void setVersionUserId(long versionUserId);

	/**
	 * Returns the version user uuid of this ddm form instance.
	 *
	 * @return the version user uuid of this ddm form instance
	 */
	public String getVersionUserUuid();

	/**
	 * Sets the version user uuid of this ddm form instance.
	 *
	 * @param versionUserUuid the version user uuid of this ddm form instance
	 */
	public void setVersionUserUuid(String versionUserUuid);

	/**
	 * Returns the version user name of this ddm form instance.
	 *
	 * @return the version user name of this ddm form instance
	 */
	@AutoEscape
	public String getVersionUserName();

	/**
	 * Sets the version user name of this ddm form instance.
	 *
	 * @param versionUserName the version user name of this ddm form instance
	 */
	public void setVersionUserName(String versionUserName);

	/**
	 * Returns the create date of this ddm form instance.
	 *
	 * @return the create date of this ddm form instance
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this ddm form instance.
	 *
	 * @param createDate the create date of this ddm form instance
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this ddm form instance.
	 *
	 * @return the modified date of this ddm form instance
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ddm form instance.
	 *
	 * @param modifiedDate the modified date of this ddm form instance
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the structure ID of this ddm form instance.
	 *
	 * @return the structure ID of this ddm form instance
	 */
	public long getStructureId();

	/**
	 * Sets the structure ID of this ddm form instance.
	 *
	 * @param structureId the structure ID of this ddm form instance
	 */
	public void setStructureId(long structureId);

	/**
	 * Returns the version of this ddm form instance.
	 *
	 * @return the version of this ddm form instance
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this ddm form instance.
	 *
	 * @param version the version of this ddm form instance
	 */
	public void setVersion(String version);

	/**
	 * Returns the name of this ddm form instance.
	 *
	 * @return the name of this ddm form instance
	 */
	public String getName();

	/**
	 * Returns the localized name of this ddm form instance in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this ddm form instance
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this ddm form instance in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm form instance. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this ddm form instance in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this ddm form instance
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this ddm form instance in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm form instance
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this ddm form instance.
	 *
	 * @return the locales and localized names of this ddm form instance
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this ddm form instance.
	 *
	 * @param name the name of this ddm form instance
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this ddm form instance in the language.
	 *
	 * @param name the localized name of this ddm form instance
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this ddm form instance in the language, and sets the default locale.
	 *
	 * @param name the localized name of this ddm form instance
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this ddm form instance from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this ddm form instance
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this ddm form instance from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this ddm form instance
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this ddm form instance.
	 *
	 * @return the description of this ddm form instance
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this ddm form instance in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this ddm form instance
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this ddm form instance in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm form instance. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this ddm form instance in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this ddm form instance
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this ddm form instance in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm form instance
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this ddm form instance.
	 *
	 * @return the locales and localized descriptions of this ddm form instance
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this ddm form instance.
	 *
	 * @param description the description of this ddm form instance
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this ddm form instance in the language.
	 *
	 * @param description the localized description of this ddm form instance
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this ddm form instance in the language, and sets the default locale.
	 *
	 * @param description the localized description of this ddm form instance
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this ddm form instance from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm form instance
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this ddm form instance from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm form instance
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the settings of this ddm form instance.
	 *
	 * @return the settings of this ddm form instance
	 */
	@AutoEscape
	public String getSettings();

	/**
	 * Sets the settings of this ddm form instance.
	 *
	 * @param settings the settings of this ddm form instance
	 */
	public void setSettings(String settings);

	/**
	 * Returns the last publish date of this ddm form instance.
	 *
	 * @return the last publish date of this ddm form instance
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this ddm form instance.
	 *
	 * @param lastPublishDate the last publish date of this ddm form instance
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

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
	public DDMFormInstance cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}