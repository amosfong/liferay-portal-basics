/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SiteNavigationMenuService}.
 *
 * @author Brian Wing Shun Chan
 * @see SiteNavigationMenuService
 * @generated
 */
public class SiteNavigationMenuServiceWrapper
	implements ServiceWrapper<SiteNavigationMenuService>,
			   SiteNavigationMenuService {

	public SiteNavigationMenuServiceWrapper() {
		this(null);
	}

	public SiteNavigationMenuServiceWrapper(
		SiteNavigationMenuService siteNavigationMenuService) {

		_siteNavigationMenuService = siteNavigationMenuService;
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			addSiteNavigationMenu(
				String externalReferenceCode, long groupId, String name,
				int type, boolean auto,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.addSiteNavigationMenu(
			externalReferenceCode, groupId, name, type, auto, serviceContext);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			addSiteNavigationMenu(
				String externalReferenceCode, long groupId, String name,
				int type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.addSiteNavigationMenu(
			externalReferenceCode, groupId, name, type, serviceContext);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			addSiteNavigationMenu(
				String externalReferenceCode, long groupId, String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.addSiteNavigationMenu(
			externalReferenceCode, groupId, name, serviceContext);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			deleteSiteNavigationMenu(long siteNavigationMenuId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.deleteSiteNavigationMenu(
			siteNavigationMenuId);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			deleteSiteNavigationMenu(String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.deleteSiteNavigationMenu(
			externalReferenceCode, groupId);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			fetchSiteNavigationMenu(long siteNavigationMenuId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.fetchSiteNavigationMenu(
			siteNavigationMenuId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _siteNavigationMenuService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			getSiteNavigationMenuByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.
			getSiteNavigationMenuByExternalReferenceCode(
				externalReferenceCode, groupId);
	}

	@Override
	public java.util.List<com.liferay.site.navigation.model.SiteNavigationMenu>
		getSiteNavigationMenus(long groupId) {

		return _siteNavigationMenuService.getSiteNavigationMenus(groupId);
	}

	@Override
	public java.util.List<com.liferay.site.navigation.model.SiteNavigationMenu>
		getSiteNavigationMenus(
			long groupId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.site.navigation.model.SiteNavigationMenu>
					orderByComparator) {

		return _siteNavigationMenuService.getSiteNavigationMenus(
			groupId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.site.navigation.model.SiteNavigationMenu>
		getSiteNavigationMenus(
			long groupId, String keywords, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.site.navigation.model.SiteNavigationMenu>
					orderByComparator) {

		return _siteNavigationMenuService.getSiteNavigationMenus(
			groupId, keywords, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.site.navigation.model.SiteNavigationMenu>
		getSiteNavigationMenus(
			long[] groupIds, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.site.navigation.model.SiteNavigationMenu>
					orderByComparator) {

		return _siteNavigationMenuService.getSiteNavigationMenus(
			groupIds, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.site.navigation.model.SiteNavigationMenu>
		getSiteNavigationMenus(
			long[] groupIds, String keywords, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.site.navigation.model.SiteNavigationMenu>
					orderByComparator) {

		return _siteNavigationMenuService.getSiteNavigationMenus(
			groupIds, keywords, start, end, orderByComparator);
	}

	@Override
	public int getSiteNavigationMenusCount(long groupId) {
		return _siteNavigationMenuService.getSiteNavigationMenusCount(groupId);
	}

	@Override
	public int getSiteNavigationMenusCount(long groupId, String keywords) {
		return _siteNavigationMenuService.getSiteNavigationMenusCount(
			groupId, keywords);
	}

	@Override
	public int getSiteNavigationMenusCount(long[] groupIds) {
		return _siteNavigationMenuService.getSiteNavigationMenusCount(groupIds);
	}

	@Override
	public int getSiteNavigationMenusCount(long[] groupIds, String keywords) {
		return _siteNavigationMenuService.getSiteNavigationMenusCount(
			groupIds, keywords);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			updateSiteNavigationMenu(
				long siteNavigationMenuId, int type, boolean auto,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.updateSiteNavigationMenu(
			siteNavigationMenuId, type, auto, serviceContext);
	}

	@Override
	public com.liferay.site.navigation.model.SiteNavigationMenu
			updateSiteNavigationMenu(
				long siteNavigationMenuId, String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _siteNavigationMenuService.updateSiteNavigationMenu(
			siteNavigationMenuId, name, serviceContext);
	}

	@Override
	public SiteNavigationMenuService getWrappedService() {
		return _siteNavigationMenuService;
	}

	@Override
	public void setWrappedService(
		SiteNavigationMenuService siteNavigationMenuService) {

		_siteNavigationMenuService = siteNavigationMenuService;
	}

	private SiteNavigationMenuService _siteNavigationMenuService;

}