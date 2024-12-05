/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayAlert from '@clayui/alert';
import {
	API,
	FormError,
	Input,
	SingleSelect,
	stringUtils,
} from '@liferay/object-js-components-web';
import {createResourceURL} from 'frontend-js-web';
import React, {useEffect, useState} from 'react';

import CurrentObjectDefinition from './CurrentObjectDefinition';
import {ObjectRelationshipInheritanceCheckbox} from './ObjectRelationshipInheritanceCheckbox';
import SelectObjectDefinition from './SelectObjectDefinition';

export type Alert = {
	displayType: 'info' | 'warning';
	message: string;
};

interface ObjectRelationshipFormBaseProps {
	alert?: Alert;
	baseResourceURL: string;
	children?: JSX.Element;
	className?: string;
	errors: FormError<ObjectRelationship>;
	handleChange: React.ChangeEventHandler<HTMLInputElement>;
	hasDefinedObjectDefinitionTarget?: boolean;
	objectDefinitionExternalReferenceCode1: string;
	objectDefinitionExternalReferenceCode2?: string;
	onSubmit?: (values?: Partial<ObjectRelationship>) => Promise<void>;
	readonly?: boolean;
	setValues: (values: Partial<ObjectRelationship>) => void;
	values: Partial<ObjectRelationship>;
}

export type ObjectRelationshipType = 'manyToMany' | 'oneToMany' | 'oneToOne';

type ObjectRelationshipTypeInfo = {
	description: string;
	label: string;
	objectInputLabel1: string;
	objectInputLabel2: string;
	value: ObjectRelationshipType;
};

const MANY_TO_MANY = {
	description: Liferay.Language.get(
		"multiple-object's-entries-can-interact-with-many-others-object's-entries"
	),
	label: Liferay.Language.get('many-to-many'),
	objectInputLabel1: Liferay.Language.get('many-records-of'),
	objectInputLabel2: Liferay.Language.get('many-records-of'),
	value: 'manyToMany',
} as ObjectRelationshipTypeInfo;

const ONE_TO_MANY = {
	description: Liferay.Language.get(
		"one-object's-entry-interacts-with-many-others-object's-entries"
	),
	label: Liferay.Language.get('one-to-many'),
	objectInputLabel1: Liferay.Language.get('one-record-of'),
	objectInputLabel2: Liferay.Language.get('many-records-of'),
	value: 'oneToMany',
} as ObjectRelationshipTypeInfo;

const ONE_TO_ONE = {
	description: Liferay.Language.get(
		"one-object's-entry-interacts-only-with-one-other-object's-entry"
	),
	label: Liferay.Language.get('one-to-one'),
	objectInputLabel1: Liferay.Language.get('one-record-of'),
	objectInputLabel2: Liferay.Language.get('one-record-of'),
	value: 'oneToOne',
} as ObjectRelationshipTypeInfo;

export const OBJECT_RELATIONSHIP_TYPES = [
	MANY_TO_MANY,
	ONE_TO_MANY,
	ONE_TO_ONE,
];

