/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.portal.tools.service.builder.test.model.PermissionCheckFinderEntry;
import com.liferay.portal.tools.service.builder.test.service.PermissionCheckFinderEntryLocalServiceUtil;

/**
 * The extended model base implementation for the PermissionCheckFinderEntry service. Represents a row in the &quot;PermissionCheckFinderEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PermissionCheckFinderEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PermissionCheckFinderEntryImpl
 * @see PermissionCheckFinderEntry
 * @generated
 */
public abstract class PermissionCheckFinderEntryBaseImpl
	extends PermissionCheckFinderEntryModelImpl
	implements PermissionCheckFinderEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a permission check finder entry model instance should use the <code>PermissionCheckFinderEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			PermissionCheckFinderEntryLocalServiceUtil.
				addPermissionCheckFinderEntry(this);
		}
		else {
			PermissionCheckFinderEntryLocalServiceUtil.
				updatePermissionCheckFinderEntry(this);
		}
	}

}