/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.hibernate;

/**
 * @author Minhchau Dang
 */
public class SQLServer2005Dialect
	extends org.hibernate.dialect.SQLServer2005Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		return SQLServerLimitStringUtil.getLimitString(sql, offset, limit);
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}

	@Override
	public boolean supportsVariableLimit() {
		return false;
	}

}