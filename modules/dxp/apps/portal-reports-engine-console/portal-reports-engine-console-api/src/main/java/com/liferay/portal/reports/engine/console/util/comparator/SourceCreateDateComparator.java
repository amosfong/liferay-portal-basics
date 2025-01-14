/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.reports.engine.console.model.Source;

/**
 * @author Rafael Praxedes
 */
public class SourceCreateDateComparator extends OrderByComparator<Source> {

	public static final String ORDER_BY_ASC = "Reports_Source.createDate ASC";

	public static final String ORDER_BY_DESC = "Reports_Source.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static SourceCreateDateComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(Source source1, Source source2) {
		int value = DateUtil.compareTo(
			source1.getCreateDate(), source2.getCreateDate());

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

	private SourceCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final SourceCreateDateComparator _INSTANCE_ASCENDING =
		new SourceCreateDateComparator(true);

	private static final SourceCreateDateComparator _INSTANCE_DESCENDING =
		new SourceCreateDateComparator(false);

	private final boolean _ascending;

}