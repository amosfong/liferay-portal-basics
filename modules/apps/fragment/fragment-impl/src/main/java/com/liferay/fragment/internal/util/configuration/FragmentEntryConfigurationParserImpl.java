/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.util.configuration;

import com.liferay.fragment.constants.FragmentConfigurationFieldDataType;
import com.liferay.fragment.entry.processor.constants.FragmentEntryProcessorConstants;
import com.liferay.fragment.util.configuration.FragmentConfigurationField;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.fragment.util.configuration.FragmentEntryMenuDisplayConfiguration;
import com.liferay.frontend.token.definition.FrontendToken;
import com.liferay.frontend.token.definition.FrontendTokenDefinition;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.frontend.token.definition.FrontendTokenMapping;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.navigation.taglib.servlet.taglib.util.NavItemUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(service = FragmentEntryConfigurationParser.class)
public class FragmentEntryConfigurationParserImpl
	implements FragmentEntryConfigurationParser {

	@Override
	public JSONObject getConfigurationDefaultValuesJSONObject(
		String configuration) {

		List<FragmentConfigurationField> fragmentConfigurationFields =
			getFragmentConfigurationFields(configuration);

		JSONObject defaultValuesJSONObject = _jsonFactory.createJSONObject();

		for (FragmentConfigurationField fragmentConfigurationField :
				fragmentConfigurationFields) {

			defaultValuesJSONObject.put(
				fragmentConfigurationField.getName(),
				getFieldValue(
					fragmentConfigurationField,
					LocaleUtil.getMostRelevantLocale(), null));
		}

		return defaultValuesJSONObject;
	}

	@Override
	public Object getConfigurationFieldValue(
		String editableValues, String fieldName,
		FragmentConfigurationFieldDataType fragmentConfigurationFieldDataType) {

		try {
			JSONObject editableValuesJSONObject = _jsonFactory.createJSONObject(
				editableValues);

			JSONObject configurationValuesJSONObject =
				editableValuesJSONObject.getJSONObject(
					FragmentEntryProcessorConstants.
						KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR);

			if (configurationValuesJSONObject == null) {
				return null;
			}

			return _getFieldValue(
				fragmentConfigurationFieldDataType,
				configurationValuesJSONObject.getString(fieldName));
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to parse configuration JSON: " + editableValues,
					jsonException);
			}
		}

		return null;
	}

	@Override
	public JSONObject getConfigurationJSONObject(
			String configuration, String editableValues, Locale locale)
		throws JSONException {

		JSONObject configurationDefaultValuesJSONObject =
			getConfigurationDefaultValuesJSONObject(configuration);

		if (configurationDefaultValuesJSONObject == null) {
			return _jsonFactory.createJSONObject();
		}

		JSONObject editableValuesJSONObject = _jsonFactory.createJSONObject(
			editableValues);

		JSONObject configurationValuesJSONObject =
			editableValuesJSONObject.getJSONObject(
				FragmentEntryProcessorConstants.
					KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR);

		if (configurationValuesJSONObject == null) {
			return configurationDefaultValuesJSONObject;
		}

		List<FragmentConfigurationField> configurationFields =
			getFragmentConfigurationFields(configuration);

		for (FragmentConfigurationField configurationField :
				configurationFields) {

			String name = configurationField.getName();

			Object object = configurationValuesJSONObject.get(name);

			if (object == null) {
				continue;
			}

			configurationDefaultValuesJSONObject.put(
				name,
				getFieldValue(
					configurationField, locale,
					configurationValuesJSONObject.getString(name)));
		}

		return configurationDefaultValuesJSONObject;
	}

	@Override
	public Map<String, Object> getContextObjects(
		JSONObject configurationValuesJSONObject, String configuration,
		Object displayObject, long[] segmentsEntryIds) {

		HashMap<String, Object> contextObjects = new HashMap<>();

		List<FragmentConfigurationField> fragmentConfigurationFields =
			getFragmentConfigurationFields(configuration);

		for (FragmentConfigurationField fragmentConfigurationField :
				fragmentConfigurationFields) {

			String name = fragmentConfigurationField.getName();

			if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(), "itemSelector")) {

				Object contextObject = displayObject;

				if (contextObject != null) {
					contextObjects.put(
						name + _CONTEXT_OBJECT_SUFFIX, contextObject);
				}

				continue;
			}

			if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(),
					"navigationMenuSelector")) {

				Object contextObject = _getNavItemsContextObject(
					configurationValuesJSONObject.getString(name));

				if (contextObject != null) {
					contextObjects.put(
						name + _CONTEXT_OBJECT_SUFFIX, contextObject);
				}
			}
		}

		return contextObjects;
	}

	@Override
	public Object getFieldValue(
		FragmentConfigurationField fragmentConfigurationField, Locale locale,
		String value) {

		return _getFieldValue(fragmentConfigurationField, locale, value);
	}

	@Override
	public Object getFieldValue(
		String editableValues,
		FragmentConfigurationField fragmentConfigurationField, Locale locale) {

		try {
			JSONObject editableValuesJSONObject = _jsonFactory.createJSONObject(
				editableValues);

			JSONObject configurationValuesJSONObject =
				editableValuesJSONObject.getJSONObject(
					FragmentEntryProcessorConstants.
						KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR);

			if (configurationValuesJSONObject == null) {
				return fragmentConfigurationField.getDefaultValue();
			}

			return getFieldValue(
				fragmentConfigurationField, locale,
				configurationValuesJSONObject.getString(
					fragmentConfigurationField.getName(), null));
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return fragmentConfigurationField.getDefaultValue();
		}
	}

	@Override
	public Object getFieldValue(
		String configuration, String editableValues, Locale locale,
		String name) {

		JSONObject editableValuesJSONObject = null;

		try {
			editableValuesJSONObject = _jsonFactory.createJSONObject(
				editableValues);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}

		JSONObject configurationValuesJSONObject =
			editableValuesJSONObject.getJSONObject(
				FragmentEntryProcessorConstants.
					KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR);

		if (configurationValuesJSONObject == null) {
			return null;
		}

		List<FragmentConfigurationField> fragmentConfigurationFields =
			getFragmentConfigurationFields(configuration);

		for (FragmentConfigurationField fragmentConfigurationField :
				fragmentConfigurationFields) {

			if (!Objects.equals(fragmentConfigurationField.getName(), name)) {
				continue;
			}

			return getFieldValue(
				fragmentConfigurationField, locale,
				configurationValuesJSONObject.getString(name));
		}

		return null;
	}

	@Override
	public List<FragmentConfigurationField> getFragmentConfigurationFields(
		String configuration) {

		JSONArray fieldSetsJSONArray = _getFieldSetsJSONArray(configuration);

		if (fieldSetsJSONArray == null) {
			return Collections.emptyList();
		}

		List<FragmentConfigurationField> fragmentConfigurationFields =
			new ArrayList<>();

		Iterator<JSONObject> iterator1 = fieldSetsJSONArray.iterator();

		iterator1.forEachRemaining(
			fieldSetJSONObject -> {
				JSONArray fieldSetFieldsJSONArray =
					fieldSetJSONObject.getJSONArray("fields");

				Iterator<JSONObject> iterator2 =
					fieldSetFieldsJSONArray.iterator();

				iterator2.forEachRemaining(
					fieldSetFieldsJSONObject -> fragmentConfigurationFields.add(
						new FragmentConfigurationField(
							fieldSetFieldsJSONObject)));
			});

		return fragmentConfigurationFields;
	}

	@Override
	public String translateConfiguration(
		JSONObject jsonObject, ResourceBundle resourceBundle) {

		JSONArray fieldSetsJSONArray = jsonObject.getJSONArray("fieldSets");

		if (fieldSetsJSONArray == null) {
			return StringPool.BLANK;
		}

		Iterator<JSONObject> iterator = fieldSetsJSONArray.iterator();

		iterator.forEachRemaining(
			fieldSetJSONObject -> {
				String fieldSetLabel = fieldSetJSONObject.getString("label");

				fieldSetJSONObject.put(
					"label",
					_language.get(
						resourceBundle, fieldSetLabel, fieldSetLabel));

				JSONArray fieldsJSONArray = fieldSetJSONObject.getJSONArray(
					"fields");

				Iterator<JSONObject> fieldsIterator =
					fieldsJSONArray.iterator();

				fieldsIterator.forEachRemaining(
					fieldJSONObject -> _translateConfigurationField(
						fieldJSONObject, resourceBundle));
			});

		return jsonObject.toString();
	}

	private String _getColorPickerCssVariable(String fieldValue) {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if ((serviceContext == null) || Validator.isNull(fieldValue)) {
			return fieldValue;
		}

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		if (themeDisplay == null) {
			return fieldValue;
		}

		Group group = themeDisplay.getScopeGroup();

		FrontendTokenDefinition frontendTokenDefinition =
			_frontendTokenDefinitionRegistry.getFrontendTokenDefinition(
				_layoutSetLocalService.fetchLayoutSet(
					themeDisplay.getSiteGroupId(),
					group.isLayoutSetPrototype()));

		if (frontendTokenDefinition == null) {
			return fieldValue;
		}

		Collection<FrontendToken> frontendTokens =
			frontendTokenDefinition.getFrontendTokens();

		for (FrontendToken frontendToken : frontendTokens) {
			JSONObject jsonObject = frontendToken.getJSONObject(
				LocaleUtil.getMostRelevantLocale());

			if (!Objects.equals(jsonObject.getString("name"), fieldValue)) {
				continue;
			}

			List<FrontendTokenMapping> frontendTokenMappings = new ArrayList<>(
				frontendToken.getFrontendTokenMappings(
					FrontendTokenMapping.TYPE_CSS_VARIABLE));

			if (frontendTokenMappings.isEmpty()) {
				return fieldValue;
			}

			FrontendTokenMapping frontendTokenMapping =
				frontendTokenMappings.get(0);

			return "var(--" + frontendTokenMapping.getValue() + ")";
		}

		return fieldValue;
	}

	private JSONArray _getFieldSetsJSONArray(String configuration) {
		try {
			JSONObject configurationJSONObject = _jsonFactory.createJSONObject(
				configuration);

			return configurationJSONObject.getJSONArray("fieldSets");
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to parse configuration JSON: " + configuration,
					jsonException);
			}
		}

		return null;
	}

	private Object _getFieldValue(
		FragmentConfigurationField fragmentConfigurationField, Locale locale,
		String value) {

		String parsedValue = GetterUtil.getString(value);

		if (fragmentConfigurationField.isLocalizable() &&
			JSONUtil.isJSONObject(parsedValue)) {

			try {
				JSONObject valueJSONObject = _jsonFactory.createJSONObject(
					parsedValue);

				parsedValue = valueJSONObject.getString(
					LocaleUtil.toLanguageId(locale),
					valueJSONObject.getString(
						LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()),
						fragmentConfigurationField.getDefaultValue()));
			}
			catch (JSONException jsonException) {
				_log.error(
					"Unable to parse configuration value JSON", jsonException);
			}
		}
		else if (value == null) {
			parsedValue = fragmentConfigurationField.getDefaultValue();
		}

		if (StringUtil.equalsIgnoreCase(
				fragmentConfigurationField.getType(), "checkbox")) {

			return _getFieldValue(
				FragmentConfigurationFieldDataType.BOOLEAN, parsedValue);
		}
		else if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(), "colorPalette")) {

			JSONObject jsonObject = (JSONObject)_getFieldValue(
				FragmentConfigurationFieldDataType.OBJECT, parsedValue);

			if ((jsonObject != null) && jsonObject.isNull("color") &&
				!jsonObject.isNull("cssClass")) {

				jsonObject.put("color", jsonObject.getString("cssClass"));
			}

			return jsonObject;
		}
		else if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(), "colorPicker")) {

			String fieldValue = (String)_getFieldValue(
				FragmentConfigurationFieldDataType.STRING, parsedValue);

			return _getColorPickerCssVariable(fieldValue);
		}
		else if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(), "length") ||
				 StringUtil.equalsIgnoreCase(
					 fragmentConfigurationField.getType(), "select") ||
				 StringUtil.equalsIgnoreCase(
					 fragmentConfigurationField.getType(), "text")) {

			FragmentConfigurationFieldDataType
				fragmentConfigurationFieldDataType =
					fragmentConfigurationField.
						getFragmentConfigurationFieldDataType();

			if (fragmentConfigurationFieldDataType == null) {
				fragmentConfigurationFieldDataType =
					FragmentConfigurationFieldDataType.STRING;
			}

			return _getFieldValue(
				fragmentConfigurationFieldDataType, parsedValue);
		}
		else if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(), "url")) {

			return _getURLValue(parsedValue);
		}

		return _getFieldValue(
			FragmentConfigurationFieldDataType.STRING, parsedValue);
	}

	private Object _getFieldValue(
		FragmentConfigurationFieldDataType fragmentConfigurationFieldDataType,
		String value) {

		if (fragmentConfigurationFieldDataType ==
				FragmentConfigurationFieldDataType.ARRAY) {

			try {
				return _jsonFactory.createJSONArray(value);
			}
			catch (JSONException jsonException) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unable to parse configuration JSON: " + value,
						jsonException);
				}
			}
		}
		else if (fragmentConfigurationFieldDataType ==
					FragmentConfigurationFieldDataType.BOOLEAN) {

			return GetterUtil.getBoolean(value);
		}
		else if (fragmentConfigurationFieldDataType ==
					FragmentConfigurationFieldDataType.DOUBLE) {

			return GetterUtil.getDouble(value);
		}
		else if (fragmentConfigurationFieldDataType ==
					FragmentConfigurationFieldDataType.INTEGER) {

			return GetterUtil.getInteger(value);
		}
		else if (fragmentConfigurationFieldDataType ==
					FragmentConfigurationFieldDataType.OBJECT) {

			try {
				return _jsonFactory.createJSONObject(value);
			}
			catch (JSONException jsonException) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unable to parse configuration JSON: " + value,
						jsonException);
				}
			}
		}
		else if (fragmentConfigurationFieldDataType ==
					FragmentConfigurationFieldDataType.STRING) {

			return value;
		}

		return null;
	}

	private Object _getNavItemsContextObject(String value) {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		FragmentEntryMenuDisplayConfiguration
			fragmentEntryMenuDisplayConfiguration =
				new FragmentEntryMenuDisplayConfiguration(value);

		return NavItemUtil.getNavigationMenuContext(
			1, "auto", serviceContext.getRequest(),
			fragmentEntryMenuDisplayConfiguration.getNavigationMenuMode(),
			false, fragmentEntryMenuDisplayConfiguration.getRootItemId(),
			fragmentEntryMenuDisplayConfiguration.getRootItemLevel(),
			fragmentEntryMenuDisplayConfiguration.getRootItemType(),
			fragmentEntryMenuDisplayConfiguration.getSiteNavigationMenuId());
	}

	private Object _getURLValue(String value) {
		JSONObject jsonObject = (JSONObject)_getFieldValue(
			FragmentConfigurationFieldDataType.OBJECT, value);

		JSONObject layoutJSONObject = jsonObject.getJSONObject("layout");

		if (layoutJSONObject == null) {
			return jsonObject.getString("href");
		}

		long groupId = layoutJSONObject.getLong("groupId");
		boolean privateLayout = layoutJSONObject.getBoolean("privateLayout");
		long layoutId = layoutJSONObject.getLong("layoutId");

		Layout layout = _layoutLocalService.fetchLayout(
			groupId, privateLayout, layoutId);

		if (layout == null) {
			return StringPool.POUND;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		try {
			return _portal.getLayoutFullURL(
				layout, serviceContext.getThemeDisplay());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
	}

	private void _translateConfigurationField(
		JSONObject fieldJSONObject, ResourceBundle resourceBundle) {

		String fieldDescription = fieldJSONObject.getString("description");

		fieldJSONObject.put(
			"description",
			_language.get(resourceBundle, fieldDescription, fieldDescription));

		String fieldLabel = fieldJSONObject.getString("label");

		fieldJSONObject.put(
			"label", _language.get(resourceBundle, fieldLabel, fieldLabel));

		String type = fieldJSONObject.getString("type");

		if (!Objects.equals(type, "select") && !Objects.equals(type, "text")) {
			return;
		}

		if (fieldJSONObject.getBoolean("localizable")) {
			String defaultValue = fieldJSONObject.getString("defaultValue");

			fieldJSONObject.put(
				"defaultValue",
				_language.get(resourceBundle, defaultValue, defaultValue));
		}

		JSONObject typeOptionsJSONObject = fieldJSONObject.getJSONObject(
			"typeOptions");

		if (typeOptionsJSONObject == null) {
			return;
		}

		if (Objects.equals(type, "select")) {
			JSONArray validValuesJSONArray = typeOptionsJSONObject.getJSONArray(
				"validValues");

			Iterator<JSONObject> validValuesIterator =
				validValuesJSONArray.iterator();

			validValuesIterator.forEachRemaining(
				validValueJSONObject -> {
					String value = validValueJSONObject.getString("value");

					String label = validValueJSONObject.getString(
						"label", value);

					validValueJSONObject.put(
						"label", _language.get(resourceBundle, label, label));
				});
		}
		else {
			JSONObject validationJSONObject =
				typeOptionsJSONObject.getJSONObject("validation");

			if ((validationJSONObject != null) &&
				validationJSONObject.has("errorMessage")) {

				String errorMessage = validationJSONObject.getString(
					"errorMessage");

				validationJSONObject.put(
					"errorMessage",
					_language.get(resourceBundle, errorMessage, errorMessage));
			}
		}
	}

	private static final String _CONTEXT_OBJECT_LIST_SUFFIX = "ObjectList";

	private static final String _CONTEXT_OBJECT_SUFFIX = "Object";

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryConfigurationParserImpl.class);

	@Reference
	private FrontendTokenDefinitionRegistry _frontendTokenDefinitionRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private Portal _portal;

}