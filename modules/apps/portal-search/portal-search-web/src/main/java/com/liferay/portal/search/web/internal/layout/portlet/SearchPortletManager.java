/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.layout.portlet;

import com.liferay.layout.portlet.PortletManager;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.search.web.constants.SearchPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Olivia Yu
 */
@Component(
	property = "javax.portlet.name=" + SearchPortletKeys.SEARCH,
	service = PortletManager.class
)
public class SearchPortletManager implements PortletManager {

	@Override
	public boolean isVisible(Layout layout) {
		if (FeatureFlagManagerUtil.isEnabled("LPD-13778")) {
			return true;
		}

		return false;
	}

}