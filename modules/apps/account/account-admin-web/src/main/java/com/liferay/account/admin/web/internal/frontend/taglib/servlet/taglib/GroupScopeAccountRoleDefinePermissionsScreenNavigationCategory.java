/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.admin.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.account.admin.web.internal.constants.AccountScreenNavigationEntryConstants;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Drew Brokke
 */
@Component(
	property = "screen.navigation.category.order:Integer=30",
	service = ScreenNavigationCategory.class
)
public class GroupScopeAccountRoleDefinePermissionsScreenNavigationCategory
	extends BaseAccountRoleDefinePermissionsScreenNavigationCategory {

	@Override
	public String getCategoryKey() {
		return AccountScreenNavigationEntryConstants.
			CATEGORY_KEY_DEFINE_GROUP_SCOPE_PERMISSIONS;
	}

}