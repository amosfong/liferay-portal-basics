/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.renderer;

import com.liferay.frontend.taglib.clay.servlet.taglib.IconTag;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * @author Eudaldo Alonso
 */
public class FragmentRendererUtil {

	public static void printPortletMessageInfo(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, String message) {

		try {
			PrintWriter printWriter = httpServletResponse.getWriter();

			StringBundler sb = new StringBundler(3);

			sb.append("<div class=\"portlet-msg-info\">");

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", themeDisplay.getLocale(),
				FragmentRendererUtil.class);

			sb.append(LanguageUtil.get(resourceBundle, message));

			sb.append("</div>");

			printWriter.write(sb.toString());
		}
		catch (IOException ioException) {
			if (_log.isDebugEnabled()) {
				_log.debug(ioException);
			}
		}
	}

	public static void printRestrictedContentMessage(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			PrintWriter printWriter = httpServletResponse.getWriter();

			printWriter.write(
				"<div class=\"alert alert-secondary align-items-baseline " +
					"bg-light d-flex\"><span class=\"alert-indicator " +
						"flex-shrink-0 mr-2\">");

			IconTag iconTag = new IconTag();

			iconTag.setCssClass("lexicon-icon lexicon-icon-password-policies");
			iconTag.setSymbol("password-policies");

			printWriter.write(
				iconTag.doTagAsString(httpServletRequest, httpServletResponse));

			printWriter.write("</span>");
			printWriter.write(
				LanguageUtil.get(
					httpServletRequest,
					"this-content-cannot-be-displayed-due-to-permission-" +
						"restrictions"));
			printWriter.write("</div>");
		}
		catch (IOException | JspException exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentRendererUtil.class);

}