/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.util.comparator;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarResourceNameComparator
	extends OrderByComparator<CalendarResource> {

	public static final String ORDER_BY_ASC =
		"CalendarResource.name, CalendarResource.code ASC";

	public static final String ORDER_BY_DESC =
		"CalendarResource.name, CalendarResource.code DESC";

	public static final String[] ORDER_BY_FIELDS = {"name", "code"};

	public static CalendarResourceNameComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CalendarResource calendarResource1,
		CalendarResource calendarResource2) {

		String name1 = calendarResource1.getName();
		String name2 = calendarResource2.getName();

		int value = name1.compareTo(name2);

		if (value == 0) {
			String code1 = calendarResource1.getCode();
			String code2 = calendarResource2.getCode();

			value = code1.compareTo(code2);
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

	private CalendarResourceNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CalendarResourceNameComparator _INSTANCE_ASCENDING =
		new CalendarResourceNameComparator(true);

	private static final CalendarResourceNameComparator _INSTANCE_DESCENDING =
		new CalendarResourceNameComparator(false);

	private final boolean _ascending;

}