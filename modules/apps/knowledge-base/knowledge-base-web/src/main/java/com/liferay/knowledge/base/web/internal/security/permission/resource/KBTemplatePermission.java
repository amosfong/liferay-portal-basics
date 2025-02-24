/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.web.internal.security.permission.resource;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

/**
 * @author Preston Crary
 */
public class KBTemplatePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, KBTemplate kbTemplate,
			String actionId)
		throws PortalException {

		ModelResourcePermission<KBTemplate> modelResourcePermission =
			_kbTemplateModelResourcePermissionSnapshot.get();

		return modelResourcePermission.contains(
			permissionChecker, kbTemplate, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long kbTemplateId,
			String actionId)
		throws PortalException {

		ModelResourcePermission<KBTemplate> modelResourcePermission =
			_kbTemplateModelResourcePermissionSnapshot.get();

		return modelResourcePermission.contains(
			permissionChecker, kbTemplateId, actionId);
	}

	private static final Snapshot<ModelResourcePermission<KBTemplate>>
		_kbTemplateModelResourcePermissionSnapshot = new Snapshot<>(
			KBTemplatePermission.class,
			Snapshot.cast(ModelResourcePermission.class),
			"(model.class.name=com.liferay.knowledge.base.model.KBTemplate)");

}