/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.test.clazz;

import com.liferay.jenkins.results.parser.test.clazz.group.BatchTestClassGroup;

import java.io.File;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class PluginsGulpTestClass extends BaseTestClass {

	protected PluginsGulpTestClass(
		BatchTestClassGroup batchTestClassGroup, File testBaseDirName) {

		super(batchTestClassGroup, testBaseDirName);

		addTestClassMethod("gulpfile.js");
	}

	protected PluginsGulpTestClass(
		BatchTestClassGroup batchTestClassGroup, JSONObject jsonObject) {

		super(batchTestClassGroup, jsonObject);
	}

}