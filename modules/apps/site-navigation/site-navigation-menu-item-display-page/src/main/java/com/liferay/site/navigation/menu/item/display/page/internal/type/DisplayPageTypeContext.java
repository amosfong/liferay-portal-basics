/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.menu.item.display.page.internal.type;

import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;

import java.util.Locale;

/**
 * @author Lourdes Fernández Besada
 */
public class DisplayPageTypeContext {

	public DisplayPageTypeContext(
		String className) {

		_className = className;
	}

	public String getClassName() {
		return _className;
	}

	public String getLabel(Locale locale) {
		return StringPool.BLANK;
	}

	public LayoutDisplayPageObjectProvider<?>
		getLayoutDisplayPageObjectProvider(long classPK) {

		return null;
	}

	public boolean isAvailable() {
		return false;
	}

	private final String _className;

}