/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.service;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for AssetTag. This utility wraps
 * <code>com.liferay.portlet.asset.service.impl.AssetTagServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagService
 * @generated
 */
public class AssetTagServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.asset.service.impl.AssetTagServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AssetTag addTag(
			String externalReferenceCode, long groupId, String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addTag(
			externalReferenceCode, groupId, name, serviceContext);
	}

	public static void deleteTag(long tagId) throws PortalException {
		getService().deleteTag(tagId);
	}

	public static void deleteTags(long[] tagIds) throws PortalException {
		getService().deleteTags(tagIds);
	}

	public static AssetTag fetchAssetTagByExternalReferenceCode(
		String externalReferenceCode, long groupId) {

		return getService().fetchAssetTagByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	public static AssetTag getAssetTagByExternalReferenceCode(
			String externalReferenceCode, long groupId)
		throws PortalException {

		return getService().getAssetTagByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	public static List<AssetTag> getGroupsTags(long[] groupIds) {
		return getService().getGroupsTags(groupIds);
	}

	public static List<AssetTag> getGroupTags(long groupId) {
		return getService().getGroupTags(groupId);
	}

	public static List<AssetTag> getGroupTags(
		long groupId, int start, int end,
		OrderByComparator<AssetTag> orderByComparator) {

		return getService().getGroupTags(
			groupId, start, end, orderByComparator);
	}

	public static int getGroupTagsCount(long groupId) {
		return getService().getGroupTagsCount(groupId);
	}

	public static com.liferay.asset.kernel.model.AssetTagDisplay
		getGroupTagsDisplay(long groupId, String name, int start, int end) {

		return getService().getGroupTagsDisplay(groupId, name, start, end);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AssetTag getTag(long tagId) throws PortalException {
		return getService().getTag(tagId);
	}

	public static List<AssetTag> getTags(
		long groupId, long classNameId, String name) {

		return getService().getTags(groupId, classNameId, name);
	}

	public static List<AssetTag> getTags(
		long groupId, long classNameId, String name, int start, int end,
		OrderByComparator<AssetTag> orderByComparator) {

		return getService().getTags(
			groupId, classNameId, name, start, end, orderByComparator);
	}

	public static List<AssetTag> getTags(
		long groupId, String name, int start, int end) {

		return getService().getTags(groupId, name, start, end);
	}

	public static List<AssetTag> getTags(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetTag> orderByComparator) {

		return getService().getTags(
			groupId, name, start, end, orderByComparator);
	}

	public static List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end) {

		return getService().getTags(groupIds, name, start, end);
	}

	public static List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end,
		OrderByComparator<AssetTag> orderByComparator) {

		return getService().getTags(
			groupIds, name, start, end, orderByComparator);
	}

	public static List<AssetTag> getTags(String className, long classPK) {
		return getService().getTags(className, classPK);
	}

	public static int getTagsCount(long groupId, String name) {
		return getService().getTagsCount(groupId, name);
	}

	public static int getTagsCount(long[] groupIds, String name) {
		return getService().getTagsCount(groupIds, name);
	}

	public static int getVisibleAssetsTagsCount(
		long groupId, long classNameId, String name) {

		return getService().getVisibleAssetsTagsCount(
			groupId, classNameId, name);
	}

	public static void mergeTags(long fromTagId, long toTagId)
		throws PortalException {

		getService().mergeTags(fromTagId, toTagId);
	}

	public static void mergeTags(long[] fromTagIds, long toTagId)
		throws PortalException {

		getService().mergeTags(fromTagIds, toTagId);
	}

	public static com.liferay.portal.kernel.json.JSONArray search(
		long groupId, String name, int start, int end) {

		return getService().search(groupId, name, start, end);
	}

	public static com.liferay.portal.kernel.json.JSONArray search(
		long[] groupIds, String name, int start, int end) {

		return getService().search(groupIds, name, start, end);
	}

	public static void subscribeTag(long userId, long groupId, long tagId)
		throws PortalException {

		getService().subscribeTag(userId, groupId, tagId);
	}

	public static void unsubscribeTag(long userId, long tagId)
		throws PortalException {

		getService().unsubscribeTag(userId, tagId);
	}

	public static AssetTag updateTag(
			String externalReferenceCode, long tagId, String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateTag(
			externalReferenceCode, tagId, name, serviceContext);
	}

	public static AssetTagService getService() {
		return _service;
	}

	public static void setService(AssetTagService service) {
		_service = service;
	}

	private static volatile AssetTagService _service;

}