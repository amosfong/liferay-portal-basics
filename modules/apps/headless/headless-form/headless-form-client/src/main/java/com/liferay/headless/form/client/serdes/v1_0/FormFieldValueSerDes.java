/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.form.client.serdes.v1_0;

import com.liferay.headless.form.client.dto.v1_0.FormFieldValue;
import com.liferay.headless.form.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class FormFieldValueSerDes {

	public static FormFieldValue toDTO(String json) {
		FormFieldValueJSONParser formFieldValueJSONParser =
			new FormFieldValueJSONParser();

		return formFieldValueJSONParser.parseToDTO(json);
	}

	public static FormFieldValue[] toDTOs(String json) {
		FormFieldValueJSONParser formFieldValueJSONParser =
			new FormFieldValueJSONParser();

		return formFieldValueJSONParser.parseToDTOs(json);
	}

	public static String toJSON(FormFieldValue formFieldValue) {
		if (formFieldValue == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (formFieldValue.getFormDocument() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formDocument\": ");

			sb.append(String.valueOf(formFieldValue.getFormDocument()));
		}

		if (formFieldValue.getFormDocumentId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formDocumentId\": ");

			sb.append(formFieldValue.getFormDocumentId());
		}

		if (formFieldValue.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(formFieldValue.getId());
		}

		if (formFieldValue.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(formFieldValue.getName()));

			sb.append("\"");
		}

		if (formFieldValue.getValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value\": ");

			sb.append("\"");

			sb.append(_escape(formFieldValue.getValue()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FormFieldValueJSONParser formFieldValueJSONParser =
			new FormFieldValueJSONParser();

		return formFieldValueJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(FormFieldValue formFieldValue) {
		if (formFieldValue == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (formFieldValue.getFormDocument() == null) {
			map.put("formDocument", null);
		}
		else {
			map.put(
				"formDocument",
				String.valueOf(formFieldValue.getFormDocument()));
		}

		if (formFieldValue.getFormDocumentId() == null) {
			map.put("formDocumentId", null);
		}
		else {
			map.put(
				"formDocumentId",
				String.valueOf(formFieldValue.getFormDocumentId()));
		}

		if (formFieldValue.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(formFieldValue.getId()));
		}

		if (formFieldValue.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(formFieldValue.getName()));
		}

		if (formFieldValue.getValue() == null) {
			map.put("value", null);
		}
		else {
			map.put("value", String.valueOf(formFieldValue.getValue()));
		}

		return map;
	}

	public static class FormFieldValueJSONParser
		extends BaseJSONParser<FormFieldValue> {

		@Override
		protected FormFieldValue createDTO() {
			return new FormFieldValue();
		}

		@Override
		protected FormFieldValue[] createDTOArray(int size) {
			return new FormFieldValue[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "formDocument")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "formDocumentId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			FormFieldValue formFieldValue, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "formDocument")) {
				if (jsonParserFieldValue != null) {
					formFieldValue.setFormDocument(
						FormDocumentSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "formDocumentId")) {
				if (jsonParserFieldValue != null) {
					formFieldValue.setFormDocumentId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					formFieldValue.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					formFieldValue.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				if (jsonParserFieldValue != null) {
					formFieldValue.setValue((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}