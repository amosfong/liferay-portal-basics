/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.segments.service.SegmentsExperienceServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>SegmentsExperienceServiceUtil</code> service
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
 * @author Eduardo Garcia
 * @generated
 */
public class SegmentsExperienceServiceHttp {

	public static com.liferay.segments.model.SegmentsExperience
			addSegmentsExperience(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long groupId, long segmentsEntryId, long plid,
				java.util.Map<java.util.Locale, String> nameMap, boolean active,
				com.liferay.portal.kernel.util.UnicodeProperties
					typeSettingsUnicodeProperties,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "addSegmentsExperience",
				_addSegmentsExperienceParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, groupId, segmentsEntryId,
				plid, nameMap, active, typeSettingsUnicodeProperties,
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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			addSegmentsExperience(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long groupId, long segmentsEntryId,
				String segmentsExperienceKey, long plid,
				java.util.Map<java.util.Locale, String> nameMap, int priority,
				boolean active,
				com.liferay.portal.kernel.util.UnicodeProperties
					typeSettingsUnicodeProperties,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "addSegmentsExperience",
				_addSegmentsExperienceParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, groupId, segmentsEntryId,
				segmentsExperienceKey, plid, nameMap, priority, active,
				typeSettingsUnicodeProperties, serviceContext);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			appendSegmentsExperience(
				HttpPrincipal httpPrincipal, long groupId, long segmentsEntryId,
				long plid, java.util.Map<java.util.Locale, String> nameMap,
				boolean active,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "appendSegmentsExperience",
				_appendSegmentsExperienceParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, segmentsEntryId, plid, nameMap, active,
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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			appendSegmentsExperience(
				HttpPrincipal httpPrincipal, long groupId, long segmentsEntryId,
				long plid, java.util.Map<java.util.Locale, String> nameMap,
				boolean active,
				com.liferay.portal.kernel.util.UnicodeProperties
					typeSettingsUnicodeProperties,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "appendSegmentsExperience",
				_appendSegmentsExperienceParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, segmentsEntryId, plid, nameMap, active,
				typeSettingsUnicodeProperties, serviceContext);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			deleteSegmentsExperience(
				HttpPrincipal httpPrincipal, long segmentsExperienceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "deleteSegmentsExperience",
				_deleteSegmentsExperienceParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, segmentsExperienceId);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			deleteSegmentsExperience(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "deleteSegmentsExperience",
				_deleteSegmentsExperienceParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, groupId);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			fetchSegmentsExperience(
				HttpPrincipal httpPrincipal, long groupId,
				String segmentsExperienceKey, long plid)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "fetchSegmentsExperience",
				_fetchSegmentsExperienceParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, segmentsExperienceKey, plid);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			fetchSegmentsExperienceByExternalReferenceCode(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class,
				"fetchSegmentsExperienceByExternalReferenceCode",
				_fetchSegmentsExperienceByExternalReferenceCodeParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, groupId);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			getSegmentsExperience(
				HttpPrincipal httpPrincipal, long segmentsExperienceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "getSegmentsExperience",
				_getSegmentsExperienceParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, segmentsExperienceId);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			getSegmentsExperience(
				HttpPrincipal httpPrincipal, long groupId,
				String segmentsExperienceKey, long plid)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "getSegmentsExperience",
				_getSegmentsExperienceParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, segmentsExperienceKey, plid);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			getSegmentsExperienceByExternalReferenceCode(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class,
				"getSegmentsExperienceByExternalReferenceCode",
				_getSegmentsExperienceByExternalReferenceCodeParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, groupId);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.segments.model.SegmentsExperience>
			getSegmentsExperiences(
				HttpPrincipal httpPrincipal, long groupId, long plid,
				boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "getSegmentsExperiences",
				_getSegmentsExperiencesParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, plid, active);

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
				<com.liferay.segments.model.SegmentsExperience>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.segments.model.SegmentsExperience>
			getSegmentsExperiences(
				HttpPrincipal httpPrincipal, long groupId, long plid,
				boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.segments.model.SegmentsExperience>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "getSegmentsExperiences",
				_getSegmentsExperiencesParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, plid, active, start, end,
				orderByComparator);

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
				<com.liferay.segments.model.SegmentsExperience>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getSegmentsExperiencesCount(
			HttpPrincipal httpPrincipal, long groupId, long plid,
			boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class,
				"getSegmentsExperiencesCount",
				_getSegmentsExperiencesCountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, plid, active);

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

