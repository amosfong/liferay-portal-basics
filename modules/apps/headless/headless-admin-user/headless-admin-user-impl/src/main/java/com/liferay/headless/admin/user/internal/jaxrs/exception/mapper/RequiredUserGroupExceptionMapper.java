/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.exception.RequiredUserGroupException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Erick Monteiro
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Admin.User)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Admin.User.RequiredUserGroupExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class RequiredUserGroupExceptionMapper
	extends BaseExceptionMapper<RequiredUserGroupException> {

	@Override
	protected Problem getProblem(
		RequiredUserGroupException requiredUserGroupException) {

		return new Problem(
			Response.Status.FORBIDDEN,
			"Remove all users before deleting this user group");
	}

}