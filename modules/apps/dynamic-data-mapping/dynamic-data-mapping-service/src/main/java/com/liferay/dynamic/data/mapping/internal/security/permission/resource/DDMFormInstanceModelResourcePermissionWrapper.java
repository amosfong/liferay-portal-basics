/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.security.permission.resource;

import com.liferay.dynamic.data.mapping.constants.DDMConstants;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalService;
import com.liferay.portal.kernel.security.permission.resource.BaseModelResourcePermissionWrapper;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lino Alves
 */
@Component(
	property = "model.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstance",
	service = ModelResourcePermission.class
)
public class DDMFormInstanceModelResourcePermissionWrapper
	extends BaseModelResourcePermissionWrapper<DDMFormInstance> {

	@Override
	protected ModelResourcePermission<DDMFormInstance>
		doGetModelResourcePermission() {

		return ModelResourcePermissionFactory.create(
			DDMFormInstance.class, DDMFormInstance::getFormInstanceId,
			_ddmFormInstanceLocalService::getDDMFormInstance,
			_portletResourcePermission,
			(modelResourcePermission, consumer) -> {
			});
	}

	@Reference
	private DDMFormInstanceLocalService _ddmFormInstanceLocalService;

	@Reference(target = "(resource.name=" + DDMConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}