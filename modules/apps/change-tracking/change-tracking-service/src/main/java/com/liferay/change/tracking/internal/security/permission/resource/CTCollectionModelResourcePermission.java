/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.internal.security.permission.resource;

import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(
	property = "model.class.name=com.liferay.change.tracking.model.CTCollection",
	service = ModelResourcePermission.class
)
public class CTCollectionModelResourcePermission
	implements ModelResourcePermission<CTCollection> {

	@Override
	public void check(
			PermissionChecker permissionChecker, CTCollection ctCollection,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ctCollection, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CTCollection.class.getName(),
				ctCollection.getCtCollectionId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long ctCollectionId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ctCollectionId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CTCollection.class.getName(), ctCollectionId,
				actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, CTCollection ctCollection,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				ctCollection.getCompanyId(), CTCollection.class.getName(),
				ctCollection.getCtCollectionId(), ctCollection.getUserId(),
				actionId)) {

			return true;
		}

		Group group = _groupLocalService.fetchGroup(
			ctCollection.getCompanyId(),
			_classNameLocalService.getClassNameId(CTCollection.class),
			ctCollection.getCtCollectionId());

		return permissionChecker.hasPermission(
			group, CTCollection.class.getName(),
			ctCollection.getCtCollectionId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long ctCollectionId,
			String actionId)
		throws PortalException {

		return contains(
			permissionChecker,
			_ctCollectionLocalService.getCTCollection(ctCollectionId),
			actionId);
	}

	@Override
	public String getModelName() {
		return CTCollection.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(target = "(resource.name=" + CTConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}