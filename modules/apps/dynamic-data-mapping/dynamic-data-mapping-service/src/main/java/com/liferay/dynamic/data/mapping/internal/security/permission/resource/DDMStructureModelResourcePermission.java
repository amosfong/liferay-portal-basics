/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.security.permission.resource;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.security.permission.DDMPermissionSupport;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	property = "model.class.name=com.liferay.dynamic.data.mapping.model.DDMStructure",
	service = ModelResourcePermission.class
)
public class DDMStructureModelResourcePermission
	implements ModelResourcePermission<DDMStructure> {

	@Override
	public void check(
			PermissionChecker permissionChecker, DDMStructure structure,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, structure, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				_ddmPermissionSupport.getStructureModelResourceName(
					structure.getClassName()),
				structure.getStructureId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		DDMStructure structure = _ddmStructureLocalService.getDDMStructure(
			primaryKey);

		check(permissionChecker, structure, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, DDMStructure structure,
			String actionId)
		throws PortalException {

		String structureModelResourceName =
			_ddmPermissionSupport.getStructureModelResourceName(
				structure.getClassName());

		if (permissionChecker.hasOwnerPermission(
				structure.getCompanyId(), structureModelResourceName,
				structure.getStructureId(), structure.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			structure.getGroupId(), structureModelResourceName,
			structure.getStructureId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		DDMStructure structure = _ddmStructureLocalService.getDDMStructure(
			primaryKey);

		return contains(permissionChecker, structure, actionId);
	}

	@Override
	public String getModelName() {
		return DDMStructure.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private DDMPermissionSupport _ddmPermissionSupport;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

}