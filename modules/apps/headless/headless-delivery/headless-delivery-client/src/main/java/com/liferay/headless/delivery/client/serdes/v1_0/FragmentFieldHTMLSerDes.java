/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.FragmentFieldHTML;
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
public class FragmentFieldHTMLSerDes {

	public static FragmentFieldHTML toDTO(String json) {
		FragmentFieldHTMLJSONParser fragmentFieldHTMLJSONParser =
			new FragmentFieldHTMLJSONParser();

		return fragmentFieldHTMLJSONParser.parseToDTO(json);
	}

	public static FragmentFieldHTML[] toDTOs(String json) {
		FragmentFieldHTMLJSONParser fragmentFieldHTMLJSONParser =
			new FragmentFieldHTMLJSONParser();

		return fragmentFieldHTMLJSONParser.parseToDTOs(json);
	}

	public static String toJSON(FragmentFieldHTML fragmentFieldHTML) {
		if (fragmentFieldHTML == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (fragmentFieldHTML.getHtml() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"html\": ");

			if (fragmentFieldHTML.getHtml() instanceof String) {
				sb.append("\"");
				sb.append((String)fragmentFieldHTML.getHtml());
				sb.append("\"");
			}
			else {
				sb.append(fragmentFieldHTML.getHtml());
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FragmentFieldHTMLJSONParser fragmentFieldHTMLJSONParser =
			new FragmentFieldHTMLJSONParser();

		return fragmentFieldHTMLJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		FragmentFieldHTML fragmentFieldHTML) {

		if (fragmentFieldHTML == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (fragmentFieldHTML.getHtml() == null) {
			map.put("html", null);
		}
		else {
			map.put("html", String.valueOf(fragmentFieldHTML.getHtml()));
		}

		return map;
	}

	public static class FragmentFieldHTMLJSONParser
		extends BaseJSONParser<FragmentFieldHTML> {

		@Override
		protected FragmentFieldHTML createDTO() {
			return new FragmentFieldHTML();
		}

		@Override
		protected FragmentFieldHTML[] createDTOArray(int size) {
			return new FragmentFieldHTML[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "html")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			FragmentFieldHTML fragmentFieldHTML, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "html")) {
				if (jsonParserFieldValue != null) {
					fragmentFieldHTML.setHtml((Object)jsonParserFieldValue);
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