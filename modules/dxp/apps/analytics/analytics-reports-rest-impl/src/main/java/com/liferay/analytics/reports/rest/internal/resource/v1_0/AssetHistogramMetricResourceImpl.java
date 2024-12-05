/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.reports.rest.internal.resource.v1_0;

import com.liferay.analytics.reports.rest.dto.v1_0.AssetHistogramMetric;
import com.liferay.analytics.reports.rest.internal.client.AnalyticsCloudClient;
import com.liferay.analytics.reports.rest.resource.v1_0.AssetHistogramMetricResource;
import com.liferay.analytics.settings.rest.manager.AnalyticsSettingsManager;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryGroupRelLocalService;
import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Marcos Martins
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/asset-histogram-metric.properties",
	scope = ServiceScope.PROTOTYPE, service = AssetHistogramMetricResource.class
)
public class AssetHistogramMetricResourceImpl
	extends BaseAssetHistogramMetricResourceImpl {

	@Override
	public AssetHistogramMetric getGroupAssetMetricAssetTypeHistogram(
			Long groupId, String assetType, String assetId, String identityType,
			Integer rangeKey)
		throws Exception {

		List<Long> analyticsCloudChannelIds = new ArrayList<>();

		Group group = _groupLocalService.getGroup(groupId);

		DepotEntry depotEntry = _depotEntryLocalService.fetchGroupDepotEntry(
			groupId);

		if (depotEntry != null) {
			analyticsCloudChannelIds = transform(
				_depotEntryGroupRelLocalService.getDepotEntryGroupRels(
					depotEntry),
				depotEntryGroupRel -> {
					Group depotEntryGroup = _groupLocalService.getGroup(
						depotEntryGroupRel.getToGroupId());

					String analyticsChannelId =
						depotEntryGroup.getTypeSettingsProperty(
							"analyticsChannelId");

					if (Validator.isNull(analyticsChannelId)) {
						return null;
					}

					return Long.valueOf(analyticsChannelId);
				});
		}
		else {
			analyticsCloudChannelIds = Collections.singletonList(
				Long.valueOf(
					group.getTypeSettingsProperty("analyticsChannelId")));
		}

		AnalyticsCloudClient analyticsCloudClient = new AnalyticsCloudClient(
			_http);

		return analyticsCloudClient.getAssetHistogramMetric(
			_analyticsSettingsManager.getAnalyticsConfiguration(
				group.getCompanyId()),
			assetId, assetType, analyticsCloudChannelIds, identityType,
			rangeKey);
	}

	@Reference
	private AnalyticsSettingsManager _analyticsSettingsManager;

	@Reference
	private DepotEntryGroupRelLocalService _depotEntryGroupRelLocalService;

	@Reference
	private DepotEntryLocalService _depotEntryLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Http _http;

}