export function ObjectRelationshipFormBase({
	alert,
	baseResourceURL,
	children,
	className,
	errors,
	handleChange,
	hasDefinedObjectDefinitionTarget,
	objectDefinitionExternalReferenceCode1,
	objectDefinitionExternalReferenceCode2,
	onSubmit,
	readonly,
	setValues,
	values,
}: ObjectRelationshipFormBaseProps) {
	const [creationLanguageId, setCreationLanguageId] =
		useState<Liferay.Language.Locale>();
	const [currentObjectDefinition, setCurrentObjectDefinition] =
		useState<Partial<ObjectDefinition>>();
	const [objectDefinition1, setObjectDefinition1] =
		useState<Partial<ObjectDefinition>>();
	const [objectDefinition2, setObjectDefinition2] =
		useState<Partial<ObjectDefinition>>();
	const [objectDefinitions, setObjectDefinitions] = useState<
		Partial<ObjectDefinition>[]
	>([]);
	const [objectRelationshipTypes, setObjectRelationshipTypes] = useState<
		ObjectRelationshipTypeInfo[]
	>([ONE_TO_MANY]);
	const [reverseOrder, setReverseOrder] = useState<boolean>(false);

	const switchObjects = () => {
		const previousObjectDefinition1 = {
			...objectDefinition1,
		};

		setObjectDefinition1(objectDefinition2);

		setObjectDefinition2(previousObjectDefinition1);
	};

	const handleHideReverseButton = () => {
		return (
			values.type !== 'oneToMany' ||
			!!readonly ||
			currentObjectDefinition?.externalReferenceCode ===
				'L_POSTAL_ADDRESS'
		);
	};

	const handleObjectRelationshipTypes = async (
		objectDefinition: Partial<ObjectDefinition> | undefined
	) => {
		const url = createResourceURL(baseResourceURL, {
			objectDefinitionId: objectDefinition?.id,
			p_p_resource_id: '/object_definitions/get_object_relationship_info',
		}).href;

		const {objectRelationshipTypes} = await API.fetchJSON<{
			objectRelationshipTypes: any;
		}>(url);

		const types = OBJECT_RELATIONSHIP_TYPES.filter((relationshipType) =>
			objectRelationshipTypes?.includes(relationshipType.value)
		);

		if (
			!objectRelationshipTypes.includes(values?.type) &&
			values.objectDefinitionExternalReferenceCode2
		) {
			setValues({type: objectRelationshipTypes[0]});
		}

		setObjectRelationshipTypes(types);
	};

	const handleReverseOrder = () => {
		setValues({
			objectDefinitionExternalReferenceCode1:
				objectDefinition2?.externalReferenceCode,
			objectDefinitionExternalReferenceCode2:
				objectDefinition1?.externalReferenceCode,
			objectDefinitionId1: objectDefinition2?.id,
			objectDefinitionId2: objectDefinition1?.id,
			objectDefinitionName2: objectDefinition1?.name,
		});

		switchObjects();

		setReverseOrder(!reverseOrder);
	};

	useEffect(() => {
		if (objectDefinition1) {
			handleObjectRelationshipTypes(objectDefinition1);
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [values.objectDefinitionExternalReferenceCode1]);

	useEffect(() => {
		const fetchObjectDefinition = async () => {
			const objectDefinition1 =
				await API.getObjectDefinitionByExternalReferenceCode(
					objectDefinitionExternalReferenceCode1 as string
				);
			let newObjectRelationshipValues: Partial<ObjectRelationship> = {
				objectDefinitionExternalReferenceCode1:
					objectDefinition1.externalReferenceCode,
				objectDefinitionId1: objectDefinition1.id,
			};

			if (objectDefinitionExternalReferenceCode2) {
				const objectDefinition2 =
					await API.getObjectDefinitionByExternalReferenceCode(
						objectDefinitionExternalReferenceCode2 as string
					);

				setObjectDefinition2(objectDefinition2);

				newObjectRelationshipValues = {
					...newObjectRelationshipValues,
					objectDefinitionExternalReferenceCode2:
						objectDefinition2?.externalReferenceCode,
					objectDefinitionId2: objectDefinition2?.id,
				};
			}
			setCurrentObjectDefinition(objectDefinition1);
			setCreationLanguageId(objectDefinition1.defaultLanguageId);
			setObjectDefinition1(objectDefinition1);

			setValues(newObjectRelationshipValues);

			handleObjectRelationshipTypes(objectDefinition1);
		};

		fetchObjectDefinition();

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [objectDefinitionExternalReferenceCode1]);

	useEffect(() => {
		const fetchObjectDefinitions = async () => {
			const {items} = await API.getAllObjectDefinitions();

			const objectDefinition = items.find(
				({externalReferenceCode}) =>
					objectDefinitionExternalReferenceCode1 ===
					externalReferenceCode
			)!;

			const objectDefinitions = items.filter(
				({modifiable, parameterRequired, storageType}) => {
					return (
						(objectDefinition.modifiable || modifiable) &&
						(!Liferay.FeatureFlags['LPS-135430'] ||
							storageType === 'default') &&
						!parameterRequired
					);
				}
			);

			setCreationLanguageId(objectDefinition.defaultLanguageId);

			setObjectDefinitions(objectDefinitions);
		};

		if (readonly) {
			setObjectDefinitions([
				{
					externalReferenceCode:
						values.objectDefinitionExternalReferenceCode2 as string,
					id: values.objectDefinitionId2 as number,
					label: values.label as LocalizedValue<string>,
					name: values.objectDefinitionName2 as string,
					system: false,
				},
			]);
		}
		else {
			fetchObjectDefinitions();
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [objectDefinitionExternalReferenceCode1, readonly]);

	return (
		<>
			<Input
				disabled={readonly}
				error={errors.name}
				id="lfr-objects__object-relationship-form-base-name"
				label={Liferay.Language.get('name')}
				name="name"
				onChange={handleChange}
				required
				value={values.name}
			/>

			<SingleSelect
				className={className}
				disabled={readonly}
				error={errors.type}
				id="lfr-objects__object-relationship-form-base-type"
				items={objectRelationshipTypes}
				label={Liferay.Language.get('type')}
				onSelectionChange={(value) => {
					if (
						(value === 'manyToMany' || value === 'oneToOne') &&
						currentObjectDefinition?.id !== objectDefinition1?.id
					) {
						setValues({
							objectDefinitionExternalReferenceCode1:
								objectDefinition2?.externalReferenceCode,
							objectDefinitionExternalReferenceCode2:
								objectDefinition1?.externalReferenceCode,
							objectDefinitionId1: objectDefinition2?.id,
							objectDefinitionId2: objectDefinition1?.id,
							objectDefinitionName2: objectDefinition1?.name,
							type: value,
						});

						switchObjects();

						setReverseOrder(!reverseOrder);
					}
					else {
						setValues({
							objectDefinitionExternalReferenceCode1:
								objectDefinition1?.externalReferenceCode,
							objectDefinitionId1: objectDefinition1?.id,
							type: value as ObjectRelationshipType,
						});
					}
				}}
				required
				selectedKey={values.type}
			/>

			{values.type &&
				(!reverseOrder ? (
					<>
						<CurrentObjectDefinition
							currentObjectDefinition={currentObjectDefinition}
							disableReverseButton={
								!values.objectDefinitionExternalReferenceCode2
							}
							disabled={readonly}
							error={errors.objectDefinitionId1}
							handleReverseOrder={handleReverseOrder}
							hideReverseButton={handleHideReverseButton()}
							label={
								OBJECT_RELATIONSHIP_TYPES.find(
									({value}) => value === values.type
								)?.objectInputLabel1
							}
						/>
						{objectDefinition2?.label &&
						hasDefinedObjectDefinitionTarget ? (
							<Input
								label={
									OBJECT_RELATIONSHIP_TYPES.find(
										({value}) => value === values.type
									)?.objectInputLabel2
								}
								name="currentObjectInput"
								readOnly={true}
								required
								value={stringUtils.getLocalizableLabel(
									objectDefinition2?.defaultLanguageId as Liferay.Language.Locale,
									objectDefinition2?.label,
									objectDefinition2?.name
								)}
							/>
						) : (
							<SelectObjectDefinition
								creationLanguageId={
									creationLanguageId as Liferay.Language.Locale
								}
								disabled={readonly}
								error={errors.objectDefinitionId2}
								label={
									OBJECT_RELATIONSHIP_TYPES.find(
										({value}) => value === values.type
									)?.objectInputLabel2
								}
								objectDefinition={objectDefinition2}
								objectDefinitionExternalReferenceCode={
									values.objectDefinitionExternalReferenceCode2
								}
								objectDefinitions={objectDefinitions}
								readOnly={readonly}
								reverseOrder={reverseOrder}
								setObjectDefinition={setObjectDefinition2}
								setValues={setValues}
							/>
						)}
					</>
				) : (
					<>
						{objectDefinition1?.label &&
						hasDefinedObjectDefinitionTarget ? (
							<Input
								label={
									OBJECT_RELATIONSHIP_TYPES.find(
										({value}) => value === values.type
									)?.objectInputLabel1
								}
								name="currentObjectInput"
								readOnly={true}
								required
								value={stringUtils.getLocalizableLabel(
									objectDefinition1?.defaultLanguageId as Liferay.Language.Locale,
									objectDefinition1?.label,
									objectDefinition1?.name
								)}
							/>
						) : (
							<SelectObjectDefinition
								creationLanguageId={
									creationLanguageId as Liferay.Language.Locale
								}
								disabled={readonly}
								error={errors.objectDefinitionId1}
								label={
									OBJECT_RELATIONSHIP_TYPES.find(
										({value}) => value === values.type
									)?.objectInputLabel1
								}
								objectDefinition={objectDefinition1}
								objectDefinitionExternalReferenceCode={
									values.objectDefinitionExternalReferenceCode1
								}
								objectDefinitions={objectDefinitions}
								readOnly={readonly}
								reverseOrder={reverseOrder}
								setObjectDefinition={setObjectDefinition1}
								setValues={setValues}
							/>
						)}

						<CurrentObjectDefinition
							currentObjectDefinition={currentObjectDefinition}
							disableReverseButton={
								!values.objectDefinitionExternalReferenceCode2
							}
							error={errors.objectDefinitionId2}
							handleReverseOrder={handleReverseOrder}
							hideReverseButton={handleHideReverseButton()}
							label={
								OBJECT_RELATIONSHIP_TYPES.find(
									({value}) => value === values.type
								)?.objectInputLabel2
							}
						/>
					</>
				))}

			{children}

			{alert &&
				onSubmit &&
				values.type === 'oneToMany' &&
				Liferay.FeatureFlags['LPS-187142'] && (
					<>
						<ObjectRelationshipInheritanceCheckbox
							onSubmit={onSubmit}
							setValues={setValues}
							values={values}
						/>

						<ClayAlert
							displayType={alert.displayType}
							title={`${alert.displayType === 'info' ? Liferay.Language.get('info') : Liferay.Language.get('warning')}:`}
						>
							{alert.message}
						</ClayAlert>
					</>
				)}
		</>
	);
}
