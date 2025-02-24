/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.portal.instances.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.exception.UserScreenNameException;
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
		"osgi.jaxrs.name=Liferay.Headless.Portal.Instances.UserScreenNameExceptionMustNotBeNullExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class UserScreenNameExceptionMustNotBeNullExceptionMapper
	extends BaseExceptionMapper<UserScreenNameException.MustNotBeNull> {

	@Override
	protected Problem getProblem(
		UserScreenNameException.MustNotBeNull mustNotBeNull) {

		return new Problem(
			Response.Status.BAD_REQUEST,
			"Email address, first name, and last name are all required when " +
				"providing information for the default admin user");
	}

}