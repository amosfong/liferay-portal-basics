/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.inventory.internal.dto.v1_0.converter;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.service.CommerceOrderTypeService;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.OrderType;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Crescenzo Rega
 */
@Component(
	property = "dto.class.name=com.liferay.commerce.model.CommerceOrderType",
	service = DTOConverter.class
)
public class OrderTypeDTOConverter
	implements DTOConverter<CommerceOrderType, OrderType> {

	@Override
	public String getContentType() {
		return OrderType.class.getSimpleName();
	}

	@Override
	public OrderType toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrderType commerceOrderType =
			_commerceOrderTypeService.getCommerceOrderType(
				(Long)dtoConverterContext.getId());

		return new OrderType() {
			{
				setId(commerceOrderType::getCommerceOrderTypeId);
				setName(
					() -> LanguageUtils.getLanguageIdMap(
						commerceOrderType.getNameMap()));
			}
		};
	}

	@Reference
	private CommerceOrderTypeService _commerceOrderTypeService;

}