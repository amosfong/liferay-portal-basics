/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionObserver;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionObserverAware;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionParameterAccessor;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionParameterAccessorAware;
import com.liferay.dynamic.data.mapping.expression.UpdateFieldPropertyRequest;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.KeyValuePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * @author Leonardo Barros
 */
public class SetOptionsFunction
	implements DDMExpressionFunction.Function2<String, String, Boolean>,
			   DDMExpressionObserverAware, DDMExpressionParameterAccessorAware {

	public static final String NAME = "setOptions";

	public SetOptionsFunction(JSONFactory jsonFactory) {
		this.jsonFactory = jsonFactory;
	}

	@Override
	public Boolean apply(String field, String json) {
		if ((_ddmExpressionObserver == null) ||
			(_ddmExpressionParameterAccessor == null)) {

			return false;
		}

		UpdateFieldPropertyRequest.Builder builder =
			UpdateFieldPropertyRequest.Builder.newBuilder(
				field, "options",
				_createKeyValuePairList(
					json, _ddmExpressionParameterAccessor.getLocale()));

		_ddmExpressionObserver.updateFieldProperty(builder.build());

		return true;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setDDMExpressionObserver(
		DDMExpressionObserver ddmExpressionObserver) {

		_ddmExpressionObserver = ddmExpressionObserver;
	}

	@Override
	public void setDDMExpressionParameterAccessor(
		DDMExpressionParameterAccessor ddmExpressionParameterAccessor) {

		_ddmExpressionParameterAccessor = ddmExpressionParameterAccessor;
	}

	protected JSONFactory jsonFactory;

	private List<KeyValuePair> _createKeyValuePairList(
		String value, Locale locale) {

		String languageId = LanguageUtil.getLanguageId(locale);

		List<KeyValuePair> keyValuePairs = new ArrayList<>();

		try {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				String.valueOf(value));

			Iterator<String> iterator = jsonObject.keys();

			while (iterator.hasNext()) {
				String currentLanguageId = iterator.next();

				if (currentLanguageId.equals(languageId)) {
					JSONArray jsonArray = jsonObject.getJSONArray(
						currentLanguageId);

					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject optionJSONObject = jsonArray.getJSONObject(
							i);

						KeyValuePair keyValuePair = new KeyValuePair(
							optionJSONObject.getString("value"),
							optionJSONObject.getString("label"));

						keyValuePairs.add(keyValuePair);
					}

					break;
				}
			}
		}
		catch (JSONException jsonException) {
			_log.error(jsonException);
		}

		return keyValuePairs;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SetOptionsFunction.class);

	private DDMExpressionObserver _ddmExpressionObserver;
	private DDMExpressionParameterAccessor _ddmExpressionParameterAccessor;

}