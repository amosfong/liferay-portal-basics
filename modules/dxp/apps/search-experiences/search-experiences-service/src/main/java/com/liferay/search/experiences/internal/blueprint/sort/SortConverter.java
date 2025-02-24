/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.internal.blueprint.sort;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.geolocation.DistanceUnit;
import com.liferay.portal.search.geolocation.GeoBuilders;
import com.liferay.portal.search.geolocation.GeoDistanceType;
import com.liferay.portal.search.sort.FieldSort;
import com.liferay.portal.search.sort.GeoDistanceSort;
import com.liferay.portal.search.sort.NestedSort;
import com.liferay.portal.search.sort.ScriptSort;
import com.liferay.portal.search.sort.Sort;
import com.liferay.portal.search.sort.SortMode;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.sort.Sorts;
import com.liferay.search.experiences.internal.blueprint.query.QueryConverter;
import com.liferay.search.experiences.internal.blueprint.script.ScriptConverter;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Petteri Karttunen
 */
public class SortConverter {

	public SortConverter(
		GeoBuilders geoBuilders, QueryConverter queryConverter,
		ScriptConverter scriptConverter, Sorts sorts) {

		_geoBuilders = geoBuilders;
		_queryConverter = queryConverter;
		_scriptConverter = scriptConverter;
		_sorts = sorts;
	}

	public Sort convert(Object object) {
		return _toSort(object);
	}

	private void _addGeoLocationPoint(
		GeoDistanceSort geoDistanceSort, JSONArray jsonArray) {

		geoDistanceSort.addGeoLocationPoints(
			_geoBuilders.geoLocationPoint(
				jsonArray.getDouble(0), jsonArray.getDouble(1)));
	}

	private void _processGeoDistanceType(
		GeoDistanceSort geoDistanceSort, JSONObject jsonObject) {

		if (!jsonObject.has("distance_type")) {
			return;
		}

		geoDistanceSort.setGeoDistanceType(
			GeoDistanceType.valueOf(
				StringUtil.toUpperCase(jsonObject.getString("distance_type"))));
	}

	private void _processGeoDistanceUnit(
		GeoDistanceSort geoDistanceSort, JSONObject jsonObject) {

		geoDistanceSort.setDistanceUnit(
			DistanceUnit.create(
				StringUtil.toLowerCase(jsonObject.getString("unit"))));
	}

	private void _processGeoLocationPoints(
		GeoDistanceSort geoDistanceSort, Object object) {

		if (object instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject)object;

			geoDistanceSort.addGeoLocationPoints(
				_geoBuilders.geoLocationPoint(
					jsonObject.getInt("lat"), jsonObject.getInt("lon")));
		}
		else if (object instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray)object;

			if ((jsonArray.length() != 2) ||
				(jsonArray.get(0) instanceof JSONArray)) {

				for (int i = 0; i < jsonArray.length(); i++) {
					_addGeoLocationPoint(
						geoDistanceSort, jsonArray.getJSONArray(i));
				}
			}
			else {
				_addGeoLocationPoint(geoDistanceSort, jsonArray);
			}
		}
		else if (object instanceof String) {
			geoDistanceSort.addGeoLocationPoints(
				_geoBuilders.geoLocationPoint((String)object));
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	private void _processGeoSortMode(
		GeoDistanceSort geoDistanceSort, JSONObject jsonObject) {

		String mode = jsonObject.getString("mode");

		if (Validator.isNotNull(mode)) {
			geoDistanceSort.setSortMode(
				SortMode.valueOf(StringUtil.toUpperCase(mode)));
		}
	}

	private void _processSortOrder(JSONObject jsonObject, Sort sort) {
		if (jsonObject.has("order")) {
			sort.setSortOrder(_toSortOrder(jsonObject.getString("order")));
		}
	}

	private Sort _toFieldSort(String field) {
		if (field.equals("_score")) {
			return _sorts.score();
		}

		return _sorts.field(field);
	}

	private Sort _toFieldSort(String field, JSONObject jsonObject) {
		FieldSort fieldSort = _sorts.field(field);

		if (jsonObject.has("missing")) {
			fieldSort.setMissing(jsonObject.getString("missing"));
		}

		if (jsonObject.has("nested")) {
			fieldSort.setNestedSort(
				_toNestedSort(jsonObject.getJSONObject("nested")));
		}

		if (jsonObject.has("mode")) {
			fieldSort.setSortMode(
				SortMode.valueOf(
					StringUtil.toUpperCase(jsonObject.getString("mode"))));
		}

		_processSortOrder(jsonObject, fieldSort);

		return fieldSort;
	}

	private Sort _toFieldSort(String field, String string) {
		Sort sort = _toFieldSort(field);

		sort.setSortOrder(_toSortOrder(string));

		return sort;
	}

	private GeoDistanceSort _toGeoDistanceSort(JSONObject jsonObject) {
		Iterator<String> iterator = jsonObject.keys();

		String field = iterator.next();

		GeoDistanceSort geoDistanceSort = _sorts.geoDistance(field);

		_processGeoDistanceUnit(geoDistanceSort, jsonObject);
		_processGeoDistanceType(geoDistanceSort, jsonObject);
		_processGeoLocationPoints(geoDistanceSort, jsonObject.get(field));
		_processGeoSortMode(geoDistanceSort, jsonObject);
		_processSortOrder(jsonObject, geoDistanceSort);

		return geoDistanceSort;
	}

	private NestedSort _toNestedSort(JSONObject jsonObject) {
		NestedSort nestedSort = _sorts.nested(
			jsonObject.getString("nested_path"));

		if (jsonObject.has("nested_filter")) {
			nestedSort.setFilterQuery(
				_queryConverter.toQuery(
					jsonObject.getJSONObject("nested_filter")));
		}

		if (jsonObject.has("nested")) {
			nestedSort.setNestedSort(
				_toNestedSort(jsonObject.getJSONObject("nested")));
		}

		return nestedSort;
	}

	private Sort _toScriptSort(JSONObject jsonObject) {
		ScriptSort scriptSort = _sorts.script(
			_scriptConverter.toScript(jsonObject.get("script")),
			ScriptSort.ScriptSortType.valueOf(
				StringUtil.toUpperCase(jsonObject.getString("type"))));

		_processSortOrder(jsonObject, scriptSort);

		return scriptSort;
	}

	private Sort _toSort(JSONObject jsonObject) {
		Iterator<String> iterator = jsonObject.keys();

		String key = iterator.next();

		Object object = jsonObject.get(key);

		if (Objects.equals(key, "_geo_distance")) {
			return _toGeoDistanceSort((JSONObject)object);
		}
		else if (Objects.equals(key, "_script")) {
			return _toScriptSort((JSONObject)object);
		}
		else if (object instanceof JSONObject) {
			return _toFieldSort(key, (JSONObject)object);
		}
		else if (object instanceof String) {
			return _toFieldSort(key, (String)object);
		}

		throw new IllegalArgumentException();
	}

	private Sort _toSort(Object object) {
		if (object instanceof JSONObject) {
			return _toSort((JSONObject)object);
		}
		else if (object instanceof String) {
			return _toFieldSort((String)object);
		}

		throw new IllegalArgumentException();
	}

	private SortOrder _toSortOrder(String string) {
		return SortOrder.valueOf(StringUtil.toUpperCase(string));
	}

	private final GeoBuilders _geoBuilders;
	private final QueryConverter _queryConverter;
	private final ScriptConverter _scriptConverter;
	private final Sorts _sorts;

}