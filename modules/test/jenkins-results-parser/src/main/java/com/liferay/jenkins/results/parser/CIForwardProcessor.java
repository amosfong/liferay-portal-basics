/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Peter Yoo
 */
public class CIForwardProcessor {

	public CIForwardProcessor(
		String consoleLogURL, boolean force, File gitRepositoryDir,
		PullRequest pullRequest, String recipientUsername) {

		_consoleLogURL = consoleLogURL;
		_force = force;

		if (gitRepositoryDir == null) {
			throw new IllegalArgumentException(
				"Git repository directory is null");
		}

		if (!gitRepositoryDir.exists()) {
			throw new IllegalArgumentException(
				"Git repository directory does not exist");
		}

		_gitRepositoryDir = gitRepositoryDir;

		if (pullRequest == null) {
			throw new IllegalArgumentException("Pull request is null");
		}

		String pullRequestState = pullRequest.getState();

		if (pullRequestState.equals("closed")) {
			throw new IllegalArgumentException("Pull request is closed");
		}

		_pullRequest = pullRequest;

		if (JenkinsResultsParserUtil.isNullOrEmpty(recipientUsername)) {
			throw new IllegalArgumentException("Recipient username is null");
		}

		_recipientUsername = recipientUsername;
	}

	public void execute() {
		String forwardedPullRequestURL = null;

		try {
			List<String> openForwardedPullRequestUrls =
				_getOpenForwardedPullRequestUrls();

			if (!openForwardedPullRequestUrls.isEmpty()) {
				_pullRequest.addComment(
					_getHasOpenForwardedPullRequestCommentBody(
						openForwardedPullRequestUrls));

				return;
			}

			if (!_isForwardEligible()) {
				_pullRequest.addComment(_getUnsuccessfulCommentBody());

				return;
			}

			_pullRequest.addComment(_getPassedCommentBody());

			final String senderUsername;

			try {
				senderUsername = JenkinsResultsParserUtil.getBuildProperty(
					"github.ci.username");
			}
			catch (IOException ioException) {
				throw new RuntimeException(
					"Unable to get build property", ioException);
			}

			final String initialComment =
				_getCIForwardPullRequestInitialComment();

			Retryable<String> retryable = new Retryable<String>(
				true, 3, (int)(_RETRY_PERIOD / 1000), true) {

				@Override
				public String execute() {
					try {
						String pullRequestURL = _pullRequest.forward(
							_getCIForwardCommentBody(initialComment),
							_consoleLogURL, _recipientUsername,
							_getCIForwardBranchName(), senderUsername,
							_gitRepositoryDir);

						if (_force) {
							GitHubRemoteGitRepository
								gitHubRemoteGitRepository =
									_pullRequest.getGitHubRemoteGitRepository();

							gitHubRemoteGitRepository.addLabel(
								"bcf5db", "", "ci:forward:force");

							GitHubRemoteGitRepository.Label
								ciForwardForceLabel =
									gitHubRemoteGitRepository.getLabel(
										"ci:forward:force");

							_pullRequest.addLabel(ciForwardForceLabel);
						}

						_pullRequest.close();

						StringBuilder sb = new StringBuilder();

						sb.append("Original Pull Request URL: ");
						sb.append(_pullRequest.getURL());
						sb.append("\nNew Pull Request URL: ");
						sb.append(pullRequestURL);

						NotificationUtil.sendSlackNotification(
							sb.toString(), "#ci-notifications",
							"Pull request successfully forwarded");

						return pullRequestURL;
					}
					catch (Exception exception) {
						if (exception instanceof RuntimeException) {
							throw (RuntimeException)exception;
						}

						throw new RuntimeException(exception);
					}
				}

				@Override
				protected String getRetryMessage(int retryCount) {
					if (retryCount < maxRetries) {
						_pullRequest.addComment(_getRetryCommentBody());
					}

					return null;
				}

			};

			try {
				forwardedPullRequestURL = retryable.executeWithRetries();
			}
			catch (GitHubSecondaryRateLimitRuntimeException
						gitHubSecondaryRateLimitRuntimeException) {

				StringBuilder sb = new StringBuilder();

				sb.append("Secondary rate limit exceeded\n");
				sb.append("Pull Request URL: ");
				sb.append(_pullRequest.getURL());
				sb.append("\nConsole log URL: ");
				sb.append(_consoleLogURL);

				NotificationUtil.sendSlackNotification(
					sb.toString(), "#ci-notifications", ":liferay-ci:",
					"Unable to forward pull request", "Liferay CI");

				throw new GitHubSecondaryRateLimitRuntimeException(
					gitHubSecondaryRateLimitRuntimeException.getGitHubApiUrl(),
					gitHubSecondaryRateLimitRuntimeException.
						getRetryAfterSeconds(),
					sb.toString(), gitHubSecondaryRateLimitRuntimeException);
			}
			catch (Exception exception) {
				exception.printStackTrace();

				StringBuilder sb = new StringBuilder();

				sb.append("Unknown exception\n");
				sb.append("Pull Request URL: ");
				sb.append(_pullRequest.getURL());
				sb.append("\nConsole log URL: ");
				sb.append(_consoleLogURL);

				NotificationUtil.sendSlackNotification(
					sb.toString(), "#ci-notifications", ":liferay-ci:",
					"Unable to forward pull request", "Liferay CI");

				_pullRequest.addComment(_getFailureCommentBody());
			}
		}
		catch (Exception exception) {
			try {
				_pullRequest.addComment(_getUnsuccessfulCommentBody());
			}
			catch (IOException ioException) {
				throw new RuntimeException(
					"Unable to post failure comment", ioException);
			}

			throw new RuntimeException(
				"Unable to forward pull request", exception);
		}

		PullRequest forwardedPullRequest = new PullRequest(
			forwardedPullRequestURL);

		try {
			for (PullRequest.Comment comment :
					_getSuiteTestResultGitHubComments()) {

				forwardedPullRequest.addComment(comment.getBody());
			}
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}

		if (!JenkinsResultsParserUtil.isNullOrEmpty(forwardedPullRequestURL)) {
			_pullRequest.addComment(
				_getSuccessCommentBody(forwardedPullRequestURL));
		}
	}

