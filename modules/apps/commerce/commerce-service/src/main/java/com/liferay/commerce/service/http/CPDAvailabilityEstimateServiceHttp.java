/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.http;

import com.liferay.commerce.service.CPDAvailabilityEstimateServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CPDAvailabilityEstimateServiceUtil</code> service
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
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CPDAvailabilityEstimateServiceHttp {

	public static com.liferay.commerce.model.CPDAvailabilityEstimate
			fetchCPDAvailabilityEstimateByCPDefinitionId(
				HttpPrincipal httpPrincipal, long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPDAvailabilityEstimateServiceUtil.class,
				"fetchCPDAvailabilityEstimateByCPDefinitionId",
				_fetchCPDAvailabilityEstimateByCPDefinitionIdParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cpDefinitionId);

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

			return (com.liferay.commerce.model.CPDAvailabilityEstimate)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.commerce.model.CPDAvailabilityEstimate
			updateCPDAvailabilityEstimate(
				HttpPrincipal httpPrincipal, long cpdAvailabilityEstimateId,
				long cpDefinitionId, long commerceAvailabilityEstimateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CPDAvailabilityEstimateServiceUtil.class,
				"updateCPDAvailabilityEstimate",
				_updateCPDAvailabilityEstimateParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cpdAvailabilityEstimateId, cpDefinitionId,
				commerceAvailabilityEstimateId);

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

			return (com.liferay.commerce.model.CPDAvailabilityEstimate)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CPDAvailabilityEstimateServiceHttp.class);

	private static final Class<?>[]
		_fetchCPDAvailabilityEstimateByCPDefinitionIdParameterTypes0 =
			new Class[] {long.class};
	private static final Class<?>[]
		_updateCPDAvailabilityEstimateParameterTypes1 = new Class[] {
			long.class, long.class, long.class
		};

}