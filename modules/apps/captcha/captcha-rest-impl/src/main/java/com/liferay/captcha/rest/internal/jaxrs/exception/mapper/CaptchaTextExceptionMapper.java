/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.captcha.rest.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Drew Brokke
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Captcha.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Captcha.REST.CaptchaTextExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class CaptchaTextExceptionMapper
	extends BaseExceptionMapper<CaptchaTextException> {

	@Override
	protected Problem getProblem(CaptchaTextException captchaTextException) {
		return new Problem(captchaTextException);
	}

}