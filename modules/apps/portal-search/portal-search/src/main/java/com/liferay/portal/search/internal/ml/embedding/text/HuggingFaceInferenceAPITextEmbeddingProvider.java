/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.ml.embedding.text;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.internal.ml.embedding.text.util.ConfigurationValidationUtil;
import com.liferay.portal.search.rest.dto.v1_0.EmbeddingProviderConfiguration;

import java.net.HttpURLConnection;

import java.util.List;
import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class HuggingFaceInferenceAPITextEmbeddingProvider
	implements TextEmbeddingProvider {

	@Override
	public Double[] getEmbedding(
		EmbeddingProviderConfiguration embeddingProviderConfiguration,
		String text) {

		Map<String, Object> attributes =
			(Map<String, Object>)embeddingProviderConfiguration.getAttributes();

		if (!ConfigurationValidationUtil.validateAttributes(
				attributes, new String[] {"accessToken", "model"})) {

			return new Double[0];
		}

		return _getEmbedding(attributes, text);
	}

	private Double[] _getEmbedding(
		Map<String, Object> attributes, String text) {

		try {
			Http.Options options = _getOptions(attributes, text);

			String responseJSON = HttpUtil.URLtoString(options);

			Http.Response response = options.getResponse();

			if (response.getResponseCode() ==
					HttpURLConnection.HTTP_UNAVAILABLE) {

				options.addHeader("x-wait-for-model", "true");
				options.setTimeout(
					MapUtil.getInteger(attributes, "modelTimeout", 30) * 1000);

				responseJSON = HttpUtil.URLtoString(options);
			}

			if (!JSONUtil.isJSONArray(responseJSON)) {
				throw new IllegalArgumentException(responseJSON);
			}
			else if (!_isValidResponse(responseJSON)) {
				if (_log.isDebugEnabled()) {
					_log.debug("Invalid response: " + responseJSON);
				}

				throw new IllegalArgumentException(
					"The selected model is not valid for creating text " +
						"embedding");
			}

			List<Double> list = JSONUtil.toDoubleList(
				_getJSONArray(JSONFactoryUtil.createJSONArray(responseJSON)));

			return list.toArray(new Double[0]);
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}
	}

	private JSONArray _getJSONArray(JSONArray jsonArray1) {
		JSONArray jsonArray2 = jsonArray1.getJSONArray(0);

		if (jsonArray2 != null) {
			return _getJSONArray(jsonArray2);
		}

		return jsonArray1;
	}

	private Http.Options _getOptions(
		Map<String, Object> attributes, String text) {

		Http.Options options = new Http.Options();

		JSONObject jsonObject = JSONUtil.put("inputs", text);

		options.addHeader(
			HttpHeaders.AUTHORIZATION,
			"Bearer " + MapUtil.getString(attributes, "accessToken"));
		options.addHeader(
			HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
		options.setBody(
			jsonObject.toString(), ContentTypes.APPLICATION_JSON,
			StringPool.UTF8);
		options.setCookieSpec(Http.CookieSpec.STANDARD);
		options.setLocation(
			"https://api-inference.huggingface.co/models/" +
				MapUtil.getString(attributes, "model"));
		options.setPost(true);

		return options;
	}

	private boolean _isValidResponse(String s) {
		if (StringUtil.startsWith(s, "[[") && StringUtil.endsWith(s, "]]")) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HuggingFaceInferenceAPITextEmbeddingProvider.class);

}