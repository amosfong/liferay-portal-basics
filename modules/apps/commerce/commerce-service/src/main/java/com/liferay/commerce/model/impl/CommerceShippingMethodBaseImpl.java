/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.CommerceShippingMethodLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceShippingMethod service. Represents a row in the &quot;CommerceShippingMethod&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceShippingMethodImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingMethodImpl
 * @see CommerceShippingMethod
 * @generated
 */
public abstract class CommerceShippingMethodBaseImpl
	extends CommerceShippingMethodModelImpl implements CommerceShippingMethod {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce shipping method model instance should use the <code>CommerceShippingMethod</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceShippingMethodLocalServiceUtil.addCommerceShippingMethod(
				this);
		}
		else {
			CommerceShippingMethodLocalServiceUtil.updateCommerceShippingMethod(
				this);
		}
	}

}