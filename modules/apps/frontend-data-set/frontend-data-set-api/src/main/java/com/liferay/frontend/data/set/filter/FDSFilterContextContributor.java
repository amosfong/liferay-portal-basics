/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.data.set.filter;

import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public interface FDSFilterContextContributor {

	public Map<String, Object> getFDSFilterContext(
		FDSFilter fdsFilter, Locale locale);

}