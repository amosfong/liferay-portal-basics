/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.exception;

import com.liferay.portal.kernel.exception.DuplicateExternalReferenceCodeException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateDDMTemplateExternalReferenceCodeException
	extends DuplicateExternalReferenceCodeException {

	public DuplicateDDMTemplateExternalReferenceCodeException() {
	}

	public DuplicateDDMTemplateExternalReferenceCodeException(String msg) {
		super(msg);
	}

	public DuplicateDDMTemplateExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateDDMTemplateExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}