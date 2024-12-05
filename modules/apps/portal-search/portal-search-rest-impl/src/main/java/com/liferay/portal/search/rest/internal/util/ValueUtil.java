/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.rest.internal.util;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Petteri Karttunen
 */
public class ValueUtil {

	public static String[] toArray(String csv) {
		if (Validator.isBlank(csv)) {
			return new String[0];
		}

		csv = StringUtil.trim(csv);

		return csv.split("\\s*,\\s*");
	}

}