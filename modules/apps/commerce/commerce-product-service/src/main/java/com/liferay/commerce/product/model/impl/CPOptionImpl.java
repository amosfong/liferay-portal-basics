/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPOptionValueLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Marco Leo
 */
public class CPOptionImpl extends CPOptionBaseImpl {

	@Override
	public List<CPOptionValue> getCPOptionValues() {
		return CPOptionValueLocalServiceUtil.getCPOptionValues(
			getCPOptionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

}