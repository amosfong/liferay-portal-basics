/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.cart.client.serdes.v1_0;

import com.liferay.headless.commerce.delivery.cart.client.dto.v1_0.CouponCode;
import com.liferay.headless.commerce.delivery.cart.client.json.BaseJSONParser;

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
public class CouponCodeSerDes {

	public static CouponCode toDTO(String json) {
		CouponCodeJSONParser couponCodeJSONParser = new CouponCodeJSONParser();

		return couponCodeJSONParser.parseToDTO(json);
	}

	public static CouponCode[] toDTOs(String json) {
		CouponCodeJSONParser couponCodeJSONParser = new CouponCodeJSONParser();

		return couponCodeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(CouponCode couponCode) {
		if (couponCode == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (couponCode.getCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"code\": ");

			sb.append("\"");

			sb.append(_escape(couponCode.getCode()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CouponCodeJSONParser couponCodeJSONParser = new CouponCodeJSONParser();

		return couponCodeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(CouponCode couponCode) {
		if (couponCode == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (couponCode.getCode() == null) {
			map.put("code", null);
		}
		else {
			map.put("code", String.valueOf(couponCode.getCode()));
		}

		return map;
	}

	public static class CouponCodeJSONParser
		extends BaseJSONParser<CouponCode> {

		@Override
		protected CouponCode createDTO() {
			return new CouponCode();
		}

		@Override
		protected CouponCode[] createDTOArray(int size) {
			return new CouponCode[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "code")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			CouponCode couponCode, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "code")) {
				if (jsonParserFieldValue != null) {
					couponCode.setCode((String)jsonParserFieldValue);
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