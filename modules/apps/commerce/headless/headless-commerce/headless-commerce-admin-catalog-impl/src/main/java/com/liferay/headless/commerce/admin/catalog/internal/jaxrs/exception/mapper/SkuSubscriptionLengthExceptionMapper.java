/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.jaxrs.exception.mapper;

import com.liferay.commerce.product.exception.CPInstanceSubscriptionLengthException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Crescenzo Rega
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Catalog)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Catalog.SkuSubscriptionLengthExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class SkuSubscriptionLengthExceptionMapper
	extends BaseExceptionMapper<CPInstanceSubscriptionLengthException> {

	@Override
	protected Problem getProblem(
		CPInstanceSubscriptionLengthException
			cpInstanceSubscriptionLengthException) {

		return new Problem(cpInstanceSubscriptionLengthException);
	}

}