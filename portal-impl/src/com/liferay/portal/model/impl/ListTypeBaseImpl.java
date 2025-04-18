/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeLocalServiceUtil;

/**
 * The extended model base implementation for the ListType service. Represents a row in the &quot;ListType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ListTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ListTypeImpl
 * @see ListType
 * @generated
 */
public abstract class ListTypeBaseImpl
	extends ListTypeModelImpl implements ListType {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a list type model instance should use the <code>ListType</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ListTypeLocalServiceUtil.addListType(this);
		}
		else {
			ListTypeLocalServiceUtil.updateListType(this);
		}
	}

}