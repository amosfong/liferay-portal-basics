/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.internal.upgrade.v15_0_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Marcos Martins
 */
public class UpgradeFaroProjectUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"select faroProjectId, createTime, subscription from " +
					"OSBFaro_FaroProject")) {

			try (ResultSet resultSet = preparedStatement.executeQuery();
				PreparedStatement updatePreparedStatement =
					AutoBatchPreparedStatementUtil.concurrentAutoBatch(
						connection,
						"update OSBFaro_FaroProject set subscription = ? " +
							"where faroProjectId = ?")) {

				while (resultSet.next()) {
					JSONObject subscriptionJSONObject =
						JSONFactoryUtil.createJSONObject(
							resultSet.getString("subscription"));

					long startDate = subscriptionJSONObject.getLong(
						"startDate", 0);

					if (startDate != 0) {
						continue;
					}

					subscriptionJSONObject.put(
						"startDate", resultSet.getLong("createTime"));

					updatePreparedStatement.setString(
						1, subscriptionJSONObject.toString());
					updatePreparedStatement.setLong(
						2, resultSet.getLong("faroProjectId"));

					updatePreparedStatement.addBatch();
				}

				updatePreparedStatement.executeBatch();
			}
		}
	}

}