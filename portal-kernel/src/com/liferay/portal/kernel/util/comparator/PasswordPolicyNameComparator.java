/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util.comparator;

import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class PasswordPolicyNameComparator
	extends OrderByComparator<PasswordPolicy> {

	public static final String ORDER_BY_ASC = "PasswordPolicy.name ASC";

	public static final String ORDER_BY_DESC = "PasswordPolicy.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static PasswordPolicyNameComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		PasswordPolicy passwordPolicy1, PasswordPolicy passwordPolicy2) {

		String name1 = passwordPolicy1.getName();
		String name2 = passwordPolicy2.getName();

		int value = name1.compareTo(name2);

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

	private PasswordPolicyNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final PasswordPolicyNameComparator _INSTANCE_ASCENDING =
		new PasswordPolicyNameComparator(true);

	private static final PasswordPolicyNameComparator _INSTANCE_DESCENDING =
		new PasswordPolicyNameComparator(false);

	private final boolean _ascending;

}