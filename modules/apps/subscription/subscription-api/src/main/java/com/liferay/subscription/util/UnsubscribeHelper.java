/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.subscription.util;

import com.liferay.portal.kernel.util.SubscriptionSender;

/**
 * @author Alejandro Tardín
 */
public interface UnsubscribeHelper {

	public void registerHooks(SubscriptionSender subscriptionSender);

}