/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.VirtualHost;
import com.liferay.portal.kernel.service.VirtualHostLocalServiceUtil;

/**
 * The extended model base implementation for the VirtualHost service. Represents a row in the &quot;VirtualHost&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VirtualHostImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHostImpl
 * @see VirtualHost
 * @generated
 */
public abstract class VirtualHostBaseImpl
	extends VirtualHostModelImpl implements VirtualHost {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a virtual host model instance should use the <code>VirtualHost</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			VirtualHostLocalServiceUtil.addVirtualHost(this);
		}
		else {
			VirtualHostLocalServiceUtil.updateVirtualHost(this);
		}
	}

}