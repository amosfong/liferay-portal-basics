/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalServiceUtil;

/**
 * The extended model base implementation for the LayoutSetBranch service. Represents a row in the &quot;LayoutSetBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutSetBranchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchImpl
 * @see LayoutSetBranch
 * @generated
 */
public abstract class LayoutSetBranchBaseImpl
	extends LayoutSetBranchModelImpl implements LayoutSetBranch {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout set branch model instance should use the <code>LayoutSetBranch</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			LayoutSetBranchLocalServiceUtil.addLayoutSetBranch(this);
		}
		else {
			LayoutSetBranchLocalServiceUtil.updateLayoutSetBranch(this);
		}
	}

}