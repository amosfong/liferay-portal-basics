/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.expando.kernel.service;

import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.Serializable;

import java.util.Map;

/**
 * Provides the remote service utility for ExpandoValue. This utility wraps
 * <code>com.liferay.portlet.expando.service.impl.ExpandoValueServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoValueService
 * @generated
 */
public class ExpandoValueServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.expando.service.impl.ExpandoValueServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, Object data)
		throws PortalException {

		return getService().addValue(
			companyId, className, tableName, columnName, classPK, data);
	}

	public static ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, String data)
		throws PortalException {

		return getService().addValue(
			companyId, className, tableName, columnName, classPK, data);
	}

	public static void addValues(
			long companyId, String className, String tableName, long classPK,
			Map<String, Serializable> attributeValues)
		throws PortalException {

		getService().addValues(
			companyId, className, tableName, classPK, attributeValues);
	}

	public static Map<String, Serializable> getData(
			long companyId, String className, String tableName,
			java.util.Collection<String> columnNames, long classPK)
		throws PortalException {

		return getService().getData(
			companyId, className, tableName, columnNames, classPK);
	}

	public static Serializable getData(
			long companyId, String className, String tableName,
			String columnName, long classPK)
		throws PortalException {

		return getService().getData(
			companyId, className, tableName, columnName, classPK);
	}

	public static com.liferay.portal.kernel.json.JSONObject getJSONData(
			long companyId, String className, String tableName,
			String columnName, long classPK)
		throws PortalException {

		return getService().getJSONData(
			companyId, className, tableName, columnName, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ExpandoValueService getService() {
		return _service;
	}

	public static void setService(ExpandoValueService service) {
		_service = service;
	}

	private static volatile ExpandoValueService _service;

}