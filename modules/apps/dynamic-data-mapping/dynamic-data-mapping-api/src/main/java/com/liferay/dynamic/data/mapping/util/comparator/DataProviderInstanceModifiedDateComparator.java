/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.util.comparator;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Leonardo Barros
 */
public class DataProviderInstanceModifiedDateComparator
	extends OrderByComparator<DDMDataProviderInstance> {

	public static final String ORDER_BY_ASC =
		"DDMDataProviderInstance.modifiedDate ASC";

	public static final String ORDER_BY_DESC =
		"DDMDataProviderInstance.modifiedDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedDate"};

	public static DataProviderInstanceModifiedDateComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		DDMDataProviderInstance ddmDataProviderInstance1,
		DDMDataProviderInstance ddmDataProviderInstance2) {

		int value = DateUtil.compareTo(
			ddmDataProviderInstance1.getModifiedDate(),
			ddmDataProviderInstance2.getModifiedDate());

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

	private DataProviderInstanceModifiedDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final DataProviderInstanceModifiedDateComparator
		_INSTANCE_ASCENDING = new DataProviderInstanceModifiedDateComparator(
			true);

	private static final DataProviderInstanceModifiedDateComparator
		_INSTANCE_DESCENDING = new DataProviderInstanceModifiedDateComparator(
			false);

	private final boolean _ascending;

}