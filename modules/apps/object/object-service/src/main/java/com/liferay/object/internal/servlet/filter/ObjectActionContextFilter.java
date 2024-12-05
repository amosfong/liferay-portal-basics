/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.servlet.filter;

import com.liferay.object.action.util.ObjectActionThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pedro Tavares
 */
@Component(
	property = {
		"servlet-context-name=",
		"servlet-filter-name=Object Action Context Filter", "url-pattern=/o/c/*"
	},
	service = Filter.class
)
public class ObjectActionContextFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws Exception {

		ObjectActionThreadLocal.setHttpServletRequest(httpServletRequest);

		try {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
		finally {
			ObjectActionThreadLocal.setHttpServletRequest(null);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectActionContextFilter.class);

}