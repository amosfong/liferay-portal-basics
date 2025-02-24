/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class BatchHistory {

	public long getAverageDuration() {
		return _averageDuration;
	}

	public String getBatchName() {
		return _batchName;
	}

	public JobHistory getJobHistory() {
		return _jobHistory;
	}

	public TestHistory getTestHistory(String key) {
		return _testHistories.get(key);
	}

	public TestTaskHistory getTestTaskHistory(String key) {
		return _testTaskHistories.get(key);
	}

	protected BatchHistory(JobHistory jobHistory, JSONObject jsonObject) {
		_jobHistory = jobHistory;

		_averageDuration = jsonObject.optLong("averageDuration");
		_batchName = jsonObject.getString("batchName");

		JSONArray testsJSONArray = jsonObject.optJSONArray("tests");

		if ((testsJSONArray != null) && !testsJSONArray.isEmpty()) {
			for (int i = 0; i < testsJSONArray.length(); i++) {
				TestHistory testHistory = new TestHistory(
					this, testsJSONArray.getJSONObject(i));

				_testHistories.put(testHistory.getTestName(), testHistory);
			}
		}

		JSONArray testTasksJSONArray = jsonObject.optJSONArray("testTasks");

		if ((testTasksJSONArray != null) && !testTasksJSONArray.isEmpty()) {
			for (int i = 0; i < testTasksJSONArray.length(); i++) {
				TestTaskHistory testTaskHistory = new TestTaskHistory(
					this, testTasksJSONArray.getJSONObject(i));

				_testTaskHistories.put(
					testTaskHistory.getTestTaskName(), testTaskHistory);
			}
		}
	}

	private final long _averageDuration;
	private final String _batchName;
	private final JobHistory _jobHistory;
	private final Map<String, TestHistory> _testHistories = new HashMap<>();
	private final Map<String, TestTaskHistory> _testTaskHistories =
		new HashMap<>();

}