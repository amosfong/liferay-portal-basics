/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.text;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.annotations.DDMFormRule;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;

/**
 * @author Lino Alves
 */
@DDMForm(
	rules = {
		@DDMFormRule(
			actions = "call('getDataProviderInstanceOutputParameters', 'dataProviderInstanceId=ddmDataProviderInstanceId', 'ddmDataProviderInstanceOutput=outputParameterNames')",
			condition = "not(equals(getValue('ddmDataProviderInstanceId'), ''))"
		),
		@DDMFormRule(
			actions = {
				"setVisible('autocomplete', TRUE)",
				"setVisible('repeatable', TRUE)",
				"setVisible('requireConfirmation', TRUE)",
				"setVisible('required', TRUE)", "setVisible('showLabel', TRUE)",
				"setVisible('validation', TRUE)"
			},
			condition = "equals(getValue('hideField'), FALSE)"
		),
		@DDMFormRule(
			actions = {
				"setValue('autocomplete', FALSE)",
				"setValue('repeatable', FALSE)",
				"setValue('requireConfirmation', FALSE)",
				"setValue('required', FALSE)", "setValue('showLabel', TRUE)",
				"setVisible('autocomplete', FALSE)",
				"setVisible('repeatable', FALSE)",
				"setVisible('requireConfirmation', FALSE)",
				"setVisible('required', FALSE)",
				"setVisible('showLabel', FALSE)",
				"setVisible('validation', FALSE)"
			},
			condition = "equals(getValue('hideField'), TRUE)"
		),
		@DDMFormRule(
			actions = {
				"setValue('autocomplete', FALSE)",
				"setValue('requireConfirmation', FALSE)",
				"setVisible('autocomplete', FALSE)",
				"setVisible('requireConfirmation', FALSE)"
			},
			condition = "not(equals(getValue('displayStyle'), 'singleline'))"
		),
		@DDMFormRule(
			actions = "setValue('required', isRequiredObjectField(getValue('objectFieldName')))",
			condition = "hasObjectField(getValue('objectFieldName'))"
		),
		@DDMFormRule(
			actions = {
				"setEnabled('required', not(hasObjectField(getValue('objectFieldName'))))",
				"setRequired('ddmDataProviderInstanceId', equals(getValue('dataSourceType'), \"data-provider\"))",
				"setRequired('ddmDataProviderInstanceOutput', equals(getValue('dataSourceType'), \"data-provider\"))",
				"setValidationDataType('validation', getValue('dataType'))",
				"setValidationFieldName('validation', getValue('name'))",
				"setVisible('confirmationErrorMessage', getValue('requireConfirmation'))",
				"setVisible('confirmationLabel', getValue('requireConfirmation'))",
				"setVisible('dataSourceType', getValue('autocomplete'))",
				"setVisible('ddmDataProviderInstanceId', equals(getValue('dataSourceType'), \"data-provider\") and getValue('autocomplete'))",
				"setVisible('ddmDataProviderInstanceOutput', equals(getValue('dataSourceType'), \"data-provider\") and getValue('autocomplete'))",
				"setVisible('direction', getValue('requireConfirmation'))",
				"setVisible('options', contains(getValue('dataSourceType'), \"manual\") and getValue('autocomplete'))",
				"setVisible('requiredErrorMessage', getValue('required'))"
			},
			condition = "TRUE"
		),
		@DDMFormRule(
			actions = {
				"setValue('ddmDataProviderInstanceId', '')",
				"setValue('ddmDataProviderInstanceOutput', '')"
			},
			condition = "not(equals(getValue('dataSourceType'), \"data-provider\")) or not(getValue('autocomplete'))"
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
								"label", "placeholder", "tip", "displayStyle",
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
								"nativeField", "readOnly", "dataType", "type",
								"hideField", "showLabel", "repeatable",
								"requireConfirmation", "direction",
								"confirmationLabel", "confirmationErrorMessage",
								"validation", "tooltip"
							}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%autocomplete",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"autocomplete", "dataSourceType",
								"ddmDataProviderInstanceId",
								"ddmDataProviderInstanceOutput", "options"
							}
						)
					}
				)
			}
		)
	}
)
public interface TextDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

	@DDMFormField(label = "%autocomplete", properties = "showAsSwitcher=true")
	public boolean autocomplete();

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
		label = "%create-list",
		optionLabels = {"%manually", "%from-data-provider"},
		optionValues = {"manual", "data-provider"},
		predefinedValue = "[\"manual\"]", properties = "showLabel=false",
		type = "radio"
	)
	public String dataSourceType();

	@DDMFormField(
		label = "%choose-a-data-provider",
		properties = {
			"dataSourceType=data-provider",
			"ddmDataProviderInstanceId=getDataProviderInstances"
		},
		type = "select"
	)
	public long ddmDataProviderInstanceId();

	@DDMFormField(
		label = "%choose-an-output-parameter",
		properties = "tooltip=%choose-an-output-parameter-for-a-data-provider-previously-created",
		type = "select"
	)
	public String ddmDataProviderInstanceOutput();

	@DDMFormField(
		label = "%direction", optionLabels = {"%horizontal", "%vertical"},
		optionValues = {"horizontal", "vertical"},
		predefinedValue = "[\"vertical\"]",
		properties = "showEmptyOption=false", type = "select"
	)
	public String direction();

	@DDMFormField(
		label = "%field-type",
		optionLabels = {"%single-line", "%multiple-lines"},
		optionValues = {"singleline", "multiline"},
		predefinedValue = "singleline", type = "radio"
	)
	public String displayStyle();

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

	@DDMFormField(
		label = "%searchable", optionLabels = {"%disable", "%keyword", "%text"},
		optionValues = {"none", "keyword", "text"}, predefinedValue = "keyword",
		type = "radio"
	)
	@Override
	public String indexType();

	@DDMFormField(
		dataType = "ddm-options", label = "%options",
		properties = {"showLabel=false", "allowEmptyOptions=true"},
		required = false, type = "options"
	)
	public DDMFormFieldOptions options();

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
		label = "%require-confirmation", properties = "showAsSwitcher=true"
	)
	public boolean requireConfirmation();

	@DDMFormField(visibilityExpression = "FALSE")
	public LocalizedValue tooltip();

	@DDMFormField(
		dataType = "string", label = "%validation", type = "validation"
	)
	@Override
	public DDMFormFieldValidation validation();

}