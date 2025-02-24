/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.util.comparator;

import com.liferay.message.boards.model.MBThread;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class ThreadLastPostDateComparator extends OrderByComparator<MBThread> {

	public static final String ORDER_BY_ASC =
		"MBThread.lastPostDate ASC, MBThread.threadId ASC";

	public static final String[] ORDER_BY_CONDITION_FIELDS = {"lastPostDate"};

	public static final String ORDER_BY_DESC =
		"MBThread.lastPostDate DESC, MBThread.threadId DESC";

	public static final String[] ORDER_BY_FIELDS = {"lastPostDate", "threadId"};

	public static ThreadLastPostDateComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(MBThread thread1, MBThread thread2) {
		int value = DateUtil.compareTo(
			thread1.getLastPostDate(), thread2.getLastPostDate());

		if (value == 0) {
			if (thread1.getThreadId() < thread2.getThreadId()) {
				value = -1;
			}
			else if (thread1.getThreadId() > thread2.getThreadId()) {
				value = 1;
			}
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
	public String[] getOrderByConditionFields() {
		return ORDER_BY_CONDITION_FIELDS;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private ThreadLastPostDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final ThreadLastPostDateComparator _INSTANCE_ASCENDING =
		new ThreadLastPostDateComparator(true);

	private static final ThreadLastPostDateComparator _INSTANCE_DESCENDING =
		new ThreadLastPostDateComparator(false);

	private final boolean _ascending;

}