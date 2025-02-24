/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.change.tracking.spi.reference;

import com.liferay.change.tracking.spi.reference.TableReferenceDefinition;
import com.liferay.change.tracking.spi.reference.builder.ChildTableReferenceInfoBuilder;
import com.liferay.change.tracking.spi.reference.builder.ParentTableReferenceInfoBuilder;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceTokenTable;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceTokenTable;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTimerInstanceTokenPersistence;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = TableReferenceDefinition.class)
public class KaleoTimerInstanceTokenTableReferenceDefinition
	implements TableReferenceDefinition<KaleoTimerInstanceTokenTable> {

	@Override
	public void defineChildTableReferences(
		ChildTableReferenceInfoBuilder<KaleoTimerInstanceTokenTable>
			childTableReferenceInfoBuilder) {
	}

	@Override
	public void defineParentTableReferences(
		ParentTableReferenceInfoBuilder<KaleoTimerInstanceTokenTable>
			parentTableReferenceInfoBuilder) {

		parentTableReferenceInfoBuilder.groupedModel(
			KaleoTimerInstanceTokenTable.INSTANCE
		).singleColumnReference(
			KaleoTimerInstanceTokenTable.INSTANCE.kaleoInstanceTokenId,
			KaleoInstanceTokenTable.INSTANCE.kaleoInstanceTokenId
		);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _kaleoTimerInstanceTokenPersistence;
	}

	@Override
	public KaleoTimerInstanceTokenTable getTable() {
		return KaleoTimerInstanceTokenTable.INSTANCE;
	}

	@Reference
	private KaleoTimerInstanceTokenPersistence
		_kaleoTimerInstanceTokenPersistence;

}