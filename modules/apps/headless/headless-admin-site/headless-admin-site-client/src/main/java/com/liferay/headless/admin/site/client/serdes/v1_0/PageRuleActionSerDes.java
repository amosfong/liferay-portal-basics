/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.serdes.v1_0;

import com.liferay.headless.admin.site.client.dto.v1_0.PageRuleAction;
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
public class PageRuleActionSerDes {

	public static PageRuleAction toDTO(String json) {
		PageRuleActionJSONParser pageRuleActionJSONParser =
			new PageRuleActionJSONParser();

		return pageRuleActionJSONParser.parseToDTO(json);
	}

	public static PageRuleAction[] toDTOs(String json) {
		PageRuleActionJSONParser pageRuleActionJSONParser =
			new PageRuleActionJSONParser();

		return pageRuleActionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(PageRuleAction pageRuleAction) {
		if (pageRuleAction == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (pageRuleAction.getAction() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"action\": ");

			sb.append("\"");

			sb.append(_escape(pageRuleAction.getAction()));

			sb.append("\"");
		}

		if (pageRuleAction.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(pageRuleAction.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (pageRuleAction.getItemId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"itemId\": ");

			sb.append("\"");

			sb.append(_escape(pageRuleAction.getItemId()));

			sb.append("\"");
		}

		if (pageRuleAction.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(pageRuleAction.getType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PageRuleActionJSONParser pageRuleActionJSONParser =
			new PageRuleActionJSONParser();

		return pageRuleActionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(PageRuleAction pageRuleAction) {
		if (pageRuleAction == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (pageRuleAction.getAction() == null) {
			map.put("action", null);
		}
		else {
			map.put("action", String.valueOf(pageRuleAction.getAction()));
		}

		if (pageRuleAction.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(pageRuleAction.getExternalReferenceCode()));
		}

		if (pageRuleAction.getItemId() == null) {
			map.put("itemId", null);
		}
		else {
			map.put("itemId", String.valueOf(pageRuleAction.getItemId()));
		}

		if (pageRuleAction.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(pageRuleAction.getType()));
		}

		return map;
	}

	public static class PageRuleActionJSONParser
		extends BaseJSONParser<PageRuleAction> {

		@Override
		protected PageRuleAction createDTO() {
			return new PageRuleAction();
		}

		@Override
		protected PageRuleAction[] createDTOArray(int size) {
			return new PageRuleAction[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "action")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "itemId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			PageRuleAction pageRuleAction, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "action")) {
				if (jsonParserFieldValue != null) {
					pageRuleAction.setAction((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					pageRuleAction.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "itemId")) {
				if (jsonParserFieldValue != null) {
					pageRuleAction.setItemId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					pageRuleAction.setType((String)jsonParserFieldValue);
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