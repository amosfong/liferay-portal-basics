/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.journal.web.internal.optimizer;

import com.liferay.adaptive.media.image.counter.AMImageCounter;
import com.liferay.adaptive.media.image.optimizer.AMImageOptimizer;
import com.liferay.adaptive.media.image.optimizer.BaseAMImageOptimizer;
import com.liferay.document.library.configuration.DLFileEntryConfigurationProvider;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mikel Lorza
 */
@Component(
	property = "adaptive.media.key=journal-article",
	service = AMImageOptimizer.class
)
public class JournalArticleAMImageOptimizer extends BaseAMImageOptimizer {

	@Override
	protected int countExpectedAMImageEntries(long companyId) {
		return _amImageCounter.countExpectedAMImageEntries(companyId);
	}

	@Override
	protected void forEachFileEntry(
			long companyId, Consumer<DLFileEntry> consumer)
		throws PortalException {

		_dlFileEntryLocalService.forEachFileEntry(
			companyId,
			_classNameLocalService.getClassNameId(
				JournalArticle.class.getName()),
			consumer,
			_dlFileEntryConfigurationProvider.
				getCompanyPreviewableProcessorMaxSize(companyId),
			getMimeTypes());
	}

	@Reference(target = "(adaptive.media.key=journal-article)")
	private AMImageCounter _amImageCounter;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DLFileEntryConfigurationProvider _dlFileEntryConfigurationProvider;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

}