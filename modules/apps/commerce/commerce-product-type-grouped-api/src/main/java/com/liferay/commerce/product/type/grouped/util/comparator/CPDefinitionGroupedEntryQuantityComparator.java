/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.grouped.util.comparator;

import com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Andrea Di Giorgi
 */
public class CPDefinitionGroupedEntryQuantityComparator
	extends OrderByComparator<CPDefinitionGroupedEntry> {

	public static final String ORDER_BY_ASC =
		"CPDefinitionGroupedEntry.quantity ASC";

	public static final String ORDER_BY_DESC =
		"CPDefinitionGroupedEntry.quantity DESC";

	public static final String[] ORDER_BY_FIELDS = {"quantity"};

	public static CPDefinitionGroupedEntryQuantityComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		CPDefinitionGroupedEntry cpDefinitionGroupedEntry1,
		CPDefinitionGroupedEntry cpDefinitionGroupedEntry2) {

		int value = Double.compare(
			cpDefinitionGroupedEntry1.getQuantity(),
			cpDefinitionGroupedEntry2.getQuantity());

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

	private CPDefinitionGroupedEntryQuantityComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final CPDefinitionGroupedEntryQuantityComparator
		_INSTANCE_ASCENDING = new CPDefinitionGroupedEntryQuantityComparator(
			true);

	private static final CPDefinitionGroupedEntryQuantityComparator
		_INSTANCE_DESCENDING = new CPDefinitionGroupedEntryQuantityComparator(
			false);

	private final boolean _ascending;

}