/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.util.comparator;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Lourdes Fernández Besada
 */
public class LayoutModifiedDateComparator extends OrderByComparator<Layout> {

	public static final String ORDER_BY_ASC = "Layout.modifiedDate ASC";

	public static final String ORDER_BY_DESC = "Layout.modifiedDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedDate"};

	public static LayoutModifiedDateComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(Layout layout1, Layout layout2) {
		int value = DateUtil.compareTo(
			layout1.getModifiedDate(), layout2.getModifiedDate());

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

	private LayoutModifiedDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final LayoutModifiedDateComparator _INSTANCE_ASCENDING =
		new LayoutModifiedDateComparator(true);

	private static final LayoutModifiedDateComparator _INSTANCE_DESCENDING =
		new LayoutModifiedDateComparator(false);

	private final boolean _ascending;

}