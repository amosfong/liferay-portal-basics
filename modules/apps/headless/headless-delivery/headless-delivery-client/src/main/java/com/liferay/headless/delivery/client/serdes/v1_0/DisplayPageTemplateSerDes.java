/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.DisplayPageTemplate;
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
public class DisplayPageTemplateSerDes {

	public static DisplayPageTemplate toDTO(String json) {
		DisplayPageTemplateJSONParser displayPageTemplateJSONParser =
			new DisplayPageTemplateJSONParser();

		return displayPageTemplateJSONParser.parseToDTO(json);
	}

	public static DisplayPageTemplate[] toDTOs(String json) {
		DisplayPageTemplateJSONParser displayPageTemplateJSONParser =
			new DisplayPageTemplateJSONParser();

		return displayPageTemplateJSONParser.parseToDTOs(json);
	}

	public static String toJSON(DisplayPageTemplate displayPageTemplate) {
		if (displayPageTemplate == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (displayPageTemplate.getContentSubtype() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentSubtype\": ");

			sb.append(String.valueOf(displayPageTemplate.getContentSubtype()));
		}

		if (displayPageTemplate.getContentType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentType\": ");

			sb.append(String.valueOf(displayPageTemplate.getContentType()));
		}

		if (displayPageTemplate.getDefaultTemplate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultTemplate\": ");

			sb.append(displayPageTemplate.getDefaultTemplate());
		}

		if (displayPageTemplate.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(displayPageTemplate.getKey()));

			sb.append("\"");
		}

		if (displayPageTemplate.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(displayPageTemplate.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		DisplayPageTemplateJSONParser displayPageTemplateJSONParser =
			new DisplayPageTemplateJSONParser();

		return displayPageTemplateJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		DisplayPageTemplate displayPageTemplate) {

		if (displayPageTemplate == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (displayPageTemplate.getContentSubtype() == null) {
			map.put("contentSubtype", null);
		}
		else {
			map.put(
				"contentSubtype",
				String.valueOf(displayPageTemplate.getContentSubtype()));
		}

		if (displayPageTemplate.getContentType() == null) {
			map.put("contentType", null);
		}
		else {
			map.put(
				"contentType",
				String.valueOf(displayPageTemplate.getContentType()));
		}

		if (displayPageTemplate.getDefaultTemplate() == null) {
			map.put("defaultTemplate", null);
		}
		else {
			map.put(
				"defaultTemplate",
				String.valueOf(displayPageTemplate.getDefaultTemplate()));
		}

		if (displayPageTemplate.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(displayPageTemplate.getKey()));
		}

		if (displayPageTemplate.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(displayPageTemplate.getName()));
		}

		return map;
	}

	public static class DisplayPageTemplateJSONParser
		extends BaseJSONParser<DisplayPageTemplate> {

		@Override
		protected DisplayPageTemplate createDTO() {
			return new DisplayPageTemplate();
		}

		@Override
		protected DisplayPageTemplate[] createDTOArray(int size) {
			return new DisplayPageTemplate[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "contentSubtype")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "contentType")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "defaultTemplate")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			DisplayPageTemplate displayPageTemplate, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "contentSubtype")) {
				if (jsonParserFieldValue != null) {
					displayPageTemplate.setContentSubtype(
						ContentSubtypeSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "contentType")) {
				if (jsonParserFieldValue != null) {
					displayPageTemplate.setContentType(
						ContentTypeSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "defaultTemplate")) {
				if (jsonParserFieldValue != null) {
					displayPageTemplate.setDefaultTemplate(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					displayPageTemplate.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					displayPageTemplate.setName((String)jsonParserFieldValue);
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