/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.util.comparator;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class ArticleDisplayDateComparator
	extends OrderByComparator<JournalArticle> {

	public static final String ORDER_BY_ASC =
		"JournalArticle.displayDate ASC, JournalArticle.version ASC";

	public static final String ORDER_BY_DESC =
		"JournalArticle.displayDate DESC, JournalArticle.version DESC";

	public static final String[] ORDER_BY_FIELDS = {"displayDate", "version"};

	public static ArticleDisplayDateComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(JournalArticle article1, JournalArticle article2) {
		int value = DateUtil.compareTo(
			article1.getDisplayDate(), article2.getDisplayDate());

		if (value == 0) {
			if (article1.getVersion() < article2.getVersion()) {
				value = -1;
			}
			else if (article1.getVersion() > article2.getVersion()) {
				value = 1;
			}
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

	private ArticleDisplayDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final ArticleDisplayDateComparator _INSTANCE_ASCENDING =
		new ArticleDisplayDateComparator(true);

	private static final ArticleDisplayDateComparator _INSTANCE_DESCENDING =
		new ArticleDisplayDateComparator(false);

	private final boolean _ascending;

}