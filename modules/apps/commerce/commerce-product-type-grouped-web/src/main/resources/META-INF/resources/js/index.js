/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openConfirmModal, openSelectionModal} from 'frontend-js-web';

export function CPDefinitionGroupedManagementToolbarPropsTransformer({
	portletNamespace,
	...props
}) {
	return {
		...props,
		onActionButtonClick(event, {item}) {
			if (item?.data?.action === 'deleteEntries') {
				openConfirmModal({
					message: Liferay.Language.get(
						'are-you-sure-you-want-to-delete-the-selected-entries'
					),
					onConfirm: (isConfirmed) => {
						if (isConfirmed) {
							const form = document.getElementById(
								`${portletNamespace}fm`
							);

							if (!form) {
								return;
							}

							submitForm(form);
						}
					},
				});
			}
		},
		onCreateButtonClick: (event, {item}) => {
			const data = item?.data;

			openSelectionModal({
				id: `${portletNamespace}addDefinitionGroupedEntry`,
				multiple: true,
				onSelect: (selectedItems) => {
					if (selectedItems && selectedItems.length) {
						const entryCPDefinitionIds = document.getElementById(
							`${portletNamespace}entryCPDefinitionIds`
						);

						if (entryCPDefinitionIds) {
							entryCPDefinitionIds.value = selectedItems.map(
								(item) => item.value
							);
						}

						const addCPDefinitionGroupedEntryFm =
							document.getElementById(
								`${portletNamespace}addCPDefinitionGroupedEntryFm`
							);

						if (addCPDefinitionGroupedEntryFm) {
							submitForm(addCPDefinitionGroupedEntryFm);
						}
					}
				},
				selectEventName: `${portletNamespace}selectCPDefinition`,
				title: data?.dialogTitle,
				url: data?.addDefinitionGroupedEntryItemSelectorURL,
			});
		},
	};
}
