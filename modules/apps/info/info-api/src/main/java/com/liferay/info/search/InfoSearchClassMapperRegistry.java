/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.search;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Cristina González
 */
@ProviderType
public interface InfoSearchClassMapperRegistry {

	public String getClassName(String searchClassName);

	public String getSearchClassName(String className);

}