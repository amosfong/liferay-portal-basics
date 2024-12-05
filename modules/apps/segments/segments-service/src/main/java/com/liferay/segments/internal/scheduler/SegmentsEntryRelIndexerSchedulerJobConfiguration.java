/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.scheduler;

import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import com.liferay.segments.configuration.SegmentsConfiguration;
import com.liferay.segments.internal.constants.SegmentsDestinationNames;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.service.SegmentsEntryLocalService;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	configurationPid = "com.liferay.segments.configuration.SegmentsConfiguration",
	service = SchedulerJobConfiguration.class
)
public class SegmentsEntryRelIndexerSchedulerJobConfiguration
	implements SchedulerJobConfiguration {

	@Override
	public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
		return () -> {
			ActionableDynamicQuery actionableDynamicQuery =
				_segmentsEntryLocalService.getActionableDynamicQuery();

			actionableDynamicQuery.setAddCriteriaMethod(
				dynamicQuery -> {
					Property activeProperty = PropertyFactoryUtil.forName(
						"active");

					dynamicQuery.add(activeProperty.eq(true));
				});
			actionableDynamicQuery.setPerformActionMethod(
				(ActionableDynamicQuery.PerformActionMethod<SegmentsEntry>)
					this::_reindex);

			actionableDynamicQuery.performActions();
		};
	}

	@Override
	public TriggerConfiguration getTriggerConfiguration() {
		return _triggerConfiguration;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		SegmentsConfiguration segmentsConfiguration =
			ConfigurableUtil.createConfigurable(
				SegmentsConfiguration.class, properties);

		_triggerConfiguration = TriggerConfiguration.createTriggerConfiguration(
			segmentsConfiguration.segmentsPreviewCheckInterval(),
			TimeUnit.MINUTE);
	}

	private void _reindex(SegmentsEntry segmentsEntry) {
		Message message = new Message();

		message.put("companyId", segmentsEntry.getCompanyId());
		message.put("segmentsEntryId", segmentsEntry.getSegmentsEntryId());

		_messageBus.sendMessage(
			SegmentsDestinationNames.SEGMENTS_ENTRY_REINDEX, message);
	}

	@Reference
	private MessageBus _messageBus;

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

	private TriggerConfiguration _triggerConfiguration;

}