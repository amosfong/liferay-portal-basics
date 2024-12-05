/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.configuration.admin.web.internal.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lourdes Fernández Besada
 */
@Component(service = ConfigurationCategory.class)
public class SEOConfigurationCategory implements ConfigurationCategory {

	@Override
	public String getCategoryIcon() {
		return "seo";
	}

	@Override
	public String getCategoryKey() {
		return "seo";
	}

	@Override
	public String getCategorySection() {
		return "platform";
	}

}