/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openConfirmModal} from 'frontend-js-web';

export function CommerceCurrenciesManagementToolbarPropsTransformer({
	portletNamespace,
	...props
}) {
	return {
		...props,
		onActionButtonClick(event, {item}) {
			if (item?.data?.action === 'deleteEntries') {
				openConfirmModal({
					message: Liferay.Language.get(
						'are-you-sure-you-want-to-delete-the-selected-currencies'
					),
					onConfirm: (isConfirmed) => {
						if (isConfirmed) {
							const form = document.getElementById(
								`${portletNamespace}fm`
							);

							if (!form) {
								return;
							}

							const cmd = form.querySelector(
								`#${portletNamespace}cmd`
							);

							if (cmd) {
								cmd.setAttribute('value', item.data.cmd);
							}

							submitForm(form);
						}
					},
				});
			}
			else if (item?.data?.action === 'updateExchangeRates') {
				openConfirmModal({
					message: Liferay.Language.get(
						'are-you-sure-you-want-to-update-the-exchange-rate-of-the-selected-currencies'
					),
					onConfirm: (isConfirmed) => {
						if (isConfirmed) {
							const form = document.getElementById(
								`${portletNamespace}fm`
							);

							if (!form) {
								return;
							}

							const cmd = form.querySelector(
								`#${portletNamespace}cmd`
							);

							if (cmd) {
								cmd.setAttribute('value', item.data.cmd);
							}

							submitForm(form);
						}
					},
				});
			}
		},
	};
}
