/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.rule.internal.entry.type;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.rule.constants.COREntryConstants;
import com.liferay.commerce.order.rule.entry.type.COREntryType;
import com.liferay.commerce.order.rule.entry.type.COREntryTypeItem;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;

import java.math.BigDecimal;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"commerce.order.rule.entry.type.key=" + COREntryConstants.TYPE_PRODUCTS_LIMIT,
		"commerce.order.rule.entry.type.order:Integer=200"
	},
	service = COREntryType.class
)
public class ProductsLimitCOREntryTypeImpl implements COREntryType {

	@Override
	public boolean evaluate(COREntry corEntry, CommerceOrder commerceOrder) {
		BigDecimal totalQuantity = BigDecimal.ZERO;

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				corEntry.getTypeSettings()
			).build();

		List<Long> cProductIds = TransformUtil.transform(
			StringUtil.split(
				typeSettingsUnicodeProperties.getProperty(
					COREntryConstants.TYPE_PRODUCTS_LIMIT_FIELD_PRODUCT_IDS)),
			cProductId -> Long.valueOf(cProductId));

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			CPDefinition cpDefinition =
				_cpDefinitionLocalService.fetchCPDefinition(
					commerceOrderItem.getCPDefinitionId());

			if (cpDefinition == null) {
				continue;
			}

			if (cProductIds.contains(cpDefinition.getCProductId())) {
				totalQuantity = totalQuantity.add(
					commerceOrderItem.getQuantity());
			}
		}

		int compare = totalQuantity.compareTo(
			BigDecimal.valueOf(
				Double.valueOf(
					typeSettingsUnicodeProperties.getProperty(
						COREntryConstants.
							TYPE_PRODUCTS_LIMIT_FIELD_PRODUCT_QUANTITY))));

		if (compare > 0) {
			return false;
		}

		return true;
	}

	@Override
	public boolean evaluate(
		COREntry corEntry, List<COREntryTypeItem> corEntryTypeItems) {

		BigDecimal totalQuantity = BigDecimal.ZERO;

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				corEntry.getTypeSettings()
			).build();

		List<Long> cProductIds = TransformUtil.transform(
			StringUtil.split(
				typeSettingsUnicodeProperties.getProperty(
					COREntryConstants.TYPE_PRODUCTS_LIMIT_FIELD_PRODUCT_IDS)),
			cProductId -> Long.valueOf(cProductId));

		for (COREntryTypeItem corEntryTypeItem : corEntryTypeItems) {
			CPDefinition cpDefinition =
				_cpDefinitionLocalService.fetchCPDefinition(
					corEntryTypeItem.getCPDefinitionId());

			if (cpDefinition == null) {
				continue;
			}

			if (cProductIds.contains(cpDefinition.getCProductId())) {
				totalQuantity = totalQuantity.add(
					corEntryTypeItem.getQuantity());
			}
		}

		int compare = totalQuantity.compareTo(
			BigDecimal.valueOf(
				Double.valueOf(
					typeSettingsUnicodeProperties.getProperty(
						COREntryConstants.
							TYPE_PRODUCTS_LIMIT_FIELD_PRODUCT_QUANTITY))));

		if (compare > 0) {
			return false;
		}

		return true;
	}

	@Override
	public String getErrorMessage(
		COREntry corEntry, CommerceOrder commerceOrder, Locale locale) {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				corEntry.getTypeSettings()
			).build();

		BigDecimal quantity = BigDecimal.valueOf(
			Double.valueOf(
				typeSettingsUnicodeProperties.getProperty(
					COREntryConstants.
						TYPE_PRODUCTS_LIMIT_FIELD_PRODUCT_QUANTITY)));

		return _language.format(
			resourceBundle,
			"no-more-than-x-products-in-this-product-range-can-be-purchased-" +
				"together",
			quantity.stripTrailingZeros());
	}

	@Override
	public String getKey() {
		return COREntryConstants.TYPE_PRODUCTS_LIMIT;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return _language.get(resourceBundle, "products-limit");
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private Language _language;

}