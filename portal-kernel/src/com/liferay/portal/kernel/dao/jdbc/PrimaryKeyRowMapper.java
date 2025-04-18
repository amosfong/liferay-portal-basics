/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Shuyang Zhou
 */
public class PrimaryKeyRowMapper implements RowMapper<Long> {

	@Override
	public Long mapRow(ResultSet resultSet) throws SQLException {
		return resultSet.getLong(1);
	}

}