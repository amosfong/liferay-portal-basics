/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.http;

import com.liferay.commerce.product.service.CommerceChannelRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceChannelRelServiceUtil</code> service
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
public class CommerceChannelRelServiceHttp {

	public static com.liferay.commerce.product.model.CommerceChannelRel
			addCommerceChannelRel(
				HttpPrincipal httpPrincipal, String className, long classPK,
				long commerceChannelId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "addCommerceChannelRel",
				_addCommerceChannelRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK, commerceChannelId,
				serviceContext);

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

			return (com.liferay.commerce.product.model.CommerceChannelRel)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceChannelRel>
				addCommerceChannelRels(
					HttpPrincipal httpPrincipal, String className,
					long[] classPKs, long commerceChannelId,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "addCommerceChannelRels",
				_addCommerceChannelRelsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPKs, commerceChannelId,
				serviceContext);

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
				<com.liferay.commerce.product.model.CommerceChannelRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCommerceChannelRel(
			HttpPrincipal httpPrincipal, long commerceChannelRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "deleteCommerceChannelRel",
				_deleteCommerceChannelRelParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceChannelRelId);

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

	public static void deleteCommerceChannelRels(
			HttpPrincipal httpPrincipal, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"deleteCommerceChannelRels",
				_deleteCommerceChannelRelsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK);

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

	public static com.liferay.commerce.product.model.CommerceChannelRel
			fetchCommerceChannelRel(
				HttpPrincipal httpPrincipal, String className, long classPK,
				long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "fetchCommerceChannelRel",
				_fetchCommerceChannelRelParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK, commerceChannelId);

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

			return (com.liferay.commerce.product.model.CommerceChannelRel)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceChannelRel>
				getCommerceChannelCountries(
					HttpPrincipal httpPrincipal, long commerceChannelId,
					String name, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"getCommerceChannelCountries",
				_getCommerceChannelCountriesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceChannelId, name, start, end);

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
				<com.liferay.commerce.product.model.CommerceChannelRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCommerceChannelCountriesCount(
			HttpPrincipal httpPrincipal, long commerceChannelId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"getCommerceChannelCountriesCount",
				_getCommerceChannelCountriesCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceChannelId, name);

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

	public static com.liferay.commerce.product.model.CommerceChannelRel
			getCommerceChannelRel(
				HttpPrincipal httpPrincipal, long commerceChannelRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "getCommerceChannelRel",
				_getCommerceChannelRelParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceChannelRelId);

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

			return (com.liferay.commerce.product.model.CommerceChannelRel)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceChannelRel>
				getCommerceChannelRels(
					HttpPrincipal httpPrincipal, long commerceChannelId,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.product.model.CommerceChannelRel>
							orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "getCommerceChannelRels",
				_getCommerceChannelRelsParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceChannelId, start, end, orderByComparator);

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
				<com.liferay.commerce.product.model.CommerceChannelRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceChannelRel>
				getCommerceChannelRels(
					HttpPrincipal httpPrincipal, String className, long classPK,
					String name, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class, "getCommerceChannelRels",
				_getCommerceChannelRelsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK, name, start, end);

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
				<com.liferay.commerce.product.model.CommerceChannelRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCommerceChannelRelsCount(
			HttpPrincipal httpPrincipal, long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"getCommerceChannelRelsCount",
				_getCommerceChannelRelsCountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceChannelId);

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

	public static int getCommerceChannelRelsCount(
			HttpPrincipal httpPrincipal, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"getCommerceChannelRelsCount",
				_getCommerceChannelRelsCountParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK);

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

	public static int getCommerceChannelRelsCount(
			HttpPrincipal httpPrincipal, String className, long classPK,
			String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceChannelRelServiceUtil.class,
				"getCommerceChannelRelsCount",
				_getCommerceChannelRelsCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK, name);

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

	private static Log _log = LogFactoryUtil.getLog(
		CommerceChannelRelServiceHttp.class);

	private static final Class<?>[] _addCommerceChannelRelParameterTypes0 =
		new Class[] {
			String.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addCommerceChannelRelsParameterTypes1 =
		new Class[] {
			String.class, long[].class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceChannelRelParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteCommerceChannelRelsParameterTypes3 =
		new Class[] {String.class, long.class};
	private static final Class<?>[] _fetchCommerceChannelRelParameterTypes4 =
		new Class[] {String.class, long.class, long.class};
	private static final Class<?>[]
		_getCommerceChannelCountriesParameterTypes5 = new Class[] {
			long.class, String.class, int.class, int.class
		};
	private static final Class<?>[]
		_getCommerceChannelCountriesCountParameterTypes6 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getCommerceChannelRelParameterTypes7 =
		new Class[] {long.class};
	private static final Class<?>[] _getCommerceChannelRelsParameterTypes8 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceChannelRelsParameterTypes9 =
		new Class[] {
			String.class, long.class, String.class, int.class, int.class
		};
	private static final Class<?>[]
		_getCommerceChannelRelsCountParameterTypes10 = new Class[] {long.class};
	private static final Class<?>[]
		_getCommerceChannelRelsCountParameterTypes11 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[]
		_getCommerceChannelRelsCountParameterTypes12 = new Class[] {
			String.class, long.class, String.class
		};

}