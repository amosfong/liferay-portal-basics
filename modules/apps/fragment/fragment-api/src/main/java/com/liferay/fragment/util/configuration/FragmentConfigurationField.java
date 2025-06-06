/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.util.configuration;

import com.liferay.fragment.constants.FragmentConfigurationFieldDataType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

/**
 * @author Víctor Galán
 */
public class FragmentConfigurationField {

	public FragmentConfigurationField(JSONObject fieldJSONObject) {
		_name = fieldJSONObject.getString("name");
		_dataType = fieldJSONObject.getString("dataType");
		_defaultValue = fieldJSONObject.getString("defaultValue");
		_localizable = fieldJSONObject.getBoolean("localizable");
		_type = fieldJSONObject.getString("type");
		_typeOptionsJSONObject = fieldJSONObject.getJSONObject("typeOptions");
	}

	public FragmentConfigurationField(
		String name, String dataType, String defaultValue, boolean localizable,
		String type) {

		_name = name;
		_dataType = dataType;
		_defaultValue = defaultValue;
		_localizable = localizable;
		_type = type;

		_typeOptionsJSONObject = JSONFactoryUtil.createJSONObject();
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 * #FragmentConfigurationField(String, String, String, boolean, String)}
	 */
	@Deprecated
	public FragmentConfigurationField(
		String name, String dataType, String defaultValue, String type) {

		_name = name;
		_dataType = dataType;
		_defaultValue = defaultValue;
		_type = type;

		_localizable = false;
		_typeOptionsJSONObject = JSONFactoryUtil.createJSONObject();
	}

	public String getDataType() {
		return _dataType;
	}

	public String getDefaultValue() {
		if (Objects.equals(_type, "colorPalette")) {
			return _getColorPaletteDefaultValue();
		}
		else if (Objects.equals(_type, "text") && isLocalizable()) {
			return _getTextDefaultValue();
		}

		if (Validator.isNotNull(_defaultValue)) {
			return _defaultValue;
		}

		return StringPool.BLANK;
	}

	public FragmentConfigurationFieldDataType
		getFragmentConfigurationFieldDataType() {

		if (StringUtil.equalsIgnoreCase(getDataType(), "array")) {
			return FragmentConfigurationFieldDataType.ARRAY;
		}
		else if (StringUtil.equalsIgnoreCase(getDataType(), "bool")) {
			return FragmentConfigurationFieldDataType.BOOLEAN;
		}
		else if (StringUtil.equalsIgnoreCase(getDataType(), "double")) {
			return FragmentConfigurationFieldDataType.DOUBLE;
		}
		else if (StringUtil.equalsIgnoreCase(getDataType(), "int")) {
			return FragmentConfigurationFieldDataType.INTEGER;
		}
		else if (StringUtil.equalsIgnoreCase(getDataType(), "object")) {
			return FragmentConfigurationFieldDataType.OBJECT;
		}
		else if (StringUtil.equalsIgnoreCase(getDataType(), "string")) {
			return FragmentConfigurationFieldDataType.STRING;
		}

		return null;
	}

	public String getName() {
		return _name;
	}

	public String getType() {
		return _type;
	}

	public JSONObject getTypeOptionsJSONObject() {
		return _typeOptionsJSONObject;
	}

	public boolean isLocalizable() {
		return _localizable;
	}

	private String _getColorPaletteDefaultValue() {
		if (Validator.isNotNull(_defaultValue)) {
			return _defaultValue;
		}

		return JSONUtil.put(
			"cssClass", StringPool.BLANK
		).put(
			"rgbValue", StringPool.BLANK
		).toString();
	}

	private String _getTextDefaultValue() {
		if (Validator.isNull(_defaultValue)) {
			return _defaultValue;
		}

		return LanguageUtil.get(
			LocaleUtil.getMostRelevantLocale(), _defaultValue);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentConfigurationField.class);

	private final String _dataType;
	private final String _defaultValue;
	private final boolean _localizable;
	private final String _name;
	private final String _type;
	private final JSONObject _typeOptionsJSONObject;

}