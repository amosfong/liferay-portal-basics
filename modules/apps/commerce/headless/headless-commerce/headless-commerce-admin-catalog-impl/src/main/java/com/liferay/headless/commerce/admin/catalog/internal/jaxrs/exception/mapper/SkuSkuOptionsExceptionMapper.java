/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.jaxrs.exception.mapper;

import com.liferay.commerce.product.exception.CPInstanceJsonException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Catalog)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Catalog.SkuSkuOptionsExceptionMapper"
	},
	service = ExceptionMapper.class
)
@Provider
public class SkuSkuOptionsExceptionMapper
	extends BaseExceptionMapper<CPInstanceJsonException> {

	@Override
	protected Problem getProblem(
		CPInstanceJsonException cpInstanceJsonException) {

		return new Problem(cpInstanceJsonException);
	}

}