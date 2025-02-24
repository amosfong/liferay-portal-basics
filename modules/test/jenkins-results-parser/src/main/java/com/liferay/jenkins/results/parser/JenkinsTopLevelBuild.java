/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser;

/**
 * @author Michael Hashimoto
 */
public class JenkinsTopLevelBuild extends DefaultTopLevelBuild {

	public JenkinsTopLevelBuild(String url) {
		super(url);
	}

	public JenkinsTopLevelBuild(String url, TopLevelBuild topLevelBuild) {
		super(url, topLevelBuild);
	}

}