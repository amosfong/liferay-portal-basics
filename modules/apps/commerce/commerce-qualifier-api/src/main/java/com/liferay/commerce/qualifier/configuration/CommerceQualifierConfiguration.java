/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.qualifier.configuration;

/**
 * @author Riccardo Alberti
 */
public interface CommerceQualifierConfiguration {

	public String[] allowedTargetKeys();

	public String[] orderByTargetKeys();

}