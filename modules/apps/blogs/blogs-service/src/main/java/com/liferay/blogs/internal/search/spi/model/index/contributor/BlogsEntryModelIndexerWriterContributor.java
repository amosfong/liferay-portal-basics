/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.internal.search.spi.model.index.contributor;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.IndexerWriterMode;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

/**
 * @author Luan Maoski
 */
public class BlogsEntryModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<BlogsEntry> {

	public BlogsEntryModelIndexerWriterContributor(
		BlogsEntryLocalService blogsEntryLocalService,
		DynamicQueryBatchIndexingActionableFactory
			dynamicQueryBatchIndexingActionableFactory) {

		_blogsEntryLocalService = blogsEntryLocalService;
		_dynamicQueryBatchIndexingActionableFactory =
			dynamicQueryBatchIndexingActionableFactory;
	}

	@Override
	public void customize(
		BatchIndexingActionable batchIndexingActionable,
		ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod(
			(BlogsEntry blogsEntry) -> batchIndexingActionable.addDocuments(
				modelIndexerWriterDocumentHelper.getDocument(blogsEntry)));
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return _dynamicQueryBatchIndexingActionableFactory.
			getBatchIndexingActionable(
				_blogsEntryLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(BlogsEntry blogsEntry) {
		return blogsEntry.getCompanyId();
	}

	@Override
	public IndexerWriterMode getIndexerWriterMode(BlogsEntry blogsEntry) {
		if (blogsEntry.isApproved() || blogsEntry.isDraft() ||
			blogsEntry.isInTrash() || blogsEntry.isPending() ||
			blogsEntry.isScheduled()) {

			return IndexerWriterMode.UPDATE;
		}

		if (!blogsEntry.isApproved() && !blogsEntry.isInTrash()) {
			return IndexerWriterMode.SKIP;
		}

		return IndexerWriterMode.DELETE;
	}

	private final BlogsEntryLocalService _blogsEntryLocalService;
	private final DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

}