/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.qualifier.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Riccardo Alberti
 */
@ExtendedObjectClassDefinition(
	category = "qualifiers", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.commerce.qualifier.configuration.AddressCommerceQualifierConfiguration",
	localization = "content/Language",
	name = "address-commerce-qualifier-configuration-name"
)
public interface AddressCommerceQualifierConfiguration
	extends CommerceQualifierConfiguration {

	@Meta.AD(name = "allowed-target-keys", required = false)
	@Override
	public String[] allowedTargetKeys();

	@Meta.AD(name = "order-by-target-keys", required = false)
	@Override
	public String[] orderByTargetKeys();

}