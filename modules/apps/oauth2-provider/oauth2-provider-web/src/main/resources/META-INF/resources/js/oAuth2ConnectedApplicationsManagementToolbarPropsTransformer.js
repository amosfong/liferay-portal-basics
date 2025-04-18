/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	getCheckedCheckboxes,
	openConfirmModal,
	postForm,
} from 'frontend-js-web';

export default function propsTransformer({
	additionalProps: {revokeOauthAuthorizationsURL},
	portletNamespace,
	...otherProps
}) {
	return {
		...otherProps,
		onActionButtonClick: (event, {item}) => {
			if (item?.data?.action === 'removeAccess') {
				openConfirmModal({
					message: Liferay.Language.get(
						'are-you-sure-you-want-to-remove-access-for-the-selected-entries'
					),
					onConfirm: (isConfirmed) => {
						if (isConfirmed) {
							const form = document.getElementById(
								`${portletNamespace}fm`
							);

							if (form) {
								postForm(form, {
									data: {
										oAuth2AuthorizationIds:
											getCheckedCheckboxes(
												form,
												`${portletNamespace}allRowIds`
											),
									},
									url: revokeOauthAuthorizationsURL,
								});
							}
						}
					},
				});
			}
		},
	};
}
