/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.order.client.serdes.v1_0;

import com.liferay.headless.commerce.delivery.order.client.dto.v1_0.ShippingMethod;
import com.liferay.headless.commerce.delivery.order.client.json.BaseJSONParser;

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
public class ShippingMethodSerDes {

	public static ShippingMethod toDTO(String json) {
		ShippingMethodJSONParser shippingMethodJSONParser =
			new ShippingMethodJSONParser();

		return shippingMethodJSONParser.parseToDTO(json);
	}

	public static ShippingMethod[] toDTOs(String json) {
		ShippingMethodJSONParser shippingMethodJSONParser =
			new ShippingMethodJSONParser();

		return shippingMethodJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ShippingMethod shippingMethod) {
		if (shippingMethod == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (shippingMethod.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(shippingMethod.getDescription()));

			sb.append("\"");
		}

		if (shippingMethod.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(shippingMethod.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (shippingMethod.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(shippingMethod.getId());
		}

		if (shippingMethod.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(shippingMethod.getName()));

			sb.append("\"");
		}

		if (shippingMethod.getShippingOption() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingOption\": ");

			sb.append(String.valueOf(shippingMethod.getShippingOption()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ShippingMethodJSONParser shippingMethodJSONParser =
			new ShippingMethodJSONParser();

		return shippingMethodJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(ShippingMethod shippingMethod) {
		if (shippingMethod == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (shippingMethod.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description", String.valueOf(shippingMethod.getDescription()));
		}

		if (shippingMethod.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(shippingMethod.getExternalReferenceCode()));
		}

		if (shippingMethod.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(shippingMethod.getId()));
		}

		if (shippingMethod.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(shippingMethod.getName()));
		}

		if (shippingMethod.getShippingOption() == null) {
			map.put("shippingOption", null);
		}
		else {
			map.put(
				"shippingOption",
				String.valueOf(shippingMethod.getShippingOption()));
		}

		return map;
	}

	public static class ShippingMethodJSONParser
		extends BaseJSONParser<ShippingMethod> {

		@Override
		protected ShippingMethod createDTO() {
			return new ShippingMethod();
		}

		@Override
		protected ShippingMethod[] createDTOArray(int size) {
			return new ShippingMethod[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "description")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "shippingOption")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			ShippingMethod shippingMethod, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					shippingMethod.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					shippingMethod.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					shippingMethod.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					shippingMethod.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "shippingOption")) {
				if (jsonParserFieldValue != null) {
					shippingMethod.setShippingOption(
						ShippingOptionSerDes.toDTO(
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