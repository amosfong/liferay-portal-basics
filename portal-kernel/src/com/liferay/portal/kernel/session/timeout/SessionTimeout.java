/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.session.timeout;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Iván Zaera Avellón
 */
public interface SessionTimeout {

	public int getAutoExtendOffset(HttpServletRequest httpServletRequest);

	public boolean isAutoExtend(HttpServletRequest httpServletRequest);

}