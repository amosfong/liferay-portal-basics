/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util.comparator;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Jorge Ferrer
 */
public class RoleRoleIdComparator extends OrderByComparator<Role> {

	public static final String ORDER_BY_ASC = "Role_.roleId ASC";

	public static final String ORDER_BY_DESC = "Role_.roleId DESC";

	public static final String[] ORDER_BY_FIELDS = {"roleId"};

	public static RoleRoleIdComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(Role role1, Role role2) {
		int value = 0;

		if (role1.getRoleId() > role2.getRoleId()) {
			value = 1;
		}
		else if (role1.getRoleId() < role2.getRoleId()) {
			value = -1;
		}

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private RoleRoleIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final RoleRoleIdComparator _INSTANCE_ASCENDING =
		new RoleRoleIdComparator(true);

	private static final RoleRoleIdComparator _INSTANCE_DESCENDING =
		new RoleRoleIdComparator(false);

	private final boolean _ascending;

}