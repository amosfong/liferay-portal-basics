/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.model.impl;

import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalServiceUtil;

/**
 * The extended model base implementation for the CommercePaymentMethodGroupRel service. Represents a row in the &quot;CommercePaymentMethodGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePaymentMethodGroupRelImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommercePaymentMethodGroupRelImpl
 * @see CommercePaymentMethodGroupRel
 * @generated
 */
public abstract class CommercePaymentMethodGroupRelBaseImpl
	extends CommercePaymentMethodGroupRelModelImpl
	implements CommercePaymentMethodGroupRel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce payment method group rel model instance should use the <code>CommercePaymentMethodGroupRel</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommercePaymentMethodGroupRelLocalServiceUtil.
				addCommercePaymentMethodGroupRel(this);
		}
		else {
			CommercePaymentMethodGroupRelLocalServiceUtil.
				updateCommercePaymentMethodGroupRel(this);
		}
	}

}