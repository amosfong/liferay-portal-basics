/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.service;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetTagDisplay;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
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
 * Provides the remote service interface for AssetTag. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagServiceUtil
 * @generated
 */
@AccessControlled
@CTAware
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AssetTagService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.portlet.asset.service.impl.AssetTagServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the asset tag remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link AssetTagServiceUtil} if injection and service tracking are not available.
	 */
	public AssetTag addTag(
			String externalReferenceCode, long groupId, String name,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteTag(long tagId) throws PortalException;

	public void deleteTags(long[] tagIds) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetTag fetchAssetTagByExternalReferenceCode(
		String externalReferenceCode, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetTag getAssetTagByExternalReferenceCode(
			String externalReferenceCode, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getGroupsTags(long[] groupIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getGroupTags(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getGroupTags(
		long groupId, int start, int end,
		OrderByComparator<AssetTag> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupTagsCount(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetTagDisplay getGroupTagsDisplay(
		long groupId, String name, int start, int end);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetTag getTag(long tagId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getTags(long groupId, long classNameId, String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getTags(
		long groupId, long classNameId, String name, int start, int end,
		OrderByComparator<AssetTag> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getTags(
		long groupId, String name, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getTags(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetTag> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end,
		OrderByComparator<AssetTag> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTag> getTags(String className, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTagsCount(long groupId, String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTagsCount(long[] groupIds, String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getVisibleAssetsTagsCount(
		long groupId, long classNameId, String name);

	public void mergeTags(long fromTagId, long toTagId) throws PortalException;

	public void mergeTags(long[] fromTagIds, long toTagId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray search(long groupId, String name, int start, int end);

	@AccessControlled(guestAccessEnabled = true)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray search(long[] groupIds, String name, int start, int end);

	public void subscribeTag(long userId, long groupId, long tagId)
		throws PortalException;

	public void unsubscribeTag(long userId, long tagId) throws PortalException;

	public AssetTag updateTag(
			String externalReferenceCode, long tagId, String name,
			ServiceContext serviceContext)
		throws PortalException;

}