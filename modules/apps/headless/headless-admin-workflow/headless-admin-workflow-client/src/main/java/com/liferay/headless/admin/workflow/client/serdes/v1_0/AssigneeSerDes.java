/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.workflow.client.serdes.v1_0;

import com.liferay.headless.admin.workflow.client.dto.v1_0.Assignee;
import com.liferay.headless.admin.workflow.client.json.BaseJSONParser;

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
public class AssigneeSerDes {

	public static Assignee toDTO(String json) {
		AssigneeJSONParser assigneeJSONParser = new AssigneeJSONParser();

		return assigneeJSONParser.parseToDTO(json);
	}

	public static Assignee[] toDTOs(String json) {
		AssigneeJSONParser assigneeJSONParser = new AssigneeJSONParser();

		return assigneeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Assignee assignee) {
		if (assignee == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (assignee.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(assignee.getId());
		}

		if (assignee.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(assignee.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AssigneeJSONParser assigneeJSONParser = new AssigneeJSONParser();

		return assigneeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Assignee assignee) {
		if (assignee == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (assignee.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(assignee.getId()));
		}

		if (assignee.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(assignee.getName()));
		}

		return map;
	}

	public static class AssigneeJSONParser extends BaseJSONParser<Assignee> {

		@Override
		protected Assignee createDTO() {
			return new Assignee();
		}

		@Override
		protected Assignee[] createDTOArray(int size) {
			return new Assignee[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			Assignee assignee, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					assignee.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					assignee.setName((String)jsonParserFieldValue);
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