/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.catalog.client.serdes.v1_0;

import com.liferay.headless.commerce.delivery.catalog.client.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.delivery.catalog.client.json.BaseJSONParser;

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
public class ProductSpecificationSerDes {

	public static ProductSpecification toDTO(String json) {
		ProductSpecificationJSONParser productSpecificationJSONParser =
			new ProductSpecificationJSONParser();

		return productSpecificationJSONParser.parseToDTO(json);
	}

	public static ProductSpecification[] toDTOs(String json) {
		ProductSpecificationJSONParser productSpecificationJSONParser =
			new ProductSpecificationJSONParser();

		return productSpecificationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ProductSpecification productSpecification) {
		if (productSpecification == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (productSpecification.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(productSpecification.getId());
		}

		if (productSpecification.getOptionCategoryId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"optionCategoryId\": ");

			sb.append(productSpecification.getOptionCategoryId());
		}

		if (productSpecification.getPriority() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priority\": ");

			sb.append(productSpecification.getPriority());
		}

		if (productSpecification.getProductId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productId\": ");

			sb.append(productSpecification.getProductId());
		}

		if (productSpecification.getSpecificationGroupKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"specificationGroupKey\": ");

			sb.append("\"");

			sb.append(_escape(productSpecification.getSpecificationGroupKey()));

			sb.append("\"");
		}

		if (productSpecification.getSpecificationGroupTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"specificationGroupTitle\": ");

			sb.append("\"");

			sb.append(
				_escape(productSpecification.getSpecificationGroupTitle()));

			sb.append("\"");
		}

		if (productSpecification.getSpecificationId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"specificationId\": ");

			sb.append(productSpecification.getSpecificationId());
		}

		if (productSpecification.getSpecificationKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"specificationKey\": ");

			sb.append("\"");

			sb.append(_escape(productSpecification.getSpecificationKey()));

			sb.append("\"");
		}

		if (productSpecification.getSpecificationPriority() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"specificationPriority\": ");

			sb.append(productSpecification.getSpecificationPriority());
		}

		if (productSpecification.getSpecificationTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"specificationTitle\": ");

			sb.append("\"");

			sb.append(_escape(productSpecification.getSpecificationTitle()));

			sb.append("\"");
		}

		if (productSpecification.getValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value\": ");

			sb.append("\"");

			sb.append(_escape(productSpecification.getValue()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductSpecificationJSONParser productSpecificationJSONParser =
			new ProductSpecificationJSONParser();

		return productSpecificationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ProductSpecification productSpecification) {

		if (productSpecification == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (productSpecification.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(productSpecification.getId()));
		}

		if (productSpecification.getOptionCategoryId() == null) {
			map.put("optionCategoryId", null);
		}
		else {
			map.put(
				"optionCategoryId",
				String.valueOf(productSpecification.getOptionCategoryId()));
		}

		if (productSpecification.getPriority() == null) {
			map.put("priority", null);
		}
		else {
			map.put(
				"priority", String.valueOf(productSpecification.getPriority()));
		}

		if (productSpecification.getProductId() == null) {
			map.put("productId", null);
		}
		else {
			map.put(
				"productId",
				String.valueOf(productSpecification.getProductId()));
		}

		if (productSpecification.getSpecificationGroupKey() == null) {
			map.put("specificationGroupKey", null);
		}
		else {
			map.put(
				"specificationGroupKey",
				String.valueOf(
					productSpecification.getSpecificationGroupKey()));
		}

		if (productSpecification.getSpecificationGroupTitle() == null) {
			map.put("specificationGroupTitle", null);
		}
		else {
			map.put(
				"specificationGroupTitle",
				String.valueOf(
					productSpecification.getSpecificationGroupTitle()));
		}

		if (productSpecification.getSpecificationId() == null) {
			map.put("specificationId", null);
		}
		else {
			map.put(
				"specificationId",
				String.valueOf(productSpecification.getSpecificationId()));
		}

		if (productSpecification.getSpecificationKey() == null) {
			map.put("specificationKey", null);
		}
		else {
			map.put(
				"specificationKey",
				String.valueOf(productSpecification.getSpecificationKey()));
		}

		if (productSpecification.getSpecificationPriority() == null) {
			map.put("specificationPriority", null);
		}
		else {
			map.put(
				"specificationPriority",
				String.valueOf(
					productSpecification.getSpecificationPriority()));
		}

		if (productSpecification.getSpecificationTitle() == null) {
			map.put("specificationTitle", null);
		}
		else {
			map.put(
				"specificationTitle",
				String.valueOf(productSpecification.getSpecificationTitle()));
		}

		if (productSpecification.getValue() == null) {
			map.put("value", null);
		}
		else {
			map.put("value", String.valueOf(productSpecification.getValue()));
		}

		return map;
	}

	public static class ProductSpecificationJSONParser
		extends BaseJSONParser<ProductSpecification> {

		@Override
		protected ProductSpecification createDTO() {
			return new ProductSpecification();
		}

		@Override
		protected ProductSpecification[] createDTOArray(int size) {
			return new ProductSpecification[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "optionCategoryId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "priority")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "productId")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "specificationGroupKey")) {

				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "specificationGroupTitle")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "specificationId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "specificationKey")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "specificationPriority")) {

				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "specificationTitle")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			ProductSpecification productSpecification,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					productSpecification.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "optionCategoryId")) {
				if (jsonParserFieldValue != null) {
					productSpecification.setOptionCategoryId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priority")) {
				if (jsonParserFieldValue != null) {
					productSpecification.setPriority(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productId")) {
				if (jsonParserFieldValue != null) {
					productSpecification.setProductId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "specificationGroupKey")) {

				if (jsonParserFieldValue != null) {
					productSpecification.setSpecificationGroupKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "specificationGroupTitle")) {

				if (jsonParserFieldValue != null) {
					productSpecification.setSpecificationGroupTitle(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "specificationId")) {
				if (jsonParserFieldValue != null) {
					productSpecification.setSpecificationId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "specificationKey")) {
				if (jsonParserFieldValue != null) {
					productSpecification.setSpecificationKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "specificationPriority")) {

				if (jsonParserFieldValue != null) {
					productSpecification.setSpecificationPriority(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "specificationTitle")) {

				if (jsonParserFieldValue != null) {
					productSpecification.setSpecificationTitle(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				if (jsonParserFieldValue != null) {
					productSpecification.setValue((String)jsonParserFieldValue);
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