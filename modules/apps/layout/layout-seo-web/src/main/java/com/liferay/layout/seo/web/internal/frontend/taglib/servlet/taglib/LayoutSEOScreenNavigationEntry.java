/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.layout.seo.web.internal.constants.LayoutSEOScreenNavigationEntryConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component(
	property = "screen.navigation.entry.order:Integer=1",
	service = ScreenNavigationEntry.class
)
public class LayoutSEOScreenNavigationEntry
	extends BaseLayoutScreenNavigationEntry {

	@Override
	public String getEntryKey() {
		return LayoutSEOScreenNavigationEntryConstants.ENTRY_KEY_SEO;
	}

	@Override
	public boolean isVisible(User user, Layout layout) {
		if (layout.isTypeAssetDisplay() || layout.isTypeUtility()) {
			return true;
		}

		return super.isVisible(user, layout);
	}

	@Override
	protected String getJspPath() {
		return "/layout/screen/navigation/entries/seo.jsp";
	}

}