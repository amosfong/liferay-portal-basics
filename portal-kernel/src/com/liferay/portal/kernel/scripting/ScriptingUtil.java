/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.scripting;

import com.liferay.portal.kernel.module.service.Snapshot;

import java.util.Map;
import java.util.Set;

/**
 * @author Alberto Montero
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ScriptingUtil {

	public static void clearCache(String language) throws ScriptingException {
		Scripting scripting = _scriptingSnapshot.get();

		scripting.clearCache(language);
	}

	public static ScriptingExecutor createScriptingExecutor(
		String language, boolean executeInSeparateThread) {

		Scripting scripting = _scriptingSnapshot.get();

		return scripting.createScriptingExecutor(
			language, executeInSeparateThread);
	}

	public static Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, String language, String script)
		throws ScriptingException {

		Scripting scripting = _scriptingSnapshot.get();

		return scripting.eval(
			allowedClasses, inputObjects, outputNames, language, script);
	}

	public static Set<String> getSupportedLanguages() {
		Scripting scripting = _scriptingSnapshot.get();

		return scripting.getSupportedLanguages();
	}

	private static final Snapshot<Scripting> _scriptingSnapshot =
		new Snapshot<>(ScriptingUtil.class, Scripting.class);

}