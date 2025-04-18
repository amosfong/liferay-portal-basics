/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.utility.page.service;

import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for LayoutUtilityPageEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutUtilityPageEntryServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LayoutUtilityPageEntryService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.layout.utility.page.service.impl.LayoutUtilityPageEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the layout utility page entry remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link LayoutUtilityPageEntryServiceUtil} if injection and service tracking are not available.
	 */
	public LayoutUtilityPageEntry addLayoutUtilityPageEntry(
			String externalReferenceCode, long groupId, long plid,
			long previewFileEntryId, boolean defaultLayoutUtilityPageEntry,
			String name, String type, long masterLayoutPlid,
			ServiceContext serviceContext)
		throws PortalException;

	public LayoutUtilityPageEntry copyLayoutUtilityPageEntry(
			long groupId, long sourceLayoutUtilityPageEntryId,
			ServiceContext serviceContext)
		throws Exception;

	public LayoutUtilityPageEntry deleteLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId)
		throws PortalException;

	public LayoutUtilityPageEntry deleteLayoutUtilityPageEntry(
			String externalReferenceCode, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutUtilityPageEntry fetchLayoutUtilityPageEntry(
		long layoutUtilityPageEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutUtilityPageEntry
			fetchLayoutUtilityPageEntryByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutUtilityPageEntry getDefaultLayoutUtilityPageEntry(
			long groupId, String type)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutUtilityPageEntry> getLayoutUtilityPageEntries(
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutUtilityPageEntry> getLayoutUtilityPageEntries(
		long groupId, int start, int end,
		OrderByComparator<LayoutUtilityPageEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutUtilityPageEntry> getLayoutUtilityPageEntries(
		long groupId, String type, int start, int end,
		OrderByComparator<LayoutUtilityPageEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutUtilityPageEntry> getLayoutUtilityPageEntries(
		long groupId, String keyword, String[] types, int start, int end,
		OrderByComparator<LayoutUtilityPageEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutUtilityPageEntry> getLayoutUtilityPageEntries(
		long groupId, String[] types, int start, int end,
		OrderByComparator<LayoutUtilityPageEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLayoutUtilityPageEntriesCount(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLayoutUtilityPageEntriesCount(
		long groupId, String keyword, String[] types);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLayoutUtilityPageEntriesCount(long groupId, String[] types);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LayoutUtilityPageEntry
			getLayoutUtilityPageEntryByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public LayoutUtilityPageEntry setDefaultLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId)
		throws PortalException;

	public LayoutUtilityPageEntry unsetDefaultLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId)
		throws PortalException;

	public LayoutUtilityPageEntry updateLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId, long previewFileEntryId)
		throws PortalException;

	public LayoutUtilityPageEntry updateLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId, String name)
		throws PortalException;

}