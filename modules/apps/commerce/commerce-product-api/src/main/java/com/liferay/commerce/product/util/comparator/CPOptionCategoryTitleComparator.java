/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util.comparator;

import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CPOptionCategoryTitleComparator
	extends OrderByComparator<CPOptionCategory> {

	public static final String ORDER_BY_ASC = "CPOptionCategory.title ASC";

	public static final String ORDER_BY_DESC = "CPOptionCategory.title DESC";

	public static final String[] ORDER_BY_FIELDS = {"title"};

	public static CPOptionCategoryTitleComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CPOptionCategory cpOptionCategory1,
		CPOptionCategory cpOptionCategory2) {

		String title1 = StringUtil.toLowerCase(cpOptionCategory1.getTitle());
		String title2 = StringUtil.toLowerCase(cpOptionCategory2.getTitle());

		int value = title1.compareTo(title2);

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

	private CPOptionCategoryTitleComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CPOptionCategoryTitleComparator _INSTANCE_ASCENDING =
		new CPOptionCategoryTitleComparator(true);

	private static final CPOptionCategoryTitleComparator _INSTANCE_DESCENDING =
		new CPOptionCategoryTitleComparator(false);

	private final boolean _ascending;

}