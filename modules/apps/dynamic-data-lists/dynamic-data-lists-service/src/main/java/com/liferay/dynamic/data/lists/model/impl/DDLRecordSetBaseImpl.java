/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.model.impl;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;

/**
 * The extended model base implementation for the DDLRecordSet service. Represents a row in the &quot;DDLRecordSet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDLRecordSetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordSetImpl
 * @see DDLRecordSet
 * @generated
 */
public abstract class DDLRecordSetBaseImpl
	extends DDLRecordSetModelImpl implements DDLRecordSet {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddl record set model instance should use the <code>DDLRecordSet</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			DDLRecordSetLocalServiceUtil.addDDLRecordSet(this);
		}
		else {
			DDLRecordSetLocalServiceUtil.updateDDLRecordSet(this);
		}
	}

}