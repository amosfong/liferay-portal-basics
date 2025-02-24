/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.change.tracking.spi.conflict;

import com.liferay.change.tracking.conflict.CTEntryConflictHelper;
import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.model.change.tracking.CTModel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tina Tian
 */
@Component(service = CTEntryConflictHelper.class)
public class JournalArticleCTEntryConflictHelper
	implements CTEntryConflictHelper {

	@Override
	public Class<? extends CTModel<?>> getModelClass() {
		return JournalArticle.class;
	}

	@Override
	public boolean hasDeletionModificationConflict(
		CTEntry ctEntry, long targetCTCollectionId) {

		if (ctEntry.getChangeType() != CTConstants.CT_CHANGE_TYPE_ADDITION) {
			return false;
		}

		JournalArticle journalArticle =
			_journalArticleLocalService.fetchJournalArticle(
				ctEntry.getModelClassPK());

		if (journalArticle == null) {
			return false;
		}

		BasePersistence<?> basePersistence =
			_journalArticleLocalService.getBasePersistence();

		Session session = basePersistence.getCurrentSession();

		session.evict(journalArticle);

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					targetCTCollectionId)) {

			JournalArticle latestJournalArticle =
				_journalArticleLocalService.fetchLatestArticle(
					journalArticle.getResourcePrimKey(),
					WorkflowConstants.STATUS_ANY, false);

			if ((latestJournalArticle != null) &&
				(latestJournalArticle.getStatus() ==
					WorkflowConstants.STATUS_IN_TRASH)) {

				return true;
			}

			return false;
		}
	}

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

}