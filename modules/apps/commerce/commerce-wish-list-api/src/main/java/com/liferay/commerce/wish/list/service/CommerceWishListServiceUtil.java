/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.wish.list.service;

import com.liferay.commerce.wish.list.model.CommerceWishList;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceWishList. This utility wraps
 * <code>com.liferay.commerce.wish.list.service.impl.CommerceWishListServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Andrea Di Giorgi
 * @see CommerceWishListService
 * @generated
 */
public class CommerceWishListServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.wish.list.service.impl.CommerceWishListServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceWishList addCommerceWishList(
			String name, boolean defaultWishList,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceWishList(
			name, defaultWishList, serviceContext);
	}

	public static void deleteCommerceWishList(long commerceWishListId)
		throws PortalException {

		getService().deleteCommerceWishList(commerceWishListId);
	}

	public static CommerceWishList fetchCommerceWishList(
			long groupId, long userId, boolean defaultWishList,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws PortalException {

		return getService().fetchCommerceWishList(
			groupId, userId, defaultWishList, orderByComparator);
	}

	public static CommerceWishList getCommerceWishList(long commerceWishListId)
		throws PortalException {

		return getService().getCommerceWishList(commerceWishListId);
	}

	public static List<CommerceWishList> getCommerceWishLists(
			long groupId, int start, int end,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws PortalException {

		return getService().getCommerceWishLists(
			groupId, start, end, orderByComparator);
	}

	public static List<CommerceWishList> getCommerceWishLists(
			long groupId, long userId, int start, int end,
			OrderByComparator<CommerceWishList> orderByComparator)
		throws PortalException {

		return getService().getCommerceWishLists(
			groupId, userId, start, end, orderByComparator);
	}

	public static int getCommerceWishListsCount(long groupId)
		throws PortalException {

		return getService().getCommerceWishListsCount(groupId);
	}

	public static int getCommerceWishListsCount(long groupId, long userId)
		throws PortalException {

		return getService().getCommerceWishListsCount(groupId, userId);
	}

	public static CommerceWishList getDefaultCommerceWishList(
			long groupId, long userId)
		throws PortalException {

		return getService().getDefaultCommerceWishList(groupId, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceWishList updateCommerceWishList(
			long commerceWishListId, String name, boolean defaultWishList)
		throws PortalException {

		return getService().updateCommerceWishList(
			commerceWishListId, name, defaultWishList);
	}

	public static CommerceWishListService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<CommerceWishListService> _serviceSnapshot =
		new Snapshot<>(
			CommerceWishListServiceUtil.class, CommerceWishListService.class);

}