/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class KBArticleParentException extends PortalException {

	public KBArticleParentException() {
	}

	public KBArticleParentException(String msg) {
		super(msg);
	}

	public KBArticleParentException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public KBArticleParentException(Throwable throwable) {
		super(throwable);
	}

}