/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openConfirmModal, openSelectionModal} from 'frontend-js-web';

export default function propsTransformer({portletNamespace, ...otherProps}) {
	return {
		...otherProps,
		onActionButtonClick(event, {item}) {
			if (item?.data?.action === 'deleteUserGroups') {
				openConfirmModal({
					message: Liferay.Language.get(
						'are-you-sure-you-want-to-delete-this'
					),
					onConfirm: (isSelected) => {
						if (isSelected) {
							const form = document.getElementById(
								`${portletNamespace}fm`
							);

							if (form) {
								submitForm(form);
							}
						}
					},
				});
			}
		},
		onCreateButtonClick(event, {item}) {
			const data = item?.data;

			const action = data?.action;

			if (action === 'selectUserGroup') {
				openSelectionModal({
					multiple: true,
					onSelect: (selectedItems) => {
						if (selectedItems.length) {
							const addTeamUserGroupsFm = document.getElementById(
								`${portletNamespace}addTeamUserGroupsFm`
							);

							if (!addTeamUserGroupsFm) {
								return;
							}

							const input = document.createElement('input');

							input.name = `${portletNamespace}rowIds`;
							input.value = selectedItems.map((selectedItem) => {
								const item = JSON.parse(selectedItem.value);

								return item.userGroupId;
							});

							addTeamUserGroupsFm.appendChild(input);

							submitForm(addTeamUserGroupsFm);
						}
					},
					title: data?.title,
					url: data?.selectUserGroupURL,
				});
			}
		},
	};
}