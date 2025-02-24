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
	id = "com.liferay.commerce.qualifier.configuration.CommerceDiscountCommerceQualifierConfiguration",
	localization = "content/Language",
	name = "commerce-discount-commerce-qualifier-configuration-name"
)
public interface CommerceDiscountCommerceQualifierConfiguration
	extends CommerceQualifierConfiguration {

	@Meta.AD(
		deflt = "account|account-group,channel,order-type",
		name = "allowed-target-keys", required = false
	)
	@Override
	public String[] allowedTargetKeys();

	@Meta.AD(
		deflt = "account|order-type|channel,account|order-type,account|channel,account,account-group|order-type|channel,account-group|order-type,account-group|channel,account-group,order-type|channel,order-type,channel",
		name = "order-by-target-keys", required = false
	)
	@Override
	public String[] orderByTargetKeys();

}