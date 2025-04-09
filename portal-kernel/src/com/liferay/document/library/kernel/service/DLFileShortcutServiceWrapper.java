/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DLFileShortcutService}.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileShortcutService
 * @generated
 */
public class DLFileShortcutServiceWrapper
	implements DLFileShortcutService, ServiceWrapper<DLFileShortcutService> {

	public DLFileShortcutServiceWrapper() {
		this(null);
	}

	public DLFileShortcutServiceWrapper(
		DLFileShortcutService dlFileShortcutService) {

		_dlFileShortcutService = dlFileShortcutService;
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileShortcut
			addFileShortcut(
				String externalReferenceCode, long groupId, long repositoryId,
				long folderId, long toFileEntryId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileShortcutService.addFileShortcut(
			externalReferenceCode, groupId, repositoryId, folderId,
			toFileEntryId, serviceContext);
	}

	@Override
	public void deleteFileShortcut(long fileShortcutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_dlFileShortcutService.deleteFileShortcut(fileShortcutId);
	}

	@Override
	public void deleteFileShortcut(String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_dlFileShortcutService.deleteFileShortcut(
			externalReferenceCode, groupId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileShortcut
			getDLFileShortcutByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileShortcutService.getDLFileShortcutByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileShortcut
			getFileShortcut(long fileShortcutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileShortcutService.getFileShortcut(fileShortcutId);
	}

	@Override
	public java.util.List
		<com.liferay.document.library.kernel.model.DLFileShortcut>
			getGroupFileShortcuts(long groupId) {

		return _dlFileShortcutService.getGroupFileShortcuts(groupId);
	}

	@Override
	public java.util.List
		<com.liferay.document.library.kernel.model.DLFileShortcut>
			getGroupFileShortcuts(long groupId, int start, int end) {

		return _dlFileShortcutService.getGroupFileShortcuts(
			groupId, start, end);
	}

	@Override
	public long getGroupFileShortcutsCount(long groupId) {
		return _dlFileShortcutService.getGroupFileShortcutsCount(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dlFileShortcutService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileShortcut
			updateFileShortcut(
				long fileShortcutId, long repositoryId, long folderId,
				long toFileEntryId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileShortcutService.updateFileShortcut(
			fileShortcutId, repositoryId, folderId, toFileEntryId,
			serviceContext);
	}

	@Override
	public void updateFileShortcuts(
			long oldToFileEntryId, long newToFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_dlFileShortcutService.updateFileShortcuts(
			oldToFileEntryId, newToFileEntryId);
	}

	@Override
	public DLFileShortcutService getWrappedService() {
		return _dlFileShortcutService;
	}

	@Override
	public void setWrappedService(DLFileShortcutService dlFileShortcutService) {
		_dlFileShortcutService = dlFileShortcutService;
	}

	private DLFileShortcutService _dlFileShortcutService;

}