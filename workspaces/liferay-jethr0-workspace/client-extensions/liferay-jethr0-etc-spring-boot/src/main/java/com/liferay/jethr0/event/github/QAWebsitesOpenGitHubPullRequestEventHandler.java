/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.event.github;

import com.liferay.jethr0.job.JobEntity;

import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class QAWebsitesOpenGitHubPullRequestEventHandler
	extends BaseOpenGitHubPullRequestEventHandler {

	protected QAWebsitesOpenGitHubPullRequestEventHandler(
		JSONObject messageJSONObject) {

		super(messageJSONObject);
	}

	@Override
	protected Set<JobEntity> createJobEntities()
		throws InvalidJSONException, IOException {

		Set<JobEntity> jobEntities = new HashSet<>();

		for (String testOption : getTestOptions()) {
			jobEntities.add(
				createPullRequestJobEntity(
					JobEntity.Type.QA_WEBSITES_PULL_REQUEST_SF, 5, testOption));
		}

		return jobEntities;
	}

}