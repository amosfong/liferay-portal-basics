/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {render} from '@liferay/frontend-js-react-web';

import OrganizationChart from '../../src/main/resources/META-INF/resources/js/OrganizationChart';

render(
	OrganizationChart,
	{
		pageSize: 100,
		rootOrganizationId: 0,
		spritemap: './assets/clay/icons.svg',
	},
	document.getElementById('organizations-chart-root')
);