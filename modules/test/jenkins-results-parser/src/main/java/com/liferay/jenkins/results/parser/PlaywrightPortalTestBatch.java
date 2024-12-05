/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser;

import java.io.File;
import java.io.IOException;

import java.util.Map;

/**
 * @author Michael Hashimoto
 */
public class PlaywrightPortalTestBatch
	extends BasePortalTestBatch<PortalBatchBuildData> {

	@Override
	public void run() {
		try {
			executeBatch();
		}
		catch (AntException antException) {
			throw new RuntimeException(antException);
		}
		finally {
			publishResults();

			publishPlaywrightReport();
		}
	}

	protected PlaywrightPortalTestBatch(
		PortalBatchBuildData portalBatchBuildData, Workspace workspace) {

		super(portalBatchBuildData, workspace);
	}

	@Override
	protected Map<String, String> getEnvironmentVariables() {
		Map<String, String> environmentVariables =
			super.getEnvironmentVariables();

		PortalBatchBuildData portalBatchBuildData = getBatchBuildData();

		TopLevelBuildData topLevelBuildData =
			portalBatchBuildData.getTopLevelBuildData();

		String portalBatchTestSelector = topLevelBuildData.getBuildParameter(
			"PORTAL_BATCH_TEST_SELECTOR");

		if (!JenkinsResultsParserUtil.isNullOrEmpty(portalBatchTestSelector)) {
			environmentVariables.put(
				"PLAYWRIGHT_PROJECT_NAME", portalBatchTestSelector);
		}

		return environmentVariables;
	}

	protected void publishPlaywrightReport() {
		File playwrightReportFile = new File(
			getPrimaryPortalWorkspaceDirectory(),
			"modules/test/playwright/playwright-report/index.html");

		if (!playwrightReportFile.exists()) {
			return;
		}

		PortalBatchBuildData portalBatchBuildData = getBatchBuildData();

		try {
			JenkinsResultsParserUtil.copy(
				playwrightReportFile.getParentFile(),
				new File(
					portalBatchBuildData.getArtifactDir(),
					"playwright-reports"));
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}

		StringBuilder sb = new StringBuilder();

		sb.append(portalBatchBuildData.getPortalBranchSHA());
		sb.append(" - ");
		sb.append(portalBatchBuildData.getBatchName());
		sb.append(" - ");
		sb.append("<a href=\"https://");
		sb.append(portalBatchBuildData.getTopLevelMasterHostname());
		sb.append(".liferay.com/userContent/");
		sb.append(portalBatchBuildData.getUserContentRelativePath());
		sb.append("jenkins-report.html\">Jenkins Report</a> - ");
		sb.append("<a href=\"https://");
		sb.append(portalBatchBuildData.getTopLevelMasterHostname());
		sb.append(".liferay.com/userContent/");
		sb.append(portalBatchBuildData.getUserContentRelativePath());
		sb.append(portalBatchBuildData.getRunID());
		sb.append("/playwright-reports/index.html\">Playwright Report</a>");

		sb.append("<ul>");

		for (String test : portalBatchBuildData.getTestList()) {
			sb.append("<li>");
			sb.append(test);
			sb.append("</li>");
		}

		sb.append("</ul>");

		portalBatchBuildData.setBuildDescription(sb.toString());
	}

}