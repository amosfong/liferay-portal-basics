/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.strategy;

import com.liferay.data.engine.rest.strategy.util.DataRecordValueKeyUtil;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.util.DDMFormFieldParameterNameUtil;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesFactoryUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Jeyvison Nascimento
 * @author Leonardo Barros
 */
public class DefaultMapToDDMFormValuesConverterStrategy
	implements MapToDDMFormValuesConverterStrategy {

	public static DefaultMapToDDMFormValuesConverterStrategy getInstance() {
		return _defaultMapToDDMFormValuesConverterStrategy;
	}

	@Override
	public void setDDMFormFieldValues(
		Map<String, Object> dataRecordValues, DDMForm ddmForm,
		DDMFormValues ddmFormValues, Locale locale) {

		Map<String, DDMFormFieldValue> ddmFormFieldValues = new HashMap<>();

		_createDDMFormFieldValues(
			dataRecordValues, ddmForm.getDDMFormFields(), ddmFormFieldValues,
			ddmForm.getDefaultLocale(), locale, StringPool.BLANK);

		ddmFormValues.setDDMFormFieldValues(
			DDMFormValuesFactoryUtil.getDDMFormFieldValues(
				ddmFormFieldValues, ddmForm.getDDMFormFields()));
	}

	private DefaultMapToDDMFormValuesConverterStrategy() {
	}

	private void _addString(Locale locale, Object object, Value value) {
		if (object == null) {
			value.addString(locale, null);
		}
		else if (object instanceof List) {
			value.addString(locale, JSONFactoryUtil.looseSerializeDeep(object));
		}
		else {
			value.addString(locale, String.valueOf(object));
		}
	}

	private void _createDDMFormFieldValues(
		Map<String, Object> dataRecordValues, DDMFormField ddmFormField,
		Map<String, DDMFormFieldValue> ddmFormFieldValues, Locale defaultLocale,
		Locale locale, String parentDataRecordValueKey) {

		boolean hasDataRecordValue = false;

		for (Map.Entry<String, Object> entry : dataRecordValues.entrySet()) {
			String dataRecordValueKey = entry.getKey();

			String[] dataRecordValueKeyParts =
				DDMFormFieldParameterNameUtil.
					getLastDDMFormFieldParameterNameParts(dataRecordValueKey);

			String dataRecordValueFieldName = dataRecordValueKeyParts
				[DDMFormFieldParameterNameUtil.DDM_FORM_FIELD_NAME_INDEX];

			if (!_isDataRecordValueFromDDMFormField(
					dataRecordValueFieldName, dataRecordValueKey,
					ddmFormField.getName(), parentDataRecordValueKey)) {

				continue;
			}

			DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue() {
				{
					setInstanceId(
						dataRecordValueKeyParts
							[DDMFormFieldParameterNameUtil.
								DDM_FORM_FIELD_INSTANCE_ID_INDEX]);
					setName(dataRecordValueFieldName);
				}
			};

			if ((entry.getValue() != null) && ddmFormField.isLocalizable() &&
				!ddmFormField.isTransient()) {

				Value value = new LocalizedValue();

				Map<String, Object> localizedValues =
					(Map<String, Object>)entry.getValue();

				if (locale == null) {
					for (Map.Entry<String, Object> localizedValue :
							localizedValues.entrySet()) {

						_addString(
							LocaleUtil.fromLanguageId(localizedValue.getKey()),
							localizedValues.get(localizedValue.getKey()),
							value);
					}
				}
				else {
					_addString(
						locale,
						GetterUtil.getObject(
							localizedValues.get(
								LocaleUtil.toLanguageId(locale)),
							localizedValues.get(
								LocaleUtil.toLanguageId(defaultLocale))),
						value);
				}

				ddmFormFieldValue.setValue(value);
			}
			else if (entry.getValue() != null) {
				ddmFormFieldValue.setValue(
					new UnlocalizedValue((String)entry.getValue()));
			}

			ddmFormFieldValues.put(dataRecordValueKey, ddmFormFieldValue);

			if (ListUtil.isNotEmpty(ddmFormField.getNestedDDMFormFields())) {
				_createDDMFormFieldValues(
					dataRecordValues, ddmFormField.getNestedDDMFormFields(),
					ddmFormFieldValues, defaultLocale, locale,
					dataRecordValueKey);
			}

			hasDataRecordValue = true;
		}

		if (hasDataRecordValue) {
			return;
		}

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue() {
			{
				setName(ddmFormField.getName());
			}
		};

		String dataRecordValueKey =
			DataRecordValueKeyUtil.createDataRecordValueKey(
				ddmFormField.getName(), ddmFormFieldValue.getInstanceId(),
				parentDataRecordValueKey, 0);

		ddmFormFieldValues.put(dataRecordValueKey, ddmFormFieldValue);

		if (ListUtil.isNotEmpty(ddmFormField.getNestedDDMFormFields())) {
			_createDDMFormFieldValues(
				dataRecordValues, ddmFormField.getNestedDDMFormFields(),
				ddmFormFieldValues, defaultLocale, locale, dataRecordValueKey);
		}
	}

	private void _createDDMFormFieldValues(
		Map<String, Object> dataRecordValues, List<DDMFormField> ddmFormFields,
		Map<String, DDMFormFieldValue> ddmFormFieldValues, Locale defaultLocale,
		Locale locale, String parentDataRecordValueKey) {

		for (DDMFormField ddmFormField : ddmFormFields) {
			_createDDMFormFieldValues(
				dataRecordValues, ddmFormField, ddmFormFieldValues,
				defaultLocale, locale, parentDataRecordValueKey);
		}
	}

	private boolean _isDataRecordValueFromDDMFormField(
		String dataRecordValueFieldName, String dataRecordValueKey,
		String ddmFormFieldName, String parentDataRecordValueKey) {

		if (StringUtil.equals(ddmFormFieldName, dataRecordValueFieldName) &&
			(Validator.isNull(parentDataRecordValueKey) ||
			 dataRecordValueKey.contains(parentDataRecordValueKey))) {

			return true;
		}

		return false;
	}

	private static final DefaultMapToDDMFormValuesConverterStrategy
		_defaultMapToDDMFormValuesConverterStrategy =
			new DefaultMapToDDMFormValuesConverterStrategy();

}