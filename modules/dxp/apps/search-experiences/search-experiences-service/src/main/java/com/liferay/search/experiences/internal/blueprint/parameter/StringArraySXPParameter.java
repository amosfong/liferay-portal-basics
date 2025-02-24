/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.internal.blueprint.parameter;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author Petteri Karttunen
 */
public class StringArraySXPParameter extends BaseSXPParameter {

	public StringArraySXPParameter(
		String name, boolean templateVariable, String[] value) {

		super(name, templateVariable);

		_value = value;
	}

	@Override
	public boolean evaluateContains(Object value) {
		if (value instanceof Object[]) {
			for (Object object : (Object[])value) {
				if (ArrayUtil.contains(
						_value, GetterUtil.getString(object), true)) {

					return true;
				}
			}

			return false;
		}

		return ArrayUtil.contains(_value, GetterUtil.getString(value), true);
	}

	@Override
	public String evaluateToString(Map<String, String> options) {
		if (ArrayUtil.isEmpty(_value)) {
			return "[]";
		}

		return StringBundler.concat(
			"[\"", StringUtils.join(_value, "\", \""), "\"]");
	}

	@Override
	public String[] getValue() {
		return _value;
	}

	private final String[] _value;

}