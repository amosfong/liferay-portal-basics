/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.internal.search;

import com.liferay.notification.internal.search.spi.model.index.contributor.NotificationQueueEntryModelIndexerWriterContributor;
import com.liferay.notification.model.NotificationQueueEntry;
import com.liferay.notification.service.NotificationQueueEntryLocalService;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchConfigurator;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Paulo Albuquerque
 */
@Component(service = ModelSearchConfigurator.class)
public class NotificationQueueEntryModelSearchConfigurator
	implements ModelSearchConfigurator<NotificationQueueEntry> {

	@Override
	public String getClassName() {
		return NotificationQueueEntry.class.getName();
	}

	@Override
	public String[] getDefaultSelectedFieldNames() {
		return new String[] {
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.UID, "fromName", "subject"
		};
	}

	@Override
	public ModelIndexerWriterContributor<NotificationQueueEntry>
		getModelIndexerWriterContributor() {

		return _modelIndexWriterContributor;
	}

	@Activate
	protected void activate() {
		_modelIndexWriterContributor =
			new NotificationQueueEntryModelIndexerWriterContributor(
				_dynamicQueryBatchIndexingActionableFactory,
				_notificationQueueEntryLocalService);
	}

	@Reference
	private DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

	private ModelIndexerWriterContributor<NotificationQueueEntry>
		_modelIndexWriterContributor;

	@Reference
	private NotificationQueueEntryLocalService
		_notificationQueueEntryLocalService;

}