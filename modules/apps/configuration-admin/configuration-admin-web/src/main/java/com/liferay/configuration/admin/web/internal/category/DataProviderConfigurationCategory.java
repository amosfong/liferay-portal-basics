/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.configuration.admin.web.internal.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Yuxing Wu
 */
@Component(service = ConfigurationCategory.class)
public class DataProviderConfigurationCategory
	implements ConfigurationCategory {

	@Override
	public String getCategoryKey() {
		return "data-providers";
	}

	@Override
	public String getCategorySection() {
		return "content-and-data";
	}

}