/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression.internal.functions;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;

import java.math.BigDecimal;

/**
 * @author Rafael Praxedes
 */
public class SquareFunction
	implements DDMExpressionFunction.Function1<BigDecimal, BigDecimal> {

	@Override
	public BigDecimal apply(BigDecimal n) {
		return n.multiply(n);
	}

	@Override
	public String getName() {
		return "square";
	}

}