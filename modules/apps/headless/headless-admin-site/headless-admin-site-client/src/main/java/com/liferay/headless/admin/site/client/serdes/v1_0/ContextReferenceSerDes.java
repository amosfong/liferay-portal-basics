/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.serdes.v1_0;

import com.liferay.headless.admin.site.client.dto.v1_0.ContextReference;
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
public class ContextReferenceSerDes {

	public static ContextReference toDTO(String json) {
		ContextReferenceJSONParser contextReferenceJSONParser =
			new ContextReferenceJSONParser();

		return contextReferenceJSONParser.parseToDTO(json);
	}

	public static ContextReference[] toDTOs(String json) {
		ContextReferenceJSONParser contextReferenceJSONParser =
			new ContextReferenceJSONParser();

		return contextReferenceJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ContextReference contextReference) {
		if (contextReference == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (contextReference.getContextSource() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contextSource\": ");

			sb.append("\"");

			sb.append(contextReference.getContextSource());

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ContextReferenceJSONParser contextReferenceJSONParser =
			new ContextReferenceJSONParser();

		return contextReferenceJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(ContextReference contextReference) {
		if (contextReference == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (contextReference.getContextSource() == null) {
			map.put("contextSource", null);
		}
		else {
			map.put(
				"contextSource",
				String.valueOf(contextReference.getContextSource()));
		}

		return map;
	}

	public static class ContextReferenceJSONParser
		extends BaseJSONParser<ContextReference> {

		@Override
		protected ContextReference createDTO() {
			return new ContextReference();
		}

		@Override
		protected ContextReference[] createDTOArray(int size) {
			return new ContextReference[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "contextSource")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			ContextReference contextReference, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "contextSource")) {
				if (jsonParserFieldValue != null) {
					contextReference.setContextSource(
						ContextReference.ContextSource.create(
							(String)jsonParserFieldValue));
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