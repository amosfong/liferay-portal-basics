/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.contacts.internal.upgrade.v3_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class EntryTable {

	public static final String TABLE_NAME = "Contacts_Entry";

	public static final Object[][] TABLE_COLUMNS = {
		{"entryId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"fullName", Types.VARCHAR},
		{"emailAddress", Types.VARCHAR},
		{"comments", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("entryId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("fullName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("emailAddress", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("comments", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE = "create table Contacts_Entry (entryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,fullName VARCHAR(75) null,emailAddress VARCHAR(254) null,comments STRING null)";

	public static final String TABLE_SQL_DROP = "drop table Contacts_Entry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
	};

}