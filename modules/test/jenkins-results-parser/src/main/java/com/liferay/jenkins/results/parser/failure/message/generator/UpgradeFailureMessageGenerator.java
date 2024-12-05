/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.failure.message.generator;

/**
 * @author Charlotte Wong
 */
public class UpgradeFailureMessageGenerator
	extends BaseFailureMessageGenerator {

	@Override
	public String getMessage(String consoleText) {
		if (!consoleText.contains(_LIFERAY_UPGRADE_ERROR_TEXT)) {
			return null;
		}

		int index = consoleText.indexOf(_LIFERAY_UPGRADE_ERROR_TEXT);

		index = consoleText.lastIndexOf("\n", index);

		return getConsoleTextSnippetByStart(consoleText, index);
	}

	private static final String _LIFERAY_UPGRADE_ERROR_TEXT =
		"Failed Liferay upgrade";

}