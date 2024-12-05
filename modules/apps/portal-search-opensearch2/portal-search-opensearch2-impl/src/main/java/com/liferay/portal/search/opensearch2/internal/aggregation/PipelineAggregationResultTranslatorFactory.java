/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.aggregation;

import com.liferay.portal.search.aggregation.pipeline.PipelineAggregationResultTranslator;

import org.opensearch.client.opensearch._types.aggregations.Aggregate;

/**
 * @author André de Oliveira
 * @author Petteri Karttunen
 */
public interface PipelineAggregationResultTranslatorFactory {

	public PipelineAggregationResultTranslator
		createPipelineAggregationResultTranslator(Aggregate aggregate);

}