/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression.model;

/**
 * @author Leonardo Barros
 */
public class OrExpression extends BinaryExpression {

	public OrExpression(
		Expression leftOperandExpression, Expression rightOperandExpression) {

		super("or", leftOperandExpression, rightOperandExpression);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}

}