/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "pricing", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.price.list.configuration.CommercePriceListConfiguration",
	localization = "content/Language",
	name = "commerce-price-lists-configuration-name"
)
public interface CommercePriceListConfiguration {

	@Meta.AD(
		deflt = "15", description = "check-interval-in-minutes-description",
		min = "1", name = "check-interval", required = false
	)
	public int checkInterval();

}