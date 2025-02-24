/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.cluster;

/**
 * @author André de Oliveira
 */
public interface ReplicasClusterContext {

	public int getClusterSize();

	public ReplicasManager getReplicasManager();

	public String[] getTargetIndexNames();

	public boolean isEmbeddedOperationMode();

	public boolean isMaster();

}