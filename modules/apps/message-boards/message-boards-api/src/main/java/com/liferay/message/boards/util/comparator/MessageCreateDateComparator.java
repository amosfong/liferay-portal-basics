/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.util.comparator;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class MessageCreateDateComparator extends OrderByComparator<MBMessage> {

	public static final String ORDER_BY_ASC = "MBMessage.createDate ASC";

	public static final String ORDER_BY_DESC = "MBMessage.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public static MessageCreateDateComparator getInstance(boolean ascending) {
		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(MBMessage message1, MBMessage message2) {
		int value = DateUtil.compareTo(
			message1.getCreateDate(), message2.getCreateDate());

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

	private MessageCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	private static final MessageCreateDateComparator _INSTANCE_ASCENDING =
		new MessageCreateDateComparator(true);

	private static final MessageCreateDateComparator _INSTANCE_DESCENDING =
		new MessageCreateDateComparator(false);

	private final boolean _ascending;

}