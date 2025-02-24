/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.helper;

import com.liferay.petra.string.StringUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.search.ccr.CrossClusterReplicationConfigurationHelper;
import com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.CrossClusterReplicationConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Bryan Engler
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.CrossClusterReplicationConfiguration",
	enabled = false, service = CrossClusterReplicationConfigurationHelper.class
)
public class CrossClusterReplicationConfigurationHelperImpl
	implements CrossClusterReplicationConfigurationHelper {

	@Override
	public List<String> getLocalClusterConnectionIds() {
		List<String> connectionIds = new ArrayList<>();

		String[] localClusterConnectionConfigurations =
			_crossClusterReplicationConfiguration.
				ccrLocalClusterConnectionConfigurations();

		for (String localClusterConnectionConfiguration :
				localClusterConnectionConfigurations) {

			List<String> localClusterConnectionConfigurationParts =
				StringUtil.split(localClusterConnectionConfiguration);

			connectionIds.add(localClusterConnectionConfigurationParts.get(1));
		}

		return connectionIds;
	}

	@Override
	public Map<String, String> getLocalClusterConnectionIdsMap() {
		Map<String, String> connectionIds = new HashMap<>();

		String[] localClusterConnectionConfigurations =
			_crossClusterReplicationConfiguration.
				ccrLocalClusterConnectionConfigurations();

		for (String localClusterConnectionConfiguration :
				localClusterConnectionConfigurations) {

			List<String> localClusterConnectionConfigurationParts =
				StringUtil.split(localClusterConnectionConfiguration);

			String hostName = localClusterConnectionConfigurationParts.get(0);
			String connectionId = localClusterConnectionConfigurationParts.get(
				1);

			connectionIds.put(hostName, connectionId);
		}

		return connectionIds;
	}

	@Override
	public boolean isCrossClusterReplicationEnabled() {
		return _crossClusterReplicationConfiguration.ccrEnabled();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_crossClusterReplicationConfiguration =
			ConfigurableUtil.createConfigurable(
				CrossClusterReplicationConfiguration.class, properties);
	}

	private volatile CrossClusterReplicationConfiguration
		_crossClusterReplicationConfiguration;

}