/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.version.VersionModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LVEntryLocalizationVersion service. Represents a row in the &quot;LVEntryLocalizationVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.tools.service.builder.test.model.impl.LVEntryLocalizationVersionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.tools.service.builder.test.model.impl.LVEntryLocalizationVersionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LVEntryLocalizationVersion
 * @generated
 */
@ProviderType
public interface LVEntryLocalizationVersionModel
	extends BaseModel<LVEntryLocalizationVersion>, ShardedModel,
			VersionModel<LVEntryLocalization> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a lv entry localization version model instance should use the {@link LVEntryLocalizationVersion} interface instead.
	 */

	/**
	 * Returns the primary key of this lv entry localization version.
	 *
	 * @return the primary key of this lv entry localization version
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this lv entry localization version.
	 *
	 * @param primaryKey the primary key of this lv entry localization version
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the lv entry localization version ID of this lv entry localization version.
	 *
	 * @return the lv entry localization version ID of this lv entry localization version
	 */
	public long getLvEntryLocalizationVersionId();

	/**
	 * Sets the lv entry localization version ID of this lv entry localization version.
	 *
	 * @param lvEntryLocalizationVersionId the lv entry localization version ID of this lv entry localization version
	 */
	public void setLvEntryLocalizationVersionId(
		long lvEntryLocalizationVersionId);

	/**
	 * Returns the version of this lv entry localization version.
	 *
	 * @return the version of this lv entry localization version
	 */
	@Override
	public int getVersion();

	/**
	 * Sets the version of this lv entry localization version.
	 *
	 * @param version the version of this lv entry localization version
	 */
	@Override
	public void setVersion(int version);

	/**
	 * Returns the lv entry localization ID of this lv entry localization version.
	 *
	 * @return the lv entry localization ID of this lv entry localization version
	 */
	public long getLvEntryLocalizationId();

	/**
	 * Sets the lv entry localization ID of this lv entry localization version.
	 *
	 * @param lvEntryLocalizationId the lv entry localization ID of this lv entry localization version
	 */
	public void setLvEntryLocalizationId(long lvEntryLocalizationId);

	/**
	 * Returns the company ID of this lv entry localization version.
	 *
	 * @return the company ID of this lv entry localization version
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this lv entry localization version.
	 *
	 * @param companyId the company ID of this lv entry localization version
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the lv entry ID of this lv entry localization version.
	 *
	 * @return the lv entry ID of this lv entry localization version
	 */
	public long getLvEntryId();

	/**
	 * Sets the lv entry ID of this lv entry localization version.
	 *
	 * @param lvEntryId the lv entry ID of this lv entry localization version
	 */
	public void setLvEntryId(long lvEntryId);

	/**
	 * Returns the language ID of this lv entry localization version.
	 *
	 * @return the language ID of this lv entry localization version
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this lv entry localization version.
	 *
	 * @param languageId the language ID of this lv entry localization version
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the title of this lv entry localization version.
	 *
	 * @return the title of this lv entry localization version
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this lv entry localization version.
	 *
	 * @param title the title of this lv entry localization version
	 */
	public void setTitle(String title);

	/**
	 * Returns the content of this lv entry localization version.
	 *
	 * @return the content of this lv entry localization version
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this lv entry localization version.
	 *
	 * @param content the content of this lv entry localization version
	 */
	public void setContent(String content);

	@Override
	public LVEntryLocalizationVersion cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}