/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.utility.page.kernel.request.contributor;

import com.liferay.portal.kernel.servlet.DynamicServletRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jürgen Kappler
 */
@ProviderType
public interface StatusLayoutUtilityPageEntryRequestContributor {

	public void addAttributesAndParameters(
		DynamicServletRequest dynamicServletRequest);

}