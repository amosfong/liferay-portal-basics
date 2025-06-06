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
public class UserJobTitleComparator extends OrderByComparator<User> {

	public static final String ORDER_BY_ASC =
		"jobTitle ASC, lastName ASC, firstName ASC, middleName ASC";

	public static final String ORDER_BY_DESC =
		"jobTitle DESC, lastName DESC, firstName DESC, middleName DESC";

	public static final String[] ORDER_BY_FIELDS = {
		"jobTitle", "lastName", "firstName", "middleName"
	};

	public static UserJobTitleComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(User user1, User user2) {
		String jobTitle1 = user1.getJobTitle();
		String jobTitle2 = user2.getJobTitle();

		int value = jobTitle1.compareTo(jobTitle2);

		if (value == 0) {
			String lastName1 = user1.getLastName();
			String lastName2 = user2.getLastName();

			value = lastName1.compareTo(lastName2);
		}

		if (value == 0) {
			String firstName1 = user1.getFirstName();
			String firstName2 = user2.getFirstName();

			value = firstName1.compareTo(firstName2);
		}

		if (value == 0) {
			String middleName1 = user1.getMiddleName();
			String middleName2 = user2.getMiddleName();

			value = middleName1.compareTo(middleName2);
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

	private UserJobTitleComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final UserJobTitleComparator _INSTANCE_ASCENDING =
		new UserJobTitleComparator(true);

	private static final UserJobTitleComparator _INSTANCE_DESCENDING =
		new UserJobTitleComparator(false);

	private final boolean _ascending;

}