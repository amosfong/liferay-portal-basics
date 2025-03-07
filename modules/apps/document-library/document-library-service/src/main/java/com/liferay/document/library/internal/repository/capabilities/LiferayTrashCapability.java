/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.repository.capabilities;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppHelperLocalService;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.capabilities.TrashCapability;
import com.liferay.portal.kernel.repository.event.RepositoryEventAware;
import com.liferay.portal.kernel.repository.event.RepositoryEventListener;
import com.liferay.portal.kernel.repository.event.RepositoryEventType;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.registry.RepositoryEventRegistry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.repository.capabilities.util.DLAppServiceAdapter;
import com.liferay.portal.repository.capabilities.util.DLFileEntryServiceAdapter;
import com.liferay.portal.repository.capabilities.util.DLFolderServiceAdapter;
import com.liferay.portal.repository.capabilities.util.RepositoryServiceAdapter;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;

import java.util.List;

/**
 * @author Adolfo Pérez
 */
public class LiferayTrashCapability
	implements RepositoryEventAware, TrashCapability {

	public LiferayTrashCapability(
		DLAppHelperLocalService dlAppHelperLocalService,
		DLAppServiceAdapter dlAppServiceAdapter,
		DLFileEntryServiceAdapter dlFileEntryServiceAdapter,
		DLFolderServiceAdapter dlFolderServiceAdapter,
		RepositoryServiceAdapter repositoryServiceAdapter) {

		_dlAppHelperLocalService = dlAppHelperLocalService;
		_dlAppServiceAdapter = dlAppServiceAdapter;
		_dlFileEntryServiceAdapter = dlFileEntryServiceAdapter;
		_dlFolderServiceAdapter = dlFolderServiceAdapter;
		_repositoryServiceAdapter = repositoryServiceAdapter;
	}

	@Override
	public void deleteFileEntry(FileEntry fileEntry) throws PortalException {
		_dlAppServiceAdapter.deleteFileEntry(fileEntry.getFileEntryId());
	}

	@Override
	public void deleteFolder(Folder folder) throws PortalException {
		List<DLFileEntry> dlFileEntries =
			_dlFileEntryServiceAdapter.getGroupFileEntries(
				folder.getGroupId(), 0, folder.getRepositoryId(),
				folder.getFolderId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null);

		for (DLFileEntry dlFileEntry : dlFileEntries) {
			FileEntry fileEntry = new LiferayFileEntry(dlFileEntry);

			_dlAppHelperLocalService.deleteFileEntry(fileEntry);
		}

		_dlAppHelperLocalService.deleteFolder(folder);

		_dlFolderServiceAdapter.deleteFolder(folder.getFolderId(), false);
	}

	@Override
	public boolean isInTrash(FileEntry fileEntry) throws PortalException {
		DLFileEntry dlFileEntry = (DLFileEntry)fileEntry.getModel();

		return dlFileEntry.isInTrash();
	}

	@Override
	public boolean isInTrash(Folder folder) {
		DLFolder dlFolder = (DLFolder)folder.getModel();

		return dlFolder.isInTrash();
	}

	@Override
	public FileEntry moveFileEntryFromTrash(
			long userId, FileEntry fileEntry, Folder newFolder,
			ServiceContext serviceContext)
		throws PortalException {

		long newFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (newFolder != null) {
			newFolderId = newFolder.getFolderId();
		}

		return _dlAppHelperLocalService.moveFileEntryFromTrash(
			userId, fileEntry, newFolderId, serviceContext);
	}

	@Override
	public FileEntry moveFileEntryToTrash(long userId, FileEntry fileEntry)
		throws PortalException {

		return _dlAppHelperLocalService.moveFileEntryToTrash(userId, fileEntry);
	}

	@Override
	public FileShortcut moveFileShortcutFromTrash(
			long userId, FileShortcut fileShortcut, Folder newFolder,
			ServiceContext serviceContext)
		throws PortalException {

		long newFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (newFolder != null) {
			newFolderId = newFolder.getFolderId();
		}

		return _dlAppHelperLocalService.moveFileShortcutFromTrash(
			userId, fileShortcut, newFolderId, serviceContext);
	}

	@Override
	public FileShortcut moveFileShortcutToTrash(
			long userId, FileShortcut fileShortcut)
		throws PortalException {

		return _dlAppHelperLocalService.moveFileShortcutToTrash(
			userId, fileShortcut);
	}

	@Override
	public Folder moveFolderFromTrash(
			long userId, Folder folder, Folder destinationFolder,
			ServiceContext serviceContext)
		throws PortalException {

		long destinationFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (destinationFolder != null) {
			destinationFolderId = destinationFolder.getFolderId();
		}

		return _dlAppHelperLocalService.moveFolderFromTrash(
			userId, folder, destinationFolderId, serviceContext);
	}

	@Override
	public Folder moveFolderToTrash(long userId, Folder folder)
		throws PortalException {

		return _dlAppHelperLocalService.moveFolderToTrash(userId, folder);
	}

	@Override
	public void registerRepositoryEventListeners(
		RepositoryEventRegistry repositoryEventRegistry) {

		repositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Delete.class, FileEntry.class,
			new DeleteFileEntryRepositoryEventListener());
		repositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Delete.class, Folder.class,
			new DeleteFolderRepositoryEventListener());
		repositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Delete.class, LocalRepository.class,
			new DeleteLocalRepositoryEventListener());
	}

	@Override
	public void restoreFileEntryFromTrash(long userId, FileEntry fileEntry)
		throws PortalException {

		_dlAppHelperLocalService.restoreFileEntryFromTrash(userId, fileEntry);
	}

	@Override
	public void restoreFileShortcutFromTrash(
			long userId, FileShortcut fileShortcut)
		throws PortalException {

		_dlAppHelperLocalService.restoreFileShortcutFromTrash(
			userId, fileShortcut);
	}

	@Override
	public void restoreFolderFromTrash(long userId, Folder folder)
		throws PortalException {

		_dlAppHelperLocalService.restoreFolderFromTrash(userId, folder);
	}

	private void _deleteRepositoryTrashEntries(
		long repositoryId, String className) {
	}

	private void _deleteTrashEntries(long repositoryId) throws PortalException {
	}

	private final DLAppHelperLocalService _dlAppHelperLocalService;
	private final DLAppServiceAdapter _dlAppServiceAdapter;
	private final DLFileEntryServiceAdapter _dlFileEntryServiceAdapter;
	private final DLFolderServiceAdapter _dlFolderServiceAdapter;
	private final RepositoryServiceAdapter _repositoryServiceAdapter;

	private class DeleteFileEntryRepositoryEventListener
		implements RepositoryEventListener
			<RepositoryEventType.Delete, FileEntry> {

		@Override
		public void execute(FileEntry fileEntry) {
		}

	}

	private class DeleteFolderRepositoryEventListener
		implements RepositoryEventListener<RepositoryEventType.Delete, Folder> {

		@Override
		public void execute(Folder folder) {
		}

	}

	private class DeleteLocalRepositoryEventListener
		implements RepositoryEventListener
			<RepositoryEventType.Delete, LocalRepository> {

		@Override
		public void execute(LocalRepository localRepository)
			throws PortalException {

			LiferayTrashCapability.this._deleteTrashEntries(
				localRepository.getRepositoryId());
		}

	}

}