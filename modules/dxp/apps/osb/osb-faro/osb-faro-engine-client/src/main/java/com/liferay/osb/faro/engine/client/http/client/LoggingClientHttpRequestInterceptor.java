/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.engine.client.http.client;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

/**
 * @author Inácio Nery
 */
public class LoggingClientHttpRequestInterceptor
	implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(
			HttpRequest httpRequest, byte[] bytes,
			ClientHttpRequestExecution clientHttpRequestExecution)
		throws IOException {

		if (_log.isDebugEnabled()) {
			_log.debug(String.format("Request URI: %s", httpRequest.getURI()));
			_log.debug(
				String.format("Request method: %s", httpRequest.getMethod()));
			_log.debug(
				String.format("Request headers: %s", httpRequest.getHeaders()));
			_log.debug(
				String.format(
					"Request body: %s",
					new String(bytes, StandardCharsets.UTF_8)));
		}

		ClientHttpResponse clientHttpResponse =
			clientHttpRequestExecution.execute(httpRequest, bytes);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Response status code: %s",
					clientHttpResponse.getStatusCode()));
			_log.debug(
				String.format(
					"Response status text: %s",
					clientHttpResponse.getStatusText()));
			_log.debug(
				String.format(
					"Response headers: %s", clientHttpResponse.getHeaders()));
			_log.debug(
				String.format(
					"Response body: %s",
					StreamUtils.copyToString(
						clientHttpResponse.getBody(), StandardCharsets.UTF_8)));
		}

		return clientHttpResponse;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoggingClientHttpRequestInterceptor.class);

}