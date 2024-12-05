/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.payment.internal.jaxrs.exception.mapper;

import com.liferay.commerce.payment.exception.CommercePaymentEntryClassNameIdException;
import com.liferay.headless.commerce.core.exception.mapper.BaseExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Payment)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Payment.PaymentRelatedItemNameExceptionMapper"
	},
	service = ExceptionMapper.class
)
@Provider
public class PaymentRelatedItemNameExceptionMapper
	extends BaseExceptionMapper<CommercePaymentEntryClassNameIdException> {

	@Override
	public String getErrorDescription() {
		return "Payment related item name is invalid";
	}

	@Override
	public Response.Status getStatus() {
		return Response.Status.BAD_REQUEST;
	}

}