/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bean.portlet.extension;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Neil Griffin
 */
@ProviderType
public interface BeanPortletMethodDecorator {

	public BeanPortletMethod getBeanPortletMethod(
		BeanPortletMethod beanPortletMethod, PortletConfig portletConfig,
		PortletRequest portletRequest, PortletResponse portletResponse);

}