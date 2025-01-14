/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.numeric;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.annotations.DDMFormRule;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;

/**
 * @author Leonardo Barros
 */
@DDMForm(
	rules = {
		@DDMFormRule(
			actions = {
				"setVisible('inputMask', TRUE)",
				"setVisible('repeatable', TRUE)",
				"setVisible('requireConfirmation', TRUE)",
				"setVisible('required', TRUE)", "setVisible('showLabel', TRUE)",
				"setVisible('validation', TRUE)"
			},
			condition = "equals(getValue('hideField'), FALSE)"
		),
		@DDMFormRule(
			actions = {
				"setValue('inputMask', FALSE)", "setValue('repeatable', FALSE)",
				"setValue('requireConfirmation', FALSE)",
				"setValue('required', FALSE)", "setValue('showLabel', TRUE)",
				"setVisible('inputMask', FALSE)",
				"setVisible('repeatable', FALSE)",
				"setVisible('requireConfirmation', FALSE)",
				"setVisible('required', FALSE)",
				"setVisible('showLabel', FALSE)",
				"setVisible('validation', FALSE)"
			},
			condition = "equals(getValue('hideField'), TRUE)"
		),
		@DDMFormRule(
			actions = "setValue('required', isRequiredObjectField(getValue('objectFieldName')))",
			condition = "hasObjectField(getValue('objectFieldName'))"
		),
		@DDMFormRule(
			actions = {
				"setDataType('predefinedValue', getValue('dataType'))",
				"setEnabled('required', not(hasObjectField(getValue('objectFieldName'))))",
				"setPropertyValue('predefinedValue', 'inputMask', getValue('inputMask'))",
				"setPropertyValue('predefinedValue', 'inputMaskFormat', getLocalizedValue('inputMaskFormat'))",
				"setPropertyValue('predefinedValue', 'numericInputMask', getLocalizedValue('numericInputMask'))",
				"setPropertyValue('validation', 'inputMask', getValue('inputMask'))",
				"setPropertyValue('validation', 'inputMaskFormat', getLocalizedValue('inputMaskFormat'))",
				"setPropertyValue('validation', 'numericInputMask', getLocalizedValue('numericInputMask'))",
				"setValidationDataType('validation', getValue('dataType'))",
				"setValidationFieldName('validation', getValue('name'))",
				"setVisible('characterOptions', equals(getValue('dataType'), 'integer') and equals(getValue('inputMask'), TRUE))",
				"setVisible('confirmationErrorMessage', getValue('requireConfirmation'))",
				"setVisible('confirmationLabel', getValue('requireConfirmation'))",
				"setVisible('direction', getValue('requireConfirmation'))",
				"setVisible('inputMaskFormat', equals(getValue('dataType'), 'integer') and equals(getValue('inputMask'), TRUE))",
				"setVisible('numericInputMask', equals(getValue('dataType'), 'double') and equals(getValue('inputMask'), TRUE))",
				"setVisible('requiredErrorMessage', getValue('required'))",
				"setVisible('tooltip', false)"
			},
			condition = "TRUE"
		)
	}
)
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
	value = {
		@DDMFormLayoutPage(
			title = "%basic",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"label", "placeholder", "tip", "dataType",
								"required", "requiredErrorMessage"
							}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%advanced",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"fieldReference", "name",
								"htmlAutocompleteAttribute", "predefinedValue",
								"objectFieldName", "visibilityExpression",
								"fieldNamespace", "indexType",
								"labelAtStructureLevel", "localizable",
								"nativeField", "readOnly", "type", "hideField",
								"showLabel", "repeatable",
								"requireConfirmation", "direction",
								"confirmationLabel", "confirmationErrorMessage",
								"validation", "tooltip", "inputMask",
								"inputMaskFormat", "characterOptions",
								"numericInputMask"
							}
						)
					}
				)
			}
		)
	}
)
public interface NumericDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

	@DDMFormField(label = "%character-options", type = "help_text")
	public boolean characterOptions();

	@DDMFormField(
		dataType = "string", label = "%error-message",
		properties = "initialValue=%the-information-does-not-match",
		type = "text"
	)
	public LocalizedValue confirmationErrorMessage();

	@DDMFormField(
		dataType = "string", label = "%label",
		properties = "initialValue=%confirm", type = "text"
	)
	public LocalizedValue confirmationLabel();

	@DDMFormField(
		label = "%my-numeric-type-is", optionLabels = {"%integer", "%decimal"},
		optionValues = {"integer", "double"}, predefinedValue = "integer",
		type = "radio"
	)
	@Override
	public String dataType();

	@DDMFormField(
		label = "%direction", optionLabels = {"%horizontal", "%vertical"},
		optionValues = {"horizontal", "vertical"},
		predefinedValue = "[\"vertical\"]",
		properties = "showEmptyOption=false", type = "select"
	)
	public String direction();

	@DDMFormField(
		label = "%hide-field",
		properties = {
			"showAsSwitcher=true",
			"tooltip=%the-user-filling-the-form-will-not-be-able-to-see-this-field"
		}
	)
	public boolean hideField();

	@DDMFormField(
		dataType = "string", label = "%html-autocomplete-attribute",
		properties = {
			"invalidCharacters=[^a-z0-9-]|-{2,}", "maxLength=20",
			"visualProperty=true"
		},
		type = "text"
	)
	public String htmlAutocompleteAttribute();

	@DDMFormField(label = "%input-mask", properties = "showAsSwitcher=true")
	public boolean inputMask();

	@DDMFormField(
		dataType = "string", label = "%format",
		properties = {
			"invalidCharacters=[1-8]",
			"placeholder=%input-mask-format-placeholder",
			"tooltip=%an-input-mask-helps-to-ensure-a-predefined-format"
		},
		required = true,
		tip = "%to-create-a-custom-input-mask-you-will-need-to-use-a-specific-set-of-characters",
		type = "text",
		validationErrorMessage = "%you-must-add-at-least-one-0-or-one-9",
		validationExpression = "match(inputMaskFormat, '^$|^(?=.*[09])([^1-8]+)$')"
	)
	public LocalizedValue inputMaskFormat();

	@DDMFormField(
		predefinedValue = "%{\"append\": \"\", \"appendType\": \"prefix\", \"decimalPlaces\": 2, \"symbols\": {\"decimalSymbol\": \".\", \"thousandsSeparator\": \"none\"}}",
		type = "numeric_input_mask"
	)
	public LocalizedValue numericInputMask();

	@DDMFormField(
		dataType = "string", label = "%placeholder-text",
		properties = {
			"tooltip=%enter-text-that-assists-the-user-but-is-not-submitted-as-a-field-value",
			"visualProperty=true"
		},
		type = "text"
	)
	public LocalizedValue placeholder();

	@DDMFormField(
		label = "%predefined-value",
		properties = {
			"placeholder=%enter-a-default-value",
			"tooltip=%enter-a-default-value-that-is-submitted-if-no-other-value-is-entered",
			"visualProperty=true"
		},
		type = "numeric"
	)
	@Override
	public LocalizedValue predefinedValue();

	@DDMFormField(
		label = "%require-confirmation", properties = "showAsSwitcher=true"
	)
	public boolean requireConfirmation();

	@DDMFormField
	public LocalizedValue tooltip();

	@DDMFormField(
		dataType = "numeric", label = "%validation", type = "validation"
	)
	@Override
	public DDMFormFieldValidation validation();

}