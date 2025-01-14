/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.warehouse.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.product.service.CommerceChannelRelService;
import com.liferay.commerce.warehouse.web.internal.display.context.CommerceInventoryWarehousesDisplayContext;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joao Victor Alves
 */
@Component(
	property = "screen.navigation.entry.order:Integer=10",
	service = ScreenNavigationEntry.class
)
public class CommerceInventoryWarehouseDetailsScreenNavigationEntry
	extends CommerceInventoryWarehouseDetailsScreenNavigationCategory
	implements ScreenNavigationEntry<CommerceInventoryWarehouse> {

	@Override
	public String getEntryKey() {
		return getCategoryKey();
	}

	@Override
	public boolean isVisible(
		User user, CommerceInventoryWarehouse commerceInventoryWarehouse) {

		if (commerceInventoryWarehouse == null) {
			return false;
		}

		boolean hasPermission = false;

		try {
			hasPermission =
				_commerceInventoryWarehouseModelResourcePermission.contains(
					PermissionThreadLocal.getPermissionChecker(),
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId(),
					ActionKeys.VIEW);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return hasPermission;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		CommerceInventoryWarehousesDisplayContext
			commerceInventoryWarehousesDisplayContext =
				new CommerceInventoryWarehousesDisplayContext(
					_commerceChannelRelService,
					_commerceInventoryWarehouseService, httpServletRequest,
					_portal,
					_commerceInventoryWarehouseModelResourcePermission);

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceInventoryWarehousesDisplayContext);

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/commerce_inventory_warehouse/details.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceInventoryWarehouseDetailsScreenNavigationEntry.class);

	@Reference
	private CommerceChannelRelService _commerceChannelRelService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)"
	)
	private ModelResourcePermission<CommerceInventoryWarehouse>
		_commerceInventoryWarehouseModelResourcePermission;

	@Reference
	private CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

}