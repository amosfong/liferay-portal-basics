/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_2_x.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class UserGroupTable {

	public static final String TABLE_NAME = "UserGroup";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"externalReferenceCode", Types.VARCHAR}, {"userGroupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"parentUserGroupId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"addedByLDAPImport", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);

TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("userGroupId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("parentUserGroupId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("addedByLDAPImport", Types.BOOLEAN);

}
	public static final String TABLE_SQL_CREATE =
"create table UserGroup (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,userGroupId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentUserGroupId LONG,name VARCHAR(255) null,description STRING null,addedByLDAPImport BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table UserGroup";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_CB9015AF on UserGroup (companyId, externalReferenceCode[$COLUMN_LENGTH:75$])",
		"create unique index IX_23EAD0D on UserGroup (companyId, name[$COLUMN_LENGTH:255$])",
		"create index IX_69771487 on UserGroup (companyId, parentUserGroupId)",
		"create index IX_72394F8E on UserGroup (uuid_[$COLUMN_LENGTH:75$], companyId)"
	};

}