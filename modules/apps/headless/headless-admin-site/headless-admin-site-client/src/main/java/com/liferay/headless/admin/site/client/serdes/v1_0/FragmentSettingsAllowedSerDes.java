/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.serdes.v1_0;

import com.liferay.headless.admin.site.client.dto.v1_0.FragmentSettingsAllowed;
import com.liferay.headless.admin.site.client.dto.v1_0.ItemExternalReference;
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
public class FragmentSettingsAllowedSerDes {

	public static FragmentSettingsAllowed toDTO(String json) {
		FragmentSettingsAllowedJSONParser fragmentSettingsAllowedJSONParser =
			new FragmentSettingsAllowedJSONParser();

		return fragmentSettingsAllowedJSONParser.parseToDTO(json);
	}

	public static FragmentSettingsAllowed[] toDTOs(String json) {
		FragmentSettingsAllowedJSONParser fragmentSettingsAllowedJSONParser =
			new FragmentSettingsAllowedJSONParser();

		return fragmentSettingsAllowedJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		FragmentSettingsAllowed fragmentSettingsAllowed) {

		if (fragmentSettingsAllowed == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (fragmentSettingsAllowed.
				getAllowedFragmentItemExternalReferences() != null) {

			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"allowedFragmentItemExternalReferences\": ");

			sb.append("[");

			for (int i = 0;
				 i < fragmentSettingsAllowed.
					 getAllowedFragmentItemExternalReferences().length;
				 i++) {

				sb.append(
					String.valueOf(
						fragmentSettingsAllowed.
							getAllowedFragmentItemExternalReferences()[i]));

				if ((i + 1) < fragmentSettingsAllowed.
						getAllowedFragmentItemExternalReferences().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FragmentSettingsAllowedJSONParser fragmentSettingsAllowedJSONParser =
			new FragmentSettingsAllowedJSONParser();

		return fragmentSettingsAllowedJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		FragmentSettingsAllowed fragmentSettingsAllowed) {

		if (fragmentSettingsAllowed == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (fragmentSettingsAllowed.
				getAllowedFragmentItemExternalReferences() == null) {

			map.put("allowedFragmentItemExternalReferences", null);
		}
		else {
			map.put(
				"allowedFragmentItemExternalReferences",
				String.valueOf(
					fragmentSettingsAllowed.
						getAllowedFragmentItemExternalReferences()));
		}

		return map;
	}

	public static class FragmentSettingsAllowedJSONParser
		extends BaseJSONParser<FragmentSettingsAllowed> {

		@Override
		protected FragmentSettingsAllowed createDTO() {
			return new FragmentSettingsAllowed();
		}

		@Override
		protected FragmentSettingsAllowed[] createDTOArray(int size) {
			return new FragmentSettingsAllowed[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(
					jsonParserFieldName,
					"allowedFragmentItemExternalReferences")) {

				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			FragmentSettingsAllowed fragmentSettingsAllowed,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(
					jsonParserFieldName,
					"allowedFragmentItemExternalReferences")) {

				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					ItemExternalReference[]
						allowedFragmentItemExternalReferencesArray =
							new ItemExternalReference
								[jsonParserFieldValues.length];

					for (int i = 0;
						 i < allowedFragmentItemExternalReferencesArray.length;
						 i++) {

						allowedFragmentItemExternalReferencesArray[i] =
							ItemExternalReferenceSerDes.toDTO(
								(String)jsonParserFieldValues[i]);
					}

					fragmentSettingsAllowed.
						setAllowedFragmentItemExternalReferences(
							allowedFragmentItemExternalReferencesArray);
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