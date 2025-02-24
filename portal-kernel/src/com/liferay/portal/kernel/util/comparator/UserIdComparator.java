/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util.comparator;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class UserIdComparator extends OrderByComparator<User> {

	public static final String ORDER_BY_ASC = "User_.userId ASC";

	public static final String ORDER_BY_DESC = "User_.userId DESC";

	public static final String[] ORDER_BY_FIELDS = {"userId"};

	public static UserIdComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(User userGroup1, User userGroup2) {
		long userId1 = userGroup1.getUserId();
		long userId2 = userGroup2.getUserId();

		int value = 0;

		if (userId1 < userId2) {
			value = -1;
		}
		else if (userId1 > userId2) {
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

	private UserIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final UserIdComparator _INSTANCE_ASCENDING =
		new UserIdComparator(true);

	private static final UserIdComparator _INSTANCE_DESCENDING =
		new UserIdComparator(false);

	private final boolean _ascending;

}