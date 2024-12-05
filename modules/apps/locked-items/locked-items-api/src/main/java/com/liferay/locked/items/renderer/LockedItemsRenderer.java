/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.locked.items.renderer;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Marco Galluzzi
 */
public interface LockedItemsRenderer {

	public String getDescription(Locale locale);

	public String getKey();

	public String getName(Locale locale);

	public default boolean isVisible() {
		return true;
	}

	public void render(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

}