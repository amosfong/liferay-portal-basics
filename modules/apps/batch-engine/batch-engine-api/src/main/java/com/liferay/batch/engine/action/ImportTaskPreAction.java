/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.action;

import com.liferay.batch.engine.context.ImportTaskContext;
import com.liferay.batch.engine.model.BatchEngineImportTask;

/**
 * @author Carlos Correa
 */
public interface ImportTaskPreAction {

	public void run(
			BatchEngineImportTask batchEngineImportTask,
			ImportTaskContext importTaskContext, Object item)
		throws Exception;

}