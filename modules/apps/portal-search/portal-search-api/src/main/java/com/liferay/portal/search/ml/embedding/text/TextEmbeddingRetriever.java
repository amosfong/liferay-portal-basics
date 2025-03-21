/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.ml.embedding.text;

import com.liferay.portal.search.ml.embedding.EmbeddingProviderInformation;
import com.liferay.portal.search.ml.embedding.EmbeddingRetriever;

/**
 * @author Petteri Karttunen
 */
public interface TextEmbeddingRetriever
	extends EmbeddingProviderInformation, EmbeddingRetriever {

	public Double[] getTextEmbedding(String providerName, String text);

}