/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.internal.upgrade.v2_10_1;

import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountRole;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Stefano Motta
 */
public class AccountRoleResourceUpgradeProcess extends UpgradeProcess {

	public AccountRoleResourceUpgradeProcess(
		ResourceActionLocalService resourceActionLocalService,
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_resourceActionLocalService = resourceActionLocalService;
		_resourcePermissionLocalService = resourcePermissionLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		ResourceAction resourceAction =
			_resourceActionLocalService.fetchResourceAction(
				AccountEntry.class.getName(),
				AccountActionKeys.VIEW_ACCOUNT_ROLES);

		if (resourceAction == null) {
			return;
		}

		for (ResourcePermission resourcePermission :
				_resourcePermissionLocalService.getResourcePermissions(
					AccountEntry.class.getName())) {

			if (_resourcePermissionLocalService.hasActionId(
					resourcePermission, resourceAction) &&
				(resourcePermission.getScope() != 4)) {

				_resourcePermissionLocalService.addResourcePermission(
					resourcePermission.getCompanyId(),
					AccountRole.class.getName(), resourcePermission.getScope(),
					resourcePermission.getPrimKey(),
					resourcePermission.getRoleId(), ActionKeys.VIEW);
			}
		}
	}

	private final ResourceActionLocalService _resourceActionLocalService;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;

}