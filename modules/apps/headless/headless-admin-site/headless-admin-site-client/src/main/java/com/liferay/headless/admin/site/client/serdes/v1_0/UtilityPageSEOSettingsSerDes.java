/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.serdes.v1_0;

import com.liferay.headless.admin.site.client.dto.v1_0.UtilityPageSEOSettings;
import com.liferay.headless.admin.site.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
public class UtilityPageSEOSettingsSerDes {

	public static UtilityPageSEOSettings toDTO(String json) {
		UtilityPageSEOSettingsJSONParser utilityPageSEOSettingsJSONParser =
			new UtilityPageSEOSettingsJSONParser();

		return utilityPageSEOSettingsJSONParser.parseToDTO(json);
	}

	public static UtilityPageSEOSettings[] toDTOs(String json) {
		UtilityPageSEOSettingsJSONParser utilityPageSEOSettingsJSONParser =
			new UtilityPageSEOSettingsJSONParser();

		return utilityPageSEOSettingsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(UtilityPageSEOSettings utilityPageSEOSettings) {
		if (utilityPageSEOSettings == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (utilityPageSEOSettings.getDescription_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description_i18n\": ");

			sb.append(_toJSON(utilityPageSEOSettings.getDescription_i18n()));
		}

		if (utilityPageSEOSettings.getHtmlTitle_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"htmlTitle_i18n\": ");

			sb.append(_toJSON(utilityPageSEOSettings.getHtmlTitle_i18n()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		UtilityPageSEOSettingsJSONParser utilityPageSEOSettingsJSONParser =
			new UtilityPageSEOSettingsJSONParser();

		return utilityPageSEOSettingsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		UtilityPageSEOSettings utilityPageSEOSettings) {

		if (utilityPageSEOSettings == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (utilityPageSEOSettings.getDescription_i18n() == null) {
			map.put("description_i18n", null);
		}
		else {
			map.put(
				"description_i18n",
				String.valueOf(utilityPageSEOSettings.getDescription_i18n()));
		}

		if (utilityPageSEOSettings.getHtmlTitle_i18n() == null) {
			map.put("htmlTitle_i18n", null);
		}
		else {
			map.put(
				"htmlTitle_i18n",
				String.valueOf(utilityPageSEOSettings.getHtmlTitle_i18n()));
		}

		return map;
	}

	public static class UtilityPageSEOSettingsJSONParser
		extends BaseJSONParser<UtilityPageSEOSettings> {

		@Override
		protected UtilityPageSEOSettings createDTO() {
			return new UtilityPageSEOSettings();
		}

		@Override
		protected UtilityPageSEOSettings[] createDTOArray(int size) {
			return new UtilityPageSEOSettings[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "description_i18n")) {
				return true;
			}
			else if (Objects.equals(jsonParserFieldName, "htmlTitle_i18n")) {
				return true;
			}

			return false;
		}

		@Override
		protected void setField(
			UtilityPageSEOSettings utilityPageSEOSettings,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "description_i18n")) {
				if (jsonParserFieldValue != null) {
					utilityPageSEOSettings.setDescription_i18n(
						(Map<String, String>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "htmlTitle_i18n")) {
				if (jsonParserFieldValue != null) {
					utilityPageSEOSettings.setHtmlTitle_i18n(
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