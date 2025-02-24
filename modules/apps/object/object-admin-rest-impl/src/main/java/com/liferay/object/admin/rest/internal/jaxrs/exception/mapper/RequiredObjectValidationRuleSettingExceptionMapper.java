/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.admin.rest.internal.jaxrs.exception.mapper;

import com.liferay.object.exception.RequiredObjectValidationRuleSettingException;
import com.liferay.object.jaxrs.exception.mapper.util.ObjectExceptionMapperUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mateus Santana
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Object.Admin.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Object.Admin.REST.RequiredObjectValidationRuleSettingExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class RequiredObjectValidationRuleSettingExceptionMapper
	extends BaseExceptionMapper<RequiredObjectValidationRuleSettingException> {

	@Override
	protected Problem getProblem(
		RequiredObjectValidationRuleSettingException
			requiredObjectValidationRuleSettingException) {

		return new Problem(
			Response.Status.BAD_REQUEST,
			ObjectExceptionMapperUtil.getTitle(
				_acceptLanguage,
				requiredObjectValidationRuleSettingException.getArguments(),
				_language,
				requiredObjectValidationRuleSettingException.getMessage(),
				requiredObjectValidationRuleSettingException.getMessageKey()));
	}

	@Context
	private AcceptLanguage _acceptLanguage;

	@Reference
	private Language _language;

}