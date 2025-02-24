/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Adolfo Pérez
 */
public class NoSuchClassTypeFieldException extends NoSuchModelException {

	public NoSuchClassTypeFieldException() {
	}

	public NoSuchClassTypeFieldException(String msg) {
		super(msg);
	}

	public NoSuchClassTypeFieldException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchClassTypeFieldException(Throwable throwable) {
		super(throwable);
	}

}