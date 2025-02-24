/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.util.comparator;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * Used to order record sets according to their created date during search
 * operations. The order can be ascending or descending and is defined by the
 * value specified in the class constructor.
 *
 * @author Rafael Praxedes
 * @see    com.liferay.dynamic.data.lists.service.DDLRecordSetService#search(
 *         long, long, String, int, int, int, OrderByComparator)
 */
public class DDLRecordSetCreateDateComparator
	extends OrderByComparator<DDLRecordSet> {

	public static final String ORDER_BY_ASC = "DDLRecordSet.createDate ASC";

	public static final String ORDER_BY_DESC = "DDLRecordSet.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static DDLRecordSetCreateDateComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(DDLRecordSet ddlRecordSet1, DDLRecordSet ddlRecordSet2) {
		int value = DateUtil.compareTo(
			ddlRecordSet1.getCreateDate(), ddlRecordSet2.getCreateDate());

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

	private DDLRecordSetCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final DDLRecordSetCreateDateComparator _INSTANCE_ASCENDING =
		new DDLRecordSetCreateDateComparator(true);

	private static final DDLRecordSetCreateDateComparator _INSTANCE_DESCENDING =
		new DDLRecordSetCreateDateComparator(false);

	private final boolean _ascending;

}