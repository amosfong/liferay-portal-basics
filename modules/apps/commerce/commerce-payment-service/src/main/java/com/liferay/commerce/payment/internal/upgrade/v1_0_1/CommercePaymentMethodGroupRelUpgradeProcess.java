/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.internal.upgrade.v1_0_1;

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Marco Leo
 */
public class CommercePaymentMethodGroupRelUpgradeProcess
	extends UpgradeProcess {

	public CommercePaymentMethodGroupRelUpgradeProcess(
		ClassNameLocalService classNameLocalService,
		GroupLocalService groupLocalService) {

		_classNameLocalService = classNameLocalService;
		_groupLocalService = groupLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (Statement s = connection.createStatement();

			ResultSet resultSet = s.executeQuery(
				"select CPaymentMethodGroupRelId, groupId from " +
					"CommercePaymentMethodGroupRel");
			PreparedStatement preparedStatement =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update CommercePaymentMethodGroupRel set groupId = ? " +
						"where CPaymentMethodGroupRelId = ?")) {

			while (resultSet.next()) {
				long commerceChannelGroupId =
					_getCommerceChannelGroupIdBySiteGroupId(
						resultSet.getLong("groupId"));

				if (commerceChannelGroupId == 0) {
					continue;
				}

				preparedStatement.setLong(1, commerceChannelGroupId);
				preparedStatement.setLong(
					2, resultSet.getLong("CPaymentMethodGroupRelId"));

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		}
	}

	private long _getCommerceChannelGroupIdBySiteGroupId(long groupId)
		throws SQLException {

		long companyId = 0;
		long commerceChannelId = 0;

		String sql =
			"select * from CommerceChannel where siteGroupId = " + groupId;

		try (Statement s = connection.createStatement()) {
			s.setMaxRows(1);

			try (ResultSet resultSet = s.executeQuery(sql)) {
				if (resultSet.next()) {
					companyId = resultSet.getLong("companyId");
					commerceChannelId = resultSet.getLong("commerceChannelId");
				}
			}
		}

		Group group = _groupLocalService.fetchGroup(
			companyId,
			_classNameLocalService.getClassNameId(
				CommerceChannel.class.getName()),
			commerceChannelId);

		if (group != null) {
			return group.getGroupId();
		}

		return 0;
	}

	private final ClassNameLocalService _classNameLocalService;
	private final GroupLocalService _groupLocalService;

}