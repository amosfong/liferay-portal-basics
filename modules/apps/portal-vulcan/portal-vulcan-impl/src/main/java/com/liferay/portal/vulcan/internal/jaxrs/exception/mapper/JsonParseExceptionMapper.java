/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.exception.mapper;

import com.fasterxml.jackson.core.JsonParseException;

import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;

/**
 * Converts any {@code JsonParseException} to a {@code 400} error.
 *
 * @author Alejandro Hernández
 * @review
 */
public class JsonParseExceptionMapper
	extends BaseExceptionMapper<JsonParseException> {

	@Override
	protected Problem getProblem(JsonParseException jsonParseException) {
		return new Problem(
			Response.Status.BAD_REQUEST, "Input is invalid JSON");
	}

}