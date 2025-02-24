/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, expect} from '@playwright/test';

export async function hoverAndExpectToBeVisible({
	autoClick = false,
	target,
	timeout = 100,
	trigger,
}: {
	autoClick?: boolean;
	target: Locator;
	timeout?: number;
	trigger: Locator;
}) {
	await expect(async () => {
		if (await trigger.isVisible()) {
			await trigger.hover();
		}

		await expect(target).toBeVisible({timeout});

		if (autoClick) {
			await target.click();
		}
	}).toPass();
}
