/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.repository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.model.RepositoryEntry;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alexander Chow
 */
public interface Repository extends DocumentRepository {

	/**
	 * Cancels the file entry check out. If the file entry is not checked out,
	 * invoking this method results in no changes.
	 *
	 * @param  fileEntryId the primary key of the file entry to cancel the check
	 *         out
	 * @return the file entry if the cancel checkout operation was successful;
	 *         <code>null</code> if the file entry was not checked out
	 * @see    #checkInFileEntry(long, long, DLVersionNumberIncrease, String,
	 *         ServiceContext)
	 * @see    #checkOutFileEntry(long, ServiceContext)
	 */
	public FileVersion cancelCheckOut(long fileEntryId) throws PortalException;

	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException;

	public FileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteFileEntry(long folderId, String title)
		throws PortalException;

	public void deleteFileVersion(long fileEntryId, String version)
		throws PortalException;

	public void deleteFolder(long parentFolderId, String name)
		throws PortalException;

	public List<FileEntry> getFileEntries(
			long folderId, long fileEntryTypeId, int start, int end,
			OrderByComparator<FileEntry> orderByComparator)
		throws PortalException;

	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator<FileEntry> orderByComparator)
		throws PortalException;

	public int getFileEntriesAndFileShortcutsCount(
			long folderId, int status, String[] mimeTypes)
		throws PortalException;

	public int getFileEntriesCount(long folderId, long fileEntryTypeId)
		throws PortalException;

	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException;

	public default List<RepositoryEntry>
			getFoldersAndFileEntriesAndFileShortcuts(
				long folderId, int status, String[] mimeTypes,
				boolean includeMountFolders, boolean includeOwner, int start,
				int end, OrderByComparator<?> orderByComparator)
		throws PortalException {

		return getFoldersAndFileEntriesAndFileShortcuts(
			folderId, status, mimeTypes, includeMountFolders, start, end,
			orderByComparator);
	}

	public List<RepositoryEntry> getFoldersAndFileEntriesAndFileShortcuts(
			long folderId, int status, String[] mimeTypes,
			boolean includeMountFolders, int start, int end,
			OrderByComparator<?> orderByComparator)
		throws PortalException;

	public int getFoldersAndFileEntriesAndFileShortcutsCount(
			long folderId, int status, String[] mimeTypes,
			boolean includeMountFolders)
		throws PortalException;

	public default int getFoldersAndFileEntriesAndFileShortcutsCount(
			long folderId, int status, String[] mimeTypes,
			boolean includeMountFolders, boolean includeOwner)
		throws PortalException {

		return getFoldersAndFileEntriesAndFileShortcutsCount(
			folderId, status, mimeTypes, includeMountFolders);
	}

	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws PortalException;

	public List<Folder> getMountFolders(
			long parentFolderId, int start, int end,
			OrderByComparator<Folder> orderByComparator)
		throws PortalException;

	public int getMountFoldersCount(long parentFolderId) throws PortalException;

	public List<FileEntry> getRepositoryFileEntries(
			long userId, long rootFolderId, String[] mimeTypes, int status,
			int start, int end, OrderByComparator<FileEntry> orderByComparator)
		throws PortalException;

	public int getRepositoryFileEntriesCount(long userId, long rootFolderId)
		throws PortalException;

	public int getRepositoryFileEntriesCount(
			long userId, long rootFolderId, String[] mimeTypes, int status)
		throws PortalException;

	public List<FileShortcut> getRepositoryFileShortcuts(long groupId)
		throws PortalException;

	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws PortalException;

	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws PortalException;

	public Lock lockFolder(long folderId) throws PortalException;

	public Lock lockFolder(
			long folderId, String owner, boolean inheritable,
			long expirationTime)
		throws PortalException;

	public Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException;

	public Lock refreshFolderLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException;

	public Hits search(long creatorUserId, int status, int start, int end)
		throws PortalException;

	public Hits search(
			long creatorUserId, long folderId, String[] mimeTypes, int status,
			int start, int end)
		throws PortalException;

	public Hits search(SearchContext searchContext) throws SearchException;

	public Hits search(SearchContext searchContext, Query query)
		throws SearchException;

	public void unlockFolder(long folderId, String lockUuid)
		throws PortalException;

	public void unlockFolder(long parentFolderId, String name, String lockUuid)
		throws PortalException;

	public Folder updateFolder(
			long folderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException;

	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException;

	public boolean verifyFileEntryLock(long fileEntryId, String lockUuid)
		throws PortalException;

	public boolean verifyInheritableLock(long folderId, String lockUuid)
		throws PortalException;

}