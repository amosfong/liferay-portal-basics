/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ServiceComponent service. Represents a row in the &quot;ServiceComponent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.ServiceComponentModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.ServiceComponentImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceComponent
 * @generated
 */
@ProviderType
public interface ServiceComponentModel
	extends BaseModel<ServiceComponent>, MVCCModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a service component model instance should use the {@link ServiceComponent} interface instead.
	 */

	/**
	 * Returns the primary key of this service component.
	 *
	 * @return the primary key of this service component
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this service component.
	 *
	 * @param primaryKey the primary key of this service component
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this service component.
	 *
	 * @return the mvcc version of this service component
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this service component.
	 *
	 * @param mvccVersion the mvcc version of this service component
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the service component ID of this service component.
	 *
	 * @return the service component ID of this service component
	 */
	public long getServiceComponentId();

	/**
	 * Sets the service component ID of this service component.
	 *
	 * @param serviceComponentId the service component ID of this service component
	 */
	public void setServiceComponentId(long serviceComponentId);

	/**
	 * Returns the build namespace of this service component.
	 *
	 * @return the build namespace of this service component
	 */
	@AutoEscape
	public String getBuildNamespace();

	/**
	 * Sets the build namespace of this service component.
	 *
	 * @param buildNamespace the build namespace of this service component
	 */
	public void setBuildNamespace(String buildNamespace);

	/**
	 * Returns the build number of this service component.
	 *
	 * @return the build number of this service component
	 */
	public long getBuildNumber();

	/**
	 * Sets the build number of this service component.
	 *
	 * @param buildNumber the build number of this service component
	 */
	public void setBuildNumber(long buildNumber);

	/**
	 * Returns the build date of this service component.
	 *
	 * @return the build date of this service component
	 */
	public long getBuildDate();

	/**
	 * Sets the build date of this service component.
	 *
	 * @param buildDate the build date of this service component
	 */
	public void setBuildDate(long buildDate);

	/**
	 * Returns the data of this service component.
	 *
	 * @return the data of this service component
	 */
	@AutoEscape
	public String getData();

	/**
	 * Sets the data of this service component.
	 *
	 * @param data the data of this service component
	 */
	public void setData(String data);

	@Override
	public ServiceComponent cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}