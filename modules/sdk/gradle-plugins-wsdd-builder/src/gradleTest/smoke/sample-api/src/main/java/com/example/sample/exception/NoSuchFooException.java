/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.example.sample.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchFooException extends NoSuchModelException {

	public NoSuchFooException() {
	}

	public NoSuchFooException(String msg) {
		super(msg);
	}

	public NoSuchFooException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFooException(Throwable throwable) {
		super(throwable);
	}

}