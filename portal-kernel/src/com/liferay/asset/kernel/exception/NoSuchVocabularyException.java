/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchVocabularyException extends NoSuchModelException {

	public NoSuchVocabularyException() {
	}

	public NoSuchVocabularyException(String msg) {
		super(msg);
	}

	public NoSuchVocabularyException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchVocabularyException(Throwable throwable) {
		super(throwable);
	}

}