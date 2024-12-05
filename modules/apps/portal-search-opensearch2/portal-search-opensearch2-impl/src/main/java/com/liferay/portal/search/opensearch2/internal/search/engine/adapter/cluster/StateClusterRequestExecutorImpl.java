/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.search.engine.adapter.cluster;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.search.engine.adapter.cluster.StateClusterRequest;
import com.liferay.portal.search.engine.adapter.cluster.StateClusterResponse;
import com.liferay.portal.search.opensearch2.internal.connection.OpenSearchConnectionManager;
import com.liferay.portal.search.opensearch2.internal.util.JsonpUtil;

import java.io.IOException;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.cluster.OpenSearchClusterClient;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 * @author Petteri Karttunen
 */
@Component(service = StateClusterRequestExecutor.class)
public class StateClusterRequestExecutorImpl
	implements StateClusterRequestExecutor {

	@Override
	public StateClusterResponse execute(
		StateClusterRequest stateClusterRequest) {

		OpenSearchClient openSearchClient =
			_openSearchConnectionManager.getOpenSearchClient(
				stateClusterRequest.getConnectionId(),
				stateClusterRequest.isPreferLocalCluster());

		OpenSearchClusterClient openSearchClusterClient =
			openSearchClient.cluster();

		try {
			return new StateClusterResponse(
				JsonpUtil.toString(openSearchClusterClient.state()));
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Reference
	private OpenSearchConnectionManager _openSearchConnectionManager;

}