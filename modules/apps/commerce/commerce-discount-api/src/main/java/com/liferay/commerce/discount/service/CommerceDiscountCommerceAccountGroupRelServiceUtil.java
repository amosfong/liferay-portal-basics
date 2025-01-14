/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.service;

import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceDiscountCommerceAccountGroupRel. This utility wraps
 * <code>com.liferay.commerce.discount.service.impl.CommerceDiscountCommerceAccountGroupRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceDiscountCommerceAccountGroupRelService
 * @generated
 */
public class CommerceDiscountCommerceAccountGroupRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.discount.service.impl.CommerceDiscountCommerceAccountGroupRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceDiscountCommerceAccountGroupRel
			addCommerceDiscountCommerceAccountGroupRel(
				long commerceDiscountId, long commerceAccountGroupId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceDiscountCommerceAccountGroupRel(
			commerceDiscountId, commerceAccountGroupId, serviceContext);
	}

	public static void deleteCommerceDiscountCommerceAccountGroupRel(
			long commerceDiscountCommerceAccountGroupRelId)
		throws PortalException {

		getService().deleteCommerceDiscountCommerceAccountGroupRel(
			commerceDiscountCommerceAccountGroupRelId);
	}

	public static void
			deleteCommerceDiscountCommerceAccountGroupRelsByCommerceDiscountId(
				long commerceDiscountId)
		throws PortalException {

		getService().
			deleteCommerceDiscountCommerceAccountGroupRelsByCommerceDiscountId(
				commerceDiscountId);
	}

	public static CommerceDiscountCommerceAccountGroupRel
			fetchCommerceDiscountCommerceAccountGroupRel(
				long commerceDiscountId, long commerceAccountGroupId)
		throws PortalException {

		return getService().fetchCommerceDiscountCommerceAccountGroupRel(
			commerceDiscountId, commerceAccountGroupId);
	}

	public static CommerceDiscountCommerceAccountGroupRel
			getCommerceDiscountCommerceAccountGroupRel(
				long commerceDiscountCommerceAccountGroupRelId)
		throws PortalException {

		return getService().getCommerceDiscountCommerceAccountGroupRel(
			commerceDiscountCommerceAccountGroupRelId);
	}

	public static List<CommerceDiscountCommerceAccountGroupRel>
			getCommerceDiscountCommerceAccountGroupRels(
				long commerceDiscountId, int start, int end,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws PortalException {

		return getService().getCommerceDiscountCommerceAccountGroupRels(
			commerceDiscountId, start, end, orderByComparator);
	}

	public static List<CommerceDiscountCommerceAccountGroupRel>
		getCommerceDiscountCommerceAccountGroupRels(
			long commerceDiscountId, String name, int start, int end) {

		return getService().getCommerceDiscountCommerceAccountGroupRels(
			commerceDiscountId, name, start, end);
	}

	public static int getCommerceDiscountCommerceAccountGroupRelsCount(
			long commerceDiscountId)
		throws PortalException {

		return getService().getCommerceDiscountCommerceAccountGroupRelsCount(
			commerceDiscountId);
	}

	public static int getCommerceDiscountCommerceAccountGroupRelsCount(
		long commerceDiscountId, String name) {

		return getService().getCommerceDiscountCommerceAccountGroupRelsCount(
			commerceDiscountId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceDiscountCommerceAccountGroupRelService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot
		<CommerceDiscountCommerceAccountGroupRelService> _serviceSnapshot =
			new Snapshot<>(
				CommerceDiscountCommerceAccountGroupRelServiceUtil.class,
				CommerceDiscountCommerceAccountGroupRelService.class);

}