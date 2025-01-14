/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.translation.internal.upgrade.registry;

import com.liferay.portal.kernel.upgrade.CTModelUpgradeProcess;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.translation.model.TranslationEntryTable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(service = UpgradeStepRegistrator.class)
public class TranslationServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.1.0",
			new CTModelUpgradeProcess(
				TranslationEntryTable.INSTANCE.getName()));
	}

}