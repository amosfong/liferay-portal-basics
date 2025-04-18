/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.util.comparator;

import com.liferay.fragment.model.FragmentComposition;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;

/**
 * @author Attila Bakay
 */
public class FragmentCompositionFragmentEntryModifiedDateComparator
	extends OrderByComparator<Object> {

	public static final String ORDER_BY_ASC = "modifiedDate ASC";

	public static final String ORDER_BY_DESC = "modifiedDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedDate"};

	public static FragmentCompositionFragmentEntryModifiedDateComparator
		getInstance(boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(Object object1, Object object2) {
		int value = DateUtil.compareTo(
			getModifiedDate(object1), getModifiedDate(object2));

		if (_ascending) {
			return value;
		}

		return -value;
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

	protected Date getModifiedDate(Object object) {
		if (object instanceof FragmentComposition) {
			FragmentComposition fragmentComposition =
				(FragmentComposition)object;

			return fragmentComposition.getModifiedDate();
		}

		FragmentEntry fragmentEntry = (FragmentEntry)object;

		return fragmentEntry.getModifiedDate();
	}

	private FragmentCompositionFragmentEntryModifiedDateComparator(
		boolean ascending) {

		_ascending = ascending;
	}

	private static final FragmentCompositionFragmentEntryModifiedDateComparator
		_INSTANCE_ASCENDING =
			new FragmentCompositionFragmentEntryModifiedDateComparator(true);

	private static final FragmentCompositionFragmentEntryModifiedDateComparator
		_INSTANCE_DESCENDING =
			new FragmentCompositionFragmentEntryModifiedDateComparator(false);

	private final boolean _ascending;

}