/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.internal.security.permission.resource;

import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.portal.kernel.security.permission.resource.BasePortletResourcePermissionWrapper;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 * @author Julius Lee
 */
@Component(
	property = "resource.name=" + CommercePriceListConstants.RESOURCE_NAME,
	service = PortletResourcePermission.class
)
public class CommercePriceListPortletResourcePermissionWrapper
	extends BasePortletResourcePermissionWrapper {

	@Override
	protected PortletResourcePermission doGetPortletResourcePermission() {
		return PortletResourcePermissionFactory.create(
			CommercePriceListConstants.RESOURCE_NAME,
			new PricingPortletResourcePermissionLogic());
	}

}