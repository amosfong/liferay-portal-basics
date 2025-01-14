/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.failure.message.generator;

import com.liferay.jenkins.results.parser.Dom4JUtil;

import java.io.IOException;

import org.dom4j.Element;

/**
 * @author Yi-Chen Tsai
 */
public class GradleTaskFailureMessageGenerator
	extends BaseFailureMessageGenerator {

	@Override
	public String getMessage(String consoleText) {
		Element errorMessageElement = getMessageElement(consoleText);

		if (errorMessageElement == null) {
			return null;
		}

		try {
			return Dom4JUtil.format(errorMessageElement);
		}
		catch (IOException ioException) {
			return ioException.getMessage();
		}
	}

	@Override
	public Element getMessageElement(String consoleText) {
		if (!consoleText.contains(_TOKEN_WHAT_WENT_WRONG)) {
			return null;
		}

		int start = consoleText.lastIndexOf(_TOKEN_WHAT_WENT_WRONG);

		int whereIndex = consoleText.lastIndexOf(_TOKEN_WHERE, start);

		if (whereIndex != -1) {
			start = whereIndex;
		}

		start = consoleText.lastIndexOf("\n", start);

		return getConsoleTextSnippetElementByStart(consoleText, start);
	}

	private static final String _TOKEN_WHAT_WENT_WRONG = "* What went wrong:";

	private static final String _TOKEN_WHERE = "* Where:";

}