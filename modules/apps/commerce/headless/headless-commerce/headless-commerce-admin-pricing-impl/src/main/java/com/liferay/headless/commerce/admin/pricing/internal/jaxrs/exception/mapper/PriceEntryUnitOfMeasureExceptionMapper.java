/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.jaxrs.exception.mapper;

import com.liferay.commerce.price.list.exception.CommercePriceEntryUnitOfMeasureKeyException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Crescenzo Rega
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Pricing)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Pricing.PriceEntryUnitOfMeasureExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class PriceEntryUnitOfMeasureExceptionMapper
	extends BaseExceptionMapper<CommercePriceEntryUnitOfMeasureKeyException> {

	@Override
	protected Problem getProblem(
		CommercePriceEntryUnitOfMeasureKeyException
			commercePriceEntryUnitOfMeasureKeyException) {

		return new Problem(
			Response.Status.BAD_REQUEST,
			StringUtil.replace(
				commercePriceEntryUnitOfMeasureKeyException.getMessage(),
				"unit of measure key", "unit of measure"));
	}

}