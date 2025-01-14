/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.io;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class PathHolderTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testEquals() {
		PathHolder pathHolder = new PathHolder(Paths.get("testFile"));

		Assert.assertTrue(pathHolder.equals(pathHolder));
		Assert.assertFalse(pathHolder.equals(new Object()));
		Assert.assertTrue(
			pathHolder.equals(new PathHolder(Paths.get("testFile"))));
		Assert.assertFalse(
			pathHolder.equals(new PathHolder(Paths.get("anotherFile"))));
	}

	@Test
	public void testGetPath() {
		Path path = Paths.get("testFile");

		PathHolder pathHolder = new PathHolder(path);

		Assert.assertEquals(path, pathHolder.getPath());
	}

	@Test
	public void testHashCode() {
		Path path = Paths.get("testFile");

		String pathString = path.toString();

		PathHolder pathHolder = new PathHolder(path);

		Assert.assertEquals(pathString.hashCode(), pathHolder.hashCode());
	}

	@Test
	public void testToString() {
		Path path = Paths.get("testFile");

		PathHolder pathHolder = new PathHolder(path);

		String toString = pathHolder.toString();

		Assert.assertEquals(path.toString(), toString);
		Assert.assertEquals(
			ReflectionTestUtil.getFieldValue(pathHolder, "_pathString"),
			toString);
		Assert.assertSame(toString, path.toString());
	}

	@Test
	public void testToStringSwitchSeparator() {
		PathHolder pathHolder = new PathHolder(Paths.get(""));

		char foreignSeparatorChar = CharPool.SLASH;

		char separatorChar = File.separatorChar;

		if (separatorChar == CharPool.SLASH) {
			foreignSeparatorChar = CharPool.BACK_SLASH;
		}

		ReflectionTestUtil.setFieldValue(
			pathHolder, "_pathString",
			"testFolder" + foreignSeparatorChar + "testFile");
		ReflectionTestUtil.setFieldValue(
			pathHolder, "_separatorChar", foreignSeparatorChar);

		Assert.assertEquals(
			"testFolder" + separatorChar + "testFile", pathHolder.toString());
	}

}