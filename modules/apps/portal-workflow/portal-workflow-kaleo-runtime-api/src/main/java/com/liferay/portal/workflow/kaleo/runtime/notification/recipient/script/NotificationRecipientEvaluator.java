/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.notification.recipient.script;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public interface NotificationRecipientEvaluator {

	public Map<String, ?> evaluate(
			KaleoNotificationRecipient kaleoNotificationRecipient,
			ExecutionContext executionContext)
		throws PortalException;

}