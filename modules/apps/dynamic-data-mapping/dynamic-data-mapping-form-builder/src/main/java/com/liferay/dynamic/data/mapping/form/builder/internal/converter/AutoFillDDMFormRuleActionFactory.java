/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.converter;

import com.liferay.dynamic.data.mapping.expression.model.Expression;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.AutoFillDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.visitor.ActionExpressionVisitor;
import com.liferay.dynamic.data.mapping.spi.converter.model.SPIDDMFormRuleAction;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leonardo Barros
 */
public class AutoFillDDMFormRuleActionFactory {

	public static SPIDDMFormRuleAction create(
		List<Expression> expressions,
		ActionExpressionVisitor actionExpressionVisitor) {

		String ddmDataProviderInstanceUUID = actionExpressionVisitor.doVisit(
			expressions.get(0));
		String paramsExpression = actionExpressionVisitor.doVisit(
			expressions.get(1));
		String resultMapExpression = actionExpressionVisitor.doVisit(
			expressions.get(2));

		return new AutoFillDDMFormRuleAction(
			ddmDataProviderInstanceUUID,
			_createAutoFillInputParameters(paramsExpression),
			_createAutoFillOutputParameters(resultMapExpression));
	}

	private static Map<String, String> _createAutoFillInputParameters(
		String paramsExpression) {

		Map<String, String> map = new LinkedHashMap<>();

		if (Validator.isNull(paramsExpression)) {
			return map;
		}

		String[] innerExpressions = StringUtil.split(
			paramsExpression, CharPool.SEMICOLON);

		for (String innerExpression : innerExpressions) {
			String[] tokens = StringUtil.split(innerExpression, CharPool.EQUAL);

			if (!_isValidExpression(tokens)) {
				continue;
			}

			map.put(tokens[0], tokens[1]);
		}

		return map;
	}

	private static Map<String, String> _createAutoFillOutputParameters(
		String resultMapExpression) {

		Map<String, String> map = new LinkedHashMap<>();

		if (Validator.isNull(resultMapExpression)) {
			return map;
		}

		String[] innerExpressions = StringUtil.split(
			resultMapExpression, CharPool.SEMICOLON);

		for (String innerExpression : innerExpressions) {
			String[] tokens = StringUtil.split(innerExpression, CharPool.EQUAL);

			if (!_isValidExpression(tokens)) {
				continue;
			}

			map.put(tokens[1], tokens[0]);
		}

		return map;
	}

	private static boolean _isValidExpression(String[] tokens) {
		if ((tokens.length < 2) || Validator.isNull(tokens[0]) ||
			Validator.isNull(tokens[1])) {

			return false;
		}

		return true;
	}

}