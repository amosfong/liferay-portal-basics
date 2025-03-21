/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dispatch.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Matija Petanjek
 */
public class DispatchLogStartDateException extends PortalException {

	public DispatchLogStartDateException() {
	}

	public DispatchLogStartDateException(String msg) {
		super(msg);
	}

	public DispatchLogStartDateException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public DispatchLogStartDateException(Throwable throwable) {
		super(throwable);
	}

}