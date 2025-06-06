/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class FileEntryDisplayDateException extends PortalException {

	public FileEntryDisplayDateException() {
	}

	public FileEntryDisplayDateException(String msg) {
		super(msg);
	}

	public FileEntryDisplayDateException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FileEntryDisplayDateException(Throwable throwable) {
		super(throwable);
	}

}