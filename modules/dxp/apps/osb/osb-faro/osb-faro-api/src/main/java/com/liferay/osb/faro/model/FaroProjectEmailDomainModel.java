/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FaroProjectEmailDomain service. Represents a row in the &quot;OSBFaro_FaroProjectEmailDomain&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.faro.model.impl.FaroProjectEmailDomainModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.faro.model.impl.FaroProjectEmailDomainImpl</code>.
 * </p>
 *
 * @author Matthew Kong
 * @see FaroProjectEmailDomain
 * @generated
 */
@ProviderType
public interface FaroProjectEmailDomainModel
	extends BaseModel<FaroProjectEmailDomain>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a faro project email domain model instance should use the {@link FaroProjectEmailDomain} interface instead.
	 */

	/**
	 * Returns the primary key of this faro project email domain.
	 *
	 * @return the primary key of this faro project email domain
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this faro project email domain.
	 *
	 * @param primaryKey the primary key of this faro project email domain
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this faro project email domain.
	 *
	 * @return the mvcc version of this faro project email domain
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this faro project email domain.
	 *
	 * @param mvccVersion the mvcc version of this faro project email domain
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the faro project email domain ID of this faro project email domain.
	 *
	 * @return the faro project email domain ID of this faro project email domain
	 */
	public long getFaroProjectEmailDomainId();

	/**
	 * Sets the faro project email domain ID of this faro project email domain.
	 *
	 * @param faroProjectEmailDomainId the faro project email domain ID of this faro project email domain
	 */
	public void setFaroProjectEmailDomainId(long faroProjectEmailDomainId);

	/**
	 * Returns the group ID of this faro project email domain.
	 *
	 * @return the group ID of this faro project email domain
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this faro project email domain.
	 *
	 * @param groupId the group ID of this faro project email domain
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this faro project email domain.
	 *
	 * @return the company ID of this faro project email domain
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this faro project email domain.
	 *
	 * @param companyId the company ID of this faro project email domain
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the faro project ID of this faro project email domain.
	 *
	 * @return the faro project ID of this faro project email domain
	 */
	public long getFaroProjectId();

	/**
	 * Sets the faro project ID of this faro project email domain.
	 *
	 * @param faroProjectId the faro project ID of this faro project email domain
	 */
	public void setFaroProjectId(long faroProjectId);

	/**
	 * Returns the email domain of this faro project email domain.
	 *
	 * @return the email domain of this faro project email domain
	 */
	@AutoEscape
	public String getEmailDomain();

	/**
	 * Sets the email domain of this faro project email domain.
	 *
	 * @param emailDomain the email domain of this faro project email domain
	 */
	public void setEmailDomain(String emailDomain);

	@Override
	public FaroProjectEmailDomain cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}