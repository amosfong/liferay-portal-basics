/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.subscription.web.internal.portlet;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.PortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Luca Pellizzon
 */
@Component(
	property = "model.class.name=com.liferay.commerce.model.CommerceSubscriptionEntry",
	service = PortletProvider.class
)
public class CommerceSubscriptionEntryPortletProvider
	extends BasePortletProvider {

	@Override
	public String getPortletName() {
		return CPPortletKeys.COMMERCE_SUBSCRIPTION_ENTRY;
	}

	@Override
	public Action[] getSupportedActions() {
		return _supportedActions;
	}

	private final Action[] _supportedActions = {
		Action.EDIT, Action.MANAGE, Action.VIEW
	};

}