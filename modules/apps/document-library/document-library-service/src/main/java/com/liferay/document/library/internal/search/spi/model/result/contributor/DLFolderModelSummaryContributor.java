/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.search.spi.model.result.contributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;

/**
 * @author Michael C. Han
 */
public class DLFolderModelSummaryContributor
	implements ModelSummaryContributor {

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String title = document.get(prefix + Field.TITLE, Field.TITLE);
		String content = document.get(
			prefix + Field.DESCRIPTION, Field.DESCRIPTION);

		Summary summary = new Summary(title, content);

		summary.setMaxContentLength(200);

		return summary;
	}

}