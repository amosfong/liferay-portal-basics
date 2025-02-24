/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.exception;

import com.liferay.portal.kernel.exception.DuplicateExternalReferenceCodeException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateDLFileEntryMetadataExternalReferenceCodeException
	extends DuplicateExternalReferenceCodeException {

	public DuplicateDLFileEntryMetadataExternalReferenceCodeException() {
	}

	public DuplicateDLFileEntryMetadataExternalReferenceCodeException(
		String msg) {

		super(msg);
	}

	public DuplicateDLFileEntryMetadataExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateDLFileEntryMetadataExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}