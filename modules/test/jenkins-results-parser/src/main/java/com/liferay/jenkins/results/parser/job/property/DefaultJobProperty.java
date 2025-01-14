/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.job.property;

import com.liferay.jenkins.results.parser.Job;

/**
 * @author Michael Hashimoto
 */
public class DefaultJobProperty extends BaseJobProperty {

	public DefaultJobProperty(
		Job job, Type type, String basePropertyName,
		boolean useBasePropertyName, String testSuiteName,
		String testBatchName) {

		super(
			job, type, basePropertyName, useBasePropertyName, testSuiteName,
			testBatchName);
	}

	public DefaultJobProperty(
		Job job, Type type, String basePropertyName,
		boolean useBasePropertyName, String testSuiteName, String testBatchName,
		String ruleName) {

		super(
			job, type, basePropertyName, useBasePropertyName, testSuiteName,
			testBatchName, ruleName);
	}

	@Override
	public Type getType() {
		return Type.DEFAULT;
	}

}