/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.web.internal.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.segments.model.SegmentsEntry;

/**
 * @author Eduardo García
 */
public class SegmentsEntryNameComparator
	extends OrderByComparator<SegmentsEntry> {

	public static final String ORDER_BY_ASC = "SegmentsEntry.name ASC";

	public static final String ORDER_BY_DESC = "SegmentsEntry.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static SegmentsEntryNameComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		SegmentsEntry segmentsEntry1, SegmentsEntry segmentsEntry2) {

		String name1 = StringUtil.toLowerCase(segmentsEntry1.getName());
		String name2 = StringUtil.toLowerCase(segmentsEntry2.getName());

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

	private SegmentsEntryNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final SegmentsEntryNameComparator _INSTANCE_ASCENDING =
		new SegmentsEntryNameComparator(true);

	private static final SegmentsEntryNameComparator _INSTANCE_DESCENDING =
		new SegmentsEntryNameComparator(false);

	private final boolean _ascending;

}