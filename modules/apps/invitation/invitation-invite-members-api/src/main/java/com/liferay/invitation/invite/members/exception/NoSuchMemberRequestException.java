/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.invitation.invite.members.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchMemberRequestException extends NoSuchModelException {

	public NoSuchMemberRequestException() {
	}

	public NoSuchMemberRequestException(String msg) {
		super(msg);
	}

	public NoSuchMemberRequestException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchMemberRequestException(Throwable throwable) {
		super(throwable);
	}

}