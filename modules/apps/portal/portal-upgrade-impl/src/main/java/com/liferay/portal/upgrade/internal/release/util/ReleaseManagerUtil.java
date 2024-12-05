/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.internal.release.util;

import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.upgrade.internal.executor.UpgradeExecutor;
import com.liferay.portal.upgrade.internal.graph.ReleaseGraphManager;
import com.liferay.portal.upgrade.internal.registry.UpgradeInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Joao Victor Alves
 */
public class ReleaseManagerUtil {

	public static String getSchemaVersionString(Release release) {
		if ((release != null) &&
			Validator.isNotNull(release.getSchemaVersion())) {

			return release.getSchemaVersion();
		}

		return "0.0.0";
	}

	public static Set<String> getUpgradableBundleSymbolicNames(
		Set<String> bundleSymbolicNames,
		ReleaseLocalService releaseLocalService,
		UpgradeExecutor upgradeExecutor) {

		Set<String> upgradableBundleSymbolicNames = new HashSet<>();

		for (String bundleSymbolicName : bundleSymbolicNames) {
			if (isUpgradable(
					bundleSymbolicName, releaseLocalService, upgradeExecutor)) {

				upgradableBundleSymbolicNames.add(bundleSymbolicName);
			}
		}

		return upgradableBundleSymbolicNames;
	}

	public static boolean isUpgradable(
		String bundleSymbolicName, ReleaseLocalService releaseLocalService,
		UpgradeExecutor upgradeExecutor) {

		ReleaseGraphManager releaseGraphManager = new ReleaseGraphManager(
			upgradeExecutor.getUpgradeInfos(bundleSymbolicName));

		List<List<UpgradeInfo>> upgradeInfosList =
			releaseGraphManager.getUpgradeInfosList(
				getSchemaVersionString(
					releaseLocalService.fetchRelease(bundleSymbolicName)));

		if (upgradeInfosList.size() == 1) {
			return true;
		}

		return false;
	}

}