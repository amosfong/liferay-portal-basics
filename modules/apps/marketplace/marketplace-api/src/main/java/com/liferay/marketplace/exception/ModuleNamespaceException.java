/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Ryan Park
 */
public class ModuleNamespaceException extends PortalException {

	public ModuleNamespaceException() {
	}

	public ModuleNamespaceException(String msg) {
		super(msg);
	}

	public ModuleNamespaceException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public ModuleNamespaceException(Throwable throwable) {
		super(throwable);
	}

}