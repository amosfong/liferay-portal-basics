/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.language.rest.internal.jaxrs.exception.mapper;

import com.liferay.portal.language.override.exception.PLOEntryImportException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Thiago Buarque
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Portal.Language.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Portal.Language.REST.PLOEntryImportExceptionInvalidPropertiesFileExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class PLOEntryImportExceptionInvalidPropertiesFileExceptionMapper
	extends BaseExceptionMapper<PLOEntryImportException.InvalidPropertiesFile> {

	@Override
	protected Problem getProblem(
		PLOEntryImportException.InvalidPropertiesFile invalidPropertiesFile) {

		return new Problem(
			invalidPropertiesFile.getMessage(), Response.Status.BAD_REQUEST,
			null,
			PLOEntryImportException.InvalidPropertiesFile.class.getName());
	}

}