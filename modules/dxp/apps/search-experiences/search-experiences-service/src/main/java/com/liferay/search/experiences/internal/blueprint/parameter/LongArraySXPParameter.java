/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.internal.blueprint.parameter;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class LongArraySXPParameter extends BaseSXPParameter {

	public LongArraySXPParameter(
		String name, boolean templateVariable, Long[] value) {

		super(name, templateVariable);

		_value = value;
	}

	@Override
	public boolean evaluateContains(Object value) {
		if (value instanceof Object[]) {
			for (Object object : (Object[])value) {
				if (ArrayUtil.contains(_value, GetterUtil.getLong(object))) {
					return true;
				}
			}

			return false;
		}

		return ArrayUtil.contains(_value, GetterUtil.getLong(value));
	}

	@Override
	public String evaluateToString(Map<String, String> options) {
		return Arrays.toString(_value);
	}

	@Override
	public Long[] getValue() {
		return _value;
	}

	private final Long[] _value;

}