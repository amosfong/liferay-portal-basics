/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.provider;

/**
 * @author Mikel Lorza
 */
public interface FriendlyURLSeparatorProvider {

	public String getFriendlyURLSeparator(long companyId, String key);

}