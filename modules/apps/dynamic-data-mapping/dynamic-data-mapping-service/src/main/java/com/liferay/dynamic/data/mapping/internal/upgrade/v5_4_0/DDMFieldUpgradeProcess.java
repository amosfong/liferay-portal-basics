/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v5_4_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Eudaldo Alonso
 */
public class DDMFieldUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement selectPreparedStatement =
				connection.prepareStatement(
					SQLTransformer.transform(
						"select ctCollectionId, fieldId, fieldName from " +
							"DDMField where LENGTH(fieldName) > ?"));
			PreparedStatement updatePreparedStatement =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMField set fieldName = ? where ctCollectionId " +
						"= ? and fieldId = ?")) {

			selectPreparedStatement.setInt(1, _MAX_LENGTH_FIELD_NAME);

			ResultSet resultSet = selectPreparedStatement.executeQuery();

			while (resultSet.next()) {
				String fieldName = resultSet.getString("fieldName");

				updatePreparedStatement.setString(
					1, StringUtil.shorten(fieldName, _MAX_LENGTH_FIELD_NAME));

				updatePreparedStatement.setLong(
					2, resultSet.getLong("ctCollectionId"));
				updatePreparedStatement.setLong(
					3, resultSet.getLong("fieldId"));

				updatePreparedStatement.addBatch();

				if (_log.isWarnEnabled()) {
					_log.warn(
						StringBundler.concat(
							"Truncated the ", fieldName, " value for field ID ",
							resultSet.getLong("fieldId"),
							" because it is too long"));
				}
			}

			updatePreparedStatement.executeBatch();
		}
	}

	@Override
	protected UpgradeStep[] getPostUpgradeSteps() {
		return new UpgradeStep[] {
			UpgradeProcessFactory.alterColumnType(
				"DDMField", "fieldName", "VARCHAR(255) null")
		};
	}

	private static final int _MAX_LENGTH_FIELD_NAME = 255;

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFieldUpgradeProcess.class);

}