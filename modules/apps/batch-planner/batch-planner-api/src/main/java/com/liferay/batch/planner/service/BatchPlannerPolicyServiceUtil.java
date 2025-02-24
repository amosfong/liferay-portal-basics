/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.planner.service;

import com.liferay.batch.planner.model.BatchPlannerPolicy;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;

import java.util.List;

/**
 * Provides the remote service utility for BatchPlannerPolicy. This utility wraps
 * <code>com.liferay.batch.planner.service.impl.BatchPlannerPolicyServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see BatchPlannerPolicyService
 * @generated
 */
public class BatchPlannerPolicyServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.batch.planner.service.impl.BatchPlannerPolicyServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static BatchPlannerPolicy addBatchPlannerPolicy(
			long batchPlannerPlanId, String name, String value)
		throws PortalException {

		return getService().addBatchPlannerPolicy(
			batchPlannerPlanId, name, value);
	}

	public static BatchPlannerPolicy deleteBatchPlannerPolicy(
			long batchPlannerPlanId, String name)
		throws PortalException {

		return getService().deleteBatchPlannerPolicy(batchPlannerPlanId, name);
	}

	public static List<BatchPlannerPolicy> getBatchPlannerPolicies(
			long batchPlannerPlanId)
		throws PortalException {

		return getService().getBatchPlannerPolicies(batchPlannerPlanId);
	}

	public static BatchPlannerPolicy getBatchPlannerPolicy(
			long batchPlannerPlanId, String name)
		throws PortalException {

		return getService().getBatchPlannerPolicy(batchPlannerPlanId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static boolean hasBatchPlannerPolicy(
			long batchPlannerPlanId, String name)
		throws PortalException {

		return getService().hasBatchPlannerPolicy(batchPlannerPlanId, name);
	}

	public static BatchPlannerPolicy updateBatchPlannerPolicy(
			long batchPlannerPlanId, String name, String value)
		throws PortalException {

		return getService().updateBatchPlannerPolicy(
			batchPlannerPlanId, name, value);
	}

	public static BatchPlannerPolicyService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<BatchPlannerPolicyService> _serviceSnapshot =
		new Snapshot<>(
			BatchPlannerPolicyServiceUtil.class,
			BatchPlannerPolicyService.class);

}