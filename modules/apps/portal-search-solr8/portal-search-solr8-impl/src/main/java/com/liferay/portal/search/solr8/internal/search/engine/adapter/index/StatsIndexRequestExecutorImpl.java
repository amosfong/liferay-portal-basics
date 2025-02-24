/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr8.internal.search.engine.adapter.index;

import com.liferay.portal.search.engine.adapter.index.StatsIndexRequest;
import com.liferay.portal.search.engine.adapter.index.StatsIndexResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Felipe Lorenz
 */
@Component(service = StatsIndexRequestExecutor.class)
public class StatsIndexRequestExecutorImpl
	implements StatsIndexRequestExecutor {

	@Override
	public StatsIndexResponse execute(StatsIndexRequest statsIndexRequest) {
		throw new UnsupportedOperationException();
	}

}