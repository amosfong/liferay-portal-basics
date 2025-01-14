/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.service;

import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for DLFileShortcut. This utility wraps
 * <code>com.liferay.portlet.documentlibrary.service.impl.DLFileShortcutServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileShortcutService
 * @generated
 */
public class DLFileShortcutServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.documentlibrary.service.impl.DLFileShortcutServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static DLFileShortcut addFileShortcut(
			String externalReferenceCode, long groupId, long repositoryId,
			long folderId, long toFileEntryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addFileShortcut(
			externalReferenceCode, groupId, repositoryId, folderId,
			toFileEntryId, serviceContext);
	}

	public static void deleteFileShortcut(long fileShortcutId)
		throws PortalException {

		getService().deleteFileShortcut(fileShortcutId);
	}

	public static void deleteFileShortcut(
			String externalReferenceCode, long groupId)
		throws PortalException {

		getService().deleteFileShortcut(externalReferenceCode, groupId);
	}

	public static DLFileShortcut getDLFileShortcutByExternalReferenceCode(
			String externalReferenceCode, long groupId)
		throws PortalException {

		return getService().getDLFileShortcutByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	public static DLFileShortcut getFileShortcut(long fileShortcutId)
		throws PortalException {

		return getService().getFileShortcut(fileShortcutId);
	}

	public static List<DLFileShortcut> getGroupFileShortcuts(long groupId) {
		return getService().getGroupFileShortcuts(groupId);
	}

	public static List<DLFileShortcut> getGroupFileShortcuts(
		long groupId, int start, int end) {

		return getService().getGroupFileShortcuts(groupId, start, end);
	}

	public static long getGroupFileShortcutsCount(long groupId) {
		return getService().getGroupFileShortcutsCount(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static DLFileShortcut updateFileShortcut(
			long fileShortcutId, long repositoryId, long folderId,
			long toFileEntryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateFileShortcut(
			fileShortcutId, repositoryId, folderId, toFileEntryId,
			serviceContext);
	}

	public static void updateFileShortcuts(
			long oldToFileEntryId, long newToFileEntryId)
		throws PortalException {

		getService().updateFileShortcuts(oldToFileEntryId, newToFileEntryId);
	}

	public static DLFileShortcutService getService() {
		return _service;
	}

	public static void setService(DLFileShortcutService service) {
		_service = service;
	}

	private static volatile DLFileShortcutService _service;

}