/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayInput} from '@clayui/form';
import {SettingsContext, useFormState} from 'data-engine-js-components-web';
import {openToast} from 'frontend-js-web';
import React, {useEffect, useState} from 'react';

import FieldBase from '../FieldBase/ReactFieldBase.es';
import usePlaces from './usePlaces.es';

const parse = (value, defaultValue) => {
	try {
		return JSON.parse(value);
	}
	catch (error) {
		return defaultValue !== undefined ? defaultValue : {};
	}
};

const getClassNameBasedOnLayout = (layout, visibleField) => {
	return layout?.includes('two-columns') && visibleField !== 'address'
		? 'col-md-6'
		: 'col-md-12';
};

const isEmpty = (object) => {
	return object && !Object.keys(object).length;
};

const Field = ({
	disabled,
	editingLanguageId,
	id,
	label,
	name,
	onBlur,
	onChange,
	onFocus,
	pageValidationFailed = false,
	parsedValue,
	placeholder,
	readOnly,
	repeatable,
	showLabel,
	valid: initialValid,
	visibleField,
	...otherProps
}) => {
	const [valid, setValid] = useState(true);

	useEffect(() => {
		if (pageValidationFailed) {
			setValid(initialValid);
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [initialValid, pageValidationFailed]);

	const accessibleProps = {
		...(otherProps.tip && {
			'aria-describedby': `${id ?? name}_fieldHelp`,
		}),
		...(otherProps.errorMessage && {
			'aria-errormessage': `${id ?? name}_fieldError`,
		}),
		'aria-invalid': !valid,
		'aria-required': otherProps.required,
	};

	return (
		<FieldBase
			{...otherProps}
			hideEditedFlag
			label={label[editingLanguageId] ?? label}
			localizedValue={{}}
			name={name}
			readOnly={readOnly}
			repeatable={repeatable}
			showLabel={showLabel}
			valid={!!parsedValue[visibleField] || valid}
		>
			<ClayInput
				{...accessibleProps}
				className="ddm-field-text"
				dir={Liferay.Language.direction[editingLanguageId]}
				disabled={disabled}
				id={id}
				name={name}
				onBlur={(event) => {
					setValid(initialValid);

					onBlur(event);
				}}
				onChange={(event) => {
					setValid(initialValid);

					const value = !isEmpty(parsedValue)
						? {
								...parsedValue,
								[visibleField]: event.target.value,
							}
						: {[visibleField]: event.target.value};
					onChange({
						target: {
							value: JSON.stringify({
								...value,
							}),
						},
					});
				}}
				onFocus={onFocus}
				placeholder={placeholder}
				type="text"
				value={!isEmpty(parsedValue) ? parsedValue[visibleField] : ''}
			/>
		</FieldBase>
	);
};

const Main = ({
	googlePlacesAPIKey,
	label,
	labels,
	layout,
	name,
	onBlur,
	onChange,
	onFocus,
	placeholder,
	readOnly,
	repeatable,
	settingsContext,
	showLabel,
	value,
	visibleFields,
	...otherProps
}) => {
	const {editingLanguageId, viewMode} = useFormState();

	usePlaces({
		elementId: `${name}#place`,
		googlePlacesAPIKey,
		isReadOnly: readOnly,
		onChange,
		viewMode,
	});

	const [availableLabels, setAvailableLabels] = useState();
	const [availableVisibleFields, setAvailableVisibleFields] = useState([]);
	const currentVisibleFields = Array.isArray(visibleFields)
		? visibleFields
		: parse(visibleFields, []);
	const currentLayout = Array.isArray(layout) ? layout : parse(layout, []);
	const parsedValue = parse(value, {});

	useEffect(() => {
		window.gm_authFailure = function () {
			openToast({
				message: Liferay.Language.get(
					'communication-with-the-api-provider-failed'
				),
				title: Liferay.Language.get('error'),
				type: 'danger',
			});
		};

		if (settingsContext) {
			const options = SettingsContext.getSettingsContextProperty(
				settingsContext,
				'visibleFields',
				'options'
			);

			setAvailableLabels(
				options.reduce((accumulator, currentOption) => {
					return {
						...accumulator,
						[currentOption.value]: currentOption.label,
					};
				}, {})
			);

			setAvailableVisibleFields(options.map((option) => option.value));
		}
		else {
			setAvailableLabels(labels ?? {});
			setAvailableVisibleFields(currentVisibleFields);
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	return (
		<FieldBase
			{...otherProps}
			accessible={false}
			displayErrors={false}
			name={name}
			placeholder=""
			readOnly={readOnly}
			repeatable={repeatable}
			required={false}
			tip=""
		>
			<Field
				{...otherProps}
				className="col-md-12"
				disabled={readOnly}
				editingLanguageId={editingLanguageId}
				id={`${name}#place`}
				label={label}
				name={`${name}#place`}
				onBlur={onBlur}
				onChange={onChange}
				onFocus={onFocus}
				parsedValue={parsedValue}
				placeholder={placeholder}
				readOnly={readOnly}
				repeatable={false}
				showLabel={showLabel}
				visibleField="place"
			/>

			<div className="row">
				{!!availableVisibleFields.length &&
					availableVisibleFields.map((visibleField, index) => {
						if (currentVisibleFields.includes(visibleField)) {
							const visibleFieldName = name + '#' + visibleField;
							const className = getClassNameBasedOnLayout(
								currentLayout,
								visibleField
							);

							return (
								<div className={className} key={index}>
									<Field
										{...otherProps}
										disabled={readOnly}
										editingLanguageId={editingLanguageId}
										id={visibleFieldName}
										key={visibleFieldName}
										label={availableLabels[visibleField]}
										name={visibleFieldName}
										onBlur={onBlur}
										onChange={onChange}
										onFocus={onFocus}
										parsedValue={parsedValue}
										placeholder=""
										readOnly={readOnly}
										repeatable={false}
										showLabel
										tip=""
										visibleField={visibleField}
									/>
								</div>
							);
						}
					})}
			</div>

			<ClayInput name={name} type="hidden" value={value} />
		</FieldBase>
	);
};

Main.displayName = 'SearchLocation';

export default Main;
