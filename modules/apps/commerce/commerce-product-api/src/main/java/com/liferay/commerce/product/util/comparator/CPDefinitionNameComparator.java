/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util.comparator;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionNameComparator
	extends OrderByComparator<CPDefinition> {

	public static final String ORDER_BY_ASC =
		"CPDefinitionLocalization.name ASC";

	public static final String ORDER_BY_DESC =
		"CPDefinitionLocalization.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static CPDefinitionNameComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(CPDefinition cpDefinition1, CPDefinition cpDefinition2) {
		String name1 = StringUtil.toLowerCase(cpDefinition1.getName());
		String name2 = StringUtil.toLowerCase(cpDefinition2.getName());

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

	private CPDefinitionNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CPDefinitionNameComparator _INSTANCE_ASCENDING =
		new CPDefinitionNameComparator(true);

	private static final CPDefinitionNameComparator _INSTANCE_DESCENDING =
		new CPDefinitionNameComparator(false);

	private final boolean _ascending;

}