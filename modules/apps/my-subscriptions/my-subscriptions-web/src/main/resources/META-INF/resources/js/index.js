/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {getCheckedCheckboxes} from 'frontend-js-web';

export function MySubscriptionsManagementToolbarPropsTransformer({
	portletNamespace,
	...otherProps
}) {
	return {
		...otherProps,
		onActionButtonClick: (event, {item}) => {
			if (item?.data?.action === 'unsubscribe') {
				const form = document.getElementById(`${portletNamespace}fm`);

				if (!form) {
					return;
				}

				form.setAttribute('method', 'post');

				const subscriptionIds = form.querySelector(
					`#${portletNamespace}subscriptionIds`
				);

				if (subscriptionIds) {
					subscriptionIds.setAttribute(
						'value',
						getCheckedCheckboxes(
							form,
							`${portletNamespace}allRowIds`
						)
					);
				}

				submitForm(form);
			}
		},
	};
}
