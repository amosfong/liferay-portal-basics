/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.aggregation.pipeline;

import com.liferay.portal.search.aggregation.pipeline.BucketScriptPipelineAggregationResult;
import com.liferay.portal.search.internal.aggregation.BaseAggregationResult;

/**
 * @author Michael C. Han
 */
public class BucketScriptPipelineAggregationResultImpl
	extends BaseAggregationResult
	implements BucketScriptPipelineAggregationResult {

	public BucketScriptPipelineAggregationResultImpl(
		String name, double value) {

		super(name);

		_value = value;
	}

	@Override
	public double getValue() {
		return _value;
	}

	private final double _value;

}