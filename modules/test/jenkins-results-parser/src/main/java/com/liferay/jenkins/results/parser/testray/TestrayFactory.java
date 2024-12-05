/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.testray;

import com.liferay.jenkins.results.parser.Build;
import com.liferay.jenkins.results.parser.JenkinsResultsParserUtil;
import com.liferay.jenkins.results.parser.SourceFormatBuild;
import com.liferay.jenkins.results.parser.TopLevelBuild;
import com.liferay.jenkins.results.parser.test.clazz.TestClass;
import com.liferay.jenkins.results.parser.test.clazz.TestClassMethod;
import com.liferay.jenkins.results.parser.test.clazz.group.AxisTestClassGroup;
import com.liferay.jenkins.results.parser.test.clazz.group.FunctionalAxisTestClassGroup;
import com.liferay.jenkins.results.parser.test.clazz.group.JSUnitAxisTestClassGroup;
import com.liferay.jenkins.results.parser.test.clazz.group.JUnitAxisTestClassGroup;
import com.liferay.jenkins.results.parser.test.clazz.group.PlaywrightAxisTestClassGroup;
import com.liferay.jenkins.results.parser.test.clazz.group.SemVerModulesAxisTestClassGroup;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class TestrayFactory {

	public static PortalLogBatchBuildTestrayCaseResult
		newPortalLogTestrayCaseResult(
			TestrayBuild testrayBuild, TopLevelBuild topLevelBuild,
			AxisTestClassGroup axisTestClassGroup) {

		return new PortalLogBatchBuildTestrayCaseResult(
			testrayBuild, topLevelBuild, axisTestClassGroup);
	}

	public static TestrayAttachment newTestrayAttachment(
		TestrayCaseResult testrayCaseResult, String name, String key) {

		return newTestrayAttachment(testrayCaseResult, name, key, null);
	}

	public static TestrayAttachment newTestrayAttachment(
		TestrayCaseResult testrayCaseResult, String name, String key, URL url) {

		return new DefaultTestrayAttachment(testrayCaseResult, name, key, url);
	}

	public static TestrayAttachmentRecorder newTestrayAttachmentRecorder(
		Build build) {

		TestrayAttachmentRecorder testrayAttachmentRecorder =
			_testrayAttachmentRecorders.get(build);

		if (testrayAttachmentRecorder != null) {
			return testrayAttachmentRecorder;
		}

		testrayAttachmentRecorder = new TestrayAttachmentRecorder(build);

		_testrayAttachmentRecorders.put(build, testrayAttachmentRecorder);

		return testrayAttachmentRecorder;
	}

	public static TestrayAttachmentUploader newTestrayAttachmentUploader(
		Build build, URL testrayServerURL,
		TestrayAttachmentUploader.Type type) {

		String testrayServerURLString = "";

		if (testrayServerURL != null) {
			testrayServerURLString = String.valueOf(testrayServerURL);
		}

		String key = JenkinsResultsParserUtil.combine(
			build.getBuildURL(), "_", testrayServerURLString, "_",
			type.toString());

		TestrayAttachmentUploader testrayAttachmentUploader =
			_testrayAttachmentUploaders.get(key);

		if (testrayAttachmentUploader != null) {
			return testrayAttachmentUploader;
		}

		if (type == TestrayAttachmentUploader.Type.RSYNC) {
			testrayAttachmentUploader = new RsyncTestrayAttachmentUploader(
				build, testrayServerURL);
		}
		else {
			testrayAttachmentUploader = new S3TestrayAttachmentUploader(
				build, testrayServerURL);
		}

		_testrayAttachmentUploaders.put(key, testrayAttachmentUploader);

		return testrayAttachmentUploader;
	}

	public static TestrayBuild newTestrayBuild(
		TestrayRoutine testrayRoutine, JSONObject jsonObject) {

		return new TestrayBuild(testrayRoutine, jsonObject);
	}

	public static TestrayBuild newTestrayBuild(
		TestrayServer testrayServer, JSONObject jsonObject) {

		return new TestrayBuild(testrayServer, jsonObject);
	}

	public static TestrayCase newTestrayCase(
		TestrayProject testrayProject, JSONObject jsonObject) {

		return new TestrayCase(testrayProject, jsonObject);
	}

	public static TestrayCaseResult newTestrayCaseResult(
		TestrayBuild testrayBuild, JSONObject jsonObject) {

		return new TestrayCaseResult(testrayBuild, jsonObject);
	}

	public static TestrayCaseResult newTestrayCaseResult(
		TestrayBuild testrayBuild, TopLevelBuild topLevelBuild,
		AxisTestClassGroup axisTestClassGroup, TestClass testClass) {

		return newTestrayCaseResult(
			testrayBuild, topLevelBuild, axisTestClassGroup, testClass, null);
	}

	public static TestrayCaseResult newTestrayCaseResult(
		TestrayBuild testrayBuild, TopLevelBuild topLevelBuild,
		AxisTestClassGroup axisTestClassGroup, TestClass testClass,
		TestClassMethod testClassMethod) {

		if (testrayBuild == null) {
			throw new RuntimeException("Testray build is null");
		}

		if (topLevelBuild == null) {
			throw new RuntimeException("Top level build is null");
		}

		if (axisTestClassGroup == null) {
			throw new RuntimeException("Axis test class group is null");
		}

		if (testClass != null) {
			if (axisTestClassGroup instanceof FunctionalAxisTestClassGroup) {
				return new FunctionalBatchBuildTestrayCaseResult(
					testrayBuild, topLevelBuild, axisTestClassGroup, testClass);
			}
			else if (axisTestClassGroup instanceof JSUnitAxisTestClassGroup) {
				return new JSUnitBatchBuildTestrayCaseResult(
					testrayBuild, topLevelBuild, axisTestClassGroup,
					testClassMethod);
			}
			else if (axisTestClassGroup instanceof JUnitAxisTestClassGroup) {
				return new JUnitBatchBuildTestrayCaseResult(
					testrayBuild, topLevelBuild, axisTestClassGroup, testClass);
			}
			else if (axisTestClassGroup instanceof
						PlaywrightAxisTestClassGroup) {

				return new PlaywrightBatchBuildTestrayCaseResult(
					testrayBuild, topLevelBuild, axisTestClassGroup, testClass,
					testClassMethod);
			}
			else if (axisTestClassGroup instanceof
						SemVerModulesAxisTestClassGroup) {

				return new SemVerModulesBatchBuildTestrayCaseResult(
					testrayBuild, topLevelBuild, axisTestClassGroup, testClass);
			}
		}

		if (topLevelBuild instanceof SourceFormatBuild) {
			return new SFBatchBuildTestrayCaseResult(
				testrayBuild, topLevelBuild, axisTestClassGroup);
		}

		return new BatchBuildTestrayCaseResult(
			testrayBuild, topLevelBuild, axisTestClassGroup);
	}

	public static TestrayCaseResult newTestrayCaseResult(
		TestrayServer testrayServer, JSONObject jsonObject) {

		return new TestrayCaseResult(testrayServer, jsonObject);
	}

	public static TestrayCaseType newTestrayCaseType(
		TestrayServer testrayServer, JSONObject jsonObject) {

		return new TestrayCaseType(testrayServer, jsonObject);
	}

	public static TestrayComponent newTestrayComponent(
		TestrayProject testrayProject, JSONObject jsonObject) {

		return new TestrayComponent(testrayProject, jsonObject);
	}

	public static TestrayProductVersion newTestrayProductVersion(
		TestrayProject testrayProject, JSONObject jsonObject) {

		return new TestrayProductVersion(testrayProject, jsonObject);
	}

	public static TestrayProject newTestrayProject(
		TestrayServer testrayServer, JSONObject jsonObject) {

		return new TestrayProject(testrayServer, jsonObject);
	}

	public static TestrayRoutine newTestrayRoutine(String testrayRoutineURL) {
		TestrayRoutine testrayRoutine = _testrayRoutines.get(testrayRoutineURL);

		if (testrayRoutine != null) {
			return testrayRoutine;
		}

		try {
			Matcher testrayURLMatcher = _testrayURLPattern.matcher(
				testrayRoutineURL);

			if (!testrayURLMatcher.find()) {
				throw new RuntimeException(
					"Invalid Testray URL " + testrayRoutineURL);
			}

			testrayRoutine = new TestrayRoutine(new URL(testrayRoutineURL));

			_testrayRoutines.put(testrayRoutineURL, testrayRoutine);

			return testrayRoutine;
		}
		catch (MalformedURLException malformedURLException) {
			throw new RuntimeException(malformedURLException);
		}
	}

	public static TestrayRoutine newTestrayRoutine(
		TestrayProject testrayProject, JSONObject jsonObject) {

		return new TestrayRoutine(testrayProject, jsonObject);
	}

	public static TestrayRoutine newTestrayRoutine(
		TestrayServer testrayServer, JSONObject jsonObject) {

		return new TestrayRoutine(testrayServer, jsonObject);
	}

	public static TestrayRun newTestrayRun(
		TestrayBuild testrayBuild, JSONObject jsonObject) {

		return new TestrayRun(testrayBuild, jsonObject);
	}

	public static TestrayRun newTestrayRun(
		TestrayBuild testrayBuild, String batchName,
		List<File> propertiesFiles) {

		return new TestrayRun(testrayBuild, batchName, propertiesFiles);
	}

	public static TestrayServer newTestrayServer(String testrayServerURL) {
		TestrayServer testrayServer = _testrayServers.get(testrayServerURL);

		if (testrayServer != null) {
			return testrayServer;
		}

		Matcher testrayURLMatcher = _testrayURLPattern.matcher(
			testrayServerURL);

		if (!testrayURLMatcher.find()) {
			throw new RuntimeException(
				"Invalid Testray URL " + testrayServerURL);
		}

		testrayServer = new TestrayServer(testrayServerURL);

		_testrayServers.put(testrayServerURL, testrayServer);

		return testrayServer;
	}

	public static TestrayTeam newTestrayTeam(
		TestrayProject testrayProject, JSONObject jsonObject) {

		return new TestrayTeam(testrayProject, jsonObject);
	}

	public static TopLevelBuildTestrayCaseResult
		newTopLevelBuildTestrayCaseResult(
			TestrayBuild testrayBuild, TopLevelBuild topLevelBuild) {

		Long testrayBuildID = testrayBuild.getID();

		if (_topLevelBuildTestrayCaseResults.containsKey(testrayBuildID)) {
			return _topLevelBuildTestrayCaseResults.get(testrayBuildID);
		}

		if (testrayBuild == null) {
			throw new RuntimeException("Please set a Testray build");
		}

		if (topLevelBuild == null) {
			throw new RuntimeException("Please set a top level build");
		}

		_topLevelBuildTestrayCaseResults.put(
			testrayBuildID,
			new TopLevelBuildTestrayCaseResult(testrayBuild, topLevelBuild));

		return _topLevelBuildTestrayCaseResults.get(testrayBuildID);
	}

	private static final Map<Build, TestrayAttachmentRecorder>
		_testrayAttachmentRecorders = new HashMap<>();
	private static final Map<String, TestrayAttachmentUploader>
		_testrayAttachmentUploaders = new HashMap<>();
	private static final Map<String, TestrayRoutine> _testrayRoutines =
		new HashMap<>();
	private static final Map<String, TestrayServer> _testrayServers =
		new HashMap<>();
	private static final Pattern _testrayURLPattern = Pattern.compile(
		"https://(testray\\.liferay\\.com|webserver-testray2" +
			"(-prd\\d*|-uat\\d*)?.lfr.cloud)");
	private static final Map<Long, TopLevelBuildTestrayCaseResult>
		_topLevelBuildTestrayCaseResults = new HashMap<>();

}