/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.runtime.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Mika Koivisto
 */
public class UnsupportedBindingException extends PortalException {

	public UnsupportedBindingException() {
	}

	public UnsupportedBindingException(String msg) {
		super(msg);
	}

	public UnsupportedBindingException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public UnsupportedBindingException(Throwable throwable) {
		super(throwable);
	}

}