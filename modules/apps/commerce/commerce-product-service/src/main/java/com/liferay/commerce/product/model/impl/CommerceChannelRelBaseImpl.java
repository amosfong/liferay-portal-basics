/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.CommerceChannelRelLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceChannelRel service. Represents a row in the &quot;CommerceChannelRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceChannelRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelRelImpl
 * @see CommerceChannelRel
 * @generated
 */
public abstract class CommerceChannelRelBaseImpl
	extends CommerceChannelRelModelImpl implements CommerceChannelRel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce channel rel model instance should use the <code>CommerceChannelRel</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceChannelRelLocalServiceUtil.addCommerceChannelRel(this);
		}
		else {
			CommerceChannelRelLocalServiceUtil.updateCommerceChannelRel(this);
		}
	}

}