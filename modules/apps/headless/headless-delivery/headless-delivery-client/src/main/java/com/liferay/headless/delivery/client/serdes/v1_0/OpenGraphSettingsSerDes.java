/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.OpenGraphSettings;
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
public class OpenGraphSettingsSerDes {

	public static OpenGraphSettings toDTO(String json) {
		OpenGraphSettingsJSONParser openGraphSettingsJSONParser =
			new OpenGraphSettingsJSONParser();

		return openGraphSettingsJSONParser.parseToDTO(json);
	}

	public static OpenGraphSettings[] toDTOs(String json) {
		OpenGraphSettingsJSONParser openGraphSettingsJSONParser =
			new OpenGraphSettingsJSONParser();

		return openGraphSettingsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(OpenGraphSettings openGraphSettings) {
		if (openGraphSettings == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (openGraphSettings.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(openGraphSettings.getDescription()));

			sb.append("\"");
		}

		if (openGraphSettings.getDescription_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description_i18n\": ");

			sb.append(_toJSON(openGraphSettings.getDescription_i18n()));
		}

		if (openGraphSettings.getImage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"image\": ");

			sb.append(String.valueOf(openGraphSettings.getImage()));
		}

		if (openGraphSettings.getImageAlt() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"imageAlt\": ");

			sb.append("\"");

			sb.append(_escape(openGraphSettings.getImageAlt()));

			sb.append("\"");
		}

		if (openGraphSettings.getImageAlt_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"imageAlt_i18n\": ");

			sb.append(_toJSON(openGraphSettings.getImageAlt_i18n()));
		}

		if (openGraphSettings.getTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title\": ");

			sb.append("\"");

			sb.append(_escape(openGraphSettings.getTitle()));

			sb.append("\"");
		}

		if (openGraphSettings.getTitle_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title_i18n\": ");

			sb.append(_toJSON(openGraphSettings.getTitle_i18n()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		OpenGraphSettingsJSONParser openGraphSettingsJSONParser =
			new OpenGraphSettingsJSONParser();

		return openGraphSettingsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		OpenGraphSettings openGraphSettings) {

		if (openGraphSettings == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (openGraphSettings.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description",
				String.valueOf(openGraphSettings.getDescription()));
		}

		if (openGraphSettings.getDescription_i18n() == null) {
			map.put("description_i18n", null);
		}
		else {
			map.put(
				"description_i18n",
				String.valueOf(openGraphSettings.getDescription_i18n()));
		}

		if (openGraphSettings.getImage() == null) {
			map.put("image", null);
		}
		else {
			map.put("image", String.valueOf(openGraphSettings.getImage()));
		}

		if (openGraphSettings.getImageAlt() == null) {
			map.put("imageAlt", null);
		}
		else {
			map.put(
				"imageAlt", String.valueOf(openGraphSettings.getImageAlt()));
		}

		if (openGraphSettings.getImageAlt_i18n() == null) {
			map.put("imageAlt_i18n", null);
		}
		else {
			map.put(
				"imageAlt_i18n",
				String.valueOf(openGraphSettings.getImageAlt_i18n()));
		}

		if (openGraphSettings.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(openGraphSettings.getTitle()));
		}

		if (openGraphSettings.getTitle_i18n() == null) {
			map.put("title_i18n", null);
		}
		else {
			map.put(
				"title_i18n",
				String.valueOf(openGraphSettings.getTitle_i18n()));
		}

		return map;
	}

	public static class OpenGraphSettingsJSONParser
		extends BaseJSONParser<OpenGraphSettings> {

		@Override
		protected OpenGraphSettings createDTO() {
			return new OpenGraphSettings();
		}

		@Override
		protected OpenGraphSettings[] createDTOArray(int size) {
			return new OpenGraphSettings[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "description")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "description_i18n")) {
				return true;
			}
			else if (Objects.equals(jsonParserFieldName, "image")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "imageAlt")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "imageAlt_i18n")) {
				return true;
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "title_i18n")) {
				return true;
			}

			return false;
		}

		@Override
		protected void setField(
			OpenGraphSettings openGraphSettings, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					openGraphSettings.setDescription(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description_i18n")) {
				if (jsonParserFieldValue != null) {
					openGraphSettings.setDescription_i18n(
						(Map<String, String>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "image")) {
				if (jsonParserFieldValue != null) {
					openGraphSettings.setImage(
						ContentDocumentSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "imageAlt")) {
				if (jsonParserFieldValue != null) {
					openGraphSettings.setImageAlt((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "imageAlt_i18n")) {
				if (jsonParserFieldValue != null) {
					openGraphSettings.setImageAlt_i18n(
						(Map<String, String>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					openGraphSettings.setTitle((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title_i18n")) {
				if (jsonParserFieldValue != null) {
					openGraphSettings.setTitle_i18n(
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