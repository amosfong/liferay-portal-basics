/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shipment.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.shipment.web.internal.constants.CommerceShipmentScreenNavigationConstants;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.portal.kernel.language.Language;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	property = "screen.navigation.category.order:Integer=10",
	service = ScreenNavigationCategory.class
)
public class CommerceShipmentShippingSummaryScreenNavigationCategory
	implements ScreenNavigationCategory {

	@Override
	public String getCategoryKey() {
		return CommerceShipmentScreenNavigationConstants.
			CATEGORY_KEY_SHIPPING_SUMMARY;
	}

	@Override
	public String getLabel(Locale locale) {
		return language.get(locale, getCategoryKey());
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceShipmentScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_SHIPMENT_GENERAL;
	}

	@Reference
	protected Language language;

}