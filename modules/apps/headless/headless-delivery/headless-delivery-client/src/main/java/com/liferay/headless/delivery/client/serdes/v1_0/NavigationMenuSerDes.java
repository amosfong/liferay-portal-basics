/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.NavigationMenu;
import com.liferay.headless.delivery.client.dto.v1_0.NavigationMenuItem;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
public class NavigationMenuSerDes {

	public static NavigationMenu toDTO(String json) {
		NavigationMenuJSONParser navigationMenuJSONParser =
			new NavigationMenuJSONParser();

		return navigationMenuJSONParser.parseToDTO(json);
	}

	public static NavigationMenu[] toDTOs(String json) {
		NavigationMenuJSONParser navigationMenuJSONParser =
			new NavigationMenuJSONParser();

		return navigationMenuJSONParser.parseToDTOs(json);
	}

	public static String toJSON(NavigationMenu navigationMenu) {
		if (navigationMenu == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (navigationMenu.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(navigationMenu.getActions()));
		}

		if (navigationMenu.getCreator() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creator\": ");

			sb.append(String.valueOf(navigationMenu.getCreator()));
		}

		if (navigationMenu.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					navigationMenu.getDateCreated()));

			sb.append("\"");
		}

		if (navigationMenu.getDateModified() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					navigationMenu.getDateModified()));

			sb.append("\"");
		}

		if (navigationMenu.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(navigationMenu.getId());
		}

		if (navigationMenu.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(navigationMenu.getName()));

			sb.append("\"");
		}

		if (navigationMenu.getNavigationMenuItems() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"navigationMenuItems\": ");

			sb.append("[");

			for (int i = 0; i < navigationMenu.getNavigationMenuItems().length;
				 i++) {

				sb.append(
					String.valueOf(navigationMenu.getNavigationMenuItems()[i]));

				if ((i + 1) < navigationMenu.getNavigationMenuItems().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (navigationMenu.getNavigationType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"navigationType\": ");

			sb.append("\"");

			sb.append(navigationMenu.getNavigationType());

			sb.append("\"");
		}

		if (navigationMenu.getSiteId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteId\": ");

			sb.append(navigationMenu.getSiteId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		NavigationMenuJSONParser navigationMenuJSONParser =
			new NavigationMenuJSONParser();

		return navigationMenuJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(NavigationMenu navigationMenu) {
		if (navigationMenu == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (navigationMenu.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(navigationMenu.getActions()));
		}

		if (navigationMenu.getCreator() == null) {
			map.put("creator", null);
		}
		else {
			map.put("creator", String.valueOf(navigationMenu.getCreator()));
		}

		if (navigationMenu.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(
					navigationMenu.getDateCreated()));
		}

		if (navigationMenu.getDateModified() == null) {
			map.put("dateModified", null);
		}
		else {
			map.put(
				"dateModified",
				liferayToJSONDateFormat.format(
					navigationMenu.getDateModified()));
		}

		if (navigationMenu.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(navigationMenu.getId()));
		}

		if (navigationMenu.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(navigationMenu.getName()));
		}

		if (navigationMenu.getNavigationMenuItems() == null) {
			map.put("navigationMenuItems", null);
		}
		else {
			map.put(
				"navigationMenuItems",
				String.valueOf(navigationMenu.getNavigationMenuItems()));
		}

		if (navigationMenu.getNavigationType() == null) {
			map.put("navigationType", null);
		}
		else {
			map.put(
				"navigationType",
				String.valueOf(navigationMenu.getNavigationType()));
		}

		if (navigationMenu.getSiteId() == null) {
			map.put("siteId", null);
		}
		else {
			map.put("siteId", String.valueOf(navigationMenu.getSiteId()));
		}

		return map;
	}

	public static class NavigationMenuJSONParser
		extends BaseJSONParser<NavigationMenu> {

		@Override
		protected NavigationMenu createDTO() {
			return new NavigationMenu();
		}

		@Override
		protected NavigationMenu[] createDTOArray(int size) {
			return new NavigationMenu[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "actions")) {
				return true;
			}
			else if (Objects.equals(jsonParserFieldName, "creator")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "navigationMenuItems")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "navigationType")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "siteId")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			NavigationMenu navigationMenu, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					navigationMenu.setActions(
						(Map<String, Map<String, String>>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "creator")) {
				if (jsonParserFieldValue != null) {
					navigationMenu.setCreator(
						CreatorSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					navigationMenu.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				if (jsonParserFieldValue != null) {
					navigationMenu.setDateModified(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					navigationMenu.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					navigationMenu.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "navigationMenuItems")) {

				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					NavigationMenuItem[] navigationMenuItemsArray =
						new NavigationMenuItem[jsonParserFieldValues.length];

					for (int i = 0; i < navigationMenuItemsArray.length; i++) {
						navigationMenuItemsArray[i] =
							NavigationMenuItemSerDes.toDTO(
								(String)jsonParserFieldValues[i]);
					}

					navigationMenu.setNavigationMenuItems(
						navigationMenuItemsArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "navigationType")) {
				if (jsonParserFieldValue != null) {
					navigationMenu.setNavigationType(
						NavigationMenu.NavigationType.create(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "siteId")) {
				if (jsonParserFieldValue != null) {
					navigationMenu.setSiteId(
						Long.valueOf((String)jsonParserFieldValue));
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