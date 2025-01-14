/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.site.navigation.model.SiteNavigationMenu;

/**
 * @author Pavel Savinov
 */
public class SiteNavigationMenuNameComparator
	extends OrderByComparator<SiteNavigationMenu> {

	public static final String ORDER_BY_ASC = "SiteNavigationMenu.name ASC";

	public static final String ORDER_BY_DESC = "SiteNavigationMenu.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public static SiteNavigationMenuNameComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		SiteNavigationMenu siteNavigationMenu1,
		SiteNavigationMenu siteNavigationMenu2) {

		String name1 = siteNavigationMenu1.getName();
		String name2 = siteNavigationMenu2.getName();

		int value = name1.compareTo(name2);

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

	private SiteNavigationMenuNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final SiteNavigationMenuNameComparator _INSTANCE_ASCENDING =
		new SiteNavigationMenuNameComparator(true);

	private static final SiteNavigationMenuNameComparator _INSTANCE_DESCENDING =
		new SiteNavigationMenuNameComparator(false);

	private final boolean _ascending;

}