/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v11_3_1;

import com.liferay.account.model.AccountEntry;
import com.liferay.commerce.constants.CommerceAccountActionKeys;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Stefano Motta
 */
public class CommercePermissionUpgradeProcess extends UpgradeProcess {

	public CommercePermissionUpgradeProcess(
		ResourceActionLocalService resourceActionLocalService,
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_resourceActionLocalService = resourceActionLocalService;
		_resourcePermissionLocalService = resourcePermissionLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		ResourceAction resourceAction =
			_resourceActionLocalService.fetchResourceAction(
				AccountEntry.class.getName(), ActionKeys.UPDATE);

		if (resourceAction == null) {
			return;
		}

		for (ResourcePermission resourcePermission :
				_resourcePermissionLocalService.getResourcePermissions(
					AccountEntry.class.getName())) {

			if (_resourcePermissionLocalService.hasActionId(
					resourcePermission, resourceAction) &&
				(resourcePermission.getScope() !=
					ResourceConstants.SCOPE_INDIVIDUAL)) {

				_resourcePermissionLocalService.addResourcePermission(
					resourcePermission.getCompanyId(),
					AccountEntry.class.getName(), resourcePermission.getScope(),
					resourcePermission.getPrimKey(),
					resourcePermission.getRoleId(),
					CommerceAccountActionKeys.MANAGE_CHANNEL_DEFAULTS);
				_resourcePermissionLocalService.addResourcePermission(
					resourcePermission.getCompanyId(),
					AccountEntry.class.getName(), resourcePermission.getScope(),
					resourcePermission.getPrimKey(),
					resourcePermission.getRoleId(),
					CommerceAccountActionKeys.VIEW_CHANNEL_DEFAULTS);
			}
		}
	}

	private final ResourceActionLocalService _resourceActionLocalService;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;

}