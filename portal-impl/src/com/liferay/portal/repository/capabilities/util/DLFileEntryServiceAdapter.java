/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.capabilities.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryService;
import com.liferay.document.library.kernel.service.DLFileEntryServiceUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.DocumentRepository;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Iván Zaera
 */
public class DLFileEntryServiceAdapter {

	public static DLFileEntryServiceAdapter create(
		DocumentRepository documentRepository) {

		if (documentRepository instanceof LocalRepository) {
			return new DLFileEntryServiceAdapter(
				DLFileEntryLocalServiceUtil.getService());
		}

		return new DLFileEntryServiceAdapter(
			DLFileEntryLocalServiceUtil.getService(),
			DLFileEntryServiceUtil.getService());
	}

	public DLFileEntryServiceAdapter(
		DLFileEntryLocalService dlFileEntryLocalService) {

		this(dlFileEntryLocalService, null);
	}

	public DLFileEntryServiceAdapter(
		DLFileEntryLocalService dlFileEntryLocalService,
		DLFileEntryService dlFileEntryService) {

		_dlFileEntryLocalService = dlFileEntryLocalService;
		_dlFileEntryService = dlFileEntryService;
	}

	public DLFileEntry fetchDLFileEntryByImageId(long imageId)
		throws PortalException {

		DLFileEntry dlFileEntry = null;

		if (_dlFileEntryService != null) {
			dlFileEntry = _dlFileEntryService.fetchFileEntryByImageId(imageId);
		}
		else {
			dlFileEntry = _dlFileEntryLocalService.fetchFileEntryByAnyImageId(
				imageId);
		}

		return dlFileEntry;
	}

	public ActionableDynamicQuery getActionableDynamicQuery()
		throws PortalException {

		if (_dlFileEntryService != null) {
			throw new PrincipalException("DL file entry service is not null");
		}

		return _dlFileEntryLocalService.getActionableDynamicQuery();
	}

	public DLFileEntry getDLFileEntry(long fileEntryId) throws PortalException {
		DLFileEntry dlFileEntry = null;

		if (_dlFileEntryService != null) {
			dlFileEntry = _dlFileEntryService.getFileEntry(fileEntryId);
		}
		else {
			dlFileEntry = _dlFileEntryLocalService.getFileEntry(fileEntryId);
		}

		return dlFileEntry;
	}

	public List<DLFileEntry> getGroupFileEntries(
			long groupId, int userId, long repositoryId, long folderId,
			int start, int end,
			OrderByComparator<DLFileEntry> orderByComparator)
		throws PortalException {

		List<DLFileEntry> dlFileEntries = null;

		if (_dlFileEntryService != null) {
			dlFileEntries = _dlFileEntryService.getGroupFileEntries(
				groupId, userId, repositoryId, folderId, null,
				WorkflowConstants.STATUS_ANY, start, end, orderByComparator);
		}
		else {
			dlFileEntries = _dlFileEntryLocalService.getGroupFileEntries(
				groupId, userId, repositoryId, folderId, start, end,
				orderByComparator);
		}

		return dlFileEntries;
	}

	public DLFileEntry updateStatus(
			long userId, long fileVersionId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		DLFileEntry dlFileEntry = null;

		if (_dlFileEntryService != null) {
			dlFileEntry = _dlFileEntryService.updateStatus(
				userId, fileVersionId, status, serviceContext, workflowContext);
		}
		else {
			dlFileEntry = _dlFileEntryLocalService.updateStatus(
				userId, fileVersionId, status, serviceContext, workflowContext);
		}

		return dlFileEntry;
	}

	private final DLFileEntryLocalService _dlFileEntryLocalService;
	private final DLFileEntryService _dlFileEntryService;

}