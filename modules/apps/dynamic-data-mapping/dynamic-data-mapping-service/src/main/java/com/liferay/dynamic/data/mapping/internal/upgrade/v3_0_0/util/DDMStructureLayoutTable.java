/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v3_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class DDMStructureLayoutTable {

	public static final String TABLE_NAME = "DDMStructureLayout";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"structureLayoutId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"structureVersionId", Types.BIGINT},
		{"definition", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("structureLayoutId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("structureVersionId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("definition", Types.CLOB);

}
	public static final String TABLE_SQL_CREATE = "create table DDMStructureLayout (uuid_ VARCHAR(75) null,structureLayoutId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,structureVersionId LONG,definition TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table DDMStructureLayout";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_B7158C0A on DDMStructureLayout (structureVersionId)",
		"create index IX_A90FF72A on DDMStructureLayout (uuid_[$COLUMN_LENGTH:75$], companyId)",
		"create unique index IX_C9A0402C on DDMStructureLayout (uuid_[$COLUMN_LENGTH:75$], groupId)"
	};

}