/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.notification.term.provider;

import com.liferay.notification.term.provider.NotificationTermProvider;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

/**
 * @author Balazs Breier
 */
public class CommerceOrderAccountNotificationTermProvider
	implements NotificationTermProvider {

	@Override
	public Map<String, String> getNotificationTerms() {
		return HashMapBuilder.put(
			"commerce-order-account-name", "[%COMMERCEORDER_ACCOUNT_NAME%]"
		).build();
	}

}