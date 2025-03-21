/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.module.service.Snapshot;

/**
 * @author Michael C. Han
 */
public class IndexAdminHelperUtil {

	public static String backup(long companyId, String backupName)
		throws SearchException {

		IndexAdminHelper indexAdminHelper = _indexAdminHelperSnapshot.get();

		return indexAdminHelper.backup(companyId, backupName);
	}

	public static void backup(String backupName) throws SearchException {
		IndexAdminHelper indexAdminHelper = _indexAdminHelperSnapshot.get();

		indexAdminHelper.backup(backupName);
	}

	public static void removeBackup(long companyId, String backupName)
		throws SearchException {

		IndexAdminHelper indexAdminHelper = _indexAdminHelperSnapshot.get();

		indexAdminHelper.removeBackup(companyId, backupName);
	}

	public static void removeBackup(String backupName) throws SearchException {
		IndexAdminHelper indexAdminHelper = _indexAdminHelperSnapshot.get();

		indexAdminHelper.removeBackup(backupName);
	}

	public static void restore(long companyId, String backupName)
		throws SearchException {

		IndexAdminHelper indexAdminHelper = _indexAdminHelperSnapshot.get();

		indexAdminHelper.restore(companyId, backupName);
	}

	public static void restore(String backupName) throws SearchException {
		IndexAdminHelper indexAdminHelper = _indexAdminHelperSnapshot.get();

		indexAdminHelper.restore(backupName);
	}

	private static final Snapshot<IndexAdminHelper> _indexAdminHelperSnapshot =
		new Snapshot<>(IndexAdminHelperUtil.class, IndexAdminHelper.class);

}