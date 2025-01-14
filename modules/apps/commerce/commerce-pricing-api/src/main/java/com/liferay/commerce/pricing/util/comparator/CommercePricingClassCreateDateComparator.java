/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.util.comparator;

import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Riccardo Alberti
 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
 */
@Deprecated
public class CommercePricingClassCreateDateComparator
	extends OrderByComparator<CommercePricingClass> {

	public static final String ORDER_BY_ASC = "createDate ASC";

	public static final String ORDER_BY_DESC = "createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static CommercePricingClassCreateDateComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CommercePricingClass commercePricingClass1,
		CommercePricingClass commercePricingClass2) {

		int value = DateUtil.compareTo(
			commercePricingClass1.getCreateDate(),
			commercePricingClass2.getCreateDate());

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

	private CommercePricingClassCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CommercePricingClassCreateDateComparator
		_INSTANCE_ASCENDING = new CommercePricingClassCreateDateComparator(
			true);

	private static final CommercePricingClassCreateDateComparator
		_INSTANCE_DESCENDING = new CommercePricingClassCreateDateComparator(
			false);

	private final boolean _ascending;

}