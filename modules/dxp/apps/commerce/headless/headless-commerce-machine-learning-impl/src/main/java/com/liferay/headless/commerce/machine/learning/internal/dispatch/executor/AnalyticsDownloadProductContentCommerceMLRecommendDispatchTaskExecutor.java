/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.machine.learning.internal.dispatch.executor;

import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.executor.DispatchTaskStatus;
import com.liferay.dispatch.model.DispatchLog;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.ProductContentRecommendation;
import com.liferay.portal.kernel.util.HashMapBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	property = {
		"dispatch.task.executor.name=" + AnalyticsDownloadProductContentCommerceMLRecommendDispatchTaskExecutor.KEY,
		"dispatch.task.executor.type=" + AnalyticsDownloadProductContentCommerceMLRecommendDispatchTaskExecutor.KEY
	},
	service = DispatchTaskExecutor.class
)
public class
	AnalyticsDownloadProductContentCommerceMLRecommendDispatchTaskExecutor
		extends BaseDispatchTaskExecutor {

	public static final String KEY =
		"analytics-download-product-content-commerce-ml-recommendation";

	@Override
	public void doExecute(
			DispatchTrigger dispatchTrigger,
			DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
		throws Exception {

		DispatchLog dispatchLog =
			dispatchLogLocalService.fetchLatestDispatchLog(
				dispatchTrigger.getDispatchTriggerId(),
				DispatchTaskStatus.IN_PROGRESS);

		analyticsBatchExportImportManager.importFromAnalyticsCloud(
			null, dispatchTrigger.getCompanyId(),
			HashMapBuilder.put(
				"createDate", "createDate"
			).put(
				"entryClassPK", "productId"
			).put(
				"jobId", "jobId"
			).put(
				"rank", "rank"
			).put(
				"recommendedEntryClassPK", "recommendedProductId"
			).put(
				"score", "score"
			).build(),
			message -> updateDispatchLog(
				dispatchLog.getDispatchLogId(), dispatchTaskExecutorOutput,
				message),
			getLatestSuccessfulDispatchLogEndDate(
				dispatchTrigger.getDispatchTriggerId()),
			ProductContentRecommendation.class.getName(),
			dispatchTrigger.getUserId());
	}

	@Override
	public String getName() {
		return KEY;
	}

}