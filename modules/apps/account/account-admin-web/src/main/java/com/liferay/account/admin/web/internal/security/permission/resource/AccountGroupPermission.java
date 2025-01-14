/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.admin.web.internal.security.permission.resource;

import com.liferay.account.model.AccountGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

/**
 * @author Pei-Jung Lan
 */
public class AccountGroupPermission {

	public static boolean contains(
		PermissionChecker permissionChecker, AccountGroup accountGroup,
		String actionId) {

		try {
			ModelResourcePermission<AccountGroup> modelResourcePermission =
				_accountGroupModelResourcePermissionSnapshot.get();

			return modelResourcePermission.contains(
				permissionChecker, accountGroup, actionId);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}
		}

		return false;
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long accountGroupId,
		String actionId) {

		try {
			ModelResourcePermission<AccountGroup> modelResourcePermission =
				_accountGroupModelResourcePermissionSnapshot.get();

			return modelResourcePermission.contains(
				permissionChecker, accountGroupId, actionId);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountGroupPermission.class);

	private static final Snapshot<ModelResourcePermission<AccountGroup>>
		_accountGroupModelResourcePermissionSnapshot = new Snapshot<>(
			AccountGroupPermission.class,
			Snapshot.cast(ModelResourcePermission.class),
			"(model.class.name=com.liferay.account.model.AccountGroup)");

}