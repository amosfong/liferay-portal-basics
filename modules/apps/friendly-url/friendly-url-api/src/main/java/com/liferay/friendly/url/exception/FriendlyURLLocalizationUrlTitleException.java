/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class FriendlyURLLocalizationUrlTitleException extends PortalException {

	public static class MustNotHaveTrailingSlash
		extends FriendlyURLLocalizationUrlTitleException {

		public MustNotHaveTrailingSlash() {
			super("The friendly URL must not have a trailing /");
		}

	}

	private FriendlyURLLocalizationUrlTitleException(String msg) {
		super(msg);
	}

}