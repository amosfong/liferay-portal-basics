/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {BrowserRouter} from 'react-router-dom';

import Pages from './pages';

const SecurityVulnerabilities = () => {
	return (
		<div>
			<BrowserRouter basename="/web/customer-portal/security-vulnerabilities">
				<Pages />
			</BrowserRouter>
		</div>
	);
};

export default SecurityVulnerabilities;