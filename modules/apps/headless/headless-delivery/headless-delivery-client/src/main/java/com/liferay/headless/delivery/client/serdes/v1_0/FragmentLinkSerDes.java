/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.FragmentLink;
import com.liferay.headless.delivery.client.dto.v1_0.FragmentLinkValue;
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
public class FragmentLinkSerDes {

	public static FragmentLink toDTO(String json) {
		FragmentLinkJSONParser fragmentLinkJSONParser =
			new FragmentLinkJSONParser();

		return fragmentLinkJSONParser.parseToDTO(json);
	}

	public static FragmentLink[] toDTOs(String json) {
		FragmentLinkJSONParser fragmentLinkJSONParser =
			new FragmentLinkJSONParser();

		return fragmentLinkJSONParser.parseToDTOs(json);
	}

	public static String toJSON(FragmentLink fragmentLink) {
		if (fragmentLink == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (fragmentLink.getHref() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"href\": ");

			if (fragmentLink.getHref() instanceof String) {
				sb.append("\"");
				sb.append((String)fragmentLink.getHref());
				sb.append("\"");
			}
			else {
				sb.append(fragmentLink.getHref());
			}
		}

		if (fragmentLink.getTarget() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"target\": ");

			sb.append("\"");

			sb.append(fragmentLink.getTarget());

			sb.append("\"");
		}

		if (fragmentLink.getValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value\": ");

			sb.append(String.valueOf(fragmentLink.getValue()));
		}

		if (fragmentLink.getValue_i18n() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value_i18n\": ");

			sb.append(_toJSON(fragmentLink.getValue_i18n()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FragmentLinkJSONParser fragmentLinkJSONParser =
			new FragmentLinkJSONParser();

		return fragmentLinkJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(FragmentLink fragmentLink) {
		if (fragmentLink == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (fragmentLink.getHref() == null) {
			map.put("href", null);
		}
		else {
			map.put("href", String.valueOf(fragmentLink.getHref()));
		}

		if (fragmentLink.getTarget() == null) {
			map.put("target", null);
		}
		else {
			map.put("target", String.valueOf(fragmentLink.getTarget()));
		}

		if (fragmentLink.getValue() == null) {
			map.put("value", null);
		}
		else {
			map.put("value", String.valueOf(fragmentLink.getValue()));
		}

		if (fragmentLink.getValue_i18n() == null) {
			map.put("value_i18n", null);
		}
		else {
			map.put("value_i18n", String.valueOf(fragmentLink.getValue_i18n()));
		}

		return map;
	}

	public static class FragmentLinkJSONParser
		extends BaseJSONParser<FragmentLink> {

		@Override
		protected FragmentLink createDTO() {
			return new FragmentLink();
		}

		@Override
		protected FragmentLink[] createDTOArray(int size) {
			return new FragmentLink[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "href")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "target")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "value_i18n")) {
				return true;
			}

			return false;
		}

		@Override
		protected void setField(
			FragmentLink fragmentLink, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "href")) {
				if (jsonParserFieldValue != null) {
					fragmentLink.setHref((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "target")) {
				if (jsonParserFieldValue != null) {
					fragmentLink.setTarget(
						FragmentLink.Target.create(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				if (jsonParserFieldValue != null) {
					fragmentLink.setValue(
						FragmentLinkValueSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value_i18n")) {
				if (jsonParserFieldValue != null) {
					fragmentLink.setValue_i18n(
						(Map<String, FragmentLinkValue>)jsonParserFieldValue);
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