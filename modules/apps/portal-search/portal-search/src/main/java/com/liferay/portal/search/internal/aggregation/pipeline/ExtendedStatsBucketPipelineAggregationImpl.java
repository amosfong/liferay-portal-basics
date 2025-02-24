/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.aggregation.pipeline;

import com.liferay.portal.search.aggregation.pipeline.ExtendedStatsBucketPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.PipelineAggregationVisitor;

/**
 * @author Michael C. Han
 */
public class ExtendedStatsBucketPipelineAggregationImpl
	extends BaseBucketMetricsPipelineAggregationImpl
	implements ExtendedStatsBucketPipelineAggregation {

	public ExtendedStatsBucketPipelineAggregationImpl(
		String name, String bucketsPath) {

		super(name, bucketsPath);
	}

	@Override
	public <T> T accept(
		PipelineAggregationVisitor<T> pipelineAggregationVisitor) {

		return pipelineAggregationVisitor.visit(this);
	}

	@Override
	public Double getSigma() {
		return _sigma;
	}

	@Override
	public void setSigma(Double sigma) {
		_sigma = sigma;
	}

	private Double _sigma;

}