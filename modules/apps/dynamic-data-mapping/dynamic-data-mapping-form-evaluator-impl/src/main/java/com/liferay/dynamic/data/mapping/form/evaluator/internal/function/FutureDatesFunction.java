/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.dynamic.data.mapping.form.validation.util.DateParameterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDateTime;

/**
 * @author Bruno Oliveira
 * @author Carolina Barbosa
 */
public class FutureDatesFunction
	implements DDMExpressionFunction.Function2<Object, Object, Boolean> {

	public static final String NAME = "futureDates";

	@Override
	public Boolean apply(Object object1, Object object2) {
		if (Validator.isNull(object1) || Validator.isNull(object2)) {
			return false;
		}

		LocalDateTime localDateTime = DateParameterUtil.getLocalDateTime(
			object1.toString());

		if (localDateTime.isBefore(
				DateParameterUtil.getLocalDateTime(object2.toString()))) {

			return false;
		}

		return true;
	}

	@Override
	public String getName() {
		return NAME;
	}

}