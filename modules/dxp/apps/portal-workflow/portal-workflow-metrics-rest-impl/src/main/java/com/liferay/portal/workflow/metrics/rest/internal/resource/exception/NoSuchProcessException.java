/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.rest.internal.resource.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Rafael Praxedes
 */
public class NoSuchProcessException extends NoSuchModelException {

	public NoSuchProcessException() {
	}

	public NoSuchProcessException(String msg) {
		super(msg);
	}

	public NoSuchProcessException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchProcessException(Throwable throwable) {
		super(throwable);
	}

}