/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upgrade;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Amos Fong
 * @author Brian Wing Shun Chan
 */
public abstract class BaseExternalReferenceCodeUpgradeProcess
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (String[] tableAndPrimaryKeyColumnName :
				getTableAndPrimaryKeyColumnNames()) {

			String tableName = tableAndPrimaryKeyColumnName[0];
			String primKeyColumnName = tableAndPrimaryKeyColumnName[1];

			upgradeExternalReferenceCode(tableName, primKeyColumnName);
		}
	}

	protected abstract String[][] getTableAndPrimaryKeyColumnNames();

	protected void upgradeExternalReferenceCode(
			String tableName, String primKeyColumnName)
		throws Exception {

		if (!hasTable(tableName)) {
			_log.error("Skip nonexistent table " + tableName);

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Upgrade table " + tableName);
		}

		if (!hasColumn(tableName, "externalReferenceCode")) {
			alterTableAddColumn(
				tableName, "externalReferenceCode", "VARCHAR(75)");
		}

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler selectSB = new StringBundler(7);

			selectSB.append("select ");
			selectSB.append(primKeyColumnName);

			boolean hasUuid = hasColumn(tableName, "uuid_");

			if (hasUuid) {
				selectSB.append(", uuid_");
			}

			selectSB.append(" from ");
			selectSB.append(tableName);
			selectSB.append(" where externalReferenceCode is null or ");
			selectSB.append("externalReferenceCode = ''");

			StringBundler updateSB = new StringBundler(5);

			updateSB.append("update ");
			updateSB.append(tableName);
			updateSB.append(" set externalReferenceCode = ? where ");
			updateSB.append(primKeyColumnName);
			updateSB.append(" = ?");

			try (PreparedStatement preparedStatement1 =
					connection.prepareStatement(selectSB.toString());
				ResultSet resultSet = preparedStatement1.executeQuery();
				PreparedStatement preparedStatement2 =
					AutoBatchPreparedStatementUtil.autoBatch(
						connection, updateSB.toString())) {

				while (resultSet.next()) {
					long primKey = resultSet.getLong(1);

					if (hasUuid) {
						String uuid = resultSet.getString(2);

						preparedStatement2.setString(1, uuid);
					}
					else {
						preparedStatement2.setString(
							1, String.valueOf(primKey));
					}

					preparedStatement2.setLong(2, primKey);

					preparedStatement2.addBatch();
				}

				preparedStatement2.executeBatch();
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseExternalReferenceCodeUpgradeProcess.class);

}