	public static com.liferay.segments.model.SegmentsExperience
			updateSegmentsExperience(
				HttpPrincipal httpPrincipal, long segmentsExperienceId,
				long segmentsEntryId,
				java.util.Map<java.util.Locale, String> nameMap, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "updateSegmentsExperience",
				_updateSegmentsExperienceParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, segmentsExperienceId, segmentsEntryId, nameMap,
				active);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			updateSegmentsExperience(
				HttpPrincipal httpPrincipal, long segmentsExperienceId,
				long segmentsEntryId,
				java.util.Map<java.util.Locale, String> nameMap, boolean active,
				com.liferay.portal.kernel.util.UnicodeProperties
					typeSettingsUnicodeProperties)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class, "updateSegmentsExperience",
				_updateSegmentsExperienceParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, segmentsExperienceId, segmentsEntryId, nameMap,
				active, typeSettingsUnicodeProperties);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.segments.model.SegmentsExperience
			updateSegmentsExperiencePriority(
				HttpPrincipal httpPrincipal, long segmentsExperienceId,
				int newPriority)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SegmentsExperienceServiceUtil.class,
				"updateSegmentsExperiencePriority",
				_updateSegmentsExperiencePriorityParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, segmentsExperienceId, newPriority);

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

			return (com.liferay.segments.model.SegmentsExperience)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SegmentsExperienceServiceHttp.class);

	private static final Class<?>[] _addSegmentsExperienceParameterTypes0 =
		new Class[] {
			String.class, long.class, long.class, long.class,
			java.util.Map.class, boolean.class,
			com.liferay.portal.kernel.util.UnicodeProperties.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addSegmentsExperienceParameterTypes1 =
		new Class[] {
			String.class, long.class, long.class, String.class, long.class,
			java.util.Map.class, int.class, boolean.class,
			com.liferay.portal.kernel.util.UnicodeProperties.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _appendSegmentsExperienceParameterTypes2 =
		new Class[] {
			long.class, long.class, long.class, java.util.Map.class,
			boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _appendSegmentsExperienceParameterTypes3 =
		new Class[] {
			long.class, long.class, long.class, java.util.Map.class,
			boolean.class,
			com.liferay.portal.kernel.util.UnicodeProperties.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteSegmentsExperienceParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteSegmentsExperienceParameterTypes5 =
		new Class[] {String.class, long.class};
	private static final Class<?>[] _fetchSegmentsExperienceParameterTypes6 =
		new Class[] {long.class, String.class, long.class};
	private static final Class<?>[]
		_fetchSegmentsExperienceByExternalReferenceCodeParameterTypes7 =
			new Class[] {String.class, long.class};
	private static final Class<?>[] _getSegmentsExperienceParameterTypes8 =
		new Class[] {long.class};
	private static final Class<?>[] _getSegmentsExperienceParameterTypes9 =
		new Class[] {long.class, String.class, long.class};
	private static final Class<?>[]
		_getSegmentsExperienceByExternalReferenceCodeParameterTypes10 =
			new Class[] {String.class, long.class};
	private static final Class<?>[] _getSegmentsExperiencesParameterTypes11 =
		new Class[] {long.class, long.class, boolean.class};
	private static final Class<?>[] _getSegmentsExperiencesParameterTypes12 =
		new Class[] {
			long.class, long.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getSegmentsExperiencesCountParameterTypes13 = new Class[] {
			long.class, long.class, boolean.class
		};
	private static final Class<?>[] _updateSegmentsExperienceParameterTypes14 =
		new Class[] {
			long.class, long.class, java.util.Map.class, boolean.class
		};
	private static final Class<?>[] _updateSegmentsExperienceParameterTypes15 =
		new Class[] {
			long.class, long.class, java.util.Map.class, boolean.class,
			com.liferay.portal.kernel.util.UnicodeProperties.class
		};
	private static final Class<?>[]
		_updateSegmentsExperiencePriorityParameterTypes16 = new Class[] {
			long.class, int.class
		};

}