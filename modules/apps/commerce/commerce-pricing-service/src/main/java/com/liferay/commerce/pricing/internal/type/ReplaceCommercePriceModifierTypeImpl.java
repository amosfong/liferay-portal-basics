/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.internal.type;

import com.liferay.commerce.pricing.constants.CommercePriceModifierConstants;
import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.type.CommercePriceModifierType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.math.BigDecimal;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = {
		"commerce.price.modifier.type.key=" + CommercePriceModifierConstants.MODIFIER_TYPE_REPLACE,
		"commerce.price.modifier.type.order:Integer=20"
	},
	service = CommercePriceModifierType.class
)
public class ReplaceCommercePriceModifierTypeImpl
	implements CommercePriceModifierType {

	@Override
	public BigDecimal evaluate(
			BigDecimal originalPrice,
			CommercePriceModifier commercePriceModifier)
		throws PortalException {

		BigDecimal amount = commercePriceModifier.getModifierAmount();

		BigDecimal modifiedPrice = amount;

		if (modifiedPrice.compareTo(BigDecimal.ZERO) < 0) {
			return originalPrice;
		}

		return modifiedPrice;
	}

	@Override
	public String getKey() {
		return CommercePriceModifierConstants.MODIFIER_TYPE_REPLACE;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return _language.get(
			resourceBundle,
			CommercePriceModifierConstants.MODIFIER_TYPE_REPLACE);
	}

	@Reference
	private Language _language;

}