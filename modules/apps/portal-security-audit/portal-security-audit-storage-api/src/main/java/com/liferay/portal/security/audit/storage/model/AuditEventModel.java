/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.audit.storage.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AuditEvent service. Represents a row in the &quot;Audit_AuditEvent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.security.audit.storage.model.impl.AuditEventModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.security.audit.storage.model.impl.AuditEventImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEvent
 * @generated
 */
@ProviderType
public interface AuditEventModel extends BaseModel<AuditEvent>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a audit event model instance should use the {@link AuditEvent} interface instead.
	 */

	/**
	 * Returns the primary key of this audit event.
	 *
	 * @return the primary key of this audit event
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this audit event.
	 *
	 * @param primaryKey the primary key of this audit event
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the audit event ID of this audit event.
	 *
	 * @return the audit event ID of this audit event
	 */
	public long getAuditEventId();

	/**
	 * Sets the audit event ID of this audit event.
	 *
	 * @param auditEventId the audit event ID of this audit event
	 */
	public void setAuditEventId(long auditEventId);

	/**
	 * Returns the group ID of this audit event.
	 *
	 * @return the group ID of this audit event
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this audit event.
	 *
	 * @param groupId the group ID of this audit event
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this audit event.
	 *
	 * @return the company ID of this audit event
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this audit event.
	 *
	 * @param companyId the company ID of this audit event
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this audit event.
	 *
	 * @return the user ID of this audit event
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this audit event.
	 *
	 * @param userId the user ID of this audit event
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this audit event.
	 *
	 * @return the user uuid of this audit event
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this audit event.
	 *
	 * @param userUuid the user uuid of this audit event
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this audit event.
	 *
	 * @return the user name of this audit event
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this audit event.
	 *
	 * @param userName the user name of this audit event
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this audit event.
	 *
	 * @return the create date of this audit event
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this audit event.
	 *
	 * @param createDate the create date of this audit event
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the event type of this audit event.
	 *
	 * @return the event type of this audit event
	 */
	@AutoEscape
	public String getEventType();

	/**
	 * Sets the event type of this audit event.
	 *
	 * @param eventType the event type of this audit event
	 */
	public void setEventType(String eventType);

	/**
	 * Returns the class name of this audit event.
	 *
	 * @return the class name of this audit event
	 */
	@AutoEscape
	public String getClassName();

	/**
	 * Sets the class name of this audit event.
	 *
	 * @param className the class name of this audit event
	 */
	public void setClassName(String className);

	/**
	 * Returns the class pk of this audit event.
	 *
	 * @return the class pk of this audit event
	 */
	@AutoEscape
	public String getClassPK();

	/**
	 * Sets the class pk of this audit event.
	 *
	 * @param classPK the class pk of this audit event
	 */
	public void setClassPK(String classPK);

	/**
	 * Returns the message of this audit event.
	 *
	 * @return the message of this audit event
	 */
	@AutoEscape
	public String getMessage();

	/**
	 * Sets the message of this audit event.
	 *
	 * @param message the message of this audit event
	 */
	public void setMessage(String message);

	/**
	 * Returns the client host of this audit event.
	 *
	 * @return the client host of this audit event
	 */
	@AutoEscape
	public String getClientHost();

	/**
	 * Sets the client host of this audit event.
	 *
	 * @param clientHost the client host of this audit event
	 */
	public void setClientHost(String clientHost);

	/**
	 * Returns the client ip of this audit event.
	 *
	 * @return the client ip of this audit event
	 */
	@AutoEscape
	public String getClientIP();

	/**
	 * Sets the client ip of this audit event.
	 *
	 * @param clientIP the client ip of this audit event
	 */
	public void setClientIP(String clientIP);

	/**
	 * Returns the server name of this audit event.
	 *
	 * @return the server name of this audit event
	 */
	@AutoEscape
	public String getServerName();

	/**
	 * Sets the server name of this audit event.
	 *
	 * @param serverName the server name of this audit event
	 */
	public void setServerName(String serverName);

	/**
	 * Returns the server port of this audit event.
	 *
	 * @return the server port of this audit event
	 */
	public int getServerPort();

	/**
	 * Sets the server port of this audit event.
	 *
	 * @param serverPort the server port of this audit event
	 */
	public void setServerPort(int serverPort);

	/**
	 * Returns the session ID of this audit event.
	 *
	 * @return the session ID of this audit event
	 */
	@AutoEscape
	public String getSessionID();

	/**
	 * Sets the session ID of this audit event.
	 *
	 * @param sessionID the session ID of this audit event
	 */
	public void setSessionID(String sessionID);

	/**
	 * Returns the additional info of this audit event.
	 *
	 * @return the additional info of this audit event
	 */
	@AutoEscape
	public String getAdditionalInfo();

	/**
	 * Sets the additional info of this audit event.
	 *
	 * @param additionalInfo the additional info of this audit event
	 */
	public void setAdditionalInfo(String additionalInfo);

	@Override
	public AuditEvent cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}