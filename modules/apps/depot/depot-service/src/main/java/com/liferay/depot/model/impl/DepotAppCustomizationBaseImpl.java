/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.depot.model.impl;

import com.liferay.depot.model.DepotAppCustomization;
import com.liferay.depot.service.DepotAppCustomizationLocalServiceUtil;

/**
 * The extended model base implementation for the DepotAppCustomization service. Represents a row in the &quot;DepotAppCustomization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DepotAppCustomizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DepotAppCustomizationImpl
 * @see DepotAppCustomization
 * @generated
 */
public abstract class DepotAppCustomizationBaseImpl
	extends DepotAppCustomizationModelImpl implements DepotAppCustomization {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a depot app customization model instance should use the <code>DepotAppCustomization</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			DepotAppCustomizationLocalServiceUtil.addDepotAppCustomization(
				this);
		}
		else {
			DepotAppCustomizationLocalServiceUtil.updateDepotAppCustomization(
				this);
		}
	}

}