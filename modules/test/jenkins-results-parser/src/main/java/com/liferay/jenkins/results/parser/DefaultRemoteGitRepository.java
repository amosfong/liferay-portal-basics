/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser;

/**
 * @author Michael Hashimoto
 */
public class DefaultRemoteGitRepository extends BaseRemoteGitRepository {

	protected DefaultRemoteGitRepository(GitRemote gitRemote) {
		super(gitRemote);
	}

	protected DefaultRemoteGitRepository(
		String hostname, String gitRepositoryName, String username) {

		super(hostname, gitRepositoryName, username);
	}

}