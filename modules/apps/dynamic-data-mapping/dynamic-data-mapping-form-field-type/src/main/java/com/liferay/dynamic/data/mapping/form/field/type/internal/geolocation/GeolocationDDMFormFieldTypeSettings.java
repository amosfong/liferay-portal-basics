/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.geolocation;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.annotations.DDMFormRule;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;

/**
 * @author Marcela Cunha
 */
@DDMForm(
	rules = {
		@DDMFormRule(
			actions = {
				"setVisible('dataType', false)",
				"setVisible('predefinedValue', false)"
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
							size = 12, value = {"label", "tip", "required"}
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
								"fieldReference", "name", "indexType",
								"localizable", "readOnly", "dataType", "type",
								"showLabel", "repeatable"
							}
						)
					}
				)
			}
		)
	}
)
public interface GeolocationDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

	@DDMFormField(predefinedValue = "geolocation", required = true)
	@Override
	public String dataType();

	@DDMFormField(
		label = "%searchable", optionLabels = {"%disable", "%keyword", "%text"},
		optionValues = {"none", "keyword", "text"}, predefinedValue = "keyword",
		type = "radio"
	)
	@Override
	public String indexType();

}