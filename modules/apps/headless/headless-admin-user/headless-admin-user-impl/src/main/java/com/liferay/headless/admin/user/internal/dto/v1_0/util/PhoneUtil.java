/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.internal.dto.v1_0.util;

import com.liferay.headless.admin.user.dto.v1_0.Phone;
import com.liferay.portal.kernel.model.ListType;

/**
 * @author Javier Gamarra
 */
public class PhoneUtil {

	public static Phone toPhone(com.liferay.portal.kernel.model.Phone phone)
		throws Exception {

		return new Phone() {
			{
				setExtension(phone::getExtension);
				setExternalReferenceCode(phone::getExternalReferenceCode);
				setId(phone::getPhoneId);
				setPhoneNumber(phone::getNumber);
				setPhoneType(
					() -> {
						ListType listType = phone.getListType();

						return listType.getName();
					});
				setPrimary(phone::isPrimary);
			}
		};
	}

}