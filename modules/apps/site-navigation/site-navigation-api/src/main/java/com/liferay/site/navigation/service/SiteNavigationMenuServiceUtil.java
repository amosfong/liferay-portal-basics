/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.site.navigation.model.SiteNavigationMenu;

import java.util.List;

/**
 * Provides the remote service utility for SiteNavigationMenu. This utility wraps
 * <code>com.liferay.site.navigation.service.impl.SiteNavigationMenuServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SiteNavigationMenuService
 * @generated
 */
public class SiteNavigationMenuServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.site.navigation.service.impl.SiteNavigationMenuServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static SiteNavigationMenu addSiteNavigationMenu(
			String externalReferenceCode, long groupId, String name, int type,
			boolean auto,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addSiteNavigationMenu(
			externalReferenceCode, groupId, name, type, auto, serviceContext);
	}

	public static SiteNavigationMenu addSiteNavigationMenu(
			String externalReferenceCode, long groupId, String name, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addSiteNavigationMenu(
			externalReferenceCode, groupId, name, type, serviceContext);
	}

	public static SiteNavigationMenu addSiteNavigationMenu(
			String externalReferenceCode, long groupId, String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addSiteNavigationMenu(
			externalReferenceCode, groupId, name, serviceContext);
	}

	public static SiteNavigationMenu deleteSiteNavigationMenu(
			long siteNavigationMenuId)
		throws PortalException {

		return getService().deleteSiteNavigationMenu(siteNavigationMenuId);
	}

	public static SiteNavigationMenu deleteSiteNavigationMenu(
			String externalReferenceCode, long groupId)
		throws PortalException {

		return getService().deleteSiteNavigationMenu(
			externalReferenceCode, groupId);
	}

	public static SiteNavigationMenu fetchSiteNavigationMenu(
			long siteNavigationMenuId)
		throws PortalException {

		return getService().fetchSiteNavigationMenu(siteNavigationMenuId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static SiteNavigationMenu
			getSiteNavigationMenuByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws PortalException {

		return getService().getSiteNavigationMenuByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	public static List<SiteNavigationMenu> getSiteNavigationMenus(
		long groupId) {

		return getService().getSiteNavigationMenus(groupId);
	}

	public static List<SiteNavigationMenu> getSiteNavigationMenus(
		long groupId, int start, int end,
		OrderByComparator<SiteNavigationMenu> orderByComparator) {

		return getService().getSiteNavigationMenus(
			groupId, start, end, orderByComparator);
	}

	public static List<SiteNavigationMenu> getSiteNavigationMenus(
		long groupId, String keywords, int start, int end,
		OrderByComparator<SiteNavigationMenu> orderByComparator) {

		return getService().getSiteNavigationMenus(
			groupId, keywords, start, end, orderByComparator);
	}

	public static List<SiteNavigationMenu> getSiteNavigationMenus(
		long[] groupIds, int start, int end,
		OrderByComparator<SiteNavigationMenu> orderByComparator) {

		return getService().getSiteNavigationMenus(
			groupIds, start, end, orderByComparator);
	}

	public static List<SiteNavigationMenu> getSiteNavigationMenus(
		long[] groupIds, String keywords, int start, int end,
		OrderByComparator<SiteNavigationMenu> orderByComparator) {

		return getService().getSiteNavigationMenus(
			groupIds, keywords, start, end, orderByComparator);
	}

	public static int getSiteNavigationMenusCount(long groupId) {
		return getService().getSiteNavigationMenusCount(groupId);
	}

	public static int getSiteNavigationMenusCount(
		long groupId, String keywords) {

		return getService().getSiteNavigationMenusCount(groupId, keywords);
	}

	public static int getSiteNavigationMenusCount(long[] groupIds) {
		return getService().getSiteNavigationMenusCount(groupIds);
	}

	public static int getSiteNavigationMenusCount(
		long[] groupIds, String keywords) {

		return getService().getSiteNavigationMenusCount(groupIds, keywords);
	}

	public static SiteNavigationMenu updateSiteNavigationMenu(
			long siteNavigationMenuId, int type, boolean auto,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateSiteNavigationMenu(
			siteNavigationMenuId, type, auto, serviceContext);
	}

	public static SiteNavigationMenu updateSiteNavigationMenu(
			long siteNavigationMenuId, String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateSiteNavigationMenu(
			siteNavigationMenuId, name, serviceContext);
	}

	public static SiteNavigationMenuService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<SiteNavigationMenuService> _serviceSnapshot =
		new Snapshot<>(
			SiteNavigationMenuServiceUtil.class,
			SiteNavigationMenuService.class);

}