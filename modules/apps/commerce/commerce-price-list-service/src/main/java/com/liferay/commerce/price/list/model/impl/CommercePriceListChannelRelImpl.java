/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.model.impl;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalServiceUtil;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListChannelRelImpl
	extends CommercePriceListChannelRelBaseImpl {

	@Override
	public CommerceChannel getCommerceChannel() throws PortalException {
		return CommerceChannelLocalServiceUtil.getCommerceChannel(
			getCommerceChannelId());
	}

	@Override
	public CommercePriceList getCommercePriceList() throws PortalException {
		return CommercePriceListLocalServiceUtil.getCommercePriceList(
			getCommercePriceListId());
	}

}