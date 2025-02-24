/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.display.context;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pedro Leite
 */
public interface ObjectEntryDisplayContextFactory {

	public ObjectEntryDisplayContext create(
		HttpServletRequest httpServletRequest);

}