/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.util.comparator;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Daniel Kocsis
 */
public class BackgroundTaskNameComparator
	extends OrderByComparator<BackgroundTask> {

	public static final String ORDER_BY_ASC = "BackgroundTask.name ASC";

	public static final String ORDER_BY_DESC = "BackgroundTask.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static BackgroundTaskNameComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		BackgroundTask backgroundTask1, BackgroundTask backgroundTask2) {

		String name1 = backgroundTask1.getName();
		String name2 = backgroundTask2.getName();

		int value = name1.compareToIgnoreCase(name2);

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

	private BackgroundTaskNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final BackgroundTaskNameComparator _INSTANCE_ASCENDING =
		new BackgroundTaskNameComparator(true);

	private static final BackgroundTaskNameComparator _INSTANCE_DESCENDING =
		new BackgroundTaskNameComparator(false);

	private final boolean _ascending;

}