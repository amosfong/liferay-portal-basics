/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.scheduler;

import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import com.liferay.portal.workflow.metrics.internal.background.task.WorkflowMetricsSLAProcessBackgroundTaskHelper;
import com.liferay.portal.workflow.metrics.internal.configuration.WorkflowMetricsConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	configurationPid = "com.liferay.portal.workflow.metrics.internal.configuration.WorkflowMetricsConfiguration",
	service = SchedulerJobConfiguration.class
)
public class WorkflowMetricsSLAProcessSchedulerJobConfiguration
	implements SchedulerJobConfiguration {

	@Override
	public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
		return () ->
			_workflowMetricsSLAProcessBackgroundTaskHelper.addBackgroundTasks(
				false);
	}

	@Override
	public TriggerConfiguration getTriggerConfiguration() {
		return _triggerConfiguration;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		WorkflowMetricsConfiguration workflowMetricsConfiguration =
			ConfigurableUtil.createConfigurable(
				WorkflowMetricsConfiguration.class, properties);

		_triggerConfiguration = TriggerConfiguration.createTriggerConfiguration(
			workflowMetricsConfiguration.checkSLAJobInterval(),
			TimeUnit.MINUTE);
	}

	private TriggerConfiguration _triggerConfiguration;

	@Reference
	private WorkflowMetricsSLAProcessBackgroundTaskHelper
		_workflowMetricsSLAProcessBackgroundTaskHelper;

}