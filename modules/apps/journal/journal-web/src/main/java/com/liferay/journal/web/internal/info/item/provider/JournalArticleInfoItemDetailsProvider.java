/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.info.item.provider;

import com.liferay.info.item.InfoItemClassDetails;
import com.liferay.info.item.InfoItemDetails;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.provider.InfoItemDetailsProvider;
import com.liferay.journal.model.JournalArticle;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

/**
 * @author Jürgen Kappler
 * @author Jorge Ferrer
 */
@Component(
	property = Constants.SERVICE_RANKING + ":Integer=10",
	service = InfoItemDetailsProvider.class
)
public class JournalArticleInfoItemDetailsProvider
	implements InfoItemDetailsProvider<JournalArticle> {

	@Override
	public InfoItemClassDetails getInfoItemClassDetails() {
		return new InfoItemClassDetails(JournalArticle.class.getName());
	}

	@Override
	public InfoItemDetails getInfoItemDetails(JournalArticle journalArticle) {
		return new InfoItemDetails(
			getInfoItemClassDetails(),
			new InfoItemReference(
				JournalArticle.class.getName(),
				journalArticle.getResourcePrimKey()));
	}

}