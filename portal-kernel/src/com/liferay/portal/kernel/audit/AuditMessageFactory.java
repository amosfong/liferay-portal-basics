/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.audit;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class AuditMessageFactory {

	public AuditMessage getAuditMessage(String message) throws JSONException {
		return new AuditMessage(message);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long groupId, long userId,
		String userName, String className, String classPK, String message,
		Date timestamp, JSONObject additionalInfoJSONObject) {

		return new AuditMessage(
			eventType, companyId, groupId, userId, userName, className, classPK,
			message, timestamp, additionalInfoJSONObject);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName) {

		return new AuditMessage(eventType, companyId, userId, userName);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK,
			message);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message, Date timestamp,
		JSONObject additionalInfoJSONObject) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK, message,
			timestamp, additionalInfoJSONObject);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message,
		JSONObject additionalInfoJSONObject) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK, message,
			additionalInfoJSONObject);
	}

}