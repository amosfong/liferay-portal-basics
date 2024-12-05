/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.client.serdes.v1_0;

import com.liferay.headless.admin.user.client.dto.v1_0.UserAccountFullNameDefinition;
import com.liferay.headless.admin.user.client.dto.v1_0.UserAccountFullNameDefinitionField;
import com.liferay.headless.admin.user.client.json.BaseJSONParser;

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
public class UserAccountFullNameDefinitionSerDes {

	public static UserAccountFullNameDefinition toDTO(String json) {
		UserAccountFullNameDefinitionJSONParser
			userAccountFullNameDefinitionJSONParser =
				new UserAccountFullNameDefinitionJSONParser();

		return userAccountFullNameDefinitionJSONParser.parseToDTO(json);
	}

	public static UserAccountFullNameDefinition[] toDTOs(String json) {
		UserAccountFullNameDefinitionJSONParser
			userAccountFullNameDefinitionJSONParser =
				new UserAccountFullNameDefinitionJSONParser();

		return userAccountFullNameDefinitionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		UserAccountFullNameDefinition userAccountFullNameDefinition) {

		if (userAccountFullNameDefinition == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (userAccountFullNameDefinition.
				getUserAccountFullNameDefinitionFields() != null) {

			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userAccountFullNameDefinitionFields\": ");

			sb.append("[");

			for (int i = 0;
				 i < userAccountFullNameDefinition.
					 getUserAccountFullNameDefinitionFields().length;
				 i++) {

				sb.append(
					String.valueOf(
						userAccountFullNameDefinition.
							getUserAccountFullNameDefinitionFields()[i]));

				if ((i + 1) < userAccountFullNameDefinition.
						getUserAccountFullNameDefinitionFields().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		UserAccountFullNameDefinitionJSONParser
			userAccountFullNameDefinitionJSONParser =
				new UserAccountFullNameDefinitionJSONParser();

		return userAccountFullNameDefinitionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		UserAccountFullNameDefinition userAccountFullNameDefinition) {

		if (userAccountFullNameDefinition == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (userAccountFullNameDefinition.
				getUserAccountFullNameDefinitionFields() == null) {

			map.put("userAccountFullNameDefinitionFields", null);
		}
		else {
			map.put(
				"userAccountFullNameDefinitionFields",
				String.valueOf(
					userAccountFullNameDefinition.
						getUserAccountFullNameDefinitionFields()));
		}

		return map;
	}

	public static class UserAccountFullNameDefinitionJSONParser
		extends BaseJSONParser<UserAccountFullNameDefinition> {

		@Override
		protected UserAccountFullNameDefinition createDTO() {
			return new UserAccountFullNameDefinition();
		}

		@Override
		protected UserAccountFullNameDefinition[] createDTOArray(int size) {
			return new UserAccountFullNameDefinition[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(
					jsonParserFieldName,
					"userAccountFullNameDefinitionFields")) {

				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			UserAccountFullNameDefinition userAccountFullNameDefinition,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(
					jsonParserFieldName,
					"userAccountFullNameDefinitionFields")) {

				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					UserAccountFullNameDefinitionField[]
						userAccountFullNameDefinitionFieldsArray =
							new UserAccountFullNameDefinitionField
								[jsonParserFieldValues.length];

					for (int i = 0;
						 i < userAccountFullNameDefinitionFieldsArray.length;
						 i++) {

						userAccountFullNameDefinitionFieldsArray[i] =
							UserAccountFullNameDefinitionFieldSerDes.toDTO(
								(String)jsonParserFieldValues[i]);
					}

					userAccountFullNameDefinition.
						setUserAccountFullNameDefinitionFields(
							userAccountFullNameDefinitionFieldsArray);
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