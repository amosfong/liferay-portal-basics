/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.liferayrepository;

import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.capabilities.ProcessorCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.repository.util.LocalRepositoryWrapper;

import java.io.File;
import java.io.InputStream;

import java.util.Date;

/**
 * @author Adolfo Pérez
 */
public class LiferayProcessorLocalRepositoryWrapper
	extends LocalRepositoryWrapper {

	public LiferayProcessorLocalRepositoryWrapper(
		LocalRepository localRepository,
		ProcessorCapability processorCapability) {

		super(localRepository);

		_processorCapability = processorCapability;
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

		_processorCapability.generateNew(fileEntry);

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

		_processorCapability.generateNew(fileEntry);

		return fileEntry;
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId,
			DLVersionNumberIncrease dlVersionNumberIncrease, String changeLog,
			ServiceContext serviceContext)
		throws PortalException {

		FileEntry fileEntry = getFileEntry(fileEntryId);

		_processorCapability.cleanUp(fileEntry.getLatestFileVersion(true));

		super.checkInFileEntry(
			userId, fileEntryId, dlVersionNumberIncrease, changeLog,
			serviceContext);

		_processorCapability.copy(fileEntry, fileEntry.getFileVersion());
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId, String lockUuid,
			ServiceContext serviceContext)
		throws PortalException {

		FileEntry fileEntry = getFileEntry(fileEntryId);

		_processorCapability.cleanUp(fileEntry.getLatestFileVersion(true));

		super.checkInFileEntry(userId, fileEntryId, lockUuid, serviceContext);

		_processorCapability.copy(fileEntry, fileEntry.getFileVersion());
	}

	@Override
	public void revertFileEntry(
			long userId, long fileEntryId, String version,
			ServiceContext serviceContext)
		throws PortalException {

		super.revertFileEntry(userId, fileEntryId, version, serviceContext);

		FileEntry fileEntry = getFileEntry(fileEntryId);

		_processorCapability.copy(fileEntry, fileEntry.getFileVersion(version));
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

		_processorCapability.cleanUp(fileEntry.getLatestFileVersion(true));
		_processorCapability.generateNew(fileEntry);

		return fileEntry;
	}

	@Override
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String urlTitle, String description,
			String changeLog, DLVersionNumberIncrease dlVersionNumberIncrease,
			InputStream inputStream, long size, Date displayDate,
			Date expirationDate, Date reviewDate, ServiceContext serviceContext)
		throws PortalException {

		FileVersion oldFileVersion = null;

		if (inputStream == null) {
			FileEntry oldFileEntry = getFileEntry(fileEntryId);

			oldFileVersion = oldFileEntry.getLatestFileVersion(true);
		}

		FileEntry fileEntry = super.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, urlTitle,
			description, changeLog, dlVersionNumberIncrease, inputStream, size,
			displayDate, expirationDate, reviewDate, serviceContext);

		if (inputStream == null) {
			_processorCapability.copy(fileEntry, oldFileVersion);
		}
		else {
			_processorCapability.cleanUp(fileEntry.getLatestFileVersion(true));
			_processorCapability.generateNew(fileEntry);
		}

		return fileEntry;
	}

	private final ProcessorCapability _processorCapability;

}