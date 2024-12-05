/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.scancode;

import com.liferay.jenkins.results.parser.JenkinsResultsParserUtil;
import com.liferay.jenkins.results.parser.NotificationUtil;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Brittney Nguyen
 */
public class ScanCodeProject {

	public ScanCodeProject(String buildURL, String pipelineName) {
		_buildURL = buildURL;
		_pipelineName = pipelineName;
	}

	public void addFileInput(String filePath)
		throws IOException, TimeoutException {

		StringBuilder sb = new StringBuilder();

		sb.append("curl ");
		sb.append(_projectAPIURL);
		sb.append("add_input/ --form \"upload_file=@");
		sb.append(filePath);
		sb.append("\" --header \"Authorization:Token ");
		sb.append(_API_KEY);
		sb.append("\" --request POST ");

		Process process = JenkinsResultsParserUtil.executeBashCommands(
			sb.toString());

		try {
			JenkinsResultsParserUtil.readInputStream(process.getInputStream());
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void addPipeline(String pipelineName)
		throws IOException, TimeoutException {

		StringBuilder sb = new StringBuilder();

		sb.append("curl ");
		sb.append(_projectAPIURL);
		sb.append("add_pipeline/");
		sb.append(" --data ");

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(
			"execute_now", true
		).put(
			"pipeline", pipelineName
		);

		sb.append("'");
		sb.append(jsonObject);
		sb.append("'");
		sb.append(" --header ");
		sb.append(_CONTENT_TYPE);
		sb.append(" --header ");
		sb.append("\"Authorization:Token ");
		sb.append(_API_KEY);
		sb.append("\"");
		sb.append(" --request POST ");

		Process process = JenkinsResultsParserUtil.executeBashCommands(
			sb.toString());

		try {
			JenkinsResultsParserUtil.readInputStream(process.getInputStream());
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void checkComplianceAlerts(ComplianceAlertType complianceAlertType)
		throws IOException, TimeoutException {

		StringBuilder sb = new StringBuilder();

		sb.append("curl ");
		sb.append(_projectAPIURL);
		sb.append("compliance/?fail_level=");
		sb.append(complianceAlertType);
		sb.append(" --header ");
		sb.append(_CONTENT_TYPE);
		sb.append(" --header ");
		sb.append("\"Authorization:Token ");
		sb.append(_API_KEY);
		sb.append("\"");
		sb.append(" --request GET ");

		Process process = JenkinsResultsParserUtil.executeBashCommands(
			new String[] {sb.toString()});

		String output = JenkinsResultsParserUtil.readInputStream(
			process.getInputStream());

		output = output.trim();

		JSONObject outputJSONObject = new JSONObject(output);

		JSONObject complianceAlertsJSONObject = outputJSONObject.getJSONObject(
			"compliance_alerts");

		if (complianceAlertsJSONObject.isEmpty()) {
			return;
		}

		for (String key : complianceAlertsJSONObject.keySet()) {
			JSONObject complianceAlertJSONObject =
				complianceAlertsJSONObject.getJSONObject(key);

			if (!complianceAlertJSONObject.has(
					complianceAlertType.toString())) {

				continue;
			}

			JSONArray complianceAlertTypeJSONArray =
				complianceAlertJSONObject.getJSONArray(
					complianceAlertType.toString());

			_complianceAlertCountsMap.put(
				key + "-" + complianceAlertType,
				complianceAlertTypeJSONArray.length());
		}
	}

	public void checkComplianceAlerts(String complianceAlertTypeString)
		throws IOException, TimeoutException {

		checkComplianceAlerts(
			ComplianceAlertType.valueOf(complianceAlertTypeString));
	}

	public void downloadResultFiles() throws IOException {
		String scanCodeResultsDir = JenkinsResultsParserUtil.getBuildProperty(
			"scancode.results.dir");

		for (String resultFileExtension : _RESULT_FILES_EXTENSIONS) {
			String link = JenkinsResultsParserUtil.combine(
				_projectURL, "results/", resultFileExtension);

			URL url = new URL(link);

			File file = new File(
				JenkinsResultsParserUtil.combine(
					scanCodeResultsDir, _projectNameFromURL, ".",
					resultFileExtension));

			JenkinsResultsParserUtil.toFile(url, file);
		}

		String tarGzName = _projectNameFromURL + ".tar.gz";

		File resultsTarGzFile = new File(scanCodeResultsDir, tarGzName);

		JenkinsResultsParserUtil.tarGzip(
			new File(scanCodeResultsDir), resultsTarGzFile);

		uploadResultsToBucket(resultsTarGzFile.toString());
	}

	public JSONObject getAnalyzeDockerImageJSONObject(String dockerTag) {
		Matcher matcher = _dockerTagPattern.matcher(dockerTag);

		if (!matcher.find()) {
			throw new IllegalArgumentException(
				"Invalid Docker tag " + dockerTag);
		}

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(
			"execute_now", false
		).put(
			"input_urls", "docker://liferay/" + dockerTag
		).put(
			"labels",
			_getLabels(
				"docker", matcher.group("buildProfile"),
				matcher.group("releaseVersion"))
		).put(
			"name",
			JenkinsResultsParserUtil.combine(
				dockerTag, " Docker Scan-",
				_simpleDateFormat.format(new Date()))
		).put(
			"pipeline", _pipelineName
		);

		return jsonObject;
	}

	public String getComplianceAlertMessage(
		ComplianceAlertType complianceAlertType) {

		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, Integer> entry :
				_complianceAlertCountsMap.entrySet()) {

			String key = entry.getKey();

			if (!key.endsWith(complianceAlertType.toString())) {
				continue;
			}

			sb.append(complianceAlertType.getSlackEmoji());
			sb.append(" ");
			sb.append(
				key.replaceAll(
					"(.*)-" + Pattern.quote(complianceAlertType.toString()) +
						"$",
					"$1"));
			sb.append(":");
			sb.append(entry.getValue());
			sb.append(" ");
		}

		return sb.toString();
	}

	public String getComplianceAlertMessage(String complianceAlertTypeString) {
		return getComplianceAlertMessage(
			ComplianceAlertType.valueOf(complianceAlertTypeString));
	}

	public JSONObject getInspectPackagesJSONObject() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(
			"execute_now", true
		).put(
			"input_urls",
			"https://github.com/liferay/liferay-portal/archive/refs/heads" +
				"/master.tar.gz"
		).put(
			"labels", _getLabels("master")
		).put(
			"name", "Master Daily Scan-" + _simpleDateFormat.format(new Date())
		).put(
			"pipeline", _pipelineName
		);

		return jsonObject;
	}

	public JSONObject getMapDevelopToDeployJSONObject() throws IOException {
		JSONObject jsonObject = new JSONObject();

		List<String> inputURLS = new ArrayList<>();

		String tomcatURL = JenkinsResultsParserUtil.getBuildParameter(
			_buildURL, "TEST_PORTAL_RELEASE_TOMCAT_URL");

		inputURLS.add(tomcatURL + "#to");

		inputURLS.add(getReleaseTarballLink());
		inputURLS.add(
			JenkinsResultsParserUtil.getBuildProperty("scancode.tar.gz.url"));
		inputURLS.add(
			JenkinsResultsParserUtil.getBuildProperty(
				"scancode.config.file.url"));

		String portalReleaseVersion =
			JenkinsResultsParserUtil.getBuildParameter(
				_buildURL, "TEST_PORTAL_RELEASE_VERSION");

		jsonObject.put(
			"execute_now", true
		).put(
			"input_urls", inputURLS
		).put(
			"labels", _getLabels(portalReleaseVersion)
		).put(
			"name",
			JenkinsResultsParserUtil.combine(
				portalReleaseVersion, " Scan-",
				_simpleDateFormat.format(new Date()))
		).put(
			"pipeline", _pipelineName + ":Java,Javascript"
		);

		return jsonObject;
	}

	public String getPipelineName() {
		return _pipelineName;
	}

	public String getPipelineRunURL(String pipelineName) {
		try {
			StringBuilder sb = new StringBuilder();

			sb.append("curl ");
			sb.append(_projectAPIURL);
			sb.append(" --header ");
			sb.append(_CONTENT_TYPE);
			sb.append(" --header \"Authorization:Token ");
			sb.append(_API_KEY);
			sb.append("\" --request GET ");

			Process process = JenkinsResultsParserUtil.executeBashCommands(
				new String[] {sb.toString()});

			String output = JenkinsResultsParserUtil.readInputStream(
				process.getInputStream());

			output = output.trim();

			JSONObject outputJSONObject = new JSONObject(output);

			JSONArray runsJSONArray = outputJSONObject.getJSONArray("runs");

			for (int i = 0; i < runsJSONArray.length(); i++) {
				JSONObject runJSONObject = runsJSONArray.getJSONObject(i);

				if (!Objects.equals(
						runJSONObject.get("pipeline_name"), pipelineName)) {

					continue;
				}

				return runJSONObject.get(
					"url"
				).toString();
			}
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		return null;
	}

	public String getProjectID() {
		return _projectID;
	}

	public String getReleaseTarballLink() {
		String portalBranchUsername =
			JenkinsResultsParserUtil.getBuildParameter(
				_buildURL, "TEST_PORTAL_USER_NAME");

		String portalSHA = JenkinsResultsParserUtil.getBuildParameter(
			_buildURL, "TEST_PORTAL_RELEASE_GIT_ID");

		StringBuilder sb = new StringBuilder();

		sb.append("https://github.com/");
		sb.append(portalBranchUsername);
		sb.append("/liferay-portal-ee/archive/");
		sb.append(portalSHA);
		sb.append(".tar.gz");
		sb.append("#from");

		return sb.toString();
	}

	public String getS3URL() {
		return _s3URL;
	}

	public void invokeScan() throws IOException, TimeoutException {
		StringBuilder sb = new StringBuilder();

		sb.append("curl ");
		sb.append(_API_URL);

		JSONObject jsonObject = null;

		if (_pipelineName.equals("inspect_packages")) {
			jsonObject = getInspectPackagesJSONObject();
		}
		else if (_pipelineName.equals("analyze_docker_image")) {
			String dockerTag = JenkinsResultsParserUtil.getBuildParameter(
				_buildURL, "LIFERAY_DOCKER_TAG");

			jsonObject = getAnalyzeDockerImageJSONObject(dockerTag);
		}
		else if (_pipelineName.equals("map_deploy_to_develop")) {
			jsonObject = getMapDevelopToDeployJSONObject();
		}

		sb.append(" --data ");
		sb.append("'");
		sb.append(jsonObject);
		sb.append("'");
		sb.append(" --header ");
		sb.append(_CONTENT_TYPE);
		sb.append(" --header ");
		sb.append("\"Authorization:Token ");
		sb.append(_API_KEY);
		sb.append("\"");
		sb.append(" --request POST ");

		Process process = JenkinsResultsParserUtil.executeBashCommands(
			sb.toString());

		String output = null;

		try {
			output = JenkinsResultsParserUtil.readInputStream(
				process.getInputStream());

			output = output.trim();

			JSONObject outputJSONObject = new JSONObject(output);

			_projectAPIURL = outputJSONObject.getString("url");
			_projectID = outputJSONObject.getString("uuid");
			_projectName = outputJSONObject.getString("name");
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void sendSlackNotification(String s3URL) {
		StringBuilder sb = new StringBuilder();

		String subject = "ScanCode pipeline is complete";

		if (_hasErrors()) {
			subject = ":red-alert: Release blocker :red-alert:";
		}

		String complianceAlertMessages =
			getComplianceAlertMessage(ComplianceAlertType.ERROR) +
				getComplianceAlertMessage(ComplianceAlertType.WARNING);

		if (!JenkinsResultsParserUtil.isNullOrEmpty(complianceAlertMessages)) {
			sb.append("*Compliance alerts:* ");
			sb.append(complianceAlertMessages);
			sb.append("\n");
		}

		sb.append("*Project link:* ");
		sb.append("<");
		sb.append(_projectURL);
		sb.append("|");
		sb.append(_projectName);
		sb.append(">\n");
		sb.append("*Pipeline:* ");

		if (_pipelineName.equals("inspect_packages")) {
			sb.append(_pipelineName);
			sb.append(", populate_purldb");
		}
		else {
			sb.append(_pipelineName);
		}

		sb.append("\n");
		sb.append("*Status:* ");
		sb.append(
			_projectStatuses.toString(
			).replaceAll(
				"(^\\[|\\]$)", ""
			));

		if (_s3URL != null) {
			sb.append("\n*S3 tar.gz:* ");
			sb.append("<");
			sb.append(_s3URL);
			sb.append("|");
			sb.append(_projectNameFromURL + ".tar.gz");
			sb.append(">");
		}

		NotificationUtil.sendSlackNotification(
			sb.toString(), "#scancode-io", ":liferay-ci:", subject,
			"Liferay CI");
	}

	public void setProjectURL(String uid, String name) {
		name = name.replaceAll(
			"[.:]", ""
		).toLowerCase();

		name = name.replace(" ", "-");

		uid = uid.substring(0, uid.indexOf("-"));

		_projectNameFromURL = name + "-" + uid;

		_projectURL =
			"https://liferay1.scancode.io/project/" + name + "-" + uid + "/";
	}

	public void startPipeline(String pipelineName) throws Exception {
		String pipelineRunURL = getPipelineRunURL(pipelineName);

		if (JenkinsResultsParserUtil.isNullOrEmpty(pipelineRunURL)) {
			throw new IOException("Unable to start " + pipelineName);
		}

		StringBuilder sb = new StringBuilder();

		sb.append("curl ");
		sb.append(pipelineRunURL);
		sb.append("start_pipeline/ --header \"Authorization:Token ");
		sb.append(_API_KEY);
		sb.append("\" --request POST ");

		Process process = JenkinsResultsParserUtil.executeBashCommands(
			sb.toString());

		try {
			JenkinsResultsParserUtil.readInputStream(process.getInputStream());
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void uploadResultsToBucket(String tarGzFilePath) {
		File tarGzFile = new File(tarGzFilePath);

		try {
			ScanCodeS3Bucket scanCodeS3Bucket = ScanCodeS3Bucket.getInstance();

			scanCodeS3Bucket.createScanCodeS3Object(
				"inbox/" + tarGzFile.getName(), tarGzFile);

			_s3URL = scanCodeS3Bucket.getS3URL();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void waitForScan(String pipelineName) {
		StringBuilder sb = new StringBuilder();

		sb.append("curl ");
		sb.append(_projectAPIURL);
		sb.append(" --header ");
		sb.append(_CONTENT_TYPE);
		sb.append(" --header ");
		sb.append("\"Authorization:Token ");
		sb.append(_API_KEY);
		sb.append("\"");
		sb.append(" --request GET ");

		while (true) {
			try {
				Process process = JenkinsResultsParserUtil.executeBashCommands(
					new String[] {sb.toString()});

				String output = JenkinsResultsParserUtil.readInputStream(
					process.getInputStream());

				output = output.trim();

				JSONObject outputJSONObject = new JSONObject(output);

				JSONArray jsonArray = outputJSONObject.getJSONArray("runs");

				Object run = jsonArray.get(0);

				if (pipelineName.equals("populate_purldb")) {
					run = jsonArray.get(1);
				}

				JSONObject runJSONObject = new JSONObject(run.toString());

				String projectStatus = runJSONObject.getString("status");

				System.out.println(
					JenkinsResultsParserUtil.combine(
						"Project status for ", pipelineName, ": ",
						projectStatus));

				if (!projectStatus.equals("running") &&
					!projectStatus.equals("queued")) {

					_projectStatuses.add(projectStatus);

					break;
				}

				Thread.sleep(10 * 60 * 1000);
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		setProjectURL(_projectID, _projectName);
	}

	public static enum ComplianceAlertType {

		ERROR("error", ":red_circle:"),
		WARNING("warning", ":large_yellow_circle:");

		public String getSlackEmoji() {
			return _slackEmoji;
		}

		public String toString() {
			return _name;
		}

		private ComplianceAlertType(String name, String slackEmoji) {
			_name = name;
			_slackEmoji = slackEmoji;
		}

		private final String _name;
		private final String _slackEmoji;

	}

	private List<String> _getLabels(String... labelsArray) {
		List<String> labels = new ArrayList<>();

		labels.add("automated");

		for (String label : labelsArray) {
			labels.add(label);
		}

		return labels;
	}

	private boolean _hasErrors() {
		for (String key : _complianceAlertCountsMap.keySet()) {
			if (key.endsWith(ComplianceAlertType.ERROR.toString())) {
				return true;
			}
		}

		return false;
	}

	private static final String _API_KEY;

	private static final String _API_URL =
		"https://liferay1.scancode.io/api/projects/";

	private static final String _CONTENT_TYPE =
		"'Content-Type: application/json;'";

	private static final String[] _RESULT_FILES_EXTENSIONS = {
		"attribution", "cyclonedx", "spdx", "xls"
	};

	private static final Map<String, Integer> _complianceAlertCountsMap =
		new HashMap<>();
	private static final Pattern _dockerTagPattern = Pattern.compile(
		"(?<buildProfile>portal|dxp):(?<releaseVersion>" +
			"\\d+.\\d+.\\d+[.\\d+]*-(ga|u)\\d+|\\d{4}.[qQ]\\d+.\\d+)");

	static {
		try {
			_API_KEY = JenkinsResultsParserUtil.getBuildProperty(
				"scancode.api.key");
		}
		catch (IOException ioException) {
			throw new RuntimeException(
				"Unable to get ScanCode API key", ioException);
		}
	}

	private final String _buildURL;
	private final String _pipelineName;
	private String _projectAPIURL;
	private String _projectID;
	private String _projectName;
	private String _projectNameFromURL;
	private final List<String> _projectStatuses = new ArrayList<>();
	private String _projectURL;
	private String _s3URL;
	private final SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(
		"MMM d yy HH:mm:ss");

}