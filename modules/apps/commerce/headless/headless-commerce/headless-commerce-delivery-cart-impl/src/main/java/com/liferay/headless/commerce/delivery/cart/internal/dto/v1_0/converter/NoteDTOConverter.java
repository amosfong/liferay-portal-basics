/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.cart.internal.dto.v1_0.converter;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.headless.commerce.delivery.cart.dto.v1_0.CartComment;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Sbarra
 */
@Component(
	property = "dto.class.name=com.liferay.commerce.model.CommerceOrderNote",
	service = DTOConverter.class
)
public class NoteDTOConverter
	implements DTOConverter<CommerceOrderNote, CartComment> {

	@Override
	public String getContentType() {
		return CartComment.class.getSimpleName();
	}

	@Override
	public CartComment toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrderNote commerceOrderNote =
			_commerceOrderNoteService.getCommerceOrderNote(
				(Long)dtoConverterContext.getId());

		return new CartComment() {
			{
				setAuthor(commerceOrderNote::getUserName);
				setAuthorId(commerceOrderNote::getUserId);
				setAuthorPortraitURL(
					() -> {
						User user = commerceOrderNote.getUser();

						return UserConstants.getPortraitURL(
							_portal.getPathImage(), user.isMale(),
							user.getPortraitId(), user.getUserUuid());
					});
				setContent(commerceOrderNote::getContent);
				setExternalReferenceCode(
					commerceOrderNote::getExternalReferenceCode);
				setId(commerceOrderNote::getCommerceOrderNoteId);
				setModifiedDate(commerceOrderNote::getModifiedDate);
				setOrderId(
					() -> {
						CommerceOrder commerceOrder =
							_commerceOrderService.getCommerceOrder(
								commerceOrderNote.getCommerceOrderId());

						return commerceOrder.getCommerceOrderId();
					});
				setRestricted(commerceOrderNote::isRestricted);
			}
		};
	}

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private Portal _portal;

}