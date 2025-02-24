/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for CPMeasurementUnit. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPMeasurementUnitServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPMeasurementUnitService
 * @generated
 */
public class CPMeasurementUnitServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPMeasurementUnitServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CPMeasurementUnit addCPMeasurementUnit(
			String externalReferenceCode, Map<java.util.Locale, String> nameMap,
			String key, double rate, boolean primary, double priority, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCPMeasurementUnit(
			externalReferenceCode, nameMap, key, rate, primary, priority, type,
			serviceContext);
	}

	public static void deleteCPMeasurementUnit(long cpMeasurementUnitId)
		throws PortalException {

		getService().deleteCPMeasurementUnit(cpMeasurementUnitId);
	}

	public static CPMeasurementUnit fetchCPMeasurementUnit(
			long cpMeasurementUnitId)
		throws PortalException {

		return getService().fetchCPMeasurementUnit(cpMeasurementUnitId);
	}

	public static CPMeasurementUnit
			fetchCPMeasurementUnitByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws PortalException {

		return getService().fetchCPMeasurementUnitByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static CPMeasurementUnit fetchCPMeasurementUnitByKey(
			long companyId, String key)
		throws PortalException {

		return getService().fetchCPMeasurementUnitByKey(companyId, key);
	}

	public static CPMeasurementUnit fetchPrimaryCPMeasurementUnit(
			long companyId, int type)
		throws PortalException {

		return getService().fetchPrimaryCPMeasurementUnit(companyId, type);
	}

	public static CPMeasurementUnit fetchPrimaryCPMeasurementUnitByType(
			long companyId, int type)
		throws PortalException {

		return getService().fetchPrimaryCPMeasurementUnitByType(
			companyId, type);
	}

	public static CPMeasurementUnit getCPMeasurementUnit(
			long cpMeasurementUnitId)
		throws PortalException {

		return getService().getCPMeasurementUnit(cpMeasurementUnitId);
	}

	public static CPMeasurementUnit getCPMeasurementUnitByKey(
			long companyId, String key)
		throws PortalException {

		return getService().getCPMeasurementUnitByKey(companyId, key);
	}

	public static List<CPMeasurementUnit> getCPMeasurementUnits(long companyId)
		throws PortalException {

		return getService().getCPMeasurementUnits(companyId);
	}

	public static List<CPMeasurementUnit> getCPMeasurementUnits(
			long companyId, int type, int start, int end,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws PortalException {

		return getService().getCPMeasurementUnits(
			companyId, type, start, end, orderByComparator);
	}

	public static List<CPMeasurementUnit> getCPMeasurementUnits(
			long companyId, int start, int end,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws PortalException {

		return getService().getCPMeasurementUnits(
			companyId, start, end, orderByComparator);
	}

	public static List<CPMeasurementUnit> getCPMeasurementUnitsByType(
			long companyId, int type)
		throws PortalException {

		return getService().getCPMeasurementUnitsByType(companyId, type);
	}

	public static List<CPMeasurementUnit> getCPMeasurementUnitsByType(
			long companyId, int type, int start, int end,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws PortalException {

		return getService().getCPMeasurementUnitsByType(
			companyId, type, start, end, orderByComparator);
	}

	public static int getCPMeasurementUnitsCount(long companyId)
		throws PortalException {

		return getService().getCPMeasurementUnitsCount(companyId);
	}

	public static int getCPMeasurementUnitsCount(long companyId, int type)
		throws PortalException {

		return getService().getCPMeasurementUnitsCount(companyId, type);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CPMeasurementUnit setPrimary(
			long cpMeasurementUnitId, boolean primary)
		throws PortalException {

		return getService().setPrimary(cpMeasurementUnitId, primary);
	}

	public static CPMeasurementUnit updateCPMeasurementUnit(
			String externalReferenceCode, long cpMeasurementUnitId,
			Map<java.util.Locale, String> nameMap, String key, double rate,
			boolean primary, double priority, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCPMeasurementUnit(
			externalReferenceCode, cpMeasurementUnitId, nameMap, key, rate,
			primary, priority, type, serviceContext);
	}

	public static CPMeasurementUnitService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<CPMeasurementUnitService> _serviceSnapshot =
		new Snapshot<>(
			CPMeasurementUnitServiceUtil.class, CPMeasurementUnitService.class);

}