/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.links;

import aQute.bnd.annotation.ProviderType;

import java.util.List;

/**
 * @author Marco Leo
 */
@ProviderType
public interface CPDefinitionLinkTypeRegistry {

	public CPDefinitionLinkType getCPDefinitionLinkType(String key);

	public List<String> getTypes();

}