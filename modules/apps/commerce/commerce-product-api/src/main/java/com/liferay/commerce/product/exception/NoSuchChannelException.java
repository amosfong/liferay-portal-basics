/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Marco Leo
 */
public class NoSuchChannelException extends NoSuchModelException {

	public NoSuchChannelException() {
	}

	public NoSuchChannelException(String msg) {
		super(msg);
	}

	public NoSuchChannelException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchChannelException(Throwable throwable) {
		super(throwable);
	}

}