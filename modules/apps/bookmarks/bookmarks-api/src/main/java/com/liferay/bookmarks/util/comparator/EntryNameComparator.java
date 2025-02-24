/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.util.comparator;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryNameComparator extends OrderByComparator<BookmarksEntry> {

	public static final String ORDER_BY_ASC = "BookmarksEntry.name ASC";

	public static final String ORDER_BY_DESC = "BookmarksEntry.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static EntryNameComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(BookmarksEntry entry1, BookmarksEntry entry2) {
		String name1 = StringUtil.toLowerCase(entry1.getName());
		String name2 = StringUtil.toLowerCase(entry2.getName());

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

	private EntryNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final EntryNameComparator _INSTANCE_ASCENDING =
		new EntryNameComparator(true);

	private static final EntryNameComparator _INSTANCE_DESCENDING =
		new EntryNameComparator(false);

	private final boolean _ascending;

}