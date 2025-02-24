/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util.comparator;

import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionLinkPriorityComparator
	extends OrderByComparator<CPDefinitionLink> {

	public static final String ORDER_BY_ASC = "CPDefinitionLink.priority ASC";

	public static final String ORDER_BY_DESC = "CPDefinitionLink.priority DESC";

	public static final String[] ORDER_BY_FIELDS = {"priority"};

	public static CPDefinitionLinkPriorityComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CPDefinitionLink cpDefinitionLink1,
		CPDefinitionLink cpDefinitionLink2) {

		int value = Double.compare(
			cpDefinitionLink1.getPriority(), cpDefinitionLink1.getPriority());

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

	private CPDefinitionLinkPriorityComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CPDefinitionLinkPriorityComparator
		_INSTANCE_ASCENDING = new CPDefinitionLinkPriorityComparator(true);

	private static final CPDefinitionLinkPriorityComparator
		_INSTANCE_DESCENDING = new CPDefinitionLinkPriorityComparator(false);

	private final boolean _ascending;

}