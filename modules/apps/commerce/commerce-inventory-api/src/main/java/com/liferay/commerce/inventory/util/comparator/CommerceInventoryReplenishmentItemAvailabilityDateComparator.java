/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.util.comparator;

import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryReplenishmentItemAvailabilityDateComparator
	extends OrderByComparator<CommerceInventoryReplenishmentItem> {

	public static final String ORDER_BY_ASC = "availabilityDate ASC";

	public static final String ORDER_BY_DESC = "availabilityDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"availabilityDate"};

	public static CommerceInventoryReplenishmentItemAvailabilityDateComparator
		getInstance(boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem1,
		CommerceInventoryReplenishmentItem
			commerceInventoryReplenishmentItem2) {

		int value = DateUtil.compareTo(
			commerceInventoryReplenishmentItem1.getAvailabilityDate(),
			commerceInventoryReplenishmentItem2.getAvailabilityDate());

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

	private CommerceInventoryReplenishmentItemAvailabilityDateComparator(
		boolean ascending) {

		_ascending = ascending;
	}

	private static final
		CommerceInventoryReplenishmentItemAvailabilityDateComparator
			_INSTANCE_ASCENDING =
				new CommerceInventoryReplenishmentItemAvailabilityDateComparator(
					true);

	private static final
		CommerceInventoryReplenishmentItemAvailabilityDateComparator
			_INSTANCE_DESCENDING =
				new CommerceInventoryReplenishmentItemAvailabilityDateComparator(
					false);

	private final boolean _ascending;

}