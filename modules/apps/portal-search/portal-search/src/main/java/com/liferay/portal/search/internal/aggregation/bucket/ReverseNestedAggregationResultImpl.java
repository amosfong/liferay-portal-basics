/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.aggregation.bucket;

import com.liferay.portal.search.aggregation.bucket.ReverseNestedAggregationResult;

/**
 * @author Michael C. Han
 */
public class ReverseNestedAggregationResultImpl
	extends BaseHierarchicalAggregationResult
	implements ReverseNestedAggregationResult {

	public ReverseNestedAggregationResultImpl(String name, long docCount) {
		super(name);

		_docCount = docCount;
	}

	@Override
	public long getDocCount() {
		return _docCount;
	}

	@Override
	public void setDocCount(long docCount) {
		_docCount = docCount;
	}

	private long _docCount;

}