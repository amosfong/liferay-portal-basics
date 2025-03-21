/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.bundle.support.util;

/**
 * @author Andrea Di Giorgi
 */
public interface StreamLogger {

	public void onCompleted();

	public void onProgress(long completed, long length);

	public void onStarted();

}