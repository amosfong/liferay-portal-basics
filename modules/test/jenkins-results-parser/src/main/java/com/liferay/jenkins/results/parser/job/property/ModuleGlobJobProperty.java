/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.job.property;

import com.liferay.jenkins.results.parser.Job;

import java.io.File;

import java.util.Collections;
import java.util.List;

/**
 * @author Michael Hashimoto
 */
public class ModuleGlobJobProperty extends BaseGlobJobProperty {

	protected ModuleGlobJobProperty(
		Job job, Type type, File testBaseDir, String basePropertyName,
		boolean useBasePropertyName, String testSuiteName,
		String testBatchName) {

		super(
			job, type, testBaseDir, basePropertyName, useBasePropertyName,
			testSuiteName, testBatchName);
	}

	protected ModuleGlobJobProperty(
		Job job, Type type, File testBaseDir, String basePropertyName,
		boolean useBasePropertyName, String testSuiteName, String testBatchName,
		String ruleName) {

		super(
			job, type, testBaseDir, basePropertyName, useBasePropertyName,
			testSuiteName, testBatchName, ruleName);
	}

	@Override
	protected List<File> getJobPropertiesFiles() {
		File propertiesFile = new File(getTestBaseDir(), "test.properties");

		return Collections.singletonList(propertiesFile);
	}

	@Override
	protected String getRelativePath() {
		return "modules";
	}

}