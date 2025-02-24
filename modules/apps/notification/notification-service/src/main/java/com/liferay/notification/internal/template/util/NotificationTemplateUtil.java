/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.internal.template.util;

import com.liferay.notification.exception.NotificationTemplateSystemException;
import com.liferay.object.definition.util.ObjectDefinitionUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Selton Guedes
 */
public class NotificationTemplateUtil {

	public static void validateInvokerBundle(String message, boolean system)
		throws PortalException {

		if (!system || ObjectDefinitionUtil.isInvokerBundleAllowed()) {
			return;
		}

		throw new NotificationTemplateSystemException(message);
	}

}