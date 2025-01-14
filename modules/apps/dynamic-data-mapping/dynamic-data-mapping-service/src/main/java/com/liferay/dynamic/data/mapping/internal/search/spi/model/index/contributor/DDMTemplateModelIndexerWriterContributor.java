/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.search.spi.model.index.contributor;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

/**
 * @author Rafael Praxedes
 */
public class DDMTemplateModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<DDMTemplate> {

	public DDMTemplateModelIndexerWriterContributor(
		DDMTemplateLocalService ddmTemplateLocalService,
		DynamicQueryBatchIndexingActionableFactory
			dynamicQueryBatchIndexingActionableFactory) {

		_ddmTemplateLocalService = ddmTemplateLocalService;
		_dynamicQueryBatchIndexingActionableFactory =
			dynamicQueryBatchIndexingActionableFactory;
	}

	@Override
	public void customize(
		BatchIndexingActionable batchIndexingActionable,
		ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod(
			(DDMTemplate ddmTemplate) -> batchIndexingActionable.addDocuments(
				modelIndexerWriterDocumentHelper.getDocument(ddmTemplate)));
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return _dynamicQueryBatchIndexingActionableFactory.
			getBatchIndexingActionable(
				_ddmTemplateLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(DDMTemplate ddmTemplate) {
		return ddmTemplate.getCompanyId();
	}

	private final DDMTemplateLocalService _ddmTemplateLocalService;
	private final DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

}