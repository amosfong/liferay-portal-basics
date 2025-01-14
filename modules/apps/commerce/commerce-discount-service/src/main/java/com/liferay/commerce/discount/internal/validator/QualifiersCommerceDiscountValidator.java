/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.validator;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.discount.validator.CommerceDiscountValidator;
import com.liferay.commerce.discount.validator.CommerceDiscountValidatorResult;
import com.liferay.commerce.util.CommerceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = {
		"commerce.discount.validator.key=" + QualifiersCommerceDiscountValidator.KEY,
		"commerce.discount.validator.priority:Integer=20",
		"commerce.discount.validator.type=" + CommerceDiscountConstants.VALIDATOR_TYPE_PRE_QUALIFICATION
	},
	service = CommerceDiscountValidator.class
)
public class QualifiersCommerceDiscountValidator
	implements CommerceDiscountValidator {

	public static final String KEY = "qualifiers";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public CommerceDiscountValidatorResult validate(
			CommerceContext commerceContext, CommerceDiscount commerceDiscount)
		throws PortalException {

		int validCommerceDiscountsCount =
			_commerceDiscountLocalService.getValidCommerceDiscountsCount(
				CommerceUtil.getCommerceAccountId(commerceContext),
				commerceContext.getCommerceAccountGroupIds(),
				commerceContext.getCommerceChannelId(),
				commerceDiscount.getCommerceDiscountId());

		if (validCommerceDiscountsCount == 0) {
			return new CommerceDiscountValidatorResult(
				commerceDiscount.getCommerceDiscountId(), false,
				"the-account-is-not-qualified-to-use-the-discount");
		}

		return new CommerceDiscountValidatorResult(true);
	}

	@Reference
	private CommerceDiscountLocalService _commerceDiscountLocalService;

}