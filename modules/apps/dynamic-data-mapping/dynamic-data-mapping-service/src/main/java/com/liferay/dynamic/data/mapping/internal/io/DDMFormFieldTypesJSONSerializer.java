/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.io;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesRegistry;
import com.liferay.dynamic.data.mapping.io.DDMFormFieldTypesSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormFieldTypesSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormFieldTypesSerializerSerializeResponse;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Basto
 */
@Component(
	property = "ddm.form.field.types.serializer.type=json",
	service = DDMFormFieldTypesSerializer.class
)
public class DDMFormFieldTypesJSONSerializer
	implements DDMFormFieldTypesSerializer {

	@Override
	public DDMFormFieldTypesSerializerSerializeResponse serialize(
		DDMFormFieldTypesSerializerSerializeRequest
			ddmFormFieldTypesSerializerSerializeRequest) {

		List<DDMFormFieldType> ddmFormFieldTypes =
			ddmFormFieldTypesSerializerSerializeRequest.getDDMFormFieldTypes();

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		try {
			for (DDMFormFieldType ddmFormFieldType : ddmFormFieldTypes) {
				jsonArray.put(toJSONObject(ddmFormFieldType));
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception);
			}
		}

		DDMFormFieldTypesSerializerSerializeResponse.Builder builder =
			DDMFormFieldTypesSerializerSerializeResponse.Builder.newBuilder(
				jsonArray.toString());

		return builder.build();
	}

	protected JSONObject toJSONObject(DDMFormFieldType ddmFormFieldType)
		throws PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		Map<String, Object> ddmFormFieldTypeProperties =
			_ddmFormFieldTypeServicesRegistry.getDDMFormFieldTypeProperties(
				ddmFormFieldType.getName());

		jsonObject.put(
			"group",
			MapUtil.getString(
				ddmFormFieldTypeProperties, "ddm.form.field.type.group")
		).put(
			"icon",
			MapUtil.getString(
				ddmFormFieldTypeProperties, "ddm.form.field.type.icon",
				"icon-ok-circle")
		).put(
			"javaScriptClass",
			MapUtil.getString(
				ddmFormFieldTypeProperties, "ddm.form.field.type.js.class.name",
				"Liferay.DDM.Renderer.Field")
		).put(
			"javaScriptModule",
			MapUtil.getString(
				ddmFormFieldTypeProperties, "ddm.form.field.type.js.module",
				"liferay-ddm-form-renderer-field")
		);

		String description = MapUtil.getString(
			ddmFormFieldTypeProperties, "ddm.form.field.type.description");

		String label = MapUtil.getString(
			ddmFormFieldTypeProperties, "ddm.form.field.type.label");

		Locale locale = LocaleThreadLocal.getThemeDisplayLocale();

		try {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, ddmFormFieldType.getClass());

			if (Validator.isNotNull(description)) {
				jsonObject.put(
					"description", _language.get(resourceBundle, description));
			}

			if (Validator.isNotNull(label)) {
				jsonObject.put("label", _language.get(resourceBundle, label));
			}
		}
		catch (MissingResourceException missingResourceException) {
			if (_log.isWarnEnabled()) {
				_log.warn(missingResourceException);
			}
		}

		jsonObject.put(
			"name", ddmFormFieldType.getName()
		).put(
			"system",
			MapUtil.getBoolean(
				ddmFormFieldTypeProperties, "ddm.form.field.type.system")
		);

		DDMFormFieldRenderer ddmFormFieldRenderer =
			_ddmFormFieldTypeServicesRegistry.getDDMFormFieldRenderer(
				ddmFormFieldType.getName());

		if (ddmFormFieldRenderer instanceof BaseDDMFormFieldRenderer) {
			BaseDDMFormFieldRenderer baseDDMFormFieldRenderer =
				(BaseDDMFormFieldRenderer)ddmFormFieldRenderer;

			jsonObject.put(
				"templateNamespace",
				baseDDMFormFieldRenderer.getTemplateNamespace());
		}

		return jsonObject;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormFieldTypesJSONSerializer.class);

	@Reference
	private DDMFormFieldTypeServicesRegistry _ddmFormFieldTypeServicesRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

}