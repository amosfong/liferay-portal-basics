/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openToast} from 'frontend-js-web';

export function undo({alertMessage, namespace}) {
	const componentId = `${namespace}recycleBinAlert`;

	openToast({
		autoClose: 15000,
		message: alertMessage,
		renderData: {
			componentId,
		},
		toastProps: {
			id: componentId,
			tabIndex: '-1',
		},
		type: 'success',
	});

	Liferay.componentReady(componentId).then(() =>
		document.getElementById(componentId).focus()
	);

	return {
		dispose() {
			Liferay.destroyComponent(componentId);
		},
	};
}
