/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import * as FormSupport from '../../utils/FormSupport.es';
import {
	addFieldToPage,
	createField,
	getField,
	localizeField,
	removeField,
} from '../../utils/fieldSupport';
import {formatRules} from '../../utils/rulesSupport';
import {
	setFieldErrorMessage,
	updateField,
	updateFieldName,
	updateFieldReference,
} from '../../utils/settingsContext';
import {PagesVisitor} from '../../utils/visitors.es';
import {EVENT_TYPES} from '../actions/eventTypes.es';
import {createDuplicatedField, isValueAlreadyUsed} from '../utils/fields';
import {updateRulesReferences} from '../utils/rules';
import sectionAdded from '../utils/sectionAddedHandler';
import {enableSubmitButton} from '../utils/submitButtonController.es';

export function deleteField({
	clean = false,
	defaultLanguageId,
	editingLanguageId,
	fieldName,
	fieldNameGenerator,
	fieldPage,
	generateFieldNameUsingFieldLabel,
	pages,
}) {
	return pages.map((page, pageIndex) => {
		if (fieldPage === pageIndex) {
			const pagesWithFieldRemoved = removeField(
				{
					defaultLanguageId,
					editingLanguageId,
					fieldNameGenerator,
					generateFieldNameUsingFieldLabel,
				},
				pages,
				fieldName,
				clean
			);

			return {
				...page,
				rows: clean
					? FormSupport.removeEmptyRows(
							pagesWithFieldRemoved,
							pageIndex
						)
					: pagesWithFieldRemoved[pageIndex].rows,
			};
		}

		return page;
	});
}

function isParameterRelatedToField(parameter, fieldName) {

	/* TODO: enforce parameter type consistency and remove this normalization */
	const json =
		typeof parameter === 'string' ? parameter : JSON.stringify(parameter);

	return json.includes(fieldName);
}

/* TODO: enforce parameter type consistency and remove this function */
function normalizeParameter(parameter, defaultLanguageId) {
	let normalizedParameter = parameter;

	if (typeof normalizedParameter === 'string') {
		normalizedParameter = JSON.parse(parameter);
	}

	if (normalizedParameter[defaultLanguageId]) {
		normalizedParameter = normalizedParameter[defaultLanguageId];
	}

	return normalizedParameter;
}

function updateFieldAffectedByActivatingRepeatable({
	defaultLanguageId,
	editingLanguageId,
	field,
	fieldNameGenerator,
	generateFieldNameUsingFieldLabel,
	repeatableFieldName,
}) {
	if (
		field.type === 'date' &&
		field.validation?.parameter &&
		isParameterRelatedToField(
			field.validation.parameter,
			repeatableFieldName
		)
	) {
		const {endsOn, startsFrom} = normalizeParameter(
			field.validation.parameter,
			defaultLanguageId
		);

		const removeDateField = (validation) => {
			if (repeatableFieldName !== validation.dateFieldName) {
				return;
			}

			if (validation.type === 'dateField') {
				validation.type = 'responseDate';
			}
			delete validation.dateFieldName;
		};
		removeDateField(endsOn);
		removeDateField(startsFrom);

		const validation = {
			...field.validation,

			/* TODO: define a proper parameter type and apply it here */
			parameter: JSON.stringify({
				endsOn,
				startsFrom,
			}),
		};

		return updateField(
			{
				defaultLanguageId,
				editingLanguageId,
				fieldNameGenerator,
				generateFieldNameUsingFieldLabel,
			},
			field,
			'validation',
			validation
		);
	}

	return field;
}

const updateFieldProperty = ({
	defaultLanguageId,
	editingLanguageId,
	fieldNameGenerator,
	focusedField,
	generateFieldNameUsingFieldLabel,
	pages,
	propertyName,
	propertyValue,
}) => {
	if (
		propertyName === 'fieldReference' &&
		propertyValue !== '' &&
		propertyValue !== focusedField.fieldName
	) {
		focusedField = updateFieldReference(
			focusedField,
			isValueAlreadyUsed(
				focusedField,
				pages,
				propertyValue,
				propertyName
			),
			false
		);
	}
	else if (propertyName === 'name') {
		focusedField = updateFieldName(
			defaultLanguageId,
			editingLanguageId,
			fieldNameGenerator,
			focusedField,
			propertyValue,
			isValueAlreadyUsed(focusedField, pages, propertyValue, propertyName)
		);
	}

	return updateField(
		{
			defaultLanguageId,
			editingLanguageId,
			fieldNameGenerator,
			generateFieldNameUsingFieldLabel,
		},
		focusedField,
		propertyName,
		propertyValue
	);
};

