/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.exception;

import com.liferay.portal.kernel.exception.DuplicateExternalReferenceCodeException;

/**
 * @author Gabriel Albuquerque
 */
public class DuplicateNotificationTemplateExternalReferenceCodeException
	extends DuplicateExternalReferenceCodeException {

	public DuplicateNotificationTemplateExternalReferenceCodeException() {
	}

	public DuplicateNotificationTemplateExternalReferenceCodeException(
		String msg) {

		super(msg);
	}

	public DuplicateNotificationTemplateExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateNotificationTemplateExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}