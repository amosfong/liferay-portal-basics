/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Sergio González
 */
public class UpgradeRepositoryEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alterColumnType("RepositoryEntry", "mappedId", "VARCHAR(255) null");
	}

}