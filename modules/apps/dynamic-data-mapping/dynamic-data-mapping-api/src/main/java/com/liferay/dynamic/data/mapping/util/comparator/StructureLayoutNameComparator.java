/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.util.comparator;

import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Marcela Cunha
 */
public class StructureLayoutNameComparator
	extends OrderByComparator<DDMStructureLayout> {

	public static final String ORDER_BY_ASC = "DDMStructureLayout.name ASC";

	public static final String ORDER_BY_DESC = "DDMStructureLayout.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static StructureLayoutNameComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		DDMStructureLayout ddmStructureLayout1,
		DDMStructureLayout ddmStructureLayout2) {

		String name1 = StringUtil.toLowerCase(ddmStructureLayout1.getName());
		String name2 = StringUtil.toLowerCase(ddmStructureLayout2.getName());

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

	private StructureLayoutNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final StructureLayoutNameComparator _INSTANCE_ASCENDING =
		new StructureLayoutNameComparator(true);

	private static final StructureLayoutNameComparator _INSTANCE_DESCENDING =
		new StructureLayoutNameComparator(false);

	private final boolean _ascending;

}