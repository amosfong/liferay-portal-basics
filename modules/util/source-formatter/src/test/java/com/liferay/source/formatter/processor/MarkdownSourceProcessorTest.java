/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.processor;

import org.junit.Test;

/**
 * @author Alan Huang
 */
public class MarkdownSourceProcessorTest extends BaseSourceProcessorTestCase {

	@Test
	public void testIncorrectCodeBlocks() throws Exception {
		test("IncorrectCodeBlocks.testmarkdown");
	}

	@Test
	public void testIncorrectNumberedList() throws Exception {
		test("IncorrectNumberedList.testmarkdown");
	}

	@Test
	public void testIncorrectWhitespace() throws Exception {
		test("IncorrectWhitespace.testmarkdown");
	}

	@Test
	public void testMissingEmptyLines() throws Exception {
		test("MissingEmptyLines.testmarkdown");
	}

}