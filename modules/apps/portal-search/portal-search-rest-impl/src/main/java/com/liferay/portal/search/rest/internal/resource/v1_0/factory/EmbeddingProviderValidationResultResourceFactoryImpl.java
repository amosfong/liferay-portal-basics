/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.rest.internal.resource.v1_0.factory;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.odata.filter.ExpressionConvert;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.portal.odata.sort.SortParserProvider;
import com.liferay.portal.search.rest.internal.security.permission.LiberalPermissionChecker;
import com.liferay.portal.search.rest.resource.v1_0.EmbeddingProviderValidationResultResource;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Component(
	property = "resource.locator.key=/search/v1.0/EmbeddingProviderValidationResult",
	service = EmbeddingProviderValidationResultResource.Factory.class
)
@Generated("")
public class EmbeddingProviderValidationResultResourceFactoryImpl
	implements EmbeddingProviderValidationResultResource.Factory {

	@Override
	public EmbeddingProviderValidationResultResource.Builder create() {
		return new EmbeddingProviderValidationResultResource.Builder() {

			@Override
			public EmbeddingProviderValidationResultResource build() {
				if (_user == null) {
					throw new IllegalArgumentException("User is not set");
				}

				Function
					<InvocationHandler,
					 EmbeddingProviderValidationResultResource>
						embeddingProviderValidationResultResourceProxyProviderFunction =
							ResourceProxyProviderFunctionHolder.
								_embeddingProviderValidationResultResourceProxyProviderFunction;

				return embeddingProviderValidationResultResourceProxyProviderFunction.
					apply(
						(proxy, method, arguments) -> _invoke(
							method, arguments, _checkPermissions,
							_httpServletRequest, _httpServletResponse,
							_preferredLocale, _uriInfo, _user));
			}

			@Override
			public EmbeddingProviderValidationResultResource.Builder
				checkPermissions(boolean checkPermissions) {

				_checkPermissions = checkPermissions;

				return this;
			}

			@Override
			public EmbeddingProviderValidationResultResource.Builder
				httpServletRequest(HttpServletRequest httpServletRequest) {

				_httpServletRequest = httpServletRequest;

				return this;
			}

			@Override
			public EmbeddingProviderValidationResultResource.Builder
				httpServletResponse(HttpServletResponse httpServletResponse) {

				_httpServletResponse = httpServletResponse;

				return this;
			}

			@Override
			public EmbeddingProviderValidationResultResource.Builder
				preferredLocale(Locale preferredLocale) {

				_preferredLocale = preferredLocale;

				return this;
			}

			@Override
			public EmbeddingProviderValidationResultResource.Builder uriInfo(
				UriInfo uriInfo) {

				_uriInfo = uriInfo;

				return this;
			}

			@Override
			public EmbeddingProviderValidationResultResource.Builder user(
				User user) {

				_user = user;

				return this;
			}

			private boolean _checkPermissions = true;
			private HttpServletRequest _httpServletRequest;
			private HttpServletResponse _httpServletResponse;
			private Locale _preferredLocale;
			private UriInfo _uriInfo;
			private User _user;

		};
	}

	private static Function
		<InvocationHandler, EmbeddingProviderValidationResultResource>
			_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			EmbeddingProviderValidationResultResource.class.getClassLoader(),
			EmbeddingProviderValidationResultResource.class);

		try {
			Constructor<EmbeddingProviderValidationResultResource> constructor =
				(Constructor<EmbeddingProviderValidationResultResource>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private Object _invoke(
			Method method, Object[] arguments, boolean checkPermissions,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Locale preferredLocale,
			UriInfo uriInfo, User user)
		throws Throwable {

		String name = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(user.getUserId());

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (checkPermissions) {
			PermissionThreadLocal.setPermissionChecker(
				_defaultPermissionCheckerFactory.create(user));
		}
		else {
			PermissionThreadLocal.setPermissionChecker(
				new LiberalPermissionChecker(user));
		}

		EmbeddingProviderValidationResultResource
			embeddingProviderValidationResultResource =
				_componentServiceObjects.getService();

		embeddingProviderValidationResultResource.setContextAcceptLanguage(
			new AcceptLanguageImpl(httpServletRequest, preferredLocale, user));

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		embeddingProviderValidationResultResource.setContextCompany(company);

		embeddingProviderValidationResultResource.setContextHttpServletRequest(
			httpServletRequest);
		embeddingProviderValidationResultResource.setContextHttpServletResponse(
			httpServletResponse);
		embeddingProviderValidationResultResource.setContextUriInfo(uriInfo);
		embeddingProviderValidationResultResource.setContextUser(user);
		embeddingProviderValidationResultResource.setExpressionConvert(
			_expressionConvert);
		embeddingProviderValidationResultResource.setFilterParserProvider(
			_filterParserProvider);
		embeddingProviderValidationResultResource.setGroupLocalService(
			_groupLocalService);
		embeddingProviderValidationResultResource.setResourceActionLocalService(
			_resourceActionLocalService);
		embeddingProviderValidationResultResource.
			setResourcePermissionLocalService(_resourcePermissionLocalService);
		embeddingProviderValidationResultResource.setRoleLocalService(
			_roleLocalService);
		embeddingProviderValidationResultResource.setSortParserProvider(
			_sortParserProvider);

		try {
			return method.invoke(
				embeddingProviderValidationResultResource, arguments);
		}
		catch (InvocationTargetException invocationTargetException) {
			throw invocationTargetException.getTargetException();
		}
		finally {
			_componentServiceObjects.ungetService(
				embeddingProviderValidationResultResource);

			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<EmbeddingProviderValidationResultResource>
		_componentServiceObjects;

	@Reference
	private PermissionCheckerFactory _defaultPermissionCheckerFactory;

	@Reference(
		target = "(result.class.name=com.liferay.portal.kernel.search.filter.Filter)"
	)
	private ExpressionConvert<Filter> _expressionConvert;

	@Reference
	private FilterParserProvider _filterParserProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private SortParserProvider _sortParserProvider;

	@Reference
	private UserLocalService _userLocalService;

	private static class ResourceProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, EmbeddingProviderValidationResultResource>
				_embeddingProviderValidationResultResourceProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private class AcceptLanguageImpl implements AcceptLanguage {

		public AcceptLanguageImpl(
			HttpServletRequest httpServletRequest, Locale preferredLocale,
			User user) {

			_httpServletRequest = httpServletRequest;
			_preferredLocale = preferredLocale;
			_user = user;
		}

		@Override
		public List<Locale> getLocales() {
			return Arrays.asList(getPreferredLocale());
		}

		@Override
		public String getPreferredLanguageId() {
			return LocaleUtil.toLanguageId(getPreferredLocale());
		}

		@Override
		public Locale getPreferredLocale() {
			if (_preferredLocale != null) {
				return _preferredLocale;
			}

			if (_httpServletRequest != null) {
				Locale locale = (Locale)_httpServletRequest.getAttribute(
					WebKeys.LOCALE);

				if (locale != null) {
					return locale;
				}
			}

			return _user.getLocale();
		}

		@Override
		public boolean isAcceptAllLanguages() {
			return false;
		}

		private final HttpServletRequest _httpServletRequest;
		private final Locale _preferredLocale;
		private final User _user;

	}

}