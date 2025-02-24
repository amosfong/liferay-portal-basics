/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.notification.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.notification.web.internal.constants.CommerceNotificationScreenNavigationConstants;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Luca Pellizzon
 * @deprecated As of Cavanaugh (7.4.x)
 */
@Component(
	property = "screen.navigation.category.order:Integer=35",
	service = ScreenNavigationCategory.class
)
@Deprecated
public class CommerceChannelNotificationsTemplatesScreenNavigationCategory
	implements ScreenNavigationCategory {

	@Override
	public String getCategoryKey() {
		return CommerceNotificationScreenNavigationConstants.
			CATEGORY_KEY_COMMERCE_NOTIFICATION_TEMPLATES;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return language.get(resourceBundle, getCategoryKey());
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceNotificationScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_CHANNEL_GENERAL;
	}

	@Override
	public boolean isDeprecated() {
		return true;
	}

	@Reference
	protected Language language;

}