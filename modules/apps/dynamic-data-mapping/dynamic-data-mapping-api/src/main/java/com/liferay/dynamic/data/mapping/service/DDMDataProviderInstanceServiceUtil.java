/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for DDMDataProviderInstance. This utility wraps
 * <code>com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DDMDataProviderInstanceService
 * @generated
 */
public class DDMDataProviderInstanceServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static DDMDataProviderInstance addDataProviderInstance(
			long groupId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				ddmFormValues,
			String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addDataProviderInstance(
			groupId, nameMap, descriptionMap, ddmFormValues, type,
			serviceContext);
	}

	public static void deleteDataProviderInstance(long dataProviderInstanceId)
		throws PortalException {

		getService().deleteDataProviderInstance(dataProviderInstanceId);
	}

	public static DDMDataProviderInstance fetchDataProviderInstance(
			long dataProviderInstanceId)
		throws PortalException {

		return getService().fetchDataProviderInstance(dataProviderInstanceId);
	}

	public static DDMDataProviderInstance fetchDataProviderInstanceByUuid(
			String uuid)
		throws PortalException {

		return getService().fetchDataProviderInstanceByUuid(uuid);
	}

	public static DDMDataProviderInstance getDataProviderInstance(
			long dataProviderInstanceId)
		throws PortalException {

		return getService().getDataProviderInstance(dataProviderInstanceId);
	}

	public static DDMDataProviderInstance getDataProviderInstanceByUuid(
			String uuid)
		throws PortalException {

		return getService().getDataProviderInstanceByUuid(uuid);
	}

	public static List<DDMDataProviderInstance> getDataProviderInstances(
		long companyId, long[] groupIds, int start, int end) {

		return getService().getDataProviderInstances(
			companyId, groupIds, start, end);
	}

	public static int getDataProviderInstancesCount(
		long companyId, long[] groupIds) {

		return getService().getDataProviderInstancesCount(companyId, groupIds);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<DDMDataProviderInstance> search(
		long companyId, long[] groupIds, String keywords, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator) {

		return getService().search(
			companyId, groupIds, keywords, start, end, orderByComparator);
	}

	public static List<DDMDataProviderInstance> search(
		long companyId, long[] groupIds, String name, String description,
		boolean andOperator, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator) {

		return getService().search(
			companyId, groupIds, name, description, andOperator, start, end,
			orderByComparator);
	}

	public static int searchCount(
		long companyId, long[] groupIds, String keywords) {

		return getService().searchCount(companyId, groupIds, keywords);
	}

	public static int searchCount(
		long companyId, long[] groupIds, String name, String description,
		boolean andOperator) {

		return getService().searchCount(
			companyId, groupIds, name, description, andOperator);
	}

	public static DDMDataProviderInstance updateDataProviderInstance(
			long dataProviderInstanceId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				ddmFormValues,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateDataProviderInstance(
			dataProviderInstanceId, nameMap, descriptionMap, ddmFormValues,
			serviceContext);
	}

	public static DDMDataProviderInstanceService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<DDMDataProviderInstanceService>
		_serviceSnapshot = new Snapshot<>(
			DDMDataProviderInstanceServiceUtil.class,
			DDMDataProviderInstanceService.class);

}