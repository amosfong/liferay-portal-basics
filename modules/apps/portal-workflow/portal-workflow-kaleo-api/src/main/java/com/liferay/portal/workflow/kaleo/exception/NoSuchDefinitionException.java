/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchDefinitionException extends NoSuchModelException {

	public NoSuchDefinitionException() {
	}

	public NoSuchDefinitionException(String msg) {
		super(msg);
	}

	public NoSuchDefinitionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchDefinitionException(Throwable throwable) {
		super(throwable);
	}

}