/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openAlertModal} from 'frontend-js-web';

export function DisagreeButtonPropsTransformer({...props}) {
	return {
		...props,
		onClick() {
			openAlertModal({
				message: Liferay.Language.get(
					'you-must-agree-with-the-terms-of-use-to-continue'
				),
			});
		},
	};
}
