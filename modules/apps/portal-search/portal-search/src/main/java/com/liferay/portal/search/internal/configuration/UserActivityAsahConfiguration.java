/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Gustavo Lima
 */
@ExtendedObjectClassDefinition(
	category = "search", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.portal.search.internal.configuration.UserActivityAsahConfiguration",
	localization = "content/Language",
	name = "asah-search-user-activity-configuration-name"
)
public interface UserActivityAsahConfiguration extends AsahConfiguration {

	@Meta.AD(deflt = "14400", name = "cache-timeout", required = false)
	@Override
	public int cacheTimeout();

}