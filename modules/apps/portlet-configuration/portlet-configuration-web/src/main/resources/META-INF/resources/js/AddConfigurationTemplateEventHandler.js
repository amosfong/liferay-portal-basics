/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {getOpener} from 'frontend-js-web';

export default function ({namespace}) {
	const form = document.getElementById(`${namespace}fm`);

	const handleSubmit = () =>
		Liferay.once('destroyPortlet', () =>
			getOpener()?.Liferay.fire('addPortletConfigurationTemplate')
		);

	form?.addEventListener('submit', handleSubmit);

	return {
		dispose: () => form?.removeEventListener('submit', handleSubmit),
	};
}
