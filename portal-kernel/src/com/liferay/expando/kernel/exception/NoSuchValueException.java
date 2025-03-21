/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.expando.kernel.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchValueException extends NoSuchModelException {

	public NoSuchValueException() {
	}

	public NoSuchValueException(String msg) {
		super(msg);
	}

	public NoSuchValueException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchValueException(Throwable throwable) {
		super(throwable);
	}

}