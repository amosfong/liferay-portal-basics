/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "orders", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.configuration.CommerceOrderTypeConfiguration",
	localization = "content/Language",
	name = "commerce-order-type-configuration-name"
)
public interface CommerceOrderTypeConfiguration {

	@Meta.AD(
		deflt = "15", description = "check-interval-in-minutes-description",
		min = "1", name = "check-interval", required = false
	)
	public int checkInterval();

}