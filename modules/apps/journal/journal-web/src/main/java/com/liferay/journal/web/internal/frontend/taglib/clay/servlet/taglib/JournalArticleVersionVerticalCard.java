/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.frontend.taglib.clay.servlet.taglib;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.frontend.taglib.clay.servlet.taglib.BaseVerticalCard;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemListBuilder;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.web.internal.constants.JournalWebConstants;
import com.liferay.journal.web.internal.servlet.taglib.util.JournalArticleActionDropdownItemsProvider;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.trash.TrashHelper;

import java.util.Date;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class JournalArticleVersionVerticalCard extends BaseVerticalCard {

	public JournalArticleVersionVerticalCard(
		BaseModel<?> baseModel, RenderRequest renderRequest,
		RenderResponse renderResponse, RowChecker rowChecker,
		AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider,
		TrashHelper trashHelper) {

		super(baseModel, renderRequest, rowChecker);

		_renderResponse = renderResponse;
		_assetDisplayPageFriendlyURLProvider =
			assetDisplayPageFriendlyURLProvider;
		_trashHelper = trashHelper;

		_article = (JournalArticle)baseModel;
		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		JournalArticleActionDropdownItemsProvider
			articleActionDropdownItemsProvider =
				new JournalArticleActionDropdownItemsProvider(
					_article,
					PortalUtil.getLiferayPortletRequest(renderRequest),
					PortalUtil.getLiferayPortletResponse(_renderResponse),
					_assetDisplayPageFriendlyURLProvider, _trashHelper);

		try {
			return articleActionDropdownItemsProvider.
				getArticleVersionActionDropdownItems();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return null;
	}

	@Override
	public String getDefaultEventHandler() {
		return JournalWebConstants.JOURNAL_ELEMENTS_DEFAULT_EVENT_HANDLER;
	}

	@Override
	public String getIcon() {
		return "web-content";
	}

	@Override
	public String getImageSrc() {
		return HtmlUtil.escape(_article.getArticleImageURL(themeDisplay));
	}

	@Override
	public List<LabelItem> getLabels() {
		return LabelItemListBuilder.add(
			labelItem -> labelItem.setLabel(
				LanguageUtil.format(
					_httpServletRequest, "version-x",
					String.valueOf(_article.getVersion()), false))
		).add(
			labelItem -> labelItem.setStatus(_article.getStatus())
		).build();
	}

	@Override
	public String getSubtitle() {
		Date createDate = _article.getModifiedDate();

		String modifiedDateDescription = LanguageUtil.getTimeDescription(
			_httpServletRequest,
			System.currentTimeMillis() - createDate.getTime(), true);

		return LanguageUtil.format(
			_httpServletRequest, "modified-x-ago", modifiedDateDescription);
	}

	@Override
	public String getTitle() {
		return _article.getTitle(themeDisplay.getLocale());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticleVersionVerticalCard.class);

	private final JournalArticle _article;
	private final AssetDisplayPageFriendlyURLProvider
		_assetDisplayPageFriendlyURLProvider;
	private final HttpServletRequest _httpServletRequest;
	private final RenderResponse _renderResponse;
	private final TrashHelper _trashHelper;

}