	private PullRequest.Comment _findMostRecentTestResultComment(
		String testSuiteName, boolean requiredPassing,
		List<PullRequest.Comment> comments) {

		StringBuilder sb = new StringBuilder();

		if (requiredPassing) {
			sb.append("heavy_check_mark: ci:test:");
		}
		else {
			sb.append(": ci:test:");
		}

		sb.append(testSuiteName);

		String testSuiteString = sb.toString();

		for (PullRequest.Comment comment : comments) {
			String commentBody = comment.getBody();

			if (commentBody.contains(testSuiteString)) {
				return comment;
			}
		}

		return null;
	}

	private String[] _getBuildPropertyAsArray(String propertyName)
		throws IOException {

		String propertyValue = JenkinsResultsParserUtil.getProperty(
			JenkinsResultsParserUtil.getBuildProperties(), propertyName,
			_pullRequest.getGitRepositoryName(), _pullRequest.getRefName());

		if (JenkinsResultsParserUtil.isNullOrEmpty(propertyValue)) {
			return new String[0];
		}

		return propertyValue.split("\\s*,\\s*");
	}

	private String _getCIForwardBranchName() throws IOException {
		return JenkinsResultsParserUtil.combine(
			JenkinsResultsParserUtil.getBuildProperty(
				"ci.forward.branch.name.prefix"),
			_pullRequest.getSenderBranchName(), "-pr-",
			_pullRequest.getNumber(), "-sender-",
			_pullRequest.getSenderUsername());
	}

