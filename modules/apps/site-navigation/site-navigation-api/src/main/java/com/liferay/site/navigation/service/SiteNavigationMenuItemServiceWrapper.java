/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SiteNavigationMenuItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see SiteNavigationMenuItemService
 * @generated
 */
public class SiteNavigationMenuItemServiceWrapper
	implements ServiceWrapper<SiteNavigationMenuItemService>,
			   SiteNavigationMenuItemService {

	public SiteNavigationMenuItemServiceWrapper() {
		this(null);
	}

	public SiteNavigationMenuItemServiceWrapper(
		SiteNavigationMenuItemService siteNavigationMenuItemService) {

		_siteNavigationMenuItemService = siteNavigationMenuItemService;
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenuItem
			addSiteNavigationMenuItem(
				String externalReferenceCode, long groupId,
				long siteNavigationMenuId, long parentSiteNavigationMenuItemId,
				String type, String typeSettings,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuItemService.addSiteNavigationMenuItem(
			externalReferenceCode, groupId, siteNavigationMenuId,
			parentSiteNavigationMenuItemId, type, typeSettings, serviceContext);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenuItem
			deleteSiteNavigationMenuItem(long siteNavigationMenuItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuItemService.deleteSiteNavigationMenuItem(
			siteNavigationMenuItemId);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenuItem
			deleteSiteNavigationMenuItem(
				long siteNavigationMenuItemId, boolean deleteChildren)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuItemService.deleteSiteNavigationMenuItem(
			siteNavigationMenuItemId, deleteChildren);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenuItem
			deleteSiteNavigationMenuItem(
				String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuItemService.deleteSiteNavigationMenuItem(
			externalReferenceCode, groupId);
	}

	@Override
	public void deleteSiteNavigationMenuItems(long siteNavigationMenuId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_siteNavigationMenuItemService.deleteSiteNavigationMenuItems(
			siteNavigationMenuId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _siteNavigationMenuItemService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<Long> getParentSiteNavigationMenuItemIds(
		long siteNavigationMenuId, String typeSettingsKeyword) {

		return _siteNavigationMenuItemService.
			getParentSiteNavigationMenuItemIds(
				siteNavigationMenuId, typeSettingsKeyword);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenuItem
			getSiteNavigationMenuItemByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuItemService.
			getSiteNavigationMenuItemByExternalReferenceCode(
				externalReferenceCode, groupId);
	}

	@Override
	public java.util.List
		<com.liferay.site.navigation.model.SiteNavigationMenuItem>
			getSiteNavigationMenuItems(long siteNavigationMenuId) {

		return _siteNavigationMenuItemService.getSiteNavigationMenuItems(
			siteNavigationMenuId);
	}

	@Override
	public java.util.List
		<com.liferay.site.navigation.model.SiteNavigationMenuItem>
				getSiteNavigationMenuItems(
					long siteNavigationMenuId,
					long parentSiteNavigationMenuItemId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuItemService.getSiteNavigationMenuItems(
			siteNavigationMenuId, parentSiteNavigationMenuItemId);
	}

	@Override
	public java.util.List
		<com.liferay.site.navigation.model.SiteNavigationMenuItem>
			getSiteNavigationMenuItems(
				long siteNavigationMenuId,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.site.navigation.model.SiteNavigationMenuItem>
						orderByComparator) {

		return _siteNavigationMenuItemService.getSiteNavigationMenuItems(
			siteNavigationMenuId, orderByComparator);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenuItem
			updateSiteNavigationMenuItem(
				long siteNavigationMenuId, long parentSiteNavigationMenuItemId,
				int order)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuItemService.updateSiteNavigationMenuItem(
			siteNavigationMenuId, parentSiteNavigationMenuItemId, order);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenuItem
			updateSiteNavigationMenuItem(
				long siteNavigationMenuId, String typeSettings,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuItemService.updateSiteNavigationMenuItem(
			siteNavigationMenuId, typeSettings, serviceContext);
	}

	@Override
	public SiteNavigationMenuItemService getWrappedService() {
		return _siteNavigationMenuItemService;
	}

	@Override
	public void setWrappedService(
		SiteNavigationMenuItemService siteNavigationMenuItemService) {

		_siteNavigationMenuItemService = siteNavigationMenuItemService;
	}

	private SiteNavigationMenuItemService _siteNavigationMenuItemService;

}