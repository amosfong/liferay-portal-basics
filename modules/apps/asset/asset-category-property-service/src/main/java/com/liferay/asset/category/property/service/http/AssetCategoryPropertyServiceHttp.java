/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.category.property.service.http;

import com.liferay.asset.category.property.service.AssetCategoryPropertyServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AssetCategoryPropertyServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetCategoryPropertyServiceHttp {

	public static
		com.liferay.asset.category.property.model.AssetCategoryProperty
				addCategoryProperty(
					HttpPrincipal httpPrincipal, long entryId, String key,
					String value)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryPropertyServiceUtil.class, "addCategoryProperty",
				_addCategoryPropertyParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, entryId, key, value);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.asset.category.property.model.
				AssetCategoryProperty)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCategoryProperty(
			HttpPrincipal httpPrincipal, long categoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryPropertyServiceUtil.class,
				"deleteCategoryProperty",
				_deleteCategoryPropertyParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryPropertyId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.asset.category.property.model.AssetCategoryProperty>
			getCategoryProperties(HttpPrincipal httpPrincipal, long entryId) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryPropertyServiceUtil.class, "getCategoryProperties",
				_getCategoryPropertiesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.asset.category.property.model.
					AssetCategoryProperty>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.asset.category.property.model.AssetCategoryProperty>
			getCategoryPropertyValues(
				HttpPrincipal httpPrincipal, long companyId, String key) {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryPropertyServiceUtil.class,
				"getCategoryPropertyValues",
				_getCategoryPropertyValuesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, key);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.asset.category.property.model.
					AssetCategoryProperty>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.asset.category.property.model.AssetCategoryProperty
				updateCategoryProperty(
					HttpPrincipal httpPrincipal, long userId,
					long categoryPropertyId, String key, String value)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryPropertyServiceUtil.class,
				"updateCategoryProperty",
				_updateCategoryPropertyParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, categoryPropertyId, key, value);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.asset.category.property.model.
				AssetCategoryProperty)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.asset.category.property.model.AssetCategoryProperty
				updateCategoryProperty(
					HttpPrincipal httpPrincipal, long categoryPropertyId,
					String key, String value)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AssetCategoryPropertyServiceUtil.class,
				"updateCategoryProperty",
				_updateCategoryPropertyParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryPropertyId, key, value);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.asset.category.property.model.
				AssetCategoryProperty)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AssetCategoryPropertyServiceHttp.class);

	private static final Class<?>[] _addCategoryPropertyParameterTypes0 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _deleteCategoryPropertyParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _getCategoryPropertiesParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _getCategoryPropertyValuesParameterTypes3 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _updateCategoryPropertyParameterTypes4 =
		new Class[] {long.class, long.class, String.class, String.class};
	private static final Class<?>[] _updateCategoryPropertyParameterTypes5 =
		new Class[] {long.class, String.class, String.class};

}