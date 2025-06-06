/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function.factory;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunctionFactory;
import com.liferay.dynamic.data.mapping.form.evaluator.internal.function.GetJSONValueFunction;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcos Martins
 */
@Component(
	property = "name=" + GetJSONValueFunction.NAME,
	service = DDMExpressionFunctionFactory.class
)
public class GetJSONValueFunctionFactory
	implements DDMExpressionFunctionFactory {

	@Override
	public DDMExpressionFunction create() {
		return new GetJSONValueFunction();
	}

}