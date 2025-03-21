/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.field.type;

/**
 * @author Rubén Pulido
 */
public class ActionInfoFieldType implements InfoFieldType {

	public static final ActionInfoFieldType INSTANCE =
		new ActionInfoFieldType();

	@Override
	public String getName() {
		return "action";
	}

	private ActionInfoFieldType() {
	}

}