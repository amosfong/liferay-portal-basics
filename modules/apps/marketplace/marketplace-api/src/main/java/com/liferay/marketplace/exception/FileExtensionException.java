/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Douglas Wong
 */
public class FileExtensionException extends PortalException {

	public FileExtensionException() {
	}

	public FileExtensionException(String msg) {
		super(msg);
	}

	public FileExtensionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FileExtensionException(Throwable throwable) {
		super(throwable);
	}

}