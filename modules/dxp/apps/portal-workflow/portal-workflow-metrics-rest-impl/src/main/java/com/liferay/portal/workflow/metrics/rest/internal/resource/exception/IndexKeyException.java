/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.rest.internal.resource.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Rafael Praxedes
 */
public class IndexKeyException extends PortalException {

	public IndexKeyException() {
	}

	public IndexKeyException(String msg) {
		super(msg);
	}

	public IndexKeyException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public IndexKeyException(Throwable throwable) {
		super(throwable);
	}

}