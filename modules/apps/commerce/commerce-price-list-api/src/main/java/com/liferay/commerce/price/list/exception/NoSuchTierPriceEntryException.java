/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Alessio Antonio Rendina
 */
public class NoSuchTierPriceEntryException extends NoSuchModelException {

	public NoSuchTierPriceEntryException() {
	}

	public NoSuchTierPriceEntryException(String msg) {
		super(msg);
	}

	public NoSuchTierPriceEntryException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchTierPriceEntryException(Throwable throwable) {
		super(throwable);
	}

}