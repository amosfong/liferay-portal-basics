/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.address;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

/**
 * @author Alec Sloan
 */
public interface CommerceAddressFormatter {

	public String getBasicAddress(
			CommerceAddress commerceAddress, Locale locale)
		throws PortalException;

	public String getDescriptiveAddress(
			CommerceAddress commerceAddress, Locale locale,
			boolean showDescription)
		throws PortalException;

	public String getOneLineAddress(CommerceAddress commerceAddress)
		throws PortalException;

}