/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.type.controller.display.page.internal.display.context;

import com.liferay.info.constants.InfoDisplayWebKeys;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.InfoItemDetails;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.InfoItemServiceRegistry;
import com.liferay.info.item.provider.InfoItemDetailsProvider;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.info.item.provider.InfoItemPermissionProvider;
import com.liferay.info.search.InfoSearchClassMapperRegistry;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jürgen Kappler
 */
public class DisplayPageLayoutTypeControllerDisplayContext {

	public DisplayPageLayoutTypeControllerDisplayContext(
			HttpServletRequest httpServletRequest,
			InfoItemServiceRegistry infoItemServiceRegistry,
			InfoSearchClassMapperRegistry infoSearchClassMapperRegistry)
		throws Exception {

		_infoItemServiceRegistry = infoItemServiceRegistry;

		Object infoItem = httpServletRequest.getAttribute(
			InfoDisplayWebKeys.INFO_ITEM);
		InfoItemDetails infoItemDetails =
			(InfoItemDetails)httpServletRequest.getAttribute(
				InfoDisplayWebKeys.INFO_ITEM_DETAILS);

		_infoItem = infoItem;
		_infoItemDetails = infoItemDetails;
	}

	public boolean hasInfoItem() {
		if ((_infoItem != null) && (_infoItemDetails != null)) {
			return true;
		}

		return false;
	}

	public boolean hasPermission(
			PermissionChecker permissionChecker, String actionId)
		throws Exception {

		if (_infoItemDetails == null) {
			return true;
		}

		InfoItemPermissionProvider infoItemPermissionProvider =
			_infoItemServiceRegistry.getFirstInfoItemService(
				InfoItemPermissionProvider.class,
				_infoItemDetails.getClassName());

		if (infoItemPermissionProvider != null) {
			return infoItemPermissionProvider.hasPermission(
				permissionChecker, _infoItem, actionId);
		}

		return true;
	}

	public boolean isForbidden(HttpServletResponse httpServletResponse) {
		if (httpServletResponse.getStatus() ==
				HttpServletResponse.SC_FORBIDDEN) {

			return true;
		}

		return false;
	}

	private final Object _infoItem;
	private final InfoItemDetails _infoItemDetails;
	private final InfoItemServiceRegistry _infoItemServiceRegistry;

}