/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.info.item.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = "service.ranking:Integer=150", service = InfoItemRenderer.class
)
public class JournalArticleTitleInfoItemRenderer
	implements InfoItemRenderer<JournalArticle> {

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "title");
	}

	@Override
	public void render(
		JournalArticle article, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		if (!JournalArticleRendererUtil.isShowArticle(
				httpServletRequest, article)) {

			return;
		}

		try {
			httpServletRequest.setAttribute(WebKeys.JOURNAL_ARTICLE, article);

			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher(
					"/info/item/renderer/title.jsp");

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Reference
	private Language _language;

	@Reference(target = "(osgi.web.symbolicname=com.liferay.journal.web)")
	private ServletContext _servletContext;

}