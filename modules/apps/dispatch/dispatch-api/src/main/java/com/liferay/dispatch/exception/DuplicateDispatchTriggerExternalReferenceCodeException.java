/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dispatch.exception;

import com.liferay.portal.kernel.exception.DuplicateExternalReferenceCodeException;

/**
 * @author Matija Petanjek
 */
public class DuplicateDispatchTriggerExternalReferenceCodeException
	extends DuplicateExternalReferenceCodeException {

	public DuplicateDispatchTriggerExternalReferenceCodeException() {
	}

	public DuplicateDispatchTriggerExternalReferenceCodeException(String msg) {
		super(msg);
	}

	public DuplicateDispatchTriggerExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateDispatchTriggerExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}