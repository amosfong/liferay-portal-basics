/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class FormInstanceNotPublishedException extends PortalException {

	public FormInstanceNotPublishedException() {
	}

	public FormInstanceNotPublishedException(String msg) {
		super(msg);
	}

	public FormInstanceNotPublishedException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FormInstanceNotPublishedException(Throwable throwable) {
		super(throwable);
	}

}