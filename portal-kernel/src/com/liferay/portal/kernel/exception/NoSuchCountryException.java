/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchCountryException extends NoSuchModelException {

	public NoSuchCountryException() {
	}

	public NoSuchCountryException(String msg) {
		super(msg);
	}

	public NoSuchCountryException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchCountryException(Throwable throwable) {
		super(throwable);
	}

}