/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.model.impl;

import com.liferay.batch.engine.model.BatchEngineExportTask;
import com.liferay.batch.engine.service.BatchEngineExportTaskLocalServiceUtil;

/**
 * The extended model base implementation for the BatchEngineExportTask service. Represents a row in the &quot;BatchEngineExportTask&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BatchEngineExportTaskImpl}.
 * </p>
 *
 * @author Shuyang Zhou
 * @see BatchEngineExportTaskImpl
 * @see BatchEngineExportTask
 * @generated
 */
public abstract class BatchEngineExportTaskBaseImpl
	extends BatchEngineExportTaskModelImpl implements BatchEngineExportTask {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a batch engine export task model instance should use the <code>BatchEngineExportTask</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			BatchEngineExportTaskLocalServiceUtil.addBatchEngineExportTask(
				this);
		}
		else {
			BatchEngineExportTaskLocalServiceUtil.updateBatchEngineExportTask(
				this);
		}
	}

}