/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Leonardo Barros
 */
public class MatchFunctionTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testInvalidRegex() {
		MatchFunction matchFunction = new MatchFunction();

		Boolean result = matchFunction.apply(
			"test@liferay", "\\\\S+[@\\S+\\.\\S+");

		Assert.assertFalse(result);
	}

	@Test
	public void testNotMatch() {
		MatchFunction matchFunction = new MatchFunction();

		Boolean result = matchFunction.apply(
			"test@liferay", "\\S+@\\S+\\.\\S+");

		Assert.assertFalse(result);
	}

	@Test
	public void testValidMatch() {
		MatchFunction matchFunction = new MatchFunction();

		Boolean result = matchFunction.apply(
			"test@liferay.com", "\\S+@\\S+\\.\\S+");

		Assert.assertTrue(result);
	}

}