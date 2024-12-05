/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ai.content.wizard.langchain4j.tools;

/**
 * @author Keven Leone
 * @author Brian Wing Shun Chan
 */
public class BaseTools {

	public BaseTools(ToolsContext toolsContext) {
		this.toolsContext = toolsContext;
	}

	protected ToolsContext toolsContext;

}