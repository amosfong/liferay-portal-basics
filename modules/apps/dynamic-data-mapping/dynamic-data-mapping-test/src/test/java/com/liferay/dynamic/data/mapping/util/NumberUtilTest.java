/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.util;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Guilherme Camacho
 */
public class NumberUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetDecimalSeparatorIndex() {
		Assert.assertEquals(-1, NumberUtil.getDecimalSeparatorIndex(""));
		Assert.assertEquals(-1, NumberUtil.getDecimalSeparatorIndex("2"));
		Assert.assertEquals(1, NumberUtil.getDecimalSeparatorIndex("2,0"));
		Assert.assertEquals(1, NumberUtil.getDecimalSeparatorIndex("2.0"));
		Assert.assertEquals(1, NumberUtil.getDecimalSeparatorIndex("2٫0"));
	}

}