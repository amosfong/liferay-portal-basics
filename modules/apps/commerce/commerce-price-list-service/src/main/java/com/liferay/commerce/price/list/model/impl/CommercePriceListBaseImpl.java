/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.model.impl;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalServiceUtil;

/**
 * The extended model base implementation for the CommercePriceList service. Represents a row in the &quot;CommercePriceList&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePriceListImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListImpl
 * @see CommercePriceList
 * @generated
 */
public abstract class CommercePriceListBaseImpl
	extends CommercePriceListModelImpl implements CommercePriceList {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce price list model instance should use the <code>CommercePriceList</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommercePriceListLocalServiceUtil.addCommercePriceList(this);
		}
		else {
			CommercePriceListLocalServiceUtil.updateCommercePriceList(this);
		}
	}

}