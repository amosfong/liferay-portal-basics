/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LayoutFriendlyURL service. Represents a row in the &quot;LayoutFriendlyURL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.LayoutFriendlyURLImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFriendlyURL
 * @generated
 */
@ProviderType
public interface LayoutFriendlyURLModel
	extends BaseModel<LayoutFriendlyURL>, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout friendly url model instance should use the {@link LayoutFriendlyURL} interface instead.
	 */

	/**
	 * Returns the primary key of this layout friendly url.
	 *
	 * @return the primary key of this layout friendly url
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout friendly url.
	 *
	 * @param primaryKey the primary key of this layout friendly url
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this layout friendly url.
	 *
	 * @return the mvcc version of this layout friendly url
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this layout friendly url.
	 *
	 * @param mvccVersion the mvcc version of this layout friendly url
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this layout friendly url.
	 *
	 * @return the uuid of this layout friendly url
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this layout friendly url.
	 *
	 * @param uuid the uuid of this layout friendly url
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the layout friendly url ID of this layout friendly url.
	 *
	 * @return the layout friendly url ID of this layout friendly url
	 */
	public long getLayoutFriendlyURLId();

	/**
	 * Sets the layout friendly url ID of this layout friendly url.
	 *
	 * @param layoutFriendlyURLId the layout friendly url ID of this layout friendly url
	 */
	public void setLayoutFriendlyURLId(long layoutFriendlyURLId);

	/**
	 * Returns the group ID of this layout friendly url.
	 *
	 * @return the group ID of this layout friendly url
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this layout friendly url.
	 *
	 * @param groupId the group ID of this layout friendly url
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout friendly url.
	 *
	 * @return the company ID of this layout friendly url
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout friendly url.
	 *
	 * @param companyId the company ID of this layout friendly url
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this layout friendly url.
	 *
	 * @return the user ID of this layout friendly url
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this layout friendly url.
	 *
	 * @param userId the user ID of this layout friendly url
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this layout friendly url.
	 *
	 * @return the user uuid of this layout friendly url
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this layout friendly url.
	 *
	 * @param userUuid the user uuid of this layout friendly url
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this layout friendly url.
	 *
	 * @return the user name of this layout friendly url
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this layout friendly url.
	 *
	 * @param userName the user name of this layout friendly url
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this layout friendly url.
	 *
	 * @return the create date of this layout friendly url
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout friendly url.
	 *
	 * @param createDate the create date of this layout friendly url
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this layout friendly url.
	 *
	 * @return the modified date of this layout friendly url
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout friendly url.
	 *
	 * @param modifiedDate the modified date of this layout friendly url
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the plid of this layout friendly url.
	 *
	 * @return the plid of this layout friendly url
	 */
	public long getPlid();

	/**
	 * Sets the plid of this layout friendly url.
	 *
	 * @param plid the plid of this layout friendly url
	 */
	public void setPlid(long plid);

	/**
	 * Returns the private layout of this layout friendly url.
	 *
	 * @return the private layout of this layout friendly url
	 */
	public boolean getPrivateLayout();

	/**
	 * Returns <code>true</code> if this layout friendly url is private layout.
	 *
	 * @return <code>true</code> if this layout friendly url is private layout; <code>false</code> otherwise
	 */
	public boolean isPrivateLayout();

	/**
	 * Sets whether this layout friendly url is private layout.
	 *
	 * @param privateLayout the private layout of this layout friendly url
	 */
	public void setPrivateLayout(boolean privateLayout);

	/**
	 * Returns the friendly url of this layout friendly url.
	 *
	 * @return the friendly url of this layout friendly url
	 */
	@AutoEscape
	public String getFriendlyURL();

	/**
	 * Sets the friendly url of this layout friendly url.
	 *
	 * @param friendlyURL the friendly url of this layout friendly url
	 */
	public void setFriendlyURL(String friendlyURL);

	/**
	 * Returns the language ID of this layout friendly url.
	 *
	 * @return the language ID of this layout friendly url
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this layout friendly url.
	 *
	 * @param languageId the language ID of this layout friendly url
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the last publish date of this layout friendly url.
	 *
	 * @return the last publish date of this layout friendly url
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this layout friendly url.
	 *
	 * @param lastPublishDate the last publish date of this layout friendly url
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public LayoutFriendlyURL cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}