/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.site.setting.internal.resource.v1_0;

import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0.util.DTOMapperUtil;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.WarehouseResource;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/warehouse.properties",
	scope = ServiceScope.PROTOTYPE, service = WarehouseResource.class
)
public class WarehouseResourceImpl extends BaseWarehouseResourceImpl {

	@Override
	public Response deleteWarehouse(Long id) throws Exception {
		_commerceInventoryWarehouseService.deleteCommerceInventoryWarehouse(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Warehouse getWarehouse(Long id) throws Exception {
		return DTOMapperUtil.modelToDTO(
			_commerceInventoryWarehouseService.getCommerceInventoryWarehouse(
				id));
	}

	@Reference
	private CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;

}