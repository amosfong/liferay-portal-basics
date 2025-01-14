/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.language.override.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.language.override.service.PLOEntryServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>PLOEntryServiceUtil</code> service
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
 * @author Drew Brokke
 * @generated
 */
public class PLOEntryServiceHttp {

	public static com.liferay.portal.language.override.model.PLOEntry
			addOrUpdatePLOEntry(
				HttpPrincipal httpPrincipal, String key, String languageId,
				String value)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PLOEntryServiceUtil.class, "addOrUpdatePLOEntry",
				_addOrUpdatePLOEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, key, languageId, value);

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

			return (com.liferay.portal.language.override.model.PLOEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deletePLOEntries(HttpPrincipal httpPrincipal, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PLOEntryServiceUtil.class, "deletePLOEntries",
				_deletePLOEntriesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, key);

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

	public static com.liferay.portal.language.override.model.PLOEntry
			deletePLOEntry(
				HttpPrincipal httpPrincipal, String key, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PLOEntryServiceUtil.class, "deletePLOEntry",
				_deletePLOEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, key, languageId);

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

			return (com.liferay.portal.language.override.model.PLOEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.portal.language.override.model.PLOEntry> getPLOEntries(
				HttpPrincipal httpPrincipal, long companyId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PLOEntryServiceUtil.class, "getPLOEntries",
				_getPLOEntriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId);

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

			return (java.util.List
				<com.liferay.portal.language.override.model.PLOEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getPLOEntriesCount(
			HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PLOEntryServiceUtil.class, "getPLOEntriesCount",
				_getPLOEntriesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void importPLOEntries(
			HttpPrincipal httpPrincipal, String languageId,
			java.util.Properties properties)
		throws com.liferay.portal.kernel.exception.PortalException,
			   java.io.IOException {

		try {
			MethodKey methodKey = new MethodKey(
				PLOEntryServiceUtil.class, "importPLOEntries",
				_importPLOEntriesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, languageId, properties);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof java.io.IOException) {
					throw (java.io.IOException)exception;
				}

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

	public static void setPLOEntries(
			HttpPrincipal httpPrincipal, String key,
			java.util.Map<java.util.Locale, String> localizationMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PLOEntryServiceUtil.class, "setPLOEntries",
				_setPLOEntriesParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, key, localizationMap);

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

	private static Log _log = LogFactoryUtil.getLog(PLOEntryServiceHttp.class);

	private static final Class<?>[] _addOrUpdatePLOEntryParameterTypes0 =
		new Class[] {String.class, String.class, String.class};
	private static final Class<?>[] _deletePLOEntriesParameterTypes1 =
		new Class[] {String.class};
	private static final Class<?>[] _deletePLOEntryParameterTypes2 =
		new Class[] {String.class, String.class};
	private static final Class<?>[] _getPLOEntriesParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getPLOEntriesCountParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _importPLOEntriesParameterTypes5 =
		new Class[] {String.class, java.util.Properties.class};
	private static final Class<?>[] _setPLOEntriesParameterTypes6 =
		new Class[] {String.class, java.util.Map.class};

}