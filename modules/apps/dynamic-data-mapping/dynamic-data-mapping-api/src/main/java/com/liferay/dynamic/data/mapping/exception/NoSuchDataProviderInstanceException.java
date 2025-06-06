/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchDataProviderInstanceException extends NoSuchModelException {

	public NoSuchDataProviderInstanceException() {
	}

	public NoSuchDataProviderInstanceException(String msg) {
		super(msg);
	}

	public NoSuchDataProviderInstanceException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public NoSuchDataProviderInstanceException(Throwable throwable) {
		super(throwable);
	}

}