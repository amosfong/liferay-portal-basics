/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.form.dto.v1_0.util;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.model.DDMFormRule;
import com.liferay.dynamic.data.mapping.model.DDMFormSuccessPageSettings;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.headless.form.dto.v1_0.FormField;
import com.liferay.headless.form.dto.v1_0.FormFieldOption;
import com.liferay.headless.form.dto.v1_0.FormPage;
import com.liferay.headless.form.dto.v1_0.FormStructure;
import com.liferay.headless.form.dto.v1_0.FormSuccessPage;
import com.liferay.headless.form.dto.v1_0.Grid;
import com.liferay.headless.form.dto.v1_0.Validation;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Victor Oliveira
 */
public class StructureUtil {

	public static FormStructure toFormStructure(
			boolean acceptAllLanguages, DDMStructure ddmStructure,
			Locale locale, Portal portal, UserLocalService userLocalService)
		throws PortalException {

		return new FormStructure() {
			{
				setAvailableLanguages(
					() -> LocaleUtil.toW3cLanguageIds(
						ddmStructure.getAvailableLanguageIds()));
				setCreator(
					() -> CreatorUtil.toCreator(
						portal,
						userLocalService.fetchUser(ddmStructure.getUserId())));
				setDateCreated(ddmStructure::getCreateDate);
				setDateModified(ddmStructure::getModifiedDate);
				setDescription(() -> ddmStructure.getDescription(locale));
				setDescription_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						acceptAllLanguages, ddmStructure.getDescriptionMap()));
				setFormPages(
					() -> {
						DDMFormLayout ddmFormLayout =
							ddmStructure.getDDMFormLayout();

						return TransformUtil.transformToArray(
							ddmFormLayout.getDDMFormLayoutPages(),
							ddmFormLayoutPage -> _toFormPage(
								acceptAllLanguages, ddmFormLayoutPage,
								ddmStructure, locale),
							FormPage.class);
					});
				setFormSuccessPage(
					() -> {
						DDMForm ddmForm = ddmStructure.getDDMForm();

						DDMFormSuccessPageSettings ddmFormSuccessPageSettings =
							ddmForm.getDDMFormSuccessPageSettings();

						if (!ddmFormSuccessPageSettings.isEnabled()) {
							return null;
						}

						LocalizedValue bodyLocalizedValue =
							ddmFormSuccessPageSettings.getBody();
						LocalizedValue titleLocalizedValue =
							ddmFormSuccessPageSettings.getTitle();

						return new FormSuccessPage() {
							{
								setDescription(
									() -> _toString(
										locale, bodyLocalizedValue));
								setDescription_i18n(
									() -> LocalizedMapUtil.getI18nMap(
										acceptAllLanguages,
										bodyLocalizedValue.getValues()));
								setHeadline(
									() -> _toString(
										locale, titleLocalizedValue));
								setHeadline_i18n(
									() -> LocalizedMapUtil.getI18nMap(
										acceptAllLanguages,
										titleLocalizedValue.getValues()));
							}
						};
					});
				setId(ddmStructure::getStructureId);
				setName(() -> ddmStructure.getName(locale));
				setName_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						acceptAllLanguages, ddmStructure.getNameMap()));
				setSiteId(ddmStructure::getGroupId);
			}
		};
	}

	private static List<String> _getNestedDDMFormFieldNames(
		List<String> ddmFormFieldNames, DDMStructure ddmStructure) {

		List<String> nestedDDMFormFieldNames = new ArrayList<>();

		for (DDMFormField ddmFormField : ddmStructure.getDDMFormFields(true)) {
			if (!ddmFormFieldNames.contains(ddmFormField.getName())) {
				continue;
			}

			nestedDDMFormFieldNames.addAll(
				_getNestedDDMFormFieldNames(
					TransformUtil.transform(
						ddmFormField.getNestedDDMFormFields(),
						DDMFormField::getName),
					ddmStructure));
		}

		nestedDDMFormFieldNames.addAll(ddmFormFieldNames);

		return nestedDDMFormFieldNames;
	}

	private static FormField _toFormField(
		boolean acceptAllLanguages, DDMFormField ddmFormField, Locale locale) {

		LocalizedValue labelLocalizedValue = ddmFormField.getLabel();
		LocalizedValue predefinedLocalizedValue =
			ddmFormField.getPredefinedValue();
		String type = ddmFormField.getType();

		return new FormField() {
			{
				setDataType(
					() -> {
						if (Objects.equals(type, "date")) {
							return type;
						}

						if (Objects.equals(type, "document_library")) {
							return "document";
						}

						if (Objects.equals(type, "paragraph")) {
							return "string";
						}

						return ddmFormField.getDataType();
					});
				setDisplayStyle(
					() -> GetterUtil.getString(
						ddmFormField.getProperty("displayStyle")));
				setFormFieldOptions(
					() -> {
						DDMFormFieldOptions ddmFormFieldOptions =
							ddmFormField.getDDMFormFieldOptions();

						if (ddmFormFieldOptions == null) {
							return new FormFieldOption[0];
						}

						Map<String, LocalizedValue> ddmFormFieldOptionsMap =
							ddmFormFieldOptions.getOptions();

						return TransformUtil.transformToArray(
							ddmFormFieldOptionsMap.entrySet(),
							entry -> _toFormFieldOption(
								acceptAllLanguages, entry, locale),
							FormFieldOption.class);
					});
				setGrid(
					() -> {
						if (!Objects.equals(type, "grid")) {
							return null;
						}

						return new Grid() {
							{
								setColumns(
									() -> TransformUtil.transform(
										_toMapEntry(ddmFormField, "columns"),
										entry -> _toFormFieldOption(
											acceptAllLanguages, entry, locale),
										FormFieldOption.class));
								setRows(
									() -> TransformUtil.transform(
										_toMapEntry(ddmFormField, "rows"),
										entry -> _toFormFieldOption(
											acceptAllLanguages, entry, locale),
										FormFieldOption.class));
							}
						};
					});
				setHasFormRules(
					() -> {
						DDMForm ddmForm = ddmFormField.getDDMForm();

						for (DDMFormRule ddmFormRule :
								ddmForm.getDDMFormRules()) {

							String condition = ddmFormRule.getCondition();

							if (condition.contains(ddmFormField.getName())) {
								return true;
							}
						}

						return false;
					});
				setImmutable(ddmFormField::isTransient);
				setInputControl(() -> type);
				setLabel(() -> _toString(locale, labelLocalizedValue));
				setLabel_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						acceptAllLanguages, labelLocalizedValue.getValues()));
				setLocalizable(ddmFormField::isLocalizable);
				setMultiple(ddmFormField::isMultiple);
				setName(ddmFormField::getName);
				setPlaceholder(
					() -> _toString(
						locale,
						(LocalizedValue)ddmFormField.getProperty(
							"placeholder")));
				setPredefinedValue(
					() -> _toString(locale, predefinedLocalizedValue));
				setPredefinedValue_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						acceptAllLanguages,
						predefinedLocalizedValue.getValues()));
				setRepeatable(ddmFormField::isRepeatable);
				setRequired(ddmFormField::isRequired);
				setShowAsSwitcher(
					() -> {
						if (!DDMFormFieldType.CHECKBOX.equals(type) &&
							!DDMFormFieldType.CHECKBOX_MULTIPLE.equals(type)) {

							return null;
						}

						return GetterUtil.getBoolean(
							ddmFormField.getProperty("showAsSwitcher"));
					});
				setShowLabel(ddmFormField::isShowLabel);
				setText(
					() -> {
						Object object = ddmFormField.getProperty("text");

						if (!(object instanceof LocalizedValue)) {
							return null;
						}

						return _toString(locale, (LocalizedValue)object);
					});
				setText_i18n(
					() -> {
						Object object = ddmFormField.getProperty("text");

						if (!(object instanceof LocalizedValue)) {
							return null;
						}

						LocalizedValue localizedValue = (LocalizedValue)object;

						return LocalizedMapUtil.getI18nMap(
							acceptAllLanguages, localizedValue.getValues());
					});
				setTooltip(
					() -> _toString(
						locale,
						(LocalizedValue)ddmFormField.getProperty("tip")));
				setValidation(
					() -> {
						Object object = ddmFormField.getProperty("validation");

						if (!(object instanceof DDMFormFieldValidation)) {
							return null;
						}

						DDMFormFieldValidation ddmFormFieldValidation =
							(DDMFormFieldValidation)object;

						LocalizedValue errorMessageLocalizedValue =
							ddmFormFieldValidation.
								getErrorMessageLocalizedValue();

						return new Validation() {
							{
								setErrorMessage(
									() -> errorMessageLocalizedValue.getString(
										locale));
								setErrorMessage_i18n(
									() -> LocalizedMapUtil.getI18nMap(
										acceptAllLanguages,
										errorMessageLocalizedValue.
											getValues()));
								setExpression(
									() ->
										ddmFormFieldValidation.getExpression());
							}
						};
					});
			}
		};
	}

	private static FormFieldOption _toFormFieldOption(
		boolean acceptAllLanguages, Map.Entry<String, LocalizedValue> entry,
		Locale locale) {

		LocalizedValue localizedValue = entry.getValue();

		return new FormFieldOption() {
			{
				setLabel(() -> _toString(locale, localizedValue));
				setLabel_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						acceptAllLanguages, localizedValue.getValues()));
				setValue(entry::getKey);
			}
		};
	}

	private static FormPage _toFormPage(
		boolean acceptAllLanguages, DDMFormLayoutPage ddmFormLayoutPage,
		DDMStructure ddmStructure, Locale locale) {

		List<String> ddmFormFieldNames = new ArrayList<>();

		for (DDMFormLayoutRow ddmFormLayoutRow :
				ddmFormLayoutPage.getDDMFormLayoutRows()) {

			for (DDMFormLayoutColumn ddmFormLayoutColumn :
					ddmFormLayoutRow.getDDMFormLayoutColumns()) {

				ddmFormFieldNames.addAll(
					_getNestedDDMFormFieldNames(
						ddmFormLayoutColumn.getDDMFormFieldNames(),
						ddmStructure));
			}
		}

		LocalizedValue titleLocalizedValue = ddmFormLayoutPage.getTitle();

		LocalizedValue descriptionLocalizedValue =
			ddmFormLayoutPage.getDescription();

		return new FormPage() {
			{
				setFormFields(
					() -> TransformUtil.transform(
						TransformUtil.transformToArray(
							ddmStructure.getDDMFormFields(true),
							ddmFormField -> {
								if (!ddmFormFieldNames.contains(
										ddmFormField.getName())) {

									return null;
								}

								return ddmFormField;
							},
							DDMFormField.class),
						ddmFormField -> _toFormField(
							acceptAllLanguages, ddmFormField, locale),
						FormField.class));
				setHeadline(() -> _toString(locale, titleLocalizedValue));
				setHeadline_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						acceptAllLanguages, titleLocalizedValue.getValues()));
				setText(
					() -> _toString(
						locale, ddmFormLayoutPage.getDescription()));
				setText_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						acceptAllLanguages,
						descriptionLocalizedValue.getValues()));
			}
		};
	}

	private static Map.Entry<String, LocalizedValue>[] _toMapEntry(
		DDMFormField ddmFormField, String name) {

		Object value = ddmFormField.getProperty(name);

		if (value == null) {
			return new Map.Entry[0];
		}

		DDMFormFieldOptions ddmFormFieldOptions = (DDMFormFieldOptions)value;

		Map<String, LocalizedValue> options = ddmFormFieldOptions.getOptions();

		Set<Map.Entry<String, LocalizedValue>> set = options.entrySet();

		return set.toArray(new Map.Entry[0]);
	}

	private static String _toString(
		Locale locale, LocalizedValue localizedValue) {

		if (localizedValue == null) {
			return null;
		}

		return localizedValue.getString(locale);
	}

}