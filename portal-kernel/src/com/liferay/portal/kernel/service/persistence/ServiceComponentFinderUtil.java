/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ServiceComponentFinderUtil {

	public static java.util.List
		<com.liferay.portal.kernel.model.ServiceComponent>
			findByMaxBuildNumber() {

		return getFinder().findByMaxBuildNumber();
	}

	public static ServiceComponentFinder getFinder() {
		if (_finder == null) {
			_finder = (ServiceComponentFinder)PortalBeanLocatorUtil.locate(
				ServiceComponentFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(ServiceComponentFinder finder) {
		_finder = finder;
	}

	private static ServiceComponentFinder _finder;

}