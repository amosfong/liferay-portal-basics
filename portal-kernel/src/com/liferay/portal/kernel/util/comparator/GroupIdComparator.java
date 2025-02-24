/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util.comparator;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Shinn Lok
 */
public class GroupIdComparator extends OrderByComparator<Group> {

	public static final String ORDER_BY_ASC = "Group_.groupId ASC";

	public static final String ORDER_BY_DESC = "Group_.groupId DESC";

	public static final String[] ORDER_BY_FIELDS = {"groupId"};

	public static GroupIdComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(Group group1, Group group2) {
		long groupId1 = group1.getGroupId();
		long groupId2 = group2.getGroupId();

		int value = 0;

		if (groupId1 < groupId2) {
			value = -1;
		}
		else if (groupId1 > groupId2) {
			value = 1;
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

	private GroupIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final GroupIdComparator _INSTANCE_ASCENDING =
		new GroupIdComparator(true);

	private static final GroupIdComparator _INSTANCE_DESCENDING =
		new GroupIdComparator(false);

	private final boolean _ascending;

}