	private String _getCIForwardCommentBody(String initialComment) {
		StringBuilder sb = new StringBuilder();

		sb.append("Forwarded from: ");
		sb.append(_pullRequest.getURL());

		int forwardAttempts = 0;
		Date firstForwardAttemptDate = null;

		for (PullRequest.Comment comment : _pullRequest.getComments()) {
			String commentBody = comment.getBody();

			if ((commentBody == null) ||
				!commentBody.startsWith("ci:forward")) {

				continue;
			}

			forwardAttempts++;

			if (firstForwardAttemptDate == null) {
				firstForwardAttemptDate = comment.getCreatedDate();
			}
		}

		String duration = null;

		if (firstForwardAttemptDate != null) {
			duration = JenkinsResultsParserUtil.toDurationString(
				JenkinsResultsParserUtil.getCurrentTimeMillis() -
					firstForwardAttemptDate.getTime());
		}

		if (forwardAttempts > 0) {
			sb.append(" (Took ");
			sb.append(forwardAttempts);
			sb.append(" `ci:forward` ");
			sb.append(
				JenkinsResultsParserUtil.getNounForm(
					forwardAttempts, "attempts", "attempt"));
			sb.append(" in ");
			sb.append(duration);
			sb.append(")");
		}

		if (!JenkinsResultsParserUtil.isNullOrEmpty(_consoleLogURL)) {
			sb.append("\n[Console](");
			sb.append(_consoleLogURL);
			sb.append(")\n\n");
		}

		String senderUsername = _pullRequest.getSenderUsername();

		sb.append("@");
		sb.append(senderUsername);

		String receiverUsername = _pullRequest.getReceiverUsername();

		if (!senderUsername.equals(receiverUsername)) {
			sb.append("\n");
			sb.append("@");
			sb.append(receiverUsername);
		}

		if (!JenkinsResultsParserUtil.isNullOrEmpty(initialComment)) {
			sb.append("\n");
			sb.append(initialComment);
		}

		return sb.toString();
	}

	private String _getCIForwardPullRequestInitialComment() throws IOException {
		StringBuilder sb = new StringBuilder();

		String pullRequestBody = _pullRequest.getBody();

		if (!pullRequestBody.isEmpty()) {
			sb.append("\n");
			sb.append("Original pull request comment:\n");
			sb.append(pullRequestBody);
		}

		return sb.toString();
	}

	private List<String> _getFailedRequiredPassingTestSuiteNames()
		throws IOException {

		List<String> passingTestSuiteNames =
			_pullRequest.getPassingTestSuiteNames();

		String joinedPassingTestSuiteNames = JenkinsResultsParserUtil.join(
			",", passingTestSuiteNames);

		System.out.println(
			"passing test suites: " + joinedPassingTestSuiteNames);

		List<String> failingRequiredPassingTestSuiteNames = new ArrayList<>(
			passingTestSuiteNames.size());

		String[] requiredPassingTestSuiteNames =
			_getRequiredPassingTestSuiteNames();

		String joinedRequiredPassingTestSuiteNames =
			JenkinsResultsParserUtil.join(",", requiredPassingTestSuiteNames);

		System.out.println(
			"required passing test suites: " +
				joinedRequiredPassingTestSuiteNames);

		for (String requiredPassingTestSuiteName :
				requiredPassingTestSuiteNames) {

			if (!passingTestSuiteNames.contains(requiredPassingTestSuiteName)) {
				failingRequiredPassingTestSuiteNames.add(
					requiredPassingTestSuiteName);
			}
		}

		return failingRequiredPassingTestSuiteNames;
	}

	private String _getFailureCommentBody() {
		StringBuilder sb = new StringBuilder();

		sb.append("Error has occurred while forwarding pull request to `");
		sb.append(_recipientUsername);
		sb.append("`.\n");
		sb.append("Please try again later or contact ");
		sb.append("the CI team for assistance.\n");

		if (!JenkinsResultsParserUtil.isNullOrEmpty(_consoleLogURL)) {
			sb.append("See console log for details: [Full Console](");
			sb.append(_consoleLogURL);
			sb.append("consoleText");
			sb.append(")");
		}

		return sb.toString();
	}

	private String _getGitHubApiSearchUrl() throws IOException {
		List<String> filters = Arrays.asList(
			"author:" +
				JenkinsResultsParserUtil.getBuildProperty("github.ci.username"),
			"head:" + _getCIForwardBranchName(), "is:pr", "is:open",
			JenkinsResultsParserUtil.combine(
				"repo:", _recipientUsername, "/",
				_pullRequest.getGitRepositoryName()));

		return JenkinsResultsParserUtil.getGitHubApiSearchUrl(filters);
	}

