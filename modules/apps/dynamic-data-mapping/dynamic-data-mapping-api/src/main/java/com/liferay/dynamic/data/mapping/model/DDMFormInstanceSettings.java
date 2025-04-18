/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.annotations.DDMFormRule;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Bruno Basto
 */
@DDMForm(
	rules = {
		@DDMFormRule(
			actions = {
				"setEnabled('expirationDate', equals(getValue('neverExpire'), FALSE))",
				"setVisible('emailFromAddress', getValue('sendEmailNotification'))",
				"setVisible('emailFromName', getValue('sendEmailNotification'))",
				"setVisible('emailSubject', getValue('sendEmailNotification'))",
				"setVisible('emailToAddress', getValue('sendEmailNotification'))",
				"setVisible('limitToOneSubmissionPerUserBody', getValue('limitToOneSubmissionPerUser'))",
				"setVisible('limitToOneSubmissionPerUserHeader', getValue('limitToOneSubmissionPerUser'))",
				"setVisible('objectDefinitionId', contains(getValue('storageType'), \"object\"))",
				"setVisible('published', FALSE)",
				"setVisible('workflowDefinition', not(contains(getValue('storageType'), \"object\")))"
			},
			condition = "TRUE"
		),
		@DDMFormRule(
			actions = "setValue('expirationDate', '')",
			condition = "equals(getValue('neverExpire'), TRUE)"
		),
		@DDMFormRule(
			actions = {
				"setEnabled('autosaveEnabled', FALSE)",
				"setValue('autosaveEnabled', FALSE)",
				"setValue('workflowDefinition', [\"no-workflow\"])"
			},
			condition = "contains(getValue('storageType'), \"object\")"
		),
		@DDMFormRule(
			actions = "setEnabled('autosaveEnabled', TRUE)",
			condition = "not(contains(getValue('storageType'), \"object\"))"
		)
	}
)
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
	value = {
		@DDMFormLayoutPage(
			title = "%general",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"requireAuthentication", "requireCaptcha",
								"autosaveEnabled", "storageType",
								"objectDefinitionId", "workflowDefinition"
							}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%personalization",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12, value = {"redirectURL", "submitLabel"}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%notifications",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"sendEmailNotification", "emailFromName",
								"emailFromAddress", "emailToAddress",
								"emailSubject", "published"
							}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%submissions",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"displayChartAsTable",
								"showPartialResultsToRespondents",
								"limitToOneSubmissionPerUser",
								"limitToOneSubmissionPerUserHeader",
								"limitToOneSubmissionPerUserBody",
								"expirationDate", "neverExpire"
							}
						)
					}
				)
			}
		)
	}
)
@ProviderType
public interface DDMFormInstanceSettings {

	@DDMFormField(
		label = "%save-answers-automatically", predefinedValue = "true",
		properties = "showAsSwitcher=true"
	)
	public boolean autosaveEnabled();

	@DDMFormField
	public boolean convertedFromPolls();

	@DDMFormField(
		label = "%display-chart-as-table",
		tip = "%display-entries-of-the-following-field-types-as-tables-select-from-list-single-selection-multiple-selection-and-boolean",
		type = "checkbox"
	)
	public boolean displayChartAsTable();

	@DDMFormField(
		label = "%from-address",
		validationErrorMessage = "%please-enter-a-valid-email-address",
		validationExpression = "isEmailAddress(emailFromAddress)"
	)
	public String emailFromAddress();

	@DDMFormField(label = "%from-name")
	public String emailFromName();

	@DDMFormField(label = "%subject")
	public String emailSubject();

	@DDMFormField(
		label = "%to-address",
		validationErrorMessage = "%please-enter-valid-email-addresses-separated-by-commas",
		validationExpression = "isEmailAddress(emailToAddress)"
	)
	public String emailToAddress();

	@DDMFormField(
		label = "%expiration-date", type = "date",
		validationErrorMessage = "%please-enter-a-valid-expiration-date-only-future-dates-are-accepted",
		validationExpression = "futureDates(expirationDate, \"{parameter}\")",
		validationExpressionName = "futureDates",
		validationParameter = "{\"startsFrom\": {\"date\": \"responseDate\", \"quantity\": 1, \"type\": \"customDate\", \"unit\": \"days\"}}"
	)
	public String expirationDate();

	@DDMFormField(
		label = "%limit-to-one-submission-per-user",
		tip = "%respondents-will-be-required-to-sign-in", type = "checkbox"
	)
	public boolean limitToOneSubmissionPerUser();

	@DDMFormField(
		label = "%body",
		properties = {
			"displayStyle=multiline",
			"placeholder=%you-can-fill-out-this-form-only-once.-contact-the-owner-of-the-form-if-you-think-this-is-a-mistake"
		},
		type = "localizable_text"
	)
	public String limitToOneSubmissionPerUserBody();

	@DDMFormField(
		label = "%header",
		properties = "placeholder=%you-have-already-responded",
		type = "localizable_text"
	)
	public String limitToOneSubmissionPerUserHeader();

	@DDMFormField(
		label = "%never-expire", predefinedValue = "true", type = "checkbox"
	)
	public boolean neverExpire();

	@DDMFormField(
		label = "%select-object",
		properties = {
			"dataSourceType=data-provider", "ddmDataProviderInstanceId=objects"
		},
		type = "select"
	)
	public String objectDefinitionId();

	@DDMFormField
	public boolean published();

	@DDMFormField(
		label = "%redirect-url-on-success",
		properties = "placeholder=%enter-a-valid-url",
		validationErrorMessage = "%please-enter-a-valid-url",
		validationExpression = "isEmpty(redirectURL) OR isURL(redirectURL, TRUE)"
	)
	public String redirectURL();

	@DDMFormField(
		label = "%require-user-authentication", predefinedValue = "false",
		properties = "showAsSwitcher=true"
	)
	public boolean requireAuthentication();

	@DDMFormField(
		label = "%require-captcha", properties = "showAsSwitcher=true",
		type = "checkbox"
	)
	public boolean requireCaptcha();

	@DDMFormField(
		label = "%send-an-email-notification-for-each-entry",
		properties = "showAsSwitcher=true", type = "checkbox"
	)
	public boolean sendEmailNotification();

	@DDMFormField(
		label = "%show-forms-report-data-to-respondents",
		tip = "%allow-respondents-to-see-the-current-forms-report-data",
		type = "checkbox"
	)
	public boolean showPartialResultsToRespondents();

	@DDMFormField(
		label = "%select-a-storage-type", predefinedValue = "[\"default\"]",
		properties = {
			"dataSourceType=data-provider",
			"ddmDataProviderInstanceId=ddm-storage-types"
		},
		type = "select"
	)
	public String storageType();

	@DDMFormField(
		label = "%submit-button-label", properties = "placeholder=%submit-form",
		type = "localizable_text"
	)
	public String submitLabel();

	@DDMFormField(
		label = "%select-a-workflow", predefinedValue = "[\"no-workflow\"]",
		properties = {
			"dataSourceType=data-provider",
			"ddmDataProviderInstanceId=workflow-definitions"
		},
		type = "select"
	)
	public String workflowDefinition();

}