/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.engine.fixed.util.comparator;

import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRateAddressRel;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceTaxFixedRateAddressRelCreateDateComparator
	extends OrderByComparator<CommerceTaxFixedRateAddressRel> {

	public static final String ORDER_BY_ASC = "createDate ASC";

	public static final String ORDER_BY_DESC = "createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static CommerceTaxFixedRateAddressRelCreateDateComparator
		getInstance(boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CommerceTaxFixedRateAddressRel commerceTaxFixedRateAddressRel1,
		CommerceTaxFixedRateAddressRel commerceTaxFixedRateAddressRel2) {

		int value = DateUtil.compareTo(
			commerceTaxFixedRateAddressRel1.getCreateDate(),
			commerceTaxFixedRateAddressRel2.getCreateDate());

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

	private CommerceTaxFixedRateAddressRelCreateDateComparator(
		boolean ascending) {

		_ascending = ascending;
	}

	private static final CommerceTaxFixedRateAddressRelCreateDateComparator
		_INSTANCE_ASCENDING =
			new CommerceTaxFixedRateAddressRelCreateDateComparator(true);

	private static final CommerceTaxFixedRateAddressRelCreateDateComparator
		_INSTANCE_DESCENDING =
			new CommerceTaxFixedRateAddressRelCreateDateComparator(false);

	private final boolean _ascending;

}