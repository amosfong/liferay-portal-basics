/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.internal.search.spi.model.result.contributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;

/**
 * @author Igor Fabiano Nazar
 * @author Luan Maoski
 */
public class BlogsEntryModelSummaryContributor
	implements ModelSummaryContributor {

	public BlogsEntryModelSummaryContributor(Localization localization) {
		_localization = localization;
	}

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		String languageId = LocaleUtil.toLanguageId(locale);

		return _createSummary(
			document, _localization.getLocalizedName(Field.CONTENT, languageId),
			_localization.getLocalizedName(Field.TITLE, languageId));
	}

	private Summary _createSummary(
		Document document, String contentField, String titleField) {

		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		Summary summary = new Summary(
			document.get(prefix + titleField, titleField),
			document.get(prefix + contentField, contentField));

		summary.setMaxContentLength(200);

		return summary;
	}

	private final Localization _localization;

}