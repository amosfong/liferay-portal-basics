/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v2_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class KaleoTaskTable {

	public static final String TABLE_NAME = "KaleoTask";

	public static final Object[][] TABLE_COLUMNS = {
		{"kaleoTaskId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoNodeId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"description", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("kaleoTaskId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("kaleoNodeId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE = "create table KaleoTask (kaleoTaskId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionVersionId LONG,kaleoNodeId LONG,name VARCHAR(200) null,description STRING null)";

	public static final String TABLE_SQL_DROP = "drop table KaleoTask";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_E1F8B23D on KaleoTask (companyId)",
		"create index IX_FECA871F on KaleoTask (kaleoDefinitionVersionId)",
		"create index IX_77B3F1A2 on KaleoTask (kaleoNodeId)"
	};

}