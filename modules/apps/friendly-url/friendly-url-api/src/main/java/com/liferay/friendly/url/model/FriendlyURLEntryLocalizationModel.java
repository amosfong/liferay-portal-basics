/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FriendlyURLEntryLocalization service. Represents a row in the &quot;FriendlyURLEntryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryLocalizationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryLocalizationImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntryLocalization
 * @generated
 */
@ProviderType
public interface FriendlyURLEntryLocalizationModel
	extends AttachedModel, BaseModel<FriendlyURLEntryLocalization>,
			CTModel<FriendlyURLEntryLocalization>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a friendly url entry localization model instance should use the {@link FriendlyURLEntryLocalization} interface instead.
	 */

	/**
	 * Returns the primary key of this friendly url entry localization.
	 *
	 * @return the primary key of this friendly url entry localization
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this friendly url entry localization.
	 *
	 * @param primaryKey the primary key of this friendly url entry localization
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this friendly url entry localization.
	 *
	 * @return the mvcc version of this friendly url entry localization
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this friendly url entry localization.
	 *
	 * @param mvccVersion the mvcc version of this friendly url entry localization
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this friendly url entry localization.
	 *
	 * @return the ct collection ID of this friendly url entry localization
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this friendly url entry localization.
	 *
	 * @param ctCollectionId the ct collection ID of this friendly url entry localization
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the friendly url entry localization ID of this friendly url entry localization.
	 *
	 * @return the friendly url entry localization ID of this friendly url entry localization
	 */
	public long getFriendlyURLEntryLocalizationId();

	/**
	 * Sets the friendly url entry localization ID of this friendly url entry localization.
	 *
	 * @param friendlyURLEntryLocalizationId the friendly url entry localization ID of this friendly url entry localization
	 */
	public void setFriendlyURLEntryLocalizationId(
		long friendlyURLEntryLocalizationId);

	/**
	 * Returns the company ID of this friendly url entry localization.
	 *
	 * @return the company ID of this friendly url entry localization
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this friendly url entry localization.
	 *
	 * @param companyId the company ID of this friendly url entry localization
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the friendly url entry ID of this friendly url entry localization.
	 *
	 * @return the friendly url entry ID of this friendly url entry localization
	 */
	public long getFriendlyURLEntryId();

	/**
	 * Sets the friendly url entry ID of this friendly url entry localization.
	 *
	 * @param friendlyURLEntryId the friendly url entry ID of this friendly url entry localization
	 */
	public void setFriendlyURLEntryId(long friendlyURLEntryId);

	/**
	 * Returns the language ID of this friendly url entry localization.
	 *
	 * @return the language ID of this friendly url entry localization
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this friendly url entry localization.
	 *
	 * @param languageId the language ID of this friendly url entry localization
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the url title of this friendly url entry localization.
	 *
	 * @return the url title of this friendly url entry localization
	 */
	@AutoEscape
	public String getUrlTitle();

	/**
	 * Sets the url title of this friendly url entry localization.
	 *
	 * @param urlTitle the url title of this friendly url entry localization
	 */
	public void setUrlTitle(String urlTitle);

	/**
	 * Returns the group ID of this friendly url entry localization.
	 *
	 * @return the group ID of this friendly url entry localization
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this friendly url entry localization.
	 *
	 * @param groupId the group ID of this friendly url entry localization
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the fully qualified class name of this friendly url entry localization.
	 *
	 * @return the fully qualified class name of this friendly url entry localization
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this friendly url entry localization.
	 *
	 * @return the class name ID of this friendly url entry localization
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this friendly url entry localization.
	 *
	 * @param classNameId the class name ID of this friendly url entry localization
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this friendly url entry localization.
	 *
	 * @return the class pk of this friendly url entry localization
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this friendly url entry localization.
	 *
	 * @param classPK the class pk of this friendly url entry localization
	 */
	@Override
	public void setClassPK(long classPK);

	@Override
	public FriendlyURLEntryLocalization cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}