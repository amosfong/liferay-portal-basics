/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util.comparator;

import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionOptionRelNameComparator
	extends OrderByComparator<CPDefinitionOptionRel> {

	public static final String ORDER_BY_ASC = "CPDefinitionOptionRel.name ASC";

	public static final String ORDER_BY_DESC =
		"CPDefinitionOptionRel.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static CPDefinitionOptionRelNameComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CPDefinitionOptionRel cpDefinitionOptionRel1,
		CPDefinitionOptionRel cpDefinitionOptionRel2) {

		String name1 = StringUtil.toLowerCase(cpDefinitionOptionRel1.getName());
		String name2 = StringUtil.toLowerCase(cpDefinitionOptionRel2.getName());

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

	private CPDefinitionOptionRelNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CPDefinitionOptionRelNameComparator
		_INSTANCE_ASCENDING = new CPDefinitionOptionRelNameComparator(true);

	private static final CPDefinitionOptionRelNameComparator
		_INSTANCE_DESCENDING = new CPDefinitionOptionRelNameComparator(false);

	private final boolean _ascending;

}