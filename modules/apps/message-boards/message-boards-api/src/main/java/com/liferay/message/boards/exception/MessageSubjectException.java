/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class MessageSubjectException extends PortalException {

	public MessageSubjectException() {
	}

	public MessageSubjectException(String msg) {
		super(msg);
	}

	public MessageSubjectException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public MessageSubjectException(Throwable throwable) {
		super(throwable);
	}

}