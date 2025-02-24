/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.Language;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

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
public class LanguageSerDes {

	public static Language toDTO(String json) {
		LanguageJSONParser languageJSONParser = new LanguageJSONParser();

		return languageJSONParser.parseToDTO(json);
	}

	public static Language[] toDTOs(String json) {
		LanguageJSONParser languageJSONParser = new LanguageJSONParser();

		return languageJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Language language) {
		if (language == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (language.getCountryName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryName\": ");

			sb.append("\"");

			sb.append(_escape(language.getCountryName()));

			sb.append("\"");
		}

		if (language.getCountryName_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryName_i18n\": ");

			sb.append(_toJSON(language.getCountryName_i18n()));
		}

		if (language.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(language.getId()));

			sb.append("\"");
		}

		if (language.getMarkedAsDefault() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"markedAsDefault\": ");

			sb.append(language.getMarkedAsDefault());
		}

		if (language.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(language.getName()));

			sb.append("\"");
		}

		if (language.getName_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name_i18n\": ");

			sb.append(_toJSON(language.getName_i18n()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		LanguageJSONParser languageJSONParser = new LanguageJSONParser();

		return languageJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Language language) {
		if (language == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (language.getCountryName() == null) {
			map.put("countryName", null);
		}
		else {
			map.put("countryName", String.valueOf(language.getCountryName()));
		}

		if (language.getCountryName_i18n() == null) {
			map.put("countryName_i18n", null);
		}
		else {
			map.put(
				"countryName_i18n",
				String.valueOf(language.getCountryName_i18n()));
		}

		if (language.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(language.getId()));
		}

		if (language.getMarkedAsDefault() == null) {
			map.put("markedAsDefault", null);
		}
		else {
			map.put(
				"markedAsDefault",
				String.valueOf(language.getMarkedAsDefault()));
		}

		if (language.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(language.getName()));
		}

		if (language.getName_i18n() == null) {
			map.put("name_i18n", null);
		}
		else {
			map.put("name_i18n", String.valueOf(language.getName_i18n()));
		}

		return map;
	}

	public static class LanguageJSONParser extends BaseJSONParser<Language> {

		@Override
		protected Language createDTO() {
			return new Language();
		}

		@Override
		protected Language[] createDTOArray(int size) {
			return new Language[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "countryName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "countryName_i18n")) {
				return true;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "markedAsDefault")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "name_i18n")) {
				return true;
			}

			return false;
		}

		@Override
		protected void setField(
			Language language, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "countryName")) {
				if (jsonParserFieldValue != null) {
					language.setCountryName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "countryName_i18n")) {
				if (jsonParserFieldValue != null) {
					language.setCountryName_i18n(
						(Map<String, String>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					language.setId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "markedAsDefault")) {
				if (jsonParserFieldValue != null) {
					language.setMarkedAsDefault((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					language.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name_i18n")) {
				if (jsonParserFieldValue != null) {
					language.setName_i18n(
						(Map<String, String>)jsonParserFieldValue);
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