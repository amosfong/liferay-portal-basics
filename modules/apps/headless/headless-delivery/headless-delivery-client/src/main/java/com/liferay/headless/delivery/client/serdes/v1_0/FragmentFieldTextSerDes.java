/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.FragmentFieldText;
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
public class FragmentFieldTextSerDes {

	public static FragmentFieldText toDTO(String json) {
		FragmentFieldTextJSONParser fragmentFieldTextJSONParser =
			new FragmentFieldTextJSONParser();

		return fragmentFieldTextJSONParser.parseToDTO(json);
	}

	public static FragmentFieldText[] toDTOs(String json) {
		FragmentFieldTextJSONParser fragmentFieldTextJSONParser =
			new FragmentFieldTextJSONParser();

		return fragmentFieldTextJSONParser.parseToDTOs(json);
	}

	public static String toJSON(FragmentFieldText fragmentFieldText) {
		if (fragmentFieldText == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (fragmentFieldText.getFragmentLink() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragmentLink\": ");

			sb.append(String.valueOf(fragmentFieldText.getFragmentLink()));
		}

		if (fragmentFieldText.getText() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"text\": ");

			if (fragmentFieldText.getText() instanceof String) {
				sb.append("\"");
				sb.append((String)fragmentFieldText.getText());
				sb.append("\"");
			}
			else {
				sb.append(fragmentFieldText.getText());
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FragmentFieldTextJSONParser fragmentFieldTextJSONParser =
			new FragmentFieldTextJSONParser();

		return fragmentFieldTextJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		FragmentFieldText fragmentFieldText) {

		if (fragmentFieldText == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (fragmentFieldText.getFragmentLink() == null) {
			map.put("fragmentLink", null);
		}
		else {
			map.put(
				"fragmentLink",
				String.valueOf(fragmentFieldText.getFragmentLink()));
		}

		if (fragmentFieldText.getText() == null) {
			map.put("text", null);
		}
		else {
			map.put("text", String.valueOf(fragmentFieldText.getText()));
		}

		return map;
	}

	public static class FragmentFieldTextJSONParser
		extends BaseJSONParser<FragmentFieldText> {

		@Override
		protected FragmentFieldText createDTO() {
			return new FragmentFieldText();
		}

		@Override
		protected FragmentFieldText[] createDTOArray(int size) {
			return new FragmentFieldText[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "fragmentLink")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "text")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			FragmentFieldText fragmentFieldText, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "fragmentLink")) {
				if (jsonParserFieldValue != null) {
					fragmentFieldText.setFragmentLink(
						FragmentLinkSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "text")) {
				if (jsonParserFieldValue != null) {
					fragmentFieldText.setText((Object)jsonParserFieldValue);
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