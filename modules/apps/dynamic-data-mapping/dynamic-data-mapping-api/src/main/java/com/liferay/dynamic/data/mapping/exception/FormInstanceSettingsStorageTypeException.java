/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class FormInstanceSettingsStorageTypeException extends PortalException {

	public FormInstanceSettingsStorageTypeException() {
	}

	public FormInstanceSettingsStorageTypeException(String msg) {
		super(msg);
	}

	public FormInstanceSettingsStorageTypeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public FormInstanceSettingsStorageTypeException(Throwable throwable) {
		super(throwable);
	}

}