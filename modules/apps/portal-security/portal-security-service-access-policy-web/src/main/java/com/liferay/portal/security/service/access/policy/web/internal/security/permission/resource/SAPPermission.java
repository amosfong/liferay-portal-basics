/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.service.access.policy.web.internal.security.permission.resource;

import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.security.service.access.policy.constants.SAPConstants;

/**
 * @author Preston Crary
 */
public class SAPPermission {

	public static boolean contains(
		PermissionChecker permissionChecker, String actionId) {

		PortletResourcePermission portletResourcePermission =
			_portletResourcePermissionSnapshot.get();

		return portletResourcePermission.contains(
			permissionChecker, null, actionId);
	}

	private static final Snapshot<PortletResourcePermission>
		_portletResourcePermissionSnapshot = new Snapshot<>(
			SAPPermission.class, PortletResourcePermission.class,
			"(resource.name=" + SAPConstants.RESOURCE_NAME + ")");

}