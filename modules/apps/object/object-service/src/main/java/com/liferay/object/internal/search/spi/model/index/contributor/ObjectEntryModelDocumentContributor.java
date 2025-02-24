/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.search.spi.model.index.contributor;

import com.liferay.account.model.AccountEntryOrganizationRel;
import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.entry.util.ObjectEntryValuesUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.FieldArray;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.BigDecimalUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.io.Serializable;

import java.math.BigDecimal;

import java.text.Format;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marco Leo
 * @author Brian Wing Shun Chan
 */
public class ObjectEntryModelDocumentContributor
	implements ModelDocumentContributor<ObjectEntry> {

	public ObjectEntryModelDocumentContributor(
		AccountEntryOrganizationRelLocalService
			accountEntryOrganizationRelLocalService,
		String className,
		ObjectDefinitionLocalService objectDefinitionLocalService,
		ObjectEntryLocalService objectEntryLocalService,
		ObjectFieldLocalService objectFieldLocalService) {

		_accountEntryOrganizationRelLocalService =
			accountEntryOrganizationRelLocalService;
		_className = className;
		_objectDefinitionLocalService = objectDefinitionLocalService;
		_objectEntryLocalService = objectEntryLocalService;
		_objectFieldLocalService = objectFieldLocalService;
	}

	@Override
	public void contribute(Document document, ObjectEntry objectEntry) {
		try {
			_contribute(document, objectEntry);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to index object entry " +
						objectEntry.getObjectEntryId(),
					exception);
			}
		}
	}

	private void _addField(
		FieldArray fieldArray, String fieldName, String valueFieldName,
		String value) {

		Field field = new Field("");

		field.addField(new Field("fieldName", fieldName));
		field.addField(new Field("valueFieldName", valueFieldName));
		field.addField(new Field(valueFieldName, value));

		fieldArray.addField(field);
	}

	private void _appendToContent(
		StringBundler sb, String objectFieldName, String valueString) {

		sb.append(objectFieldName);
		sb.append(": ");
		sb.append(valueString);
		sb.append(StringPool.COMMA_AND_SPACE);
	}

	private void _contribute(
		Document document, FieldArray fieldArray, String fieldName,
		Object fieldValue, String locale, ObjectDefinition objectDefinition,
		ObjectEntry objectEntry, ObjectField objectField, StringBundler sb,
		Map<String, Serializable> values) {

		if (!objectField.isIndexed()) {
			return;
		}

		if (fieldValue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Object entry ", objectEntry.getObjectEntryId(),
						" has object field \"", objectField.getName(),
						"\" with a null value"));
			}

			return;
		}

		if (StringUtil.equals(
				objectField.getBusinessType(),
				ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT) ||
			StringUtil.equals(
				objectField.getBusinessType(),
				ObjectFieldConstants.BUSINESS_TYPE_RICH_TEXT)) {

			fieldValue = ObjectEntryValuesUtil.getValueString(
				objectField, values);
		}
		else if (StringUtil.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_PRECISION_DECIMAL)) {

			fieldValue = BigDecimalUtil.stripTrailingZeros(
				(BigDecimal)fieldValue);
		}
		else if (Objects.equals(
					objectDefinition.getAccountEntryRestrictedObjectFieldId(),
					objectField.getObjectFieldId())) {

			Long accountEntryId = (Long)fieldValue;

			document.addKeyword(
				"accountEntryRestrictedObjectFieldValue", accountEntryId);

			document.addKeyword(
				"accountEntryRestrictedOrganizationIds",
				TransformUtil.transformToArray(
					_accountEntryOrganizationRelLocalService.
						getAccountEntryOrganizationRels(accountEntryId),
					AccountEntryOrganizationRel::getOrganizationId,
					Long.class));
		}

		String valueString = String.valueOf(fieldValue);

		if (objectField.isIndexedAsKeyword()) {
			_addField(
				fieldArray, fieldName, "value_keyword",
				StringUtil.lowerCase(valueString));

			_appendToContent(sb, fieldName, valueString);
		}
		else if (fieldValue instanceof BigDecimal) {
			_addField(fieldArray, fieldName, "value_double", valueString);

			_appendToContent(sb, fieldName, valueString);
		}
		else if (fieldValue instanceof Boolean) {
			_addField(fieldArray, fieldName, "value_boolean", valueString);
			_addField(
				fieldArray, fieldName, "value_keyword",
				_translate((Boolean)fieldValue));

			_appendToContent(sb, fieldName, valueString);
		}
		else if (fieldValue instanceof Date) {
			_addField(
				fieldArray, fieldName, "value_date",
				_getDateString(fieldValue));

			_appendToContent(sb, fieldName, _getDateString(fieldValue));
		}
		else if (fieldValue instanceof Double) {
			_addField(fieldArray, fieldName, "value_double", valueString);

			_appendToContent(sb, fieldName, valueString);
		}
		else if (fieldValue instanceof Integer) {
			_addField(fieldArray, fieldName, "value_integer", valueString);

			_appendToContent(sb, fieldName, valueString);
		}
		else if (fieldValue instanceof Long) {
			_addField(fieldArray, fieldName, "value_long", valueString);

			_appendToContent(sb, fieldName, valueString);
		}
		else if (fieldValue instanceof String) {
			if (Validator.isBlank(objectField.getIndexedLanguageId())) {
				_addField(fieldArray, fieldName, "value_text", valueString);
			}
			else if (objectField.isLocalized()) {
				_addField(
					fieldArray, fieldName, "value_" + locale, valueString);
			}
			else {
				_addField(
					fieldArray, fieldName,
					"value_" + objectField.getIndexedLanguageId(), valueString);
			}

			_addField(
				fieldArray, fieldName, "value_keyword_lowercase",
				_getSortableValue(valueString));

			_appendToContent(sb, fieldName, valueString);
		}
		else if (fieldValue instanceof byte[]) {
			_addField(
				fieldArray, fieldName, "value_binary",
				Base64.encode((byte[])fieldValue));
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Object entry ", objectEntry.getObjectEntryId(),
						" has object field \"", fieldName,
						"\" with unsupported value ", fieldValue));
			}
		}
	}

	private void _contribute(Document document, ObjectEntry objectEntry)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Document " + document);
			_log.debug("Object entry " + objectEntry);
		}

		document.add(
			new Field(
				Field.getSortableFieldName(Field.ENTRY_CLASS_PK),
				document.get(Field.ENTRY_CLASS_PK)));

		FieldArray fieldArray = (FieldArray)document.getField(
			"nestedFieldArray");

		if (fieldArray == null) {
			fieldArray = new FieldArray("nestedFieldArray");

			document.add(fieldArray);
		}

		document.addKeyword(
			"objectDefinitionId", objectEntry.getObjectDefinitionId());

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				objectEntry.getObjectDefinitionId());

		document.addKeyword(
			"objectDefinitionName", objectDefinition.getShortName());

		Map<String, Serializable> values = objectEntry.getValues();

		List<ObjectField> objectFields =
			_objectFieldLocalService.getObjectFields(
				objectEntry.getObjectDefinitionId(), false);

		StringBundler sb = new StringBundler(objectFields.size() * 4);

		for (ObjectField objectField : objectFields) {
			if (objectField.isLocalized()) {
				Map<String, String> localizedValues =
					(Map<String, String>)values.get(
						objectField.getI18nObjectFieldName());

				if (MapUtil.isEmpty(localizedValues)) {
					continue;
				}

				for (Map.Entry<String, String> localeMap :
						localizedValues.entrySet()) {

					_contribute(
						document, fieldArray, objectField.getName(),
						localizedValues.get(localeMap.getKey()),
						LocaleUtil.fromLanguageId(
							localeMap.getKey(), true, false
						).toString(),
						objectDefinition, objectEntry, objectField, sb, values);
				}
			}
			else {
				_contribute(
					document, fieldArray, objectField.getName(),
					values.get(objectField.getName()), null, objectDefinition,
					objectEntry, objectField, sb, values);
			}
		}

		if (sb.index() > 0) {
			sb.setIndex(sb.index() - 1);
		}

		document.add(new Field("objectEntryContent", sb.toString()));

		document.add(
			new Field("objectEntryTitle", objectEntry.getTitleValue()));
	}

	private String _getDateString(Object value) {
		return _format.format(value);
	}

	private String _getSortableValue(String value) {
		if (value.length() > 256) {
			return value.substring(0, 256);
		}

		return value;
	}

	private String _translate(Boolean value) {
		if (value.booleanValue()) {
			return "yes";
		}

		return "no";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntryModelDocumentContributor.class);

	private static final Format _format =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyyMMddHHmmss");

	private final AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;
	private final String _className;
	private final ObjectDefinitionLocalService _objectDefinitionLocalService;
	private final ObjectEntryLocalService _objectEntryLocalService;
	private final ObjectFieldLocalService _objectFieldLocalService;

}