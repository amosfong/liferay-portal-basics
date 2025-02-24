/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.poshi.runner.exception;

/**
 * @author Kenji Heigel
 */
public class LiferayLogException extends PoshiRunnerWarningException {

	public LiferayLogException(String msg) {
		super(msg);
	}

	public LiferayLogException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}