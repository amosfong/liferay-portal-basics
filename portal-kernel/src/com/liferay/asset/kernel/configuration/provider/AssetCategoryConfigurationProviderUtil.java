/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.configuration.provider;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.service.Snapshot;

/**
 * @author Lourdes Fernández Besada
 */
public class AssetCategoryConfigurationProviderUtil {

	public static boolean isSearchHierarchical(long companyId) {
		try {
			AssetCategoryConfigurationProvider
				assetCategoryConfigurationProvider =
					_assetCategoryConfigurationProviderSnapshot.get();

			return assetCategoryConfigurationProvider.isSearchHierarchical(
				companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get asset category configuration for company " +
					companyId,
				configurationException);
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetCategoryConfigurationProviderUtil.class);

	private static final Snapshot<AssetCategoryConfigurationProvider>
		_assetCategoryConfigurationProviderSnapshot = new Snapshot<>(
			AssetCategoryConfigurationProviderUtil.class,
			AssetCategoryConfigurationProvider.class);

}