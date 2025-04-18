/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.service;

import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for DLFileShortcut. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileShortcutServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DLFileShortcutService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.portlet.documentlibrary.service.impl.DLFileShortcutServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the document library file shortcut remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link DLFileShortcutServiceUtil} if injection and service tracking are not available.
	 */
	public DLFileShortcut addFileShortcut(
			String externalReferenceCode, long groupId, long repositoryId,
			long folderId, long toFileEntryId, ServiceContext serviceContext)
		throws PortalException;

	public void deleteFileShortcut(long fileShortcutId) throws PortalException;

	public void deleteFileShortcut(String externalReferenceCode, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DLFileShortcut getDLFileShortcutByExternalReferenceCode(
			String externalReferenceCode, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DLFileShortcut getFileShortcut(long fileShortcutId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DLFileShortcut> getGroupFileShortcuts(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DLFileShortcut> getGroupFileShortcuts(
		long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getGroupFileShortcutsCount(long groupId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public DLFileShortcut updateFileShortcut(
			long fileShortcutId, long repositoryId, long folderId,
			long toFileEntryId, ServiceContext serviceContext)
		throws PortalException;

	public void updateFileShortcuts(
			long oldToFileEntryId, long newToFileEntryId)
		throws PortalException;

}