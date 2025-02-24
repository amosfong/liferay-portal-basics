/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.legacy.hits;

import com.liferay.portal.kernel.search.Document;

import org.opensearch.client.json.JsonData;
import org.opensearch.client.opensearch.core.search.Hit;

/**
 * @author Michael C. Han
 * @author Petteri Karttunen
 */
public interface HitDocumentTranslator {

	public Document translate(Hit<JsonData> hit);

}