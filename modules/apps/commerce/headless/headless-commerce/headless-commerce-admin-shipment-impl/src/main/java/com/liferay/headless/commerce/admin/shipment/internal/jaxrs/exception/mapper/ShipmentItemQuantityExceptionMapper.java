/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.shipment.internal.jaxrs.exception.mapper;

import com.liferay.commerce.exception.CommerceShipmentItemQuantityException;
import com.liferay.headless.commerce.core.exception.mapper.BaseExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Andrea Sbarra
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Shipment)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Shipment.ShipmentItemQuantityExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class ShipmentItemQuantityExceptionMapper
	extends BaseExceptionMapper<CommerceShipmentItemQuantityException> {

	@Override
	public String getErrorDescription() {
		return "Invalid item quantity";
	}

	@Override
	public Response.Status getStatus() {
		return Response.Status.BAD_REQUEST;
	}

}