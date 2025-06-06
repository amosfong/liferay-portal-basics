/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LayoutSet service. Represents a row in the &quot;LayoutSet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.LayoutSetModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.LayoutSetImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSet
 * @generated
 */
@ProviderType
public interface LayoutSetModel
	extends BaseModel<LayoutSet>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout set model instance should use the {@link LayoutSet} interface instead.
	 */

	/**
	 * Returns the primary key of this layout set.
	 *
	 * @return the primary key of this layout set
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout set.
	 *
	 * @param primaryKey the primary key of this layout set
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this layout set.
	 *
	 * @return the mvcc version of this layout set
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this layout set.
	 *
	 * @param mvccVersion the mvcc version of this layout set
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the layout set ID of this layout set.
	 *
	 * @return the layout set ID of this layout set
	 */
	public long getLayoutSetId();

	/**
	 * Sets the layout set ID of this layout set.
	 *
	 * @param layoutSetId the layout set ID of this layout set
	 */
	public void setLayoutSetId(long layoutSetId);

	/**
	 * Returns the group ID of this layout set.
	 *
	 * @return the group ID of this layout set
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this layout set.
	 *
	 * @param groupId the group ID of this layout set
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout set.
	 *
	 * @return the company ID of this layout set
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout set.
	 *
	 * @param companyId the company ID of this layout set
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this layout set.
	 *
	 * @return the create date of this layout set
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout set.
	 *
	 * @param createDate the create date of this layout set
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this layout set.
	 *
	 * @return the modified date of this layout set
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout set.
	 *
	 * @param modifiedDate the modified date of this layout set
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the private layout of this layout set.
	 *
	 * @return the private layout of this layout set
	 */
	public boolean getPrivateLayout();

	/**
	 * Returns <code>true</code> if this layout set is private layout.
	 *
	 * @return <code>true</code> if this layout set is private layout; <code>false</code> otherwise
	 */
	public boolean isPrivateLayout();

	/**
	 * Sets whether this layout set is private layout.
	 *
	 * @param privateLayout the private layout of this layout set
	 */
	public void setPrivateLayout(boolean privateLayout);

	/**
	 * Returns the logo ID of this layout set.
	 *
	 * @return the logo ID of this layout set
	 */
	public long getLogoId();

	/**
	 * Sets the logo ID of this layout set.
	 *
	 * @param logoId the logo ID of this layout set
	 */
	public void setLogoId(long logoId);

	/**
	 * Returns the theme ID of this layout set.
	 *
	 * @return the theme ID of this layout set
	 */
	@AutoEscape
	public String getThemeId();

	/**
	 * Sets the theme ID of this layout set.
	 *
	 * @param themeId the theme ID of this layout set
	 */
	public void setThemeId(String themeId);

	/**
	 * Returns the color scheme ID of this layout set.
	 *
	 * @return the color scheme ID of this layout set
	 */
	@AutoEscape
	public String getColorSchemeId();

	/**
	 * Sets the color scheme ID of this layout set.
	 *
	 * @param colorSchemeId the color scheme ID of this layout set
	 */
	public void setColorSchemeId(String colorSchemeId);

	/**
	 * Returns the favicon file entry ID of this layout set.
	 *
	 * @return the favicon file entry ID of this layout set
	 */
	public long getFaviconFileEntryId();

	/**
	 * Sets the favicon file entry ID of this layout set.
	 *
	 * @param faviconFileEntryId the favicon file entry ID of this layout set
	 */
	public void setFaviconFileEntryId(long faviconFileEntryId);

	/**
	 * Returns the css of this layout set.
	 *
	 * @return the css of this layout set
	 */
	@AutoEscape
	public String getCss();

	/**
	 * Sets the css of this layout set.
	 *
	 * @param css the css of this layout set
	 */
	public void setCss(String css);

	/**
	 * Returns the settings of this layout set.
	 *
	 * @return the settings of this layout set
	 */
	@AutoEscape
	public String getSettings();

	/**
	 * Sets the settings of this layout set.
	 *
	 * @param settings the settings of this layout set
	 */
	public void setSettings(String settings);

	/**
	 * Returns the layout set prototype uuid of this layout set.
	 *
	 * @return the layout set prototype uuid of this layout set
	 */
	@AutoEscape
	public String getLayoutSetPrototypeUuid();

	/**
	 * Sets the layout set prototype uuid of this layout set.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid of this layout set
	 */
	public void setLayoutSetPrototypeUuid(String layoutSetPrototypeUuid);

	/**
	 * Returns the layout set prototype link enabled of this layout set.
	 *
	 * @return the layout set prototype link enabled of this layout set
	 */
	public boolean getLayoutSetPrototypeLinkEnabled();

	/**
	 * Returns <code>true</code> if this layout set is layout set prototype link enabled.
	 *
	 * @return <code>true</code> if this layout set is layout set prototype link enabled; <code>false</code> otherwise
	 */
	public boolean isLayoutSetPrototypeLinkEnabled();

	/**
	 * Sets whether this layout set is layout set prototype link enabled.
	 *
	 * @param layoutSetPrototypeLinkEnabled the layout set prototype link enabled of this layout set
	 */
	public void setLayoutSetPrototypeLinkEnabled(
		boolean layoutSetPrototypeLinkEnabled);

	@Override
	public LayoutSet cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}