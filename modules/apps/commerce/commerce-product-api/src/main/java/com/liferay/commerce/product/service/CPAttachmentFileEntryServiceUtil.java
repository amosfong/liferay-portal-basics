/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for CPAttachmentFileEntry. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPAttachmentFileEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPAttachmentFileEntryService
 * @generated
 */
public class CPAttachmentFileEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPAttachmentFileEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CPAttachmentFileEntry addCPAttachmentFileEntry(
			long groupId, long classNameId, long classPK, long fileEntryId,
			boolean cdnEnabled, String cdnURL, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, boolean galleryEnabled,
			Map<java.util.Locale, String> titleMap, String json,
			double priority, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCPAttachmentFileEntry(
			groupId, classNameId, classPK, fileEntryId, cdnEnabled, cdnURL,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, galleryEnabled, titleMap, json, priority, type,
			serviceContext);
	}

	public static CPAttachmentFileEntry addOrUpdateCPAttachmentFileEntry(
			String externalReferenceCode, long groupId, long classNameId,
			long classPK, long cpAttachmentFileEntryId, long fileEntryId,
			boolean cdnEnabled, String cdnURL, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, boolean galleryEnabled,
			Map<java.util.Locale, String> titleMap, String json,
			double priority, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addOrUpdateCPAttachmentFileEntry(
			externalReferenceCode, groupId, classNameId, classPK,
			cpAttachmentFileEntryId, fileEntryId, cdnEnabled, cdnURL,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, galleryEnabled, titleMap, json, priority, type,
			serviceContext);
	}

	public static void deleteCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws PortalException {

		getService().deleteCPAttachmentFileEntry(cpAttachmentFileEntryId);
	}

	public static CPAttachmentFileEntry fetchCPAttachmentFileEntry(
			long cpAttachmentFileEntryId)
		throws PortalException {

		return getService().fetchCPAttachmentFileEntry(cpAttachmentFileEntryId);
	}

	public static CPAttachmentFileEntry
			fetchCPAttachmentFileEntryByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws PortalException {

		return getService().fetchCPAttachmentFileEntryByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	public static List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, int type, int status, int start,
			int end)
		throws PortalException {

		return getService().getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end);
	}

	public static List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, int type, int status, int start,
			int end, OrderByComparator<CPAttachmentFileEntry> orderByComparator)
		throws PortalException {

		return getService().getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end, orderByComparator);
	}

	public static List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, String keywords, int type,
			int status, int start, int end)
		throws PortalException {

		return getService().getCPAttachmentFileEntries(
			classNameId, classPK, keywords, type, status, start, end);
	}

	public static int getCPAttachmentFileEntriesCount(
			long classNameId, long classPK, int type, int status)
		throws PortalException {

		return getService().getCPAttachmentFileEntriesCount(
			classNameId, classPK, type, status);
	}

	public static int getCPAttachmentFileEntriesCount(
			long classNameId, long classPK, String keywords, int type,
			int status)
		throws PortalException {

		return getService().getCPAttachmentFileEntriesCount(
			classNameId, classPK, keywords, type, status);
	}

	public static CPAttachmentFileEntry getCPAttachmentFileEntry(
			long cpAttachmentFileEntryId)
		throws PortalException {

		return getService().getCPAttachmentFileEntry(cpAttachmentFileEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CPAttachmentFileEntry updateCPAttachmentFileEntry(
			long cpAttachmentFileEntryId, long fileEntryId, boolean cdnEnabled,
			String cdnURL, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			boolean galleryEnabled, Map<java.util.Locale, String> titleMap,
			String json, double priority, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCPAttachmentFileEntry(
			cpAttachmentFileEntryId, fileEntryId, cdnEnabled, cdnURL,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, galleryEnabled, titleMap, json, priority, type,
			serviceContext);
	}

	public static CPAttachmentFileEntryService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<CPAttachmentFileEntryService>
		_serviceSnapshot = new Snapshot<>(
			CPAttachmentFileEntryServiceUtil.class,
			CPAttachmentFileEntryService.class);

}