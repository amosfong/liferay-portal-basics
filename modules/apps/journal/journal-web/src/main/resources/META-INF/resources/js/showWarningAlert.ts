/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openToast} from 'frontend-js-web';

export default function showWarningAlert({message}: {message: string}) {
	openToast({
		message,
		toastProps: {
			autoClose: 20000,
		},
		type: 'warning',
	});
}
