/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util.comparator;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Andrea Sbarra
 */
public class CPDefinitionVersionComparator
	extends OrderByComparator<CPDefinition> {

	public static final String ORDER_BY_ASC = "version ASC";

	public static final String ORDER_BY_DESC = "version DESC";

	public static final String[] ORDER_BY_FIELDS = {"version"};

	public static CPDefinitionVersionComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(CPDefinition cpDefinition1, CPDefinition cpDefinition2) {
		int version1 = cpDefinition1.getVersion();
		int version2 = cpDefinition2.getVersion();

		int value = Integer.compare(version1, version2);

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

	private CPDefinitionVersionComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CPDefinitionVersionComparator _INSTANCE_ASCENDING =
		new CPDefinitionVersionComparator(true);

	private static final CPDefinitionVersionComparator _INSTANCE_DESCENDING =
		new CPDefinitionVersionComparator(false);

	private final boolean _ascending;

}