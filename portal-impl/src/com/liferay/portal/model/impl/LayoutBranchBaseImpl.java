/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.LayoutBranch;
import com.liferay.portal.kernel.service.LayoutBranchLocalServiceUtil;

/**
 * The extended model base implementation for the LayoutBranch service. Represents a row in the &quot;LayoutBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutBranchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranchImpl
 * @see LayoutBranch
 * @generated
 */
public abstract class LayoutBranchBaseImpl
	extends LayoutBranchModelImpl implements LayoutBranch {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout branch model instance should use the <code>LayoutBranch</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			LayoutBranchLocalServiceUtil.addLayoutBranch(this);
		}
		else {
			LayoutBranchLocalServiceUtil.updateLayoutBranch(this);
		}
	}

}