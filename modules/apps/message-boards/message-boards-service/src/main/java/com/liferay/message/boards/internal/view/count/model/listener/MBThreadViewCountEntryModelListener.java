/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.internal.view.count.model.listener;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBMessageLocalService;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.index.UpdateDocumentIndexWriter;
import com.liferay.portal.search.indexer.BaseModelDocumentFactory;
import com.liferay.view.count.model.ViewCountEntry;
import com.liferay.view.count.model.listener.ViewCountEntryModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Felipe Veloso
 */
@Component(service = ViewCountEntryModelListener.class)
public class MBThreadViewCountEntryModelListener
	implements ViewCountEntryModelListener {

	@Override
	public String getModelClassName() {
		return MBThread.class.getName();
	}

	@Override
	public void onAfterIncrement(ViewCountEntry viewCountEntry) {
		MBThread mbThread = _mbThreadLocalService.fetchMBThread(
			viewCountEntry.getClassPK());

		MBMessage mbMessage = _mbMessageLocalService.fetchMBMessage(
			mbThread.getRootMessageId());

		Document document = _baseModelDocumentFactory.createDocument(mbMessage);

		document.addNumber("viewCount", viewCountEntry.getViewCount());

		_updateDocumentIndexWriter.updateDocumentPartially(
			mbMessage.getCompanyId(), document, false);
	}

	@Reference
	private BaseModelDocumentFactory _baseModelDocumentFactory;

	@Reference
	private MBMessageLocalService _mbMessageLocalService;

	@Reference
	private MBThreadLocalService _mbThreadLocalService;

	@Reference
	private UpdateDocumentIndexWriter _updateDocumentIndexWriter;

}