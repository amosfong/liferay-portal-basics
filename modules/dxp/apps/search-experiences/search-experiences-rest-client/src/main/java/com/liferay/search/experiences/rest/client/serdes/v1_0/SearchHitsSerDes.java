/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.rest.client.serdes.v1_0;

import com.liferay.search.experiences.rest.client.dto.v1_0.Hit;
import com.liferay.search.experiences.rest.client.dto.v1_0.SearchHits;
import com.liferay.search.experiences.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@Generated("")
public class SearchHitsSerDes {

	public static SearchHits toDTO(String json) {
		SearchHitsJSONParser searchHitsJSONParser = new SearchHitsJSONParser();

		return searchHitsJSONParser.parseToDTO(json);
	}

	public static SearchHits[] toDTOs(String json) {
		SearchHitsJSONParser searchHitsJSONParser = new SearchHitsJSONParser();

		return searchHitsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(SearchHits searchHits) {
		if (searchHits == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (searchHits.getHits() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hits\": ");

			sb.append("[");

			for (int i = 0; i < searchHits.getHits().length; i++) {
				sb.append(String.valueOf(searchHits.getHits()[i]));

				if ((i + 1) < searchHits.getHits().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (searchHits.getMaxScore() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxScore\": ");

			sb.append(searchHits.getMaxScore());
		}

		if (searchHits.getTotalHits() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalHits\": ");

			sb.append(searchHits.getTotalHits());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SearchHitsJSONParser searchHitsJSONParser = new SearchHitsJSONParser();

		return searchHitsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(SearchHits searchHits) {
		if (searchHits == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (searchHits.getHits() == null) {
			map.put("hits", null);
		}
		else {
			map.put("hits", String.valueOf(searchHits.getHits()));
		}

		if (searchHits.getMaxScore() == null) {
			map.put("maxScore", null);
		}
		else {
			map.put("maxScore", String.valueOf(searchHits.getMaxScore()));
		}

		if (searchHits.getTotalHits() == null) {
			map.put("totalHits", null);
		}
		else {
			map.put("totalHits", String.valueOf(searchHits.getTotalHits()));
		}

		return map;
	}

	public static class SearchHitsJSONParser
		extends BaseJSONParser<SearchHits> {

		@Override
		protected SearchHits createDTO() {
			return new SearchHits();
		}

		@Override
		protected SearchHits[] createDTOArray(int size) {
			return new SearchHits[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "hits")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "maxScore")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "totalHits")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			SearchHits searchHits, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "hits")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					Hit[] hitsArray = new Hit[jsonParserFieldValues.length];

					for (int i = 0; i < hitsArray.length; i++) {
						hitsArray[i] = HitSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					searchHits.setHits(hitsArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "maxScore")) {
				if (jsonParserFieldValue != null) {
					searchHits.setMaxScore(
						Float.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "totalHits")) {
				if (jsonParserFieldValue != null) {
					searchHits.setTotalHits(
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