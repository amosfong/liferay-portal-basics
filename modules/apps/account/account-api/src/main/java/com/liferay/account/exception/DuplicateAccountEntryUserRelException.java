/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateAccountEntryUserRelException extends PortalException {

	public DuplicateAccountEntryUserRelException() {
	}

	public DuplicateAccountEntryUserRelException(String msg) {
		super(msg);
	}

	public DuplicateAccountEntryUserRelException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateAccountEntryUserRelException(Throwable throwable) {
		super(throwable);
	}

}