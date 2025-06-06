/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.liferayrepository;

import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.repository.capabilities.WorkflowSupport;
import com.liferay.portal.repository.util.RepositoryWrapper;

import java.io.File;
import java.io.InputStream;

import java.util.Date;

/**
 * @author Adolfo Pérez
 */
public class LiferayWorkflowRepositoryWrapper extends RepositoryWrapper {

	public LiferayWorkflowRepositoryWrapper(
		Repository repository, WorkflowSupport workflowSupport) {

		super(repository);

		_workflowSupport = workflowSupport;
	}

	@Override
	public FileEntry addFileEntry(
			String externalReferenceCode, long userId, long folderId,
			String sourceFileName, String mimeType, String title,
			String urlTitle, String description, String changeLog, File file,
			Date displayDate, Date expirationDate, Date reviewDate,
			ServiceContext serviceContext)
		throws PortalException {

		FileEntry fileEntry = super.addFileEntry(
			externalReferenceCode, userId, folderId, sourceFileName, mimeType,
			title, urlTitle, description, changeLog, file, displayDate,
			expirationDate, reviewDate, serviceContext);

		_workflowSupport.addFileEntry(userId, fileEntry, serviceContext);

		return fileEntry;
	}

	@Override
	public FileEntry addFileEntry(
			String externalReferenceCode, long userId, long folderId,
			String sourceFileName, String mimeType, String title,
			String urlTitle, String description, String changeLog,
			InputStream inputStream, long size, Date displayDate,
			Date expirationDate, Date reviewDate, ServiceContext serviceContext)
		throws PortalException {

		FileEntry fileEntry = super.addFileEntry(
			externalReferenceCode, userId, folderId, sourceFileName, mimeType,
			title, urlTitle, description, changeLog, inputStream, size,
			displayDate, expirationDate, reviewDate, serviceContext);

		_workflowSupport.addFileEntry(userId, fileEntry, serviceContext);

		return fileEntry;
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId,
			DLVersionNumberIncrease dlVersionNumberIncrease, String changeLog,
			ServiceContext serviceContext)
		throws PortalException {

		super.checkInFileEntry(
			userId, fileEntryId, dlVersionNumberIncrease, changeLog,
			serviceContext);

		_workflowSupport.checkInFileEntry(
			userId, super.getFileEntry(fileEntryId), dlVersionNumberIncrease,
			serviceContext);
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId, String lockUuid,
			ServiceContext serviceContext)
		throws PortalException {

		super.checkInFileEntry(userId, fileEntryId, lockUuid, serviceContext);

		_workflowSupport.checkInFileEntry(
			userId, super.getFileEntry(fileEntryId),
			DLVersionNumberIncrease.MINOR, serviceContext);
	}

	@Override
	public FileEntry copyFileEntry(
			long userId, long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		FileEntry fileEntry = super.copyFileEntry(
			userId, groupId, fileEntryId, destFolderId, serviceContext);

		_workflowSupport.addFileEntry(userId, fileEntry, serviceContext);

		return fileEntry;
	}

	@Override
	public void revertFileEntry(
			long userId, long fileEntryId, String version,
			ServiceContext serviceContext)
		throws PortalException {

		super.revertFileEntry(userId, fileEntryId, version, serviceContext);

		_workflowSupport.revertFileEntry(
			userId, super.getFileEntry(fileEntryId), serviceContext);
	}

	@Override
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String urlTitle, String description,
			String changeLog, DLVersionNumberIncrease dlVersionNumberIncrease,
			File file, Date displayDate, Date expirationDate, Date reviewDate,
			ServiceContext serviceContext)
		throws PortalException {

		FileEntry fileEntry = super.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, urlTitle,
			description, changeLog, dlVersionNumberIncrease, file, displayDate,
			expirationDate, reviewDate, serviceContext);

		_workflowSupport.updateFileEntry(
			userId, fileEntry, dlVersionNumberIncrease, serviceContext);

		return super.getFileEntry(fileEntryId);
	}

	@Override
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String urlTitle, String description,
			String changeLog, DLVersionNumberIncrease dlVersionNumberIncrease,
			InputStream inputStream, long size, Date displayDate,
			Date expirationDate, Date reviewDate, ServiceContext serviceContext)
		throws PortalException {

		FileEntry fileEntry = super.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, urlTitle,
			description, changeLog, dlVersionNumberIncrease, inputStream, size,
			displayDate, expirationDate, reviewDate, serviceContext);

		_workflowSupport.updateFileEntry(
			userId, fileEntry, dlVersionNumberIncrease, serviceContext);

		return super.getFileEntry(fileEntryId);
	}

	private final WorkflowSupport _workflowSupport;

}