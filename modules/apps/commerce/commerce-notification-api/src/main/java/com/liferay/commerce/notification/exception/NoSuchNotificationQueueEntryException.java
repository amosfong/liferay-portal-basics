/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.notification.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Alessio Antonio Rendina
 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
 */
@Deprecated
public class NoSuchNotificationQueueEntryException
	extends NoSuchModelException {

	public NoSuchNotificationQueueEntryException() {
	}

	public NoSuchNotificationQueueEntryException(String msg) {
		super(msg);
	}

	public NoSuchNotificationQueueEntryException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public NoSuchNotificationQueueEntryException(Throwable throwable) {
		super(throwable);
	}

}