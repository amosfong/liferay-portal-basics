/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.scheduler;

import com.liferay.commerce.price.list.configuration.CommercePriceModifierConfiguration;
import com.liferay.commerce.pricing.service.CommercePriceModifierLocalService;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	configurationPid = "com.liferay.commerce.price.list.configuration.CommercePriceModifierConfiguration",
	service = SchedulerJobConfiguration.class
)
public class CheckCommercePriceModifierSchedulerJobConfiguration
	implements SchedulerJobConfiguration {

	@Override
	public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
		return _commercePriceModifierLocalService::checkCommercePriceModifiers;
	}

	@Override
	public TriggerConfiguration getTriggerConfiguration() {
		return _triggerConfiguration;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		CommercePriceModifierConfiguration commercePriceModifierConfiguration =
			ConfigurableUtil.createConfigurable(
				CommercePriceModifierConfiguration.class, properties);

		_triggerConfiguration = TriggerConfiguration.createTriggerConfiguration(
			commercePriceModifierConfiguration.checkInterval(),
			TimeUnit.MINUTE);
	}

	@Reference
	private CommercePriceModifierLocalService
		_commercePriceModifierLocalService;

	private TriggerConfiguration _triggerConfiguration;

}