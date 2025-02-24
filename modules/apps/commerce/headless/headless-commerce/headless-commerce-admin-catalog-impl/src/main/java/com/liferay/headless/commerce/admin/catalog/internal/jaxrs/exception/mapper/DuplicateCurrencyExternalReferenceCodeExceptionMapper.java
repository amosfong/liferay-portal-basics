/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.jaxrs.exception.mapper;

import com.liferay.commerce.currency.exception.DuplicateCommerceCurrencyExternalReferenceCodeException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;

/**
 * @author João Victor Cordeiro
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Catalog)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Catalog.DuplicateCurrencyExternalReferenceCodeExceptionMapper"
	},
	service = ExceptionMapper.class
)
@Provider
public class DuplicateCurrencyExternalReferenceCodeExceptionMapper
	extends BaseExceptionMapper
		<DuplicateCommerceCurrencyExternalReferenceCodeException> {

	@Override
	protected Problem getProblem(
		DuplicateCommerceCurrencyExternalReferenceCodeException
			duplicateCommerceCurrencyExternalReferenceCodeException) {

		return new Problem(
			Response.Status.CONFLICT,
			duplicateCommerceCurrencyExternalReferenceCodeException.
				getMessage());
	}

}