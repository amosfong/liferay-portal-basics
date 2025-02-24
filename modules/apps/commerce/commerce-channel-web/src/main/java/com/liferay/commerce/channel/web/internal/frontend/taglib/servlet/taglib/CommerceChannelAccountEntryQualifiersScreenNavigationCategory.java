/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.channel.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.channel.web.internal.constants.CommerceChannelScreenNavigationConstants;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Danny Situ
 */
@Component(
	property = "screen.navigation.category.order:Integer=90",
	service = ScreenNavigationCategory.class
)
public class CommerceChannelAccountEntryQualifiersScreenNavigationCategory
	implements ScreenNavigationCategory {

	@Override
	public String getCategoryKey() {
		return CommerceChannelScreenNavigationConstants.CATEGORY_KEY_QUALIFIERS;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return language.get(resourceBundle, "eligibility");
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceChannelScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_CHANNEL_GENERAL;
	}

	@Reference
	protected Language language;

}