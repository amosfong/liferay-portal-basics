/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_4_x;

import com.liferay.portal.kernel.upgrade.util.UpgradeModulesFactory;
import com.liferay.portal.kernel.upgrade.util.UpgradeVersionTreeMap;
import com.liferay.portal.kernel.version.Version;
import com.liferay.portal.upgrade.util.PortalUpgradeProcessRegistry;

/**
 * @author Pei-Jung Lan
 */
public class PortalUpgradeProcessRegistryImpl
	implements PortalUpgradeProcessRegistry {

	@Override
	public void registerUpgradeProcesses(
		UpgradeVersionTreeMap upgradeVersionTreeMap) {

		upgradeVersionTreeMap.put(
			new Version(31, 12, 1),
			UpgradeModulesFactory.create(
				new String[] {"com.liferay.feature.flag.web"}, null));
	}

}