/**
 * NOTE: This is a literal copy of the old LayoutProvider logic. Small changes
 * were made only to adapt to the reducer.
 */
export default function fieldEditableReducer(state, action, config) {
	switch (action.type) {
		case EVENT_TYPES.FIELD.ADD: {
			const {data, fieldType, indexes} = action.payload;
			const {
				availableLanguageIds,
				defaultLanguageId,
				editingLanguageId,
				pages,
			} = state;
			const {
				generateFieldNameUsingFieldLabel,
				getFieldNameGenerator,
				portletNamespace,
			} = config;

			const fieldNameGenerator = getFieldNameGenerator(
				pages,
				generateFieldNameUsingFieldLabel
			);

			const field =
				action.payload.newField ||
				createField({
					defaultLanguageId,
					editingLanguageId,
					fieldNameGenerator,
					fieldType,
					portletNamespace,
				});

			const settingsVisitor = new PagesVisitor(
				field.settingsContext.pages
			);

			const newField = {
				...field,
				settingsContext: {
					...field.settingsContext,
					availableLanguageIds,
					defaultLanguageId,
					pages: settingsVisitor.mapFields((field) =>
						localizeField(
							field,
							defaultLanguageId,
							editingLanguageId
						)
					),
				},
			};

			const updatedPages = addFieldToPage({
				defaultLanguageId,
				editingLanguageId,
				fieldNameGenerator,
				generateFieldNameUsingFieldLabel,
				indexes,
				newField,
				pages,
				parentFieldName: data?.parentFieldName,
			});

			return {
				activePage: indexes.pageIndex,
				focusedField: newField,
				pages: updatedPages,
			};
		}
		case EVENT_TYPES.FIELD.BLUR: {
			const {propertyName, propertyValue} = action.payload;
			const {defaultLanguageId, editingLanguageId} = state;
			let {focusedField, pages} = state;

			if (Object.keys(focusedField).length) {
				if (
					propertyName === 'fieldReference' &&
					(propertyValue === '' ||
						isValueAlreadyUsed(
							focusedField,
							pages,
							propertyValue,
							propertyName
						))
				) {
					focusedField = updateField(
						{
							defaultLanguageId,
							editingLanguageId,
						},
						updateFieldReference(focusedField, false, true),
						propertyName,
						focusedField.fieldName
					);
				}
				else if (
					propertyName === 'name' &&
					(propertyValue === '' ||
						isValueAlreadyUsed(
							focusedField,
							pages,
							propertyValue,
							propertyName
						))
				) {
					const fieldNameGenerator = config.getFieldNameGenerator(
						pages,
						false
					);

					focusedField = updateField(
						{
							defaultLanguageId,
							editingLanguageId,
							fieldNameGenerator,
						},
						focusedField,
						propertyName,
						''
					);

					const visitor = new PagesVisitor(pages);

					pages = visitor.mapFields(
						(field) => {
							if (
								field.fieldReference ===
								focusedField.fieldReference
							) {
								if (field.displayErrors) {
									focusedField.displayErrors = false;

									focusedField.settingsContext =
										setFieldErrorMessage(
											focusedField.settingsContext,
											'name',
											false,
											false
										);

									focusedField.errorMessage = '';
								}

								return focusedField;
							}

							return field;
						},
						false,
						true
					);
				}
			}

			return {
				fieldHovered: {},
				focusedField,
				pages,
			};
		}
		case EVENT_TYPES.FIELD.CLICK: {
			const {activePage, field} = action.payload;
			const {defaultLanguageId, editingLanguageId} = state;

			const visitor = new PagesVisitor(field.settingsContext.pages);

			const focusedField = {
				...field,
				settingsContext: {
					...field.settingsContext,
					currentPage: activePage,
					pages: visitor.mapFields((currentfield) => {
						const {fieldName} = currentfield;

						if (fieldName === 'validation') {
							currentfield = {
								...currentfield,
								validation: {
									...currentfield.validation,
									fieldName: field.fieldName,
								},
							};
						}

						return localizeField(
							currentfield,
							defaultLanguageId,
							editingLanguageId
						);
					}),
				},
			};

			return {
				activePage,
				focusedField,
			};
		}
		case EVENT_TYPES.FIELD.CHANGE: {
			const {fieldInstance, propertyName, propertyValue} = action.payload;
			let {fieldName} = action.payload;
			const {
				defaultLanguageId,
				editingLanguageId,
				focusedField,
				pages,
				rules,
			} = state;
			const {generateFieldNameUsingFieldLabel, getFieldNameGenerator} =
				config;

			const fieldNameGenerator = getFieldNameGenerator(
				pages,
				generateFieldNameUsingFieldLabel
			);

			if (propertyName === 'name' && propertyValue === '') {
				return state;
			}

			if (!fieldName && fieldInstance) {
				fieldName = fieldInstance.fieldName;
			}

			const newFocusedField = updateFieldProperty({
				defaultLanguageId,
				editingLanguageId,
				fieldNameGenerator,
				focusedField: fieldName
					? getField(pages, fieldName)
					: focusedField,
				generateFieldNameUsingFieldLabel,
				pages,
				propertyName,
				propertyValue,
			});

			const visitor = new PagesVisitor(pages);

			return {
				focusedField: newFocusedField,
				pages: visitor.mapFields(
					(field) => {
						if (field.fieldName === focusedField.fieldName) {
							if (
								propertyName === 'name' &&
								field.fieldReference ===
									focusedField.fieldReference &&
								newFocusedField.displayErrors
							) {
								newFocusedField.fieldName =
									focusedField.fieldName;
							}

							return newFocusedField;
						}
						if (propertyValue && propertyName === 'repeatable') {
							return updateFieldAffectedByActivatingRepeatable({
								defaultLanguageId,
								editingLanguageId,
								field,
								fieldNameGenerator,
								generateFieldNameUsingFieldLabel,
								repeatableFieldName: newFocusedField.fieldName,
							});
						}

						return field;
					},
					false,
					true
				),
				rules: updateRulesReferences(
					rules || [],
					focusedField,
					newFocusedField
				),
			};
		}
		case EVENT_TYPES.FIELD.DELETE: {
			const {
				activePage,
				editRule = true,
				fieldName,
				removeEmptyRows = true,
			} = action.payload;
			const {defaultLanguageId, editingLanguageId, pages, rules} = state;
			const {generateFieldNameUsingFieldLabel, getFieldNameGenerator} =
				config;

			const fieldNameGenerator = getFieldNameGenerator(
				pages,
				generateFieldNameUsingFieldLabel
			);

			const newPages = deleteField({
				clean: removeEmptyRows,
				defaultLanguageId,
				editingLanguageId,
				fieldName,
				fieldNameGenerator,
				fieldPage: activePage ?? state.activePage,
				generateFieldNameUsingFieldLabel,
				pages,
			});

			return {
				focusedField: {},
				pages: newPages,
				rules: editRule ? formatRules(newPages, rules) : rules,
			};
		}
		case EVENT_TYPES.FIELD.DUPLICATE: {
			const {fieldName, parentFieldName} = action.payload;
			const {
				availableLanguageIds,
				defaultLanguageId,
				editingLanguageId,
				pages,
			} = state;
			const {generateFieldNameUsingFieldLabel, getFieldNameGenerator} =
				config;

			const fieldNameGenerator = getFieldNameGenerator(
				pages,
				generateFieldNameUsingFieldLabel
			);

			const originalField = JSON.parse(
				JSON.stringify(
					FormSupport.findFieldByFieldName(pages, fieldName)
				)
			);

			const newField = createDuplicatedField(originalField, {
				availableLanguageIds,
				defaultLanguageId,
				editingLanguageId,
				fieldNameGenerator,
				generateFieldNameUsingFieldLabel,
			});

			let newPages = null;

			if (parentFieldName) {
				const visitor = new PagesVisitor(pages);

				newPages = visitor.mapFields(
					(field) => {
						if (field.fieldName === parentFieldName) {
							const nestedFields = field.nestedFields
								? [...field.nestedFields, newField]
								: [newField];

							field = updateField(
								{
									availableLanguageIds,
									defaultLanguageId,
									fieldNameGenerator,
									generateFieldNameUsingFieldLabel,
								},
								field,
								'nestedFields',
								nestedFields
							);

							let pages = [{rows: field.rows}];

							const {pageIndex, rowIndex} =
								FormSupport.getFieldIndexes(
									pages,
									originalField.fieldName
								);

							const newRow = FormSupport.implAddRow(12, [
								newField.fieldName,
							]);

							pages = FormSupport.addRow(
								pages,
								rowIndex + 1,
								pageIndex,
								newRow
							);

							return updateField(
								{
									availableLanguageIds,
									defaultLanguageId,
									fieldNameGenerator,
									generateFieldNameUsingFieldLabel,
								},
								field,
								'rows',
								pages[0].rows
							);
						}

						return field;
					},
					true,
					true
				);
			}
			else {
				const {pageIndex, rowIndex} = FormSupport.getFieldIndexes(
					pages,
					originalField.fieldName
				);

				const newRow = FormSupport.implAddRow(12, [newField]);

				newPages = FormSupport.addRow(
					pages,
					rowIndex + 1,
					pageIndex,
					newRow
				);
			}

			return {
				focusedField: {
					...newField,
				},
				pages: newPages,
			};
		}
		case EVENT_TYPES.FIELD.EVALUATE: {
			const {settingsContextPages} = action.payload;
			const {
				defaultLanguageId,
				editingLanguageId,
				focusedField,
				pages,
				rules,
			} = state;
			const {
				generateFieldNameUsingFieldLabel,
				getFieldNameGenerator,
				submitButtonId,
			} = config;

			const fieldName = getField(settingsContextPages, 'name');
			const focusedFieldName = getField(
				focusedField.settingsContext.pages,
				'name'
			);

			if (fieldName.instanceId !== focusedFieldName.instanceId) {
				return state;
			}

			const fieldNameGenerator = getFieldNameGenerator(
				pages,
				generateFieldNameUsingFieldLabel
			);

			let newFocusedField = {
				...focusedField,
				settingsContext: {
					...focusedField.settingsContext,
					pages: settingsContextPages,
				},
			};

			const settingsContextVisitor = new PagesVisitor(
				settingsContextPages
			);

			settingsContextVisitor.mapFields(({fieldName, value}) => {
				newFocusedField = updateFieldProperty({
					defaultLanguageId,
					editingLanguageId,
					fieldNameGenerator,
					focusedField: newFocusedField,
					generateFieldNameUsingFieldLabel,
					pages,
					propertyName: fieldName,
					propertyValue: value,
				});
			});

			const visitor = new PagesVisitor(pages);

			const newPages = visitor.mapFields(
				(field) => {
					if (field.fieldName !== fieldName.value) {
						return field;
					}

					return newFocusedField;
				},
				true,
				true
			);

			enableSubmitButton(submitButtonId);

			return {
				focusedField: newFocusedField,
				pages: newPages,
				rules: updateRulesReferences(
					rules || [],
					focusedField,
					newFocusedField
				),
			};
		}
		case EVENT_TYPES.FIELD.HOVER:
			return {
				fieldHovered: action.payload,
			};
		case EVENT_TYPES.DND.MOVE: {
			const {focusedField, pages} = state;

			if (!focusedField.fieldName) {
				return state;
			}

			const updatedFocusedField = FormSupport.findFieldByFieldName(
				pages,
				focusedField.fieldName
			);

			return {
				focusedField: updatedFocusedField,
			};
		}
		case EVENT_TYPES.SECTION.ADD: {
			const {
				activePage,
				availableLanguageIds,
				defaultLanguageId,
				editingLanguageId,
				pages,
				rules,
			} = state;
			const {
				fieldTypes,
				generateFieldNameUsingFieldLabel,
				getFieldNameGenerator,
			} = config;

			const fieldNameGenerator = getFieldNameGenerator(
				pages,
				generateFieldNameUsingFieldLabel
			);

			return sectionAdded(
				{
					availableLanguageIds,
					defaultLanguageId,
					editingLanguageId,
					fieldNameGenerator,
					fieldTypes,
					generateFieldNameUsingFieldLabel,
				},
				{
					activePage,
					pages,
					rules,
				},
				action.payload
			);
		}
		default:
			return state;
	}
}
