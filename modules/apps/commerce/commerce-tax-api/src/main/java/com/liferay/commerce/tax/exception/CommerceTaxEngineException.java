/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 */
public class CommerceTaxEngineException extends PortalException {

	public CommerceTaxEngineException() {
	}

	public CommerceTaxEngineException(String msg) {
		super(msg);
	}

	public CommerceTaxEngineException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CommerceTaxEngineException(Throwable throwable) {
		super(throwable);
	}

}