/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.PageElement;
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
public class PageElementSerDes {

	public static PageElement toDTO(String json) {
		PageElementJSONParser pageElementJSONParser =
			new PageElementJSONParser();

		return pageElementJSONParser.parseToDTO(json);
	}

	public static PageElement[] toDTOs(String json) {
		PageElementJSONParser pageElementJSONParser =
			new PageElementJSONParser();

		return pageElementJSONParser.parseToDTOs(json);
	}

	public static String toJSON(PageElement pageElement) {
		if (pageElement == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (pageElement.getDefinition() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"definition\": ");

			if (pageElement.getDefinition() instanceof String) {
				sb.append("\"");
				sb.append((String)pageElement.getDefinition());
				sb.append("\"");
			}
			else {
				sb.append(pageElement.getDefinition());
			}
		}

		if (pageElement.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(pageElement.getId()));

			sb.append("\"");
		}

		if (pageElement.getPageElements() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pageElements\": ");

			sb.append("[");

			for (int i = 0; i < pageElement.getPageElements().length; i++) {
				sb.append(String.valueOf(pageElement.getPageElements()[i]));

				if ((i + 1) < pageElement.getPageElements().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (pageElement.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(pageElement.getType());

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PageElementJSONParser pageElementJSONParser =
			new PageElementJSONParser();

		return pageElementJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(PageElement pageElement) {
		if (pageElement == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (pageElement.getDefinition() == null) {
			map.put("definition", null);
		}
		else {
			map.put("definition", String.valueOf(pageElement.getDefinition()));
		}

		if (pageElement.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(pageElement.getId()));
		}

		if (pageElement.getPageElements() == null) {
			map.put("pageElements", null);
		}
		else {
			map.put(
				"pageElements", String.valueOf(pageElement.getPageElements()));
		}

		if (pageElement.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(pageElement.getType()));
		}

		return map;
	}

	public static class PageElementJSONParser
		extends BaseJSONParser<PageElement> {

		@Override
		protected PageElement createDTO() {
			return new PageElement();
		}

		@Override
		protected PageElement[] createDTOArray(int size) {
			return new PageElement[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "definition")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "pageElements")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			PageElement pageElement, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "definition")) {
				if (jsonParserFieldValue != null) {
					pageElement.setDefinition((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					pageElement.setId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "pageElements")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					PageElement[] pageElementsArray =
						new PageElement[jsonParserFieldValues.length];

					for (int i = 0; i < pageElementsArray.length; i++) {
						pageElementsArray[i] = PageElementSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					pageElement.setPageElements(pageElementsArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					pageElement.setType(
						PageElement.Type.create((String)jsonParserFieldValue));
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