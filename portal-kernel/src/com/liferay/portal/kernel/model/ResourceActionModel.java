/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ResourceAction service. Represents a row in the &quot;ResourceAction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.ResourceActionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.ResourceActionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceAction
 * @generated
 */
@ProviderType
public interface ResourceActionModel
	extends BaseModel<ResourceAction>, MVCCModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a resource action model instance should use the {@link ResourceAction} interface instead.
	 */

	/**
	 * Returns the primary key of this resource action.
	 *
	 * @return the primary key of this resource action
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this resource action.
	 *
	 * @param primaryKey the primary key of this resource action
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this resource action.
	 *
	 * @return the mvcc version of this resource action
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this resource action.
	 *
	 * @param mvccVersion the mvcc version of this resource action
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the resource action ID of this resource action.
	 *
	 * @return the resource action ID of this resource action
	 */
	public long getResourceActionId();

	/**
	 * Sets the resource action ID of this resource action.
	 *
	 * @param resourceActionId the resource action ID of this resource action
	 */
	public void setResourceActionId(long resourceActionId);

	/**
	 * Returns the name of this resource action.
	 *
	 * @return the name of this resource action
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this resource action.
	 *
	 * @param name the name of this resource action
	 */
	public void setName(String name);

	/**
	 * Returns the action ID of this resource action.
	 *
	 * @return the action ID of this resource action
	 */
	@AutoEscape
	public String getActionId();

	/**
	 * Sets the action ID of this resource action.
	 *
	 * @param actionId the action ID of this resource action
	 */
	public void setActionId(String actionId);

	/**
	 * Returns the bitwise value of this resource action.
	 *
	 * @return the bitwise value of this resource action
	 */
	public long getBitwiseValue();

	/**
	 * Sets the bitwise value of this resource action.
	 *
	 * @param bitwiseValue the bitwise value of this resource action
	 */
	public void setBitwiseValue(long bitwiseValue);

	@Override
	public ResourceAction cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}