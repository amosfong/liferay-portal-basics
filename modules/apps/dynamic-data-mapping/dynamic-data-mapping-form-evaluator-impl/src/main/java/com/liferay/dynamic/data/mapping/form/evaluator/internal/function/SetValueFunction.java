/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

/**
 * @author Leonardo Barros
 */
public class SetValueFunction extends BaseSetPropertyFunction<Object> {

	public static final String NAME = "setValue";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	protected String getPropertyName() {
		return "value";
	}

}