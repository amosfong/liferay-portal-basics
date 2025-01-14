/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.locked.layouts.web.internal.scheduler;

import com.liferay.layout.locked.layouts.web.internal.configuration.LockedLayoutsCompanyConfiguration;
import com.liferay.layout.manager.LayoutLockManager;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import com.liferay.portal.kernel.service.CompanyLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(service = SchedulerJobConfiguration.class)
public class UnlockLayoutsSchedulerJobConfiguration
	implements SchedulerJobConfiguration {

	@Override
	public UnsafeConsumer<Long, Exception>
		getCompanyJobExecutorUnsafeConsumer() {

		return _getCompanyJobExecutorUnsafeConsumer();
	}

	@Override
	public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
		return () -> _companyLocalService.forEachCompanyId(
			_getCompanyJobExecutorUnsafeConsumer());
	}

	@Override
	public TriggerConfiguration getTriggerConfiguration() {
		return TriggerConfiguration.createTriggerConfiguration(
			15, TimeUnit.MINUTE);
	}

	private UnsafeConsumer<Long, Exception>
		_getCompanyJobExecutorUnsafeConsumer() {

		return companyId -> {
			LockedLayoutsCompanyConfiguration
				lockedLayoutsCompanyConfiguration =
					_configurationProvider.getCompanyConfiguration(
						LockedLayoutsCompanyConfiguration.class, companyId);

			if (!lockedLayoutsCompanyConfiguration.
					allowAutomaticUnlockingProcess()) {

				return;
			}

			_layoutLockManager.unlockLayouts(
				companyId, lockedLayoutsCompanyConfiguration.autosaveMinutes());
		};
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private LayoutLockManager _layoutLockManager;

}