/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {API} from '@liferay/object-js-components-web';

export type ObjectsOptionsList = {
	items: ObjectOptionsListItem[];
	label: string;
}[];

export type ObjectOptionsListItem = {
	isSystemObjectDefinition: boolean;
	label?: string;
	objectDefinitionExternalReferenceCode: string;
	objectDefinitionId: number;
};

function fillSelect(
	items: ObjectOptionsListItem[],
	label: string,
	objectsOptionsList: ObjectsOptionsList
) {
	if (items.length) {
		objectsOptionsList.push({items, label});
	}
}

interface FetchObjectDefinitionsProps {
	objectDefinitionsRelationshipsURL: string;
	setAddObjectEntryDefinitions: (values: AddObjectEntryDefinitions[]) => void;
	setObjectOptions: (values: ObjectsOptionsList) => void;
}

export async function fetchObjectDefinitions({
	objectDefinitionsRelationshipsURL,
	setAddObjectEntryDefinitions,
	setObjectOptions,
}: FetchObjectDefinitionsProps) {
	const addObjectEntryDefinitions = await API.fetchJSON<
		AddObjectEntryDefinitions[]
	>(objectDefinitionsRelationshipsURL);

	const relatedObjects: ObjectOptionsListItem[] = [];
	const unrelatedObjects: ObjectOptionsListItem[] = [];

	addObjectEntryDefinitions?.forEach((object) => {
		const {externalReferenceCode, id, label, system} = object;

		const target = object.related ? relatedObjects : unrelatedObjects;

		target.push({
			isSystemObjectDefinition: system as boolean,
			label,
			objectDefinitionExternalReferenceCode: externalReferenceCode,
			objectDefinitionId: id,
		});
	});

	const objectsOptionsList: ObjectsOptionsList = [];

	fillSelect(
		relatedObjects,
		Liferay.Language.get('related-objects'),
		objectsOptionsList
	);

	fillSelect(
		unrelatedObjects,
		Liferay.Language.get('unrelated-objects'),
		objectsOptionsList
	);

	setObjectOptions(objectsOptionsList);
	setAddObjectEntryDefinitions(addObjectEntryDefinitions);
}

export async function fetchObjectDefinitionFields(
	objectDefinitionId: number,
	objectDefinitionExternalReferenceCode: string,
	systemObject: boolean,
	values: Partial<ObjectAction>,
	isValidField: (
		{businessType, name, objectFieldSettings, system}: ObjectField,
		isObjectActionSystem?: boolean
	) => boolean,
	setCurrentObjectDefinitionFields: (values: ObjectField[]) => void,
	setValues: (values: Partial<ObjectAction>) => void
) {
	let definitionId = objectDefinitionId;
	let externalReferenceCode = objectDefinitionExternalReferenceCode;
	let isSystemObject = systemObject;
	const validFields: ObjectField[] = [];

	if (values.objectActionExecutorKey === 'add-object-entry') {
		definitionId = values?.parameters?.objectDefinitionId as number;
		externalReferenceCode = values.parameters
			?.objectDefinitionExternalReferenceCode as string;
		isSystemObject = !!values?.parameters?.system;
	}

	if (externalReferenceCode) {
		const items =
			await API.getObjectDefinitionByExternalReferenceCodeObjectFields(
				externalReferenceCode
			);

		items.forEach((field) => {
			if (isValidField(field, isSystemObject)) {
				validFields.push(field);
			}
		});
	}

	setCurrentObjectDefinitionFields(validFields);

	const {predefinedValues = []} = values.parameters as ObjectActionParameters;

	const predefinedValuesMap = new Map<string, PredefinedValue>();

	predefinedValues.forEach((field) => {
		predefinedValuesMap.set(field.name, field);
	});

	const newPredefinedValues: PredefinedValue[] = [];

	validFields.forEach(({businessType, label, name, required}) => {
		if (predefinedValuesMap.has(name)) {
			const field = predefinedValuesMap.get(name);

			newPredefinedValues.push(field as PredefinedValue);
		}
		else if (
			required &&
			values.objectActionExecutorKey === 'add-object-entry'
		) {
			const inputAsValue = businessType === 'DateTime' ? true : false;

			newPredefinedValues.push({
				businessType,
				inputAsValue,
				label,
				name,
				value: '',
			});
		}
	});
	setValues({
		parameters: {
			...values.parameters,
			objectDefinitionExternalReferenceCode: externalReferenceCode,
			objectDefinitionId: definitionId,
			predefinedValues: newPredefinedValues,
		},
	});
}
