/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.portal.instances.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Drew Brokke
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Portal.Instances)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Portal.Instances.UserEmailAddressExceptionMustValidateExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class UserEmailAddressExceptionMustValidateExceptionMapper
	extends BaseExceptionMapper<UserEmailAddressException.MustValidate> {

	@Override
	protected Problem getProblem(
		UserEmailAddressException.MustValidate mustValidate) {

		return new Problem(
			Response.Status.BAD_REQUEST, mustValidate.getMessage());
	}

}