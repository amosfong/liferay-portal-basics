/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Matthew Kong
 */
public class NoSuchFaroPreferencesException extends NoSuchModelException {

	public NoSuchFaroPreferencesException() {
	}

	public NoSuchFaroPreferencesException(String msg) {
		super(msg);
	}

	public NoSuchFaroPreferencesException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFaroPreferencesException(Throwable throwable) {
		super(throwable);
	}

}