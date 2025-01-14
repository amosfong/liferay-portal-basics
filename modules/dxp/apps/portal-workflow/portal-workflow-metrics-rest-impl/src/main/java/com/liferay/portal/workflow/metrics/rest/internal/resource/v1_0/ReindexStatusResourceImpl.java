/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0;

import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskLocalService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistry;
import com.liferay.portal.kernel.backgroundtask.constants.BackgroundTaskConstants;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.ReindexStatus;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.ReindexStatusResource;
import com.liferay.portal.workflow.metrics.search.background.task.WorkflowMetricsBackgroundTaskExecutorNames;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Rafael Praxedes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/reindex-status.properties",
	scope = ServiceScope.PROTOTYPE, service = ReindexStatusResource.class
)
public class ReindexStatusResourceImpl extends BaseReindexStatusResourceImpl {

	@Override
	public Page<ReindexStatus> getReindexStatusesPage() throws Exception {
		return Page.of(
			transform(
				_backgroundTaskLocalService.getBackgroundTasks(
					WorkflowMetricsBackgroundTaskExecutorNames.
						WORKFLOW_METRICS_REINDEX_BACKGROUND_TASK_EXECUTOR,
					BackgroundTaskConstants.STATUS_IN_PROGRESS),
				this::_toReindexStatus));
	}

	private ReindexStatus _toReindexStatus(BackgroundTask backgroundTask) {
		BackgroundTaskStatus backgroundTaskStatus =
			_backgroundTaskStatusRegistry.getBackgroundTaskStatus(
				backgroundTask.getBackgroundTaskId());

		return new ReindexStatus() {
			{
				setCompletionPercentage(
					() -> MapUtil.getLong(
						backgroundTaskStatus.getAttributes(), "percentage"));
				setKey(
					() -> MapUtil.getString(
						backgroundTask.getTaskContextMap(),
						"workflow.metrics.index.key"));
			}
		};
	}

	@Reference
	private BackgroundTaskLocalService _backgroundTaskLocalService;

	@Reference
	private BackgroundTaskStatusRegistry _backgroundTaskStatusRegistry;

}