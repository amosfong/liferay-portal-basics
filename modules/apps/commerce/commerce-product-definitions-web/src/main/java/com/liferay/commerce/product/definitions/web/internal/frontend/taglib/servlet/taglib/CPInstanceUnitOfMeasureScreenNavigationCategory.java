/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.product.servlet.taglib.ui.constants.CPInstanceScreenNavigationConstants;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.portal.kernel.language.Language;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Crescenzo Rega
 */
@Component(
	property = "screen.navigation.category.order:Integer=15",
	service = ScreenNavigationCategory.class
)
public class CPInstanceUnitOfMeasureScreenNavigationCategory
	implements ScreenNavigationCategory {

	@Override
	public String getCategoryKey() {
		return CPInstanceScreenNavigationConstants.
			CATEGORY_KEY_UNITS_OF_MEASURE;
	}

	@Override
	public String getLabel(Locale locale) {
		return language.get(
			locale,
			CPInstanceScreenNavigationConstants.CATEGORY_KEY_UNITS_OF_MEASURE);
	}

	@Override
	public String getScreenNavigationKey() {
		return CPInstanceScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_CP_INSTANCE_GENERAL;
	}

	@Reference
	protected Language language;

}