/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

/**
 * @author Jeyvison Nascimento
 */
public class SetValidationFieldNameFunction
	extends BaseSetPropertyFunction<String> {

	public static final String NAME = "setValidationFieldName";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	protected String getPropertyName() {
		return "validationFieldName";
	}

}