/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.check;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.source.formatter.util.FileUtil;
import com.liferay.source.formatter.util.SourceFormatterUtil;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Alan Huang
 */
public class PropertiesPlaywrightTestCheck extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		if (!fileName.endsWith("/test.properties")) {
			return content;
		}

		if (absolutePath.contains("/modules/test/playwright/tests/")) {
			Properties properties = new Properties();

			properties.load(new StringReader(content));

			String testrayMainComponentName = properties.getProperty(
				_TESTRAY_MAIN_COMPONENT_NAME);

			if (testrayMainComponentName == null) {
				addMessage(
					fileName,
					"Missing property \"" + _TESTRAY_MAIN_COMPONENT_NAME +
						"\" in test.properties");

				return content;
			}

			List<String> buildGradleFileNames = _getBuildGradleFileNames();

			if (ListUtil.isEmpty(buildGradleFileNames)) {
				return content;
			}

			String moduleName = _getModuleName(absolutePath);
			List<String> testPropertiesFileNames = new ArrayList<>();

			for (String buildGradleFileName : buildGradleFileNames) {
				if (buildGradleFileName.endsWith(
						"/" + moduleName + "/build.gradle")) {

					int x = buildGradleFileName.lastIndexOf("/");

					testPropertiesFileNames.add(
						buildGradleFileName.substring(0, x) +
							"/test.properties");
				}
			}

			testPropertiesFileNames = ListUtil.filter(
				testPropertiesFileNames,
				testPropertiesFileName ->
					!testPropertiesFileName.endsWith(
						"/modules/apps/" + moduleName + "/test.properties") &&
					!testPropertiesFileName.endsWith(
						"/modules/dxp/apps/" + moduleName +
							"/test.properties"));

			if (ListUtil.isEmpty(testPropertiesFileNames) ||
				(testPropertiesFileNames.size() != 1)) {

				return content;
			}

			String testPropertiesFileName = testPropertiesFileNames.get(0);

			File file = new File(testPropertiesFileName);

			if (!file.exists()) {
				int x = testPropertiesFileName.indexOf("/modules/");
				int y = testPropertiesFileName.lastIndexOf(StringPool.SLASH);

				addMessage(
					fileName,
					"Missing test.properties in " +
						testPropertiesFileName.substring(x + 1, y));

				return content;
			}

			_checkMissingPlaywrightTestProjectProperty(
				fileName, FileUtil.read(file), moduleName,
				file.getAbsolutePath());
		}

		if (absolutePath.contains("/modules/apps/") ||
			absolutePath.contains("/modules/dxp/apps/")) {

			String moduleName = _getModuleName(absolutePath);

			if (absolutePath.endsWith(
					"/modules/apps/" + moduleName + "/test.properties") ||
				absolutePath.endsWith(
					"/modules/dxp/apps/" + moduleName + "/test.properties")) {

				return content;
			}

			File file = new File(
				getPortalDir() + "/modules/test/playwright/tests/" +
					moduleName);

			if (!file.exists()) {
				return content;
			}

			file = new File(file, "test.properties");

			if (!file.exists()) {
				addMessage(
					fileName,
					"Missing test.properties in playwright/tests/" +
						moduleName);

				return content;
			}

			_checkMissingPlaywrightTestProjectProperty(
				fileName, content, moduleName, null);
		}

		return content;
	}

	private void _checkMissingPlaywrightTestProjectProperty(
			String fileName, String content, String moduleName,
			String moduleTestPropertiesFilePath)
		throws IOException {

		Properties properties = new Properties();

		properties.load(new StringReader(content));

		List<String> relevantRuleNames = ListUtil.fromString(
			properties.getProperty(_RELEVANT_RULE_NAMES), StringPool.COMMA);

		if (ListUtil.isEmpty(relevantRuleNames)) {
			addMessage(
				fileName,
				"Missing property \"" + _RELEVANT_RULE_NAMES +
					"\" in test.properties for Playwright tests");

			return;
		}

		for (String relevantRuleName : relevantRuleNames) {
			String playwrightProjectsIncludesPropertyName =
				StringBundler.concat(
					"playwright.projects.includes",
					"[playwright-js-tomcat90-mysql57][relevant][",
					relevantRuleName, "]");

			List<String> playwrightProjectsIncludesList = ListUtil.fromString(
				properties.getProperty(playwrightProjectsIncludesPropertyName),
				StringPool.COMMA);

			String additionalMessage = "";

			if (moduleTestPropertiesFilePath != null) {
				int x = moduleTestPropertiesFilePath.indexOf("/modules/");

				additionalMessage =
					" in " + moduleTestPropertiesFilePath.substring(x + 1);
			}

			if (ListUtil.isEmpty(playwrightProjectsIncludesList)) {
				addMessage(
					fileName,
					StringBundler.concat(
						"Missing property \"",
						playwrightProjectsIncludesPropertyName, "\"",
						additionalMessage));
			}
			else if (!playwrightProjectsIncludesList.contains(moduleName)) {
				addMessage(
					fileName,
					StringBundler.concat(
						"Missing property value \"", moduleName, "\" in \"",
						playwrightProjectsIncludesPropertyName, "\"",
						additionalMessage));
			}
		}
	}

	private synchronized List<String> _getBuildGradleFileNames()
		throws IOException {

		if (_buildGradleFileNames != null) {
			return _buildGradleFileNames;
		}

		_buildGradleFileNames = SourceFormatterUtil.scanForFileNames(
			getPortalDir().getCanonicalPath() + "/modules",
			new String[] {"apps/**/build.gradle", "dxp/apps/**/build.gradle"});

		return _buildGradleFileNames;
	}

	private String _getModuleName(String absolutePath) {
		int x = absolutePath.lastIndexOf(StringPool.SLASH);

		int y = absolutePath.lastIndexOf(StringPool.SLASH, x - 1);

		return absolutePath.substring(y + 1, x);
	}

	private static final String _RELEVANT_RULE_NAMES = "relevant.rule.names";

	private static final String _TESTRAY_MAIN_COMPONENT_NAME =
		"testray.main.component.name";

	private List<String> _buildGradleFileNames;

}