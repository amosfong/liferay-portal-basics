/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuth2ApplicationHomePageURLSchemeException
	extends PortalException {

	public OAuth2ApplicationHomePageURLSchemeException() {
	}

	public OAuth2ApplicationHomePageURLSchemeException(String msg) {
		super(msg);
	}

	public OAuth2ApplicationHomePageURLSchemeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public OAuth2ApplicationHomePageURLSchemeException(Throwable throwable) {
		super(throwable);
	}

}