/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util.comparator;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class RoleTypeComparator extends OrderByComparator<Role> {

	public static final String ORDER_BY_ASC = "Role_.type_ ASC, Role_.name ASC";

	public static final String ORDER_BY_DESC =
		"Role_.type_ DESC, Role_.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"type", "name"};

	public static RoleTypeComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(Role role1, Role role2) {
		int value = 0;

		if (role1.getType() > role2.getType()) {
			value = 1;
		}
		else if (role1.getType() < role2.getType()) {
			value = -1;
		}

		if (value == 0) {
			String name1 = role1.getName();
			String name2 = role2.getName();

			value = name1.compareTo(name2);
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

	private RoleTypeComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final RoleTypeComparator _INSTANCE_ASCENDING =
		new RoleTypeComparator(true);

	private static final RoleTypeComparator _INSTANCE_DESCENDING =
		new RoleTypeComparator(false);

	private final boolean _ascending;

}