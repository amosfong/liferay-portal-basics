/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.model.impl;

import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 */
public class CPDVirtualSettingFileEntryImpl
	extends CPDVirtualSettingFileEntryBaseImpl {

	@Override
	public CPDefinitionVirtualSetting getCPDefinitionVirtualSetting()
		throws PortalException {

		return CPDefinitionVirtualSettingLocalServiceUtil.
			getCPDefinitionVirtualSetting(getCPDefinitionVirtualSettingId());
	}

}