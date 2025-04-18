/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.service.access.policy.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for SAPEntry. This utility wraps
 * <code>com.liferay.portal.security.service.access.policy.service.impl.SAPEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SAPEntryService
 * @generated
 */
public class SAPEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.security.service.access.policy.service.impl.SAPEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static SAPEntry addSAPEntry(
			String allowedServiceSignatures, boolean defaultSAPEntry,
			boolean enabled, String name,
			Map<java.util.Locale, String> titleMap,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addSAPEntry(
			allowedServiceSignatures, defaultSAPEntry, enabled, name, titleMap,
			serviceContext);
	}

	public static SAPEntry deleteSAPEntry(long sapEntryId)
		throws PortalException {

		return getService().deleteSAPEntry(sapEntryId);
	}

	public static SAPEntry deleteSAPEntry(SAPEntry sapEntry)
		throws PortalException {

		return getService().deleteSAPEntry(sapEntry);
	}

	public static SAPEntry fetchSAPEntry(long companyId, String name)
		throws PortalException {

		return getService().fetchSAPEntry(companyId, name);
	}

	public static List<SAPEntry> getCompanySAPEntries(
		long companyId, int start, int end) {

		return getService().getCompanySAPEntries(companyId, start, end);
	}

	public static List<SAPEntry> getCompanySAPEntries(
		long companyId, int start, int end,
		OrderByComparator<SAPEntry> orderByComparator) {

		return getService().getCompanySAPEntries(
			companyId, start, end, orderByComparator);
	}

	public static int getCompanySAPEntriesCount(long companyId) {
		return getService().getCompanySAPEntriesCount(companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static SAPEntry getSAPEntry(long sapEntryId) throws PortalException {
		return getService().getSAPEntry(sapEntryId);
	}

	public static SAPEntry getSAPEntry(long companyId, String name)
		throws PortalException {

		return getService().getSAPEntry(companyId, name);
	}

	public static SAPEntry updateSAPEntry(
			long sapEntryId, String allowedServiceSignatures,
			boolean defaultSAPEntry, boolean enabled, String name,
			Map<java.util.Locale, String> titleMap,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateSAPEntry(
			sapEntryId, allowedServiceSignatures, defaultSAPEntry, enabled,
			name, titleMap, serviceContext);
	}

	public static SAPEntryService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<SAPEntryService> _serviceSnapshot =
		new Snapshot<>(SAPEntryServiceUtil.class, SAPEntryService.class);

}