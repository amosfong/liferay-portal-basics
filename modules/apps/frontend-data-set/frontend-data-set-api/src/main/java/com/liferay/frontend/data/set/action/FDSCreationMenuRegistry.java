/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.data.set.action;

/**
 * @author Daniel Sanz
 */
public interface FDSCreationMenuRegistry {

	public FDSCreationMenu getFDSCreationMenu(String fdsName);

}