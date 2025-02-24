/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.util.comparator;

import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBArticleViewCountComparator extends OrderByComparator<KBArticle> {

	public static final String ORDER_BY_ASC = "viewCount ASC";

	public static final String ORDER_BY_DESC = "viewCount DESC";

	public static final String[] ORDER_BY_FIELDS = {"viewCount", "title"};

	public static KBArticleViewCountComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(KBArticle kbArticle1, KBArticle kbArticle2) {
		int value = 0;

		if (kbArticle1.getViewCount() < kbArticle2.getViewCount()) {
			value = -1;
		}
		else if (kbArticle1.getViewCount() > kbArticle2.getViewCount()) {
			value = 1;
		}
		else {
			String title1 = kbArticle1.getTitle();
			String title2 = kbArticle2.getTitle();

			value = title1.compareToIgnoreCase(title2);
		}

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

	private KBArticleViewCountComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final KBArticleViewCountComparator _INSTANCE_ASCENDING =
		new KBArticleViewCountComparator(true);

	private static final KBArticleViewCountComparator _INSTANCE_DESCENDING =
		new KBArticleViewCountComparator(false);

	private final boolean _ascending;

}