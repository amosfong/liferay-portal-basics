/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid.connect.persistence.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the OpenIdConnectSession service. Represents a row in the &quot;OpenIdConnectSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.security.sso.openid.connect.persistence.model.impl.OpenIdConnectSessionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.security.sso.openid.connect.persistence.model.impl.OpenIdConnectSessionImpl</code>.
 * </p>
 *
 * @author Arthur Chan
 * @see OpenIdConnectSession
 * @generated
 */
@ProviderType
public interface OpenIdConnectSessionModel
	extends BaseModel<OpenIdConnectSession>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a open ID connect session model instance should use the {@link OpenIdConnectSession} interface instead.
	 */

	/**
	 * Returns the primary key of this open ID connect session.
	 *
	 * @return the primary key of this open ID connect session
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this open ID connect session.
	 *
	 * @param primaryKey the primary key of this open ID connect session
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this open ID connect session.
	 *
	 * @return the mvcc version of this open ID connect session
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this open ID connect session.
	 *
	 * @param mvccVersion the mvcc version of this open ID connect session
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the open ID connect session ID of this open ID connect session.
	 *
	 * @return the open ID connect session ID of this open ID connect session
	 */
	public long getOpenIdConnectSessionId();

	/**
	 * Sets the open ID connect session ID of this open ID connect session.
	 *
	 * @param openIdConnectSessionId the open ID connect session ID of this open ID connect session
	 */
	public void setOpenIdConnectSessionId(long openIdConnectSessionId);

	/**
	 * Returns the company ID of this open ID connect session.
	 *
	 * @return the company ID of this open ID connect session
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this open ID connect session.
	 *
	 * @param companyId the company ID of this open ID connect session
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this open ID connect session.
	 *
	 * @return the user ID of this open ID connect session
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this open ID connect session.
	 *
	 * @param userId the user ID of this open ID connect session
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this open ID connect session.
	 *
	 * @return the user uuid of this open ID connect session
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this open ID connect session.
	 *
	 * @param userUuid the user uuid of this open ID connect session
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the modified date of this open ID connect session.
	 *
	 * @return the modified date of this open ID connect session
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this open ID connect session.
	 *
	 * @param modifiedDate the modified date of this open ID connect session
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the access token of this open ID connect session.
	 *
	 * @return the access token of this open ID connect session
	 */
	@AutoEscape
	public String getAccessToken();

	/**
	 * Sets the access token of this open ID connect session.
	 *
	 * @param accessToken the access token of this open ID connect session
	 */
	public void setAccessToken(String accessToken);

	/**
	 * Returns the access token expiration date of this open ID connect session.
	 *
	 * @return the access token expiration date of this open ID connect session
	 */
	public Date getAccessTokenExpirationDate();

	/**
	 * Sets the access token expiration date of this open ID connect session.
	 *
	 * @param accessTokenExpirationDate the access token expiration date of this open ID connect session
	 */
	public void setAccessTokenExpirationDate(Date accessTokenExpirationDate);

	/**
	 * Returns the auth server well known uri of this open ID connect session.
	 *
	 * @return the auth server well known uri of this open ID connect session
	 */
	@AutoEscape
	public String getAuthServerWellKnownURI();

	/**
	 * Sets the auth server well known uri of this open ID connect session.
	 *
	 * @param authServerWellKnownURI the auth server well known uri of this open ID connect session
	 */
	public void setAuthServerWellKnownURI(String authServerWellKnownURI);

	/**
	 * Returns the client ID of this open ID connect session.
	 *
	 * @return the client ID of this open ID connect session
	 */
	@AutoEscape
	public String getClientId();

	/**
	 * Sets the client ID of this open ID connect session.
	 *
	 * @param clientId the client ID of this open ID connect session
	 */
	public void setClientId(String clientId);

	/**
	 * Returns the id token of this open ID connect session.
	 *
	 * @return the id token of this open ID connect session
	 */
	@AutoEscape
	public String getIdToken();

	/**
	 * Sets the id token of this open ID connect session.
	 *
	 * @param idToken the id token of this open ID connect session
	 */
	public void setIdToken(String idToken);

	/**
	 * Returns the refresh token of this open ID connect session.
	 *
	 * @return the refresh token of this open ID connect session
	 */
	@AutoEscape
	public String getRefreshToken();

	/**
	 * Sets the refresh token of this open ID connect session.
	 *
	 * @param refreshToken the refresh token of this open ID connect session
	 */
	public void setRefreshToken(String refreshToken);

	@Override
	public OpenIdConnectSession cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}