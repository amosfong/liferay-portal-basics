/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.channel.client.serdes.v1_0;

import com.liferay.headless.commerce.admin.channel.client.dto.v1_0.DefaultCategoryDisplayPage;
import com.liferay.headless.commerce.admin.channel.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public class DefaultCategoryDisplayPageSerDes {

	public static DefaultCategoryDisplayPage toDTO(String json) {
		DefaultCategoryDisplayPageJSONParser
			defaultCategoryDisplayPageJSONParser =
				new DefaultCategoryDisplayPageJSONParser();

		return defaultCategoryDisplayPageJSONParser.parseToDTO(json);
	}

	public static DefaultCategoryDisplayPage[] toDTOs(String json) {
		DefaultCategoryDisplayPageJSONParser
			defaultCategoryDisplayPageJSONParser =
				new DefaultCategoryDisplayPageJSONParser();

		return defaultCategoryDisplayPageJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		DefaultCategoryDisplayPage defaultCategoryDisplayPage) {

		if (defaultCategoryDisplayPage == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (defaultCategoryDisplayPage.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(defaultCategoryDisplayPage.getActions()));
		}

		if (defaultCategoryDisplayPage.getPageUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pageUuid\": ");

			sb.append("\"");

			sb.append(_escape(defaultCategoryDisplayPage.getPageUuid()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		DefaultCategoryDisplayPageJSONParser
			defaultCategoryDisplayPageJSONParser =
				new DefaultCategoryDisplayPageJSONParser();

		return defaultCategoryDisplayPageJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		DefaultCategoryDisplayPage defaultCategoryDisplayPage) {

		if (defaultCategoryDisplayPage == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (defaultCategoryDisplayPage.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put(
				"actions",
				String.valueOf(defaultCategoryDisplayPage.getActions()));
		}

		if (defaultCategoryDisplayPage.getPageUuid() == null) {
			map.put("pageUuid", null);
		}
		else {
			map.put(
				"pageUuid",
				String.valueOf(defaultCategoryDisplayPage.getPageUuid()));
		}

		return map;
	}

	public static class DefaultCategoryDisplayPageJSONParser
		extends BaseJSONParser<DefaultCategoryDisplayPage> {

		@Override
		protected DefaultCategoryDisplayPage createDTO() {
			return new DefaultCategoryDisplayPage();
		}

		@Override
		protected DefaultCategoryDisplayPage[] createDTOArray(int size) {
			return new DefaultCategoryDisplayPage[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "actions")) {
				return true;
			}
			else if (Objects.equals(jsonParserFieldName, "pageUuid")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			DefaultCategoryDisplayPage defaultCategoryDisplayPage,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					defaultCategoryDisplayPage.setActions(
						(Map<String, Map<String, String>>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "pageUuid")) {
				if (jsonParserFieldValue != null) {
					defaultCategoryDisplayPage.setPageUuid(
						(String)jsonParserFieldValue);
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