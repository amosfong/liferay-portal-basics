/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.model.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.service.CommerceInventoryBookedQuantityLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceInventoryBookedQuantity service. Represents a row in the &quot;CIBookedQuantity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceInventoryBookedQuantityImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryBookedQuantityImpl
 * @see CommerceInventoryBookedQuantity
 * @generated
 */
public abstract class CommerceInventoryBookedQuantityBaseImpl
	extends CommerceInventoryBookedQuantityModelImpl
	implements CommerceInventoryBookedQuantity {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce inventory booked quantity model instance should use the <code>CommerceInventoryBookedQuantity</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceInventoryBookedQuantityLocalServiceUtil.
				addCommerceInventoryBookedQuantity(this);
		}
		else {
			CommerceInventoryBookedQuantityLocalServiceUtil.
				updateCommerceInventoryBookedQuantity(this);
		}
	}

}