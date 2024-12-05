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
import com.liferay.portal.search.internal.ml.embedding.text.util.ConfigurationValidationUtil;
import com.liferay.portal.search.rest.dto.v1_0.EmbeddingProviderConfiguration;

import java.util.List;
import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class OpenAITextEmbeddingProvider implements TextEmbeddingProvider {

	@Override
	public Double[] getEmbedding(
		EmbeddingProviderConfiguration embeddingProviderConfiguration,
		String text) {

		Map<String, Object> attributes =
			(Map<String, Object>)embeddingProviderConfiguration.getAttributes();

		if (!ConfigurationValidationUtil.validateAttributes(
				attributes, new String[] {"apiKey", "model"})) {

			return new Double[0];
		}

		return _getEmbedding(attributes, text);
	}

	private Double[] _getEmbedding(
		Map<String, Object> attributes, String text) {

		try {
			JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
				HttpUtil.URLtoString(_getOptions(attributes, text)));

			JSONArray dataJSONArray = responseJSONObject.getJSONArray("data");

			if (JSONUtil.isEmpty(dataJSONArray)) {
				throw new IllegalArgumentException(
					responseJSONObject.toString());
			}

			JSONObject dataJSONObject = dataJSONArray.getJSONObject(0);

			List<Double> embedding = JSONUtil.toDoubleList(
				dataJSONObject.getJSONArray("embedding"));

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Usage: " + responseJSONObject.getJSONObject("usage"));
			}

			return embedding.toArray(new Double[0]);
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}
	}

	private Http.Options _getOptions(
		Map<String, Object> attributes, String text) {

		Http.Options options = new Http.Options();

		options.addHeader(
			HttpHeaders.AUTHORIZATION,
			"Bearer " + MapUtil.getString(attributes, "apiKey"));
		options.addHeader(
			HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
		options.setBody(
			_getRequestBody(attributes, text), ContentTypes.APPLICATION_JSON,
			StringPool.UTF8);
		options.setCookieSpec(Http.CookieSpec.STANDARD);
		options.setLocation("https://api.openai.com/v1/embeddings");
		options.setPost(true);

		return options;
	}

	private String _getRequestBody(
		Map<String, Object> attributes, String text) {

		JSONObject requestBodyJSONObject = JSONUtil.put(
			"input", text
		).put(
			"model", MapUtil.getString(attributes, "model")
		);

		if (attributes.containsKey("dimensions")) {
			requestBodyJSONObject.put(
				"dimensions", MapUtil.getInteger(attributes, "dimensions"));
		}

		if (attributes.containsKey("user")) {
			requestBodyJSONObject.put(
				"user", MapUtil.getString(attributes, "user"));
		}

		return requestBodyJSONObject.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OpenAITextEmbeddingProvider.class);

}