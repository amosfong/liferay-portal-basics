/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.util.comparator;

import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.portal.kernel.util.MapUtil;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSubscriptionTypeServiceWrapperOrderComparator
	implements Comparator<ServiceWrapper<CPSubscriptionType>>, Serializable {

	public CPSubscriptionTypeServiceWrapperOrderComparator() {
		this(true);
	}

	public CPSubscriptionTypeServiceWrapperOrderComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		ServiceWrapper<CPSubscriptionType> serviceWrapper1,
		ServiceWrapper<CPSubscriptionType> serviceWrapper2) {

		int displayOrder1 = MapUtil.getInteger(
			serviceWrapper1.getProperties(),
			"commerce.product.subscription.type.order", Integer.MAX_VALUE);
		int displayOrder2 = MapUtil.getInteger(
			serviceWrapper2.getProperties(),
			"commerce.product.subscription.type.order", Integer.MAX_VALUE);

		int value = Integer.compare(displayOrder1, displayOrder2);

		if (_ascending) {
			return value;
		}

		return Math.negateExact(value);
	}

	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}