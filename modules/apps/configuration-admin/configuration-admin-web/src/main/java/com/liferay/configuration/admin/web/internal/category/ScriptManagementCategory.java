/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.configuration.admin.web.internal.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Feliphe Marinho
 */
@Component(service = ConfigurationCategory.class)
public class ScriptManagementCategory implements ConfigurationCategory {

	@Override
	public String getCategoryIcon() {
		return "code";
	}

	@Override
	public String getCategoryKey() {
		return "script-management";
	}

	@Override
	public String getCategorySection() {
		return "security";
	}

}