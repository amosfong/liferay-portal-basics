/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.http;

import com.liferay.commerce.product.service.CPSpecificationOptionListTypeDefinitionRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CPSpecificationOptionListTypeDefinitionRelServiceUtil</code> service
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
 * @author Marco Leo
 * @generated
 */
public class CPSpecificationOptionListTypeDefinitionRelServiceHttp {

	public static com.liferay.commerce.product.model.
		CPSpecificationOptionListTypeDefinitionRel
				addCPSpecificationOptionListTypeDefinitionRel(
					HttpPrincipal httpPrincipal, long cpSpecificationOptionId,
					long listTypeDefinitionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPSpecificationOptionListTypeDefinitionRelServiceUtil.class,
				"addCPSpecificationOptionListTypeDefinitionRel",
				_addCPSpecificationOptionListTypeDefinitionRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cpSpecificationOptionId, listTypeDefinitionId);

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

			return (com.liferay.commerce.product.model.
				CPSpecificationOptionListTypeDefinitionRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCPSpecificationOptionListTypeDefinitionRel(
			HttpPrincipal httpPrincipal, long cpSpecificationOptionId,
			long listTypeDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPSpecificationOptionListTypeDefinitionRelServiceUtil.class,
				"deleteCPSpecificationOptionListTypeDefinitionRel",
				_deleteCPSpecificationOptionListTypeDefinitionRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cpSpecificationOptionId, listTypeDefinitionId);

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

	private static Log _log = LogFactoryUtil.getLog(
		CPSpecificationOptionListTypeDefinitionRelServiceHttp.class);

	private static final Class<?>[]
		_addCPSpecificationOptionListTypeDefinitionRelParameterTypes0 =
			new Class[] {long.class, long.class};
	private static final Class<?>[]
		_deleteCPSpecificationOptionListTypeDefinitionRelParameterTypes1 =
			new Class[] {long.class, long.class};

}