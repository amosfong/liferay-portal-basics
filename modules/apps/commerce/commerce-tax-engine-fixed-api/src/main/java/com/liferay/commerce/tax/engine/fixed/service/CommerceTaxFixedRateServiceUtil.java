/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.engine.fixed.service;

import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceTaxFixedRate. This utility wraps
 * <code>com.liferay.commerce.tax.engine.fixed.service.impl.CommerceTaxFixedRateServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTaxFixedRateService
 * @generated
 */
public class CommerceTaxFixedRateServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.tax.engine.fixed.service.impl.CommerceTaxFixedRateServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public static CommerceTaxFixedRate addCommerceTaxFixedRate(
			long commerceTaxMethodId, long cpTaxCategoryId, double rate,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceTaxFixedRate(
			commerceTaxMethodId, cpTaxCategoryId, rate, serviceContext);
	}

	public static CommerceTaxFixedRate addCommerceTaxFixedRate(
			long groupId, long commerceTaxMethodId, long cpTaxCategoryId,
			double rate)
		throws PortalException {

		return getService().addCommerceTaxFixedRate(
			groupId, commerceTaxMethodId, cpTaxCategoryId, rate);
	}

	public static void deleteCommerceTaxFixedRate(long commerceTaxFixedRateId)
		throws PortalException {

		getService().deleteCommerceTaxFixedRate(commerceTaxFixedRateId);
	}

	public static CommerceTaxFixedRate fetchCommerceTaxFixedRate(
			long commerceTaxFixedRateId)
		throws PortalException {

		return getService().fetchCommerceTaxFixedRate(commerceTaxFixedRateId);
	}

	public static CommerceTaxFixedRate fetchCommerceTaxFixedRate(
			long cpTaxCategoryId, long commerceTaxMethodId)
		throws PortalException {

		return getService().fetchCommerceTaxFixedRate(
			cpTaxCategoryId, commerceTaxMethodId);
	}

	public static List<CommerceTaxFixedRate> getCommerceTaxFixedRates(
			long groupId, long commerceTaxMethodId, int start, int end,
			OrderByComparator<CommerceTaxFixedRate> orderByComparator)
		throws PortalException {

		return getService().getCommerceTaxFixedRates(
			groupId, commerceTaxMethodId, start, end, orderByComparator);
	}

	public static int getCommerceTaxFixedRatesCount(
			long groupId, long commerceTaxMethodId)
		throws PortalException {

		return getService().getCommerceTaxFixedRatesCount(
			groupId, commerceTaxMethodId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceTaxFixedRate updateCommerceTaxFixedRate(
			long commerceTaxFixedRateId, double rate)
		throws PortalException {

		return getService().updateCommerceTaxFixedRate(
			commerceTaxFixedRateId, rate);
	}

	public static CommerceTaxFixedRateService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<CommerceTaxFixedRateService>
		_serviceSnapshot = new Snapshot<>(
			CommerceTaxFixedRateServiceUtil.class,
			CommerceTaxFixedRateService.class);

}