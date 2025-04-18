/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.change.tracking;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Pei-Jung Lan
 */
public class CTRequiredModelException extends SystemException {

	public CTRequiredModelException() {
	}

	public CTRequiredModelException(String msg) {
		super(msg);
	}

	public CTRequiredModelException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CTRequiredModelException(Throwable throwable) {
		super(throwable);
	}

}