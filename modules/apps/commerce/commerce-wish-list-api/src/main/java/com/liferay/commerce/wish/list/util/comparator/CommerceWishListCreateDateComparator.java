/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.wish.list.util.comparator;

import com.liferay.commerce.wish.list.model.CommerceWishList;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceWishListCreateDateComparator
	extends OrderByComparator<CommerceWishList> {

	public static final String ORDER_BY_ASC = "createDate ASC";

	public static final String ORDER_BY_DESC = "createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static CommerceWishListCreateDateComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CommerceWishList commerceWishList1,
		CommerceWishList commerceWishList2) {

		int value = DateUtil.compareTo(
			commerceWishList1.getCreateDate(),
			commerceWishList2.getCreateDate());

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

	private CommerceWishListCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CommerceWishListCreateDateComparator
		_INSTANCE_ASCENDING = new CommerceWishListCreateDateComparator(true);

	private static final CommerceWishListCreateDateComparator
		_INSTANCE_DESCENDING = new CommerceWishListCreateDateComparator(false);

	private final boolean _ascending;

}