/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.service;

import com.liferay.commerce.price.list.model.CommercePriceListDiscountRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommercePriceListDiscountRel. This utility wraps
 * <code>com.liferay.commerce.price.list.service.impl.CommercePriceListDiscountRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListDiscountRelService
 * @generated
 */
public class CommercePriceListDiscountRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListDiscountRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommercePriceListDiscountRel addCommercePriceListDiscountRel(
			long commercePriceListId, long commerceDiscountId, int order,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommercePriceListDiscountRel(
			commercePriceListId, commerceDiscountId, order, serviceContext);
	}

	public static void deleteCommercePriceListDiscountRel(
			long commercePriceListDiscountRelId)
		throws PortalException {

		getService().deleteCommercePriceListDiscountRel(
			commercePriceListDiscountRelId);
	}

	public static CommercePriceListDiscountRel
			fetchCommercePriceListDiscountRel(
				long commercePriceListId, long commerceDiscountId)
		throws PortalException {

		return getService().fetchCommercePriceListDiscountRel(
			commercePriceListId, commerceDiscountId);
	}

	public static CommercePriceListDiscountRel getCommercePriceListDiscountRel(
			long commercePriceListDiscountRelId)
		throws PortalException {

		return getService().getCommercePriceListDiscountRel(
			commercePriceListDiscountRelId);
	}

	public static List<CommercePriceListDiscountRel>
			getCommercePriceListDiscountRels(long commercePriceListId)
		throws PortalException {

		return getService().getCommercePriceListDiscountRels(
			commercePriceListId);
	}

	public static List<CommercePriceListDiscountRel>
			getCommercePriceListDiscountRels(
				long commercePriceListId, int start, int end,
				OrderByComparator<CommercePriceListDiscountRel>
					orderByComparator)
		throws PortalException {

		return getService().getCommercePriceListDiscountRels(
			commercePriceListId, start, end, orderByComparator);
	}

	public static int getCommercePriceListDiscountRelsCount(
			long commercePriceListId)
		throws PortalException {

		return getService().getCommercePriceListDiscountRelsCount(
			commercePriceListId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommercePriceListDiscountRelService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<CommercePriceListDiscountRelService>
		_serviceSnapshot = new Snapshot<>(
			CommercePriceListDiscountRelServiceUtil.class,
			CommercePriceListDiscountRelService.class);

}