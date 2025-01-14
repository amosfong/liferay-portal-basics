/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.admin.web.internal.security.permission.resource;

import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

/**
 * @author Preston Crary
 */
public class LayoutPageTemplateCollectionPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			LayoutPageTemplateCollection layoutPageTemplateCollection,
			String actionId)
		throws PortalException {

		ModelResourcePermission<LayoutPageTemplateCollection>
			modelResourcePermission =
				_layoutPageTemplateCollectionModelResourcePermissionSnapshot.
					get();

		return modelResourcePermission.contains(
			permissionChecker, layoutPageTemplateCollection, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			long layoutPageTemplateCollectionId, String actionId)
		throws PortalException {

		ModelResourcePermission<LayoutPageTemplateCollection>
			modelResourcePermission =
				_layoutPageTemplateCollectionModelResourcePermissionSnapshot.
					get();

		return modelResourcePermission.contains(
			permissionChecker, layoutPageTemplateCollectionId, actionId);
	}

	private static final Snapshot
		<ModelResourcePermission<LayoutPageTemplateCollection>>
			_layoutPageTemplateCollectionModelResourcePermissionSnapshot =
				new Snapshot<>(
					LayoutPageTemplateCollectionPermission.class,
					Snapshot.cast(ModelResourcePermission.class),
					"(model.class.name=com.liferay.layout.page.template." +
						"model.LayoutPageTemplateCollection)");

}