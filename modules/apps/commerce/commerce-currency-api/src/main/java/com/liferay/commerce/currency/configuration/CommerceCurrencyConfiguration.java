/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.currency.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "pricing", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.currency.configuration.CommerceCurrencyConfiguration",
	localization = "content/Language",
	name = "commerce-currency-configuration-name"
)
public interface CommerceCurrencyConfiguration {

	@Meta.AD(name = "default-exchange-rate-provider-key", required = false)
	public String defaultExchangeRateProviderKey();

	@Meta.AD(
		deflt = StringPool.FALSE, name = "enable-auto-update", required = false
	)
	public boolean enableAutoUpdate();

	@Meta.AD(
		deflt = "60", description = "update-interval-help", min = "1",
		name = "update-interval", required = false
	)
	public int updateInterval();

}