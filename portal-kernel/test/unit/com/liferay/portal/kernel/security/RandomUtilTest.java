/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class RandomUtilTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testConstructor() {
		new RandomUtil();
	}

	@Test
	public void testNext() {
		RandomUtil.random = new PredictableRandom(_NUMBERS);

		for (int number : _NUMBERS) {
			Assert.assertEquals(number, RandomUtil.nextInt(10));
		}
	}

	@Test
	public void testNextInts() {
		int[] expectedResult = {6, 0, 1, 8, 4, 9, 3, 7, 2, 5};

		RandomUtil.random = new PredictableRandom(_NUMBERS);

		Assert.assertArrayEquals(expectedResult, RandomUtil.nextInts(10, 10));

		RandomUtil.random = new PredictableRandom(_NUMBERS);

		Assert.assertArrayEquals(expectedResult, RandomUtil.nextInts(10, 20));

		RandomUtil.random = new PredictableRandom(_NUMBERS);

		Assert.assertArrayEquals(
			new int[] {6, 0, 1, 8, 4}, RandomUtil.nextInts(10, 5));
	}

	@Test
	public void testShuffle() {
		RandomUtil.random = new PredictableRandom(_NUMBERS);

		String inputString = "abcdefghij";

		String shutffledString = RandomUtil.shuffle(inputString);

		char[] shutffledChars = shutffledString.toCharArray();

		Arrays.sort(shutffledChars);

		Assert.assertEquals(inputString, new String(shutffledChars));
	}

	private static final int[] _NUMBERS = {5, 2, 7, 3, 5, 4, 2, 1, 0, 0};

	private static class PredictableRandom extends Random {

		public PredictableRandom(int[] values) {
			_values = values;
		}

		@Override
		public int nextInt(int n) {
			int value = _values[_index++ % _values.length];

			if (value >= n) {
				throw new IllegalArgumentException(
					StringBundler.concat(
						"Value ", value, " is larger than ", n));
			}

			return value;
		}

		private int _index;
		private final int[] _values;

	}

}