/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.result.display.context.builder;

import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.web.internal.result.display.context.SearchResultContentDisplayContext;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public class SearchResultContentDisplayContextBuilder {

	public SearchResultContentDisplayContext build() throws Exception {
		SearchResultContentDisplayContext searchResultContentDisplayContext =
			new SearchResultContentDisplayContext();

		boolean visible = false;

		searchResultContentDisplayContext.setVisible(visible);

		return searchResultContentDisplayContext;
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setPermissionChecker(PermissionChecker permissionChecker) {
		_permissionChecker = permissionChecker;
	}

	public void setPortal(Portal portal) {
		_portal = portal;
	}

	public void setRenderRequest(RenderRequest renderRequest) {
		_renderRequest = renderRequest;
	}

	public void setRenderResponse(RenderResponse renderResponse) {
		_renderResponse = renderResponse;
	}

	public void setType(String type) {
		_type = type;
	}

	private Locale _locale;
	private PermissionChecker _permissionChecker;
	private Portal _portal;
	private RenderRequest _renderRequest;
	private RenderResponse _renderResponse;
	private String _type;

}