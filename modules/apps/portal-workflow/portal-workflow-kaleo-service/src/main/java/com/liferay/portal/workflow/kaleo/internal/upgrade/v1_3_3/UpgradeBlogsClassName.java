/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_3;

import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.upgrade.util.Table;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_0.BaseUpgradeClassNames;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Leonardo Barros
 */
public class UpgradeBlogsClassName extends BaseUpgradeClassNames {

	@Override
	protected String getWhereClause() {
		return " where workflowContext like " +
			"'%com.liferay.blogs.kernel.model.BlogsEntry%'";
	}

	@Override
	protected void updateClassName(String tableName, String columnName) {
		try (LoggingTimer loggingTimer = new LoggingTimer(tableName)) {
			Table table = new Table(tableName);

			table.updateColumnValue(
				columnName, "com.liferay.blogs.kernel.model.BlogsEntry",
				"com.liferay.blogs.model.BlogsEntry");
		}
	}

	@Override
	protected Map<String, Serializable> updateWorkflowContext(
		String workflowContextJSON) {

		Map<String, Serializable> workflowContext = WorkflowContextUtil.convert(
			workflowContextJSON);

		workflowContext.put(
			"entryClassName", "com.liferay.blogs.model.BlogsEntry");

		return workflowContext;
	}

}