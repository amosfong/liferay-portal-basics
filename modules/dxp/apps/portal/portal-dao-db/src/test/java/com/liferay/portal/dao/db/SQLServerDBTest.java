/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.db;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.test.BaseDBTestCase;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Miguel Pastor
 * @author Alberto Chaparro
 */
public class SQLServerDBTest extends BaseDBTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetLongDefaultValue() {
		Assert.assertEquals("10", db.getDefaultValue("((10))"));
	}

	@Test
	public void testGetVarcharDefaultValue() {
		Assert.assertEquals("test", db.getDefaultValue("('test')"));
	}

	@Test
	public void testRewordAlterColumnType() throws Exception {
		Assert.assertEquals(
			"alter table DLFolder alter column userName nvarchar(75);\n",
			buildSQL("alter_column_type DLFolder userName VARCHAR(75);"));
	}

	@Test
	public void testRewordAlterColumnTypeBigDecimal() throws Exception {
		Assert.assertEquals(
			"alter table DLFolder alter column userId decimal(30, 16);\n",
			buildSQL("alter_column_type DLFolder userId BIGDECIMAL;"));
	}

	@Test
	public void testRewordAlterColumnTypeNoSemicolon() throws Exception {
		Assert.assertEquals(
			"alter table DLFolder alter column userName nvarchar(75);\n",
			buildSQL("alter_column_type DLFolder userName VARCHAR(75)"));
	}

	@Test
	public void testRewordAlterColumnTypeNotNull() throws Exception {
		Assert.assertEquals(
			"alter table DLFolder alter column userName nvarchar(75) not " +
				"null;\n",
			buildSQL(
				"alter_column_type DLFolder userName VARCHAR(75) not null;"));
	}

	@Test
	public void testRewordAlterColumnTypeNotNullUpperCase() throws Exception {
		Assert.assertEquals(
			"alter table DLFolder alter column userName nvarchar(75) not " +
				"null;\n",
			buildSQL(
				"alter_column_type DLFolder userName VARCHAR(75) NOT NULL;"));
	}

	@Test
	public void testRewordAlterColumnTypeNull() throws Exception {
		Assert.assertEquals(
			"alter table DLFolder alter column userName nvarchar(75) null;\n",
			buildSQL("alter_column_type DLFolder userName VARCHAR(75) null;"));
	}

	@Test
	public void testRewordAlterColumnTypeNullUpperCase() throws Exception {
		Assert.assertEquals(
			"alter table DLFolder alter column userName nvarchar(75) null;\n",
			buildSQL("alter_column_type DLFolder userName VARCHAR(75) NULL;"));
	}

	@Test
	public void testRewordAlterColumnTypeWithDefault() throws Exception {
		Assert.assertEquals(
			"alter table DLFolder alter column userName nvarchar(75) not " +
				"null;alter table DLFolder add constraint userName_default " +
					"default 'test test' for userName;\n",
			buildSQL(
				"alter_column_type DLFolder userName VARCHAR(75) default " +
					"'test test' not null;"));
	}

	@Test
	public void testRewordRenameTable() throws Exception {
		Assert.assertEquals(
			"exec sp_rename 'a', 'b';\n", buildSQL(RENAME_TABLE_QUERY));
	}

	@Override
	protected DB getDB() {
		return new SQLServerDB(0, 0);
	}

}