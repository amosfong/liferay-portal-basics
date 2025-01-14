/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.search.spi.model.index.contributor;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.IndexerWriterMode;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

/**
 * @author Brian I. Kim
 */
public class CPDefinitionModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<CPDefinition> {

	public CPDefinitionModelIndexerWriterContributor(
		CPDefinitionLocalService cpDefinitionLocalService,
		DynamicQueryBatchIndexingActionableFactory
			dynamicQueryBatchIndexingActionableFactory) {

		_cpDefinitionLocalService = cpDefinitionLocalService;
		_dynamicQueryBatchIndexingActionableFactory =
			dynamicQueryBatchIndexingActionableFactory;
	}

	@Override
	public void customize(
		BatchIndexingActionable batchIndexingActionable,
		ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod(
			(CPDefinition cpDefinition) -> batchIndexingActionable.addDocuments(
				modelIndexerWriterDocumentHelper.getDocument(cpDefinition)));
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return _dynamicQueryBatchIndexingActionableFactory.
			getBatchIndexingActionable(
				_cpDefinitionLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(CPDefinition cpDefinition) {
		return cpDefinition.getCompanyId();
	}

	@Override
	public IndexerWriterMode getIndexerWriterMode(CPDefinition cpDefinition) {
		return IndexerWriterMode.UPDATE;
	}

	private final CPDefinitionLocalService _cpDefinitionLocalService;
	private final DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

}