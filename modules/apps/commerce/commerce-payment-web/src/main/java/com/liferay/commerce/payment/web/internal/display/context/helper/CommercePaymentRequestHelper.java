/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.web.internal.display.context.helper;

import com.liferay.portal.kernel.display.context.helper.BaseRequestHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 */
public class CommercePaymentRequestHelper extends BaseRequestHelper {

	public CommercePaymentRequestHelper(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
	}

}