/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.language.override.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchPLOEntryException extends NoSuchModelException {

	public NoSuchPLOEntryException() {
	}

	public NoSuchPLOEntryException(String msg) {
		super(msg);
	}

	public NoSuchPLOEntryException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchPLOEntryException(Throwable throwable) {
		super(throwable);
	}

}