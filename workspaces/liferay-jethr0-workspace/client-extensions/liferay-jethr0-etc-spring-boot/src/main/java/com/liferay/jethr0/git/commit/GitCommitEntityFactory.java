/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.git.commit;

import com.liferay.jethr0.entity.factory.BaseEntityFactory;

import org.json.JSONObject;

import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Hashimoto
 */
@Configuration
public class GitCommitEntityFactory extends BaseEntityFactory<GitCommitEntity> {

	@Override
	public GitCommitEntity newEntity(JSONObject jsonObject) {
		return new DefaultGitCommitEntity(jsonObject);
	}

	protected GitCommitEntityFactory() {
		super(GitCommitEntity.class);
	}

}