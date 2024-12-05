/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.exception.mapper;

import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.ws.rs.core.Response;

/**
 * @author Matija Petanjek
 */
public class SQLIntegrityConstraintViolationExceptionMapper
	extends BaseExceptionMapper<SQLIntegrityConstraintViolationException> {

	@Override
	protected Problem getProblem(
		SQLIntegrityConstraintViolationException
			sqlIntegrityConstraintViolationException) {

		return new Problem(
			Response.Status.BAD_REQUEST,
			"Database integrity constraint violation with error code: " +
				sqlIntegrityConstraintViolationException.getErrorCode());
	}

}