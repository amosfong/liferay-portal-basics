/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.suggestions;

import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface Suggestion {

	public Object getAttribute(String name);

	public Map<String, Object> getAttributes();

	public float getScore();

	public String getText();

}