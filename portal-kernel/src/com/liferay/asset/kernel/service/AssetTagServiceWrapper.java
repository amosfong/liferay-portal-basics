/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.service;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetTagService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagService
 * @generated
 */
public class AssetTagServiceWrapper
	implements AssetTagService, ServiceWrapper<AssetTagService> {

	public AssetTagServiceWrapper() {
		this(null);
	}

	public AssetTagServiceWrapper(AssetTagService assetTagService) {
		_assetTagService = assetTagService;
	}

	@Override
	public AssetTag addTag(
			String externalReferenceCode, long groupId, String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetTagService.addTag(
			externalReferenceCode, groupId, name, serviceContext);
	}

	@Override
	public void deleteTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_assetTagService.deleteTag(tagId);
	}

	@Override
	public void deleteTags(long[] tagIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_assetTagService.deleteTags(tagIds);
	}

	@Override
	public AssetTag fetchAssetTagByExternalReferenceCode(
		String externalReferenceCode, long groupId) {

		return _assetTagService.fetchAssetTagByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	@Override
	public AssetTag getAssetTagByExternalReferenceCode(
			String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetTagService.getAssetTagByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	@Override
	public java.util.List<AssetTag> getGroupsTags(long[] groupIds) {
		return _assetTagService.getGroupsTags(groupIds);
	}

	@Override
	public java.util.List<AssetTag> getGroupTags(long groupId) {
		return _assetTagService.getGroupTags(groupId);
	}

	@Override
	public java.util.List<AssetTag> getGroupTags(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetTag>
			orderByComparator) {

		return _assetTagService.getGroupTags(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getGroupTagsCount(long groupId) {
		return _assetTagService.getGroupTagsCount(groupId);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetTagDisplay getGroupTagsDisplay(
		long groupId, String name, int start, int end) {

		return _assetTagService.getGroupTagsDisplay(groupId, name, start, end);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetTagService.getOSGiServiceIdentifier();
	}

	@Override
	public AssetTag getTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetTagService.getTag(tagId);
	}

	@Override
	public java.util.List<AssetTag> getTags(
		long groupId, long classNameId, String name) {

		return _assetTagService.getTags(groupId, classNameId, name);
	}

	@Override
	public java.util.List<AssetTag> getTags(
		long groupId, long classNameId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetTag>
			orderByComparator) {

		return _assetTagService.getTags(
			groupId, classNameId, name, start, end, orderByComparator);
	}

	@Override
	public java.util.List<AssetTag> getTags(
		long groupId, String name, int start, int end) {

		return _assetTagService.getTags(groupId, name, start, end);
	}

	@Override
	public java.util.List<AssetTag> getTags(
		long groupId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetTag>
			orderByComparator) {

		return _assetTagService.getTags(
			groupId, name, start, end, orderByComparator);
	}

	@Override
	public java.util.List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end) {

		return _assetTagService.getTags(groupIds, name, start, end);
	}

	@Override
	public java.util.List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetTag>
			orderByComparator) {

		return _assetTagService.getTags(
			groupIds, name, start, end, orderByComparator);
	}

	@Override
	public java.util.List<AssetTag> getTags(String className, long classPK) {
		return _assetTagService.getTags(className, classPK);
	}

	@Override
	public int getTagsCount(long groupId, String name) {
		return _assetTagService.getTagsCount(groupId, name);
	}

	@Override
	public int getTagsCount(long[] groupIds, String name) {
		return _assetTagService.getTagsCount(groupIds, name);
	}

	@Override
	public int getVisibleAssetsTagsCount(
		long groupId, long classNameId, String name) {

		return _assetTagService.getVisibleAssetsTagsCount(
			groupId, classNameId, name);
	}

	@Override
	public void mergeTags(long fromTagId, long toTagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_assetTagService.mergeTags(fromTagId, toTagId);
	}

	@Override
	public void mergeTags(long[] fromTagIds, long toTagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_assetTagService.mergeTags(fromTagIds, toTagId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray search(
		long groupId, String name, int start, int end) {

		return _assetTagService.search(groupId, name, start, end);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray search(
		long[] groupIds, String name, int start, int end) {

		return _assetTagService.search(groupIds, name, start, end);
	}

	@Override
	public void subscribeTag(long userId, long groupId, long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_assetTagService.subscribeTag(userId, groupId, tagId);
	}

	@Override
	public void unsubscribeTag(long userId, long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_assetTagService.unsubscribeTag(userId, tagId);
	}

	@Override
	public AssetTag updateTag(
			String externalReferenceCode, long tagId, String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetTagService.updateTag(
			externalReferenceCode, tagId, name, serviceContext);
	}

	@Override
	public AssetTagService getWrappedService() {
		return _assetTagService;
	}

	@Override
	public void setWrappedService(AssetTagService assetTagService) {
		_assetTagService = assetTagService;
	}

	private AssetTagService _assetTagService;

}