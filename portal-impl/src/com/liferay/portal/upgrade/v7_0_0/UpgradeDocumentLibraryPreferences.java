/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.upgrade.BasePortletPreferencesUpgradeProcess;

import javax.portlet.PortletPreferences;

/**
 * @author Iván Zaera
 */
public class UpgradeDocumentLibraryPreferences
	extends BasePortletPreferencesUpgradeProcess {

	@Override
	protected String[] getPortletIds() {
		return new String[] {"20", "110%", "31%"};
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		upgradeMultiValuePreference(portletPreferences, "displayViews");
		upgradeMultiValuePreference(portletPreferences, "entryColumns");
		upgradeMultiValuePreference(portletPreferences, "folderColumns");
		upgradeMultiValuePreference(portletPreferences, "fileEntryColumns");
		upgradeMultiValuePreference(portletPreferences, "mimeTypes");

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}