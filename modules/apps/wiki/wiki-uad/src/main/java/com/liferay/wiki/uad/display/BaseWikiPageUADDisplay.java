/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.uad.display;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.user.associated.data.display.BaseModelUADDisplay;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalService;
import com.liferay.wiki.uad.constants.WikiUADConstants;

import java.io.Serializable;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the WikiPage UAD display.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom methods should be put in
 * {@link WikiPageUADDisplay}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseWikiPageUADDisplay
	extends BaseModelUADDisplay<WikiPage> {

	@Override
	public WikiPage get(Serializable primaryKey) throws PortalException {
		return wikiPageLocalService.getWikiPage(
			Long.valueOf(primaryKey.toString()));
	}

	@Override
	public String[] getDisplayFieldNames() {
		return new String[] {"title", "content", "summary"};
	}

	@Override
	public Class<WikiPage> getTypeClass() {
		return WikiPage.class;
	}

	@Override
	protected long doCount(DynamicQuery dynamicQuery) {
		return wikiPageLocalService.dynamicQueryCount(dynamicQuery);
	}

	@Override
	protected DynamicQuery doGetDynamicQuery() {
		return wikiPageLocalService.dynamicQuery();
	}

	@Override
	protected List<WikiPage> doGetRange(
		DynamicQuery dynamicQuery, int start, int end) {

		return wikiPageLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return WikiUADConstants.USER_ID_FIELD_NAMES_WIKI_PAGE;
	}

	@Reference
	protected WikiPageLocalService wikiPageLocalService;

}