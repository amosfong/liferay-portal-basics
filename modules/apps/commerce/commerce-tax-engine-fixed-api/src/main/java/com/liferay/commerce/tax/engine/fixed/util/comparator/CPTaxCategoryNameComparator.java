/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.engine.fixed.util.comparator;

import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Marco Leo
 */
public class CPTaxCategoryNameComparator
	extends OrderByComparator<CPTaxCategory> {

	public static final String ORDER_BY_ASC = "CPTaxCategory.name ASC";

	public static final String ORDER_BY_DESC = "CPTaxCategory.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static CPTaxCategoryNameComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CPTaxCategory cpTaxCategory1, CPTaxCategory cpTaxCategory2) {

		String name1 = StringUtil.toLowerCase(cpTaxCategory1.getName());
		String name2 = StringUtil.toLowerCase(cpTaxCategory2.getName());

		int value = name1.compareTo(name2);

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

	private CPTaxCategoryNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CPTaxCategoryNameComparator _INSTANCE_ASCENDING =
		new CPTaxCategoryNameComparator(true);

	private static final CPTaxCategoryNameComparator _INSTANCE_DESCENDING =
		new CPTaxCategoryNameComparator(false);

	private final boolean _ascending;

}