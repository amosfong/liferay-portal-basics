/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.serdes.v1_0;

import com.liferay.headless.admin.site.client.dto.v1_0.ColumnViewport;
import com.liferay.headless.admin.site.client.dto.v1_0.PageColumnDefinition;
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
public class PageColumnDefinitionSerDes {

	public static PageColumnDefinition toDTO(String json) {
		PageColumnDefinitionJSONParser pageColumnDefinitionJSONParser =
			new PageColumnDefinitionJSONParser();

		return pageColumnDefinitionJSONParser.parseToDTO(json);
	}

	public static PageColumnDefinition[] toDTOs(String json) {
		PageColumnDefinitionJSONParser pageColumnDefinitionJSONParser =
			new PageColumnDefinitionJSONParser();

		return pageColumnDefinitionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(PageColumnDefinition pageColumnDefinition) {
		if (pageColumnDefinition == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (pageColumnDefinition.getColumnViewports() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"columnViewports\": ");

			sb.append("[");

			for (int i = 0;
				 i < pageColumnDefinition.getColumnViewports().length; i++) {

				sb.append(
					String.valueOf(
						pageColumnDefinition.getColumnViewports()[i]));

				if ((i + 1) <
						pageColumnDefinition.getColumnViewports().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (pageColumnDefinition.getSize() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"size\": ");

			sb.append(pageColumnDefinition.getSize());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PageColumnDefinitionJSONParser pageColumnDefinitionJSONParser =
			new PageColumnDefinitionJSONParser();

		return pageColumnDefinitionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		PageColumnDefinition pageColumnDefinition) {

		if (pageColumnDefinition == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (pageColumnDefinition.getColumnViewports() == null) {
			map.put("columnViewports", null);
		}
		else {
			map.put(
				"columnViewports",
				String.valueOf(pageColumnDefinition.getColumnViewports()));
		}

		if (pageColumnDefinition.getSize() == null) {
			map.put("size", null);
		}
		else {
			map.put("size", String.valueOf(pageColumnDefinition.getSize()));
		}

		return map;
	}

	public static class PageColumnDefinitionJSONParser
		extends BaseJSONParser<PageColumnDefinition> {

		@Override
		protected PageColumnDefinition createDTO() {
			return new PageColumnDefinition();
		}

		@Override
		protected PageColumnDefinition[] createDTOArray(int size) {
			return new PageColumnDefinition[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "columnViewports")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "size")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			PageColumnDefinition pageColumnDefinition,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "columnViewports")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					ColumnViewport[] columnViewportsArray =
						new ColumnViewport[jsonParserFieldValues.length];

					for (int i = 0; i < columnViewportsArray.length; i++) {
						columnViewportsArray[i] = ColumnViewportSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					pageColumnDefinition.setColumnViewports(
						columnViewportsArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "size")) {
				if (jsonParserFieldValue != null) {
					pageColumnDefinition.setSize(
						Integer.valueOf((String)jsonParserFieldValue));
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