/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.json;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class JSONException extends PortalException {

	public JSONException() {
	}

	public JSONException(String msg) {
		super(msg);
	}

	public JSONException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public JSONException(Throwable throwable) {
		super(throwable);
	}

}