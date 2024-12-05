/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import axe, {AxeResults, ContextObject} from 'axe-core';

const config = {

	// Color contrast checks do not work in JSDOM so are turned off.

	rules: {
		'color-contrast': {enabled: false},
	},
	runOnly: ['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa', 'wcag22aa'],
};

export default async function checkAccessibility({
	bestPractices = false,
	context,
}: {
	bestPractices: boolean;
	context: ContextObject;
}) {
	if (bestPractices) {
		config.runOnly = [...config.runOnly, 'best-practice'];
	}

	const results: AxeResults = await axe.run(context, config);

	expect(results.violations).toStrictEqual([]);
}
