/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.permission.converter;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Permission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.PermissionConversionFilter;

import java.util.List;

/**
 * @author Michael C. Han
 */
public interface PermissionConverter {

	public List<Permission> convertPermissions(Role role)
		throws PortalException;

	public List<Permission> convertPermissions(
			Role role, PermissionConversionFilter permissionConversionFilter)
		throws PortalException;

}