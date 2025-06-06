/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.web.internal.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Stian Sigvartsen
 */
public class UserIdentifierExpressionException extends PortalException {

	public UserIdentifierExpressionException(String msg) {
		super(msg);
	}

	public UserIdentifierExpressionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public UserIdentifierExpressionException(Throwable throwable) {
		super(throwable);
	}

}