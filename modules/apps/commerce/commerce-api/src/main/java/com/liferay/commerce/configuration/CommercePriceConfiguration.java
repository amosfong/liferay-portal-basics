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
@ExtendedObjectClassDefinition(category = "pricing")
@Meta.OCD(
	id = "com.liferay.commerce.configuration.CommercePriceConfiguration",
	localization = "content/Language", name = "price-configuration-name"
)
public interface CommercePriceConfiguration {

	@Meta.AD(
		deflt = "false", name = "display-discount-levels", required = false
	)
	public boolean displayDiscountLevels();

}