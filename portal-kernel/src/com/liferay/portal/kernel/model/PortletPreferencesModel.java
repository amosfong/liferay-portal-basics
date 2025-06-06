/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PortletPreferences service. Represents a row in the &quot;PortletPreferences&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.PortletPreferencesModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.PortletPreferencesImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferences
 * @generated
 */
@ProviderType
public interface PortletPreferencesModel
	extends BaseModel<PortletPreferences>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a portlet preferences model instance should use the {@link PortletPreferences} interface instead.
	 */

	/**
	 * Returns the primary key of this portlet preferences.
	 *
	 * @return the primary key of this portlet preferences
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this portlet preferences.
	 *
	 * @param primaryKey the primary key of this portlet preferences
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this portlet preferences.
	 *
	 * @return the mvcc version of this portlet preferences
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this portlet preferences.
	 *
	 * @param mvccVersion the mvcc version of this portlet preferences
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the portlet preferences ID of this portlet preferences.
	 *
	 * @return the portlet preferences ID of this portlet preferences
	 */
	public long getPortletPreferencesId();

	/**
	 * Sets the portlet preferences ID of this portlet preferences.
	 *
	 * @param portletPreferencesId the portlet preferences ID of this portlet preferences
	 */
	public void setPortletPreferencesId(long portletPreferencesId);

	/**
	 * Returns the company ID of this portlet preferences.
	 *
	 * @return the company ID of this portlet preferences
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this portlet preferences.
	 *
	 * @param companyId the company ID of this portlet preferences
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the owner ID of this portlet preferences.
	 *
	 * @return the owner ID of this portlet preferences
	 */
	public long getOwnerId();

	/**
	 * Sets the owner ID of this portlet preferences.
	 *
	 * @param ownerId the owner ID of this portlet preferences
	 */
	public void setOwnerId(long ownerId);

	/**
	 * Returns the owner type of this portlet preferences.
	 *
	 * @return the owner type of this portlet preferences
	 */
	public int getOwnerType();

	/**
	 * Sets the owner type of this portlet preferences.
	 *
	 * @param ownerType the owner type of this portlet preferences
	 */
	public void setOwnerType(int ownerType);

	/**
	 * Returns the plid of this portlet preferences.
	 *
	 * @return the plid of this portlet preferences
	 */
	public long getPlid();

	/**
	 * Sets the plid of this portlet preferences.
	 *
	 * @param plid the plid of this portlet preferences
	 */
	public void setPlid(long plid);

	/**
	 * Returns the portlet ID of this portlet preferences.
	 *
	 * @return the portlet ID of this portlet preferences
	 */
	@AutoEscape
	public String getPortletId();

	/**
	 * Sets the portlet ID of this portlet preferences.
	 *
	 * @param portletId the portlet ID of this portlet preferences
	 */
	public void setPortletId(String portletId);

	@Override
	public PortletPreferences cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}