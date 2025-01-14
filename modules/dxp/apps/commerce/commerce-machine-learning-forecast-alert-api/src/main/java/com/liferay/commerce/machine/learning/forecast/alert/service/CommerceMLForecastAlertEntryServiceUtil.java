/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.forecast.alert.service;

import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;

import java.util.List;

/**
 * Provides the remote service utility for CommerceMLForecastAlertEntry. This utility wraps
 * <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryService
 * @generated
 */
public class CommerceMLForecastAlertEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static List<CommerceMLForecastAlertEntry>
			getAboveThresholdCommerceMLForecastAlertEntries(
				long companyId, long userId, int status, double relativeChange,
				int start, int end)
		throws PortalException {

		return getService().getAboveThresholdCommerceMLForecastAlertEntries(
			companyId, userId, status, relativeChange, start, end);
	}

	public static int getAboveThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status, double relativeChange)
		throws PortalException {

		return getService().
			getAboveThresholdCommerceMLForecastAlertEntriesCount(
				companyId, userId, status, relativeChange);
	}

	public static List<CommerceMLForecastAlertEntry>
			getBelowThresholdCommerceMLForecastAlertEntries(
				long companyId, long userId, int status, double relativeChange,
				int start, int end)
		throws PortalException {

		return getService().getBelowThresholdCommerceMLForecastAlertEntries(
			companyId, userId, status, relativeChange, start, end);
	}

	public static int getBelowThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status, double relativeChange)
		throws PortalException {

		return getService().
			getBelowThresholdCommerceMLForecastAlertEntriesCount(
				companyId, userId, status, relativeChange);
	}

	public static List<CommerceMLForecastAlertEntry>
			getCommerceMLForecastAlertEntries(
				long companyId, long userId, int status, int start, int end)
		throws PortalException {

		return getService().getCommerceMLForecastAlertEntries(
			companyId, userId, status, start, end);
	}

	public static int getCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status)
		throws PortalException {

		return getService().getCommerceMLForecastAlertEntriesCount(
			companyId, userId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceMLForecastAlertEntry updateStatus(
			long commerceMLForecastAlertEntryId, int status)
		throws PortalException {

		return getService().updateStatus(
			commerceMLForecastAlertEntryId, status);
	}

	public static CommerceMLForecastAlertEntryService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<CommerceMLForecastAlertEntryService>
		_serviceSnapshot = new Snapshot<>(
			CommerceMLForecastAlertEntryServiceUtil.class,
			CommerceMLForecastAlertEntryService.class);

}