/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.servlet;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.io.WriterOutputStream;
import com.liferay.portal.kernel.servlet.PipingServletResponse;
import com.liferay.portal.kernel.servlet.ServletOutputStreamAdapter;
import com.liferay.portal.kernel.util.ServerDetector;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * @author Shuyang Zhou
 */
public class PipingServletResponseFactory {

	public static HttpServletResponse createPipingServletResponse(
		PageContext pageContext) {

		HttpServletResponse httpServletResponse =
			(HttpServletResponse)pageContext.getResponse();

		JspWriter jspWriter = pageContext.getOut();

		if (!(pageContext instanceof PageContextWrapper) ||
			(jspWriter instanceof BodyContent)) {

			// This optimization cannot be applied to a page context with a
			// pushed body

			return new PipingServletResponse(httpServletResponse, jspWriter);
		}

		if (!ServerDetector.isTomcat()) {
			try {
				jspWriter.flush();
			}
			catch (IOException ioException) {
				ReflectionUtil.throwException(ioException);
			}
		}

		return httpServletResponse;
	}

}