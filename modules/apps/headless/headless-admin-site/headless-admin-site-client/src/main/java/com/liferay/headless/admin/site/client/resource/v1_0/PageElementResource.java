/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.resource.v1_0;

import com.liferay.headless.admin.site.client.dto.v1_0.FragmentComposition;
import com.liferay.headless.admin.site.client.dto.v1_0.PageElement;
import com.liferay.headless.admin.site.client.http.HttpInvoker;
import com.liferay.headless.admin.site.client.pagination.Page;
import com.liferay.headless.admin.site.client.problem.Problem;
import com.liferay.headless.admin.site.client.serdes.v1_0.PageElementSerDes;

import java.net.URL;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
public interface PageElementResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<PageElement>
			getSiteSiteByExternalReferenceCodePageExperiencePageElementsPage(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode, Boolean flatten)
		throws Exception;

	public HttpInvoker.HttpResponse
			getSiteSiteByExternalReferenceCodePageExperiencePageElementsPageHttpResponse(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode, Boolean flatten)
		throws Exception;

	public PageElement
			postSiteSiteByExternalReferenceCodePageExperiencePageElement(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				PageElement pageElement)
		throws Exception;

	public HttpInvoker.HttpResponse
			postSiteSiteByExternalReferenceCodePageExperiencePageElementHttpResponse(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				PageElement pageElement)
		throws Exception;

	public void deleteSiteSiteByExternalReferenceCodePageElement(
			String siteExternalReferenceCode,
			String pageSpecificationExternalReferenceCode,
			String pageExperienceExternalReferenceCode,
			String pageElementExternalReferenceCode)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteSiteSiteByExternalReferenceCodePageElementHttpResponse(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode)
		throws Exception;

	public PageElement getSiteSiteByExternalReferenceCodePageElement(
			String siteExternalReferenceCode,
			String pageSpecificationExternalReferenceCode,
			String pageExperienceExternalReferenceCode,
			String pageElementExternalReferenceCode)
		throws Exception;

	public HttpInvoker.HttpResponse
			getSiteSiteByExternalReferenceCodePageElementHttpResponse(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode)
		throws Exception;

	public PageElement patchSiteSiteByExternalReferenceCodePageElement(
			String siteExternalReferenceCode,
			String pageSpecificationExternalReferenceCode,
			String pageExperienceExternalReferenceCode,
			String pageElementExternalReferenceCode, PageElement pageElement)
		throws Exception;

	public HttpInvoker.HttpResponse
			patchSiteSiteByExternalReferenceCodePageElementHttpResponse(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode,
				PageElement pageElement)
		throws Exception;

	public PageElement putSiteSiteByExternalReferenceCodePageElement(
			String siteExternalReferenceCode,
			String pageSpecificationExternalReferenceCode,
			String pageExperienceExternalReferenceCode,
			String pageElementExternalReferenceCode, PageElement pageElement)
		throws Exception;

	public HttpInvoker.HttpResponse
			putSiteSiteByExternalReferenceCodePageElementHttpResponse(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode,
				PageElement pageElement)
		throws Exception;

	public PageElement
			postSiteSiteByExternalReferenceCodePageElementFragmentComposition(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode, Integer position,
				FragmentComposition fragmentComposition)
		throws Exception;

	public HttpInvoker.HttpResponse
			postSiteSiteByExternalReferenceCodePageElementFragmentCompositionHttpResponse(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode, Integer position,
				FragmentComposition fragmentComposition)
		throws Exception;

	public Page<PageElement>
			getSiteSiteByExternalReferenceCodePageElementPageElementsPage(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode, Boolean flatten)
		throws Exception;

	public HttpInvoker.HttpResponse
			getSiteSiteByExternalReferenceCodePageElementPageElementsPageHttpResponse(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode, Boolean flatten)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public Builder bearerToken(String token) {
			return header("Authorization", "Bearer " + token);
		}

		public PageElementResource build() {
			return new PageElementResourceImpl(this);
		}

		public Builder contextPath(String contextPath) {
			_contextPath = contextPath;

			return this;
		}

		public Builder endpoint(String address, String scheme) {
			String[] addressParts = address.split(":");

			String host = addressParts[0];

			int port = 443;

			if (addressParts.length > 1) {
				String portString = addressParts[1];

				try {
					port = Integer.parseInt(portString);
				}
				catch (NumberFormatException numberFormatException) {
					throw new IllegalArgumentException(
						"Unable to parse port from " + portString);
				}
			}

			return endpoint(host, port, scheme);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder endpoint(URL url) {
			return endpoint(url.getHost(), url.getPort(), url.getProtocol());
		}

		public Builder header(String key, String value) {
			_headers.put(key, value);

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public Builder parameter(String key, String value) {
			_parameters.put(key, value);

			return this;
		}

		public Builder parameters(String... parameters) {
			if ((parameters.length % 2) != 0) {
				throw new IllegalArgumentException(
					"Parameters length is not an even number");
			}

			for (int i = 0; i < parameters.length; i += 2) {
				String parameterName = String.valueOf(parameters[i]);
				String parameterValue = String.valueOf(parameters[i + 1]);

				_parameters.put(parameterName, parameterValue);
			}

			return this;
		}

		private Builder() {
		}

		private String _contextPath = "";
		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "localhost";
		private Locale _locale;
		private String _login = "";
		private String _password = "";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class PageElementResourceImpl implements PageElementResource {

		public Page<PageElement>
				getSiteSiteByExternalReferenceCodePageExperiencePageElementsPage(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode, Boolean flatten)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getSiteSiteByExternalReferenceCodePageExperiencePageElementsPageHttpResponse(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode,
					pageExperienceExternalReferenceCode, flatten);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				Problem.ProblemException problemException = null;

				if (Objects.equals(
						httpResponse.getContentType(), "application/json")) {

					problemException = new Problem.ProblemException(
						Problem.toDTO(content));
				}
				else {
					_logger.log(
						Level.WARNING,
						"Unable to process content type: " +
							httpResponse.getContentType());

					Problem problem = new Problem();

					problem.setStatus(
						String.valueOf(httpResponse.getStatusCode()));

					problemException = new Problem.ProblemException(problem);
				}

				throw problemException;
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, PageElementSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getSiteSiteByExternalReferenceCodePageExperiencePageElementsPageHttpResponse(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode, Boolean flatten)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (flatten != null) {
				httpInvoker.parameter("flatten", String.valueOf(flatten));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/headless-admin-site/v1.0/sites/{siteExternalReferenceCode}/page-specifications/{pageSpecificationExternalReferenceCode}/page-experiences/{pageExperienceExternalReferenceCode}/page-elements");

			httpInvoker.path(
				"siteExternalReferenceCode", siteExternalReferenceCode);
			httpInvoker.path(
				"pageSpecificationExternalReferenceCode",
				pageSpecificationExternalReferenceCode);
			httpInvoker.path(
				"pageExperienceExternalReferenceCode",
				pageExperienceExternalReferenceCode);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public PageElement
				postSiteSiteByExternalReferenceCodePageExperiencePageElement(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					PageElement pageElement)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postSiteSiteByExternalReferenceCodePageExperiencePageElementHttpResponse(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode,
					pageExperienceExternalReferenceCode, pageElement);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				Problem.ProblemException problemException = null;

				if (Objects.equals(
						httpResponse.getContentType(), "application/json")) {

					problemException = new Problem.ProblemException(
						Problem.toDTO(content));
				}
				else {
					_logger.log(
						Level.WARNING,
						"Unable to process content type: " +
							httpResponse.getContentType());

					Problem problem = new Problem();

					problem.setStatus(
						String.valueOf(httpResponse.getStatusCode()));

					problemException = new Problem.ProblemException(problem);
				}

				throw problemException;
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return PageElementSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				postSiteSiteByExternalReferenceCodePageExperiencePageElementHttpResponse(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					PageElement pageElement)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(pageElement.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/headless-admin-site/v1.0/sites/{siteExternalReferenceCode}/page-specifications/{pageSpecificationExternalReferenceCode}/page-experiences/{pageExperienceExternalReferenceCode}/page-elements");

			httpInvoker.path(
				"siteExternalReferenceCode", siteExternalReferenceCode);
			httpInvoker.path(
				"pageSpecificationExternalReferenceCode",
				pageSpecificationExternalReferenceCode);
			httpInvoker.path(
				"pageExperienceExternalReferenceCode",
				pageExperienceExternalReferenceCode);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteSiteSiteByExternalReferenceCodePageElement(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteSiteSiteByExternalReferenceCodePageElementHttpResponse(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode,
					pageExperienceExternalReferenceCode,
					pageElementExternalReferenceCode);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				Problem.ProblemException problemException = null;

				if (Objects.equals(
						httpResponse.getContentType(), "application/json")) {

					problemException = new Problem.ProblemException(
						Problem.toDTO(content));
				}
				else {
					_logger.log(
						Level.WARNING,
						"Unable to process content type: " +
							httpResponse.getContentType());

					Problem problem = new Problem();

					problem.setStatus(
						String.valueOf(httpResponse.getStatusCode()));

					problemException = new Problem.ProblemException(problem);
				}

				throw problemException;
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteSiteSiteByExternalReferenceCodePageElementHttpResponse(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					String pageElementExternalReferenceCode)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/headless-admin-site/v1.0/sites/{siteExternalReferenceCode}/page-specifications/{pageSpecificationExternalReferenceCode}/page-experiences/{pageExperienceExternalReferenceCode}/page-elements/{pageElementExternalReferenceCode}");

			httpInvoker.path(
				"siteExternalReferenceCode", siteExternalReferenceCode);
			httpInvoker.path(
				"pageSpecificationExternalReferenceCode",
				pageSpecificationExternalReferenceCode);
			httpInvoker.path(
				"pageExperienceExternalReferenceCode",
				pageExperienceExternalReferenceCode);
			httpInvoker.path(
				"pageElementExternalReferenceCode",
				pageElementExternalReferenceCode);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public PageElement getSiteSiteByExternalReferenceCodePageElement(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getSiteSiteByExternalReferenceCodePageElementHttpResponse(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode,
					pageExperienceExternalReferenceCode,
					pageElementExternalReferenceCode);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				Problem.ProblemException problemException = null;

				if (Objects.equals(
						httpResponse.getContentType(), "application/json")) {

					problemException = new Problem.ProblemException(
						Problem.toDTO(content));
				}
				else {
					_logger.log(
						Level.WARNING,
						"Unable to process content type: " +
							httpResponse.getContentType());

					Problem problem = new Problem();

					problem.setStatus(
						String.valueOf(httpResponse.getStatusCode()));

					problemException = new Problem.ProblemException(problem);
				}

				throw problemException;
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return PageElementSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getSiteSiteByExternalReferenceCodePageElementHttpResponse(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					String pageElementExternalReferenceCode)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/headless-admin-site/v1.0/sites/{siteExternalReferenceCode}/page-specifications/{pageSpecificationExternalReferenceCode}/page-experiences/{pageExperienceExternalReferenceCode}/page-elements/{pageElementExternalReferenceCode}");

			httpInvoker.path(
				"siteExternalReferenceCode", siteExternalReferenceCode);
			httpInvoker.path(
				"pageSpecificationExternalReferenceCode",
				pageSpecificationExternalReferenceCode);
			httpInvoker.path(
				"pageExperienceExternalReferenceCode",
				pageExperienceExternalReferenceCode);
			httpInvoker.path(
				"pageElementExternalReferenceCode",
				pageElementExternalReferenceCode);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public PageElement patchSiteSiteByExternalReferenceCodePageElement(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode,
				PageElement pageElement)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				patchSiteSiteByExternalReferenceCodePageElementHttpResponse(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode,
					pageExperienceExternalReferenceCode,
					pageElementExternalReferenceCode, pageElement);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				Problem.ProblemException problemException = null;

				if (Objects.equals(
						httpResponse.getContentType(), "application/json")) {

					problemException = new Problem.ProblemException(
						Problem.toDTO(content));
				}
				else {
					_logger.log(
						Level.WARNING,
						"Unable to process content type: " +
							httpResponse.getContentType());

					Problem problem = new Problem();

					problem.setStatus(
						String.valueOf(httpResponse.getStatusCode()));

					problemException = new Problem.ProblemException(problem);
				}

				throw problemException;
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return PageElementSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				patchSiteSiteByExternalReferenceCodePageElementHttpResponse(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					String pageElementExternalReferenceCode,
					PageElement pageElement)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(pageElement.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PATCH);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/headless-admin-site/v1.0/sites/{siteExternalReferenceCode}/page-specifications/{pageSpecificationExternalReferenceCode}/page-experiences/{pageExperienceExternalReferenceCode}/page-elements/{pageElementExternalReferenceCode}");

			httpInvoker.path(
				"siteExternalReferenceCode", siteExternalReferenceCode);
			httpInvoker.path(
				"pageSpecificationExternalReferenceCode",
				pageSpecificationExternalReferenceCode);
			httpInvoker.path(
				"pageExperienceExternalReferenceCode",
				pageExperienceExternalReferenceCode);
			httpInvoker.path(
				"pageElementExternalReferenceCode",
				pageElementExternalReferenceCode);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public PageElement putSiteSiteByExternalReferenceCodePageElement(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				String pageExperienceExternalReferenceCode,
				String pageElementExternalReferenceCode,
				PageElement pageElement)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putSiteSiteByExternalReferenceCodePageElementHttpResponse(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode,
					pageExperienceExternalReferenceCode,
					pageElementExternalReferenceCode, pageElement);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				Problem.ProblemException problemException = null;

				if (Objects.equals(
						httpResponse.getContentType(), "application/json")) {

					problemException = new Problem.ProblemException(
						Problem.toDTO(content));
				}
				else {
					_logger.log(
						Level.WARNING,
						"Unable to process content type: " +
							httpResponse.getContentType());

					Problem problem = new Problem();

					problem.setStatus(
						String.valueOf(httpResponse.getStatusCode()));

					problemException = new Problem.ProblemException(problem);
				}

				throw problemException;
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return PageElementSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				putSiteSiteByExternalReferenceCodePageElementHttpResponse(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					String pageElementExternalReferenceCode,
					PageElement pageElement)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(pageElement.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/headless-admin-site/v1.0/sites/{siteExternalReferenceCode}/page-specifications/{pageSpecificationExternalReferenceCode}/page-experiences/{pageExperienceExternalReferenceCode}/page-elements/{pageElementExternalReferenceCode}");

			httpInvoker.path(
				"siteExternalReferenceCode", siteExternalReferenceCode);
			httpInvoker.path(
				"pageSpecificationExternalReferenceCode",
				pageSpecificationExternalReferenceCode);
			httpInvoker.path(
				"pageExperienceExternalReferenceCode",
				pageExperienceExternalReferenceCode);
			httpInvoker.path(
				"pageElementExternalReferenceCode",
				pageElementExternalReferenceCode);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public PageElement
				postSiteSiteByExternalReferenceCodePageElementFragmentComposition(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					String pageElementExternalReferenceCode, Integer position,
					FragmentComposition fragmentComposition)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postSiteSiteByExternalReferenceCodePageElementFragmentCompositionHttpResponse(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode,
					pageExperienceExternalReferenceCode,
					pageElementExternalReferenceCode, position,
					fragmentComposition);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				Problem.ProblemException problemException = null;

				if (Objects.equals(
						httpResponse.getContentType(), "application/json")) {

					problemException = new Problem.ProblemException(
						Problem.toDTO(content));
				}
				else {
					_logger.log(
						Level.WARNING,
						"Unable to process content type: " +
							httpResponse.getContentType());

					Problem problem = new Problem();

					problem.setStatus(
						String.valueOf(httpResponse.getStatusCode()));

					problemException = new Problem.ProblemException(problem);
				}

				throw problemException;
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return PageElementSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				postSiteSiteByExternalReferenceCodePageElementFragmentCompositionHttpResponse(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					String pageElementExternalReferenceCode, Integer position,
					FragmentComposition fragmentComposition)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				fragmentComposition.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			if (position != null) {
				httpInvoker.parameter("position", String.valueOf(position));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/headless-admin-site/v1.0/sites/{siteExternalReferenceCode}/page-specifications/{pageSpecificationExternalReferenceCode}/page-experiences/{pageExperienceExternalReferenceCode}/page-elements/{pageElementExternalReferenceCode}/fragment-compositions");

			httpInvoker.path(
				"siteExternalReferenceCode", siteExternalReferenceCode);
			httpInvoker.path(
				"pageSpecificationExternalReferenceCode",
				pageSpecificationExternalReferenceCode);
			httpInvoker.path(
				"pageExperienceExternalReferenceCode",
				pageExperienceExternalReferenceCode);
			httpInvoker.path(
				"pageElementExternalReferenceCode",
				pageElementExternalReferenceCode);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<PageElement>
				getSiteSiteByExternalReferenceCodePageElementPageElementsPage(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					String pageElementExternalReferenceCode, Boolean flatten)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getSiteSiteByExternalReferenceCodePageElementPageElementsPageHttpResponse(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode,
					pageExperienceExternalReferenceCode,
					pageElementExternalReferenceCode, flatten);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				Problem.ProblemException problemException = null;

				if (Objects.equals(
						httpResponse.getContentType(), "application/json")) {

					problemException = new Problem.ProblemException(
						Problem.toDTO(content));
				}
				else {
					_logger.log(
						Level.WARNING,
						"Unable to process content type: " +
							httpResponse.getContentType());

					Problem problem = new Problem();

					problem.setStatus(
						String.valueOf(httpResponse.getStatusCode()));

					problemException = new Problem.ProblemException(problem);
				}

				throw problemException;
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, PageElementSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getSiteSiteByExternalReferenceCodePageElementPageElementsPageHttpResponse(
					String siteExternalReferenceCode,
					String pageSpecificationExternalReferenceCode,
					String pageExperienceExternalReferenceCode,
					String pageElementExternalReferenceCode, Boolean flatten)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (flatten != null) {
				httpInvoker.parameter("flatten", String.valueOf(flatten));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/headless-admin-site/v1.0/sites/{siteExternalReferenceCode}/page-specifications/{pageSpecificationExternalReferenceCode}/page-experiences/{pageExperienceExternalReferenceCode}/page-elements/{pageElementExternalReferenceCode}/page-elements");

			httpInvoker.path(
				"siteExternalReferenceCode", siteExternalReferenceCode);
			httpInvoker.path(
				"pageSpecificationExternalReferenceCode",
				pageSpecificationExternalReferenceCode);
			httpInvoker.path(
				"pageExperienceExternalReferenceCode",
				pageExperienceExternalReferenceCode);
			httpInvoker.path(
				"pageElementExternalReferenceCode",
				pageElementExternalReferenceCode);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private PageElementResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			PageElementResource.class.getName());

		private Builder _builder;

	}

}