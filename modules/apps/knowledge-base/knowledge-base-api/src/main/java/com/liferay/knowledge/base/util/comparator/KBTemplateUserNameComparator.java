/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.util.comparator;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBTemplateUserNameComparator
	extends OrderByComparator<KBTemplate> {

	public static final String ORDER_BY_ASC = "KBTemplate.userName ASC";

	public static final String ORDER_BY_DESC = "KBTemplate.userName DESC";

	public static final String[] ORDER_BY_FIELDS = {"userName", "title"};

	public static KBTemplateUserNameComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(KBTemplate kbTemplate1, KBTemplate kbTemplate2) {
		String lowerCaseUserName1 = StringUtil.toLowerCase(
			kbTemplate1.getUserName());
		String lowerCaseUserName2 = StringUtil.toLowerCase(
			kbTemplate2.getUserName());

		int value = lowerCaseUserName1.compareTo(lowerCaseUserName2);

		if (value == 0) {
			String title1 = kbTemplate1.getTitle();
			String title2 = kbTemplate1.getTitle();

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

	private KBTemplateUserNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final KBTemplateUserNameComparator _INSTANCE_ASCENDING =
		new KBTemplateUserNameComparator(true);

	private static final KBTemplateUserNameComparator _INSTANCE_DESCENDING =
		new KBTemplateUserNameComparator(false);

	private final boolean _ascending;

}