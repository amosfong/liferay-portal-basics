/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.util.comparator;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBTemplateModifiedDateComparator
	extends OrderByComparator<KBTemplate> {

	public static final String ORDER_BY_ASC = "KBTemplate.modifiedDate ASC";

	public static final String ORDER_BY_DESC = "KBTemplate.modifiedDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedDate"};

	public static KBTemplateModifiedDateComparator getInstance(
		boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(KBTemplate kbTemplate1, KBTemplate kbTemplate2) {
		int value = DateUtil.compareTo(
			kbTemplate1.getModifiedDate(), kbTemplate2.getModifiedDate());

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

	private KBTemplateModifiedDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final KBTemplateModifiedDateComparator _INSTANCE_ASCENDING =
		new KBTemplateModifiedDateComparator(true);

	private static final KBTemplateModifiedDateComparator _INSTANCE_DESCENDING =
		new KBTemplateModifiedDateComparator(false);

	private final boolean _ascending;

}