/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util.comparator;

import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSpecificationOptionModifiedDateComparator
	extends OrderByComparator<CPSpecificationOption> {

	public static final String ORDER_BY_ASC = "modifiedDate ASC";

	public static final String ORDER_BY_DESC = "modifiedDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedDate"};

	public static CPSpecificationOptionModifiedDateComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CPSpecificationOption cpSpecificationOption1,
		CPSpecificationOption cpSpecificationOption2) {

		int value = DateUtil.compareTo(
			cpSpecificationOption1.getModifiedDate(),
			cpSpecificationOption2.getModifiedDate());

		if (_ascending) {
			return value;
		}

		return Math.negateExact(value);
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

	private CPSpecificationOptionModifiedDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CPSpecificationOptionModifiedDateComparator
		_INSTANCE_ASCENDING = new CPSpecificationOptionModifiedDateComparator(
			true);

	private static final CPSpecificationOptionModifiedDateComparator
		_INSTANCE_DESCENDING = new CPSpecificationOptionModifiedDateComparator(
			false);

	private final boolean _ascending;

}