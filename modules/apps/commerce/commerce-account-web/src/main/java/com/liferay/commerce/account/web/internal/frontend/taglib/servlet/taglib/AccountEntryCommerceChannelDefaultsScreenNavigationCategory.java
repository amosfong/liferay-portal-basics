/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.account.web.internal.constants.AccountEntryScreenNavigationEntryConstants;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Sbarra
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "screen.navigation.category.order:Integer=70",
	service = ScreenNavigationCategory.class
)
public class AccountEntryCommerceChannelDefaultsScreenNavigationCategory
	implements ScreenNavigationCategory {

	@Override
	public String getCategoryKey() {
		return AccountEntryScreenNavigationEntryConstants.
			CATEGORY_KEY_COMMERCE_CHANNEL_DEFAULTS;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return language.get(resourceBundle, "channel-defaults");
	}

	@Override
	public String getScreenNavigationKey() {
		return AccountEntryScreenNavigationEntryConstants.
			SCREEN_NAVIGATION_KEY_ACCOUNT_ENTRY;
	}

	@Reference
	protected Language language;

}