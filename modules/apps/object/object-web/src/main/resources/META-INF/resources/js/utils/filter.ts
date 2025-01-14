/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {MultiSelectItem} from '@liferay/object-js-components-web';

export function getCheckedWorkflowStatusItems(
	itemValues: LabelValueObject[],
	setEditingFilterType: () => number[] | string[] | null
): MultiSelectItem[] {
	const valuesArray = setEditingFilterType() as number[];

	const newItemsValues = [
		{
			children: itemValues.map((itemValue) => {
				const item = {
					checked: false,
					label: itemValue.label,
					value: itemValue.value,
				};

				if (valuesArray?.includes(Number(itemValue.value))) {
					item.checked = true;
				}

				return item;
			}),
			label: '',
			value: 'checkedWorkflowStatusItems',
		},
	];

	return newItemsValues;
}

export function getCheckedListTypeEntries(
	itemValues: ListTypeEntry[],
	setEditingFilterType: () => number[] | string[] | null
): MultiSelectItem[] {
	const valuesArray = setEditingFilterType() as string[];

	const newItemsValues = [
		{
			children: (itemValues as ListTypeEntry[]).map((itemValue) => {
				const item = {
					checked: false,
					label: itemValue.name,
					value: itemValue.key,
				};

				if (valuesArray?.includes(itemValue.key)) {
					item.checked = true;
				}

				return item;
			}),
			label: '',
			value: 'checkedListTypeEntries',
		},
	];

	return newItemsValues;
}

export function getSystemObjectFieldLabelFromObjectEntry(
	titleFieldName: string,
	entry: ObjectEntry,
	itemObject: {['value']: string}
) {
	if (titleFieldName === 'creator') {
		const {name} = entry.creator;

		return {
			...itemObject,
			label: name,
		};
	}

	if (titleFieldName === 'status') {
		const {label_i18n} = entry.status;

		return {
			...itemObject,
			label: label_i18n,
		};
	}

	if (titleFieldName === 'createDate') {
		return {
			...itemObject,
			label: entry['dateCreated'],
		};
	}

	if (titleFieldName === 'modifiedDate') {
		return {
			...itemObject,
			label: entry['dateModified'],
		};
	}

	return {
		...itemObject,
		label: String(entry[titleFieldName]),
	};
}

export function getCheckedObjectRelationshipItems(
	relatedEntries: ObjectEntry[],
	titleFieldName: string,
	systemField: boolean,
	systemObject: boolean,
	setEditingFilterType: () => number[] | string[] | null
): MultiSelectItem[] {
	const valuesArray = setEditingFilterType() as string[];

	const newItemsValues = [
		{
			children: relatedEntries.map((entry) => {
				let item = {
					checked: false,
					value: systemObject
						? String(entry.id)
						: entry.externalReferenceCode,
				} as IItem;

				if (systemField) {
					item = getSystemObjectFieldLabelFromObjectEntry(
						titleFieldName,
						entry,
						item
					) as IItem;
				}
				else {
					item = {
						...item,
						label: entry[titleFieldName] as string,
					};
				}

				if (valuesArray.includes(entry.externalReferenceCode)) {
					item.checked = true;
				}

				return item;
			}),
			label: '',
			value: 'checkedObjectRelationshipItems',
		},
	];

	return newItemsValues;
}
