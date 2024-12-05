/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.suggest;

/**
 * @author Michael C. Han
 */
public class OpenSearchSuggesterTranslatorFixture {

	public OpenSearchSuggesterTranslator getOpenSearchSuggesterTranslator() {
		return _openSearchSuggesterTranslator;
	}

	private final OpenSearchSuggesterTranslator _openSearchSuggesterTranslator =
		new OpenSearchSuggesterTranslator();

}