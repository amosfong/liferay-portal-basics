/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.order.internal.upgrade.registry;

import com.liferay.commerce.product.type.virtual.order.internal.upgrade.v2_0_0.CommerceVirtualOrderItemFileEntryUpgradeProcess;
import com.liferay.portal.kernel.upgrade.MVCCVersionUpgradeProcess;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Cheryl Tang
 */
@Component(service = UpgradeStepRegistrator.class)
public class CommerceProductTypeVirtualOrderServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.1.0",
			new MVCCVersionUpgradeProcess() {

				@Override
				protected String[] getTableNames() {
					return new String[] {"CommerceVirtualOrderItem"};
				}

			});

		registry.register(
			"1.1.0", "2.0.0",
			new CommerceVirtualOrderItemFileEntryUpgradeProcess());
	}

}