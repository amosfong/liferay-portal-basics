/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.invitation.invite.members.internal.upgrade.v1_0_0;

import com.liferay.invitation.invite.members.internal.upgrade.v1_0_0.util.MemberRequestTable;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.upgrade.util.UpgradeTableFactoryUtil;

/**
 * @author Adolfo Pérez
 */
public class NamespaceUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_renameTable(
			_getOldTableName(), MemberRequestTable.TABLE_NAME,
			MemberRequestTable.TABLE_COLUMNS,
			MemberRequestTable.TABLE_SQL_CREATE,
			MemberRequestTable.TABLE_SQL_DROP);
	}

	private String _getOldTableName() {
		if (MemberRequestTable.TABLE_NAME.startsWith(_NEW_NAMESPACE)) {
			return StringUtil.replaceFirst(
				MemberRequestTable.TABLE_NAME, _NEW_NAMESPACE, _OLD_NAMESPACE);
		}

		return _OLD_NAMESPACE + MemberRequestTable.TABLE_NAME;
	}

	private void _renameTable(
			String oldTableName, String newTableName, Object[][] tableColumns,
			String tableSqlCreate, String tableSqlDrop)
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer(newTableName)) {
			boolean hasNewTable = hasTable(newTableName);

			if (hasNewTable && hasRows(newTableName)) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						StringBundler.concat(
							"Not renaming ", oldTableName, " to ", newTableName,
							" because ", newTableName, " has data"));
				}

				return;
			}

			boolean hasOldTable = hasTable(oldTableName);

			if (!hasNewTable && !hasOldTable) {
				runSQL(tableSqlCreate);

				return;
			}

			if (hasNewTable) {
				runSQL(tableSqlDrop);
			}

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				oldTableName, tableColumns);

			upgradeTable.setCreateSQL(tableSqlCreate);

			upgradeTable.updateTable();
		}
	}

	private static final String _NEW_NAMESPACE = "IM_";

	private static final String _OLD_NAMESPACE = "SO_";

	private static final Log _log = LogFactoryUtil.getLog(
		NamespaceUpgradeProcess.class);

}