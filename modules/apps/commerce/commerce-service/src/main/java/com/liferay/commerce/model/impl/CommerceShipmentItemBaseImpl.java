/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.service.CommerceShipmentItemLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceShipmentItem service. Represents a row in the &quot;CommerceShipmentItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceShipmentItemImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentItemImpl
 * @see CommerceShipmentItem
 * @generated
 */
public abstract class CommerceShipmentItemBaseImpl
	extends CommerceShipmentItemModelImpl implements CommerceShipmentItem {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce shipment item model instance should use the <code>CommerceShipmentItem</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceShipmentItemLocalServiceUtil.addCommerceShipmentItem(this);
		}
		else {
			CommerceShipmentItemLocalServiceUtil.updateCommerceShipmentItem(
				this);
		}
	}

}