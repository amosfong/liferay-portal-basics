/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class TestHistory {

	public long getAverageDuration() {
		return _averageDuration;
	}

	public long getAverageOverheadDuration() {
		return _averageOverheadDuration;
	}

	public BatchHistory getBatchHistory() {
		return _batchHistory;
	}

	public int getFailureCount() {
		return _failureCount;
	}

	public int getStatusChanges() {
		return _statusChanges;
	}

	public long getTestCount() {
		return _testCount;
	}

	public String getTestName() {
		return _testName;
	}

	public long getTestrayCaseResultID() {
		return _testrayCaseResultID;
	}

	public String getTestrayCaseResultURL() {
		long testrayCaseResultID = getTestrayCaseResultID();

		if (testrayCaseResultID <= 0L) {
			return null;
		}

		BatchHistory batchHistory = getBatchHistory();

		if (batchHistory == null) {
			return null;
		}

		JobHistory jobHistory = batchHistory.getJobHistory();

		if (jobHistory == null) {
			return null;
		}

		return JenkinsResultsParserUtil.combine(
			String.valueOf(jobHistory.getTestrayURL()),
			"/home/-/testray/case_results/",
			String.valueOf(testrayCaseResultID), "/history");
	}

	public TestTaskHistory getTestTaskHistory() {
		return _batchHistory.getTestTaskHistory(getTestTaskName());
	}

	public String getTestTaskName() {
		return _testTaskName;
	}

	protected TestHistory(BatchHistory batchHistory, JSONObject jsonObject) {
		_batchHistory = batchHistory;

		_averageDuration = jsonObject.optLong("averageDuration");
		_averageOverheadDuration = jsonObject.optLong(
			"averageOverheadDuration");
		_failureCount = jsonObject.optInt("failureCount");
		_statusChanges = jsonObject.optInt("statusChanges");
		_testName = jsonObject.getString("testName");
		_testCount = jsonObject.optInt("testCount");
		_testrayCaseResultID = jsonObject.optLong("testrayCaseResultID");
		_testTaskName = jsonObject.optString("testTaskName");
	}

	private final long _averageDuration;
	private final long _averageOverheadDuration;
	private final BatchHistory _batchHistory;
	private final int _failureCount;
	private final int _statusChanges;
	private final int _testCount;
	private final String _testName;
	private final long _testrayCaseResultID;
	private final String _testTaskName;

}