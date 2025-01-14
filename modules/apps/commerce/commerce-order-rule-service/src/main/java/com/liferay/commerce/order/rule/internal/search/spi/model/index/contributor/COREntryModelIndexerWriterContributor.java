/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.rule.internal.search.spi.model.index.contributor;

import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.service.COREntryLocalService;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.IndexerWriterMode;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

/**
 * @author Alessio Antonio Rendina
 */
public class COREntryModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<COREntry> {

	public COREntryModelIndexerWriterContributor(
		COREntryLocalService corEntryLocalService,
		DynamicQueryBatchIndexingActionableFactory
			dynamicQueryBatchIndexingActionableFactory) {

		_corEntryLocalService = corEntryLocalService;
		_dynamicQueryBatchIndexingActionableFactory =
			dynamicQueryBatchIndexingActionableFactory;
	}

	@Override
	public void customize(
		BatchIndexingActionable batchIndexingActionable,
		ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod(
			(COREntry corEntry) -> batchIndexingActionable.addDocuments(
				modelIndexerWriterDocumentHelper.getDocument(corEntry)));
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return _dynamicQueryBatchIndexingActionableFactory.
			getBatchIndexingActionable(
				_corEntryLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(COREntry corEntry) {
		return corEntry.getCompanyId();
	}

	@Override
	public IndexerWriterMode getIndexerWriterMode(COREntry corEntry) {
		return IndexerWriterMode.UPDATE;
	}

	private final COREntryLocalService _corEntryLocalService;
	private final DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

}