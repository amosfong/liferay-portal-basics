/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.CPTaxCategoryLocalServiceUtil;

/**
 * The extended model base implementation for the CPTaxCategory service. Represents a row in the &quot;CPTaxCategory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPTaxCategoryImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPTaxCategoryImpl
 * @see CPTaxCategory
 * @generated
 */
public abstract class CPTaxCategoryBaseImpl
	extends CPTaxCategoryModelImpl implements CPTaxCategory {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp tax category model instance should use the <code>CPTaxCategory</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CPTaxCategoryLocalServiceUtil.addCPTaxCategory(this);
		}
		else {
			CPTaxCategoryLocalServiceUtil.updateCPTaxCategory(this);
		}
	}

}