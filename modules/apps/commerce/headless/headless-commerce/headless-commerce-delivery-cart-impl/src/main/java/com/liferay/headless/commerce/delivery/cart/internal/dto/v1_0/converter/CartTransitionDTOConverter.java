/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.cart.internal.dto.v1_0.converter;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.headless.commerce.delivery.cart.dto.v1_0.CartTransition;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "dto.class.name=com.liferay.headless.commerce.delivery.cart.dto.v1_0.CartTransition",
	service = DTOConverter.class
)
public class CartTransitionDTOConverter
	implements DTOConverter<CommerceOrder, CartTransition> {

	@Override
	public String getContentType() {
		return CartTransition.class.getSimpleName();
	}

	@Override
	public CartTransition toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			(Long)dtoConverterContext.getId());
		ObjectValuePair<Long, String> transitionOVP =
			(ObjectValuePair<Long, String>)dtoConverterContext.getAttribute(
				"transitionOVP");

		return new CartTransition() {
			{
				setCartId(commerceOrder::getCommerceOrderId);
				setComment(
					() -> (String)dtoConverterContext.getAttribute("comment"));
				setLabel(
					() -> _language.get(
						dtoConverterContext.getLocale(),
						transitionOVP.getValue()));
				setName(transitionOVP::getValue);
				setOpen(commerceOrder::isOpen);
				setWorkflowTaskId(transitionOVP::getKey);
			}
		};
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private Language _language;

}