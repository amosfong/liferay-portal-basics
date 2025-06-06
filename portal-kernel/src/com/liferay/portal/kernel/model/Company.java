/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Company service. Represents a row in the &quot;Company&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CompanyModel
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.CompanyImpl")
@ProviderType
public interface Company extends CompanyModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.model.impl.CompanyImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Company, Long> COMPANY_ID_ACCESSOR =
		new Accessor<Company, Long>() {

			@Override
			public Long get(Company company) {
				return company.getCompanyId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Company> getTypeClass() {
				return Company.class;
			}

		};

	public int compareTo(Company company);

	public String getAdminName();

	public String getAuthType();

	public CompanyInfo getCompanyInfo();

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #getGuestUser}
	 */
	@Deprecated
	public User getDefaultUser()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getDefaultWebId();

	public String getEmailAddress();

	public Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getGroupId();

	public User getGuestUser()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getKey();

	public java.security.Key getKeyObj();

	public java.util.Locale getLocale()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getPortalURL(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getPortalURL(long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getShortName()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.TimeZone getTimeZone()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getVirtualHostname();

	public boolean hasCompanyMx(String emailAddress);

	public boolean isAutoLogin();

	public boolean isSendPasswordResetLink();

	public boolean isSiteLogo();

	public boolean isStrangers();

	public boolean isStrangersVerify();

	public boolean isStrangersWithMx();

	public boolean isUpdatePasswordRequired();

	public void setGroupId(long groupId);

	public void setKey(String key);

	public void setKeyObj(java.security.Key keyObj);

	public void setVirtualHostname(String virtualHostname);

}