/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.template.exception;

import com.liferay.portal.kernel.exception.DuplicateExternalReferenceCodeException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateTemplateEntryExternalReferenceCodeException
	extends DuplicateExternalReferenceCodeException {

	public DuplicateTemplateEntryExternalReferenceCodeException() {
	}

	public DuplicateTemplateEntryExternalReferenceCodeException(String msg) {
		super(msg);
	}

	public DuplicateTemplateEntryExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateTemplateEntryExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}