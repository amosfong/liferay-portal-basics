/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class ContentException extends PortalException {

	public ContentException() {
	}

	public ContentException(String msg) {
		super(msg);
	}

	public ContentException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public ContentException(Throwable throwable) {
		super(throwable);
	}

}