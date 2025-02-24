/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.layout.set.prototype.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.instance.PortalInstancePool;
import com.liferay.portal.kernel.upgrade.BaseLocalizedColumnUpgradeProcess;
import com.liferay.portal.language.LanguageResources;

/**
 * @author Leon Chi
 */
public class UpgradeLocalizedColumn extends BaseLocalizedColumnUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		long[] companyIds = PortalInstancePool.getCompanyIds();

		upgradeLocalizedColumn(
			LanguageResources.PORTAL_RESOURCE_BUNDLE_LOADER,
			"LayoutSetPrototype", "name", _NAME,
			"layout-set-prototype-intranet-site-title", "Name", companyIds);

		upgradeLocalizedColumn(
			LanguageResources.PORTAL_RESOURCE_BUNDLE_LOADER,
			"LayoutSetPrototype", "description", _DESCRIPTION,
			"layout-set-prototype-intranet-site-description", "Description",
			companyIds);
	}

	private static final String _DESCRIPTION = "Site with Documents and News";

	private static final String _NAME =
		"<?xml version='1.0' encoding='UTF-8'?><root available-locales=" +
			"\"en_US\" default-locale=\"en_US\"><Name language-id=\"en_US\">" +
				"Intranet Site</Name></root>";

}