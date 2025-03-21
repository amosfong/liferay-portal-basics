/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchVirtualHostException extends NoSuchModelException {

	public NoSuchVirtualHostException() {
	}

	public NoSuchVirtualHostException(String msg) {
		super(msg);
	}

	public NoSuchVirtualHostException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchVirtualHostException(Throwable throwable) {
		super(throwable);
	}

}