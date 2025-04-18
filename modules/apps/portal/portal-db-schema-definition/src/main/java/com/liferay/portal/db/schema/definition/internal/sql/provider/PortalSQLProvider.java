/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.db.schema.definition.internal.sql.provider;

import com.liferay.portal.kernel.dao.db.DBType;

/**
 * @author Mariano Álvaro Sáiz
 */
public class PortalSQLProvider extends BaseSQLProvider {

	public PortalSQLProvider(DBType dbType) throws Exception {
		super(dbType);
	}

	@Override
	public String getIndexesSQL() {
		return super.getIndexesSQL();
	}

	@Override
	public String getTablesSQL() {
		return super.getTablesSQL();
	}

}