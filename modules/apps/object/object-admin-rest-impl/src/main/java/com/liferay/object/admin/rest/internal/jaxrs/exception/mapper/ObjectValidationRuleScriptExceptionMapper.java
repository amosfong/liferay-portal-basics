/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.admin.rest.internal.jaxrs.exception.mapper;

import com.liferay.object.exception.ObjectValidationRuleScriptException;
import com.liferay.portal.kernel.json.JSONUtil;
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
 * @author Carolina Barbosa
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Object.Admin.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Object.Admin.REST.ObjectValidationRuleScriptExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class ObjectValidationRuleScriptExceptionMapper
	extends BaseExceptionMapper<ObjectValidationRuleScriptException> {

	@Override
	protected Problem getProblem(
		ObjectValidationRuleScriptException
			objectValidationRuleScriptException) {

		return new Problem(
			JSONUtil.put(
				"fieldName", "script"
			).put(
				"message",
				_language.get(
					_acceptLanguage.getPreferredLocale(),
					objectValidationRuleScriptException.getMessageKey())
			).toString(),
			Response.Status.BAD_REQUEST, null,
			ObjectValidationRuleScriptException.class.getName());
	}

	@Context
	private AcceptLanguage _acceptLanguage;

	@Reference
	private Language _language;

}