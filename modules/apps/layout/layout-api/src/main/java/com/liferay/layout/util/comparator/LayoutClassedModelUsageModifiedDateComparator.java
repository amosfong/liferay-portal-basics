/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.util.comparator;

import com.liferay.layout.model.LayoutClassedModelUsage;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Eudaldo Alonso
 */
public class LayoutClassedModelUsageModifiedDateComparator
	extends OrderByComparator<LayoutClassedModelUsage> {

	public static final String ORDER_BY_ASC =
		"LayoutClassedModelUsage.modifiedDate ASC";

	public static final String ORDER_BY_DESC =
		"LayoutClassedModelUsage.modifiedDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedDate"};

	public static LayoutClassedModelUsageModifiedDateComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		LayoutClassedModelUsage layoutClassedModelUsage1,
		LayoutClassedModelUsage layoutClassedModelUsage2) {

		int value = DateUtil.compareTo(
			layoutClassedModelUsage1.getModifiedDate(),
			layoutClassedModelUsage2.getModifiedDate());

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

	private LayoutClassedModelUsageModifiedDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final LayoutClassedModelUsageModifiedDateComparator
		_INSTANCE_ASCENDING = new LayoutClassedModelUsageModifiedDateComparator(
			true);

	private static final LayoutClassedModelUsageModifiedDateComparator
		_INSTANCE_DESCENDING =
			new LayoutClassedModelUsageModifiedDateComparator(false);

	private final boolean _ascending;

}