/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.wiki.model.WikiPage;

/**
 * @author Brian Wing Shun Chan
 */
public class PageCreateDateComparator extends OrderByComparator<WikiPage> {

	public static final String ORDER_BY_ASC = "WikiPage.createDate ASC";

	public static final String ORDER_BY_DESC = "WikiPage.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static PageCreateDateComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(WikiPage page1, WikiPage page2) {
		int value = DateUtil.compareTo(
			page1.getCreateDate(), page2.getCreateDate());

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

	private PageCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final PageCreateDateComparator _INSTANCE_ASCENDING =
		new PageCreateDateComparator(true);

	private static final PageCreateDateComparator _INSTANCE_DESCENDING =
		new PageCreateDateComparator(false);

	private final boolean _ascending;

}