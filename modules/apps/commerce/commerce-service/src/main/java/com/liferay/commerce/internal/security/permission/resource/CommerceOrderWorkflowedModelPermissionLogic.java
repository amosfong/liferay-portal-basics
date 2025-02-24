/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.security.permission.resource;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermissionUtil;

import java.util.Objects;
import java.util.function.ToLongFunction;

/**
 * @author Marco Leo
 */
public class CommerceOrderWorkflowedModelPermissionLogic
	implements ModelResourcePermissionLogic<CommerceOrder> {

	public CommerceOrderWorkflowedModelPermissionLogic(
		ModelResourcePermission<CommerceOrder> modelResourcePermission,
		ToLongFunction<CommerceOrder> primKeyToLongFunction) {

		_modelResourcePermission = Objects.requireNonNull(
			modelResourcePermission);
		_primKeyToLongFunction = Objects.requireNonNull(primKeyToLongFunction);
	}

	@Override
	public Boolean contains(
			PermissionChecker permissionChecker, String name,
			CommerceOrder commerceOrder, String actionId)
		throws PortalException {

		if (commerceOrder.isDraft() || commerceOrder.isScheduled()) {
			if (!actionId.equals(ActionKeys.VIEW) ||
				_modelResourcePermission.contains(
					permissionChecker, commerceOrder, ActionKeys.UPDATE)) {

				return null;
			}

			return false;
		}
		else if (commerceOrder.isPending()) {
			return WorkflowPermissionUtil.hasPermission(
				permissionChecker, commerceOrder.getGroupId(), name,
				_primKeyToLongFunction.applyAsLong(commerceOrder), actionId);
		}

		return null;
	}

	private final ModelResourcePermission<CommerceOrder>
		_modelResourcePermission;
	private final ToLongFunction<CommerceOrder> _primKeyToLongFunction;

}