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
public class KBArticleStatusComparator extends OrderByComparator<KBArticle> {

	public static final String ORDER_BY_ASC = "KBArticle.status ASC";

	public static final String ORDER_BY_DESC = "KBArticle.status DESC";

	public static final String[] ORDER_BY_FIELDS = {"status", "title"};

	public static KBArticleStatusComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(KBArticle kbArticle1, KBArticle kbArticle2) {
		int value = 0;

		if (kbArticle1.getStatus() < kbArticle2.getStatus()) {
			value = -1;
		}
		else if (kbArticle1.getStatus() > kbArticle2.getStatus()) {
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

	private KBArticleStatusComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final KBArticleStatusComparator _INSTANCE_ASCENDING =
		new KBArticleStatusComparator(true);

	private static final KBArticleStatusComparator _INSTANCE_DESCENDING =
		new KBArticleStatusComparator(false);

	private final boolean _ascending;

}