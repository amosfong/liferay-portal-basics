/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.account.client.serdes.v1_0;

import com.liferay.headless.commerce.admin.account.client.dto.v1_0.AccountRole;
import com.liferay.headless.commerce.admin.account.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class AccountRoleSerDes {

	public static AccountRole toDTO(String json) {
		AccountRoleJSONParser accountRoleJSONParser =
			new AccountRoleJSONParser();

		return accountRoleJSONParser.parseToDTO(json);
	}

	public static AccountRole[] toDTOs(String json) {
		AccountRoleJSONParser accountRoleJSONParser =
			new AccountRoleJSONParser();

		return accountRoleJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AccountRole accountRole) {
		if (accountRole == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (accountRole.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append(_toJSON(accountRole.getDescription()));
		}

		if (accountRole.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(accountRole.getName()));

			sb.append("\"");
		}

		if (accountRole.getRoleId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleId\": ");

			sb.append(accountRole.getRoleId());
		}

		if (accountRole.getTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title\": ");

			sb.append(_toJSON(accountRole.getTitle()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AccountRoleJSONParser accountRoleJSONParser =
			new AccountRoleJSONParser();

		return accountRoleJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AccountRole accountRole) {
		if (accountRole == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (accountRole.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description", String.valueOf(accountRole.getDescription()));
		}

		if (accountRole.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(accountRole.getName()));
		}

		if (accountRole.getRoleId() == null) {
			map.put("roleId", null);
		}
		else {
			map.put("roleId", String.valueOf(accountRole.getRoleId()));
		}

		if (accountRole.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(accountRole.getTitle()));
		}

		return map;
	}

	public static class AccountRoleJSONParser
		extends BaseJSONParser<AccountRole> {

		@Override
		protected AccountRole createDTO() {
			return new AccountRole();
		}

		@Override
		protected AccountRole[] createDTOArray(int size) {
			return new AccountRole[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "description")) {
				return true;
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "roleId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				return true;
			}

			return false;
		}

		@Override
		protected void setField(
			AccountRole accountRole, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					accountRole.setDescription(
						(Map<String, String>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					accountRole.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleId")) {
				if (jsonParserFieldValue != null) {
					accountRole.setRoleId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					accountRole.setTitle(
						(Map<String, String>)jsonParserFieldValue);
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