	private List<PullRequest.Comment> _getGitHubCIComments() {
		try {
			List<PullRequest.Comment> comments = _pullRequest.getComments();

			Collections.reverse(comments);

			String gitHubCIUsername = JenkinsResultsParserUtil.getBuildProperty(
				"github.ci.username");

			List<PullRequest.Comment> gitHubCIComments = new ArrayList<>(
				comments.size());

			for (PullRequest.Comment comment : comments) {
				if (gitHubCIUsername.equals(comment.getUserLogin())) {
					gitHubCIComments.add(comment);
				}
			}

			return gitHubCIComments;
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private String _getHasOpenForwardedPullRequestCommentBody(
		List<String> openForwardedPullRequestURLs) {

		StringBuilder sb = new StringBuilder();

		sb.append(
			"This pull request already has open forwarded pull request(s):\n");

		for (String openForwardedPullRequestURL :
				openForwardedPullRequestURLs) {

			sb.append(openForwardedPullRequestURL);
			sb.append("\n");
		}

		sb.append("\nPull request will not be forwarded to ");
		sb.append("`");
		sb.append(_recipientUsername);
		sb.append("`.\n");
		sb.append("[Console](");
		sb.append(_consoleLogURL);
		sb.append(")\n");

		return sb.toString();
	}

	private List<String> _getIncompleteRequiredCompletedTestSuiteNames()
		throws IOException {

		List<String> completedTestSuiteNames =
			_pullRequest.getCompletedTestSuiteNames();

		String joinedCompletedTestSuiteNames = JenkinsResultsParserUtil.join(
			",", completedTestSuiteNames);

		System.out.println(
			"completed test suites: " + joinedCompletedTestSuiteNames);

		List<String> incompleteRequiredCompletedTestSuiteNames =
			new ArrayList<>(completedTestSuiteNames.size());

		String[] requiredCompletedTestSuiteNames =
			_getRequiredCompletedTestSuiteNames();

		String joinedRequiredCompletedTestSuiteNames =
			JenkinsResultsParserUtil.join(",", requiredCompletedTestSuiteNames);

		System.out.println(
			"required completed test suites: " +
				joinedRequiredCompletedTestSuiteNames);

		for (String requiredCompletedTestSuiteName :
				requiredCompletedTestSuiteNames) {

			if (!completedTestSuiteNames.contains(
					requiredCompletedTestSuiteName)) {

				incompleteRequiredCompletedTestSuiteNames.add(
					requiredCompletedTestSuiteName);
			}
		}

		return incompleteRequiredCompletedTestSuiteNames;
	}

	private List<String> _getOpenForwardedPullRequestUrls() throws IOException {
		List<String> openForwardedPullRequestUrls = new ArrayList<>();

		JSONObject jsonObject = JenkinsResultsParserUtil.toJSONObject(
			_getGitHubApiSearchUrl());

		JSONArray itemsJSONArray = jsonObject.getJSONArray("items");

		if ((itemsJSONArray == null) || itemsJSONArray.isEmpty()) {
			return openForwardedPullRequestUrls;
		}

		for (int i = 0; i < itemsJSONArray.length(); i++) {
			JSONObject itemsJSONObject = itemsJSONArray.getJSONObject(i);

			openForwardedPullRequestUrls.add(
				itemsJSONObject.optString("html_url"));
		}

		return openForwardedPullRequestUrls;
	}

	private String _getPassedCommentBody() {
		StringBuilder sb = new StringBuilder();

		sb.append("All required test suite(s) ");

		if (_force) {
			sb.append("completed");
		}
		else {
			sb.append("passed");
		}

		sb.append(".\n");
		sb.append("Forwarding pull request to `");
		sb.append(_recipientUsername);
		sb.append("`.\n");

		if (!JenkinsResultsParserUtil.isNullOrEmpty(_consoleLogURL)) {
			sb.append("[Console](");
			sb.append(_consoleLogURL);
			sb.append(")\n");
		}

		return sb.toString();
	}

	private String[] _getRequiredCompletedTestSuiteNames() throws IOException {
		return _getBuildPropertyAsArray(
			JenkinsResultsParserUtil.combine(
				"ci.forward", _force ? ".force" : "",
				".required.completed.suites"));
	}

	private String[] _getRequiredPassingTestSuiteNames() throws IOException {
		return _getBuildPropertyAsArray(
			JenkinsResultsParserUtil.combine(
				"ci.forward", _force ? ".force" : "",
				".required.passing.suites"));
	}

	private String _getRetryCommentBody() {
		StringBuilder sb = new StringBuilder();

		sb.append(
			"Error has occurred while attempting to forward pull request to `");
		sb.append(_recipientUsername);
		sb.append("`. Retrying in ");
		sb.append(JenkinsResultsParserUtil.toDurationString(_RETRY_PERIOD));
		sb.append("...");

		if (!JenkinsResultsParserUtil.isNullOrEmpty(_consoleLogURL)) {
			sb.append("\nSee console log for detail:[Full Console](");
			sb.append(_consoleLogURL);
			sb.append(")\n");
		}

		return sb.toString();
	}

	private String _getSuccessCommentBody(String forwardedPullRequestURL) {
		StringBuilder sb = new StringBuilder();

		sb.append("Pull request has been successfully forwarded to  ");
		sb.append(forwardedPullRequestURL);

		if (!JenkinsResultsParserUtil.isNullOrEmpty(_consoleLogURL)) {
			sb.append("\n[Console](");
			sb.append(_consoleLogURL);
			sb.append(")");
		}

		return sb.toString();
	}

	private List<PullRequest.Comment> _getSuiteTestResultGitHubComments()
		throws IOException {

		Set<String> testSuiteNames = new HashSet<>();

		List<String> requiredCompletedTestSuiteNames = Arrays.asList(
			_getRequiredCompletedTestSuiteNames());

		testSuiteNames.addAll(requiredCompletedTestSuiteNames);

		List<String> requiredPassingTestSuiteNames = Arrays.asList(
			_getRequiredPassingTestSuiteNames());

		testSuiteNames.addAll(requiredPassingTestSuiteNames);

		List<PullRequest.Comment> filteredComments = new ArrayList<>(
			testSuiteNames.size());

		List<PullRequest.Comment> comments = _getGitHubCIComments();

		for (String testSuiteName : testSuiteNames) {
			boolean requiredPassing = requiredPassingTestSuiteNames.contains(
				testSuiteName);

			PullRequest.Comment comment = _findMostRecentTestResultComment(
				testSuiteName, requiredPassing, comments);

			if ((comment != null) && !filteredComments.contains(comment)) {
				filteredComments.add(comment);
			}
		}

		Collections.sort(filteredComments);

		return filteredComments;
	}

	private String _getUnsuccessfulCommentBody() throws IOException {
		StringBuilder sb = new StringBuilder();

		List<String> incompleteRequiredCompletedTestSuiteNames =
			_getIncompleteRequiredCompletedTestSuiteNames();

		if (!incompleteRequiredCompletedTestSuiteNames.isEmpty()) {
			sb.append("Not all required test suite(s) completed:\n");

			for (String requiredCompletedTestSuiteName :
					_getRequiredCompletedTestSuiteNames()) {

				sb.append("`");
				sb.append(requiredCompletedTestSuiteName);
				sb.append("`\n");
			}
		}

		List<String> failedRequiredPassingTestSuiteNames =
			_getFailedRequiredPassingTestSuiteNames();

		if (!failedRequiredPassingTestSuiteNames.isEmpty()) {
			sb.append("Not all required test suite(s) passed:\n");

			for (String requiredPassingTestSuiteName :
					_getRequiredPassingTestSuiteNames()) {

				sb.append("`");
				sb.append(requiredPassingTestSuiteName);
				sb.append("`");

				if (requiredPassingTestSuiteName.equals("stable")) {
					sb.append(" - If you believe that the stable test ");
					sb.append("failures were caused by flaky tests, please ");
					sb.append("contact QA for confirmation and rerun the ");
					sb.append("test.");
				}

				sb.append("\n");
			}
		}

		sb.append("\nPull request will not be forwarded to ");
		sb.append("`");
		sb.append(_recipientUsername);
		sb.append("`.\n");

		if (JenkinsResultsParserUtil.isNullOrEmpty(_consoleLogURL)) {
			sb.append("[Console](");
			sb.append(_consoleLogURL);
			sb.append(")\n");
		}

		return sb.toString();
	}

	private boolean _isForwardEligible() throws IOException {
		List<String> incompleteRequiredCompletedTestSuiteNames =
			_getIncompleteRequiredCompletedTestSuiteNames();

		if (!incompleteRequiredCompletedTestSuiteNames.isEmpty()) {
			return false;
		}

		List<String> failedRequiredPassingTestSuiteNames =
			_getFailedRequiredPassingTestSuiteNames();

		if (!failedRequiredPassingTestSuiteNames.isEmpty()) {
			return false;
		}

		return true;
	}

	private static final long _RETRY_PERIOD = 1000L * 60L;

	private final String _consoleLogURL;
	private final boolean _force;
	private final File _gitRepositoryDir;
	private final PullRequest _pullRequest;
	private final String _recipientUsername;

}