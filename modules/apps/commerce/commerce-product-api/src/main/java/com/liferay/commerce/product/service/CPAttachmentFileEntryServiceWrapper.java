/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPAttachmentFileEntryService}.
 *
 * @author Marco Leo
 * @see CPAttachmentFileEntryService
 * @generated
 */
public class CPAttachmentFileEntryServiceWrapper
	implements CPAttachmentFileEntryService,
			   ServiceWrapper<CPAttachmentFileEntryService> {

	public CPAttachmentFileEntryServiceWrapper() {
		this(null);
	}

	public CPAttachmentFileEntryServiceWrapper(
		CPAttachmentFileEntryService cpAttachmentFileEntryService) {

		_cpAttachmentFileEntryService = cpAttachmentFileEntryService;
	}

	@Override
	public CPAttachmentFileEntry addCPAttachmentFileEntry(
			long groupId, long classNameId, long classPK, long fileEntryId,
			boolean cdnEnabled, String cdnURL, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, boolean galleryEnabled,
			java.util.Map<java.util.Locale, String> titleMap, String json,
			double priority, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.addCPAttachmentFileEntry(
			groupId, classNameId, classPK, fileEntryId, cdnEnabled, cdnURL,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, galleryEnabled, titleMap, json, priority, type,
			serviceContext);
	}

	@Override
	public CPAttachmentFileEntry addOrUpdateCPAttachmentFileEntry(
			String externalReferenceCode, long groupId, long classNameId,
			long classPK, long cpAttachmentFileEntryId, long fileEntryId,
			boolean cdnEnabled, String cdnURL, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, boolean galleryEnabled,
			java.util.Map<java.util.Locale, String> titleMap, String json,
			double priority, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.addOrUpdateCPAttachmentFileEntry(
			externalReferenceCode, groupId, classNameId, classPK,
			cpAttachmentFileEntryId, fileEntryId, cdnEnabled, cdnURL,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, galleryEnabled, titleMap, json, priority, type,
			serviceContext);
	}

	@Override
	public void deleteCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpAttachmentFileEntryService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	@Override
	public CPAttachmentFileEntry fetchCPAttachmentFileEntry(
			long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.fetchCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	@Override
	public CPAttachmentFileEntry
			fetchCPAttachmentFileEntryByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.
			fetchCPAttachmentFileEntryByExternalReferenceCode(
				externalReferenceCode, companyId);
	}

	@Override
	public java.util.List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, int type, int status, int start,
			int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end);
	}

	@Override
	public java.util.List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, int type, int status, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CPAttachmentFileEntry> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end, orderByComparator);
	}

	@Override
	public java.util.List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, String keywords, int type,
			int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, classPK, keywords, type, status, start, end);
	}

	@Override
	public int getCPAttachmentFileEntriesCount(
			long classNameId, long classPK, int type, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
			classNameId, classPK, type, status);
	}

	@Override
	public int getCPAttachmentFileEntriesCount(
			long classNameId, long classPK, String keywords, int type,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
			classNameId, classPK, keywords, type, status);
	}

	@Override
	public CPAttachmentFileEntry getCPAttachmentFileEntry(
			long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpAttachmentFileEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public CPAttachmentFileEntry updateCPAttachmentFileEntry(
			long cpAttachmentFileEntryId, long fileEntryId, boolean cdnEnabled,
			String cdnURL, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			boolean galleryEnabled,
			java.util.Map<java.util.Locale, String> titleMap, String json,
			double priority, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.updateCPAttachmentFileEntry(
			cpAttachmentFileEntryId, fileEntryId, cdnEnabled, cdnURL,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, galleryEnabled, titleMap, json, priority, type,
			serviceContext);
	}

	@Override
	public CPAttachmentFileEntryService getWrappedService() {
		return _cpAttachmentFileEntryService;
	}

	@Override
	public void setWrappedService(
		CPAttachmentFileEntryService cpAttachmentFileEntryService) {

		_cpAttachmentFileEntryService = cpAttachmentFileEntryService;
	}

	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

}