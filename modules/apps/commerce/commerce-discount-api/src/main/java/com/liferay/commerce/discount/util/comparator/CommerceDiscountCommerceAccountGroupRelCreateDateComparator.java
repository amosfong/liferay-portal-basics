/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.util.comparator;

import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Marco Leo
 */
public class CommerceDiscountCommerceAccountGroupRelCreateDateComparator
	extends OrderByComparator<CommerceDiscountCommerceAccountGroupRel> {

	public static final String ORDER_BY_ASC = "createDate ASC";

	public static final String ORDER_BY_DESC = "createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static CommerceDiscountCommerceAccountGroupRelCreateDateComparator
		getInstance(boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel1,
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel2) {

		int value = DateUtil.compareTo(
			commerceDiscountCommerceAccountGroupRel1.getCreateDate(),
			commerceDiscountCommerceAccountGroupRel2.getCreateDate());

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

	private CommerceDiscountCommerceAccountGroupRelCreateDateComparator(
		boolean ascending) {

		_ascending = ascending;
	}

	private static final
		CommerceDiscountCommerceAccountGroupRelCreateDateComparator
			_INSTANCE_ASCENDING =
				new CommerceDiscountCommerceAccountGroupRelCreateDateComparator(
					true);

	private static final
		CommerceDiscountCommerceAccountGroupRelCreateDateComparator
			_INSTANCE_DESCENDING =
				new CommerceDiscountCommerceAccountGroupRelCreateDateComparator(
					false);

	private final boolean _ascending;

}