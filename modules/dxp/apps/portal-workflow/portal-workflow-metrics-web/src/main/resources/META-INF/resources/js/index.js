/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import App from './components/App.es';

export function Main(props) {
	return <App companyId={Liferay.ThemeDisplay.getCompanyId()} {...props} />;
}
