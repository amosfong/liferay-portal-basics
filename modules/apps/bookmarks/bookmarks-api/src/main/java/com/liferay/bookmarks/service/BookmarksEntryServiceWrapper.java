/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.service;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BookmarksEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksEntryService
 * @generated
 */
public class BookmarksEntryServiceWrapper
	implements BookmarksEntryService, ServiceWrapper<BookmarksEntryService> {

	public BookmarksEntryServiceWrapper() {
		this(null);
	}

	public BookmarksEntryServiceWrapper(
		BookmarksEntryService bookmarksEntryService) {

		_bookmarksEntryService = bookmarksEntryService;
	}

	@Override
	public BookmarksEntry addEntry(
			long groupId, long folderId, String name, String url,
			String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.addEntry(
			groupId, folderId, name, url, description, serviceContext);
	}

	@Override
	public void deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_bookmarksEntryService.deleteEntry(entryId);
	}

	@Override
	public java.util.List<BookmarksEntry> getEntries(
		long groupId, long folderId, int start, int end) {

		return _bookmarksEntryService.getEntries(groupId, folderId, start, end);
	}

	@Override
	public java.util.List<BookmarksEntry> getEntries(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BookmarksEntry>
			orderByComparator) {

		return _bookmarksEntryService.getEntries(
			groupId, folderId, start, end, orderByComparator);
	}

	@Override
	public int getEntriesCount(long groupId, long folderId) {
		return _bookmarksEntryService.getEntriesCount(groupId, folderId);
	}

	@Override
	public int getEntriesCount(long groupId, long folderId, int status) {
		return _bookmarksEntryService.getEntriesCount(
			groupId, folderId, status);
	}

	@Override
	public BookmarksEntry getEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.getEntry(entryId);
	}

	@Override
	public int getFoldersEntriesCount(
		long groupId, java.util.List<Long> folderIds) {

		return _bookmarksEntryService.getFoldersEntriesCount(
			groupId, folderIds);
	}

	@Override
	public java.util.List<BookmarksEntry> getGroupEntries(
			long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.getGroupEntries(groupId, start, end);
	}

	@Override
	public java.util.List<BookmarksEntry> getGroupEntries(
			long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.getGroupEntries(
			groupId, userId, start, end);
	}

	@Override
	public java.util.List<BookmarksEntry> getGroupEntries(
			long groupId, long userId, long rootFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.getGroupEntries(
			groupId, userId, rootFolderId, start, end);
	}

	@Override
	public int getGroupEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.getGroupEntriesCount(groupId);
	}

	@Override
	public int getGroupEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.getGroupEntriesCount(groupId, userId);
	}

	@Override
	public int getGroupEntriesCount(
			long groupId, long userId, long rootFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.getGroupEntriesCount(
			groupId, userId, rootFolderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _bookmarksEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public BookmarksEntry moveEntry(long entryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.moveEntry(entryId, parentFolderId);
	}

	@Override
	public BookmarksEntry moveEntryFromTrash(long entryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.moveEntryFromTrash(
			entryId, parentFolderId);
	}

	@Override
	public BookmarksEntry moveEntryToTrash(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.moveEntryToTrash(entryId);
	}

	@Override
	public BookmarksEntry openEntry(BookmarksEntry entry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.openEntry(entry);
	}

	@Override
	public BookmarksEntry openEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.openEntry(entryId);
	}

	@Override
	public void restoreEntryFromTrash(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_bookmarksEntryService.restoreEntryFromTrash(entryId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
			long groupId, long creatorUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.search(
			groupId, creatorUserId, status, start, end);
	}

	@Override
	public void subscribeEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_bookmarksEntryService.subscribeEntry(entryId);
	}

	@Override
	public void unsubscribeEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_bookmarksEntryService.unsubscribeEntry(entryId);
	}

	@Override
	public BookmarksEntry updateEntry(
			long entryId, long groupId, long folderId, String name, String url,
			String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookmarksEntryService.updateEntry(
			entryId, groupId, folderId, name, url, description, serviceContext);
	}

	@Override
	public BookmarksEntryService getWrappedService() {
		return _bookmarksEntryService;
	}

	@Override
	public void setWrappedService(BookmarksEntryService bookmarksEntryService) {
		_bookmarksEntryService = bookmarksEntryService;
	}

	private BookmarksEntryService _bookmarksEntryService;

}