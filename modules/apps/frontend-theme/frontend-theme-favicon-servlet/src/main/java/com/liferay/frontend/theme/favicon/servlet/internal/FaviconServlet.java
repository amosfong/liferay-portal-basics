/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.theme.favicon.servlet.internal;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Basto
 */
@Component(property = "servlet.type=favicon", service = Servlet.class)
public class FaviconServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (themeDisplay != null) {
			httpServletResponse.sendRedirect(themeDisplay.getFaviconURL());

			return;
		}

		LayoutSet layoutSet = (LayoutSet)httpServletRequest.getAttribute(
			WebKeys.VIRTUAL_HOST_LAYOUT_SET);

		if (layoutSet == null) {
			return;
		}

		String referer = GetterUtil.getString(
			httpServletRequest.getHeader(HttpHeaders.REFERER));

		if (Validator.isNotNull(referer)) {
			layoutSet = _getLayoutSet(layoutSet, referer);
		}

		String faviconURL = _getFaviconURL(layoutSet);

		if (Validator.isNotNull(faviconURL)) {
			httpServletResponse.sendRedirect(faviconURL);

			return;
		}

		Theme theme = layoutSet.getTheme();

		httpServletResponse.sendRedirect(
			theme.getContextPath() + theme.getImagesPath() + "/favicon.ico");
	}

	private String _getFaviconURL(LayoutSet layoutSet) {
		return layoutSet.getFaviconURL();
	}

	private LayoutSet _getLayoutSet(LayoutSet layoutSet, String path) {
		String groupFriendlyURLDomain = HttpComponentsUtil.getDomain(path);

		int pos = path.indexOf(groupFriendlyURLDomain);

		if (pos > 0) {
			pos = path.indexOf(
				CharPool.SLASH, pos + groupFriendlyURLDomain.length());
		}

		String groupFriendlyURL = path.substring(pos);

		if (groupFriendlyURL.startsWith(_PATH_DOCUMENTS)) {
			String fileEntryFriendlyURL = groupFriendlyURL.substring(
				_PATH_DOCUMENTS.length() - 1);

			groupFriendlyURL = fileEntryFriendlyURL.substring(
				0, fileEntryFriendlyURL.indexOf(CharPool.SLASH, 1));
		}
		else {
			pos = groupFriendlyURL.indexOf(CharPool.SLASH, 1);

			if (pos > 0) {
				groupFriendlyURL = groupFriendlyURL.substring(0, pos);
			}
		}

		Group group = _groupLocalService.fetchFriendlyURLGroup(
			layoutSet.getCompanyId(), groupFriendlyURL);

		if (group != null) {
			return group.getPublicLayoutSet();
		}

		return layoutSet;
	}

	private static final String _PATH_DOCUMENTS = "/documents/d/";

	private static final long serialVersionUID = 1L;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

}