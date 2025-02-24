/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util.comparator;

import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Andrea Di Giorgi
 */
public class CPDefinitionSpecificationOptionValuePriorityComparator
	extends OrderByComparator<CPDefinitionSpecificationOptionValue> {

	public static final String ORDER_BY_ASC = "CPOptionValue.priority ASC";

	public static final String ORDER_BY_DESC = "CPOptionValue.priority DESC";

	public static final String[] ORDER_BY_FIELDS = {"priority"};

	public static CPDefinitionSpecificationOptionValuePriorityComparator
		getInstance(boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue1,
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue2) {

		int value = Double.compare(
			cpDefinitionSpecificationOptionValue1.getPriority(),
			cpDefinitionSpecificationOptionValue2.getPriority());

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

	private CPDefinitionSpecificationOptionValuePriorityComparator(
		boolean ascending) {

		_ascending = ascending;
	}

	private static final CPDefinitionSpecificationOptionValuePriorityComparator
		_INSTANCE_ASCENDING =
			new CPDefinitionSpecificationOptionValuePriorityComparator(true);

	private static final CPDefinitionSpecificationOptionValuePriorityComparator
		_INSTANCE_DESCENDING =
			new CPDefinitionSpecificationOptionValuePriorityComparator(false);

	private final boolean _ascending;

}